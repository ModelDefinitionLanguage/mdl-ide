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

	final static HashMap<String, DistributionParameter> attrs = 
			new HashMap<String , DistributionParameter>() {
				private static final long serialVersionUID = 551199268331781795L;
			{
				//Bernoulli, Binomial, NegativeBinomial
				put("probability", new DistributionParameter("probability", DataTypes.TYPE_REAL, true, 0., 1.));
				//Beta
				put("alpha", new DistributionParameter("alpha", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				put("beta", new DistributionParameter("beta", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				//Beta, Cauchy, StudentT
				put("continuous_truncationLowerInclusiveBound", new DistributionParameter("truncationLowerInclusiveBound", DataTypes.TYPE_REAL, false, 0., 1.));
				put("continuous_truncationUpperInclusiveBound", new DistributionParameter("truncationUpperInclusiveBound", DataTypes.TYPE_REAL, false, 0., 1.));
				//Binomial, Hypergeometric
				put("numberOfTrials", new DistributionParameter("numberOfTrials", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				//Binomial
				put("probabilityOfSuccess", new DistributionParameter("probabilityOfSuccess", DataTypes.TYPE_REAL, true, 0., 1.));
				//Binomial, NegativeBinomial, Poisson
				put("natural_truncationLowerInclusiveBound", new DistributionParameter("truncationLowerInclusiveBound", DataTypes.TYPE_INT, false, 0, Integer.MAX_VALUE));
				put("natural_truncationUpperInclusiveBound", new DistributionParameter("truncationUpperInclusiveBound", DataTypes.TYPE_INT, false, 0, Integer.MAX_VALUE));
				//Categorical
				put("ncategories", new DistributionParameter("ncategories", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				put("probabilities", new DistributionParameter("probabilities", DataTypes.TYPE_VECTOR_REAL, true, 0., 1.));
				//Cauchy, Laplace
				put("location", new DistributionParameter("location", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				//Cauchy, Gamma, InverseGamma, Laplace, Logistic
				put("scale", new DistributionParameter("scale", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				//ChiSquare	
				put("degreesOfFreedom", new DistributionParameter("degreesOfFreedom", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				put("dof", new DistributionParameter("dof", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				//ChiSquare, Exponential, F, Gamma, InverseGamma
				put("preal_truncationLowerInclusiveBound", new DistributionParameter("truncationLowerInclusiveBound", DataTypes.TYPE_REAL, false, 0., Double.MAX_VALUE));
				put("preal_truncationUpperInclusiveBound", new DistributionParameter("truncationUpperInclusiveBound", DataTypes.TYPE_REAL, false, 0., Double.MAX_VALUE));
				//Dirichlet
				put("concentration", new DistributionParameter("concentration", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				//Exponential
				put("rate", new DistributionParameter("rate", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				//F
				put("denominator", new DistributionParameter("denominator", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				put("numerator", new DistributionParameter("numerator", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				//Gamma, InverseGamma, LogNormal
				put("shape", new DistributionParameter("shape", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				//Hypergeometric
				put("numberOfSuccesses", new DistributionParameter("numberOfSuccesses", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				put("populationSize", new DistributionParameter("populationSize", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				//LogNormal
				put("logScale", new DistributionParameter("logScale", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				//Multinomial
				put("probabilities", new DistributionParameter("probabilities", DataTypes.TYPE_VECTOR_REAL, true, 0., 1.));
				//MultivariateNormal, MultivariateStudentT
				put("meanVector", new DistributionParameter("meanVector", DataTypes.TYPE_VECTOR_REAL, true, 0., Double.MAX_VALUE));
				put("covarianceMatrix", new DistributionParameter("covarianceMatrix", DataTypes.TYPE_VECTOR_REAL, true, 0., Double.MAX_VALUE));
				//NegativeBinomial
				put("numberOfFailures", new DistributionParameter("numberOfFailures", DataTypes.TYPE_INT, true, 0., Integer.MAX_VALUE));
				//Normal
				put("mean", new DistributionParameter("mean", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				put("variance", new DistributionParameter("variance", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				put("stddev", new DistributionParameter("stddev", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				//NormalInverseGamma
				put("varianceScaling", new DistributionParameter("varianceScaling", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				put("truncationLowerInclusiveBoundN", new DistributionParameter("truncationLowerInclusiveBoundN", DataTypes.TYPE_INT, false, 0, Integer.MAX_VALUE));
				put("truncationUpperInclusiveBoundN", new DistributionParameter("truncationUpperInclusiveBoundN", DataTypes.TYPE_INT, false, 0, Integer.MAX_VALUE));
				put("truncationLowerInclusiveBoundIG", new DistributionParameter("truncationLowerInclusiveBoundIG", DataTypes.TYPE_REAL, false, 0., Double.MAX_VALUE));
				put("truncationUpperInclusiveBoundIG", new DistributionParameter("truncationUpperInclusiveBoundIG", DataTypes.TYPE_REAL, false, 0., Double.MAX_VALUE));
				//Uniform
				put("minimal", new DistributionParameter("minimal", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				put("maximal", new DistributionParameter("maximal", DataTypes.TYPE_REAL, true, 0., Double.MAX_VALUE));
				put("numberOfClasses", new DistributionParameter("numberOfClasses", DataTypes.TYPE_INT, true, 0, Integer.MAX_VALUE));
				//Wishart
				put("scaleMatrix", new DistributionParameter("scaleMatrix", DataTypes.TYPE_VECTOR_REAL, true, 0., Double.MAX_VALUE));
				//MultivariateNormal, MultivariateStudentT, Wishart 
				put("dimension", new DistributionParameter("dimension", DataTypes.TYPE_INT, false, 0, Integer.MAX_VALUE));
			}
		};
		
	//List of synonyms or alternatives
	HashMap<String, String> alternative_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = 28966755954108955L;
		{
			put("degreesOfFreedom", "dof");
			put("dof", "degreesOfFreedom");
			put("variance", "stddev");
			put("stddev", "variance");
			put("numberOfClasses", "minimal");
			put("minimal", "numberOfClasses");
			put("maximal", "numberOfClasses");
		}
	};
	
	final static HashMap<String, List<DistributionParameter>> distr_attrs = 
			new HashMap<String, List<DistributionParameter>>() {
				private static final long serialVersionUID = 27681295286815005L;
		{
			put("Bernoulli", Arrays.asList(attrs.get("probability")));
			put("Beta", Arrays.asList(
					attrs.get("alpha"),
					attrs.get("beta"), 
					attrs.get("continuous_truncationLowerInclusiveBound"),
					attrs.get("continuous_truncationUpperInclusiveBound")));
			put("Binomial", Arrays.asList(
					attrs.get("numberOfTrials"),
					attrs.get("probabilityOfSuccess"), 
					attrs.get("natural_truncationLowerInclusiveBound"),
					attrs.get("natural_truncationUpperInclusiveBound")));
			put("Categorical", Arrays.asList(
					attrs.get("ncategories"),
					attrs.get("probabilities")));
			put("Cauchy", Arrays.asList(
					attrs.get("location"),
					attrs.get("scale"), 
					attrs.get("continuous_truncationLowerInclusiveBound"),
					attrs.get("continuous_truncationUpperInclusiveBound")));
			put("ChiSquare", Arrays.asList(
					attrs.get("degreesOfFreedom"),
					attrs.get("dof"), 
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("Dirichlet", Arrays.asList(attrs.get("concentration")));
			put("Exponential", Arrays.asList(attrs.get("rate"),
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("FDistribution", Arrays.asList(
					attrs.get("denominator"),
					attrs.get("numerator"), 
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("Gamma", Arrays.asList(
					attrs.get("shape"),
					attrs.get("scale"), 
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("Hypergeometric", Arrays.asList(
					attrs.get("numberOfSuccesses"), 
					attrs.get("numberOfTrials"), 
					attrs.get("populationSize"),
					attrs.get("natural_truncationLowerInclusiveBound"),
					attrs.get("natural_truncationUpperInclusiveBound")));
			put("InverseGamma", Arrays.asList(
					attrs.get("shape"),
					attrs.get("scale"), 
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("Laplace", Arrays.asList(
					attrs.get("location"),
					attrs.get("scale"), 
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("Logistic", Arrays.asList(
					attrs.get("location"),
					attrs.get("scale"), 
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("LogScale", Arrays.asList(
					attrs.get("logScale"),
					attrs.get("shape"), 
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("Multinomial", Arrays.asList(
					attrs.get("numberOfTrials"),
					attrs.get("probabilities"))); 
			put("MultivariateNormal", Arrays.asList(
					attrs.get("meanVector"),
					attrs.get("covarianceMatrix"),
					attrs.get("dimension"))); 
			put("MultivariateStudentT", Arrays.asList(
					attrs.get("meanVector"),
					attrs.get("covarianceMatrix"),
					attrs.get("degreesOfFreedom"),
					attrs.get("dof"),
					attrs.get("dimension"))); 
			put("NegativeBinomial", Arrays.asList(
					attrs.get("numberOfFailures"),
					attrs.get("probability"), 
					attrs.get("natural_truncationLowerInclusiveBound"),
					attrs.get("natural_truncationUpperInclusiveBound")));
			put("Normal", Arrays.asList(
					attrs.get("mean"),
					attrs.get("stddev"), 
					attrs.get("variance"), 
					attrs.get("continuous_truncationLowerInclusiveBound"),
					attrs.get("continuous_truncationUpperInclusiveBound")));
			put("NormalInverseGamma", Arrays.asList(
					attrs.get("mean"),
					attrs.get("varianceScaling"), 
					attrs.get("shape"), 
					attrs.get("scale"),
					attrs.get("truncationLowerInclusiveBoundN"), 
					attrs.get("truncationUpperInclusiveBoundN"), 
					attrs.get("truncationLowerInclusiveBoundIG"), 
					attrs.get("truncationUpperInclusiveBoundIG")));
			put("Pareto", Arrays.asList(
					attrs.get("scale"),
					attrs.get("shape"), 
					attrs.get("continuous_truncationLowerInclusiveBound"), 
					attrs.get("continuous_truncationUpperInclusiveBound")));
			put("Poisson", Arrays.asList(
					attrs.get("rate"),
					attrs.get("natural_truncationLowerInclusiveBound"),
					attrs.get("natural_truncationUpperInclusiveBound")));
			put("StudentT", Arrays.asList(
					attrs.get("location"),
					attrs.get("scale"), 
					attrs.get("degreesOfFreedom"),
					attrs.get("dof"),
					attrs.get("continuous_truncationLowerInclusiveBound"),
					attrs.get("continuous_truncationUpperInclusiveBound")));
			put("Weibull", Arrays.asList(
					attrs.get("scale"),
					attrs.get("shape"), 
					attrs.get("preal_truncationLowerInclusiveBound"),
					attrs.get("preal_truncationUpperInclusiveBound")));
			put("Wishart", Arrays.asList(
					attrs.get("degreesOfFreedom"),
					attrs.get("scaleMatrix"),
					attrs.get("dimension"))); 
		}
	};

	final static List<DistributionParameter> common_attrs = Arrays.asList(
			new DistributionParameter("type", DataTypes.TYPE_UNDEFINED, true),
			new DistributionParameter("level", DataTypes.TYPE_UNDEFINED, false),
			new DistributionParameter("weight", DataTypes.TYPE_REAL, false, 0., 1.));
	
	@Check
	public void checkRequiredArguments(DistributionArguments args){
		DistributionArgument type = findDistributionAttribute(args, "type");
		if (type != null){
			List<DistributionParameter> recognized_attrs = distr_attrs.get(type.getDistribution().getIdentifier());
			if (recognized_attrs != null){
				for (DistributionParameter arg: recognized_attrs){
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
				MSG_DISTR_ATTRIBUTE_UNKNOWN, "type");	
		}
	}
	
	@Check
	public void checkAllArguments(DistributionArgument argument){
		EObject argContainer = argument.eContainer();	
		if (!(argContainer instanceof DistributionArgumentsImpl)) return;
		DistributionArguments args = (DistributionArguments)argContainer;
		DistributionArgument type = findDistributionAttribute(args, "type");
		if (type != null){
			List<DistributionParameter> recognized_attrs = distr_attrs.get(type.getDistribution().getIdentifier());
			if (recognized_attrs != null){
				for (DistributionParameter arg: recognized_attrs){
					if (arg.name.equals(argument.getIdentifier())) 
						return;
				}
			}
			for (DistributionParameter arg: common_attrs){
				if (arg.name.equals(argument.getIdentifier())) 
					return;
			}
			warning(MSG_DISTR_ATTRIBUTE_UNKNOWN + ": " + argument.getIdentifier(), 
			MdlPackage.Literals.DISTRIBUTION_ARGUMENT__IDENTIFIER,
			MSG_DISTR_ATTRIBUTE_UNKNOWN, argument.getIdentifier());			
		}
	}
	
	DistributionArgument findDistributionAttribute(DistributionArguments args, String name){
		for (DistributionArgument arg: args.getArguments()){
			if (arg.getIdentifier().equals(name)){
				return arg;
			}
		}
		return null;
	}
	
	
}
