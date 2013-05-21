package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.FileBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.HeaderBlockImpl;
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;

import com.google.common.collect.ImmutableMap;
 

public class MdlJavaValidator extends AbstractMdlJavaValidator {

	final static String MSG_VARIABLE_DEFINED = "A variable with such name already exists";
	final static String MSG_PARAMETER_DEFINED = "A parameter with such name already exists";
	final static String MSG_FUNCTION_DEFINED = "A function with such name is already defined";
	final static String MSG_FUNCTION_UNKNOWN = "Unknown function";
	final static String MSG_SYMBOL_UNKNOWN = "Variable or parameter not declared or conditionally declared";
	final static String MSG_PARAMETER_UNKNOWN = "Parameter not declared or conditionally declared";
	final static String MSG_VARIABLE_UNKNOWN = "Variable not declared or conditionally declared";
	final static String MSG_ATTRIBUTE_UNKNOWN = "Unknown attribute";
	final static String MSG_ATTRIBUTE_MISSING = "Required attribute is missing";

	//private enum VAL_RES {OK, ERROR, WARNING}
	
	final static List<String> attr_structural = Arrays.asList("value", "lo", "hi", "fix", "units", "transform");
	final static List<String> attr_req_structural = Arrays.asList("value");

	final static List<String> attr_variability = Arrays.asList("value", "type", "fix", "units", "transform");
	final static List<String> attr_req_variability = Arrays.asList("value");
	
	final static List<String> attr_variability_subblock = Arrays.asList("name", "type", "fix");
	final static List<String> attr_req_variability_subblock = Arrays.asList("name");
	
	final static List<String> attr_header = Arrays.asList("type", "units", "recode", "boundaries");
	final static List<String> attr_req_header = Arrays.asList("type");

	final static List<String> attr_file = Arrays.asList("source", "ignore", "inputformat");
	final static List<String> attr_req_file = Arrays.asList("source");

	final static List<String> attr_design = Arrays.asList("source", "units", "interp", "idv");
	final static List<String> attr_req_design = Arrays.asList("source");
	
	final static List<String> attr_inputVariables = Arrays.asList("value", "use", "units", "type", "level");
	
	final static List<String> attr_random = Arrays.asList("type", "mean", "variance", "level");
	final static List<String> attr_req_random = Arrays.asList("type", "mean", "variance");
	
	static final Map<String, String> defaultAttributeValues = ImmutableMap.of(
	        "fix", "false",
			"type", "SD",
			"inputformat", "NONMEM"
	);
	
	//List of recognized mathematical functions
	final static List<String> standardFunctions = Arrays.asList(
			//PharmML
			"exp", "ln", "minus", "factorial",   
			"sin", "cos", "tan", "sec",         
			"csc", "cot", "sinh", "csch", "coth", "arcsin", "arccos", "arctan",      
			"arcsec", "arccsc", "arccot", "arcsinh",     
			"arccosh","arctanh", "arcsech", "arccsch",     
			"arccoth", "floor", "abs", "ceiling", "logit",
			//MDL
			"sqrt", "seq",
			//TEL
			"update",
			//TOOL SPECIFIC
			"runif", "errorexit", "PHI"
		);
	
	//1=observation, level 2=subject, level 3=study, level 4=country 
		
	//List of declared function names per object
	static HashMap<String, ArrayList<String>> declaredFunctions = new HashMap<String, ArrayList<String>>();

	//List of declared variables per object
	static HashMap<String, ArrayList<String>> declaredVariables = new HashMap<String, ArrayList<String>>();

	//List of declared parameters per object
	static HashMap<String, ArrayList<String>> declaredParameters = new HashMap<String, ArrayList<String>>();

	//List of partially declared variables (if then else)
	//static HashMap<String, ArrayList<String>> partiallyDeclaredVariables = new HashMap<String, ArrayList<String>>();

	//Checks whether the symbol is declared
	private boolean isSymbolDeclared(HashMap<String, ArrayList<String>> map, String id, ObjectName objName){
		if (objName != null)
			if (map.get(objName.getName()).contains(id)) return true;
		for (String key: map.keySet()){
			if (map.get(key).contains(id)) return true;
		} 
		return false;
	}
	
	//Checks whether the symbol is declared more than once
	private boolean isSymbolDeclaredMoreThanOnce(HashMap<String, ArrayList<String>> map, String id){
		int i = 0;
		for (String key: map.keySet()){
			ArrayList<String> functions = map.get(key); 
			for (String func: functions){
				if (func.equals(id)) i++;
				if (i > 1) return true;
			}
		} 
		return false;
	}
	
	//Check whether the list of attributes contains a give attribute
	private Boolean containsAttribute(Arguments args, String attrName){
		for (Argument arg: args.getArguments()){
			if (arg.getIdentifier() != null){
				if (arg.getIdentifier().equals(attrName)) return true;
			}
		}
		return false;
	}
	
	//Add a symbol to a list of known symbols
	private void addSymbol(ArrayList<String> list, EList<SymbolDeclaration> defns){
		if (defns != null)
			for (SymbolDeclaration s: defns){
				list.add(s.getIdentifier());
			}
	}
	
	//Add a symbol to a list of known symbols
	private void addSymbol(ArrayList<String> list, BlockStatement st){
		if (st != null){
			if (st.getSymbol() != null){
				list.add(st.getSymbol().getIdentifier());
			}
		}
	}
	
	//Add a symbol to a list of known symbols
	private void addSymbol(ArrayList<String> list, Arguments args){
		if (args != null){
			for (Argument arg: args.getArguments()){
				if (arg.getIdentifier() != null)
					list.add(arg.getIdentifier());
			}
		}
	}

	//Update the list of recognised functions
	@Check
	public void updateDeclaredFunctionList(Mcl mcl){
		declaredFunctions.clear();
		for (MclObject obj: mcl.getObjects()){
			if (obj.getTaskObject() != null){
				TaskObject taskObj = obj.getTaskObject();
				ArrayList<String> funcList =  new ArrayList<String>();
				for (TaskObjectBlock block : taskObj.getBlocks()){
					if (block.getFunctionDeclaration() != null){
						funcList.add(block.getFunctionDeclaration().getIdentifier());
					}
				}
				declaredFunctions.put(taskObj.getIdentifier().getName(), funcList);
			}
		}
	}
	
	//Update the list of recognised variables
	@Check
	public void updateDeclaredVariableList(Mcl mcl){
		declaredVariables.clear();
		for (MclObject obj: mcl.getObjects()){
			ArrayList<String> varList = new ArrayList<String>();
			if (obj.getModelObject() != null){
				for (ModelObjectBlock block: obj.getModelObject().getBlocks()){
					//RANDOM_VARIABLE_DEFINITION
					if (block.getRandomVariableDefinitionBlock() != null){
						addSymbol(varList, block.getRandomVariableDefinitionBlock().getVariables());
					}
					//INPUT_VARIABLES
					if (block.getInputVariablesBlock() != null){
						addSymbol(varList, block.getInputVariablesBlock().getVariables());
					}
					//GROUP_VARIABLES, MIXTURE
					if (block.getGroupVariablesBlock() != null){
						for (GroupVariablesBlockStatement s: block.getGroupVariablesBlock().getStatements()){
							addSymbol(varList, s.getStatement());
							if (s.getMixtureBlock() != null){
								for (BlockStatement st: s.getMixtureBlock().getStatements()){
									addSymbol(varList, st);
								}
							}
						}
					}
					//INDIVIDUAL_VARIABLES
					if (block.getIndividualVariablesBlock() != null){
						for (BlockStatement st: block.getIndividualVariablesBlock().getStatements()){
							addSymbol(varList, st);
						}
					}
					//MODEL_PREDICATION, ODE, LIBRARY
					if (block.getModelPredictionBlock() != null){
						for (ModelPredictionBlockStatement s: block.getModelPredictionBlock().getStatements()){
							addSymbol(varList, s.getStatement());
							if (s.getOdeBlock() != null){
								for (BlockStatement st: s.getOdeBlock().getStatements()){
									addSymbol(varList, st);
								}
							}
							if (s.getLibraryBlock() != null){
								for (BlockStatement st: s.getLibraryBlock().getStatements()){
									addSymbol(varList, st);
								}
							}
						}
					}
					//OBSERVATION
					if (block.getObservationBlock() != null){
						for (BlockStatement st: block.getObservationBlock().getStatements()){
							addSymbol(varList, st);
						}
					}
					//SIMULATION
					if (block.getSimulationBlock() != null){
						for (BlockStatement st: block.getSimulationBlock().getStatements()){
							addSymbol(varList, st);
						}
					}
					//ESTIMATION
					if (block.getEstimationBlock() != null){
						for (BlockStatement st: block.getEstimationBlock().getStatements()){
							addSymbol(varList, st);
						}
					}
				}
				declaredVariables.put(obj.getModelObject().getIdentifier().getName(), varList);
			}
			if (obj.getDataObject() != null){
				for (DataObjectBlock block: obj.getDataObject().getBlocks()){
					//FILE, RSCRIPT
					if (block.getFileBlock() != null){
						for (FileBlockStatement s: block.getFileBlock().getStatements()){
							if (s.getVariable() != null){
								varList.add(s.getVariable().getIdentifier());
							}
							if (s.getRscriptBlock() != null){
								for (RScriptBlockStatement rs: s.getRscriptBlock().getVariables()){
									varList.add(rs.getIdentifier());
								}
							}
						}
					}
				}
				declaredVariables.put(obj.getDataObject().getIdentifier().getName(), varList);
			}
		}
	}
	
	//Update the list of recognised parameters
	@Check
	public void updateDeclaredParameterList(Mcl mcl){
		declaredParameters.clear();
		for (MclObject obj: mcl.getObjects()){
			ArrayList<String> paramList = new ArrayList<String>();
			if (obj.getParameterObject() != null){
				for (ParameterObjectBlock block: obj.getParameterObject().getBlocks()){
					//STRUCTURAL
					if (block.getStructuralBlock() != null){
						for (ParameterDeclaration s: block.getStructuralBlock().getParameters()){
							paramList.add(s.getIdentifier());
						}
					}
					//VARIABILITY, block, diag, same
					if (block.getVariabilityBlock() != null){
						for (VariabilityBlockStatement s: block.getVariabilityBlock().getStatements()){
							if (s.getParameter() != null){
								paramList.add(s.getParameter().getIdentifier());
							}
							if (s.getBlockBlock() != null){
								addSymbol(paramList, s.getBlockBlock().getParameters());
							}
							if (s.getDiagBlock() != null){
								addSymbol(paramList, s.getDiagBlock().getParameters());
							}
							if (s.getSameBlock() != null){
								addSymbol(paramList, s.getSameBlock().getParameters());
							}
						}
					}
					//PRIOR_PARAMETERS
					if (block.getPriorBlock() != null){
						for (BlockStatement s: block.getPriorBlock().getStatements()){
							if (s.getSymbol() != null){
								paramList.add(s.getSymbol().getIdentifier());
							}
						}
					}
				}
				declaredParameters.put(obj.getParameterObject().getIdentifier().getName(), paramList);
			}
		}
	}

	//Check whether the function with such a name is already defined
	@Check
	public void checkFunctionDeclaration(TaskFunctionDeclaration func){
		if (isSymbolDeclaredMoreThanOnce(declaredFunctions, func.getIdentifier())){
			warning(MSG_FUNCTION_DEFINED, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__IDENTIFIER);
		}
	}	
	
	//Check whether the symbol with such a name is already defined
	@Check
	public void checkSymbolDeclaration(SymbolDeclaration symbol){
		if (isSymbolDeclaredMoreThanOnce(declaredVariables, symbol.getIdentifier())){
			warning(MSG_VARIABLE_DEFINED, MdlPackage.Literals.SYMBOL_DECLARATION__IDENTIFIER);
		}
		if (isSymbolDeclaredMoreThanOnce(declaredParameters, symbol.getIdentifier())){
			warning(MSG_PARAMETER_DEFINED, MdlPackage.Literals.SYMBOL_DECLARATION__IDENTIFIER);
		}
	}	
	
	//Check whether the symbol with such a name is already defined
	@Check
	public void checkSymbolDeclaration(ParameterDeclaration symbol){
		if (isSymbolDeclaredMoreThanOnce(declaredVariables, symbol.getIdentifier())){
			warning(MSG_VARIABLE_DEFINED, MdlPackage.Literals.PARAMETER_DECLARATION__IDENTIFIER);
		}
		if (isSymbolDeclaredMoreThanOnce(declaredParameters, symbol.getIdentifier())){
			warning(MSG_PARAMETER_DEFINED, MdlPackage.Literals.PARAMETER_DECLARATION__IDENTIFIER);
		}
	}	
	
	@Check
	public void checkSymbolDeclaration(RScriptBlockStatement symbol){
		if (isSymbolDeclaredMoreThanOnce(declaredVariables, symbol.getIdentifier())){
			warning(MSG_VARIABLE_DEFINED, MdlPackage.Literals.PARAMETER_DECLARATION__IDENTIFIER);
		}
		if (isSymbolDeclaredMoreThanOnce(declaredParameters, symbol.getIdentifier())){
			warning(MSG_PARAMETER_DEFINED, MdlPackage.Literals.PARAMETER_DECLARATION__IDENTIFIER);
		}
	}	
	
	//Check that the function call is to an existing function
	@Check
	public void checkFunctionCall(FunctionCall call) {
		if (!standardFunctions.contains(call.getIdentifier().getIdentifier()) && 
				!(isSymbolDeclared(declaredFunctions, call.getIdentifier().getIdentifier(), call.getIdentifier().getObject()))){
			warning(MSG_FUNCTION_UNKNOWN, MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER);
		}
	}
		
	@Check
	public void checkReference(FullyQualifiedSymbolName ref) {
		EObject container = ref.eContainer();
		if (container instanceof StructuralParametersBlockImpl || 
				container instanceof VariabilityParametersBlockImpl){
			if (!isSymbolDeclared(declaredParameters, ref.getIdentifier(), ref.getObject())){
				warning(MSG_PARAMETER_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER);
			}
		}
		if (container instanceof OutputVariablesBlockImpl){
			if (!(isSymbolDeclared(declaredVariables, ref.getIdentifier(), ref.getObject()))){
				warning(MSG_VARIABLE_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER);
			}
		}
	}
	
	//Check attributes of parameters in blocks
	@Check
	public void checkParameterAttributes(ParameterDeclaration p){
		EObject container = p.eContainer();
		//STRUCTURAL
		if (container instanceof StructuralBlockImpl){
			if (p.getList() != null){
				for (Argument arg: p.getList().getArguments().getArguments()){
					if (arg.getIdentifier() != null)
						if (!attr_structural.contains(arg.getIdentifier()))
							warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.PARAMETER_DECLARATION__LIST);

				}
				for (String attrName: attr_req_structural){
					if (!containsAttribute(p.getList().getArguments(), attrName))
						warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, MdlPackage.Literals.PARAMETER_DECLARATION__LIST);
				}
			}
		}
		//VARIABILITY
		if (container instanceof VariabilityBlockStatementImpl){
			if (p.getList() != null){
				for (Argument arg: p.getList().getArguments().getArguments()){
					if (arg.getIdentifier() != null)
						if (!attr_variability.contains(arg.getIdentifier()))
							warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.PARAMETER_DECLARATION__LIST);
				}
				for (String attrName: attr_req_variability){
					if (!containsAttribute(p.getList().getArguments(), attrName))
						warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, MdlPackage.Literals.PARAMETER_DECLARATION__LIST);
				}
			}
		}
	}
	
	//Check attributes of parameters in sub-blocks
	@Check
	public void checkParameterAttributes(BlockBlock block){
		//VARIABILITY subblocks: block
		if (block.getArguments() != null){
			for (Argument arg: block.getArguments().getArguments()){
				if (arg.getIdentifier() != null)
					if (!attr_variability_subblock.contains(arg.getIdentifier()))
						warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.BLOCK_BLOCK__ARGUMENTS);
			}
			for (String attrName: attr_req_variability_subblock){
				if (!containsAttribute(block.getArguments(), attrName))
					warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, MdlPackage.Literals.BLOCK_BLOCK__ARGUMENTS);
			}
		}
	}
	
	//Check attributes of parameters in sub-blocks
	@Check
	public void checkParameterAttributes(DiagBlock block){
		//VARIABILITY subblocks: diag
		if (block.getArguments() != null){
			for (Argument arg: block.getArguments().getArguments()){
				if (arg.getIdentifier() != null)
					if (!attr_variability_subblock.contains(arg.getIdentifier()))
						warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.DIAG_BLOCK__ARGUMENTS);
			}
			for (String attrName: attr_req_variability_subblock){
				if (!containsAttribute(block.getArguments(), attrName))
					warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, MdlPackage.Literals.DIAG_BLOCK__ARGUMENTS);
			}
		}
	}
	
	//Check attributes of parameters in sub-blocks
	@Check
	public void checkParameterAttributes(SameBlock block){
		//VARIABILITY subblocks: same
		if (block.getArguments() != null){
			for (Argument arg: block.getArguments().getArguments()){
				if (arg.getIdentifier() != null)
					if (!attr_variability_subblock.contains(arg.getIdentifier()))
						warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.SAME_BLOCK__ARGUMENTS);
			}
		}
	}
	
	//Check attributes of parameters in blocks
	@Check
	public void checkParameterAttributes(SymbolModification p){
		EObject container = p.eContainer();
		//HEADER
		if (container instanceof HeaderBlockImpl){
			if (p.getList() != null){	
				for (Argument arg: p.getList().getArguments().getArguments()){
						if (arg.getIdentifier() != null)
							if (!attr_header.contains(arg.getIdentifier()))
								warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.SYMBOL_MODIFICATION__LIST);
					}
					for (String attrName: attr_req_header){
						if (!containsAttribute(p.getList().getArguments(), attrName))
							warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, MdlPackage.Literals.SYMBOL_MODIFICATION__LIST);
					}
			}
		}
	}
	
	//Check attributes of variables in blocks
	@Check
	public void checkParameterAttributes(SymbolDeclaration p){
		EObject container = p.eContainer();
		//FILE
		if (container instanceof FileBlockStatementImpl){
			if (p.getExpression() != null){	
				if (p.getExpression().getList() != null){
					for (Argument arg: p.getExpression().getList().getArguments().getArguments()){
						if (arg.getIdentifier() != null)
							if (!attr_file.contains(arg.getIdentifier()))
								warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.SYMBOL_DECLARATION__EXPRESSION);
					}
					for (String attrName: attr_req_file){
						if (!containsAttribute(p.getExpression().getList().getArguments(), attrName))
							warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, MdlPackage.Literals.SYMBOL_DECLARATION__EXPRESSION);
					}
				}
			}
		}
		//INPUT_VARIABLES
		if (container instanceof InputVariablesBlockImpl){
			if (p.getExpression() != null){	
				if (p.getExpression().getList() != null){
					for (Argument arg: p.getExpression().getList().getArguments().getArguments()){
						if (arg.getIdentifier() != null)
							if (!attr_inputVariables.contains(arg.getIdentifier()))
								warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.SYMBOL_DECLARATION__EXPRESSION);
					}
				}
			}
		}
		//RANDOM_VARIABLE_DEFINITION
		if (container instanceof RandomVariableDefinitionBlockImpl){
			if (p.getExpression() != null){	
				if (p.getExpression().getList() != null){
					for (Argument arg: p.getExpression().getList().getArguments().getArguments()){
						if (arg.getIdentifier() != null)
							if (!attr_random.contains(arg.getIdentifier()))
								warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.SYMBOL_DECLARATION__EXPRESSION);
					}
					for (String attrName: attr_req_random){
						if (!containsAttribute(p.getExpression().getList().getArguments(), attrName))
							warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, MdlPackage.Literals.SYMBOL_DECLARATION__EXPRESSION);
					}
				}
			}
		}
	}
	
	@Check
	public void checkParameterAttributes(DesignBlockStatement p){
		//DESIGN
		if (p.getExpression() != null){	
			if (p.getExpression().getList() != null){
				for (Argument arg: p.getExpression().getList().getArguments().getArguments()){
					if (arg.getIdentifier() != null)
						if (!attr_design.contains(arg.getIdentifier()))
							warning(MSG_ATTRIBUTE_UNKNOWN + ": " + arg.getIdentifier(), MdlPackage.Literals.DESIGN_BLOCK_STATEMENT__EXPRESSION);
				}
				for (String attrName: attr_req_design){
					if (!containsAttribute(p.getExpression().getList().getArguments(), attrName))
						warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, MdlPackage.Literals.DESIGN_BLOCK_STATEMENT__EXPRESSION);
				}
			}
		}
	}
}
