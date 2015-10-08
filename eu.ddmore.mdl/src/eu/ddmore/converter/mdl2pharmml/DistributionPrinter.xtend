package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.validation.BuiltinFunctionProvider
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class DistributionPrinter {
	
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	
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

	def writeUncertmlDist(BuiltinFunctionCall it){
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
	
	
}