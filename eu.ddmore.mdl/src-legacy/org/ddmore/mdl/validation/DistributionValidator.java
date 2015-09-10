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
import java.util.Map;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.impl.RandomListImpl;
import org.ddmore.mdl.types.DistributionType;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

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

	public final static Attribute attr_weight = new Attribute("weight", MdlDataType.TYPE_REAL, false);	
	public final static Attribute attr_seed = new Attribute("seed", MdlDataType.TYPE_REAL, false);	
	
	final public static Map<DistributionType, List<Attribute>> distr_attrs = 
			new HashMap<DistributionType, List<Attribute>>() {
				private static final long serialVersionUID = 27681295286815005L;
		{
			put(DistributionType.Bernoulli, Arrays.asList( 
					attr_probability /*,
					attr_p*/));
//			put(DistributionType.BetaDistribution, Arrays.asList(
//					attr_alpha,
//					attr_beta, 
//					attr_continuous_lo,
//					attr_continuous_hi));
			put(DistributionType.Binomial, Arrays.asList(
					attr_numberOfTrials/*,
					attr_nTrials,
					attr_n*/,
					attr_probabilityOfSuccess/*, 
					attr_p_ofSuccess*/,
					attr_nat_lo,
					attr_nat_hi));
//			put(DistributionType.Categorical, Arrays.asList(
//					attr_ncat,
//					attr_categories,
//					attr_probabilities,
//					attr_prob));
//			put(DistributionType.Cauchy, Arrays.asList(
//					attr_location,
//					attr_scale, 
//					attr_continuous_lo,
//					attr_continuous_hi));
//			put(DistributionType.ChiSquare, Arrays.asList(
//					attr_pnat_degreesOfFreedom,
//					attr_pnat_dof, 
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.Dirichlet, Arrays.asList(attr_prealVector_alpha));
//			put(DistributionType.Exponential, Arrays.asList(attr_lambda,
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.F, Arrays.asList(
//					attr_denominator,
//					attr_den,
//					attr_numerator, 
//					attr_num,
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.Gamma, Arrays.asList(
//					attr_shape,
//					attr_scale, 
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.Geometric, Arrays.asList(
//					attr_probability, 
//					attr_p,
//					attr_nat_lo,
//					attr_nat_hi));
//			put(DistributionType.Hypergeometric, Arrays.asList(
//					attr_numberOfSuccesses,
//					attr_nSuccess,
//					attr_numberOfTrials, 
//					attr_nTrials,
//					attr_n,
//					attr_populationSize,
//					attr_popSize,
//					attr_nat_lo,
//					attr_nat_hi));
//			put(DistributionType.InverseGamma, Arrays.asList(
//					attr_shape,
//					attr_scale, 
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.Laplace, Arrays.asList(
//					attr_location,
//					attr_scale, 
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.Logistic, Arrays.asList(
//					attr_location,
//					attr_scale, 
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.LogNormal, Arrays.asList(
//					attr_median,
//					attr_cv, 
//					attr_var,
//					attr_mu,
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.Multinomial, Arrays.asList(
//					attr_numberOfTrials,
//					attr_nTrials,
//					attr_probabilities,
//					attr_prob)); 
//			put(DistributionType.MultivariateNormal, Arrays.asList(
//					attr_realVector_mean,
//					attr_cov)); 
//			put(DistributionType.MultivariateStudentT, Arrays.asList(
//					attr_realVector_mean,
//					attr_cov,
//					attr_pnat_degreesOfFreedom,
//					attr_pnat_dof)); 
//			put(DistributionType.NegativeBinomial, Arrays.asList(
//					attr_numberOfFailures,
//					attr_nFail,
//					attr_probability, 
//					attr_p,
//					attr_nat_lo,
//					attr_nat_hi));
			put(DistributionType.Normal, Arrays.asList(
					attr_mean,
					attr_sd, 
					attr_var, 
					attr_continuous_lo,
					attr_continuous_hi));
//			put(DistributionType.NormalInverseGamma, Arrays.asList(
//					attr_mean,
//					attr_varianceScaling, 
//					attr_shape, 
//					attr_scale,
//					attr_loN, 
//					attr_hiN, 
//					attr_loIG, 
//					attr_hiIG));
//			put(DistributionType.Pareto, Arrays.asList(
//					attr_scale,
//					attr_shape, 
//					attr_continuous_lo, 
//					attr_continuous_hi));
			put(DistributionType.Poisson, Arrays.asList(
					attr_lambda,
					attr_nat_lo,
					attr_nat_hi));
//			put(DistributionType.StudentT, Arrays.asList(
//					attr_location,
//					attr_scale, 
//					attr_pnat_degreesOfFreedom,
//					attr_pnat_dof,
//					attr_continuous_lo,
//					attr_continuous_hi));
//			put(DistributionType.Uniform, Arrays.asList(
//					attr_min,
//					attr_max,
//					attr_numberOfClasses));
//			put(DistributionType.Unif, Arrays.asList(
//					attr_min,
//					attr_max,
//					attr_numberOfClasses));
//			put(DistributionType.Weibull, Arrays.asList(
//					attr_lambda,
//					attr_kappa, 
//					attr_preal_lo,
//					attr_preal_hi));
//			put(DistributionType.Wishart, Arrays.asList(
//					attr_n,
//					attr_scaleMatrix)); 
		}
	};

	final static List<Attribute> common_attrs = Arrays.asList(attr_weight, attr_seed);
	
	//List of synonyms or alternatives
	Map<String, String> alternative_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = 28966755954108955L; {
//			put(DistributionType.Bernoulli + ":" +attr_probability.getName(), attr_p.getName());
//			put(DistributionType.Bernoulli + ":" +attr_p.getName(), attr_probability.getName());
//			
//			put(DistributionType.Binomial + ":"+attr_numberOfTrials.getName(), attr_nTrials.getName());
//			put(DistributionType.Binomial + ":"+attr_nTrials.getName(), attr_numberOfTrials.getName());
//			put(DistributionType.Binomial + ":"+attr_numberOfTrials.getName(), attr_n.getName());
//			put(DistributionType.Binomial + ":"+attr_nTrials.getName(), attr_n.getName());
//			put(DistributionType.Binomial + ":"+attr_n.getName(), attr_nTrials.getName());
//			put(DistributionType.Binomial + ":"+attr_n.getName(), attr_numberOfTrials.getName());
//			put(DistributionType.Binomial + ":"+attr_probabilityOfSuccess.getName(), attr_p_ofSuccess.getName());
//			put(DistributionType.Binomial + ":"+attr_p_ofSuccess.getName(), attr_probabilityOfSuccess.getName());
//
//			put(DistributionType.Categorical +":"+attr_ncat.getName(), attr_categories.getName());
//			put(DistributionType.Categorical +":"+attr_categories.getName(), attr_ncat.getName());
//			put(DistributionType.Categorical +":"+attr_probabilities.getName(), attr_prob.getName());
//			put(DistributionType.Categorical +":"+attr_prob.getName(), attr_probabilities.getName());
//			
//			put(DistributionType.ChiSquare + ":" + attr_pnat_degreesOfFreedom.getName(), attr_pnat_dof.getName());
//			put(DistributionType.ChiSquare + ":" + attr_pnat_dof.getName(), attr_pnat_degreesOfFreedom.getName()); 
//
//			put(DistributionType.F + ":" + attr_denominator.getName(), attr_den.getName());
//			put(DistributionType.F + ":" + attr_den.getName(), attr_denominator.getName()); 
//			put(DistributionType.F + ":" + attr_numerator.getName(), attr_num.getName());
//			put(DistributionType.F + ":" + attr_num.getName(), attr_numerator.getName()); 
//			
//			put(DistributionType.Geometric +":"+attr_probability.getName(), attr_p.getName());
//			put(DistributionType.Geometric +":"+attr_p.getName(), attr_probability.getName());
//
//			put(DistributionType.Hypergeometric + ":" + attr_numberOfTrials.getName(), attr_nTrials.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_nTrials.getName(), attr_numberOfTrials.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_n.getName(), attr_nTrials.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_n.getName(), attr_numberOfTrials.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_numberOfTrials.getName(), attr_n.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_nTrials.getName(), attr_n.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_populationSize.getName(), attr_popSize.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_popSize.getName(), attr_populationSize.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_numberOfSuccesses.getName(), attr_nSuccess.getName());
//			put(DistributionType.Hypergeometric + ":" + attr_nSuccess.getName(), attr_numberOfSuccesses.getName());
//
//			put(DistributionType.LogNormal + ":" + attr_median.getName(), attr_mu.getName());
//			put(DistributionType.LogNormal + ":" + attr_mu.getName(), attr_median.getName());
//			put(DistributionType.LogNormal + ":" + attr_cv.getName(), attr_var.getName());
//			put(DistributionType.LogNormal + ":" + attr_var.getName(), attr_cv.getName());
//
//			put(DistributionType.Multinomial + ":" + attr_numberOfTrials.getName(), attr_nTrials.getName());
//			put(DistributionType.Multinomial + ":" + attr_nTrials.getName(), attr_numberOfTrials.getName());
//			put(DistributionType.Multinomial + ":" + attr_probabilities.getName(), attr_prob.getName());
//			put(DistributionType.Multinomial + ":" + attr_prob.getName(), attr_probabilities.getName());
//			
//			put(DistributionType.MultivariateStudentT + ":" + attr_pnat_degreesOfFreedom.getName(), attr_pnat_dof.getName());
//			put(DistributionType.MultivariateStudentT + ":" + attr_pnat_dof.getName(), attr_pnat_degreesOfFreedom.getName()); 
//
//			put(DistributionType.NegativeBinomial +":"+attr_numberOfFailures.getName(), attr_nFail.getName());
//			put(DistributionType.NegativeBinomial +":"+attr_nFail.getName(), attr_numberOfFailures.getName());
//			put(DistributionType.NegativeBinomial +":"+attr_probability.getName(), attr_p.getName());
//			put(DistributionType.NegativeBinomial +":"+attr_p.getName(), attr_probability.getName());					
//
//			put(DistributionType.StudentT + ":" + attr_pnat_degreesOfFreedom.getName(), attr_pnat_dof.getName());
//			put(DistributionType.StudentT + ":" + attr_pnat_dof.getName(), attr_pnat_degreesOfFreedom.getName()); 
//
			put(DistributionType.Normal + ":" + attr_var.getName(), attr_sd.getName()); 
			put(DistributionType.Normal + ":" + attr_sd.getName(), attr_var.getName());
//			
//			put(DistributionType.Uniform + ":" + attr_numberOfClasses.getName(), attr_min.getName()); 
//			put(DistributionType.Uniform + ":" + attr_min.getName(), attr_numberOfClasses.getName()); 
//			put(DistributionType.Uniform + ":" +attr_max.getName(), attr_numberOfClasses.getName());
//			
//			put(DistributionType.Unif + ":" + attr_numberOfClasses.getName(), attr_min.getName()); 
//			put(DistributionType.Unif + ":" + attr_min.getName(), attr_numberOfClasses.getName()); 
//			put(DistributionType.Unif + ":" +attr_max.getName(), attr_numberOfClasses.getName());
		}
	};

	//List of synonyms or alternatives
	Map<String, String> exclusive_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = -4192464782401647313L;
		{
			put(attr_var.getName(), attr_sd.getName()); 
			put(attr_sd.getName(), attr_var.getName());

			put(attr_var.getName(), attr_cv.getName()); 
			put(attr_cv.getName(), attr_var.getName());

			put(attr_numberOfClasses.getName(), attr_min.getName()); 
			put(attr_min.getName(), attr_numberOfClasses.getName()); 
			put(attr_max.getName(), attr_numberOfClasses.getName());
		}
	};

	@Check
	public void checkRequiredArguments(RandomList distr){
		if (distr.getType() != null){
			if (Utils.isInEnum(distr.getType().getName(), DistributionType.class)){
				DistributionType type = Utils.getEnum(distr.getType().getName(), DistributionType.class);
				List<Attribute> recognized_attrs = distr_attrs.get(type);
				if (recognized_attrs != null){
					for (Attribute arg: recognized_attrs){
						if (arg.isMandatory()){
							AnyExpression actualArg = null;
							if (distr.getArguments() != null){
								actualArg = MdlPrinter.getInstance().getAttributeExpression(distr.getArguments(), arg.getName());
								if (actualArg == null){
									String synonym = alternative_attrs.get(type + ":" + arg.getName());
									if (synonym != null) actualArg =  MdlPrinter.getInstance().getAttributeExpression(distr.getArguments(), synonym);
								}
							}
							if (actualArg == null){
								error(MSG_DISTR_ATTRIBUTE_MISSING + ": " + arg.getName(), 
										MdlPackage.Literals.RANDOM_LIST__ARGUMENTS,
										MSG_DISTR_ATTRIBUTE_MISSING, type + ":" + arg.getName());	
							}
						}
					}
				}
			} else {
				error(MSG_DISTR_UNKNOWN, 
					MdlPackage.Literals.RANDOM_LIST__TYPE,
					MSG_DISTR_UNKNOWN, distr.getType().getName());	
			}
		}
	}
	
	@Check
	public void checkAllArguments(Argument argument){
		EObject namedArgContainer = argument.eContainer();	
		EObject argContainer = namedArgContainer.eContainer();	
		EObject blockContainer = argContainer.eContainer().eContainer();
		if (!(blockContainer instanceof RandomListImpl)) return; 
		
		Arguments args = (Arguments)argContainer;
		RandomList distr = (RandomList)argContainer.eContainer();
		HashSet<String> argumentNames = new HashSet<String>();	
		for (Argument arg: args.getNamedArguments().getArguments()){
			if (!argumentNames.contains(arg.getArgumentName().getArgName())){
				argumentNames.add(arg.getArgumentName().getArgName());
			} else {
				error(MSG_DISTR_ATTRIBUTE_DEFINED + ": " + arg.getArgumentName().getArgName(), 
						MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME, 
						MSG_DISTR_ATTRIBUTE_DEFINED, arg.getArgumentName().getArgName());				
			}
		}
		if (exclusive_attrs.containsKey(argument.getArgumentName().getArgName())){
			String exclusive = exclusive_attrs.get(argument.getArgumentName().getArgName());
			if (argumentNames.contains(exclusive)){
				error("Distribution attribute '" + argument.getArgumentName().getArgName() + "' cannot be used together with '" + 
						exclusive + "'", 
						MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME, MSG_DISTR_ATTRIBUTE_DEFINED, 
						argument.getArgumentName().getArgName());				
			}
		}
		if (distr.getType() == null) return;
		DistributionType type = Utils.getEnum(distr.getType().getName(), DistributionType.class);
		if (type != null){
			if (distr_attrs.containsKey(type)){
				List<Attribute> recognized_attrs = distr_attrs.get(type);
				if (checkAttribute(recognized_attrs, argument)) return;
			}
			if (checkAttribute(common_attrs, argument)) return;
			error(MSG_DISTR_ATTRIBUTE_UNKNOWN + ": " + argument.getArgumentName().getArgName(), 
				MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
				MSG_DISTR_ATTRIBUTE_UNKNOWN, argument.getArgumentName().getArgName());
		}
	}
	
	private Boolean checkAttribute(List<Attribute> recognized_attrs, Argument argument){
		for (Attribute attr: recognized_attrs){
			if (attr.getName().equals(argument.getArgumentName().getArgName())) {
				if (!MdlDataType.validateType(attr.getType(), argument.getExpression()) 
						&& !MdlDataType.validateType(MdlDataType.TYPE_REF, argument.getExpression())){
					error(MSG_DISTR_ATTRIBUTE_WRONG_TYPE + 
							": attribute \"" + argument.getArgumentName().getArgName() + "\" expects value of type " + 
						attr.getType().name(), 
						MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
						MSG_DISTR_ATTRIBUTE_WRONG_TYPE, argument.getArgumentName().getArgName());
				}					
				return true; //found in the list
			}
		}
		return false;
	}
	
	public static List<Attribute> getAttributes(String type){
		for (DistributionType dType: distr_attrs.keySet()){
			if (dType.toString().equals(type))
				return distr_attrs.get(dType);
		}
		return null;
	}
}
