package eu.ddmore.converter.mdl2pharmml

import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtend.lib.annotations.Data
import java.util.Map
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.validation.BuiltinFunctionProvider.NamedArgFuncDefn
import eu.ddmore.mdl.mdl.NamedFuncArguments
import java.util.List
import eu.ddmore.mdl.validation.BuiltinFunctionProvider
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.RealLiteral

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import eu.ddmore.mdl.mdl.SymbolReference

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
								'var' -> new UncertMlArgument('variance', 'rVal'),
								'sd' -> new UncertMlArgument('stddev', 'rVal')
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
						<«defn.typeStr»>
							«argExpression.convertToString»
						</«defn.typeStr»>
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