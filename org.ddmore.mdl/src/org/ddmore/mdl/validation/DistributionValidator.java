/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks distribution definitions
 */
package org.ddmore.mdl.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.ddmore.mdl.mdl.DistributionArgument;
import org.ddmore.mdl.mdl.DistributionArguments;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.impl.DistributionArgumentsImpl;
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
	public final static String MSG_DISTR_ATTRIBUTE_WRONG_TYPE = "Type error";

	final static Attribute attr_probability = new Attribute("probability", DataType.TYPE_PROBABILITY, true);
	final static Attribute attr_probabilityOfSuccess = new Attribute("probabilityOfSuccess", DataType.TYPE_PROBABILITY, true);
	final static Attribute attr_probabilities = new Attribute("probabilities", DataType.TYPE_VECTOR_PROBABILITY, true);
	
	final static Attribute attr_alpha = new Attribute("alpha", DataType.TYPE_REAL, true);
	final static Attribute attr_beta = new Attribute("beta", DataType.TYPE_REAL, true);
	
	final static Attribute attr_continuous_truncationLowerInclusiveBound = new Attribute("truncationLowerInclusiveBound", DataType.TYPE_PROBABILITY, false);
	final static Attribute attr_continuous_truncationUpperInclusiveBound = new Attribute("truncationUpperInclusiveBound", DataType.TYPE_PROBABILITY, false);
	final static Attribute attr_natural_truncationLowerInclusiveBound = new Attribute("truncationLowerInclusiveBound", DataType.TYPE_NAT, false);
	final static Attribute attr_natural_truncationUpperInclusiveBound = new Attribute("truncationUpperInclusiveBound", DataType.TYPE_NAT, false);

	final static Attribute attr_ncategories = new Attribute("ncategories", DataType.TYPE_NAT, true);
	final static Attribute attr_numberOfTrials = new Attribute("numberOfTrials", DataType.TYPE_NAT, true);
	final static Attribute attr_numberOfSuccesses = new Attribute("numberOfSuccesses", DataType.TYPE_NAT, true);
	final static Attribute attr_numberOfFailures = new Attribute("numberOfFailures", DataType.TYPE_NAT, true);
	final static Attribute attr_populationSize = new Attribute("populationSize", DataType.TYPE_NAT, true);
	
	final static Attribute attr_degreesOfFreedom = new Attribute("degreesOfFreedom", DataType.TYPE_PNAT, true);
	final static Attribute attr_dof = new Attribute("dof", DataType.TYPE_PNAT, true);
	
	final static Attribute attr_real_degreesOfFreedom = new Attribute("degreesOfFreedom", DataType.TYPE_PREAL, true);
	final static Attribute attr_real_dof = new Attribute("dof", DataType.TYPE_PREAL, true);

	final static Attribute attr_preal_truncationLowerInclusiveBound = new Attribute("truncationLowerInclusiveBound", DataType.TYPE_PREAL, false);
	final static Attribute attr_preal_truncationUpperInclusiveBound = new Attribute("truncationUpperInclusiveBound", DataType.TYPE_PREAL, false);
	final static Attribute attr_concentration = new Attribute("concentration", DataType.TYPE_VECTOR_PREAL, true);
	final static Attribute attr_rate = new Attribute("rate", DataType.TYPE_PREAL, true);
	
	final static Attribute attr_denominator = new Attribute("denominator", DataType.TYPE_NAT, true);
	final static Attribute attr_numerator = new Attribute("numerator", DataType.TYPE_NAT, true);
	
	final static Attribute attr_location = new Attribute("location", DataType.TYPE_REAL, true);
	final static Attribute attr_shape = new Attribute("shape", DataType.TYPE_PREAL, true);
	final static Attribute attr_scale = new Attribute("scale", DataType.TYPE_PREAL, true);
	final static Attribute attr_logScale = new Attribute("logScale", DataType.TYPE_PREAL, true);

	final static Attribute attr_meanVector = new Attribute("meanVector", DataType.TYPE_VECTOR_REAL, true);
	final static Attribute attr_covarianceMatrix = new Attribute("covarianceMatrix", DataType.TYPE_VECTOR_REAL, true);

	final static Attribute attr_mean = new Attribute("mean", DataType.TYPE_REAL, true);
	final static Attribute attr_variance = new Attribute("variance", DataType.TYPE_PREAL, true);
	final static Attribute attr_stddev = new Attribute("stddev", DataType.TYPE_PREAL, true);

	final static Attribute attr_varianceScaling = new Attribute("varianceScaling", DataType.TYPE_PREAL, true);
	
	final static Attribute attr_truncationLowerInclusiveBoundN = new Attribute("truncationLowerInclusiveBoundN", DataType.TYPE_NAT, false);
	final static Attribute attr_truncationUpperInclusiveBoundN = new Attribute("truncationUpperInclusiveBoundN", DataType.TYPE_NAT, false);
	final static Attribute attr_truncationLowerInclusiveBoundIG = new Attribute("truncationLowerInclusiveBoundIG", DataType.TYPE_PREAL, false);
	final static Attribute attr_truncationUpperInclusiveBoundIG = new Attribute("truncationUpperInclusiveBoundIG", DataType.TYPE_REAL, false);

	final static Attribute attr_minimal = new Attribute("minimal", DataType.TYPE_REAL, true);
	final static Attribute attr_maximal = new Attribute("maximal", DataType.TYPE_REAL, true);
	final static Attribute attr_numberOfClasses = new Attribute("numberOfClasses", DataType.TYPE_NAT, true);

	final static Attribute attr_scaleMatrix = new Attribute("scaleMatrix", DataType.TYPE_VECTOR_PREAL, true);
	final static Attribute attr_dimension = new Attribute("dimension", DataType.TYPE_NAT, false);
	
	final static Attribute attr_type = new Attribute("type", DataType.TYPE_DISTRIBUTION, true);
	final static Attribute attr_level = new Attribute("level", DataType.TYPE_ID, false);
	final static Attribute attr_weight = new Attribute("weight", DataType.TYPE_REAL, false);	
	
	//List of synonyms or alternatives
	HashMap<String, String> alternative_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = 28966755954108955L;
		{
			put(attr_degreesOfFreedom.name, attr_dof.name);
			put(attr_dof.name, attr_degreesOfFreedom.name);
			put(attr_variance.name, attr_stddev.name);
			put(attr_stddev.name, attr_variance.name);
			put(attr_numberOfClasses.name, attr_minimal.name);
			put(attr_minimal.name, attr_numberOfClasses.name);
			put(attr_maximal.name, attr_numberOfClasses.name);
		}
	};
	
	final static HashMap<String, List<Attribute>> distr_attrs = 
			new HashMap<String, List<Attribute>>() {
				private static final long serialVersionUID = 27681295286815005L;
		{
			put("Bernoulli", Arrays.asList(attr_probability));
			put("Beta", Arrays.asList(
					attr_alpha,
					attr_beta, 
					attr_continuous_truncationLowerInclusiveBound,
					attr_continuous_truncationUpperInclusiveBound));
			put("Binomial", Arrays.asList(
					attr_numberOfTrials,
					attr_probabilityOfSuccess, 
					attr_natural_truncationLowerInclusiveBound,
					attr_natural_truncationUpperInclusiveBound));
			put("Categorical", Arrays.asList(
					attr_ncategories,
					attr_probabilities));
			put("Cauchy", Arrays.asList(
					attr_location,
					attr_scale, 
					attr_continuous_truncationLowerInclusiveBound,
					attr_continuous_truncationUpperInclusiveBound));
			put("ChiSquare", Arrays.asList(
					attr_degreesOfFreedom,
					attr_dof, 
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("Dirichlet", Arrays.asList(attr_concentration));
			put("Exponential", Arrays.asList(attr_rate,
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("FDistribution", Arrays.asList(
					attr_denominator,
					attr_numerator, 
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("Gamma", Arrays.asList(
					attr_shape,
					attr_scale, 
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("Geometric", Arrays.asList(
					attr_probability, 
					attr_natural_truncationLowerInclusiveBound,
					attr_natural_truncationUpperInclusiveBound));
			put("Hypergeometric", Arrays.asList(
					attr_numberOfSuccesses, 
					attr_numberOfTrials, 
					attr_populationSize,
					attr_natural_truncationLowerInclusiveBound,
					attr_natural_truncationUpperInclusiveBound));
			put("InverseGamma", Arrays.asList(
					attr_shape,
					attr_scale, 
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("Laplace", Arrays.asList(
					attr_location,
					attr_scale, 
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("Logistic", Arrays.asList(
					attr_location,
					attr_scale, 
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("LogScale", Arrays.asList(
					attr_logScale,
					attr_shape, 
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("Multinomial", Arrays.asList(
					attr_numberOfTrials,
					attr_probabilities)); 
			put("MultivariateNormal", Arrays.asList(
					attr_meanVector,
					attr_covarianceMatrix,
					attr_dimension)); 
			put("MultivariateStudentT", Arrays.asList(
					attr_meanVector,
					attr_covarianceMatrix,
					attr_degreesOfFreedom,
					attr_dof,
					attr_dimension)); 
			put("NegativeBinomial", Arrays.asList(
					attr_numberOfFailures,
					attr_probability, 
					attr_natural_truncationLowerInclusiveBound,
					attr_natural_truncationUpperInclusiveBound));
			put("Normal", Arrays.asList(
					attr_mean,
					attr_stddev, 
					attr_variance, 
					attr_continuous_truncationLowerInclusiveBound,
					attr_continuous_truncationUpperInclusiveBound));
			put("NormalInverseGamma", Arrays.asList(
					attr_mean,
					attr_varianceScaling, 
					attr_shape, 
					attr_scale,
					attr_truncationLowerInclusiveBoundN, 
					attr_truncationUpperInclusiveBoundN, 
					attr_truncationLowerInclusiveBoundIG, 
					attr_truncationUpperInclusiveBoundIG));
			put("Pareto", Arrays.asList(
					attr_scale,
					attr_shape, 
					attr_continuous_truncationLowerInclusiveBound, 
					attr_continuous_truncationUpperInclusiveBound));
			put("Poisson", Arrays.asList(
					attr_rate,
					attr_natural_truncationLowerInclusiveBound,
					attr_natural_truncationUpperInclusiveBound));
			put("StudentT", Arrays.asList(
					attr_location,
					attr_scale, 
					attr_degreesOfFreedom,
					attr_dof,
					attr_continuous_truncationLowerInclusiveBound,
					attr_continuous_truncationUpperInclusiveBound));
			put("Uniform", Arrays.asList(
					attr_minimal,
					attr_maximal,
					attr_numberOfClasses));
			put("Weibull", Arrays.asList(
					attr_scale,
					attr_shape, 
					attr_preal_truncationLowerInclusiveBound,
					attr_preal_truncationUpperInclusiveBound));
			put("Wishart", Arrays.asList(
					attr_real_degreesOfFreedom,
					attr_real_dof,
					attr_scaleMatrix,
					attr_dimension)); 
		}
	};

	final static List<Attribute> common_attrs = Arrays.asList(attr_type, attr_level,attr_weight);
	
	@Check
	public void checkRequiredArguments(DistributionArguments args){
		DistributionArgument type = findDistributionAttribute(args, attr_type.name);
		if (type != null){
			List<Attribute> recognized_attrs = distr_attrs.get(type.getDistribution().getIdentifier());
			if (recognized_attrs != null){
				for (Attribute arg: recognized_attrs){
					if (arg.mandatory){
						DistributionArgument actualArg = findDistributionAttribute(args, arg.name);
						if (actualArg == null){
							String synonym = alternative_attrs.get(arg.name);
							if (synonym != null){
								actualArg = findDistributionAttribute(args, synonym);
							if (actualArg == null)
								warning(MSG_DISTR_ATTRIBUTE_MISSING, 
										MdlPackage.Literals.DISTRIBUTION_ARGUMENTS__ARGUMENTS,
										MSG_DISTR_ATTRIBUTE_MISSING, arg.name);	
							}
						}
					}
				}
			}
		} else {
			warning(MSG_DISTR_UNKNOWN, 
				MdlPackage.Literals.DISTRIBUTION_ARGUMENTS__ARGUMENTS,
				MSG_DISTR_ATTRIBUTE_UNKNOWN, attr_type.name);	
		}
	}
	
	@Check
	public void checkAllArguments(DistributionArgument argument){
		EObject argContainer = argument.eContainer();	
		if (!(argContainer instanceof DistributionArgumentsImpl)) return;
		DistributionArguments args = (DistributionArguments)argContainer;
		DistributionArgument type = findDistributionAttribute(args, attr_type.name);
		if (type != null){
			List<Attribute> recognized_attrs = distr_attrs.get(type.getDistribution().getIdentifier());
			if (recognized_attrs != null){
				if (checkAttribute(argument, recognized_attrs)) return;
			}
			if (!checkAttribute(argument, common_attrs))
				warning(MSG_DISTR_ATTRIBUTE_UNKNOWN + ": " + argument.getArgumentName().getName(), 
					MdlPackage.Literals.DISTRIBUTION_ARGUMENT__ARGUMENT_NAME,
					MSG_DISTR_ATTRIBUTE_UNKNOWN, argument.getArgumentName().getName());	
		}
	}
	
	Boolean checkAttribute(DistributionArgument argument, List<Attribute> attrs){
		for (Attribute arg: attrs){
			if (arg.name.equals(argument.getArgumentName().getName())) {
				boolean isValid = DataType.validateType(arg.type, argument);
				if (!isValid){
					warning(MSG_DISTR_ATTRIBUTE_WRONG_TYPE + 
						": attribute \"" + argument.getArgumentName().getName() + "\" expects value of type " + 
							arg.type.name(), 
						MdlPackage.Literals.DISTRIBUTION_ARGUMENT__ARGUMENT_NAME,
						MSG_DISTR_ATTRIBUTE_WRONG_TYPE, argument.getArgumentName().getName());	
				}
				return true;
			}					
		}
		return false;
	}
	
	DistributionArgument findDistributionAttribute(DistributionArguments args, String name){
		for (DistributionArgument arg: args.getArguments()){
			if (arg.getArgumentName().getName().equals(name)){
				return arg;
			}
		}
		return null;
	}
	
	
}
