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
import org.ddmore.mdl.mdl.DistributionType;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.impl.ArgumentsImpl;
import org.ddmore.mdl.mdl.impl.RandomListImpl;
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
	public final static Attribute attr_dimension = new Attribute("dimension", MdlDataType.TYPE_NAT, false);
	
	public final static Attribute attr_type = new Attribute("type", MdlDataType.TYPE_DISTRIBUTION, true, "Normal");
	public final static Attribute attr_level = new Attribute("level", MdlDataType.TYPE_REF, false, "ID");
	public final static Attribute attr_weight = new Attribute("weight", MdlDataType.TYPE_REAL, false);	
	public final static Attribute attr_seed = new Attribute("seed", MdlDataType.TYPE_REAL, false);	
	
	final public static Map<DistributionType, List<Attribute>> distr_attrs = 
			new HashMap<DistributionType, List<Attribute>>() {
				private static final long serialVersionUID = 27681295286815005L;
		{
			put(DistributionType.BERNOULLI, Arrays.asList(
					attr_probability,
					attr_p));
			put(DistributionType.BETA_DISTRIBUTION, Arrays.asList(
					attr_alpha,
					attr_beta, 
					attr_continuous_lo,
					attr_continuous_hi));
			put(DistributionType.BINOMIAL, Arrays.asList(
					attr_numberOfTrials,
					attr_nTrials,
					attr_n,
					attr_probabilityOfSuccess, 
					attr_p_ofSuccess,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.DISCRETE, Arrays.asList(
					attr_ncat,
					attr_categories,
					attr_probabilities,
					attr_prob));
			put(DistributionType.CAUCHY, Arrays.asList(
					attr_location,
					attr_scale, 
					attr_continuous_lo,
					attr_continuous_hi));
			put(DistributionType.CHI_SQUARE, Arrays.asList(
					attr_pnat_degreesOfFreedom,
					attr_pnat_dof, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.DIRICHLET, Arrays.asList(attr_prealVector_alpha));
			put(DistributionType.EXPONENTIAL, Arrays.asList(attr_lambda,
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.FDISTRIBUTION, Arrays.asList(
					attr_denominator,
					attr_den,
					attr_numerator, 
					attr_num,
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.GAMMA, Arrays.asList(
					attr_shape,
					attr_scale, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.GEOMETRIC, Arrays.asList(
					attr_probability, 
					attr_p,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.HYPERGEOMETRIC, Arrays.asList(
					attr_numberOfSuccesses,
					attr_nSuccess,
					attr_numberOfTrials, 
					attr_nTrials,
					attr_n,
					attr_populationSize,
					attr_popSize,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.INVERSE_GAMMA, Arrays.asList(
					attr_shape,
					attr_scale, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.LAPLACE, Arrays.asList(
					attr_location,
					attr_scale, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.LOGISTIC, Arrays.asList(
					attr_location,
					attr_scale, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.LOG_NORMAL, Arrays.asList(
					attr_median,
					attr_cv, 
					attr_var,
					attr_mu,
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.MULTINOMIAL, Arrays.asList(
					attr_numberOfTrials,
					attr_nTrials,
					attr_probabilities,
					attr_prob)); 
			put(DistributionType.MULTIVARIATE_NORMAL, Arrays.asList(
					attr_realVector_mean,
					attr_cov,
					//attr_corr,      //does not exist in PharmML
					//attr_vector_sd, //does not exist in PharmML
					attr_dimension)); 
			put(DistributionType.MULTIVARIATE_STUDENT_T, Arrays.asList(
					//attr_meanVector,
					//attr_covarianceMatrix,
					attr_realVector_mean,
					attr_cov,
					attr_pnat_degreesOfFreedom,
					attr_pnat_dof,
					attr_dimension)); 
			put(DistributionType.NEGATIVE_BINOMIAL, Arrays.asList(
					attr_numberOfFailures,
					attr_nFail,
					attr_probability, 
					attr_p,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.NORMAL, Arrays.asList(
					attr_mean,
					attr_sd, 
					attr_var, 
					attr_continuous_lo,
					attr_continuous_hi));
			put(DistributionType.NORMAL_INVERSE_GAMMA, Arrays.asList(
					attr_mean,
					attr_varianceScaling, 
					attr_shape, 
					attr_scale,
					attr_loN, 
					attr_hiN, 
					attr_loIG, 
					attr_hiIG));
			put(DistributionType.PARETO, Arrays.asList(
					attr_scale,
					attr_shape, 
					attr_continuous_lo, 
					attr_continuous_hi));
			put(DistributionType.POISSON, Arrays.asList(
					attr_lambda,
					attr_nat_lo,
					attr_nat_hi));
			put(DistributionType.STUDENT_T, Arrays.asList(
					attr_location,
					attr_scale, 
					attr_pnat_degreesOfFreedom,
					attr_pnat_dof,
					attr_continuous_lo,
					attr_continuous_hi));
			put(DistributionType.UNIFORM, Arrays.asList(
					attr_min,
					attr_max,
					attr_numberOfClasses));
			put(DistributionType.UNIF, Arrays.asList(
					attr_min,
					attr_max,
					attr_numberOfClasses));
			put(DistributionType.WEIBULL, Arrays.asList(
					attr_lambda,
					attr_kappa, 
					attr_preal_lo,
					attr_preal_hi));
			put(DistributionType.WISHART, Arrays.asList(
					attr_n,
					attr_scaleMatrix,
					attr_dimension)); 
		}
	};

	final static List<Attribute> common_attrs = Arrays.asList(attr_type, attr_level,attr_weight, attr_seed);
	
	//List of synonyms or alternatives
	Map<String, String> alternative_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = 28966755954108955L; {
			put(DistributionType.BERNOULLI.toString() +":"+attr_probability.getName(), attr_p.getName());
			put(DistributionType.BERNOULLI.toString() +":"+attr_p.getName(), attr_probability.getName());
			
			put(DistributionType.BINOMIAL.toString() + ":"+attr_numberOfTrials.getName(), attr_nTrials.getName());
			put(DistributionType.BINOMIAL.toString() + ":"+attr_nTrials.getName(), attr_numberOfTrials.getName());
			put(DistributionType.BINOMIAL.toString() + ":"+attr_numberOfTrials.getName(), attr_n.getName());
			put(DistributionType.BINOMIAL.toString() + ":"+attr_nTrials.getName(), attr_n.getName());
			put(DistributionType.BINOMIAL.toString() + ":"+attr_n.getName(), attr_nTrials.getName());
			put(DistributionType.BINOMIAL.toString() + ":"+attr_n.getName(), attr_numberOfTrials.getName());
			put(DistributionType.BINOMIAL.toString() + ":"+attr_probabilityOfSuccess.getName(), attr_p_ofSuccess.getName());
			put(DistributionType.BINOMIAL.toString() + ":"+attr_p_ofSuccess.getName(), attr_probabilityOfSuccess.getName());

			put(DistributionType.DISCRETE.toString() +":"+attr_ncat.getName(), attr_categories.getName());
			put(DistributionType.DISCRETE.toString() +":"+attr_categories.getName(), attr_ncat.getName());
			put(DistributionType.DISCRETE.toString() +":"+attr_probabilities.getName(), attr_prob.getName());
			put(DistributionType.DISCRETE.toString() +":"+attr_prob.getName(), attr_probabilities.getName());
			
			put(DistributionType.CHI_SQUARE.toString() + ":" + attr_pnat_degreesOfFreedom.getName(), attr_pnat_dof.getName());
			put(DistributionType.CHI_SQUARE.toString() + ":" + attr_pnat_dof.getName(), attr_pnat_degreesOfFreedom.getName()); 

			put(DistributionType.FDISTRIBUTION.toString() + ":" + attr_denominator.getName(), attr_den.getName());
			put(DistributionType.FDISTRIBUTION.toString() + ":" + attr_den.getName(), attr_denominator.getName()); 
			put(DistributionType.FDISTRIBUTION.toString() + ":" + attr_numerator.getName(), attr_num.getName());
			put(DistributionType.FDISTRIBUTION.toString() + ":" + attr_num.getName(), attr_numerator.getName()); 
			
			put(DistributionType.GEOMETRIC.toString() +":"+attr_probability.getName(), attr_p.getName());
			put(DistributionType.GEOMETRIC.toString() +":"+attr_p.getName(), attr_probability.getName());

			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_numberOfTrials.getName(), attr_nTrials.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_nTrials.getName(), attr_numberOfTrials.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_n.getName(), attr_nTrials.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_n.getName(), attr_numberOfTrials.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_numberOfTrials.getName(), attr_n.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_nTrials.getName(), attr_n.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_populationSize.getName(), attr_popSize.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_popSize.getName(), attr_populationSize.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_numberOfSuccesses.getName(), attr_nSuccess.getName());
			put(DistributionType.HYPERGEOMETRIC.toString() +":"+attr_nSuccess.getName(), attr_numberOfSuccesses.getName());

			put(DistributionType.LOG_NORMAL.toString() +":"+attr_median.getName(), attr_mu.getName());
			put(DistributionType.LOG_NORMAL.toString() +":"+attr_mu.getName(), attr_median.getName());
			put(DistributionType.LOG_NORMAL.toString() +":"+attr_cv.getName(), attr_var.getName());
			put(DistributionType.LOG_NORMAL.toString() + ":"+attr_var.getName(), attr_cv.getName());

			put(DistributionType.MULTINOMIAL.toString() +":"+attr_numberOfTrials.getName(), attr_nTrials.getName());
			put(DistributionType.MULTINOMIAL.toString() +":"+attr_nTrials.getName(), attr_numberOfTrials.getName());
			put(DistributionType.MULTINOMIAL.toString() +":"+attr_probabilities.getName(), attr_prob.getName());
			put(DistributionType.MULTINOMIAL.toString() +":"+attr_prob.getName(), attr_probabilities.getName());
			
			put(DistributionType.MULTIVARIATE_STUDENT_T.toString() + ":" + attr_pnat_degreesOfFreedom.getName(), attr_pnat_dof.getName());
			put(DistributionType.MULTIVARIATE_STUDENT_T.toString() + ":" + attr_pnat_dof.getName(), attr_pnat_degreesOfFreedom.getName()); 

			put(DistributionType.NEGATIVE_BINOMIAL.toString() +":"+attr_numberOfFailures.getName(), attr_nFail.getName());
			put(DistributionType.NEGATIVE_BINOMIAL.toString() +":"+attr_nFail.getName(), attr_numberOfFailures.getName());
			put(DistributionType.NEGATIVE_BINOMIAL.toString() +":"+attr_probability.getName(), attr_p.getName());
			put(DistributionType.NEGATIVE_BINOMIAL.toString() +":"+attr_p.getName(), attr_probability.getName());					

			put(DistributionType.STUDENT_T.toString() + ":" + attr_pnat_degreesOfFreedom.getName(), attr_pnat_dof.getName());
			put(DistributionType.STUDENT_T.toString() + ":" + attr_pnat_dof.getName(), attr_pnat_degreesOfFreedom.getName()); 

			put(DistributionType.NORMAL.toString() + ":" + attr_var.getName(), attr_sd.getName()); 
			put(DistributionType.NORMAL.toString() + ":" + attr_sd.getName(), attr_var.getName());
			
			put(DistributionType.UNIFORM.toString() + ":" + attr_numberOfClasses.getName(), attr_min.getName()); 
			put(DistributionType.UNIFORM.toString() + ":" + attr_min.getName(), attr_numberOfClasses.getName()); 
			put(DistributionType.UNIFORM.toString() + ":" +attr_max.getName(), attr_numberOfClasses.getName());
			
			put(DistributionType.UNIF.toString() + ":" + attr_numberOfClasses.getName(), attr_min.getName()); 
			put(DistributionType.UNIF.toString() + ":" + attr_min.getName(), attr_numberOfClasses.getName()); 
			put(DistributionType.UNIF.toString() + ":" +attr_max.getName(), attr_numberOfClasses.getName());
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

	public static final Attribute getAttributeById(String id){
		String[] tokens = id.split(":");
        if (tokens.length > 0){
        	List<Attribute> recognizedAttrs = distr_attrs.get(tokens[0]);
        	if (recognizedAttrs != null)
	        	for (Attribute attr: recognizedAttrs){
	        		if (attr.getName().equals(tokens[1])) return attr;
	        	}
        }
		return null;
    }
	
	@Check
	public void checkRequiredArguments(Arguments args){
		if (!(args.eContainer() instanceof RandomListImpl)) return; 
		AnyExpression type = MdlPrinter.getInstance().getAttributeExpression(args, attr_type.getName());
		if (type != null && type.getType() != null && type.getType().getDistribution() != DistributionType.NO_DISTRIBUTION){
			DistributionType typeName = type.getType().getDistribution();
			List<Attribute> recognized_attrs = distr_attrs.get(typeName);
			for (Attribute arg: recognized_attrs){
				if (arg.isMandatory()){
					AnyExpression actualArg = MdlPrinter.getInstance().getAttributeExpression(args, arg.getName());
					if (actualArg == null){
						String synonym = alternative_attrs.get(typeName +":" + arg.getName());
						if (synonym != null) actualArg =  MdlPrinter.getInstance().getAttributeExpression(args, synonym);
						if (actualArg == null)
							warning(MSG_DISTR_ATTRIBUTE_MISSING + ": " + arg.getName(), 
									MdlPackage.Literals.ARGUMENTS__ARGUMENTS,
									MSG_DISTR_ATTRIBUTE_MISSING, typeName + ":" + arg.getName());	
					}
				}
			}
		} else {
			warning(MSG_DISTR_UNKNOWN, 
				MdlPackage.Literals.ARGUMENTS__ARGUMENTS,
				MSG_DISTR_UNKNOWN, attr_type.getName());	
		}
	}
	
	@Check
	public void checkAllArguments(Argument argument){
		EObject argContainer = argument.eContainer();	
		if (!(argContainer instanceof ArgumentsImpl)) return;
		if (!(argContainer.eContainer() instanceof RandomListImpl)) return; 
		
		Arguments args = (Arguments)argContainer;
		HashSet<String> argumentNames = new HashSet<String>();	
		for (Argument arg: args.getArguments()){
			if (!argumentNames.contains(arg.getArgumentName().getName())){
				argumentNames.add(arg.getArgumentName().getName());
			} else {
				warning(MSG_DISTR_ATTRIBUTE_DEFINED + ": " + arg.getArgumentName().getName(), 
						MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME, 
						MSG_DISTR_ATTRIBUTE_DEFINED, arg.getArgumentName().getName());				
			}
		}	
		if (exclusive_attrs.containsKey(argument.getArgumentName().getName())){
			String exclusive = exclusive_attrs.get(argument.getArgumentName().getName());
			if (argumentNames.contains(exclusive)){
				warning("Distribution attribute '" + argument.getArgumentName().getName() + "' cannot be used together with '" + 
						exclusive + "'", 
						MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME, MSG_DISTR_ATTRIBUTE_DEFINED, 
						argument.getArgumentName().getName());				
			}
		}
		AnyExpression type = MdlPrinter.getInstance().getAttributeExpression(args, attr_type.getName());
		if (type != null && type.getType() != null && type.getType().getDistribution() != DistributionType.NO_DISTRIBUTION){
			List<Attribute> recognized_attrs = distr_attrs.get(type.getType().getDistribution());
			if (checkAttribute(recognized_attrs, argument)) return;
		}
		if (checkAttribute(common_attrs, argument)) return;
		warning(MSG_DISTR_ATTRIBUTE_UNKNOWN + ": " + argument.getArgumentName().getName(), 
			MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
			MSG_DISTR_ATTRIBUTE_UNKNOWN, argument.getArgumentName().getName());	
	}
	
	private Boolean checkAttribute(List<Attribute> recognized_attrs, Argument argument){
		for (Attribute attr: recognized_attrs){
			if (attr.getName().equals(argument.getArgumentName().getName())) {
				if (argument.getExpression() != null
						&& (!MdlDataType.validateType(attr.getType(), argument.getExpression()) 
						&& !MdlDataType.validateType(MdlDataType.TYPE_REF, argument.getExpression())) ||
					argument.getRandomList() != null 
						&& !MdlDataType.validateType(attr.getType(), argument.getRandomList())){
					warning(MSG_DISTR_ATTRIBUTE_WRONG_TYPE + 
							": attribute \"" + argument.getArgumentName().getName() + "\" expects value of type " + 
						attr.getType().name(), 
						MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
						MSG_DISTR_ATTRIBUTE_WRONG_TYPE, argument.getArgumentName().getName());
				}					
				return true; //found in the list
			}
		}
		return false;
	}
}
