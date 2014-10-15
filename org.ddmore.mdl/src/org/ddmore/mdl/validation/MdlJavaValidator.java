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

import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl;
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

	public final static String MSG_SYMBOL_DEFINED  = "A variable or parameter with such name already exists";
	public final static String MSG_SYMBOL_UNKNOWN  = "Unresolved reference: parameter, variable, object or formal argument not declared";
		
	public final static String MSG_UNRESOLVED_FUNC_ARGUMENT_REF = "Unresolved reference to a function output parameter";
	public final static String MSG_UNRESOLVED_SAME_BLOCK_NAME = "No corresponding matrix or diag block found";
	
	public final static String MSG_UNRESOLVED_ATTRIBUTE_REF = "Unresolved reference to a list attribute";
	public final static String MSG_UNRESOLVED_DISTR_ATTRIBUTE_REF = "Unresolved reference to a distribution attribute";

	public final static String MSG_MODEL_OBJ_MISSING = "MOG should include a model object";
	public final static String MSG_DATA_OBJ_MISSING  = "MOG should include a data object";
	public final static String MSG_PARAM_OBJ_MISSING = "MOG should include a parameter object";
	public final static String MSG_TASK_OBJ_MISSING  = "MOG should include a task object";
	public final static String MSG_OBJ_DEFINED       = "Cannot create a MOG";

	//List of objects
	Map<String, MdlDataType> declaredObjects = new HashMap<String, MdlDataType>();	
	
	//List of declared variables per object
	Map<String, List<String>> declaredVariables = new HashMap<String, List<String>>();	
	
	//List of declared variability subblocks diag and matrix (to match with same blocks)
	Map<String, List<String>> variabilitySubblockNames = new HashMap<String, List<String>>();

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
								String name = Utils.getAttributeValue(s.getMatrixBlock().getArguments(), AttributeValidator.attr_name.getName());
								if (name.length() > 0) paramList.add(name);
							}
							if (s.getDiagBlock() != null){
								String name = Utils.getAttributeValue(s.getDiagBlock().getArguments(), AttributeValidator.attr_name.getName());
								if (name.length() > 0) paramList.add(name);
							}
						}
					}
				}
				variabilitySubblockNames.put(obj.getObjectName().getName(), paramList);
			}
		}
	}
	
	@Check
	public void updateLinkedObjects(Mcl mcl){
		mogs = Utils.getMOGs(mcl);
	}
	
	//Match the name of the same block with the name of a matrix or a diag block
	@Check
	public void validateSameSubblockName(SameBlock b){
		String name = Utils.getAttributeValue(b.getArguments(), AttributeValidator.attr_name.getName());
		if (name.length() > 0){
			ObjectName objName = Utils.getObjectName(b.eContainer());
			if (!Utils.isIdentifierDeclared(variabilitySubblockNames, name, objName))
				warning(MSG_UNRESOLVED_SAME_BLOCK_NAME, 
						MdlPackage.Literals.SAME_BLOCK__IDENTIFIER,
						MSG_UNRESOLVED_SAME_BLOCK_NAME, b.getIdentifier());
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
		
		if (container instanceof FullyQualifiedArgumentNameImpl){
			if (!Utils.isSymbolDeclared(declaredVariables, ref))
				warning(MSG_SYMBOL_UNKNOWN, MdlPackage.Literals.SYMBOL_NAME__NAME,
						MSG_SYMBOL_UNKNOWN, ref.getName());
		}
		else {
			//TODO: for MOG validation, collect declared variables for a given object name instead of all
			if (!(Utils.isSymbolDeclared(declaredVariables, ref, mogs) ||
					declaredObjects.containsKey(ref.getName()) )){
				warning(MSG_SYMBOL_UNKNOWN, MdlPackage.Literals.SYMBOL_NAME__NAME,
						MSG_SYMBOL_UNKNOWN, ref.getName());
			}
		}
	}

	@Check
	public void checkReference(FullyQualifiedArgumentName ref) {
		//Skip if the reference is to the symbol with assigned expression which is a function call
		if (checkReferenceToFuctionOutput(ref)) return;
		List<Argument> args = new LinkedList<Argument>();
		List<DistributionArgument> distrArgs = new LinkedList<DistributionArgument>();		
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
	       				if (s.getRandomList() != null)
	       					for (DistributionArgument x: s.getRandomList().getArguments().getArguments())
			           			distrArgs.add(x);
		    		}
	    		}
	    	}
	    }
	    if ((args.size() > 0) && !AttributeValidator.checkAttributes(ref, args))
			warning(MSG_UNRESOLVED_ATTRIBUTE_REF, 
				MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
					MSG_UNRESOLVED_ATTRIBUTE_REF, ref.getParent().getName());
	    if ((distrArgs.size() > 0) && !DistributionValidator.checkAttributes(ref, distrArgs))
			warning(MSG_UNRESOLVED_DISTR_ATTRIBUTE_REF, 
				MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
					MSG_UNRESOLVED_DISTR_ATTRIBUTE_REF, ref.getParent().getName());
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
					if (declaredObjects.get(obj.getName()) == MdlDataType.TYPE_OBJ_REF_MODEL)
						params[0] += 1;
					if (declaredObjects.get(obj.getName()) == MdlDataType.TYPE_OBJ_REF_PARAM)
						params[1] += 1;
					if (declaredObjects.get(obj.getName()) == MdlDataType.TYPE_OBJ_REF_DATA)
						params[2] += 1;
					if (declaredObjects.get(obj.getName()) == MdlDataType.TYPE_OBJ_REF_TASK)
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
		}
	}
}
