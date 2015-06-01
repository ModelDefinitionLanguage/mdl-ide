/* 
 * MDL converter toolbox, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to convert distributions from MDL to PharmML
 */
package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.RandomList
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.validation.DistributionValidator
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import eu.ddmore.converter.mdl2pharmml.domain.Attribute
import org.ddmore.mdl.types.MdlDataType
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.types.DistributionType
import org.ddmore.mdl.mdl.Expression

class DistributionPrinter extends MdlPrinter{
	
	private static val DistributionPrinter distrPrinter = new DistributionPrinter();
	protected new(){}
	public static def DistributionPrinter getInstance(){
		return distrPrinter;
	}

	//Recognised types of distributions and pairs (attribute, value type) to print as PharmML tags
	private val distribution_attrs = newHashMap(
		DistributionType::Bernoulli.toString -> newHashMap(
			DistributionValidator::attr_p.name -> new Attribute("probability", pVal)),          
		DistributionType::BetaDistribution.toString -> newHashMap(
			DistributionValidator::attr_alpha.name  -> new Attribute("alpha", rVal), 
			DistributionValidator::attr_beta.name  -> new Attribute("beta", rVal),  
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", pVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", pVal)),  
		DistributionType::Binomial.toString -> newHashMap(
			DistributionValidator::attr_numberOfTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_probabilityOfSuccess.name  -> new Attribute("probabilityOfSuccess", pVal), 
			DistributionValidator::attr_nTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_n.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_p_ofSuccess.name  -> new Attribute("probabilityOfSuccess", pVal), 
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)),
		DistributionType::Categorical.toString -> newHashMap(
			DistributionValidator::attr_probabilities.name  -> new Attribute("probabilities", pVal), 
			DistributionValidator::attr_ncat.name  -> new Attribute("ncategories", nVal), 
			DistributionValidator::attr_prob.name  -> new Attribute("probabilities", pVal), 
			DistributionValidator::attr_categories.name  -> new Attribute("ncategories", nVal)),
		DistributionType::Cauchy.toString -> newHashMap(
			DistributionValidator::attr_location.name  -> new Attribute("location", rVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::ChiSquare.toString -> newHashMap(
			DistributionValidator::attr_pnat_degreesOfFreedom.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_pnat_dof.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)), 
		DistributionType::Dirichlet.toString -> newHashMap(
			DistributionValidator::attr_prealVector_alpha.name  -> new Attribute("concentration", prVal)),
		DistributionType::Exponential.toString -> newHashMap(
			DistributionValidator::attr_alpha.name  -> new Attribute("rate", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)),
		DistributionType::F.toString -> newHashMap(
			DistributionValidator::attr_denominator.name  -> new Attribute("denominator", nVal), 
			DistributionValidator::attr_numerator.name  -> new Attribute("numerator", nVal), 
			DistributionValidator::attr_den.name  -> new Attribute("denominator", nVal), 
			DistributionValidator::attr_num.name  -> new Attribute("numerator", nVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)),
		DistributionType::Gamma.toString -> newHashMap(
			DistributionValidator::attr_shape.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::Geometric.toString -> newHashMap(
			DistributionValidator::attr_p.name  -> new Attribute("probability", pVal), 
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)),
		DistributionType::Hypergeometric.toString -> newHashMap(
			DistributionValidator::attr_numberOfSuccesses.name  -> new Attribute("numberOfSuccesses", nVal), 
			DistributionValidator::attr_numberOfTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_populationSize.name  -> new Attribute("populationSize", nVal), 
			DistributionValidator::attr_nSuccess.name  -> new Attribute("numberOfSuccesses", nVal), 
			DistributionValidator::attr_nTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_n.name  -> new Attribute("numberOfTrials", nVal),
			DistributionValidator::attr_popSize.name  -> new Attribute("populationSize", nVal), 
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)), 
		DistributionType::InverseGamma.toString -> newHashMap(
			DistributionValidator::attr_shape.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)), 
		DistributionType::Laplace.toString -> newHashMap(
			DistributionValidator::attr_location.name  -> new Attribute("location", rVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal),
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)), 
		DistributionType::Logistic.toString -> newHashMap(
			DistributionValidator::attr_location.name  -> new Attribute("location", rVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)), 
		DistributionType::LogNormal.toString -> newHashMap(
			DistributionValidator::attr_median.name  -> new Attribute("logScale", rVal), 
			DistributionValidator::attr_mu.name  -> new Attribute("logScale", rVal), 
			DistributionValidator::attr_cv.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_var.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)),
		DistributionType::Multinomial.toString -> newHashMap(
			DistributionValidator::attr_numberOfTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_probabilities.name  -> new Attribute("probabilities", pVal), 
			DistributionValidator::attr_nTrials.name  -> new Attribute("numberOfTrials", nVal), 
			DistributionValidator::attr_prob.name  -> new Attribute("probabilities", pVal)), 
		DistributionType::MultivariateNormal.toString -> newHashMap(
			DistributionValidator::attr_realVector_mean.name  -> new Attribute("meanVector", rVal), 
			DistributionValidator::attr_cov.name  -> new Attribute("covarianceMatrix", rVal)), 
		DistributionType::MultivariateStudentT.toString -> newHashMap(
			DistributionValidator::attr_realVector_mean.name  -> new Attribute("meanVector", rVal), 
			DistributionValidator::attr_cov.name  -> new Attribute("covarianceMatrix", rVal), 
			DistributionValidator::attr_pnat_degreesOfFreedom.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_pnat_dof.name  -> new Attribute("degreesOfFreedom", pnVal)), 
		DistributionType::NegativeBinomial.toString -> newHashMap(
			DistributionValidator::attr_numberOfFailures.name  -> new Attribute("numberOfFailures", nVal), 
			DistributionValidator::attr_nFail.name  -> new Attribute("numberOfFailures", nVal), 
			DistributionValidator::attr_p.name  -> new Attribute("probability", pVal),  
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)),
		DistributionType::Normal.toString -> newHashMap(
			DistributionValidator::attr_mean.name  -> new Attribute("mean", rVal), 
			DistributionValidator::attr_var.name  -> new Attribute("variance", prVal), 
			DistributionValidator::attr_sd.name  -> new Attribute("stddev", prVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::NormalInverseGamma.toString -> newHashMap(
			DistributionValidator::attr_mean.name  -> new Attribute("mean", rVal), 
			DistributionValidator::attr_varianceScaling.name  -> new Attribute("varianceScaling", prVal), 
			DistributionValidator::attr_shape.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal),
			DistributionValidator::attr_loN.name  -> new Attribute("truncationLowerInclusiveBoundN", nVal), 
			DistributionValidator::attr_hiN.name  -> new Attribute("truncationUpperInclusiveBoundN", nVal), 
			DistributionValidator::attr_loIG.name  -> new Attribute("truncationLowerInclusiveBoundIG", rVal), 
			DistributionValidator::attr_hiIG.name  -> new Attribute("truncationUpperInclusiveBoundIG", rVal)),
		DistributionType::Pareto.toString -> newHashMap(
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_shape.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::Poisson.toString -> newHashMap(
			DistributionValidator::attr_lambda.name  -> new Attribute("rate", prVal), 
			DistributionValidator::attr_nat_lo.name  -> new Attribute("truncationLowerInclusiveBound", nVal), 
			DistributionValidator::attr_nat_hi.name  -> new Attribute("truncationUpperInclusiveBound", nVal)),
		DistributionType::StudentT.toString -> newHashMap(
			DistributionValidator::attr_location.name  -> new Attribute("location", rVal), 
			DistributionValidator::attr_scale.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_pnat_degreesOfFreedom.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_pnat_dof.name  -> new Attribute("degreesOfFreedom", pnVal), 
			DistributionValidator::attr_continuous_lo.name  -> new Attribute("truncationLowerInclusiveBound", rVal), 
			DistributionValidator::attr_continuous_hi.name  -> new Attribute("truncationUpperInclusiveBound", rVal)),
		DistributionType::Uniform.toString -> newHashMap(
			DistributionValidator::attr_min.name  -> new Attribute("minimum", rVal), 
			DistributionValidator::attr_max.name  -> new Attribute("maximum", rVal), 
			DistributionValidator::attr_numberOfClasses.name  -> new Attribute("numberOfClasses", nVal)),
		DistributionType::Unif.toString -> newHashMap(
			DistributionValidator::attr_min.name  -> new Attribute("minimum", rVal), 
			DistributionValidator::attr_max.name  -> new Attribute("maximum", rVal), 
			DistributionValidator::attr_numberOfClasses.name  -> new Attribute("numberOfClasses", nVal)),
		DistributionType::Weibull.toString -> newHashMap(
			DistributionValidator::attr_lambda.name  -> new Attribute("scale", prVal), 
			DistributionValidator::attr_kappa.name  -> new Attribute("shape", prVal), 
			DistributionValidator::attr_preal_lo.name  -> new Attribute("truncationLowerInclusiveBound", prVal), 
			DistributionValidator::attr_preal_hi.name  -> new Attribute("truncationUpperInclusiveBound", prVal)),
		DistributionType::Wishart.toString -> newHashMap(
			DistributionValidator::attr_n.name  -> new Attribute("degreesOfFreedom", prVal), 
			DistributionValidator::attr_scaleMatrix.name  -> new Attribute("scaleMatrix", prVal))
	)
	
	//Names of attributes that expect matrix as a value
	private val matrix_attrs = 
		newHashSet(DistributionValidator::attr_cov.name, DistributionValidator::attr_scaleMatrix.name
	);	

	//Prints all distributions
	public def CharSequence print_uncert_Distribution(RandomList randomList){
		if (randomList != null && randomList.type != null){
			var typeName = randomList.type.name;
			if (typeName.length > 0){
				switch(typeName){//For any mixture model
					case typeName.contains("Model"): randomList.print_MixtureModel(typeName)
					default: randomList.print_DistributionDefault(typeName)
				}
			}
		}
	}
	
	protected def print_MixtureModel(RandomList randomList, String type)'''
		«val tagName = type.substring(0, 1).toUpperCase() + type.substring(1)»
		<«tagName» xmlns="«xmlns_uncert»" definition="«definition»mixture-model">
			«IF randomList.arguments.namedArguments != null»
				«FOR arg: randomList.arguments.namedArguments.arguments»
					«IF arg.expression.randomList != null»
						«val weight = arg.expression.randomList.arguments.getAttribute(DistributionValidator::attr_weight.name)»
						«IF weight.length > 0»
							<component weight="«weight»">
								«arg.expression.randomList.print_uncert_Distribution»
							</component>
						«ENDIF»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		</«tagName»>
	'''
	
	//NOTE: Conversion works only for definitions with explicit attribute names, no default order supported
	//TODO: fix default order case
	protected def print_DistributionDefault(RandomList randomList, String type){
		val recognizedArgs = distribution_attrs.get(type);
		if (recognizedArgs == null) return "";
		'''
		<«type»Distribution xmlns="«xmlns_uncert»" definition="0.1">
			«IF randomList.arguments.namedArguments != null»
				«FOR arg: randomList.arguments.namedArguments.arguments»
					«IF recognizedArgs.containsKey(arg.argumentName.argName)»
						«val attr = recognizedArgs.get(arg.argumentName.argName)»
						«IF attr != null»
							«attr.print_DistributionDefault(arg.expression.expression)»
						«ENDIF»
					«ENDIF»
				«ENDFOR»
			«ELSE»
				«IF randomList.arguments.unnamedArguments != null»
					«var mdlAttrs = DistributionValidator::getAttributes(type)»
					«IF mdlAttrs != null»
						«FOR i: 0..randomList.arguments.unnamedArguments.arguments.size-1»
							«var arg = randomList.arguments.unnamedArguments.arguments.get(i)»
							«IF mdlAttrs.size - 1 > i»
								«val mdlAttr = mdlAttrs.get(i)»
								«val attr = recognizedArgs.get(mdlAttr.name)»
								«IF attr != null»
									«attr.print_DistributionDefault(arg.expression)»
								«ENDIF»
							«ENDIF»
						«ENDFOR»
					«ENDIF»	
				«ENDIF»
			«ENDIF»
		</«type»Distribution>
		'''
	}	
	
	protected def print_DistributionDefault(Attribute attr, AnyExpression expr)'''
		«IF expr != null»
			«IF matrix_attrs.contains(attr.name)»
				«var dimension = expr.defineDimension»
				<«attr.name» dimension="«dimension»">
			«ELSE»	
				<«attr.name»>
			«ENDIF»
				«IF attr.name.equals(DistributionValidator::attr_categories.name)»
					«IF expr.vector != null»
						«IF expr.vector.expression != null»
							«var exprVector = expr.vector.expression»
							«IF exprVector.expressions != null»
								<«attr.type»>«exprVector.expressions.size»</«attr.type»>
							«ENDIF»
						«ENDIF»
					«ENDIF»
				«ELSE»	
					«expr.toPharmML(attr.type)»
				«ENDIF»	
			</«attr.name»>
		«ENDIF»
	'''
	
	//For distributions that expect matrix, determine its dimension from the size 
	protected def defineDimension(AnyExpression expr){
		if (expr.vector != null){
			val matrix = expr.vector;
			if (matrix.expression != null && matrix.expression.expressions != null)
				return matrix.expression.expressions.size();
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
	
	protected def String toPharmML(AnyExpression expr, String type){
		if (expr.expression != null)
			return toPharmML(expr.expression, type);
		if (expr.vector != null && expr.vector.expression != null && expr.vector.expression.expressions != null) {
			var res = "";
			for (pp: expr.vector.expression.expressions)
				res = res + '''«pp.toPharmML(type)»'''
			return '''«res»'''
		}
	}
	
	//TODO create temporal variables for complex expressions?
	protected def String toPharmML(Expression expr, String type){
		if (MdlDataType::validateType(MdlDataType::TYPE_REF, expr) || MdlDataType::validateType(MdlDataType::TYPE_STRING, expr))
			return '''<var varId="«expr.toStr»"/>'''; 
		if (MdlDataType::validateType(MdlDataType::TYPE_REAL, expr))
			return '''<«type»>«expr.toStr»</«type»>''';
	}
}