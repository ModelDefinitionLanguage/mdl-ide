package eu.ddmore.converter.mdl2pharmml

import java.util.HashMap
import java.util.HashSet
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.SymbolName
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.DataDerivedBlock
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.impl.MclObjectImpl
import org.ddmore.mdl.mdl.MclObject
import java.util.ArrayList
import java.util.List

class ReferenceResolver{
	extension MdlPrinter mdlPrinter;
	
	new(Mcl m, MdlPrinter mdlPrinter) {
    	this.mdlPrinter = mdlPrinter
    	prepareCollections(m);
 	}
	
	protected var deriv_vars = new HashSet<String>();	 
	
	//protected val LEVEL_UNprotected def = 0;	
	protected var eps_vars = newHashMap   //EPSs   - Random variables, level 1
	protected var eta_vars = newHashMap	  //ETAs   - Random variables, level 2
	protected var level_vars = newHashMap //       - Input variables, level attribute			
	
	//List of PharmML declared symbols and corresponding blocks 
	protected var ind_vars = new HashSet<String>();
	protected var vm_err_vars = new HashMap<String, List<String>>(); 
	protected var vm_mdl_vars = new HashMap<String, List<String>>();
	protected var cm_vars = new HashMap<String, List<String>>();
	protected var pm_vars = new HashMap<String, List<String>>();	  
	protected var om_vars = new HashMap<String, List<String>>();	  
	protected var sm_vars = new HashMap<String, List<String>>();	 
		
	protected def prepareCollections(Mcl m){
		for (o: m.objects){
			m.setDerivativeVariables;
			if (o.modelObject != null){
	  			setLevelVars(o.modelObject);
	  			setRandomVariables(o.modelObject);

				//Independent variables
				var indVars = o.modelObject.getIndependentVars();
				if (indVars.size > 0)
					ind_vars.addAll(indVars);	
			
				//VariabilityModel definitions
				var errorVars = o.modelObject.getLevelVars("1");
				if (errorVars.size > 0)
					vm_err_vars.put(o.objectName.name, errorVars);

				var mdlVars = o.modelObject.getLevelVars("2")
				if (mdlVars.size > 0)
					vm_mdl_vars.put(o.objectName.name, mdlVars);

				//CovariateModel				
				var covariateVars = o.modelObject.getCovariateVars();
				if (covariateVars.size > 0)
					cm_vars.put(o.objectName.name, covariateVars);
					
				//StructuralModel
				var structuralVars = o.modelObject.getStructuralVars;	
				if (structuralVars.size > 0)
					sm_vars.put(o.objectName.name, structuralVars);
					
				//ParameterModel
				var parameters = o.modelObject.getParameters;	
				if (parameters.size > 0)
					pm_vars.put(o.objectName.name, parameters);
					
				//ObservationModel
				var observationVars = o.modelObject.getObservationVars;	
				if (observationVars.size > 0)
					sm_vars.put(o.objectName.name, observationVars);
					
			}
			if (o.parameterObject != null){
				//ParameterModel
				var parameters = o.parameterObject.getParameters;	
				if (parameters.size > 0)
					pm_vars.put(o.objectName.name, parameters);
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
	
	protected def getDerivedVariables(DataDerivedBlock b){
		var derivedVars = newArrayList;
		for (st: b.variables)
			if (st.symbolName.name != null)
				derivedVars.add(st.symbolName.name)
		return derivedVars;
	}
	
	protected def getReferenceBlock(String name){
		//try to find by name
		for (set: vm_err_vars.entrySet)
			if (set.value.contains(name)) return "vm_err." + set.key;
		for (set:vm_mdl_vars.entrySet)
			if (set.value.contains(name)) return "vm_mdl." + set.key;
		for (set: cm_vars.entrySet)
			if (set.value.contains(name)) return "cm." + set.key;
		for (set: om_vars.entrySet)
			if (set.value.contains(name)) return "om." + set.key;	
		for (set: sm_vars.entrySet)
			if (set.value.contains(name)) return "sm." + set.key;	
		for (set: pm_vars.entrySet)
			if (set.value.contains(name)) return "pm." + set.key;	
		return "";
	}	
	
	protected def getReferenceBlock(String objName, String name){
		//try to find by name
		var source = vm_err_vars.get(objName);
		if (source != null)
			if (source.contains(name)) return "vm_err." + objName
		source = vm_mdl_vars.get(name);
		if (source != null)
			if (source.contains(name)) return "vm_mdl." + objName
		source = cm_vars.get(objName);
		if (source != null)
			if (source.contains(name)) return "cm." + objName
		source = om_vars.get(objName);
		if (source != null)
			if (source.contains(name)) return "om." + objName	
		source = sm_vars.get(objName);
		if (source != null)
			if (source.contains(name)) return "sm." + objName	
		source = pm_vars.get(objName);
		if (source != null)
			if (source.contains(name)) return "pm." + objName	
		return "";
	}	
	
	//+ Return input variables with use=idv (individual)
	protected def getIndependentVars(ModelObject obj){
		var independentVars = new HashSet<String>();
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
		var covariateVars = new ArrayList<String>();
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
	
	//+Returns declarations for ParameterModel
	protected def getParameters(ModelObject obj){		
		var parameters = new ArrayList<String>();
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
		var parameters = new ArrayList<String>();
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
		var observationVars = new ArrayList<String>();
		for (b: obj.blocks){
			if (b.observationBlock != null){
				for (st: b.observationBlock.variables){
					if (st.symbolName != null)
						observationVars.add(st.symbolName.name);
					if (st.expression != null){
						var classifiedVars = st.expression.getReferences;
						observationVars.addAll(classifiedVars.keySet);
					}
				}
			}
		}
		return observationVars;
	}
	
	//+ Return a list of structural variables per object
	protected def getStructuralVars(ModelObject obj){
		var structuralVars = new ArrayList<String>();
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
	
	//+ For each reference, define its purpose
	protected def getReferences(Expression expr){
		var classifiedVars = new HashMap<String, String>();
		var iterator = expr.eAllContents();
	    while (iterator.hasNext()){
	    	var obj = iterator.next();
	    	if (obj instanceof SymbolName){
	    		var ref = obj as SymbolName;
	    		if (classifiedVars.get(ref.name) == null)
			    	if (eps_vars.get(ref.name) != null)
			    		classifiedVars.put(ref.name, "random")
			    	else 	
			    		classifiedVars.put(ref.name, "other");
	    	}
	    }
	    return classifiedVars;
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
		var levelVars = new ArrayList<String>();
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