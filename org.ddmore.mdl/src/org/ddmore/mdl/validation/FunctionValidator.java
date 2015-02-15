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
import org.ddmore.mdl.mdl.Arguments;
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
		"ln", //Supported by MDL but not PharmML
		"log", "log2", "log10", "logistic", "logit",
		"normcdf", "probit", "sqrt",
		//trigonometric
		"sin", "cos", "tan", "cot", "atan2", "sec", "csc", "sinh", "cosh", "tanh", "sech", "csch", "coth",
		"arcsin", "arccos", "arctan", "arcsec", "arccsc", "arccot", "arcsinh", "arccosh", "arctanh", "arcsech", "arccsch", "arccoth",
		//type conversion
		"floor", "ceiling");

	final public static List<String> funct_standard2 = Arrays.asList("logx", "root", "min", "max", "rem");

	final public static String funct_runif     = "runif";
	final public static String funct_pnorm     = "pnorm";	
	final public static FunctionParameter param_n = new FunctionParameter("n", MdlDataType.TYPE_INT);
	
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

	final public static FunctionParameter param_error_additive     = new FunctionParameter("additive", MdlDataType.TYPE_REF);
	final public static FunctionParameter param_error_proportional = new FunctionParameter("proportional", MdlDataType.TYPE_REF);
	final public static FunctionParameter param_error_power        = new FunctionParameter("power", MdlDataType.TYPE_REF);
	final public static FunctionParameter param_error_f            = new FunctionParameter("f", MdlDataType.TYPE_REF);

	final public static List<String> errorModels = Arrays.asList(
		funct_error_additive, funct_error_prop, funct_error_combined1, funct_error_combined2, funct_error_combined2log, funct_error_combined3, 
		funct_error_power, funct_error_combinedPower1);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*LIBRARY*/
	final public static String lib_nmadvan = "nmadvan";
	final public static String lib_PK      = "PK";

	final public static List<String> libraries = Arrays.asList(lib_nmadvan, lib_PK);
	
	/*nmadvan parameters*/
	final public static FunctionParameter param_nmadvan_model            = new FunctionParameter("model", MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_trans            = new FunctionParameter("trans", MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_ncmt             = new FunctionParameter("ncmt", MdlDataType.TYPE_NAT, ParameterPassingMethod.IN); //number of compartments
	final public static FunctionParameter param_nmadvan_input            = new FunctionParameter("input", MdlDataType.TYPE_LIST, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_distribution     = new FunctionParameter("distribution", MdlDataType.TYPE_NAT, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_elimination      = new FunctionParameter("elimination", MdlDataType.TYPE_STRING, ParameterPassingMethod.IN);
	final public static FunctionParameter param_nmadvan_parameterization = new FunctionParameter("parameterization", MdlDataType.TYPE_STRING, ParameterPassingMethod.IN);
	
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
			put(funct_pnorm, new FunctionSignature(funct_pnorm, 1, MdlDataType.TYPE_REAL));
			put(funct_runif, new FunctionSignature(funct_runif, new FunctionParameterSet(param_n), MdlDataType.TYPE_REAL));
			
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
			//nmadvan
			Variable A = new Variable("A", MdlDataType.TYPE_VECTOR_REAL); 
			Variable F = new Variable("F", MdlDataType.TYPE_REAL);
			Map<String, List<Variable>> nmadvanReturnedValues = new HashMap<String, List<Variable>>();
			nmadvanReturnedValues.put("",  Arrays.asList(A, F));
			
			put(lib_nmadvan, new FunctionSignature(lib_nmadvan, 
					new FunctionParameterSet(Arrays.asList(
					param_nmadvan_model, param_nmadvan_trans, param_nmadvan_ncmt, param_nmadvan_input, 
					param_nmadvan_distribution, param_nmadvan_elimination, param_nmadvan_parameterization)), 
				MdlDataType.TYPE_VECTOR_REF, true, nmadvanReturnedValues)
			);			
			
			//PK
			Variable V     = new Variable("V",     MdlDataType.TYPE_REAL); 
			Variable V1    = new Variable("V1",    MdlDataType.TYPE_REAL); 
			Variable V2    = new Variable("V2",    MdlDataType.TYPE_REAL); 
			Variable V3    = new Variable("V3",    MdlDataType.TYPE_REAL); 
			Variable V4    = new Variable("V4",    MdlDataType.TYPE_REAL); 
			Variable VSS   = new Variable("VSS",   MdlDataType.TYPE_REAL); 
			Variable CL    = new Variable("CL",    MdlDataType.TYPE_REAL);
			Variable K     = new Variable("K",     MdlDataType.TYPE_REAL);
			Variable K12   = new Variable("K12",   MdlDataType.TYPE_REAL);
			Variable K21   = new Variable("K21",   MdlDataType.TYPE_REAL);
			Variable K13   = new Variable("K13",   MdlDataType.TYPE_REAL);
			Variable K31   = new Variable("K31",   MdlDataType.TYPE_REAL);
			Variable K23   = new Variable("K23",   MdlDataType.TYPE_REAL);
			Variable K32   = new Variable("K32",   MdlDataType.TYPE_REAL);
			Variable K24   = new Variable("K24",   MdlDataType.TYPE_REAL);
			Variable K42   = new Variable("K42",   MdlDataType.TYPE_REAL);
			Variable KA    = new Variable("KA",    MdlDataType.TYPE_REAL);
			Variable Q     = new Variable("Q",     MdlDataType.TYPE_REAL);
			Variable Q2    = new Variable("Q2",    MdlDataType.TYPE_REAL);
			Variable Q3    = new Variable("Q3",    MdlDataType.TYPE_REAL);
			Variable Q4    = new Variable("Q4",    MdlDataType.TYPE_REAL);
			Variable VM    = new Variable("VM",    MdlDataType.TYPE_REAL);
			Variable KM    = new Variable("KM",    MdlDataType.TYPE_REAL);
			Variable ALPHA = new Variable("ALPHA", MdlDataType.TYPE_REAL); 
			Variable BETA  = new Variable("BETA",  MdlDataType.TYPE_REAL); 
			Variable GAMMA = new Variable("GAMMA", MdlDataType.TYPE_REAL); 
			
			Map<String, List<Variable>> pkReturnedValues = new HashMap<String, List<Variable>>();
			/*Key: values of attributes in the following order: ndist|depot|par|glm|glmr|ncomp|mom1
			 * Boolean attributes which are not 'true' are skipped */
			//v_cl
			pkReturnedValues.put("ndist=1|par=v_cl", Arrays.asList(A, V, CL));
			pkReturnedValues.put("ndist=1|depot=true|par=v_cl", Arrays.asList(A, V, CL, KA));
			pkReturnedValues.put("ndist=2|par=v_cl", Arrays.asList(A, CL, V1, Q, V2));
			pkReturnedValues.put("ndist=2|depot=true|par=v_cl", Arrays.asList(A, CL, V2, Q, KA));
			pkReturnedValues.put("ndist=3|par=v_cl", Arrays.asList(A, CL,V1,Q2,V2,Q3,V3));
			pkReturnedValues.put("ndist=3|depot=true|par=v_cl", Arrays.asList(A, CL, V2, Q3, V3, Q4, V4, KA));
			pkReturnedValues.put("par=v_cl|mom1=true", Arrays.asList(A, V, VM, KM));
			//v_k
			pkReturnedValues.put("ndist=1|par=v_k", Arrays.asList(A, V, K));
			pkReturnedValues.put("ndist=1|depot=true|par=v_k", Arrays.asList(A, V, K, KA));
			pkReturnedValues.put("ndist=2|par=v_k", Arrays.asList(A, V, K, K12, K21));
			pkReturnedValues.put("ndist=2|depot=true|par=v_k", Arrays.asList(A, V, K, K23, K32, KA));
			pkReturnedValues.put("ndist=3|par=v_k", Arrays.asList(A, V, K, K12, K21, K13, K31));
			pkReturnedValues.put("ndist=3|depot=true|par=v_k", Arrays.asList(A, V,K,K23,K32,K24,K42,KA));
			pkReturnedValues.put("par=v_k|glm=true", Arrays.asList(A, V, K));
			pkReturnedValues.put("par=v_k|glmr=true", Arrays.asList(A, V, K));
			//vss_cl
			pkReturnedValues.put("ndist=2|par=vss_cl", Arrays.asList(A, CL, V, Q, VSS));
			pkReturnedValues.put("ndist=2|depot=true|par=vss_cl", Arrays.asList(A, CL, V, Q, VSS, KA));
			//abk
			pkReturnedValues.put("ndist=2|par=a_b", Arrays.asList(A, ALPHA, BETA, K21));
			pkReturnedValues.put("ndist=2|depot=true|par=a_b", Arrays.asList(A, ALPHA, BETA, K32, KA));
			pkReturnedValues.put("ndist=3|par=a_b", Arrays.asList(A, ALPHA, BETA, GAMMA, K21, K31));
			pkReturnedValues.put("ndist=3|depot=true|par=a_b", Arrays.asList(A, ALPHA, BETA, GAMMA, K32, K42, KA));
			
			put(lib_PK, new FunctionSignature(lib_PK, new FunctionParameterSet(Arrays.asList(
				param_PK_ndist, param_PK_depot, param_PK_par, 
				param_PK_glm, param_PK_glmr, param_PK_ncomp, param_PK_mom1)), 
				MdlDataType.TYPE_VECTOR_REF, true, pkReturnedValues)
			);					
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
			warning(MSG_FUNCTION_UNKNOWN, 
					MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER,
			MSG_FUNCTION_UNKNOWN, call.getIdentifier().getName());
		else //standard function
			validateStandardFunction(call);
	}
	
	public void validateStandardFunction(FunctionCall call){
		FunctionSignature functSig = standardFunctions.get(call.getIdentifier().getName());
		//TODO validate whether the function returns any value to enable/disable its use in expressions
		//TODO instead of checking whether a parameter is known, match a list of actual parameters with one of valid sets!
		if (isPassedByName(call.getArguments())){
			if (functSig.isPassingByName()){
				if (call.getArguments() != null){
					for (Argument arg: call.getArguments().getArguments()){
						if (arg.getArgumentName() != null){
							Map<String, FunctionParameter> allParams = functSig.getAllParams(); 
							if (allParams.containsKey(arg.getArgumentName().getName())){
								FunctionParameter p = allParams.get(arg.getArgumentName().getName());
								if (!MdlDataType.validateType(p.getType(), arg.getExpression()))
									warning(MSG_FUNCTION_WRONG_TYPE + ": parameter " + arg.getArgumentName().getName()
									+ " expects value of type " + p.getType(),
									MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS,
									MSG_FUNCTION_WRONG_TYPE, arg.getArgumentName().getName());		
							} else 
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
			if (isPassedByPlace(call.getArguments())) {
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
					List<FunctionParameter> defaultParams = functSig.getDefaultParamSet();
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
	
	public boolean isPassedByName(Arguments args){
		if (args != null){
			int nNames = 0; 
			for (Argument arg: args.getArguments()){
				if (arg.getArgumentName() != null) nNames++;
			}
			int count = args.getArguments().size();
			return (nNames == count && count != 0);
		}
		return false;
	}
	
	public boolean isPassedByPlace(Arguments args){
		if (args != null){
			int nNames = 0; 
			for (Argument arg: args.getArguments()){
				if (arg.getArgumentName() != null) nNames++;
			}
			return (nNames == 0);
		}
		return true;
	}
}
