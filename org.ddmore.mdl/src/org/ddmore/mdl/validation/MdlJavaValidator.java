/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks references
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.ImportBlockImpl;
import org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl;
import org.ddmore.mdl.mdl.impl.FunctionCallStatementImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.MclObjectImpl;
import org.ddmore.mdl.mdl.impl.ParameterDeclarationImpl;
import org.ddmore.mdl.mdl.impl.PrimaryImpl;
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl;
import org.ddmore.mdl.mdl.impl.SymbolModificationImpl;
import org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl;
import org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators = { 
		AttributeValidator.class, 
		FunctionValidator.class, 
		DistributionValidator.class, 
		UnitValidator.class})
public class MdlJavaValidator extends AbstractMdlJavaValidator {

	public final static String MSG_VARIABLE_DEFINED  = "A variable with such name already exists";
	public final static String MSG_PARAMETER_DEFINED = "A parameter with such name already exists";
	
	public final static String MSG_SYMBOL_UNKNOWN    = "Unresolved reference: parameter, variable, object or formal argument not declared";
	public final static String MSG_PARAMETER_UNKNOWN = "Parameter not declared";
	public final static String MSG_VARIABLE_UNKNOWN  = "Variable not declared";
		
	public final static String MSG_UNRESOLVED_ATTRIBUTE_REF = "Unresolved reference to a list attribute";
	public final static String MSG_UNRESOLVED_FUNC_ARGUMENT_REF = "Unresolved reference to a function output parameter";
	public final static String MSG_UNRESOLVED_SAME_BLOCK_NAME = "No corresponding matrix or diag block found";

	public final static String MSG_DATA_MISSING = "FILE block does not contain variable \"data\"!";
	
	public final static String var_file_data = "data";

	//List of objects
	static HashSet<String> declaredObjects = new HashSet<String>();

	//List of declared variables per object
	static HashMap<String, ArrayList<String>> declaredVariables = new HashMap<String, ArrayList<String>>();

	//List of declared parameters per object
	static HashMap<String, ArrayList<String>> declaredParameters = new HashMap<String, ArrayList<String>>();
	
	//List of declared variability subblocks diag and matrix (to match with same blocks)
	static HashMap<String, ArrayList<String>> variabilitySubblockNames = new HashMap<String, ArrayList<String>>();
	
	@Check
	public void updateObjectList(Mcl mcl){
		declaredObjects.clear();
		for (MclObject obj: mcl.getObjects()){
			declaredObjects.add(obj.getObjectName().getName());
		}
	}

	//TODO: Split variables and random variables?
	//Update the list of recognised variables
	@Check
	public void updateDeclaredVariableList(Mcl mcl){
		declaredVariables.clear();
		for (MclObject obj: mcl.getObjects()){
			ArrayList<String> varList = new ArrayList<String>();
			//Model object
			if (obj.getModelObject() != null){
				for (ModelObjectBlock block: obj.getModelObject().getBlocks()){
					//RANDOM_VARIABLE_DEFINITION
					if (block.getRandomVariableDefinitionBlock() != null){
						for (RandomVariable s: block.getRandomVariableDefinitionBlock().getVariables()){
							varList.add(s.getSymbolName().getName());
						}
					}
					//INPUT_VARIABLES
					if (block.getInputVariablesBlock() != null){
						for (SymbolDeclaration s: block.getInputVariablesBlock().getVariables()){
							varList.add(s.getSymbolName().getName());
						}
					}
					//GROUP_VARIABLES, MIXTURE
					if (block.getGroupVariablesBlock() != null){
						for (GroupVariablesBlockStatement s: block.getGroupVariablesBlock().getStatements()){
							Utils.addSymbol(varList, s.getStatement());
							if (s.getMixtureBlock() != null){
								for (BlockStatement st: s.getMixtureBlock().getStatements()){
									Utils.addSymbol(varList, st);
								}
							}
						}
					}
					//INDIVIDUAL_VARIABLES
					if (block.getIndividualVariablesBlock() != null){
						for (BlockStatement st: block.getIndividualVariablesBlock().getStatements()){
							Utils.addSymbol(varList, st);
						}
					}
					//MODEL_PREDICTION, ODE, LIBRARY
					if (block.getModelPredictionBlock() != null){
						for (ModelPredictionBlockStatement s: block.getModelPredictionBlock().getStatements()){
							Utils.addSymbol(varList, s.getStatement());
							if (s.getOdeBlock() != null){
								for (BlockStatement st: s.getOdeBlock().getStatements()){
									Utils.addSymbol(varList, st);
								}
							}
							if (s.getLibraryBlock() != null){
								for (FunctionCallStatement st: s.getLibraryBlock().getStatements()){
									varList.add(st.getFunctionName().getName());
								}
							}
						}
					}
					//OBSERVATION
					if (block.getObservationBlock() != null){
						for (BlockStatement st: block.getObservationBlock().getStatements()){
							Utils.addSymbol(varList, st);
						}
					}
					//SIMULATION
					if (block.getSimulationBlock() != null){
						for (BlockStatement st: block.getSimulationBlock().getStatements()){
							Utils.addSymbol(varList, st);
						}
					}
					//ESTIMATION
					if (block.getEstimationBlock() != null){
						for (BlockStatement st: block.getEstimationBlock().getStatements()){
							Utils.addSymbol(varList, st);
						}
					}
				}
				declaredVariables.put(obj.getObjectName().getName(), varList);
			}
			//Data object
			if (obj.getDataObject() != null){
				for (DataObjectBlock block: obj.getDataObject().getBlocks()){
					if (block.getDataInputBlock() != null){
						//DATA_INPUT_VARIABLES
						for (SymbolDeclaration s: block.getDataInputBlock().getVariables()){
							varList.add(s.getSymbolName().getName());
						}
					}
					//FILE, RSCRIPT
					if (block.getFileBlock() != null){
						for (FileBlockStatement s: block.getFileBlock().getStatements()){
							if (s.getVariable() != null){
								varList.add(s.getVariable().getSymbolName().getName());
							}
							if (s.getRscriptBlock() != null){
								for (RScriptBlockStatement rs: s.getRscriptBlock().getVariables()){
									varList.add(rs.getSymbolName().getName());
								}
							}
						}
					}
				}
				declaredVariables.put(obj.getObjectName().getName(), varList);
			}
			//TEL object
			if (obj.getTelObject() != null){
				for (FunctionCallStatement st: obj.getTelObject().getStatements()){
					varList.add(st.getFunctionName().getName());
				}
				declaredVariables.put(obj.getObjectName().getName(), varList);
			}
			//Add variables from LIBRARY and IMPORT block's attribute "output" to the list of recognized variables
			Resource resource = obj.eResource();
			TreeIterator<EObject> iterator = resource.getAllContents();
		    while (iterator.hasNext()){
		    	EObject block = iterator.next();
		    	if (block instanceof ImportBlockImpl){
		    		for (ImportedFunction f: ((ImportBlock) block).getFunctions()){
		    			if (f.getList() != null){
		    				varList.addAll(Utils.extractSymbolNames(f.getList().getArguments(), AttributeValidator.attr_output.name));
		    			}
		    		}
					declaredVariables.put(obj.getObjectName().getName(), varList);
		    	}
		    	if (block instanceof LibraryBlockImpl){
		    		for (FunctionCallStatement st: ((LibraryBlock) block).getStatements()){
		    			if (st.getExpression().getArguments() != null){
			    			varList.addAll(Utils.extractSymbolNames(st.getExpression().getArguments(), AttributeValidator.attr_output.name));
		    			}
		    		}
					declaredVariables.put(obj.getObjectName().getName(), varList);
		    	}
		    }
		}
	}
		
	//Update the list of recognised parameters
	@Check
	public void updateDeclaredParameterList(Mcl mcl){
		declaredParameters.clear();
		for (MclObject obj: mcl.getObjects()){
			//Parameter object
			if (obj.getParameterObject() != null){
				ArrayList<String> paramList = new ArrayList<String>();
				for (ParameterObjectBlock block: obj.getParameterObject().getBlocks()){
					//STRUCTURAL
					if (block.getStructuralBlock() != null){
						for (ParameterDeclaration s: block.getStructuralBlock().getParameters()){
							paramList.add(s.getSymbolName().getName());
						}
					}
					//VARIABILITY, matrix, diag, same
					if (block.getVariabilityBlock() != null){
						for (VariabilityBlockStatement s: block.getVariabilityBlock().getStatements()){
							if (s.getParameter() != null){
								paramList.add(s.getParameter().getSymbolName().getName());
							}
							if (s.getMatrixBlock() != null){
								Utils.addSymbol(paramList, s.getMatrixBlock().getParameters());
							}
							if (s.getDiagBlock() != null){
								Utils.addSymbol(paramList, s.getDiagBlock().getParameters());
							}
							if (s.getSameBlock() != null){
								Utils.addSymbol(paramList, s.getSameBlock().getParameters());
							}
						}
					}
					//PRIOR_PARAMETERS
					if (block.getPriorBlock() != null){
						for (BlockStatement s: block.getPriorBlock().getStatements()){
							if (s.getSymbol() != null){
								paramList.add(s.getSymbol().getSymbolName().getName());
							}
						}
					}
				}
				declaredParameters.put(obj.getObjectName().getName(), paramList);
			}
		}
	}
	
	//Update the list of declared variability subblock names
	@Check
	public void updateVariabilitySubblockNames(Mcl mcl){
		variabilitySubblockNames.clear();
		for (MclObject obj: mcl.getObjects()){
			//Parameter object
			if (obj.getParameterObject() != null){
				ArrayList<String> paramList = new ArrayList<String>();
				for (ParameterObjectBlock block: obj.getParameterObject().getBlocks()){
					//VARIABILITY sub-blocks matrix & diag
					if (block.getVariabilityBlock() != null){
						for (VariabilityBlockStatement s: block.getVariabilityBlock().getStatements()){
							if (s.getMatrixBlock() != null){
								String name = Utils.getAttributeValue(s.getMatrixBlock().getArguments(), AttributeValidator.attr_name.name);
								if (name.length() > 0) paramList.add(name);
							}
							if (s.getDiagBlock() != null){
								String name = Utils.getAttributeValue(s.getDiagBlock().getArguments(), AttributeValidator.attr_name.name);
								if (name.length() > 0) paramList.add(name);
							}
						}
					}
				}
				variabilitySubblockNames.put(obj.getObjectName().getName(), paramList);
			}
		}
	}
	
	//Match the name of the same block with the name of a matrix or a diag block
	@Check
	public void validateSameSubblockName(SameBlock b){
		String name = Utils.getAttributeValue(b.getArguments(), AttributeValidator.attr_name.name);
		if (name.length() > 0){
			ObjectName objName = Utils.getObjectName(b.eContainer());
			if (!Utils.isSymbolDeclared(variabilitySubblockNames, name, objName))
				warning(MSG_UNRESOLVED_SAME_BLOCK_NAME, 
						MdlPackage.Literals.SAME_BLOCK__IDENTIFIER,
						MSG_UNRESOLVED_SAME_BLOCK_NAME, b.getIdentifier());
		}
	}

	//Check whether the symbol with such a name is already defined
	@Check
	public void checkSymbolDeclaration(SymbolDeclaration symbol){
		if (Utils.isSymbolDeclaredMoreThanOnce(declaredVariables, symbol.getSymbolName())){
			warning(MSG_VARIABLE_DEFINED, MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME);
		}
		if (Utils.isSymbolDeclaredMoreThanOnce(declaredParameters, symbol.getSymbolName())){
			warning(MSG_PARAMETER_DEFINED, MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME);
		}
	}	
	
	//Check whether the symbol with such a name is already defined
	@Check
	public void checkSymbolDeclaration(ParameterDeclaration symbol){
		if (Utils.isSymbolDeclaredMoreThanOnce(declaredVariables, symbol.getSymbolName())){
			warning(MSG_VARIABLE_DEFINED, MdlPackage.Literals.PARAMETER_DECLARATION__SYMBOL_NAME);
		}
		if (Utils.isSymbolDeclaredMoreThanOnce(declaredParameters, symbol.getSymbolName())){
			warning(MSG_PARAMETER_DEFINED, MdlPackage.Literals.PARAMETER_DECLARATION__SYMBOL_NAME);
		}
	}	
	
	@Check
	public void checkSymbolDeclaration(RScriptBlockStatement symbol){
		if (Utils.isSymbolDeclaredMoreThanOnce(declaredVariables, symbol.getSymbolName())){
			warning(MSG_VARIABLE_DEFINED, MdlPackage.Literals.PARAMETER_DECLARATION__SYMBOL_NAME);
		}
		if (Utils.isSymbolDeclaredMoreThanOnce(declaredParameters, symbol.getSymbolName())){
			warning(MSG_PARAMETER_DEFINED, MdlPackage.Literals.PARAMETER_DECLARATION__SYMBOL_NAME);
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
		if (Utils.isParameterRefContainer(container)){
			if (!Utils.isSymbolDeclared(declaredParameters, ref)){
				warning(MSG_PARAMETER_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__SYMBOL,
						MSG_PARAMETER_UNKNOWN, ref.getSymbol().getName());
			}
		}

		/*References to variables*/
		if (Utils.isVariableRefContainer(container)){
			if (!(Utils.isSymbolDeclared(declaredVariables, ref))){
				warning(MSG_VARIABLE_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__SYMBOL,
						MSG_VARIABLE_UNKNOWN, ref.getSymbol().getName());
			}
		}
		
		/*References either to variables, parameters or to objects*/
		if ((container instanceof PrimaryImpl) || (container instanceof UnaryExpression)){
			if (!(Utils.isSymbolDeclared(declaredVariables, ref) || 
					Utils.isSymbolDeclared(declaredParameters, ref) ||
					declaredObjects.contains(ref.getSymbol().getName()) ||
					isFormalParameter(ref))){
				warning(MSG_SYMBOL_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__SYMBOL,
						MSG_SYMBOL_UNKNOWN, ref.getSymbol().getName());
			}
		}
		if (container instanceof FullyQualifiedArgumentNameImpl){
			if (!(Utils.isSymbolDeclared(declaredVariables, ref) || 
					Utils.isSymbolDeclared(declaredParameters, ref))){
				warning(MSG_SYMBOL_UNKNOWN, 
						MdlPackage.Literals.FULLY_QUALIFIED_SYMBOL_NAME__SYMBOL,
						MSG_SYMBOL_UNKNOWN, ref.getSymbol().getName());
			}
		}
	}

	//Check whether the reference is a formal parameter or local variable (property) in the function declaration
	private boolean isFormalParameter(FullyQualifiedSymbolName ref) {
		//Look for TaskFunctionDeclaration
		EObject container = ref.eContainer();
		ArrayList<String> varList = new ArrayList<String>();
		while (!(container instanceof TaskFunctionBlockImpl)){
			if (container instanceof TaskFunctionDeclarationImpl) break; //we are too high in the tree, no function blocks was found
			container = container.eContainer();
		}
		if (container instanceof TaskFunctionBlockImpl){
			TaskFunctionBlock tfb = (TaskFunctionBlock)container; 
			if (tfb.getEstimateBlock() != null){
				for (BlockStatement b: tfb.getEstimateBlock().getStatements())
					Utils.addSymbol(varList, b);
			}
			if (tfb.getSimulateBlock() != null){
				for (BlockStatement b: tfb.getSimulateBlock().getStatements())
					Utils.addSymbol(varList, b);
			}
			if (tfb.getExecuteBlock() != null){
				for (BlockStatement b: tfb.getExecuteBlock().getStatements())
					Utils.addSymbol(varList, b);
			}
		}
		for (String varName: varList){
			if (ref.getSymbol().getName().equals(varName)) return true;
		}
		
		while (!(container instanceof TaskFunctionDeclarationImpl)){
			if (container instanceof MclObjectImpl) return false; //we are too high in the tree, no function declaration was found
			container = container.eContainer();
		}
		//TaskFunctionDeclaration found
		TaskFunctionDeclaration func = (TaskFunctionDeclaration)container;
		if (func.getFormalArguments() != null){
			for (ArgumentName arg: func.getFormalArguments().getArguments()){
				if (arg.getName().equals(ref.getSymbol().getName())) return true;
			}
		}
		//Check whether
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
				if (x.getArgumentName() != null){
					int i = 0;
					for (Argument arg: currArg){
						if (arg.getArgumentName().getName().equals(x.getArgumentName().getName())){
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

		String varName = ref.getParent().getSymbol().getName();
		Resource resource = ref.eResource();
		LinkedList<Argument> args = new LinkedList<Argument>();		
		TreeIterator<EObject> iterator = resource.getAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof SymbolDeclarationImpl){
	    		SymbolDeclaration s = (SymbolDeclaration) obj;
	    		if (s.getSymbolName().getName().equals(varName)) {
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
		    	if (s.getSymbolName().getName().equals(varName)) 
		       		if (s.getList() != null)
		       			if (s.getList().getArguments() != null)
		       				for (Argument x: s.getList().getArguments().getArguments())
		          				args.add(x);
		    }
	    }
	    if (!checkAttributes(ref, args)){
			warning(MSG_UNRESOLVED_ATTRIBUTE_REF, 
					MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
					MSG_UNRESOLVED_ATTRIBUTE_REF, ref.getParent().getSymbol().getName());
	    }
	}

	//Validate a fully qualified argument whose parent refers to a variable declared as a function 
	//It is assumed that attribute selectors will refer to symbols in attribute "param" of a function call 
	public boolean checkReferenceToFuctionOutput(FullyQualifiedArgumentName ref) {
		String varName = ref.getParent().getSymbol().getName();		
		//N.K. - exclude/validate standard functions???		
		Resource resource = ref.eResource();
		TreeIterator<EObject> iterator = resource.getAllContents();
		ArrayList<String> params = new ArrayList<String>();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof FunctionCallStatementImpl){
	    		FunctionCallStatement s = (FunctionCallStatement) obj;
	    		if (s.getFunctionName().getName().equals(varName)) {	    			
	    			//Compare reference with references in FunctionCall param attribute
	    			//Does not guarantee the correctness as references may occur in expressions
	    			FunctionCall funcCall = s.getExpression();
	    			params.addAll(Utils.extractSymbolNames(funcCall.getArguments(), "param"));
	       			ArgumentName paramRef = ref.getSelectors().get(0).getArgumentName();
	       			if (paramRef != null){
	       				if (!params.contains(paramRef.getName())){
	       					warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       							paramRef.getName() + " is not in the reference set " + Utils.printList(params), 
	       							MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       							MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getSymbol().getName());
	       				}
	       			} else {
	       				String selector = ref.getSelectors().get(0).getSelector();
	       				int index = Integer.parseInt(selector);
	       				if (index < 1 || index > params.size()){
	       					warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       							"wrong index [" + index + "]. " + 
	       							"Reference set " + Utils.printList(params) + " contains " + params.size() + " items.", 
	       							MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       							MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getSymbol().getName());
	       				}	       					
	       			}
	       			return true; //skip list attribute check
	    		}
	    	}
	    }
	    return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Some symbols (variables, parameters) need to be declared
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	//DataObject -> FILE
	@Check
	public void checkSymbolDeclaration(FileBlock b){
		//Check that variable "data" is defined 
		for (FileBlockStatement st: b.getStatements()){
			if (st.getVariable() != null)	
				if (st.getVariable().getSymbolName().getName().equals(var_file_data)) return;
		}
		warning(MSG_DATA_MISSING, MdlPackage.Literals.FILE_BLOCK__STATEMENTS,
			MSG_DATA_MISSING, var_file_data);
	}	
}
