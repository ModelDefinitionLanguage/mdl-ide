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
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.EstimateTask;
import org.ddmore.mdl.mdl.ExecuteTask;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.SimulateTask;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;
import org.ddmore.mdl.mdl.TaskObject;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.impl.BlockStatementImpl;
import org.ddmore.mdl.mdl.impl.EstimateTaskImpl;
import org.ddmore.mdl.mdl.impl.ExecuteTaskImpl;
import org.ddmore.mdl.mdl.impl.SimulateTaskImpl;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

public class FunctionValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	public final static String MSG_FUNCTION_WRONG_TYPE = "Type error";
	public final static String MSG_FUNCTION_WRONG_PASSING_METHOD = "Wrong parameter passing method";
	
	public final static String MSG_FUNCTION_DEFINED    = "A function with such name is already defined";
	public final static String MSG_FUNCTION_UNKNOWN    = "Unknown function";
	public final static String MSG_FUNCTION_INVALID    = "Invalid function call";
	
	public final static String MSG_FUNCTION_PARAMETER_DEFINED = "A parameter with such name is already defined";
	public final static String MSG_FUNCTION_PARAMETER_UNKNOWN = "Unknown function parameter";
	public final static String MSG_FUNCTION_PARAMETER_MISSING = "Required parameter is not set";

	public final static String MSG_FUNCTION_PROPERTY_UNKNOWN = "Unknown property";
	public final static String MSG_FUNCTION_PROPERTY_MISSING = "Required property is not set";
	public final static String MSG_FUNCTION_PROPERTY_DEFINED = "Property defined more than once";

	public final static String MSG_FUNCTION_CALL_TARGET_MISSING    = "Target environment is not defined";
	public final static String MSG_FUNCTION_CALL_MODEL_OBJ_MISSING = "MOG should include a model object";
	public final static String MSG_FUNCTION_CALL_DATA_OBJ_MISSING  = "MOG should include a data object";
	public final static String MSG_FUNCTION_CALL_PARAM_OBJ_MISSING = "MOG should include a parameter object";
	public final static String MSG_FUNCTION_CALL_OBJ_DEFINED     = "Cannot create a MOG";

	final public static List<String> funct_standard1 = Arrays.asList(
		"abs", "exp", "factorial", "factl", "gammaln", "ln", "log", "logistic", "logit", "normcdf",
		"probit", "sqrt", "sin", "cos", "tan", "sec", "csc", "cot", "sinh", "cosh", "tanh", 
		"sech", "csch", "coth", "arcsin", "arccos", "arctan", "arcsec", "arccsc", 
		"arccot", "arcsinh", "arccosh", "arctanh", "arcsech", "arccsch", "arccoth", 
		"floor", "ceiling");

	final public static List<String> funct_standard2 = Arrays.asList("logx", "root", "min", "max");

	final public static String funct_runif     = "runif";
	final public static String funct_errorExit = "errorExit";
	final public static String funct_update    = "update";
	final public static String funct_pnorm     = "pnorm";
	
	final public static String funct_seq     = "seq";
	final public static FunctionParameter param_seq_start = new FunctionParameter("start", 0, MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_stepSize = new FunctionParameter("stepSize", 1, MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_repetition = new FunctionParameter("repetition", 1, MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_end = new FunctionParameter("end", 2, MdlDataType.TYPE_REAL);
	
	/*LIBRARY*/
	final public static String funct_nmadvan = "nmadvan";
	final public static String funct_PK = "PK";
	
	final public static FunctionParameter param_model = new FunctionParameter("model", 0, MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_trans = new FunctionParameter("trans", 1, MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_ncmt = new FunctionParameter("ncmt", 2, MdlDataType.TYPE_NAT, ParameterPassingMethod.IN); //number of compartments
	final public static FunctionParameter param_input = new FunctionParameter("input", 3, MdlDataType.TYPE_LIST, ParameterPassingMethod.IN);
	final public static FunctionParameter param_output = new FunctionParameter("output", 4, MdlDataType.TYPE_LIST, ParameterPassingMethod.IN_OUT);
	final public static FunctionParameter param_distribution = new FunctionParameter("distribution", 5, MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_elimination = new FunctionParameter("elimination", 6, MdlDataType.TYPE_STRING, ParameterPassingMethod.IN);
	final public static FunctionParameter param_parameterization = new FunctionParameter("parameterization", 7, MdlDataType.TYPE_STRING, ParameterPassingMethod.IN);
	
	final public static List<String> libraries = Arrays.asList(funct_nmadvan, funct_PK);
	
	final public static Map<String, FunctionSignature> standardFunctions 
		= new HashMap<String, FunctionSignature>(){
			/** * */
		private static final long serialVersionUID = -3320608024292207312L;
		{
			for (String functName: funct_standard1)
				put(functName, new FunctionSignature(functName, 1, MdlDataType.TYPE_REAL));
			for (String functName: funct_standard2)
				put(functName, new FunctionSignature(functName, 2, MdlDataType.TYPE_REAL));
						
			put(funct_seq, new FunctionSignature(funct_seq, 
				Arrays.asList(param_seq_start, param_seq_stepSize, param_seq_repetition, param_seq_end), 
				MdlDataType.TYPE_REAL, true));
			put(funct_pnorm, new FunctionSignature(funct_pnorm, 1, MdlDataType.TYPE_REAL));
			put(funct_update, new FunctionSignature(funct_update, 1, MdlDataType.TYPE_REAL));
			put(funct_errorExit, new FunctionSignature(funct_errorExit, 2, MdlDataType.TYPE_VOID));
			put(funct_runif, new FunctionSignature(funct_runif, 
				Arrays.asList(
					new FunctionParameter("n", 0, MdlDataType.TYPE_INT), 
					new FunctionParameter("R", 1, MdlDataType.TYPE_REAL, ParameterPassingMethod.IN_OUT)), 
					MdlDataType.TYPE_VOID));
			
			for (String functName: libraries){
				put(functName, new FunctionSignature(functName, Arrays.asList(
				param_model, param_trans, param_ncmt, param_input, param_output, 
				param_distribution, param_elimination, param_parameterization), 
				MdlDataType.TYPE_LIST, true));
			}
		}
	};
	
	public static List<String> funct_standardWithOutputParams = new ArrayList<String>() {
		/** * */
		private static final long serialVersionUID = -8196317757338022395L;
		{
			add(funct_runif);
			for (String functName: libraries){
				add(functName);
			}
		}
	};
	
	//List of declared function names per object
	Map<String, List<String>> declaredFunctions = new HashMap<String, List<String>>();
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//Task object
	//NOTE: the type should be TYPE_OBJ_REF but the validation will fail because task properties
	//are assigned formal parameters, i.e., model=m, parameter=p, data=d
	final public static Attribute attr_task_model = new Attribute("model", MdlDataType.TYPE_REF, true);
	final public static Attribute attr_task_parameter = new Attribute("parameter", MdlDataType.TYPE_REF, true);
	final public static Attribute attr_task_data = new Attribute("data", MdlDataType.TYPE_REF, true);
	//NOTE: the type should be TYPE_TARGET but the validation will fail because task properties
	//are assigned formal parameters, i.e., target=t
	final public static Attribute attr_task_target = new Attribute("target", MdlDataType.TYPE_REF, true);
	
	final public static Attribute attr_task_algo = new Attribute("algo", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_task_max = new Attribute("max", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_task_sig = new Attribute("sig", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_task_cov = new Attribute("cov", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_task_simopt = new Attribute("simopt", MdlDataType.TYPE_LIST, false);
		
	final public static List<Attribute> attrs_task = Arrays.asList(
			attr_task_target, attr_task_model, attr_task_parameter, attr_task_data, 
			attr_task_algo, attr_task_max, attr_task_sig, attr_task_cov, attr_task_simopt);
	
	final public static Attribute attr_task_command = new Attribute("command", MdlDataType.TYPE_STRING, true);
	final public static List<Attribute> attrs_exec_task = Arrays.asList(attr_task_command);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
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
						funcList.add(block.getFunctionDeclaration().getFunctionName().getName());
					}
				}
				declaredFunctions.put(obj.getObjectName().getName(), funcList);
			}
		}
	}
	
	//Check whether the function with such a name is already defined
	@Check
	public void checkFunctionDeclaration(TaskFunctionDeclaration func){
		if (Utils.isFunctionDeclaredMoreThanOnce(declaredFunctions, func.getFunctionName())){
			warning(MSG_FUNCTION_DEFINED, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__FUNCTION_NAME);
		}
	}
	
	//Check that the function call is to an existing function
	@Check
	public void checkFunctionCall(FunctionCall call) {
		if (!standardFunctions.containsKey(call.getIdentifier().getFunction().getName()))
			if(!Utils.isFunctionDeclared(declaredFunctions, call.getIdentifier())) 
				warning(MSG_FUNCTION_UNKNOWN, 
					MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER,
					MSG_FUNCTION_UNKNOWN, call.getIdentifier().getFunction().getName());
			else  //declared functions (Task object) 
				validateDeclaredFunction(call);
		else //standard function
			validateStandardFunction(call);
	}
	
	public void validateStandardFunction(FunctionCall call){
		FunctionSignature functSig = standardFunctions.get(call.getIdentifier().getFunction().getName());
		//TODO validate whether the function returns any value to enable/disable its use in expressions
		if (Utils.isPassedByName(call.getArguments())){
			if (functSig.isPassingByName()){
				if (call.getArguments() != null){
					for (Argument arg: call.getArguments().getArguments()){
						if (arg.getArgumentName() != null){
							boolean isFound = false;
							for (FunctionParameter p: functSig.getAllParams()){
								if (p.getName().equals(arg.getArgumentName().getName())){
									isFound = true;
									if (!MdlDataType.validateType(p.getType(), arg.getExpression()))
										warning(MSG_FUNCTION_WRONG_TYPE + ": parameter " + arg.getArgumentName().getName()
										+ " expects value of type " + p.getType(),
										MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
										MSG_FUNCTION_WRONG_TYPE, arg.getArgumentName().getName());		
								}
							}
							if (!isFound)
								warning(MSG_FUNCTION_PARAMETER_UNKNOWN + ": " + arg.getArgumentName().getName(), 
									MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
									MSG_FUNCTION_PARAMETER_UNKNOWN, arg.getArgumentName().getName());		
						}
					}	
					HashSet<String> argumentNames = new HashSet<String>();	
					for (Argument arg: call.getArguments().getArguments()){
						if (!argumentNames.contains(arg.getArgumentName().getName())){
							argumentNames.add(arg.getArgumentName().getName());
						} else {
							warning(MSG_FUNCTION_PARAMETER_DEFINED + ": " + arg.getArgumentName().getName(), 
									MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
									MSG_FUNCTION_PARAMETER_DEFINED, arg.getArgumentName().getName());		
						}
					}
				}
			}	
			else {	
				warning(MSG_FUNCTION_INVALID + ": cannot pass parameters by name to " +
					call.getIdentifier().getFunction().getName(), 
					MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
					MSG_FUNCTION_INVALID, 
					call.getIdentifier().getFunction().getName());					
			}	
		} else {
			if (Utils.isPassedByPlace(call.getArguments())) {
				int expected = functSig.getNumberOfParams();
				int actual = 0;
				if (call.getArguments().getArguments() != null)
					actual = call.getArguments().getArguments().size();
				if (actual != expected){
					warning(MSG_FUNCTION_INVALID + ": " +
							call.getIdentifier().getFunction().getName() + " expects " + expected + " parameter(s).", 
							MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
							MSG_FUNCTION_INVALID, 
							call.getIdentifier().getFunction().getName());
					return;
				}
				if (call.getArguments() != null){
					List<FunctionParameter> defaultParams = functSig.getDefaultParams();
					for (int i = 0; i < call.getArguments().getArguments().size(); i++){
						Argument arg = call.getArguments().getArguments().get(i);
						if (!MdlDataType.validateType(defaultParams.get(i).getType(), arg.getExpression())){
							warning(MSG_FUNCTION_WRONG_TYPE + ": parameter #" + i + " expects value of type " + 
									defaultParams.get(i).getType(),
							MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
							MSG_FUNCTION_WRONG_TYPE, "" + String.valueOf(i));		
						}
					}
				}
			} else {
				warning(MSG_FUNCTION_WRONG_PASSING_METHOD + ": cannot mix parameter passing by place and by name",
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_WRONG_PASSING_METHOD, 
						call.getIdentifier().getFunction().getName());	
			}
		}
	}
	
	public void validateDeclaredFunction(FunctionCall call){
		if (Utils.isFunctionDeclared(declaredFunctions, call.getIdentifier())){
			byte params[] = new byte[ 4];
			for (int i = 0; i < 4; i++) params[i] = 0;
			for (Argument arg: call.getArguments().getArguments()){
				if (MdlDataType.validateType(MdlDataType.TYPE_TARGET, arg.getExpression()))
					params[0] += 1;
				if (MdlDataType.validateType(MdlDataType.TYPE_OBJ_REF_MODEL, arg.getExpression()))
					params[1] += 1;
				if (MdlDataType.validateType(MdlDataType.TYPE_OBJ_REF_PARAM, arg.getExpression()))
					params[2] += 1;
				if (MdlDataType.validateType(MdlDataType.TYPE_OBJ_REF_DATA, arg.getExpression()))
					params[3] += 1;
			}
			String[] names = {"model", "parameter", "data"};
			if (params[0] == 0)
				warning(MSG_FUNCTION_CALL_TARGET_MISSING, 
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_CALL_TARGET_MISSING, call.getIdentifier().getFunction().getName());
			if (params[1] == 0)
				warning(MSG_FUNCTION_CALL_MODEL_OBJ_MISSING, 
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_CALL_MODEL_OBJ_MISSING, call.getIdentifier().getFunction().getName());
			if (params[2] == 0)
				warning(MSG_FUNCTION_CALL_PARAM_OBJ_MISSING, 
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_CALL_PARAM_OBJ_MISSING, call.getIdentifier().getFunction().getName());
			if (params[3] == 0)
				warning(MSG_FUNCTION_CALL_DATA_OBJ_MISSING, 
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_CALL_DATA_OBJ_MISSING, call.getIdentifier().getFunction().getName());
			for (int i = 1; i < 4; i++){
				if (params[i] > 1)
					warning(MSG_FUNCTION_CALL_OBJ_DEFINED + ": two or more " + names[i-1] + " objects selected!", 
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_CALL_OBJ_DEFINED, call.getIdentifier().getFunction().getName());
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	@Check
	public void checkRequiredProperties(EstimateTask t){
		HashSet<String> properties = new HashSet<String>();
		for (BlockStatement b: t.getStatements()){
			if (b.getSymbol() != null){
				if (!properties.contains(b.getSymbol().getSymbolName().getName())){
					properties.add(b.getSymbol().getSymbolName().getName());
				} else {
					warning(MSG_FUNCTION_PROPERTY_DEFINED + ": " + b.getSymbol().getSymbolName().getName(), 
							MdlPackage.Literals.ESTIMATE_TASK__IDENTIFIER,
							MSG_FUNCTION_PROPERTY_DEFINED, b.getSymbol().getSymbolName().getName());
				}
			}
		}	
		for (String attrName: getRequiredAttributeNames(t)){
			if (!properties.contains(attrName)) 
				warning(MSG_FUNCTION_PROPERTY_MISSING + ": " + attrName, 
				MdlPackage.Literals.ESTIMATE_TASK__IDENTIFIER, MSG_FUNCTION_PROPERTY_MISSING, attrName);
		}
	}

	@Check
	public void checkRequiredProperties(SimulateTask t){
		HashSet<String> properties = new HashSet<String>();
		for (BlockStatement b: t.getStatements()){
			if (b.getSymbol() != null){
				if (!properties.contains(b.getSymbol().getSymbolName().getName())){
					properties.add(b.getSymbol().getSymbolName().getName());
					//TODO Check type
				}
				else {
					warning(MSG_FUNCTION_PROPERTY_DEFINED + ": " + b.getSymbol().getSymbolName().getName(), 
							MdlPackage.Literals.SIMULATE_TASK__IDENTIFIER,
							MSG_FUNCTION_PROPERTY_DEFINED, b.getSymbol().getSymbolName().getName());	
				}
			}
		}
		for (String attrName: getRequiredAttributeNames(t)){
			if (!properties.contains(attrName)) 
				warning(MSG_FUNCTION_PROPERTY_MISSING + ": " + attrName, 
				MdlPackage.Literals.SIMULATE_TASK__IDENTIFIER, MSG_FUNCTION_PROPERTY_MISSING, attrName);
		}
	}

	@Check
	public void checkRequiredProperties(ExecuteTask t){
		HashSet<String> properties = new HashSet<String>();
		for (BlockStatement b: t.getStatements()){
			if (b.getSymbol() != null){
				if (!properties.contains(b.getSymbol().getSymbolName().getName())){
					properties.add(b.getSymbol().getSymbolName().getName());
				}
				else {
					warning(MSG_FUNCTION_PROPERTY_DEFINED + ": " + b.getSymbol().getSymbolName().getName(), 
							MdlPackage.Literals.EXECUTE_TASK__IDENTIFIER,
							MSG_FUNCTION_PROPERTY_DEFINED, b.getSymbol().getSymbolName().getName());	
				}
			}
		}	
		for (String attrName: getRequiredAttributeNames(t)){
			if (!properties.contains(attrName)) 
				warning(MSG_FUNCTION_PROPERTY_MISSING + ": " + attrName, 
				MdlPackage.Literals.EXECUTE_TASK__IDENTIFIER, MSG_FUNCTION_PROPERTY_MISSING, attrName);
		}
	}
	
	@Check
	public void checkAllProperties(SymbolDeclaration s){
		EObject container = s.eContainer();
		while (container instanceof BlockStatementImpl)
			container = container.eContainer();
		if (container instanceof EstimateTaskImpl ||
			container instanceof SimulateTaskImpl ||
			container instanceof ExecuteTaskImpl){
			//check that an argument is recognized
			List<Attribute> knownAttributes = getAllAttributes(container);
			if (knownAttributes != null){
				List<String> attributeNames = Utils.getAllNames(knownAttributes);
				if (!attributeNames.contains(s.getSymbolName().getName())){
					warning(MSG_FUNCTION_PROPERTY_UNKNOWN + ": " + s.getSymbolName().getName(), 
					MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME,
					MSG_FUNCTION_PROPERTY_UNKNOWN, s.getSymbolName().getName());		
				}
				for (Attribute x: knownAttributes){
					if (x.getName().equals(s.getSymbolName().getName())){
						boolean isValid = false;
						if (s.getExpression() != null) 
							isValid = MdlDataType.validateType(x.getType(), s.getExpression());
						if (!isValid){
							warning(MSG_FUNCTION_WRONG_TYPE + 
								": property \"" + s.getSymbolName().getName() + "\" expects value of type " + x.getType().name(), 
								MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME,
								MSG_FUNCTION_WRONG_TYPE, s.getSymbolName().getName());		
						}
						break;
					}
				}
			}
			return;
		}
	}
	
	List<Attribute> getAllAttributes(EObject obj){
		if (obj instanceof EstimateTaskImpl || obj instanceof SimulateTaskImpl)
			return attrs_task;
		if (obj instanceof ExecuteTaskImpl)
			return attrs_exec_task;
		return null;
	}
	
	List<String> getRequiredAttributeNames(EObject obj){
		if (obj instanceof EstimateTaskImpl || obj instanceof SimulateTaskImpl)
			return Utils.getRequiredNames(attrs_task);
		if (obj instanceof ExecuteTaskImpl)
			return Utils.getRequiredNames(attrs_exec_task);
		return null;
	}	
	
	ArrayList<String> getParamNames(List<FunctionParameter> params){
		ArrayList<String> names = new ArrayList<String>();
		if (params != null){
			for (FunctionParameter param: params){
				names.add(param.getName());
			}
		}
		return names;
	}

}
