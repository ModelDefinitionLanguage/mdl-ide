package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.converter.mdl2pharmml.domain.Attribute
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.types.DistributionType
import org.ddmore.mdl.types.MdlDataType
import org.ddmore.mdl.validation.DistributionValidator

import static eu.ddmore.converter.mdl2pharmml.Constants.*

class CustomDistributionPrinter {
	private extension DistributionPrinter mdlPrinter
	
	protected new(DistributionPrinter distPrinter){
			mdlPrinter = distPrinter
	}


	private val distribution_attrs = newHashMap(
		DistributionType::Bernoulli.toString -> newHashMap(
			DistributionValidator::attr_p.name -> new Attribute("probability", pVal)),        
		DistributionType::Binomial.toString -> newHashMap(
			DistributionValidator::attr_numberOfTrials.name -> new Attribute("numberOfTrials", nVal),        
			DistributionValidator::attr_probabilityOfSuccess.name -> new Attribute("probabilityOfSuccess", pVal))        
		)

	public def printDiscreteDistribution(RandomList distnDef, String category){
		val typeName = distnDef.type.name;
		var retVal = ''''''
		switch(typeName){
			case "Bernoulli": retVal = distnDef.printBernoulliDistn(category).toString
			case "Binomial": retVal = distnDef.printBinomialDistn.toString
		}
		retVal
	}

	public def printBernoulliDistn(RandomList randomList, String category){
		var typeName = randomList.type.name;
		val recognizedArgs = distribution_attrs.get(typeName);
		val attr = recognizedArgs.get(DistributionValidator::attr_p.name)
		val expr = randomList.arguments.namedArguments.arguments.get(0)
		'''
						<BernoulliDistribution xmlns="http://www.uncertml.org/3.0" definition="http://www.uncertml.org/3.0">
							<categoryProb definition="">
								<name>«category»</name>
								<probability>«expr.expression.expression.expression.toPharmML(attr.type)»</probability>
							</categoryProb>
						</BernoulliDistribution>
		'''
		}

	public def printBinomialDistn(RandomList randomList){
		var typeName = randomList.type.name;
		val recognizedArgs = distribution_attrs.get(typeName);
		val numTrials = randomList.arguments.getAttributeExpression(DistributionValidator::attr_numberOfTrials.name);
		val probs = randomList.arguments.getAttributeExpression(DistributionValidator::attr_probabilityOfSuccess.name);
		'''
						<BinomialDistribution xmlns="http://www.uncertml.org/3.0" definition="http://www.uncertml.org/3.0">
							<numberOfTrials>
								«numTrials.expression.toPharmML(recognizedArgs.get(DistributionValidator::attr_numberOfTrials.name).type)»
							</numberOfTrials>
							<probabilityOfSuccess>
								«probs.expression.toPharmML(recognizedArgs.get(DistributionValidator::attr_probabilityOfSuccess.name).type)»
							</probabilityOfSuccess>
						</BinomialDistribution>
		'''
		}

	protected def String toPharmML(Expression expr, String type){
		if (MdlDataType::validateType(MdlDataType::TYPE_REF, expr) || MdlDataType::validateType(MdlDataType::TYPE_STRING, expr))
			return '''<var varId="«expr.toStr»"/>'''; 
		if (MdlDataType::validateType(MdlDataType::TYPE_REAL, expr))
			return '''<«type»>«expr.toStr»</«type»>''';
	}
}