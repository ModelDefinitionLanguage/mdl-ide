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
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.types.MdlDataType;
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

	final public static List<String> funct_standard1 = Arrays.asList(
		"abs", "exp", "factorial", "factl", "gammaln", "ln", "log", "logistic", "logit", "normcdf",
		"probit", "sqrt", "sin", "cos", "tan", "sec", "csc", "cot", "sinh", "cosh", "tanh", 
		"sech", "csch", "coth", "arcsin", "arccos", "arctan", "arcsec", "arccsc", 
		"arccot", "arcsinh", "arccosh", "arctanh", "arcsech", "arccsch", "arccoth", 
		"floor", "ceiling");

	final public static List<String> funct_standard2 = Arrays.asList("logx", "root", "min", "max");

	final public static String funct_runif     = "runif";
	final public static String funct_errorExit = "errorExit";
	final public static String funct_pnorm     = "pnorm";
	
	final public static String funct_seq     = "seq";
	final public static FunctionParameter param_seq_start = new FunctionParameter("start", 0, MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_stepSize = new FunctionParameter("stepSize", 1, MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_repetition = new FunctionParameter("repetition", 1, MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_end = new FunctionParameter("end", 2, MdlDataType.TYPE_REAL);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*LIBRARY*/
	final public static String lib_nmadvan = "nmadvan";
	final public static String lib_PK = "PK";

	final public static List<String> libraries = Arrays.asList(lib_nmadvan, lib_PK);
	
	/*nmadvan parameters*/
	final public static FunctionParameter param_nmadvan_model = new FunctionParameter("model", 0, MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_trans = new FunctionParameter("trans", 1, MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_ncmt = new FunctionParameter("ncmt", 2, MdlDataType.TYPE_NAT, ParameterPassingMethod.IN); //number of compartments
	final public static FunctionParameter param_nmadvan_input = new FunctionParameter("input", 3, MdlDataType.TYPE_LIST, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_distribution = new FunctionParameter("distribution", 4, MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_elimination = new FunctionParameter("elimination", 5, MdlDataType.TYPE_STRING, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_parameterization = new FunctionParameter("parameterization", 6, MdlDataType.TYPE_STRING, ParameterPassingMethod.IN);
	
	/*PK parameters*/
	final public static FunctionParameter param_PK_ndist = new FunctionParameter("ndist", 0, 
			MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_PK_depot = new FunctionParameter("depot", 1, 
			MdlDataType.TYPE_BOOLEAN, ParameterPassingMethod.IN);
	
	//////////////////////////////////////////////////////////////////////////
	final public static Map<String, FunctionSignature> standardFunctions 
		= new HashMap<String, FunctionSignature>(){
			/** * */
		private static final long serialVersionUID = -3320608024292207312L;
		{
			for (String functName: funct_standard1)
				put(functName, new FunctionSignature(functName, 1, MdlDataType.TYPE_REAL));
			for (String functName: funct_standard2)
				put(functName, new FunctionSignature(functName, 2, MdlDataType.TYPE_REAL));
						
			put(funct_seq, new FunctionSignature(funct_seq, Arrays.asList(param_seq_start, param_seq_stepSize, param_seq_repetition, param_seq_end), MdlDataType.TYPE_REAL, true));
			put(funct_pnorm, new FunctionSignature(funct_pnorm, 1, MdlDataType.TYPE_REAL));
			put(funct_errorExit, new FunctionSignature(funct_errorExit, 2, MdlDataType.TYPE_VOID));
			put(funct_runif, new FunctionSignature(funct_runif, Arrays.asList(new FunctionParameter("n", 0, MdlDataType.TYPE_INT)), MdlDataType.TYPE_REAL));
			
			/*libraries*/
			//nmadvan
			put(lib_nmadvan, new FunctionSignature(lib_nmadvan, 
				Arrays.asList(
					param_nmadvan_model, param_nmadvan_trans, param_nmadvan_ncmt, param_nmadvan_input, 
					param_nmadvan_distribution, param_nmadvan_elimination, param_nmadvan_parameterization), 
				MdlDataType.TYPE_VECTOR_REF, true,
				Arrays.asList(
						new Variable("A", MdlDataType.TYPE_VECTOR_REAL), 
						new Variable("F", MdlDataType.TYPE_REAL))
				)
			);
			
			//PK
			put(lib_PK, new FunctionSignature(lib_PK, Arrays.asList(
				param_PK_ndist, param_PK_depot), 
				MdlDataType.TYPE_VECTOR_REF, true, 
				Arrays.asList(
						new Variable("V", MdlDataType.TYPE_UNDEFINED), 
						new Variable("KL", MdlDataType.TYPE_UNDEFINED),
						new Variable("KA", MdlDataType.TYPE_UNDEFINED))
				)
			);					
		}
	};
	
	public static List<String> funct_standardWithOutputParams = new ArrayList<String>() {
		/** * */
		private static final long serialVersionUID = -8196317757338022395L;
		{
			for (String functName: libraries){
				add(functName);
			}
		}
	};
	

	//Check that the function call is to an existing function
	@Check
	public void checkFunctionCall(FunctionCall call) {
		if (!standardFunctions.containsKey(call.getIdentifier().getName()))
			warning(MSG_FUNCTION_UNKNOWN, 
					MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER,
			MSG_FUNCTION_UNKNOWN, call.getIdentifier().getName());
		else //standard function
			validateStandardFunction(call);
	}
	
	public void validateStandardFunction(FunctionCall call){
		FunctionSignature functSig = standardFunctions.get(call.getIdentifier().getName());
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
					call.getIdentifier().getName(), 
					MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
					MSG_FUNCTION_INVALID, 
					call.getIdentifier().getName());					
			}	
		} else {
			if (Utils.isPassedByPlace(call.getArguments())) {
				int expected = functSig.getNumberOfParams();
				int actual = 0;
				if (call.getArguments().getArguments() != null)
					actual = call.getArguments().getArguments().size();
				if (actual != expected){
					warning(MSG_FUNCTION_INVALID + ": " +
							call.getIdentifier().getName() + " expects " + expected + " parameter(s).", 
							MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
							MSG_FUNCTION_INVALID, 
							call.getIdentifier().getName());
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
						call.getIdentifier().getName());	
			}
		}
	}
}
