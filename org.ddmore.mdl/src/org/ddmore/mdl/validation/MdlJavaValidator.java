/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks references
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.domain.Variable;
import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.FunctionCallStatementImpl;
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators = { 
		AttributeValidator.class,
		PropertyValidator.class,
		FunctionValidator.class, 
		DistributionValidator.class, 
		UnitValidator.class})
public class MdlJavaValidator extends AbstractMdlJavaValidator {

	public final static String MSG_SYMBOL_DEFINED  = "A variable with such name already exists";
	public final static String MSG_SYMBOL_UNKNOWN  = "Unresolved reference: variable not declared";
		
	public final static String MSG_UNRESOLVED_FUNC_ARGUMENT_REF = "Unresolved reference to a function output parameter";
	public final static String MSG_UNRESOLVED_SAME_BLOCK_NAME = "No corresponding matrix or diag block found";
	
	public final static String MSG_UNRESOLVED_ATTRIBUTE_REF = "Unresolved reference to a list attribute";
	public final static String MSG_UNRESOLVED_DISTR_ATTRIBUTE_REF = "Unresolved reference to a distribution attribute";

	public final static String MSG_MODEL_OBJ_MISSING = "MOG should include a model object";
	public final static String MSG_DATA_OBJ_MISSING  = "MOG should include a data object";
	public final static String MSG_PARAM_OBJ_MISSING = "MOG should include a parameter object";
	public final static String MSG_TASK_OBJ_MISSING  = "MOG should include a task object";
	public final static String MSG_OBJ_DEFINED         = "Cannot create a MOG";
	public final static String MSG_MODEL_DATA_MISMATCH = "Inconsistent sets of model/data variables";
	
	//List of objects
	Map<String, MdlDataType> declaredObjects = new HashMap<String, MdlDataType>();	
	
	//List of declared variables per object
	public static Map<String, List<Variable>> declaredVariables = new HashMap<String, List<Variable>>();	
	
	List<MOGObject> mogs = new ArrayList<MOGObject>(); 
	
	@Check
	public void updateObjectList(Mcl mcl){
		declaredObjects = Utils.getDeclaredObjects(mcl);
	}

	//Update the list of recognised variables
	@Check
	public void updateDeclaredVariableList(Mcl mcl){
		declaredVariables = Utils.getDeclaredSymbols(mcl);
	}
	
	@Check
	public void updateLinkedObjects(Mcl mcl){
		mogs = Utils.getMOGs(mcl);
	}
	
	@Check
	public void checkSymbolDeclarations(SymbolDeclaration s){
		if (s.getSymbolName() != null)
			if (Utils.isSymbolDeclaredMoreThanOnce(declaredVariables, s.getSymbolName())){
				warning(MSG_SYMBOL_DEFINED, 
						MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME,
						MSG_UNRESOLVED_SAME_BLOCK_NAME, s.getSymbolName().getName());
			}
	} 

	////////////////////////////////////////////////////////////////
	//Check references
	////////////////////////////////////////////////////////////////

	@Check
	public void checkReference(SymbolName ref) {
		EObject container = ref.eContainer();
		//Skip validation of symbol names which are not references 
		if (container instanceof SymbolDeclarationImpl ||
			container instanceof FunctionCallStatement) return;
		
		//Skip reference to a standard function
		if (FunctionValidator.standardFunctions.containsKey(ref.getName())) return;
		
		//Check that each variable is in the local object or in the MOG
		if (!(	Utils.isSymbolDeclared(declaredVariables, ref, mogs) 
				|| declaredObjects.containsKey(ref.getName())) ){
			warning(MSG_SYMBOL_UNKNOWN, MdlPackage.Literals.SYMBOL_NAME__NAME,
					MSG_SYMBOL_UNKNOWN, ref.getName());
		}
	}

	@Check
	public void checkReference(FullyQualifiedArgumentName ref) {
		//Skip if the reference is to the symbol with assigned expression which is a function call
		if (checkReferenceToFuctionOutput(ref)) return;
		List<Argument> args = new LinkedList<Argument>();
		TreeIterator<EObject> iterator = ref.eResource().getAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof SymbolDeclarationImpl){
	    		SymbolDeclaration s = (SymbolDeclaration) obj;
	    		if (s.getSymbolName() != null){
		    		if (s.getSymbolName().getName().equals(ref.getParent().getName())) {
		    			if (s.getList() != null){
		    				Arguments arguments = s.getList().getArguments();
		       				if (arguments != null)
			       				for (Argument x: arguments.getArguments())
	           						args.add(x);
		    			}
		    			if (s.getRandomList() != null){
		    				Arguments arguments = s.getRandomList().getArguments();
		       				if (arguments != null)
			       				for (Argument x: arguments.getArguments())
	           						args.add(x);
		    			}
		    		}
	    		}
	    	}
	    }
	    if ((args.size() > 0) && !checkAttributes(ref, args))
			warning(MSG_UNRESOLVED_ATTRIBUTE_REF, 
				MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
					MSG_UNRESOLVED_ATTRIBUTE_REF, ref.getParent().getName());
	}
	
	//Check references to list attributes
	public boolean checkAttributes(FullyQualifiedArgumentName ref, List<Argument> arguments) {
		//Skip 
		if (ref.eContainer() instanceof SymbolDeclarationImpl) return true;
		List <Argument> currArgs = arguments; 
		for (Selector x: ref.getSelectors()){
			if (currArgs != null){
				int index = -1;
				if (x.getSelector() != null){
					index = Integer.parseInt(x.getSelector());
					if (!((index >= 1) && (index < currArgs.size() + 1))) return false;
					index = 1;	
				}
				if (x.getArgumentName() != null){
					int i = 0;
					for (Argument arg: currArgs){
						if (arg.getArgumentName().getName().equals(x.getArgumentName().getName())){
							index = i + 1; break;
						}
						i++; 
					}
				}
				if (index > 0) {
					if (currArgs.get(index - 1).getExpression().getList() != null)
						if (arguments.get(index).getExpression().getList().getArguments() != null)
							currArgs = arguments.get(index).getExpression().getList().getArguments().getArguments();
				} else return false;
			} 
		}
		return true;
	}

	//Validate a fully qualified argument whose parent refers to a variable declared as a function 
	public boolean checkReferenceToFuctionOutput(FullyQualifiedArgumentName ref) {
		TreeIterator<EObject> iterator = ref.eResource().getAllContents();
		ArrayList<String> params = new ArrayList<String>();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof FunctionCallStatementImpl){
	    		FunctionCallStatement s = (FunctionCallStatement) obj;
	    		if (s.getSymbolName() != null && s.getSymbolName().getName().equals(ref.getParent().getName())) {	    			
	    			//Compare reference with returned variables of functions or libraries
	    			String functName = s.getExpression().getIdentifier().getName();
	    			if (FunctionValidator.libraries.contains(functName))
	    				params.addAll(FunctionValidator.standardFunctions.get(functName).getReturnedVariableNames());
	    			
	       			ArgumentName paramRef = ref.getSelectors().get(0).getArgumentName();
	       			if (paramRef != null){
	       				if (!params.contains(paramRef.getName()))
	       					warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       						paramRef.getName() + " is not in the reference set " + Utils.printList(params), 
	       						MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       						MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getName());
	       			} else {
	       				String selector = ref.getSelectors().get(0).getSelector();
	       				int index = Integer.parseInt(selector);
	       				if (index < 1 || index > params.size())
	       					warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       						"wrong index [" + index + "]. " + 
	       						"Reference set " + Utils.printList(params) + " contains " + params.size() + " items.", 
	       						MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       						MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getName());
	       			}
	       			return true; //skip list attribute check
	    		}
	    	}
	    }
	    return false;
	}
	
	//Validate MOG!
	@Check
	public void validateMOG(MclObject mcl){
		if (mcl.getMogObject() != null){
			MOGObject mog = mcl.getMogObject();	
			Integer [] params = {0, 0, 0, 0};
			for (ObjectName obj: mog.getObjects()){
				if (declaredObjects.containsKey(obj.getName())){
					MdlDataType objType = declaredObjects.get(obj.getName());
					if (objType == MdlDataType.TYPE_OBJ_REF_MODEL)
						params[0] += 1;
					if (objType == MdlDataType.TYPE_OBJ_REF_PARAM)
						params[1] += 1;
					if (objType == MdlDataType.TYPE_OBJ_REF_DATA)
						params[2] += 1;
					if (objType == MdlDataType.TYPE_OBJ_REF_TASK)
						params[3] += 1;
				}
			}
			if (params[0] == 0)
				warning(MSG_MODEL_OBJ_MISSING, 
						MdlPackage.Literals.MCL_OBJECT__MOG_OBJECT,
						MSG_MODEL_OBJ_MISSING, mcl.getObjectName().getName());
			if (params[1] == 0)
				warning(MSG_PARAM_OBJ_MISSING, 
						MdlPackage.Literals.MCL_OBJECT__MOG_OBJECT,
						MSG_PARAM_OBJ_MISSING, mcl.getObjectName().getName());
			if (params[2] == 0)
				warning(MSG_DATA_OBJ_MISSING, 
						MdlPackage.Literals.MCL_OBJECT__MOG_OBJECT,
						MSG_DATA_OBJ_MISSING, mcl.getObjectName().getName());
			if (params[3] == 0)
				warning(MSG_TASK_OBJ_MISSING, 
						MdlPackage.Literals.MCL_OBJECT__MOG_OBJECT,
						MSG_TASK_OBJ_MISSING, mcl.getObjectName().getName());
			String [] names = {"model", "parameter", "data", "task"};
			for (int i = 0; i < 4; i++){
				if (params[i] > 1)
					warning(MSG_OBJ_DEFINED + ": two or more " + names[i] + " objects selected!", 
						MdlPackage.Literals.MCL_OBJECT__MOG_OBJECT,
						MSG_OBJ_DEFINED,  mcl.getObjectName().getName());
			}			
			for (int i = 0; i < 4; i++)
				if (params[i] != 1) return;
			
			/*Validate MOG with correct set of objects*/
			MclObject dObj = null;
			MclObject mObj = null;
			for (ObjectName obj: mog.getObjects()){
				MclObject mclObj = (MclObject)obj.eContainer();
				if (mclObj.getModelObject() != null) mObj = mclObj;
				if (mclObj.getDataObject() != null) dObj = mclObj;
			}			
			if (dObj != null && mObj != null){
				List<Variable> dVars = declaredVariables.get(dObj.getObjectName().getName());
				for (ModelObjectBlock b: mObj.getModelObject().getBlocks()){
					if (b.getInputVariablesBlock() != null){
						for (SymbolDeclaration s: b.getInputVariablesBlock().getVariables()){
							String varName = "";
							if (s.getSymbolName() != null) varName = s.getSymbolName().getName();
							/* if (s.getList() != null){
								String alias = MdlPrinter.getInstance().getAttribute(s.getList().getArguments(), AttributeValidator.attr_alias.getName());
								if (alias.length() > 0) varName = alias;}*/
							if (varName.length() > 0){
								boolean notFound = true;
								for (Variable var: dVars)
									if (var.getName().equals(varName)){
										notFound = false; break;
									}
								if (notFound)
									warning(MSG_MODEL_DATA_MISMATCH + 
										": no mapping for model variable " + varName + " found in " + 
										dObj.getObjectName().getName() + " object", 
										MdlPackage.Literals.MCL_OBJECT__OBJECT_NAME,
										MSG_MODEL_DATA_MISMATCH,  mcl.getObjectName().getName());
							}
						}
					}
				}
			}
		}
	}	
}
