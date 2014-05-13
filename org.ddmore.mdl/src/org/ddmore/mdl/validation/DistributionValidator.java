/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks distribution definitions
 */
package org.ddmore.mdl.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.ddmore.mdl.mdl.DistributionArgument;
import org.ddmore.mdl.mdl.DistributionArguments;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.impl.DistributionArgumentsImpl;
import org.ddmore.mdl.types.MdlDataType;
import org.ddmore.mdl.types.DistributionType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

public class DistributionValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}

	public final static String MSG_DISTR_UNKNOWN = "Failed to recognize distribution type";
	public final static String MSG_DISTR_ATTRIBUTE_UNKNOWN = "Unknown distribution attribute";
	public final static String MSG_DISTR_ATTRIBUTE_MISSING = "Required distribution attribute is missing";
	public final static String MSG_DISTR_ATTRIBUTE_DEFINED    = "Distribution attribute or its synonym is already defined";
	public final static String MSG_DISTR_ATTRIBUTE_WRONG_TYPE = "Type error";

	public final static Attribute attr_probability = new Attribute("probability", MdlDataType.TYPE_PROBABILITY, true);
	public final static Attribute attr_p = new Attribute("probability", MdlDataType.TYPE_PROBABILITY, true);
	
	public final static Attribute attr_probabilityOfSuccess = new Attribute("probabilityOfSuccess", MdlDataType.TYPE_PROBABILITY, true);
	public final static Attribute attr_p_ofSuccess = new Attribute("p", MdlDataType.TYPE_PROBABILITY, true);
	public final static Attribute attr_probabilities = new Attribute("probabilities", MdlDataType.TYPE_VECTOR_PROBABILITY, true);
	public final static Attribute attr_prob = new Attribute("prob", MdlDataType.TYPE_VECTOR_PROBABILITY, true);
	
	public final static Attribute attr_alpha = new Attribute("alpha", MdlDataType.TYPE_REAL, true);
	public final static Attribute attr_beta = new Attribute("beta", MdlDataType.TYPE_REAL, true);
	
	public final static Attribute attr_continuous_lo = new Attribute("lo", MdlDataType.TYPE_PROBABILITY, false);
	public final static Attribute attr_continuous_hi = new Attribute("hi", MdlDataType.TYPE_PROBABILITY, false);
	public final static Attribute attr_nat_lo = new Attribute("lo", MdlDataType.TYPE_NAT, false);
	public final static Attribute attr_nat_hi = new Attribute("hi", MdlDataType.TYPE_NAT, false);
	public final static Attribute attr_preal_lo = new Attribute("lo", MdlDataType.TYPE_PREAL, false);
	public final static Attribute attr_preal_hi = new Attribute("hi", MdlDataType.TYPE_PREAL, false);
	public final static Attribute attr_loN = new Attribute("loN", MdlDataType.TYPE_NAT, false);
	public final static Attribute attr_hiN = new Attribute("hiN", MdlDataType.TYPE_NAT, false);
	public final static Attribute attr_loIG = new Attribute("loIG", MdlDataType.TYPE_PREAL, false);
	public final static Attribute attr_hiIG = new Attribute("hiIG", MdlDataType.TYPE_REAL, false);

	public final static Attribute attr_categories = new Attribute("categories", MdlDataType.TYPE_VECTOR_NAT, true);
	public final static Attribute attr_ncategories = new Attribute("ncategories", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_ncat = new Attribute("ncat", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_numberOfTrials = new Attribute("numberOfTrials", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_nTrials = new Attribute("nTrials", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_numberOfSuccesses = new Attribute("numberOfSuccesses", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_nSuccess = new Attribute("nSuccess", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_numberOfFailures = new Attribute("numberOfFailures", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_nFail= new Attribute("nFail", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_populationSize = new Attribute("populationSize", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_popSize = new Attribute("popSize", MdlDataType.TYPE_NAT, true);
	
	public final static Attribute attr_pnat_degreesOfFreedom = new Attribute("degreesOfFreedom", MdlDataType.TYPE_PNAT, true);
	public final static Attribute attr_pnat_dof = new Attribute("dof", MdlDataType.TYPE_PNAT, true);
	public final static Attribute attr_n = new Attribute("n", MdlDataType.TYPE_PREAL, true);

	public final static Attribute attr_prealVector_alpha = new Attribute("alpha", MdlDataType.TYPE_VECTOR_PREAL, true);
	//public final static Attribute attr_concentration = new Attribute("concentration", MdlDataType.TYPE_VECTOR_PREAL, true);
	public final static Attribute attr_lambda = new Attribute("lambda", MdlDataType.TYPE_PREAL, true);
	public final static Attribute attr_kappa = new Attribute("kappa", MdlDataType.TYPE_PREAL, true);

	public final static Attribute attr_denominator = new Attribute("denominator", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_numerator = new Attribute("numerator", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_den = new Attribute("den", MdlDataType.TYPE_NAT, true);
	public final static Attribute attr_num = new Attribute("num", MdlDataType.TYPE_NAT, true);
	
	public final static Attribute attr_location = new Attribute("location", MdlDataType.TYPE_REAL, true);
	public final static Attribute attr_scale = new Attribute("scale", MdlDataType.TYPE_PREAL, true);
	public final static Attribute attr_shape = new Attribute("shape", MdlDataType.TYPE_PREAL, true);
	public final static Attribute attr_cv = new Attribute("cv", MdlDataType.TYPE_PREAL, true);
	public final static Attribute attr_sigmatr = new Attribute("sigmatr", MdlDataType.TYPE_PREAL, true);
	public final static Attribute attr_median = new Attribute("median", MdlDataType.TYPE_PREAL, true);
	public final static Attribute attr_mu = new Attribute("mu", MdlDataType.TYPE_PREAL, true);
	
	public final static Attribute attr_realVector_mean = new Attribute("mean", MdlDataType.TYPE_VECTOR_REAL, true);
	public final static Attribute attr_cov = new Attribute("cov", MdlDataType.TYPE_VECTOR_REAL, true);

	public final static Attribute attr_mean = new Attribute("mean", MdlDataType.TYPE_REAL, true, "0");
	public final static Attribute attr_var = new Attribute("var", MdlDataType.TYPE_PREAL, true, "0");
	public final static Attribute attr_sd = new Attribute("sd", MdlDataType.TYPE_PREAL, true);

	public final static Attribute attr_varianceScaling = new Attribute("varianceScaling", MdlDataType.TYPE_PREAL, true);

	public final static Attribute attr_min = new Attribute("min", MdlDataType.TYPE_REAL, true);
	public final static Attribute attr_max = new Attribute("max", MdlDataType.TYPE_REAL, true);
	public final static Attribute attr_numberOfClasses = new Attribute("numberOfClasses", MdlDataType.TYPE_NAT, true);

	public final static Attribute attr_scaleMatrix = new Attribute("scaleMatrix", MdlDataType.TYPE_VECTOR_PREAL, true);
	public final static Attribute attr_dimension = new Attribute("dimension", MdlDataType.TYPE_NAT, false);
	
	public final static Attribute attr_type = new Attribute("type", MdlDataType.TYPE_DISTRIBUTION, true, "Normal");
	public final static Attribute attr_level = new Attribute("level", MdlDataType.TYPE_REF, false, "ID");
	public final static Attribute attr_weight = new Attribute("weight", MdlDataType.TYPE_REAL, false);	
	public final static Attribute attr_seed = new Attribute("seed", MdlDataType.TYPE_REAL, false);	
	
	final public static HashMap<String, List<Attribute>> distr_attrs = 
			new HashMap<String, List<Attribute>>() {
				private static final long serialVersionUID = 27681295286815005L;
		{
			put(DistributionType.bernoulli, Arrays.asList(
					attr_probability,
					attr_p));
			put(DistributionType.beta, Arrays.asList(
					attr_alpha,
					attr_beta, 
					attr_continuous_lo,
					attr_continuous_hi));
			put(DistributionType.binomial, Arrays.asList(
					attr_numberOfTrials,
					attr_nTrials,
					attr_n,
					attr_probabilityOfSuccess, 
					attr_p_ofSuccess,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.categorical, Arrays.asList(
					attr_ncategories,
					attr_ncat,
					attr_categories,
					attr_probabilities,
					attr_prob));
			put(DistributionType.cauchy, Arrays.asList(
					attr_location,
					attr_scale, 
					attr_continuous_lo,
					attr_continuous_hi));
			put(DistributionType.chiSquare, Arrays.asList(
					attr_pnat_degreesOfFreedom,
					attr_pnat_dof, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.dirichlet, Arrays.asList(attr_prealVector_alpha));
			put(DistributionType.exponential, Arrays.asList(attr_lambda,
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.fDistribution, Arrays.asList(
					attr_denominator,
					attr_den,
					attr_numerator, 
					attr_num,
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.gamma, Arrays.asList(
					attr_shape,
					attr_scale, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.geometric, Arrays.asList(
					attr_probability, 
					attr_p,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.hypergeometric, Arrays.asList(
					attr_numberOfSuccesses,
					attr_nSuccess,
					attr_numberOfTrials, 
					attr_nTrials,
					attr_n,
					attr_populationSize,
					attr_popSize,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.inverseGamma, Arrays.asList(
					attr_shape,
					attr_scale, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.laplace, Arrays.asList(
					attr_location,
					attr_scale, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.logistic, Arrays.asList(
					attr_location,
					attr_scale, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.logNormal, Arrays.asList(
					attr_median,
					attr_cv, 
					attr_var,
					attr_mu,
					attr_sigmatr,
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.multinomial, Arrays.asList(
					attr_numberOfTrials,
					attr_nTrials,
					attr_probabilities,
					attr_prob)); 
			put(DistributionType.multivariateNormal, Arrays.asList(
					attr_realVector_mean,
					attr_cov,
					//attr_corr,      //does not exist in PharmML
					//attr_vector_sd, //does not exist in PharmML
					attr_dimension)); 
			put(DistributionType.multivariateStudentT, Arrays.asList(
					//attr_meanVector,
					//attr_covarianceMatrix,
					attr_realVector_mean,
					attr_cov,
					attr_pnat_degreesOfFreedom,
					attr_pnat_dof,
					attr_dimension)); 
			put(DistributionType.negativeBinomial, Arrays.asList(
					attr_numberOfFailures,
					attr_nFail,
					attr_probability, 
					attr_p,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.normal, Arrays.asList(
					attr_mean,
					attr_sd, 
					attr_var, 
					attr_continuous_lo,
					attr_continuous_hi));
			put(DistributionType.normalInverseGamma, Arrays.asList(
					attr_mean,
					attr_varianceScaling, 
					attr_shape, 
					attr_scale,
					attr_loN, 
					attr_hiN, 
					attr_loIG, 
					attr_hiIG));
			put(DistributionType.pareto, Arrays.asList(
					attr_scale,
					attr_shape, 
					attr_continuous_lo, 
					attr_continuous_hi));
			put(DistributionType.poisson, Arrays.asList(
					attr_lambda,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.studentT, Arrays.asList(
					attr_location,
					attr_scale, 
					attr_pnat_degreesOfFreedom,
					attr_pnat_dof,
					attr_continuous_lo,
					attr_continuous_hi));
			put(DistributionType.uniform, Arrays.asList(
					attr_min,
					attr_max,
					attr_numberOfClasses));
			put(DistributionType.unif, Arrays.asList(
					attr_min,
					attr_max,
					attr_numberOfClasses));
			put(DistributionType.weibull, Arrays.asList(
					attr_lambda,
					attr_kappa, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.wishart, Arrays.asList(
					attr_n,
					attr_scaleMatrix,
					attr_dimension)); 
		}
	};

	final static List<Attribute> common_attrs = Arrays.asList(attr_type, attr_level,attr_weight, attr_seed);
	
	//List of synonyms or alternatives
	HashMap<String, String> alternative_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = 28966755954108955L; {
			put(DistributionType.bernoulli+":"+attr_probability.name, attr_p.name);
			put(DistributionType.bernoulli+":"+attr_p.name, attr_probability.name);
			
			put(DistributionType.binomial+":"+attr_numberOfTrials.name, attr_nTrials.name);
			put(DistributionType.binomial+":"+attr_nTrials.name, attr_numberOfTrials.name);
			put(DistributionType.binomial+":"+attr_numberOfTrials.name, attr_n.name);
			put(DistributionType.binomial+":"+attr_nTrials.name, attr_n.name);
			put(DistributionType.binomial+":"+attr_n.name, attr_nTrials.name);
			put(DistributionType.binomial+":"+attr_n.name, attr_numberOfTrials.name);

			put(DistributionType.binomial+":"+attr_probabilityOfSuccess.name, attr_p_ofSuccess.name);
			put(DistributionType.binomial+":"+attr_p_ofSuccess.name, attr_probabilityOfSuccess.name);

			put(DistributionType.categorical+":"+attr_ncategories.name, attr_ncat.name);
			put(DistributionType.categorical+":"+attr_ncat.name, attr_ncategories.name);
			put(DistributionType.categorical+":"+attr_categories.name, attr_ncategories.name);
			put(DistributionType.categorical+":"+attr_categories.name, attr_ncat.name);
			put(DistributionType.categorical+":"+attr_ncategories.name, attr_categories.name);
			put(DistributionType.categorical+":"+attr_ncat.name, attr_categories.name);
			put(DistributionType.categorical+":"+attr_probabilities.name, attr_prob.name);
			put(DistributionType.categorical+":"+attr_prob.name, attr_probabilities.name);
			
			put(DistributionType.chiSquare + ":" + attr_pnat_degreesOfFreedom.name, attr_pnat_dof.name);
			put(DistributionType.chiSquare + ":" + attr_pnat_dof.name, attr_pnat_degreesOfFreedom.name); 

			put(DistributionType.fDistribution + ":" + attr_denominator.name, attr_den.name);
			put(DistributionType.fDistribution + ":" + attr_den.name, attr_denominator.name); 
			put(DistributionType.fDistribution + ":" + attr_numerator.name, attr_num.name);
			put(DistributionType.fDistribution + ":" + attr_num.name, attr_numerator.name); 
			
			put(DistributionType.geometric+":"+attr_probability.name, attr_p.name);
			put(DistributionType.geometric+":"+attr_p.name, attr_probability.name);

			put(DistributionType.hypergeometric+":"+attr_numberOfTrials.name, attr_nTrials.name);
			put(DistributionType.hypergeometric+":"+attr_nTrials.name, attr_numberOfTrials.name);
			put(DistributionType.hypergeometric+":"+attr_n.name, attr_nTrials.name);
			put(DistributionType.hypergeometric+":"+attr_n.name, attr_numberOfTrials.name);
			put(DistributionType.hypergeometric+":"+attr_numberOfTrials.name, attr_n.name);
			put(DistributionType.hypergeometric+":"+attr_nTrials.name, attr_n.name);
			put(DistributionType.hypergeometric+":"+attr_populationSize.name, attr_popSize.name);
			put(DistributionType.hypergeometric+":"+attr_popSize.name, attr_populationSize.name);
			put(DistributionType.hypergeometric+":"+attr_numberOfSuccesses.name, attr_nSuccess.name);
			put(DistributionType.hypergeometric+":"+attr_nSuccess.name, attr_numberOfSuccesses.name);

			put(DistributionType.logNormal+":"+attr_median.name, attr_mu.name);
			put(DistributionType.logNormal+":"+attr_mu.name, attr_median.name);
			put(DistributionType.logNormal+":"+attr_cv.name, attr_sigmatr.name);
			put(DistributionType.logNormal+":"+attr_sigmatr.name, attr_cv.name);
			put(DistributionType.logNormal+":"+attr_cv.name, attr_var.name);
			put(DistributionType.logNormal+":"+attr_var.name, attr_cv.name);
			put(DistributionType.logNormal+":"+attr_var.name, attr_sigmatr.name);
			put(DistributionType.logNormal+":"+attr_sigmatr.name, attr_var.name);

			put(DistributionType.multinomial+":"+attr_numberOfTrials.name, attr_nTrials.name);
			put(DistributionType.multinomial+":"+attr_nTrials.name, attr_numberOfTrials.name);
			put(DistributionType.multinomial+":"+attr_probabilities.name, attr_prob.name);
			put(DistributionType.multinomial+":"+attr_prob.name, attr_probabilities.name);
			
			put(DistributionType.multivariateStudentT + ":" + attr_pnat_degreesOfFreedom.name, attr_pnat_dof.name);
			put(DistributionType.multivariateStudentT + ":" + attr_pnat_dof.name, attr_pnat_degreesOfFreedom.name); 

			put(DistributionType.negativeBinomial+":"+attr_numberOfFailures.name, attr_nFail.name);
			put(DistributionType.negativeBinomial+":"+attr_nFail.name, attr_numberOfFailures.name);
			put(DistributionType.negativeBinomial+":"+attr_probability.name, attr_p.name);
			put(DistributionType.negativeBinomial+":"+attr_p.name, attr_probability.name);					

			put(DistributionType.studentT + ":" + attr_pnat_degreesOfFreedom.name, attr_pnat_dof.name);
			put(DistributionType.studentT + ":" + attr_pnat_dof.name, attr_pnat_degreesOfFreedom.name); 

			put(DistributionType.normal + ":" + attr_var.name, attr_sd.name); 
			put(DistributionType.normal + ":" + attr_sd.name, attr_var.name);
			
			put(DistributionType.uniform + ":" + attr_numberOfClasses.name, attr_min.name); 
			put(DistributionType.uniform + ":" + attr_min.name, attr_numberOfClasses.name); 
			put(DistributionType.uniform + ":" +attr_max.name, attr_numberOfClasses.name);
			
			put(DistributionType.unif + ":" + attr_numberOfClasses.name, attr_min.name); 
			put(DistributionType.unif + ":" + attr_min.name, attr_numberOfClasses.name); 
			put(DistributionType.unif + ":" +attr_max.name, attr_numberOfClasses.name);
		}
	};

	//List of synonyms or alternatives
	HashMap<String, String> exclusive_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = -4192464782401647313L;
		{
			put(attr_var.name, attr_sd.name); 
			put(attr_sd.name, attr_var.name);

			put(attr_var.name, attr_cv.name); 
			put(attr_cv.name, attr_var.name);

			put(attr_numberOfClasses.name, attr_min.name); 
			put(attr_min.name, attr_numberOfClasses.name); 
			put(attr_max.name, attr_numberOfClasses.name);
		}
	};

	public static final Attribute getAttributeById(String id){
		String[] tokens = id.split(":");
        if (tokens.length > 0){
        	List<Attribute> recognizedAttrs = distr_attrs.get(tokens[0]);
        	if (recognizedAttrs != null)
	        	for (Attribute attr: recognizedAttrs){
	        		if (attr.name.equals(tokens[1])) return attr;
	        	}
        }
		return null;
    }
	
	@Check
	public void checkRequiredArguments(DistributionArguments args){
		DistributionArgument type = findDistributionAttribute(args, attr_type.name);
		if ((type != null) && (DistributionType.DISTR_VALUES.contains(type.getDistribution().getIdentifier()))){
			String typeName = type.getDistribution().getIdentifier();
			List<Attribute> recognized_attrs = distr_attrs.get(typeName);
			for (Attribute arg: recognized_attrs){
				if (arg.mandatory){
					DistributionArgument actualArg = findDistributionAttribute(args, arg.name);
					if (actualArg == null){
						String synonym = alternative_attrs.get(type.getDistribution().getIdentifier() +":" + arg.name);
						if (synonym != null) actualArg = findDistributionAttribute(args, synonym);
						if (actualArg == null)
							warning(MSG_DISTR_ATTRIBUTE_MISSING + ": " + arg.name, 
									MdlPackage.Literals.DISTRIBUTION_ARGUMENTS__ARGUMENTS,
									MSG_DISTR_ATTRIBUTE_MISSING, typeName + ":" + arg.name);	
					}
				}
			}
		} else {
			warning(MSG_DISTR_UNKNOWN, 
				MdlPackage.Literals.DISTRIBUTION_ARGUMENTS__ARGUMENTS,
				MSG_DISTR_UNKNOWN, attr_type.name);	
		}
	}
	
	@Check
	public void checkAllArguments(DistributionArgument argument){
		EObject argContainer = argument.eContainer();	
		if (!(argContainer instanceof DistributionArgumentsImpl)) return;
		DistributionArguments args = (DistributionArguments)argContainer;
		HashSet<String> argumentNames = new HashSet<String>();	
		for (DistributionArgument arg: args.getArguments()){
			if (!argumentNames.contains(arg.getArgumentName().getName())){
				argumentNames.add(arg.getArgumentName().getName());
			} else {
				warning(MSG_DISTR_ATTRIBUTE_DEFINED + ": " + arg.getArgumentName().getName(), 
						MdlPackage.Literals.DISTRIBUTION_ARGUMENT__ARGUMENT_NAME, 
						MSG_DISTR_ATTRIBUTE_DEFINED, arg.getArgumentName().getName());				
			}
		}	
		if (exclusive_attrs.containsKey(argument.getArgumentName().getName())){
			String exclusive = exclusive_attrs.get(argument.getArgumentName().getName());
			if (argumentNames.contains(exclusive)){
				warning("Distribution attribute '" + argument.getArgumentName().getName() + "' cannot be used together with '" + 
						exclusive + "'", 
						MdlPackage.Literals.DISTRIBUTION_ARGUMENT__ARGUMENT_NAME, MSG_DISTR_ATTRIBUTE_DEFINED, 
						argument.getArgumentName().getName());				
			}
		}
		DistributionArgument type = findDistributionAttribute(args, attr_type.name);
		if (type != null){
			List<Attribute> recognized_attrs = distr_attrs.get(type.getDistribution().getIdentifier());
			if (checkAttribute(recognized_attrs, argument)) return;
		}
		if (checkAttribute(common_attrs, argument)) return;
		warning(MSG_DISTR_ATTRIBUTE_UNKNOWN + ": " + argument.getArgumentName().getName(), 
			MdlPackage.Literals.DISTRIBUTION_ARGUMENT__ARGUMENT_NAME,
			MSG_DISTR_ATTRIBUTE_UNKNOWN, argument.getArgumentName().getName());	
	}
	
	private Boolean checkAttribute(List<Attribute> recognized_attrs, DistributionArgument argument){
		for (Attribute attr: recognized_attrs){
			if (attr.name.equals(argument.getArgumentName().getName())) {
				boolean isValid = MdlDataType.validateType(attr.type, argument);
				if (!isValid){
					warning(MSG_DISTR_ATTRIBUTE_WRONG_TYPE + 
						": attribute \"" + argument.getArgumentName().getName() + "\" expects value of type " + 
							attr.type.name(), 
						MdlPackage.Literals.DISTRIBUTION_ARGUMENT__ARGUMENT_NAME,
						MSG_DISTR_ATTRIBUTE_WRONG_TYPE, argument.getArgumentName().getName());
				}
				return true; //found in the list
			}					
		}
		return false;
	}
	
	public static DistributionArgument findDistributionAttribute(DistributionArguments args, String name){
		for (DistributionArgument arg: args.getArguments()){
			if (arg.getArgumentName().getName().equals(name)){
				return arg;
			}
		}
		return null;
	}	
	
}
