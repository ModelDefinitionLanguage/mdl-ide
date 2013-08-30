package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.AnyExpressionImpl;
import org.ddmore.mdl.mdl.impl.ArgumentsImpl;
import org.ddmore.mdl.mdl.impl.MatrixBlockImpl;
import org.ddmore.mdl.mdl.impl.BlockStatementImpl;
import org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.DiagBlockImpl;
import org.ddmore.mdl.mdl.impl.FileBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl;
import org.ddmore.mdl.mdl.impl.FullyQualifiedSymbolNameImpl;
import org.ddmore.mdl.mdl.impl.FunctionCallStatementImpl;
import org.ddmore.mdl.mdl.impl.HeaderBlockImpl;
import org.ddmore.mdl.mdl.impl.ImportedFunctionImpl;
import org.ddmore.mdl.mdl.impl.InlineBlockImpl;
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.ListImpl;
import org.ddmore.mdl.mdl.impl.MclObjectImpl;
import org.ddmore.mdl.mdl.impl.OdeBlockImpl;
import org.ddmore.mdl.mdl.impl.OdeListImpl;
import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.ParameterBlockImpl;
import org.ddmore.mdl.mdl.impl.ParameterDeclarationImpl;
import org.ddmore.mdl.mdl.impl.PrimaryImpl;
import org.ddmore.mdl.mdl.impl.RandomListImpl;
import org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl;
import org.ddmore.mdl.mdl.impl.SameBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl;
import org.ddmore.mdl.mdl.impl.SymbolListImpl;
import org.ddmore.mdl.mdl.impl.SymbolModificationImpl;
import org.ddmore.mdl.mdl.impl.TargetBlockImpl;
import org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.VariableListImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.validation.Check;

public class MdlJavaValidator extends AbstractMdlJavaValidator {

	public final static String MSG_VARIABLE_DEFINED = "A variable with such name already exists";
	public final static String MSG_PARAMETER_DEFINED = "A parameter with such name already exists";
	public final static String MSG_FUNCTION_DEFINED = "A function with such name is already defined";
	public final static String MSG_FUNCTION_UNKNOWN = "Unknown function";
	public final static String MSG_SYMBOL_UNKNOWN = "Unresolved reference: parameter, variable, object or formal argument not declared or conditionally declared";
	public final static String MSG_PARAMETER_UNKNOWN = "Parameter not declared or conditionally declared";
	public final static String MSG_VARIABLE_UNKNOWN = "Variable not declared or conditionally declared";
	public final static String MSG_ATTRIBUTE_UNKNOWN = "Unknown attribute";
	public final static String MSG_ATTRIBUTE_MISSING = "Required attribute is missing";

	public final static String MSG_UNRESOLVED_ATTRIBUTE_REF = "Unresolved reference to a list attribute";
	public final static String MSG_UNRESOLVED_FUNC_ARGUMENT_REF = "Unresolved reference to a function output parameter";
	public final static String MSG_UNRESOLVED_SAME_BLOCK_NAME = "No corresponding matrix or diag block found";

	//private enum VAL_RES {OK, ERROR, WARNING}
	
	//Parameter object
	final static List<String> attr_structural = Arrays.asList("value", "lo", "hi", "fix", "units", "transform");
	final static List<String> attr_req_structural = Arrays.asList("value");

	final static List<String> attr_variability = Arrays.asList("value", "type", "fix", "units", "transform");
	final static List<String> attr_req_variability = Arrays.asList("value");

	final static List<String> attr_variability_subblock = Arrays.asList("name", "type", "fix");
	final static List<String> attr_req_variability_subblock = Arrays.asList("name"); //for "same" block

	//Model object
	final static List<String> attr_inputVariables = Arrays.asList("value", "use", "units", "type", "level");
	
	final static List<String> attr_random = Arrays.asList("type", "mean", "variance", "level");
	final static List<String> attr_req_random = Arrays.asList("type", "mean", "variance");
	
	final static List<String> attr_library = Arrays.asList("model", "ncmt", "param", "input",
			"distribution", "elimination", "parameterization", "trans");
	final static List<String> attr_req_library = Arrays.asList("model");
	
	final static List<String> attr_ode = Arrays.asList("deriv", "init", "x0", "wrt");
	final static List<String> attr_req_ode = Arrays.asList("deriv", "init", "wrt");	
	
	//Data object
	final static List<String> attr_header = Arrays.asList("type", "define", "units", "recode", "boundaries", "missing");
	final static List<String> attr_req_header = Arrays.asList("type");

	final static List<String> attr_file = Arrays.asList("source", "ignore", "inputformat");
	final static List<String> attr_req_file = Arrays.asList("source");

	final static List<String> attr_design = Arrays.asList("source", "units", "interp", "idv");
	final static List<String> attr_req_design = Arrays.asList("source");
	
	//All blocks
	final static List<String> attr_import = Arrays.asList("target", "name", "ncmt", "trans", "param");
	final static List<String> attr_req_import = Arrays.asList("target", "param");

	final static List<String> attr_target = Arrays.asList("target", "location", "first", "before", "after");
	final static List<String> attr_req_target = Arrays.asList("target");

	List<String> getAllAttributes(EObject obj){
		if (obj instanceof StructuralBlockImpl)
			return attr_structural;
		if (obj instanceof VariabilityBlockStatementImpl)
			return attr_variability;
		if (obj instanceof MatrixBlockImpl || obj instanceof DiagBlockImpl || obj instanceof SameBlockImpl)
			return attr_variability_subblock;
		if (obj instanceof InputVariablesBlockImpl)
			return attr_inputVariables;
		if (obj instanceof RandomVariableDefinitionBlockImpl)
			return attr_random;
		if (obj instanceof LibraryBlockImpl)
			return attr_library;
		if (obj instanceof OdeBlockImpl)
			return  attr_ode; 
		if (obj instanceof HeaderBlockImpl)
			return attr_header; 
		if (obj instanceof FileBlockStatementImpl)
			return attr_file; 
		if (obj instanceof DesignBlockStatementImpl)
			return attr_design; 
		if (obj instanceof ImportedFunctionImpl)
			return attr_import;
		if (obj instanceof TargetBlockImpl)
			return attr_target;
		return null;
	}
	
	List<String> getRequiredAttributes(EObject obj){
		if (obj instanceof StructuralBlockImpl)
			return attr_req_structural;
		if (obj instanceof VariabilityBlockStatementImpl)
			return attr_req_variability;
		if (obj instanceof SameBlockImpl)
			return attr_req_variability_subblock;
		if (obj instanceof RandomVariableDefinitionBlockImpl)
			return attr_req_random;
		if (obj instanceof LibraryBlockImpl)
			return attr_req_library;
		if (obj instanceof OdeBlockImpl)
			return attr_req_ode; 
		if (obj instanceof HeaderBlockImpl)
			return attr_req_header; 
		if (obj instanceof FileBlockStatementImpl)
			return attr_req_file; 
		if (obj instanceof DesignBlockStatementImpl)
			return attr_req_design; 
		if (obj instanceof ImportedFunctionImpl)
			return attr_req_import;
		if (obj instanceof TargetBlockImpl)
			return attr_req_target;
		List<String> other = Arrays.asList();
		return other;
}
	
	//final static List<String> attr_funcDecl = Arrays.asList("algo", "sig", "max");
	//final static List<String> attr_req_funcDecl = Arrays.asList("");
	
	//List of recognized mathematical functions
	final static List<String> standardFunctions = Arrays.asList(
			//PharmML
			"exp", "ln", "minus", "factorial", "sin", "cos", "tan", "sec", "csc", "cot", "sinh", "csch", "coth", "arcsin", 
			"arccos", "arctan", "arcsec", "arccsc", "arccot", "arcsinh", "arccosh","arctanh", "arcsech", "arccsch", "arccoth", 
			"floor", "abs", "ceiling", "logit",
			//MDL
			"sqrt", "seq",
			//TEL
			"update",
			//TOOL SPECIFIC
			"runif", "errorexit", "PHI"
		);
	
	//1=observation, level 2=subject, level 3=study, level 4=country 

	//List of objects
	static HashSet<String> declaredObjects = new HashSet<String>();

	//List of declared function names per object
	static HashMap<String, ArrayList<String>> externalFunctions = new HashMap<String, ArrayList<String>>();

	//List of declared function names per object
	static HashMap<String, ArrayList<String>> declaredFunctions = new HashMap<String, ArrayList<String>>();

	//List of declared variables per object
	static HashMap<String, ArrayList<String>> declaredVariables = new HashMap<String, ArrayList<String>>();

	//List of declared parameters per object
	static HashMap<String, ArrayList<String>> declaredParameters = new HashMap<String, ArrayList<String>>();

	//List of declared variability subblocks diag and matrix (to match with same blocks)
	static HashSet<String> variabilitySubblockNames = new HashSet<String>();
	
	
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
	
	//Check whether the list of attributes contains a given attribute
	private Boolean containsAttribute(Arguments args, String attrName){
		for (Argument arg: args.getArguments()){
			if (arg.getIdentifier() != null){
				if (arg.getIdentifier().equals(attrName)) return true;
			}
		}
		return false;
	}
	
	//Add a symbol to a list of known symbols
	private void addSymbol(ArrayList<String> list, BlockStatement st){
		if (st != null){
			if (st.getSymbol() != null){
				list.add(st.getSymbol().getIdentifier());
			}
			//conditional declarations
			if (st.getStatement() != null){
				ConditionalStatement e = st.getStatement();
				ArrayList<BlockStatement> ifBlocks = new ArrayList<BlockStatement>();
				ArrayList<BlockStatement> elseBlocks = new ArrayList<BlockStatement>();
				prepareConditionalBlocks(e, ifBlocks, elseBlocks);
				addSymbol(list, ifBlocks, elseBlocks);
			}
		}
	}
	
	private void addSymbol(ArrayList<String> list, ArrayList<BlockStatement> ifBlocks, ArrayList<BlockStatement> elseBlocks){
		//Add symbols defined in both branches of nested conditional statements 
		ArrayList<String> ifSymbols = new ArrayList<String>();
		ArrayList<String> elseSymbols = new ArrayList<String>();
		for (BlockStatement b: ifBlocks){
			if (b.getStatement() != null){
				ConditionalStatement e = b.getStatement();
				ArrayList<BlockStatement> ifBlocks1 = new ArrayList<BlockStatement>();
				ArrayList<BlockStatement> elseBlocks1 = new ArrayList<BlockStatement>();
				prepareConditionalBlocks(e, ifBlocks1, elseBlocks1);
				addSymbol(ifSymbols, ifBlocks1, elseBlocks1);
			}
		}
		for (BlockStatement b: elseBlocks){
			if (b.getStatement() != null){
				ConditionalStatement e = b.getStatement();
				ArrayList<BlockStatement> ifBlocks1 = new ArrayList<BlockStatement>();
				ArrayList<BlockStatement> elseBlocks1 = new ArrayList<BlockStatement>();
				prepareConditionalBlocks(e, ifBlocks1, elseBlocks1);
				addSymbol(elseSymbols, ifBlocks1, elseBlocks1);
			}
		}
		for (String s: ifSymbols){
			if (isSymbolDefined(elseBlocks, s) || elseSymbols.contains(s)) list.add(s);
		}
		//BlockStatement is an unconditional symbol declaration
		for (BlockStatement b: ifBlocks){
			if (b.getSymbol() != null){
				String s = b.getSymbol().getIdentifier();
				if (isSymbolDefined(elseBlocks, s) || elseSymbols.contains(s)) {
					list.add(s);
				}				
			}
		}
	}
	
	private void prepareConditionalBlocks(ConditionalStatement e, ArrayList<BlockStatement> ifBlocks, ArrayList<BlockStatement> elseBlocks){
		if (e.getIfStatement() != null)
			ifBlocks.add(e.getIfStatement());	
		if (e.getIfBlock() != null) {
			for (BlockStatement b: e.getIfBlock().getStatements())
				ifBlocks.add(b);
		}
		if (e.getElseStatement() != null)
			elseBlocks.add(e.getElseStatement());
		if (e.getElseBlock() !=null){
			for (BlockStatement b: e.getElseBlock().getStatements())
				elseBlocks.add(b);
		}
	}
	
	private boolean isSymbolDefined(ArrayList<BlockStatement> blocks, String name){
		for (BlockStatement b: blocks){
			if (b.getSymbol() != null){
				if (b.getSymbol().getIdentifier().equals(name)) return true;				
			}
		}
		return false;
	}
	
	//Add a symbol to a list of known symbols
	private void addSymbol(ArrayList<String> list, Arguments args){
		if (args != null){
			if (args.getArguments() != null){	
				for (Argument arg: args.getArguments()){
					if (arg.getIdentifier() != null)
						list.add(arg.getIdentifier());
				}
			}
		}
	}
	
	private void addSymbol(ArrayList<String> list, FormalArguments args){
		if (args != null){
			for (FormalArgument id: args.getArguments()){
				list.add(id.getIdentifier());
			}
		}
	}
	
	private void addSymbol(ArrayList<String> list, ImportBlock block){
		if (block != null){
			for (ImportedFunction id: block.getFunctions()){
				list.add(id.getIdentifier());
			}
		}
	}
	
	//Update the list of recognised external functions
	@Check
	public void updateExternalFunctionList(Mcl mcl){
		externalFunctions.clear();
		for (MclObject obj: mcl.getObjects()){
			ArrayList<String> funcList =  new ArrayList<String>();
			if (obj.getModelObject() != null){
				ModelObject modelObj = obj.getModelObject();
				for (ModelObjectBlock block : modelObj.getBlocks()){
					addSymbol(funcList, block.getImportBlock());
				}
				externalFunctions.put(obj.getIdentifier().getName(), funcList);
			}
			if (obj.getParameterObject() != null){
				ParameterObject paramObj = obj.getParameterObject();
				for (ParameterObjectBlock block : paramObj.getBlocks()){
					addSymbol(funcList, block.getImportBlock());
				}
				externalFunctions.put(obj.getIdentifier().getName(), funcList);
			}
			if (obj.getDataObject() != null){
				DataObject dataObj = obj.getDataObject();
				for (DataObjectBlock block : dataObj.getBlocks()){
					addSymbol(funcList, block.getImportBlock());
				}
				externalFunctions.put(obj.getIdentifier().getName(), funcList);
			}
			if (obj.getTaskObject() != null){
				TaskObject taskObj = obj.getTaskObject();
				for (TaskObjectBlock block : taskObj.getBlocks()){
					addSymbol(funcList, block.getImportBlock());
				}
				externalFunctions.put(obj.getIdentifier().getName(), funcList);
			}
		}
	}

	@Check
	public void updateObjectList(Mcl mcl){
		declaredObjects.clear();
		for (MclObject obj: mcl.getObjects()){
			declaredObjects.add(obj.getIdentifier().getName());
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
				declaredFunctions.put(obj.getIdentifier().getName(), funcList);
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
						for (SymbolDeclaration s: block.getRandomVariableDefinitionBlock().getVariables()){
							varList.add(s.getIdentifier());
						}
					}
					//INPUT_VARIABLES
					if (block.getInputVariablesBlock() != null){
						for (SymbolDeclaration s: block.getInputVariablesBlock().getVariables()){
							varList.add(s.getIdentifier());
						}
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
					//MODEL_PREDICTION, ODE, LIBRARY
					if (block.getModelPredictionBlock() != null){
						for (ModelPredictionBlockStatement s: block.getModelPredictionBlock().getStatements()){
							addSymbol(varList, s.getStatement());
							if (s.getOdeBlock() != null){
								for (BlockStatement st: s.getOdeBlock().getStatements()){
									addSymbol(varList, st);
								}
							}
							if (s.getLibraryBlock() != null){
								for (FunctionCallStatement st: s.getLibraryBlock().getStatements()){
									varList.add(st.getIdentifier());
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
				declaredVariables.put(obj.getIdentifier().getName(), varList);
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
				declaredVariables.put(obj.getIdentifier().getName(), varList);
			}
			if (obj.getTelObject() != null){
				for (BlockStatement st: obj.getTelObject().getStatements()){
					addSymbol(varList, st);
				}
				declaredVariables.put(obj.getIdentifier().getName(), varList);
			}
		}
	}
	
	
	//Update the list of recognised parameters
	@Check
	public void updateDeclaredParameterList(Mcl mcl){
		declaredParameters.clear();
		for (MclObject obj: mcl.getObjects()){
			if (obj.getParameterObject() != null){
				ArrayList<String> paramList = new ArrayList<String>();
				for (ParameterObjectBlock block: obj.getParameterObject().getBlocks()){
					//STRUCTURAL
					if (block.getStructuralBlock() != null){
						for (ParameterDeclaration s: block.getStructuralBlock().getParameters()){
							paramList.add(s.getIdentifier());
						}
					}
					//VARIABILITY, matrix, diag, same
					if (block.getVariabilityBlock() != null){
						for (VariabilityBlockStatement s: block.getVariabilityBlock().getStatements()){
							if (s.getParameter() != null){
								paramList.add(s.getParameter().getIdentifier());
							}
							if (s.getMatrixBlock() != null){
								addSymbol(paramList, s.getMatrixBlock().getParameters());
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
				declaredParameters.put(obj.getIdentifier().getName(), paramList);
			}
		}
	}
	
	//Update the list of declared variability subblock names
	@Check
	public void updateVariabilitySubblockNames(Mcl mcl){
		variabilitySubblockNames.clear();
		for (MclObject obj: mcl.getObjects()){
			if (obj.getParameterObject() != null){
				for (ParameterObjectBlock block: obj.getParameterObject().getBlocks()){
					//VARIABILITY sub-blocks matrix & diag
					if (block.getVariabilityBlock() != null){
						for (VariabilityBlockStatement s: block.getVariabilityBlock().getStatements()){
							if (s.getMatrixBlock() != null){
								String name = getAttributeValue(s.getMatrixBlock().getArguments(),"name");
								if (name.length() > 0)
									variabilitySubblockNames.add(name);
							}
							if (s.getDiagBlock() != null){
								String name = getAttributeValue(s.getDiagBlock().getArguments(),"name");
								if (name.length() > 0)
									variabilitySubblockNames.add(name);
							}
						}
					}
				}
			}
		}
	}
	
	//Match the name of the same block with the name of a matrix or a diag block
	@Check
	public void validateSameSubblockName(SameBlock b){
		String name = getAttributeValue(b.getArguments(),"name");
		if (name.length() > 0)
			if (!variabilitySubblockNames.contains(name))
				warning(MSG_UNRESOLVED_SAME_BLOCK_NAME, 
						MdlPackage.Literals.SAME_BLOCK__IDENTIFIER,
						MSG_UNRESOLVED_SAME_BLOCK_NAME, b.getIdentifier());
	}
	
	//Evaluate STRING expression
	private String getAttributeValue(Arguments a, String attrName){
		String res = "";
		for (Argument arg: a.getArguments()){
			if (arg.getIdentifier().equals(attrName))
				if (arg.getExpression() != null){
					if (arg.getExpression().getExpression() != null){
						if (arg.getExpression().getExpression().getConditionalExpression() != null){
							OrExpression orExpr = arg.getExpression().getExpression().getConditionalExpression().getExpression();
							AndExpression andExpr = orExpr.getExpression().get(0);
							LogicalExpression logicalExpr = andExpr.getExpression().get(0);	
							if (logicalExpr.getExpression() != null){	
								AdditiveExpression addExpr = logicalExpr.getExpression().get(0);
								if (addExpr.getString() != null)
									for (String str: addExpr.getString()) res += str;
							}
						}
					}
				}
		}
		return res;
	}	

	//Check whether the function with such a name is already defined
	@Check
	public void checkFunctionDeclaration(TaskFunctionDeclaration func){
		if (isSymbolDeclaredMoreThanOnce(declaredFunctions, func.getIdentifier())){
			warning(MSG_FUNCTION_DEFINED, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__IDENTIFIER);
		}
	}
	
	//Check whether the function with such a name is already defined
	@Check
	public void checkExportFunctionDeclaration(ImportedFunction func){
		if (isSymbolDeclaredMoreThanOnce(externalFunctions, func.getIdentifier())){
			warning(MSG_FUNCTION_DEFINED, MdlPackage.Literals.IMPORTED_FUNCTION__IDENTIFIER);
		}
		if (isSymbolDeclared(declaredFunctions, func.getIdentifier(), null)){
			warning(MSG_FUNCTION_DEFINED, MdlPackage.Literals.IMPORTED_FUNCTION__IDENTIFIER);
		}
		//Check that it is not standard and was not defined in Task objects
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
				!(isSymbolDeclared(declaredFunctions, call.getIdentifier().getIdentifier(), call.getIdentifier().getObject()))
				&& !(isSymbolDeclared(externalFunctions, call.getIdentifier().getIdentifier(), call.getIdentifier().getObject()))){
			warning(MSG_FUNCTION_UNKNOWN, 
					MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER,
					MSG_FUNCTION_UNKNOWN, call.getIdentifier().getIdentifier());
		}
	}
		
	////////////////////////////////////////////////////////////////
	//Check references
	////////////////////////////////////////////////////////////////

	@Check
	public void checkReference(FullyQualifiedSymbolName ref) {
		EObject container = ref.eContainer();
		if (container instanceof SymbolModificationImpl)
			container = container.eContainer();
			
		/*References to parameters*/
		//STRUCTURAL_PARAMETERS, VARIABILITY_PARAMETERS
		//SymbolModification (PARAMETER)
		if (container instanceof StructuralParametersBlockImpl || 
				container instanceof VariabilityParametersBlockImpl ||
				container instanceof ParameterBlockImpl){
			if (!isSymbolDeclared(declaredParameters, ref.getIdentifier(), ref.getObject())){
				warning(MSG_PARAMETER_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER,
						MSG_PARAMETER_UNKNOWN, ref.getIdentifier());
			}
		}


		/*References to variables*/
		//OUTPUT, INLINE, HEADER
		//DESIGN: VariableList and DesignBlockStatement
		if (container instanceof OutputVariablesBlockImpl || 
				container instanceof InlineBlockImpl ||
				container instanceof VariableListImpl ||
				container instanceof DesignBlockStatementImpl ||
				container instanceof SymbolListImpl){
			if (!(isSymbolDeclared(declaredVariables, ref.getIdentifier(), ref.getObject()))){
				warning(MSG_VARIABLE_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER,
						MSG_VARIABLE_UNKNOWN, ref.getIdentifier());
			}
		}
		
		/*References in expressions either to variables of parameters*/
		if (container instanceof PrimaryImpl){
			if (!(isSymbolDeclared(declaredVariables, ref.getIdentifier(), ref.getObject()) || 
					isSymbolDeclared(declaredParameters, ref.getIdentifier(), ref.getObject()) ||
					declaredObjects.contains(ref.getIdentifier()) ||
					isFormalParameter(ref))){
				warning(MSG_SYMBOL_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER,
						MSG_SYMBOL_UNKNOWN, ref.getIdentifier());
			}
		}
		if (container instanceof FullyQualifiedArgumentNameImpl){
			if (!(isSymbolDeclared(declaredVariables, ref.getIdentifier(), ref.getObject()) || 
					isSymbolDeclared(declaredParameters, ref.getIdentifier(), ref.getObject()))){
				warning(MSG_SYMBOL_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER,
						MSG_SYMBOL_UNKNOWN, ref.getIdentifier());
			}
		}
	}

	//Check whether the reference is a formal parameter in function declaration
	private boolean isFormalParameter(FullyQualifiedSymbolName ref) {
		//Look for TaskFunctionDeclaration
		EObject container = ref.eContainer();
		while (!(container instanceof TaskFunctionDeclarationImpl)){
			if (container instanceof MclObjectImpl) return false; //we are too high in the tree, no function declaration was found
			container = container.eContainer();
		}
		//TaskFunctionDeclaration found
		TaskFunctionDeclaration func = (TaskFunctionDeclaration)container;
		if (func.getFormalArguments() != null){
			for (FormalArgument arg: func.getFormalArguments().getArguments()){
				if (arg.getIdentifier().equals(ref.getIdentifier())) return true;
			}
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//Check references to list attributes
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	private boolean checkAttributes(FullyQualifiedArgumentName attrName, LinkedList<Argument> arguments) {
		List <Argument> currArg = arguments; 
		for (Selector x: attrName.getSelectors()){
			if (currArg != null){
				int index = -1;
				if (x.getSelector() != null){
					index = Integer.parseInt(x.getSelector());
					if (!((index >= 1) && (index < currArg.size() + 1))) return false;
					index = 1;	
				}
				if (x.getIdentifier() != null){
					int i = 0;
					for (Argument arg: currArg){
						if (arg.getIdentifier().equals(x.getIdentifier().getIdentifier())){
							index = i + 1;
							break;
						}
						i++; 
					}
				}
				if (index > 0) {
					if (currArg.get(index - 1).getExpression().getList() != null)
						if (arguments.get(index).getExpression().getList().getArguments() != null)
							currArg = arguments.get(index).getExpression().getList().getArguments().getArguments();
					if (currArg.get(index - 1).getExpression().getOdeList() != null) 
						if (arguments.get(index).getExpression().getOdeList().getArguments() != null)
							currArg = arguments.get(index).getExpression().getOdeList().getArguments().getArguments();
				} else return false;
			} 
		}
		return true;		
	}

	@Check
	public void checkReference(FullyQualifiedArgumentName ref) {
		//The reference is to the symbol with assigned expression which is a function call
		//We check that attributes refer to function arguments
		if (checkReferenceToFuctionOutput(ref)) return;

		String varName = ref.getParent().getIdentifier();
		Resource resource = ref.eResource();
		LinkedList<Argument> args = new LinkedList<Argument>();			
		TreeIterator<EObject> iterator = resource.getAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof SymbolDeclarationImpl){
	    		SymbolDeclaration s = (SymbolDeclaration) obj;
	    		if (s.getIdentifier().equals(varName)) {
	    			if (s.getExpression() != null){
	       				if (s.getExpression().getList() != null)
	       					if (s.getExpression().getList().getArguments() != null)
	       						for (Argument x: s.getExpression().getList().getArguments().getArguments())
	           						args.add(x);
	       				if (s.getExpression().getOdeList() != null)
	       					if (s.getExpression().getOdeList().getArguments() != null)
	       						for (Argument x: s.getExpression().getOdeList().getArguments().getArguments())
	           						args.add(x);
	    			}
	    			if (s.getRandomList() != null)
	       				if (s.getRandomList().getArguments() != null)
	   						for (Argument x: s.getRandomList().getArguments().getArguments())
	       						args.add(x);
	    		}
	    	}
	    	if (obj instanceof SymbolModificationImpl){
	    		SymbolModification s = (SymbolModification) obj;
	    		if (s.getIdentifier().equals(varName)) {
	       			if (s.getList() != null){
	       				if (s.getList().getArguments() != null){
	       					for (Argument x: s.getList().getArguments().getArguments())
	       						args.add(x);
	       				}
	       			}   
	    		}
	    	}
	    	if (obj instanceof ParameterDeclarationImpl){
	    		ParameterDeclaration s = (ParameterDeclaration) obj;
		    	if (s.getIdentifier().equals(varName)) 
		       		if (s.getList() != null)
		       			if (s.getList().getArguments() != null)
		       				for (Argument x: s.getList().getArguments().getArguments())
		          				args.add(x);
		    }
	    }
	    if (!checkAttributes(ref, args)){
			warning(MSG_UNRESOLVED_ATTRIBUTE_REF, 
					MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
					MSG_UNRESOLVED_ATTRIBUTE_REF, ref.getParent().getIdentifier());
	    }
	}

	//Validate a fully qualified argument whose parent refers to a variable declared as a function 
	//It is assumed that attribute selectors will refer to symbols in attribute "param" of a function call 
	public boolean checkReferenceToFuctionOutput(FullyQualifiedArgumentName ref) {
		String varName = ref.getParent().getIdentifier();		
		//N.K. - exclude/validate standard functions???		
		Resource resource = ref.eResource();
		TreeIterator<EObject> iterator = resource.getAllContents();
		ArrayList<String> params = new ArrayList<String>();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof FunctionCallStatementImpl){
	    		FunctionCallStatement s = (FunctionCallStatement) obj;
	    		if (s.getIdentifier().equals(varName)) {	    			
	    			//Compare reference with references in FunctionCall param attribute
	    			//Does not guarantee the correctness as references may occur in expressions
	    			FunctionCall funcCall = s.getExpression();
	       			for (Argument x: funcCall.getArguments().getArguments()){
	       				if (x.getIdentifier().equals("param")){
	       					if (x.getExpression().getList() != null){
	       						for (Argument paramArg: x.getExpression().getList().getArguments().getArguments()){
	       							TreeIterator<EObject> paramIterator = paramArg.getExpression().eAllContents();
	       							while (paramIterator.hasNext()){
	       						    	EObject paramObj = paramIterator.next();
	       						    	if (paramObj instanceof FullyQualifiedSymbolNameImpl){
	       						    		FullyQualifiedSymbolName foundParam = (FullyQualifiedSymbolName) paramObj;
	       						    		params.add(foundParam.getIdentifier());
	       						    	}
	       							}
	       						}
	       					}
	       				}
	       			}
	       			FormalArgument paramRef = ref.getSelectors().get(0).getIdentifier();
	       			if (paramRef != null){
	       				if (!params.contains(paramRef.getIdentifier())){
	       					warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       							paramRef.getIdentifier() + " is not in the reference set " + printList(params), 
	       							MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       							MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getIdentifier());
	       				}
	       			} else {
	       				String selector = ref.getSelectors().get(0).getSelector();
	       				int index = Integer.parseInt(selector);
	       				if (index < 1 || index > params.size()){
	       					warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       							"wrong index [" + index + "]. " + 
	       							"Reference set " + printList(params) + " contains " + params.size() + " items.", 
	       							MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       							MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getIdentifier());
	       				}	       					
	       			}
	       			return true; //skip list attribute check
	    		}
	    	}
	    }
	    return false;
	}
	
	//For testing
	private String printList(ArrayList<String> list){
		String res = "{ ";
		for (String str: list) res += str + "; ";
		return res + "}";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//Check attributes of parameters in blocks
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Check
	public void checkRequiredArguments(Arguments args){
		EObject container = args.eContainer();
		if (container instanceof ListImpl || container instanceof OdeListImpl || container instanceof RandomListImpl )
			container = container.eContainer();
		if (container instanceof AnyExpressionImpl)
			container = container.eContainer();
		if (container instanceof ParameterDeclarationImpl ||
			container instanceof SymbolDeclarationImpl ||
			container instanceof SymbolModificationImpl)
			container = container.eContainer();
		if (container instanceof BlockStatementImpl)
			container = container.eContainer();
		
		//Exclude content of Diag and Matrix check from attribute checks
		if (container instanceof MatrixBlockImpl){
			MatrixBlockImpl block = (MatrixBlockImpl)container;
			if (block.getParameters().equals(args)) return;
		} 
		if(container instanceof DiagBlockImpl){
			DiagBlockImpl block = (DiagBlockImpl)container;
			if (block.getParameters().equals(args)) return;
		}		

		//getRequiredAttributes contains lists of required attributes for each container
		for (String attrName: getRequiredAttributes(container)){
			if (!containsAttribute(args, attrName)) warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, 
				MdlPackage.Literals.ARGUMENTS__ARGUMENTS, MSG_ATTRIBUTE_MISSING, attrName);
		}
	}
	
	@Check
	public void checkAllArguments(Argument argument){
		EObject argContainer = argument.eContainer();	
		if (!(argContainer instanceof ArgumentsImpl)) return;
		EObject container = argContainer.eContainer();
		if (container instanceof ListImpl || container instanceof OdeListImpl || container instanceof RandomListImpl)
			container = container.eContainer();
		if (container instanceof AnyExpressionImpl)
			container = container.eContainer();
		if (container instanceof ParameterDeclarationImpl ||
			container instanceof SymbolDeclarationImpl ||
			container instanceof SymbolModificationImpl)
			container = container.eContainer();
		if (container instanceof BlockStatementImpl)
			container = container.eContainer();
		
		Arguments args = (Arguments)argContainer;
		//Exclude content of Diag and Matrix check from attribute checks
		if (container instanceof MatrixBlockImpl){
			MatrixBlockImpl block = (MatrixBlockImpl)container;
			if (block.getParameters().equals(args)) return;
		} 
		if(container instanceof DiagBlockImpl){
			DiagBlockImpl block = (DiagBlockImpl)container;
			if (block.getParameters().equals(args)) return;
		}			
			
		List<String> knownAttributes = getAllAttributes(container);
		if (knownAttributes != null)
			if (!knownAttributes.contains(argument.getIdentifier()))
				warning(MSG_ATTRIBUTE_UNKNOWN + ": " + argument.getIdentifier(), 
				MdlPackage.Literals.ARGUMENT__IDENTIFIER,
				MSG_ATTRIBUTE_UNKNOWN, argument.getIdentifier());
			
	}
		
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Some symbols (variables, parameters) need to be declared
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	//DataObject -> FILE
	@Check
	public void checkSymbolDeclaration(FileBlock b){
		//Check that variable "data" is defined 
		Boolean isDataDefined = false;
		for (FileBlockStatement st: b.getStatements()){
			if (st.getVariable() != null){	
				if (st.getVariable().getIdentifier().equalsIgnoreCase("data")) {
					isDataDefined  = true;
					break;
				}
			}
		}
		if (!isDataDefined){
				warning("FILE block does not contain variable \"data\"!", MdlPackage.Literals.FILE_BLOCK__STATEMENTS);
		}
	}	
}
