package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.ModelObject
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.MOGObject
import java.util.ArrayList
import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.mdl.LevelType
import java.util.HashMap

class ReferenceResolver{
	extension MdlPrinter mdlPrinter = MdlPrinter::getInstance();
	
	new(MOGObject mog) {
    	mog.prepareCollections;
 	}
	
	//List of PharmML variables in corresponding blocks 
	protected var vm_err_vars = new HashMap<String, Integer>(); 
	protected var vm_mdl_vars = new HashMap<String, Integer>();
	protected var cm_vars = new ArrayList<String>();
	protected var pm_vars = new ArrayList<String>();	  
	protected var om_vars = new ArrayList<String>();	  
	protected var sm_vars = new ArrayList<String>();	
	protected var cm_assigned_vars = new ArrayList<String>();
		
	protected def prepareCollections(MOGObject mog){
		for (o: Utils::getMOGObjects(mog)){
			if (o.modelObject != null) {
				//VariabilityModel definitions
				vm_err_vars = o.modelObject.getLevelVars(LevelType::OBSERVATION);
				vm_mdl_vars = o.modelObject.getLevelVars(LevelType::MODEL)
				//CovariateModel				
				cm_vars = o.modelObject.getCovariateVars;
				cm_assigned_vars = o.modelObject.getAssignedCovariateVars;
				//StructuralModel
				sm_vars = o.modelObject.getStructuralVars;	
				//ParameterModel
				pm_vars = o.modelObject.getParameters;
				//ObservationModel
				om_vars = o.modelObject.getObservationVars;	
			}
		}
	}
		
	protected def getReferenceBlock(String name){
		if (vm_err_vars.containsKey(name)) return "vm_err";
		if (vm_mdl_vars.containsKey(name)) return "vm_mdl";
		if (cm_vars.contains(name)) return "cm";
		if (om_vars.contains(name)) return "om";	
		if (sm_vars.contains(name)) return "sm";	
		if (pm_vars.contains(name)) return "pm";	
		return "";
	}	
		
	//+ Return a list of covariate variables per object
	protected def getCovariateVars(ModelObject obj){
		var covariateVars = newArrayList;
		for (b: obj.blocks){
			if (b.covariateBlock != null){
				for (s: b.covariateBlock.variables){
					if (s.symbolName != null){
						if (!covariateVars.contains(s.symbolName.name))
							covariateVars.add(s.symbolName.name);
					}
				}
			}					
		}
		return covariateVars;		
	}
	
	//Return covariates with assigned expressions which are not transformations of other covariates
	protected def getAssignedCovariateVars(ModelObject obj){
		var assignedVars = newArrayList;
		for (b: obj.blocks){
			if (b.covariateBlock != null){
				for (s: b.covariateBlock.variables){
					if (s.symbolName != null && s.expression != null){
						var dependencies = Utils::getDependencies(s.expression);
						var isTransformed = false;
						for (v: dependencies)
							if (cm_vars.contains(v)) isTransformed = true;
						if (!isTransformed){
							assignedVars.add(s.symbolName.name);
						}
					}
				}
			}					
		}
		return assignedVars;		
	}
	
	
	//+ Returns declarations for ParameterModel
	protected def getParameters(ModelObject obj){		
		var parameters = newArrayList;
		for (b: obj.blocks){
			//GROUP_VARIABLES (covariate parameters)
			if (b.groupVariablesBlock != null){
				for (st: b.groupVariablesBlock.statements){
					if (st.variable != null){
						if (st.variable.symbolName.name != null)
							parameters.add(st.variable.symbolName.name)
					}							
				}
			}	
			//RANDOM_VARIABLES_DEFINITION
			if (b.randomVariableDefinitionBlock != null){
				for (s: b.randomVariableDefinitionBlock.variables){
					if (s.symbolName != null && s.randomList != null){
						parameters.add(s.symbolName.name);
					}
				} 
	  		}
	  		//INDIVIDUAL_VARIABLES
			if (b.individualVariablesBlock != null){
				for (s: b.individualVariablesBlock.variables){
					if (s.symbolName != null)
						parameters.add(s.symbolName.name);
				} 
	  		}
			//STRUCTURAL_PARAMETERS
			if (b.structuralParametersBlock != null){
				for (id: b.structuralParametersBlock.parameters) 
					if (id.symbolName != null)
						parameters.add(id.symbolName.name);
			}
			//VARIABILITY_PARAMETERS
			if (b.variabilityParametersBlock != null){
				for (id: b.variabilityParametersBlock.parameters){
					if (id.symbolName != null)
						parameters.add(id.symbolName.name);
				} 
			}
	  	}
	  	return parameters;
	}
	
	//+ Returns declarations in ObservationModel
	protected def getObservationVars(ModelObject obj){
		var observationVars = newArrayList;
		for (b: obj.blocks){
			if (b.observationBlock != null){
				for (st: b.observationBlock.variables){
					if (st.symbolName != null)
						observationVars.add(st.symbolName.name);
				}
			}
		}
		return observationVars;
	}
	
	//+ Return a list of structural variables per object
	protected def getStructuralVars(ModelObject obj){
		var structuralVars = newArrayList;
		for (b: obj.blocks){
			if (b.modelPredictionBlock != null){
				for (st: b.modelPredictionBlock.statements){
					if (st.variable != null && st.variable.symbolName != null) {
						structuralVars.add(st.variable.symbolName.name);
					} else 
						if (st.odeBlock != null){
							for (s: st.odeBlock.variables){
								if (s.symbolName != null) {
									structuralVars.add(s.symbolName.name);
								}
							}
						}
				}
			}			
		}
		return structuralVars;
	}
	
	//+ Returns a map of covariates with levels
	protected def getLevelVars(ModelObject o, LevelType type) {
		var levelVars = new HashMap<String, Integer>;
		for (b: o.blocks){
			if(b.variabilityBlock != null){
				for (s: b.variabilityBlock.variables){
					if (s.list != null && s.symbolName != null){
						var varType = s.list.arguments.getAttributeExpression(AttributeValidator::attr_type_level.name);
						var level = s.list.arguments.getAttribute(AttributeValidator::attr_level.name);
						var x = 0;
						try{
							x = Integer::parseInt(level);
						} catch (NumberFormatException e){}
						if (//default type = model or explicitly defined requested type 
							(varType == null && type.toString().equals(LevelType::MODEL.toString)) 
							|| (varType != null && varType.toStr.equals(type.toString))){
							if (!levelVars.containsKey(s.symbolName.name)){
								levelVars.put(s.symbolName.name, x);
							}
						}
					}
				}
			}
		}
		return levelVars;
	}	
}