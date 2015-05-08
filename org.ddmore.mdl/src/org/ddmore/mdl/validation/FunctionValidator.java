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

import org.ddmore.mdl.domain.FunctionParameter;
import org.ddmore.mdl.domain.FunctionParameterSet;
import org.ddmore.mdl.domain.FunctionSignature;
import org.ddmore.mdl.domain.ParameterPassingMethod;
import org.ddmore.mdl.domain.Variable;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentExpression;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.PkParameterType;
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

	//Synchronised with PharmML v.4.0.1 
	final public static List<String> funct_standard1 = Arrays.asList(
		"abs","exp", "factorial", "factln", "gammaln",
		"lfactorial", "ln", //Supported by MDL but not PharmML
		"log", "log2", "log10", "logit", "logistic", "invLogit",
		"normcdf", "probit", "sqrt",
		//trigonometric
		"sin", "cos", "tan", "cot", "atan2", "sec", "csc", "sinh", "cosh", "tanh", "sech", "csch", "coth",
		"arcsin", "arccos", "arctan", "arcsec", "arccsc", "arccot", "arcsinh", "arccosh", "arctanh", "arcsech", "arccsch", "arccoth",
		//type conversion
		"floor", "ceiling");

	final public static List<String> funct_standard2 = Arrays.asList("logx", "root", "min", "max", "rem");

	//final public static String funct_runif     = "runif";
	//final public static String funct_pnorm     = "pnorm";	
	//final public static FunctionParameter param_n = new FunctionParameter("n", MdlDataType.TYPE_INT);
	
	final public static String funct_seq = "seq";
	final public static FunctionParameter param_seq_start      = new FunctionParameter("start", MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_stepSize   = new FunctionParameter("stepSize", MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_repetition = new FunctionParameter("repetition", MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_seq_end        = new FunctionParameter("end", MdlDataType.TYPE_REAL);

	final public static String funct_error_additive   	  = "additiveError";
	final public static String funct_error_prop       	  = "proportionalError";
	final public static String funct_error_combined1  	  = "combinedError1";
	final public static String funct_error_combined2  	  = "combinedError2";
	final public static String funct_error_combined2log	  = "combinedError2log";
	final public static String funct_error_combined3  	  = "combinedError3";
	final public static String funct_error_power      	  = "powerError";
	final public static String funct_error_combinedPower1 = "combinedPowerError1";

	final public static FunctionParameter param_error_additive     = new FunctionParameter("additive", MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_error_proportional = new FunctionParameter("proportional", MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_error_power        = new FunctionParameter("power", MdlDataType.TYPE_REAL);
	final public static FunctionParameter param_error_f            = new FunctionParameter("f", MdlDataType.TYPE_REAL);

	final public static List<String> errorModels = Arrays.asList(
		funct_error_additive, funct_error_prop, funct_error_combined1, funct_error_combined2, funct_error_combined2log, funct_error_combined3, 
		funct_error_power, funct_error_combinedPower1);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*LIBRARY*/
	final public static String lib_PK      = "PK";
	final public static List<String> libraries = Arrays.asList(lib_PK);
	
	/*PK parameters*/
	final public static FunctionParameter param_PK_ndist = new FunctionParameter("ndist", MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_PK_depot = new FunctionParameter("depot", MdlDataType.TYPE_BOOLEAN, ParameterPassingMethod.IN);
	final public static FunctionParameter param_PK_par   = new FunctionParameter("par", MdlDataType.TYPE_PK_PARAMETER, ParameterPassingMethod.IN, PkParameterType.VCL.toString());
	final public static FunctionParameter param_PK_glm   = new FunctionParameter("glm", MdlDataType.TYPE_BOOLEAN, ParameterPassingMethod.IN);
	final public static FunctionParameter param_PK_glmr  = new FunctionParameter("glmr", MdlDataType.TYPE_BOOLEAN, ParameterPassingMethod.IN);
	final public static FunctionParameter param_PK_ncomp = new FunctionParameter("ncomp", MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_PK_mom1  = new FunctionParameter("mom1", MdlDataType.TYPE_BOOLEAN, ParameterPassingMethod.IN);
	
	//////////////////////////////////////////////////////////////////////////
	final public static Map<String, FunctionSignature> standardFunctions 
		= new HashMap<String, FunctionSignature>(){
			/** * */
		private static final long serialVersionUID = -3320608024292207312L;
		{
			/*Standard mathematical functions*/
			for (String functName: funct_standard1)
				put(functName, new FunctionSignature(functName, 1, MdlDataType.TYPE_REAL));
			for (String functName: funct_standard2)
				put(functName, new FunctionSignature(functName, 2, MdlDataType.TYPE_REAL));
						
			put(funct_seq, new FunctionSignature(funct_seq, 
				Arrays.asList(
					new FunctionParameterSet(Arrays.asList(param_seq_start, param_seq_stepSize, param_seq_repetition)),
					new FunctionParameterSet(Arrays.asList(param_seq_start, param_seq_stepSize, param_seq_end))
				), MdlDataType.TYPE_VECTOR_INT, true));
			//put(funct_pnorm, new FunctionSignature(funct_pnorm, 1, MdlDataType.TYPE_REAL));
			//put(funct_runif, new FunctionSignature(funct_runif, new FunctionParameterSet(param_n), MdlDataType.TYPE_REAL));
			
			/*Error models*/
			put(funct_error_additive, new FunctionSignature(funct_error_additive, 
					new FunctionParameterSet(param_error_additive), MdlDataType.TYPE_REAL, true));
			put(funct_error_prop, new FunctionSignature(funct_error_prop, 
					new FunctionParameterSet(Arrays.asList(param_error_proportional, param_error_f)),
					MdlDataType.TYPE_REAL, true));
			put(funct_error_combined1, new FunctionSignature(funct_error_combined1, 
					new FunctionParameterSet(Arrays.asList(param_error_additive, param_error_proportional, param_error_f)),
					MdlDataType.TYPE_REAL, true));
			put(funct_error_combined2, new FunctionSignature(funct_error_combined2, 
					new FunctionParameterSet(Arrays.asList(param_error_additive, param_error_proportional, param_error_f)),
					MdlDataType.TYPE_REAL, true));
			put(funct_error_combined2log, new FunctionSignature(funct_error_combined2log, 
					new FunctionParameterSet(Arrays.asList(param_error_additive, param_error_proportional, param_error_f)),
					MdlDataType.TYPE_REAL, true));
			put(funct_error_combined3, new FunctionSignature(funct_error_combined3, 
					new FunctionParameterSet(Arrays.asList(param_error_additive, param_error_proportional, param_error_f)),
					MdlDataType.TYPE_REAL, true));
			put(funct_error_power, new FunctionSignature(
					funct_error_power, 
					new FunctionParameterSet(Arrays.asList(param_error_proportional, param_error_power, param_error_f)), MdlDataType.TYPE_REAL, true));
			put(funct_error_combinedPower1, new FunctionSignature(
					funct_error_combinedPower1, 
					new FunctionParameterSet(Arrays.asList(param_error_additive, param_error_proportional, param_error_power, param_error_f)), MdlDataType.TYPE_REAL, true));
			
			/*Libraries*/
			//PK
			Variable A = new Variable("A", MdlDataType.TYPE_VECTOR_REAL); 
			Variable F = new Variable("F", MdlDataType.TYPE_REAL);
			Map<String, List<Variable>> pkReturnedValues = new HashMap<String, List<Variable>>();
			pkReturnedValues.put("",  Arrays.asList(A, F));
			
			put(lib_PK, new FunctionSignature(lib_PK, new FunctionParameterSet(Arrays.asList(
				param_PK_ndist, param_PK_depot, param_PK_par, 
				param_PK_glm, param_PK_glmr, param_PK_ncomp, param_PK_mom1)), 
				MdlDataType.TYPE_VECTOR_REF, true, pkReturnedValues)
			);					
		}
	};
	
	/*Key: values of attributes in the following order: ndist|depot|par|glm|glmr|ncomp|mom1
	 * Boolean attributes which are not 'true' are skipped */

	//PK
	static Map<String, List<String>> pkInputParameters = new HashMap<String, List<String>>(){
		/** * */
		private static final long serialVersionUID = -8196317757338022395L;
		{
			//v_cl
			put("ndist=1|par=v_cl", Arrays.asList("V", "CL"));
			put("ndist=1|depot=true|par=v_cl", Arrays.asList("V", "CL", "KA"));
			put("ndist=2|par=v_cl", Arrays.asList("CL", "V1", "Q", "V2"));
			put("ndist=2|depot=true|par=v_cl", Arrays.asList("CL", "V2", "Q", "KA"));
			put("ndist=3|par=v_cl", Arrays.asList("CL","V1","Q2","V2","Q3" , "V3"));
			put("ndist=3|depot=true|par=v_cl", Arrays.asList("CL", "V2", "Q3", "V3", "Q4", "V4", "KA"));
			put("par=v_cl|mom1=true", Arrays.asList("V", "VM", "KM"));
			//v_k
			put("ndist=1|par=v_k", Arrays.asList("V", "K"));
			put("ndist=1|depot=true|par=v_k", Arrays.asList("V", "K", "KA"));
			put("ndist=2|par=v_k", Arrays.asList("V", "K", "K12", "K21"));
			put("ndist=2|depot=true|par=v_k", Arrays.asList("V", "K", "K23", "K32", "KA"));
			put("ndist=3|par=v_k", Arrays.asList("V", "K", "K12", "K21", "K13", "K31"));
			put("ndist=3|depot=true|par=v_k", Arrays.asList("V", "K", "K23", "K32", "K24", "K42", "KA"));
			put("par=v_k|glm=true", Arrays.asList("V", "K"));
			put("par=v_k|glmr=true", Arrays.asList("V", "K"));
			//vss_cl
			put("ndist=2|par=vss_cl", Arrays.asList("CL", "V", "Q", "VSS"));
			put("ndist=2|depot=true|par=vss_cl", Arrays.asList("CL", "V", "Q", "VSS", "KA"));
			//abk
			put("ndist=2|par=abk", Arrays.asList("ALPHA", "BETA", "K21"));
			put("ndist=2|depot=true|par=abk", Arrays.asList("ALPHA", "BETA", "K32", "KA"));
			put("ndist=3|par=abk", Arrays.asList("ALPHA", "BETA", "GAMMA", "K21", "K31"));
			put("ndist=3|depot=true|par=abk", Arrays.asList("ALPHA", "BETA", "GAMMA", "K32", "K42", "KA"));
		}
	};
		
	
	public static List<String> funct_standardWithOutputParams = new ArrayList<String>() {
		/** * */
		private static final long serialVersionUID = -8196317757338022395L;
		{
			for (String functName: libraries)
				add(functName);
		}
	};
	

	//Check that the function call is to an existing function
	@Check
	public void checkFunctionCall(FunctionCall call) {
		if (!standardFunctions.containsKey(call.getIdentifier().getName()))
			error(MSG_FUNCTION_UNKNOWN, 
					MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER,
			MSG_FUNCTION_UNKNOWN, call.getIdentifier().getName());
		else {
			//standard function
			validateStandardFunction(call);
			if (call.getIdentifier().getName().equals(lib_PK))
				validatePK(call);
		}
	}
	
private void validateStandardFunction(FunctionCall call){
		FunctionSignature functSig = standardFunctions.get(call.getIdentifier().getName());
		//TODO validate whether the function returns any value to enable/disable its use in expressions
		//TODO instead of checking whether a parameter is known, match a list of actual parameters with one of valid sets!
		int expected = functSig.getNumberOfParams();
		if (call.getArguments() == null){
			if (expected > 0) 
				error(MSG_FUNCTION_WRONG_PASSING_METHOD + ": function exprects " + expected + " parameter(s)",
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_WRONG_PASSING_METHOD, 
						call.getIdentifier().getName());	
			return;
		} 
		if (call.getArguments().getNamedArguments() != null){
			if (functSig.isPassingByName()){
				//Check that only valid parameters are passed
				for (Argument arg: call.getArguments().getNamedArguments().getArguments()){
					if (arg.getArgumentName() != null){
						Map<String, FunctionParameter> allParams = functSig.getAllParams(); 
						if (allParams.containsKey(arg.getArgumentName().getName())){
							FunctionParameter p = allParams.get(arg.getArgumentName().getName());
							if (!MdlDataType.validateType(p.getType(), arg.getExpression()))
								error(MSG_FUNCTION_WRONG_TYPE + ": parameter " + arg.getArgumentName().getName()
								+ " expects value of type " + p.getType(),
								MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
								MSG_FUNCTION_WRONG_TYPE, arg.getArgumentName().getName());		
						} else 
							error(MSG_FUNCTION_PARAMETER_UNKNOWN + ": " + arg.getArgumentName().getName(), 
								MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
								MSG_FUNCTION_PARAMETER_UNKNOWN, arg.getArgumentName().getName());		
						}
					}	
				HashSet<String> argumentNames = new HashSet<String>();	
				for (Argument arg: call.getArguments().getNamedArguments().getArguments()){
					if (!argumentNames.contains(arg.getArgumentName().getName())){
						argumentNames.add(arg.getArgumentName().getName());
					} else {
						error(MSG_FUNCTION_PARAMETER_DEFINED + ": " + arg.getArgumentName().getName(), 
								MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
								MSG_FUNCTION_PARAMETER_DEFINED, arg.getArgumentName().getName());		
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
			if (!functSig.isPassingByName()){
				int actual = call.getArguments().getUnnamedArguments().getArguments().size();
				if (actual != expected){
					warning(MSG_FUNCTION_INVALID + ": " +
							call.getIdentifier().getName() + " expects " + expected + " parameter(s).", 
							MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
							MSG_FUNCTION_INVALID, 
							call.getIdentifier().getName());
					return;
				}
				List<FunctionParameter> defaultParams = functSig.getDefaultParamSet();
				for (int i = 0; i < call.getArguments().getUnnamedArguments().getArguments().size(); i++){
					ArgumentExpression arg = call.getArguments().getUnnamedArguments().getArguments().get(i);
					if (!MdlDataType.validateType(defaultParams.get(i).getType(), arg.getExpression())){
						warning(MSG_FUNCTION_WRONG_TYPE + ": parameter #" + i + " expects value of type " + 
								defaultParams.get(i).getType(),
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_WRONG_TYPE, "" + String.valueOf(i));		
					}
				}
			} else {
				warning(MSG_FUNCTION_WRONG_PASSING_METHOD + ": cannot pass parameters by place to " +
						call.getIdentifier().getName(),
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_WRONG_PASSING_METHOD, 
						call.getIdentifier().getName());	
			}
		}
	}
	
	//Check that only valid values are passed to PK 
	private void validatePK(FunctionCall call){
		if (call.getArguments() != null){
			String paramKey = standardFunctions.get(lib_PK).getParameterKey(call.getArguments());
			if (!pkInputParameters.containsKey(paramKey)){
				warning(MSG_FUNCTION_INVALID + ": unknown " + lib_PK + " parameters value combination", 
						MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
						MSG_FUNCTION_INVALID, 
						call.getIdentifier().getName());					
			}
		}
	}
}
