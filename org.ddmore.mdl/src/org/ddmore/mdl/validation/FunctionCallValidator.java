/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks function calls
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.ImportedFunction;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;
import org.ddmore.mdl.mdl.TaskObject;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

public class FunctionCallValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	
	public final static String MSG_FUNCTION_DEFINED  = "A function with such name is already defined";
	public final static String MSG_FUNCTION_UNKNOWN  = "Unknown function";
	public final static String MSG_FUNCTION_INVALID = "Invalid function call";

	//List of recognized mathematical functions (MDL = PharMML)
	final static List<String> standardFunctions = Arrays.asList(
		"abs", "exp", "factorial", "factl", "gammaln", "ln", "log", "logistic", "logit", "normcdf",
		"probit", "sqrt", "sin", "cos", "tan", "sec", "csc", "cot", "sinh", "cosh", "tanh", 
		"sech", "csch", "coth", "arcsin", "arccos", "arctan", "arcsec", "arccsc", 
		"arccot", "arcsinh", "arccosh", "arctanh", "arcsech", "arccsch", "arccoth", 
		"floor", "ceiling", "logx", "root", "min", "max");
	
	//Number of arguments for standard functions 
	//if a function is not in the list, 1 argument is implied
	//-1 stands for 1 and more, -2 for 2 and more, etc. 
	final static HashMap<String, Integer> functionParameters = 
		new HashMap<String , Integer>() {private static final long serialVersionUID = 1L;
		{//NOTE: PharmML standard functions (operators) support either 1 or 2 parameters 
		    put("logx", 2);
		    put("root", 2);
		    put("min", 2);
		    put("max", 2);
		    put("seq", 3);
		}
	};
	
	//List of recognized MDL functions
	final static List<String> specialFunctions = Arrays.asList("seq", "update", "runif", "errorexit", "PHI");
	
	//List of declared function names per object
	static HashMap<String, ArrayList<String>> externalFunctions = new HashMap<String, ArrayList<String>>();

	//List of declared function names per object
	static HashMap<String, ArrayList<String>> declaredFunctions = new HashMap<String, ArrayList<String>>();

	//TODO
	//Validate named attributes of special functions, e.g., for "seq"
	//start, stepSize, end
 	//start, stepSize, repetition
	
	Boolean isStandardFunction(String funcName){
		if (standardFunctions.contains(funcName) || specialFunctions.contains(funcName)) return true;
		return false;
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
	
	//Update the list of recognised external functions
	@Check
	public void updateExternalFunctionList(Mcl mcl){
		externalFunctions.clear();
		for (MclObject obj: mcl.getObjects()){
			ArrayList<String> funcList =  new ArrayList<String>();
			if (obj.getModelObject() != null){
				ModelObject modelObj = obj.getModelObject();
				for (ModelObjectBlock block : modelObj.getBlocks()){
					Utils.addSymbol(funcList, block.getImportBlock());
				}
				externalFunctions.put(obj.getIdentifier().getName(), funcList);
			}
			if (obj.getParameterObject() != null){
				ParameterObject paramObj = obj.getParameterObject();
				for (ParameterObjectBlock block : paramObj.getBlocks()){
					Utils.addSymbol(funcList, block.getImportBlock());
				}
				externalFunctions.put(obj.getIdentifier().getName(), funcList);
			}
			if (obj.getDataObject() != null){
				DataObject dataObj = obj.getDataObject();
				for (DataObjectBlock block : dataObj.getBlocks()){
					Utils.addSymbol(funcList, block.getImportBlock());
				}
				externalFunctions.put(obj.getIdentifier().getName(), funcList);
			}
			if (obj.getTaskObject() != null){
				TaskObject taskObj = obj.getTaskObject();
				for (TaskObjectBlock block : taskObj.getBlocks()){
					Utils.addSymbol(funcList, block.getImportBlock());
				}
				externalFunctions.put(obj.getIdentifier().getName(), funcList);
			}
		}
	}
	
	//Check whether the function with such a name is already defined
	@Check
	public void checkFunctionDeclaration(TaskFunctionDeclaration func){
		if (Utils.isSymbolDeclaredMoreThanOnce(declaredFunctions, func.getIdentifier())){
			warning(MSG_FUNCTION_DEFINED, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__IDENTIFIER);
		}
	}
	
	//Check whether the function with such a name is already defined
	@Check
	public void checkExportFunctionDeclaration(ImportedFunction func){
		if (Utils.isSymbolDeclaredMoreThanOnce(externalFunctions, func.getIdentifier())){
			warning(MSG_FUNCTION_DEFINED, MdlPackage.Literals.IMPORTED_FUNCTION__IDENTIFIER);
		}
		if (Utils.isSymbolDeclared(declaredFunctions, func.getIdentifier(), null)){
			warning(MSG_FUNCTION_DEFINED, MdlPackage.Literals.IMPORTED_FUNCTION__IDENTIFIER);
		}
		//Check that it is not standard and was not defined in Task objects
	}	
	
	//Check that the function call is to an existing function
	@Check
	public void checkFunctionCall(FunctionCall call) {
		if (!isStandardFunction(call.getIdentifier().getIdentifier())){
			if(!(Utils.isSymbolDeclared(declaredFunctions, call.getIdentifier().getIdentifier(), call.getIdentifier().getObject()))
				&& !(Utils.isSymbolDeclared(externalFunctions, call.getIdentifier().getIdentifier(), call.getIdentifier().getObject()))){
			warning(MSG_FUNCTION_UNKNOWN, 
					MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER,
					MSG_FUNCTION_UNKNOWN, call.getIdentifier().getIdentifier());
			} else {
				//declared functions (Task object) or external functions (IMPORT)
				//TODO: match number of actual parameters with the number of references in param attribute 
				//or input and output attributes together
			}
		}
		else {//standard function
			//TODO: check number of expected parameters
			Integer expected = functionParameters.get(call.getIdentifier().getIdentifier());
			if (expected == null) expected = 1; //1 by default
			Integer actual = 0;
			if (call.getArguments().getArguments() != null)
				actual = call.getArguments().getArguments().size();
			if ((expected < 0) && (actual < -expected)){
				warning(MSG_FUNCTION_INVALID + ": " +  
						call.getIdentifier().getIdentifier() + " expects " + (-expected) + " or more parameters.", 
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_INVALID, 
						call.getIdentifier().getIdentifier());
			}
			if ((expected >= 0) && (actual < expected)){
				warning(MSG_FUNCTION_INVALID + ": " +
						call.getIdentifier().getIdentifier() + " expects " + expected + " parameter(s).", 
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_INVALID, 
						call.getIdentifier().getIdentifier());
			}
		}
	}
	
}
