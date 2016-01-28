package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import eu.ddmore.mdl.utils.MdlUtils

class DistributionPrinter {
	
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension MdlUtils mu = new MdlUtils
	
	@Data @FinalFieldsConstructor
	static class UncertMlMapping{
		String name
		String definition
		List<String> argOrder 
		Map<String, UncertMlArgument> argMapping
	}
	
	@Data @FinalFieldsConstructor
	static class UncertMlArgument{
		String name
		String typeStr
	}
	

	static var pharmMLMapping = #{
		'Normal' -> new UncertMlMapping('NormalDistribution', 'http://www.uncertml.org/distributions/normal',
							#['mean', 'var', 'sd'],
							#{ 'mean' -> new UncertMlArgument('mean', 'rVal'),
								'var' -> new UncertMlArgument('variance', 'prVal'),
								'sd' -> new UncertMlArgument('stddev', 'prVal')
							}
		),
		'Poisson' -> new UncertMlMapping('PoissonDistribution', 'http://www.uncertml.org/distributions/poisson',
							#['lambda'],
							#{ 'lambda' -> new UncertMlArgument('rate', 'prVal')
							}
		),
		'Bernoulli' -> new UncertMlMapping('BernoulliDistribution', 'http://www.uncertml.org/distributions/poisson',
							#['category', 'probability'],
							#{ 
								'category' -> new UncertMlArgument('name', 'ref'),
								'probability' -> new UncertMlArgument('probability', 'pVal')
							}
		),
		'Binomial' -> new UncertMlMapping('BinomialDistribution', 'http://www.uncertml.org/distributions/poisson',
							#['numberOfTrials', 'probabilityOfSuccess'],
							#{ 'numberOfTrials' -> new UncertMlArgument('numberOfTrials', 'nVal'),
								'probabilityOfSuccess' -> new UncertMlArgument('probabilityOfSuccess', 'pVal')
							}
		)
	}

	def writeUncertmlArg(UncertMlArgument defn, Expression argExpression){
		if(argExpression != null){
			'''
			<«defn.name»>
				«switch(argExpression){
					IntegerLiteral,
					RealLiteral:
						'''
						<«defn.typeStr»>«argExpression.convertToString»</«defn.typeStr»>
						'''
					SymbolReference:
						'''
						<var varId="«argExpression.convertToString»"/>
						'''
					default: '''<!--- ERROR! --->'''
				}»
			</«defn.name»>
			'''
		}
	}

	def writeUncertmlDist(SymbolReference it){
		val mapping = pharmMLMapping.get(func)
		// assume checked that named argument
		val nargs = argList as NamedFuncArguments
		'''
			<«mapping.name» xmlns="http://www.uncertml.org/3.0" definition="«mapping.definition»">
				«FOR arg : mapping.argOrder»
					«IF nargs.getArgumentExpression(arg) != null && mapping.argMapping.containsKey(arg)»
						«mapping.argMapping.get(arg).writeUncertmlArg(nargs.getArgumentExpression(arg))»
					«ENDIF»
				«ENDFOR»
			</«mapping.name»>
		''' 
	}
	
	public def printDiscreteDistribution(SymbolReference distnDef){
		val typeName = distnDef.func;
		switch(typeName){
			case "Bernoulli": distnDef.printBernoulliDistn
			case "Binomial": distnDef.printBinomialDistn
			default: ''''''
		}
	}

	public def printBernoulliDistn(SymbolReference randomList){
		val mapping = pharmMLMapping.get(randomList.func)
//		var typeName = randomList.func;
//		val recognizedArgs = distribution_attrs.get(typeName);
//		val attr = recognizedArgs.get(DistributionValidator::attr_p.name)
//		val expr = randomList.arguments.namedArguments.arguments.get(0)
		val catArg = 'category'
		val probArg = 'probability'
		val category = randomList.getArgumentExpression(catArg)
		val expr = randomList.getArgumentExpression(probArg)
		'''
			<BernoulliDistribution xmlns="http://www.uncertml.org/3.0" definition="http://www.uncertml.org/3.0">
				<categoryProb definition="">
					<name>«category.convertToString»</name>
					«mapping.argMapping.get(probArg).writeUncertmlArg(expr)»
				</categoryProb>
			</BernoulliDistribution>
		'''
	}

	public def printBinomialDistn(SymbolReference randomList){
		val mapping = pharmMLMapping.get(randomList.func)
//		var typeName = randomList.func;
//		val recognizedArgs = distribution_attrs.get(typeName);
//		val numTrials = randomList.arguments.getAttributeExpression(DistributionValidator::attr_numberOfTrials.name);
//		val probs = randomList.arguments.getAttributeExpression(DistributionValidator::attr_probabilityOfSuccess.name);
		val numTrials = randomList.getArgumentExpression('numberOfTrials')
		val trialsArg = 'numberOfTrials'
		val probs = randomList.getArgumentExpression('probabilityOfSuccess')
		val probArg = 'probabilityOfSuccess'
		'''
			<BinomialDistribution xmlns="http://www.uncertml.org/3.0" definition="http://www.uncertml.org/3.0">
				«mapping.argMapping.get(trialsArg).writeUncertmlArg(numTrials)»
				«mapping.argMapping.get(probArg).writeUncertmlArg(probs)»
			</BinomialDistribution>
		'''
		}
	
}