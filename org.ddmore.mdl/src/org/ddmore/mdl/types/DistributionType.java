package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

public class DistributionType {
	public static final String bernoulli = "bernoulli";
	public static final String beta = "betaDistribution";
	public static final String binomial = "binomial";
	public static final String discrete = "discrete"; 
	public static final String cauchy = "cauchy"; 	
	public static final String chiSquare = "chiSquare";
	public static final String dirichlet = "dirichlet"; 	
	public static final String exponential = "exponential"; 	
	public static final String fDistribution = "fDistribution";
	public static final String gamma = "gamma"; 
	public static final String geometric = "geometric"; 
	public static final String hypergeometric = "hypergeometric";
	public static final String inverseGamma = "inverseGamma";	
	public static final String laplace = "laplace";	
	public static final String logistic = "logistic";
	public static final String logNormal = "logNormal";
	public static final String multinomial = "multinomial";
	public static final String multivariateNormal = "multivariateNormal"; 	
	public static final String multivariateStudentT = "multivariateStudentT"; 	
	public static final String negativeBinomial = "negativeBinomial";
	public static final String normal = "normal";
	public static final String normalInverseGamma = "normalInverseGamma";
	public static final String pareto = "pareto";
	public static final String poisson = "poisson"; 
	public static final String studentT = "studentT";
	public static final String uniform = "uniform";
	public static final String unif = "unif"; 
	public static final String weibull = "weibull"; 
	public static final String wishart = "wishart"; 
	public static final String discreteUnivariateMixtureModel = "discreteUnivariateMixtureModel"; 
	public static final String discreteMultivariateMixtureModel = "discreteMultivariateMixtureModel"; 
	public static final String categoricalUnivariateMixtureModel = "categoricalUnivariateMixtureModel"; 
	public static final String categoricalMultivariateMixtureModel = "categoricalMultivariateMixtureModel"; 
	public static final String continuousMultivariateMixtureModel = "continuousMultivariateMixtureModel"; 
	public static final String continuousUnivariateMixtureModel = "continuousUnivariateMixtureModel";
	
	public final static List<String> DISTR_VALUES = Arrays.asList(
			bernoulli ,
			beta ,
			binomial ,
			discrete , 
			cauchy , 	
			chiSquare ,
			dirichlet , 	
			exponential , 	
			fDistribution ,
			gamma , 
			geometric , 
			hypergeometric ,
			inverseGamma ,	
			laplace ,	
			logistic ,
			logNormal	,
			multinomial ,
			multivariateNormal , 	
			multivariateStudentT , 	
			negativeBinomial ,
			normal ,
			normalInverseGamma ,
			pareto ,
			poisson ,
			unif ,
			studentT ,
			uniform ,
			weibull ,
			wishart ,
			discreteUnivariateMixtureModel ,
		    discreteMultivariateMixtureModel ,
		    categoricalUnivariateMixtureModel ,
			categoricalMultivariateMixtureModel ,
			continuousMultivariateMixtureModel ,
			continuousUnivariateMixtureModel
			);
}
