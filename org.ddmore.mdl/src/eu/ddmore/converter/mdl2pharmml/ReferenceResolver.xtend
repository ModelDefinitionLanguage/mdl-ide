package eu.ddmore.converter.mdl2pharmml

import java.util.HashSet
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ParameterObject
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.DataDerivedBlock
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.impl.MclObjectImpl
import org.ddmore.mdl.mdl.MclObject
import java.util.ArrayList

class ReferenceResolver{
	extension MdlPrinter mdlPrinter;
	
	new(Mcl m, MdlPrinter mdlPrinter) {
    	this.mdlPrinter = mdlPrinter
    	prepareCollections(m);
 	}
	
	protected var deriv_vars = new HashSet<String>();	 
	
	protected var eps_vars = newHashMap   //EPSs   - Random variables, level 1
	protected var eta_vars = newHashMap	  //ETAs   - Random variables, level 2
	protected var level_vars = newHashMap //       - Input variables, level attribute			
	
	//List of PharmML declared symbols and corresponding blocks 
	protected var ind_vars = new ArrayList<String>();
	protected var vm_err_vars = new ArrayList<String>(); 
	protected var vm_mdl_vars = new ArrayList<String>();
	protected var cm_vars = new ArrayList<String>();
	protected var pm_vars = new ArrayList<String>();	  
	protected var om_vars = new ArrayList<String>();	  
	protected var sm_vars = new ArrayList<String>();	 
		
	protected def prepareCollections(Mcl m){
		for (o: m.objects){
			m.setDerivativeVariables;
			if (o.modelObject != null){
	  			setLevelVars(o.modelObject);
	  			setRandomVariables(o.modelObject);

				//Independent variables
				ind_vars = o.modelObject.getIndependentVars();
			
				//VariabilityModel definitions
				vm_err_vars = o.modelObject.getLevelVars("1");
				vm_mdl_vars = o.modelObject.getLevelVars("2")

				//CovariateModel				
				cm_vars = o.modelObject.getCovariateVars();
					
				//StructuralModel
				sm_vars = o.modelObject.getStructuralVars;	
					
				//ParameterModel
				pm_vars = o.modelObject.getParameters;	
					
				//ObservationModel
				om_vars = o.modelObject.getObservationVars;	
			}
			if (o.parameterObject != null){
				//ParameterModel
				var parameters = o.parameterObject.getParameters;	
				if (parameters.size > 0)
				for (p: parameters)
					if (!pm_vars.contains(p)) pm_vars.add(p);
			}
		}
	}
	
	protected def setDerivativeVariables(Mcl m){
		deriv_vars.clear();
		for (o: m.objects){
		var iterator = o.eAllContents();
		    while (iterator.hasNext()){
		    	var obj = iterator.next();
		    	if (obj instanceof SymbolDeclaration){
		    		var s = obj as SymbolDeclaration;
		    		if (s.list != null && s.symbolName != null){
		    			val deriv = getAttributeExpression(s.list.arguments, AttributeValidator::attr_req_deriv.name);
						if (deriv != null && !deriv_vars.contains(s.symbolName.name)){
		    				deriv_vars.add(s.symbolName.name);
		    			}
		    		}
		    	}
		    }
	    }
	}
	
	protected def getReferenceBlock(String name){
		//try to find by name
		if (vm_err_vars.contains(name)) return "vm_err";
		if (vm_mdl_vars.contains(name)) return "vm_mdl";
		if (cm_vars.contains(name)) return "cm";
		if (om_vars.contains(name)) return "om";	
		if (sm_vars.contains(name)) return "sm";	
		if (pm_vars.contains(name)) return "pm";	
		return "";
	}	
	
	//+ Return input variables with use=idv (individual)
	protected def getIndependentVars(ModelObject obj){
		var independentVars = newArrayList;
		for (block: obj.blocks){
			if (block.inputVariablesBlock != null){
				for (s: block.inputVariablesBlock.variables){
					if (s.list != null && s.symbolName != null){
						var use = getAttribute(s.list.arguments, AttributeValidator::attr_use.name);
						if (use.equals(UseType::IDV.toString) && !independentVars.contains(s.symbolName.name)) 
							independentVars.add(s.symbolName.name);
					}
				}
			}
		}
		return independentVars;
	}
		
	//+ Return a list of covariate variables per object
	protected def getCovariateVars(ModelObject obj){
		var covariateVars = newArrayList;
		for (b: obj.blocks){
			if (b.inputVariablesBlock != null){
				for (s: b.inputVariablesBlock.variables){
					if (s.list != null && s.symbolName != null){
						var use = getAttribute(s.list.arguments, AttributeValidator::attr_use.name);
						if (use.equals(UseType::COVARIATE.toString)) {
							if (!covariateVars.contains(s.symbolName.name))
								covariateVars.add(s.symbolName.name);
						}
					}
				}
			}					
		}
		return covariateVars;		
	}
	
	protected def getDerivedVariables(DataDerivedBlock b){
		var derivedVars = newArrayList;
		for (st: b.variables)
			if (st.symbolName.name != null)
				derivedVars.add(st.symbolName.name)
		return derivedVars;
	}
	
	//+Returns declarations for ParameterModel
	protected def getParameters(ModelObject obj){		
		var parameters = newArrayList;
		for (b: obj.blocks){
			//Model object, GROUP_VARIABLES (covariate parameters)
			if (b.groupVariablesBlock != null){
				for (st: b.groupVariablesBlock.statements){
					if (st.variable != null){
						if (st.variable.symbolName.name != null)
							parameters.add(st.variable.symbolName.name)
					}							
				}
			}	
			//Model object, RANDOM_VARIABLES_DEFINITION
			if (b.randomVariableDefinitionBlock != null){
				for (s: b.randomVariableDefinitionBlock.variables){
					if (s.symbolName != null && s.randomList != null){
						parameters.add(s.symbolName.name);
					}
				} 
	  		}
	  		//Model object, INDIVIDUAL_VARIABLES
			if (b.individualVariablesBlock != null){
				for (s: b.individualVariablesBlock.variables){
					if (s.symbolName != null)
						parameters.add(s.symbolName.name);
				} 
	  		}
	  	}
	  	return parameters;
	}
	
	//+Returns declarations for ParameterModel
	protected def getParameters(ParameterObject obj){		
		var parameters = newArrayList;
		for (b: obj.blocks){
			//Parameter object, STRUCTURAL
			if (b.structuralBlock != null){
				for (id: b.structuralBlock.parameters) 
					if (id.symbolName != null)
						parameters.add(id.symbolName.name);
			}
			//ParameterObject, VARIABILITY
			if (b.variabilityBlock != null){
				for (st: b.variabilityBlock.statements){
					if (st.parameter != null && st.parameter.symbolName != null)
						parameters.add(st.parameter.symbolName.name);
				} 
			}
		}
  		return parameters;
	}
	
	//+Returns declarations in ObservationModel
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
	
	protected def setLevelVars(ModelObject o){
		var tmp = o.getLevelVars("1");
		for (v: tmp){
			if (level_vars.get(v) == null)
				level_vars.put(v, "1");
		}
		tmp = o.getLevelVars("2");
		for (v: tmp){
			if (level_vars.get(v) == null)
				level_vars.put(v, "2");
		}
	}
		
	protected def getLevelVars(ModelObject o, String levelId) {
		var levelVars = newArrayList;
		for (b: o.blocks){
			if(b.inputVariablesBlock != null){
				for (s: b.inputVariablesBlock.variables){
					if (s.list != null && s.symbolName != null){
						var level = s.list.arguments.getAttribute(AttributeValidator::attr_level.name);
						if (level.equals(levelId)){
							if (!levelVars.contains(s.symbolName.name)){
								levelVars.add(s.symbolName.name);
							}
						}
					}
				}
			}
		}
		return levelVars;
	}	
		
	//Assign indices to variability parameters ($ETA, $ESP)
	protected def setRandomVariables(ModelObject o){
    	var i = 1; var j = 1; 
		for (b: o.blocks){
	  		if (b.randomVariableDefinitionBlock != null){
				for (s: b.randomVariableDefinitionBlock.variables) {
					if (s.randomList != null && s.symbolName != null){	
						var level = s.randomList.arguments.getAttribute(AttributeValidator::attr_level.name);
						if (level_vars.get(level) != null){
							if (level_vars.get(level).equals("2")){
								if (eta_vars.get(s.symbolName.name) == null){
									eta_vars.put(s.symbolName.name, i);
									i = i + 1;
								}
							}
							if (level_vars.get(level).equals("1"))
								if (eps_vars.get(s.symbolName.name) == null){
									eps_vars.put(s.symbolName.name, j);
									j = j + 1;
								}	
						}
					}
	  			}
	  		}
  		}
	}
	
	protected def getTaskObject(MOGObject mog){
		for (o: mog.objects){
			var container = o.eContainer;
			if (container instanceof MclObjectImpl){
				val mclObject = container as MclObject;
				if (mclObject.taskObject != null) return mclObject;
			}
		}
		return null;
	}	
	
	protected def getModelObject(MOGObject mog){
		for (o: mog.objects){
			var container = o.eContainer;
			if (container instanceof MclObjectImpl){
				val mclObject = container as MclObject;
				if (mclObject.modelObject != null) return mclObject;
			}
		}
		return null;
	}	

	protected def getParameterObject(MOGObject mog){
		for (o: mog.objects){
			var container = o.eContainer;
			if (container instanceof MclObjectImpl){
				val mclObject = container as MclObject;
				if (mclObject.parameterObject != null) return mclObject;
			}
		}
		return null;
	}	

	protected def getDataObject(MOGObject mog){
		for (o: mog.objects){
			var container = o.eContainer;
			if (container instanceof MclObjectImpl){
				val mclObject = container as MclObject;
				if (mclObject.dataObject != null) return mclObject;
			}
		}
		return null;
	}	
}