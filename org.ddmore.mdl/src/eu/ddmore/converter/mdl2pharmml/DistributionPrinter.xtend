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

//TODO: test for all types of distributions 
//TODO: document + examples
class DistributionPrinter extends MdlPrinter{

	extension Constants constants = new Constants();

	//Recognised types of distributions and pairs (attribute, value type) to print as PharmML tags
	val distribution_attrs = newHashMap(
		'Bernoulli' -> newHashMap(
			"probability" -> new Attribute("probability", pVal)),          
		'Beta'  -> newHashMap(
			"alpha" -> new Attribute("alpha", rVal), 
			"beta" -> new Attribute("beta", rVal),  
			"lower" -> new Attribute("truncationLowerInclusiveBound", pVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", pVal)),  
		'Binomial' -> newHashMap(
			"numberOfTrials" -> new Attribute("numberOfTrials", nVal), 
			"probabilityOfSuccess" -> new Attribute("probabilityOfSuccess", pVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", nVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", nVal)),
		'Categorical' -> newHashMap(
			"ncategories" -> new Attribute("ncategories", nVal), 
			"probabilities" -> new Attribute("probabilities", pVal)), 
		'Cauchy' -> newHashMap(
			"location" -> new Attribute("location", rVal), 
			"scale" -> new Attribute("scale", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", nVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", nVal)),
		'ChiSquare' -> newHashMap(
			"degreesOfFreedom" -> new Attribute("degreesOfFreedom", pnVal), 
			"dof" -> new Attribute("degreesOfFreedom", pnVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", prVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", prVal)), 
		'Dirichlet' -> newHashMap(
			"concentration" -> new Attribute("concentration", prVal)),
		'Exponential' -> newHashMap(
			"alpha" -> new Attribute("rate", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", prVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", prVal)),
		'FDistribution' -> newHashMap(
			"denominator" -> new Attribute("denominator", nVal), 
			"numerator" -> new Attribute("numerator", nVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", prVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", prVal)),
		'Gamma' -> newHashMap(
			"shape" -> new Attribute("shape", prVal), 
			"scale" -> new Attribute("scale", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'Geometric' -> newHashMap(
			"probability" -> new Attribute("probability", pVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'Hypergeometric' -> newHashMap(
			"numberOfSuccesses" -> new Attribute("numberOfSuccesses", nVal), 
			"numberOfTrials" -> new Attribute("numberOfTrials", nVal), 
			"populationSize" -> new Attribute("populationSize", nVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)), 
		'InverseGamma' -> newHashMap(
			"shape" -> new Attribute("shape", prVal), 
			"scale" -> new Attribute("scale", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)), 
		'Laplace' -> newHashMap(
			"location" -> new Attribute("location", rVal), 
			"scale" -> new Attribute("scale", prVal),
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)), 
		'Logistic' -> newHashMap(
			"location" -> new Attribute("location", rVal), 
			"scale" -> new Attribute("scale", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)), 
		'LogNormal' -> newHashMap(
			"logScale" -> new Attribute("logScale", rVal), 
			"shape" -> new Attribute("shape", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'Multinomial' -> newHashMap(
			"numberOfTrials" -> new Attribute("numberOfTrials", nVal), 
			"probabilities" -> new Attribute("probabilities", pVal)), 
		'MultivariateNormal' -> newHashMap(
			"meanVector" -> new Attribute("meanVector", rVal), 
			"covarianceMatrix" -> new Attribute("covarianceMatrix", rVal)), 
		'MultivariateStudentT'	-> newHashMap(
			"meanVector" -> new Attribute("meanVector", rVal), 
			"covarianceMatrix" -> new Attribute("covarianceMatrix", rVal), 
			"degreesOfFreedom" -> new Attribute("degreesOfFreedom", pnVal), 
			"dof" -> new Attribute("degreesOfFreedom", pnVal)), 
		'NegativeBinomial'    	-> newHashMap(
			"numberOfFailures" -> new Attribute("numberOfFailures", nVal), 
			"probability" -> new Attribute("probability", pVal),  
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'Normal' -> newHashMap(
			"mean" -> new Attribute("mean", rVal), 
			"variance" -> new Attribute("variance", prVal), 
			"stddev" -> new Attribute("stddev", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'NormalInverseGamma' 	-> newHashMap(
			"mean" -> new Attribute("mean", rVal), 
			"varianceScaling" -> new Attribute("varianceScaling", prVal), 
			"shape" -> new Attribute("shape", prVal), 
			"scale" -> new Attribute("scale", prVal),
			"lowerN" -> new Attribute("truncationLowerInclusiveBoundN", nVal), 
			"upperN" -> new Attribute("truncationUpperInclusiveBoundN", nVal), 
			"lowerIG" -> new Attribute("truncationLowerInclusiveBoundIG", rVal), 
			"upperIG" -> new Attribute("truncationUpperInclusiveBoundIG", rVal)),
		'Pareto' -> newHashMap(
			"scale" -> new Attribute("scale", prVal), 
			"shape" -> new Attribute("shape", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'Poisson' -> newHashMap(
			"alpha" -> new Attribute("rate", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'StudentT' -> newHashMap(
			"location" -> new Attribute("location", rVal), 
			"scale" -> new Attribute("scale", prVal), 
			"degreesOfFreedom" -> new Attribute("degreesOfFreedom", pnVal), 
			"dof" -> new Attribute("degreesOfFreedom", pnVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'Uniform' -> newHashMap(
			"minimum" -> new Attribute("minimum", rVal), 
			"maximum" -> new Attribute("maximum", rVal), 
			"numberOfClasses" -> new Attribute("numberOfClasses", nVal)),
		'Weibull' -> newHashMap(
			"scale" -> new Attribute("scale", prVal), 
			"shape" -> new Attribute("shape", prVal), 
			"lower" -> new Attribute("truncationLowerInclusiveBound", rVal), 
			"upper" -> new Attribute("truncationUpperInclusiveBound", rVal)),
		'Wishart' -> newHashMap(
			"degreesOfFreedom" -> new Attribute("degreesOfFreedom", rVal), 
			"dof" -> new Attribute("degreesOfFreedom", rVal), 
			"scaleMatrix" -> new Attribute("scaleMatrix", prVal))
	)
	
	//Names of attributes that expect matrix as a value
	val matrix_attrs = newHashSet("covarianceMatrix", "scaleMatrix");	

	//Prints all distributions
	public def CharSequence print_uncert_Distribution(RandomList randomList){
		if (randomList != null){
			if (randomList.arguments != null){
				var type = randomList.arguments.getAttribute(DistributionValidator::attr_type.name);
				if (type.length > 0){
					if (type.equals("FDistribution")) type = "F";	
					switch(type){
						case type.equals("MixtureModel"): print_MixtureModel(randomList)
						default: print_DistributionDefault(randomList, type)
					}
				}
			}
		}
	}
	
	//NOTE: all attributes in distributions are named
	protected def print_DistributionDefault(RandomList randomList, String type){
		val recognizedArgs = distribution_attrs.get(type);
		if (recognizedArgs == null) return "";
		'''
		<«type»Distribution xmlns="«xmlns_uncert»" definition="«type.getURLExtension»">
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
									<values>«arg.value.toPharmML(dataType)»</values>
								</«attrName»>
							«ELSE»	
								<«attrName»>
									«arg.value.toPharmML(dataType)»
								</«attrName»>
							«ENDIF»
							«ENDIF»
					«ENDIF»
				«ENDIF»
			«ENDFOR»	
		</«type»Distribution>
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
	
	//TODO: add mixed model to the grammar, e.g.,	X = ~(type=mixedModel, ~(type=Normal...), ~(type=StudentT...)...)
	def print_MixtureModel(RandomList randomList)
	'''
		<MixtureModelDistribution xmlns="«xmlns_uncert»" definition="«definition»mixture-model">
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
		</MixtureModelDistribution>
	'''
	
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