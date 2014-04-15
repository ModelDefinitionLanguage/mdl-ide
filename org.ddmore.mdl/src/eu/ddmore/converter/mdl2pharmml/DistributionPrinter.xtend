/* 
 * MDL converter toolbox, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to convert distributions from MDL to PharmML
 */
package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.Primary 
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.DistributionArgument
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.validation.DistributionValidator
import org.ddmore.mdl.types.DistributionType

//TODO: test for all types of distributions 
//TODO: document + examples
class DistributionPrinter extends MdlPrinter{

	extension Constants constants = new Constants();

	//Recognised types of distributions and pairs (attribute, value type) to print as PharmML tags
	val distribution_attrs = newHashMap(
		DistributionType::bernoulli -> newHashMap(
		//	DistributionValidator::attr_probability.name -> new Attribute("probability", pVal),
			DistributionValidator::attr_p.name -> new Attribute("probability", pVal)),          
		DistributionType::beta -> newHashMap(
			DistributionValidator::attr_alpha.name  -> new Attribute("alpha", rVal), 
			DistributionValidator::attr_beta.name  -> new Attribute("beta", rVal),  
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", pVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", pVal)),  
		DistributionType::binomial -> newHashMap(
			DistributionValidator::attr_numberOfTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_probabilityOfSuccess.name  -> new Attribute("probabilityOfSuccess", pVal), 
			DistributionValidator::attr_nTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_p_ofSuccess.name  -> new Attribute("probabilityOfSuccess", pVal), 
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)),
		DistributionType::categorical -> newHashMap(
			DistributionValidator::attr_ncategories.name  -> new Attribute("ncategories", nVal), 
			DistributionValidator::attr_probabilities.name  -> new Attribute("probabilities", pVal), 
			DistributionValidator::attr_ncat.name  -> new Attribute("ncategories", nVal), 
			DistributionValidator::attr_prob.name  -> new Attribute("probabilities", pVal)), 
		DistributionType::cauchy -> newHashMap(
			DistributionValidator::attr_location.name  -> new Attribute("location", rVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::chiSquare -> newHashMap(
			DistributionValidator::attr_pnat_degreesOfFreedom.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_pnat_dof.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)), 
		DistributionType::dirichlet -> newHashMap(
			DistributionValidator::attr_prealVector_alpha.name  -> new Attribute("concentration", prVal)),
		DistributionType::exponential -> newHashMap(
			DistributionValidator::attr_alpha.name  -> new Attribute("rate", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)),
		DistributionType::fDistribution -> newHashMap(
			DistributionValidator::attr_denominator.name  -> new Attribute("denominator", nVal), 
			DistributionValidator::attr_numerator.name  -> new Attribute("numerator", nVal), 
			DistributionValidator::attr_den.name  -> new Attribute("denominator", nVal), 
			DistributionValidator::attr_num.name  -> new Attribute("numerator", nVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)),
		DistributionType::gamma -> newHashMap(
			DistributionValidator::attr_shape.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::geometric -> newHashMap(
		//	DistributionValidator::attr_probability.name  -> new Attribute("probability", pVal), 
			DistributionValidator::attr_p.name  -> new Attribute("probability", pVal), 
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)),
		DistributionType::hypergeometric -> newHashMap(
			DistributionValidator::attr_numberOfSuccesses.name  -> new Attribute("numberOfSuccesses", nVal), 
			DistributionValidator::attr_numberOfTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_populationSize.name  -> new Attribute("populationSize", nVal), 
			DistributionValidator::attr_nSuccess.name  -> new Attribute("numberOfSuccesses", nVal), 
			DistributionValidator::attr_nTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_popSize.name  -> new Attribute("populationSize", nVal), 
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)), 
		DistributionType::inverseGamma -> newHashMap(
			DistributionValidator::attr_shape.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)), 
		DistributionType::laplace -> newHashMap(
			DistributionValidator::attr_location.name  -> new Attribute("location", rVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal),
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)), 
		DistributionType::logistic -> newHashMap(
			DistributionValidator::attr_location.name  -> new Attribute("location", rVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)), 
		DistributionType::logNormal -> newHashMap(
			DistributionValidator::attr_median.name  -> new Attribute("logScale", rVal), 
			DistributionValidator::attr_mu.name  -> new Attribute("logScale", rVal), 
			DistributionValidator::attr_cv.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_sigmatr.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)),
		DistributionType::multinomial -> newHashMap(
			DistributionValidator::attr_numberOfTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_probabilities.name  -> new Attribute("probabilities", pVal), 
			DistributionValidator::attr_nTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_prob.name  -> new Attribute("probabilities", pVal)), 
		DistributionType::multivariateNormal -> newHashMap(
			DistributionValidator::attr_realVector_mean.name  -> new Attribute("meanVector", rVal), 
			DistributionValidator::attr_cov.name  -> new Attribute("covarianceMatrix", rVal)), 
		DistributionType::multivariateStudentT	-> newHashMap(
			DistributionValidator::attr_realVector_mean.name  -> new Attribute("meanVector", rVal), 
			DistributionValidator::attr_cov.name  -> new Attribute("covarianceMatrix", rVal), 
			DistributionValidator::attr_pnat_degreesOfFreedom.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_pnat_dof.name  -> new Attribute("degreesOfFreedom", pnVal)), 
		DistributionType::negativeBinomial    	-> newHashMap(
			DistributionValidator::attr_numberOfFailures.name  -> new Attribute("numberOfFailures", nVal), 
		//	DistributionValidator::attr_probability.name  -> new Attribute("probability", pVal),  
			DistributionValidator::attr_nFail.name  -> new Attribute("numberOfFailures", nVal), 
			DistributionValidator::attr_p.name  -> new Attribute("probability", pVal),  
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)),
		DistributionType::normal -> newHashMap(
			DistributionValidator::attr_mean.name  -> new Attribute("mean", rVal), 
			DistributionValidator::attr_variance.name  -> new Attribute("variance", prVal), 
			DistributionValidator::attr_stddev.name  -> new Attribute("stddev", prVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::normalInverseGamma 	-> newHashMap(
			DistributionValidator::attr_mean.name  -> new Attribute("mean", rVal), 
			DistributionValidator::attr_varianceScaling.name  -> new Attribute("varianceScaling", prVal), 
			DistributionValidator::attr_shape.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal),
			DistributionValidator::attr_loN.name  -> new Attribute("truncationLowerInclusiveBoundN", nVal), 
			DistributionValidator::attr_hiN.name  -> new Attribute("truncationUpperInclusiveBoundN", nVal), 
			DistributionValidator::attr_loIG.name  -> new Attribute("truncationLowerInclusiveBoundIG", rVal), 
			DistributionValidator::attr_hiIG.name  -> new Attribute("truncationUpperInclusiveBoundIG", rVal)),
		DistributionType::pareto -> newHashMap(
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_shape.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::poisson -> newHashMap(
			DistributionValidator::attr_alpha.name  -> new Attribute("rate", prVal), 
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)),
		DistributionType::studentT -> newHashMap(
			DistributionValidator::attr_location.name  -> new Attribute("location", rVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_pnat_degreesOfFreedom.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_pnat_dof.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::uniform -> newHashMap(
			DistributionValidator::attr_min.name  -> new Attribute("minimum", rVal), 
			DistributionValidator::attr_max.name  -> new Attribute("maximum", rVal), 
			DistributionValidator::attr_numberOfClasses.name  -> new Attribute("numberOfClasses", nVal)),
		DistributionType::unif -> newHashMap(
			DistributionValidator::attr_min.name  -> new Attribute("minimum", rVal), 
			DistributionValidator::attr_max.name  -> new Attribute("maximum", rVal), 
			DistributionValidator::attr_numberOfClasses.name  -> new Attribute("numberOfClasses", nVal)),
		DistributionType::weibull -> newHashMap(
			DistributionValidator::attr_lambda.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_kappa.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)),
		DistributionType::wishart -> newHashMap(
			DistributionValidator::attr_n.name  -> new Attribute("degreesOfFreedom", prVal), 
			DistributionValidator::attr_scaleMatrix.name  -> new Attribute("scaleMatrix", prVal))
	)
	
	//Names of attributes that expect matrix as a value
	val matrix_attrs = newHashSet(DistributionValidator::attr_cov.name, DistributionValidator::attr_scaleMatrix.name
	);	

	//Prints all distributions
	public def CharSequence print_uncert_Distribution(RandomList randomList){
		if (randomList != null){
			if (randomList.arguments != null){
				var type = randomList.arguments.getAttribute(DistributionValidator::attr_type.name);
				if (type.length > 0){
					switch(type){
						//For any mixture model
						case type.contains("Model"): print_MixtureModel(randomList, type)
						default: {
							//Types in PharmML start from upper case!
							print_DistributionDefault(randomList, type)
						}
					}
				}
			}
		}
	}
	
	//TODO: add mixed model to the grammar, e.g.,	X = ~(type=mixedModel, ~(type=Normal...), ~(type=StudentT...)...)
	def print_MixtureModel(RandomList randomList, String type)'''
		«val tagName = type.substring(0, 1).toUpperCase() + type.substring(1)»
		<«tagName» xmlns="«xmlns_uncert»" definition="«definition»mixture-model">
			«FOR arg: randomList.arguments.arguments»
				«IF arg.component != null»
					«val weight = arg.component.arguments.getAttribute(DistributionValidator::attr_weight.name)»
					«IF weight.length > 0»
						<component weight="«weight»">
							«arg.component.print_uncert_Distribution»
						</component>
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		</«tagName»>
	'''
	
	//NOTE: all attributes in distributions are named
	protected def print_DistributionDefault(RandomList randomList, String type){
		val recognizedArgs = distribution_attrs.get(type);
		if (recognizedArgs == null) {
			System::out.println("MDL2PharmML: Could not find attributes for " + type);
			return "";
		}
		'''
		«var tagName = type.substring(0, 1).toUpperCase() + type.substring(1)»
		«if (tagName.contains("Distribution")) tagName = tagName.substring(0, tagName.indexOf("Distribution"))»	
		<«tagName»Distribution xmlns="«xmlns_uncert»" definition="«type.getURLExtension»">
			«FOR arg: randomList.arguments.arguments»
				«IF arg.argumentName != null»
					«IF recognizedArgs.containsKey(arg.argumentName.name)»
						«val attr = recognizedArgs.get(arg.argumentName.name)»
						«IF attr != null»
							«val dataType = attr.type»
							«val attrName = attr.name»
							«IF matrix_attrs.contains(arg.argumentName.name)»
								«var dimension = defineDimension(randomList, arg)»
								<«attrName» dimension="«dimension»">
							«ELSE»	
								<«attrName»>
							«ENDIF»
									«arg.value.toPharmML(dataType)»
								</«attrName»>
							«ENDIF»
					«ENDIF»
				«ENDIF»
			«ENDFOR»	
		</«tagName»Distribution>
		'''
	}	
	
	//For distributions that expect matrix, determine its dimensions
	//First look for an explicitly specified dimensions
	//If the attribute dimensions is not found, calculate the dimensions based on the number of nested lists (not reliable)
	//e.g., c(c(1,2,...), c(10, 20,...), c(100, 200,...)) yields 3	
	protected def defineDimension(RandomList randomList, DistributionArgument matrixAttr){
		var dimension = randomList.arguments.getAttribute(DistributionValidator::attr_dimension.name);
		if (dimension.length >= 0) {//explicit dimension is given
			try{
				Integer::parseInt(dimension);
			} 
			catch (NumberFormatException e){
			}
		}
		//compute from data 
		if (matrixAttr.value != null){
			if (matrixAttr.value.vector != null){
				val matrix = matrixAttr.value.vector;
				return matrix.values.size();
			}
		}
		return 0;
	}
	
	//Given distribution type, returns an URL to the XML schema
	//For example, MultivariateStudentT corresponds to multivariate-student-t
	protected def getURLExtension(String type){
		try{
			return definition + type.replaceAll("(.)([A-Z])", "$1-$2").toLowerCase();
		} 
		catch(Exception e){//just in case regEx fails...
			return definition + type.toLowerCase();
		}
	}
	
	//A value assigned to a distribution attribute can be a number, reference, or vector
	def toPharmML(Primary p, String type){
		if (p.number != null){
			return '''<«type»>«p.number»</«type»>''';
		}
		if (p.symbol != null){
			return '''<var varId="«p.symbol.toStr»"/>'''; 
		}
		if (p.vector != null) {
			return p.vector.toStr;
		}
	}
	
	//For references in distributions we just print its name and 
	//do not point to the PharmML block (MDL object) it appears  
	override toStr(FullyQualifiedSymbolName s){
		return s.symbol.name;
	}
}