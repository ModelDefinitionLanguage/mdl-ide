package eu.ddmore.converter.mdl2pharmml

import java.util.HashMap
import java.util.HashSet
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.types.UseType

class ReferenceResolver{
	val Mcl mcl;
	extension MdlPrinter mdlPrinter = new MdlPrinter();
	new(Mcl m) {
    	this.mcl = m;
    	prepareCollections(m);
 	}
	
	//protected val LEVEL_UNDEF = 0;	
	protected var eps_vars = newHashMap   //EPSs   - Random variables, level 1
	protected var eta_vars = newHashMap	  //ETAs   - Random variables, level 2
	protected var level_vars = newHashMap //       - Input variables, level attribute			
	
	//List of PharmML declared symbols and corresponding blocks 
	protected var ind_vars = new HashSet<String>();
	protected var vm_err_vars = new HashMap<String, HashSet<String>>(); 
	protected var vm_mdl_vars = new HashMap<String, HashSet<String>>();
	protected var cm_vars = new HashMap<String, HashSet<String>>();
	protected var pm_vars = new HashMap<String, HashSet<String>>();	  
	protected var om_vars = new HashMap<String, HashSet<String>>();	  
	protected var sm_vars = new HashMap<String, HashSet<String>>();	 
		
	def prepareCollections(Mcl m){
		for (o: m.objects){
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

				var mdlVars = o.modelObject.getLevelVars("2");
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
	
	//+Return name of PharmML block for a given reference
	def getReferenceBlock(FullyQualifiedSymbolName ref){
		if (ref.object != null){
			return getReferenceBlock(ref.object.name, ref.symbol.name);
		} else {
			//try to find by name
			return getReferenceBlock(ref.symbol.name);
		}
	}	
	
	//TODO: correct function (does not work!)
	def getReferenceBlock(String name){
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
	
	def getReferenceBlock(String objName, String name){
		//try to find by name
		var source = vm_err_vars.get(objName);
		if (source != null)
			if (source.contains(name)) return "vm_err." +objName
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
		return objName;
	}	
	
	//+ Return input variables with use=idv (individual)
	def getIndependentVars(ModelObject obj){
		var independentVars = new HashSet<String>();
		for (block: obj.blocks){
			if (block.inputVariablesBlock != null){
				for (s: block.inputVariablesBlock.variables){
					if (s.expression != null){
						if (s.expression.list != null){
							var use = s.expression.list.arguments.getAttribute(AttributeValidator::attr_use.name);
							if (use.equals(UseType::USE_IDV) && !independentVars.contains(s.symbolName.name)) 
								independentVars.add(s.symbolName.name);
						}
					}
				}
			}
		}
		return independentVars;
	}
		
	//+ Return a list of covariate variables per object
	def getCovariateVars(ModelObject obj){
		var covariateVars = new HashSet<String>();
		for (b: obj.blocks){
			if (b.inputVariablesBlock != null){
				for (s: b.inputVariablesBlock.variables){
					if (s.expression != null){
						if (s.expression.list != null){
							var use = s.expression.list.arguments.getAttribute(AttributeValidator::attr_use.name);
							if (use.equals(UseType::USE_COVARIATE)) {
								if (!covariateVars.contains(s.symbolName.name))
									covariateVars.add(s.symbolName.name);
							}
						}
					}												
				}
			}					
		}
		return covariateVars;		
	}
	
	//+Returns declarations for ParameterModel
	def getParameters(ModelObject obj){		
		var parameters = new HashSet<String>();
		for (b: obj.blocks){
			//Model object, GROUP_VARIABLES (covariate parameters)
			if (b.groupVariablesBlock != null){
				for (st: b.groupVariablesBlock.statements){
					if (st.statement != null){
						parameters.addAll(st.statement.getSymbols());
					}							
				}
			}	
			//Model object, RANDOM_VARIABLES_DEFINITION
			if (b.randomVariableDefinitionBlock != null){
				for (s: b.randomVariableDefinitionBlock.variables){
					if (eps_vars.get(s.symbolName.name) != null)
						parameters.add(s.symbolName.name);
				} 
	  		}
	  		//Model object, INDIVIDUAL_VARIABLES
			if (b.individualVariablesBlock != null){
				for (s: b.individualVariablesBlock.statements){
					parameters.addAll(s.getSymbols());
				} 
	  		}
	  	}
	  	return parameters;
	}
	
	//+Returns declarations for ParameterModel
	def getParameters(ParameterObject obj){		
		var parameters = new HashSet<String>();
		for (b: obj.blocks){
			//Parameter object, STRUCTURAL
			if (b.structuralBlock != null){
				for (id: b.structuralBlock.parameters) 
					parameters.add(id.symbolName.name);
			}
			//ParameterObject, VARIABILITY
			if (b.variabilityBlock != null){
				for (st: b.variabilityBlock.statements){
					if (st.parameter != null)
						parameters.add(st.parameter.symbolName.name);
				} 
			}
		}
  		return parameters;
	}
	
	//+Returns declarations in ObservationModel
	def getObservationVars(ModelObject obj){
		var observationVars = new HashSet<String>();
		for (b: obj.blocks){
			if (b.observationBlock != null){
				for (st: b.observationBlock.statements){
					if (st.symbol != null){//!TODO: revise
						observationVars.add(st.symbol.symbolName.name);
						if (st.symbol.expression != null){
							if (st.symbol.expression.expression != null){
								var classifiedVars = st.symbol.expression.expression.getReferences;
								observationVars.addAll(classifiedVars.keySet);
							}
						}
					}
				}
			}
		}
		return observationVars;
	}
	
	//+ Return a list of structural variables per object
	def getStructuralVars(ModelObject obj){
		var structuralVars = new HashSet<String>();
		for (b: obj.blocks){
			if (b.modelPredictionBlock != null){
				for (st: b.modelPredictionBlock.statements){
					if (st.statement != null) {
						structuralVars.addAll(st.statement.getSymbols);
					} else 
						if (st.odeBlock != null){
							for (s: st.odeBlock.statements){
								structuralVars.addAll(s.getSymbols);
							}
						}
				}
			}			
		}
		return structuralVars;
	}
	
	//+Collect symbol names from a block
	def HashSet<String> getSymbols(BlockStatement b){
		var symbols = new HashSet<String>();
		if (b.symbol != null){
			if (!symbols.contains(b.symbol.symbolName.name)) symbols.add(b.symbol.symbolName.name);
		}
		if (b.statement != null){
			val s = b.statement;
			if (s.ifStatement != null){
				symbols.addAll(s.ifStatement.getSymbols);
			}
			if (s.elseStatement != null){
				symbols.addAll(s.elseStatement.getSymbols);
			}		
			if (s.ifBlock != null){
				for (bb:s.ifBlock.statements)
					symbols.addAll(bb.getSymbols);
			}
			if (s.elseBlock != null){
				for (bb:s.elseBlock.statements)
					symbols.addAll(bb.getSymbols);
			}			
		}
		return symbols;
	}
	
	def isDataVariable(String s){
		return (eps_vars.get(s) != null)
	}
	
	def isIndependentVariable(String s){
		return (eta_vars.get(s) != null)
	}
	
	//+ For each reference, define its purpose
	def getReferences(Expression expr){
		var classifiedVars = new HashMap<String, String>();
		var iterator = expr.eAllContents();
	    while (iterator.hasNext()){
	    	var obj = iterator.next();
	    	if (obj instanceof FullyQualifiedSymbolName){
	    		var ref = obj as FullyQualifiedSymbolName;
	    		if (classifiedVars.get(ref.toStr) == null)
			    	if (eps_vars.get(ref.toStr) != null)
			    		classifiedVars.put(ref.toStr, "random")
			    	else 	
			    		classifiedVars.put(ref.toStr, "other");
	    	}
	    }
	    return classifiedVars;
	}

	def setLevelVars(ModelObject o){
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
		
	def protected getLevelVars(ModelObject o, String levelId) {
		var levelVars = new HashSet<String>();
		for (b: o.blocks){
			if(b.inputVariablesBlock != null){
				for (SymbolDeclaration s: b.inputVariablesBlock.variables){
					if (s.expression != null){
						if (s.expression.list != null){
							var level = s.expression.list.arguments.getAttribute(AttributeValidator::attr_level.name);
							if (level.equals(levelId)){
								if (!levelVars.contains(s.symbolName.name)){
									levelVars.add(s.symbolName.name);
								}
							}
						}
					}
				}
			}
		}
		return levelVars;
	}	
		
	//Assign indices to variability parameters ($ETA, $ESP)
	def protected setRandomVariables(ModelObject o){
    	var i = 1; var j = 1; 
		for (b: o.blocks){
	  		if (b.randomVariableDefinitionBlock != null){
				for (s: b.randomVariableDefinitionBlock.variables) {
					if (s.randomList != null){	
						var level = s.randomList.arguments.getAttribute(AttributeValidator::attr_level.name);
						val id = s.symbolName.name;
						if (level_vars.get(level) != null){
							if (level_vars.get(level).equals("2")){
								if (eta_vars.get(id) == null){
									eta_vars.put(id, i);
									i = i + 1;
								}
							}
							if (level_vars.get(level).equals("1"))
								if (eps_vars.get(id) == null){
									eps_vars.put(id, j);
									j = j + 1;
								}	
							}
						}
	  			}
	  		}
  		}
	}
}