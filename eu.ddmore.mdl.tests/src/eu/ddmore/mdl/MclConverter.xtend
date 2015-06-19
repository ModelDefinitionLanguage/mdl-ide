package eu.ddmore.mdl

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EstimateRange
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NumberLiteral
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.VectorExpression
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenClause
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments

public class MclConverter {
	
	def static dispatch String getString(Expression exp){
		getString(exp)
	}
	
	def static dispatch String getString(OrExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def static dispatch String getString(AndExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def static dispatch String getString(EqualityExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def static dispatch String getString(RelationalExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def static dispatch String getString(AdditiveExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def static dispatch String getString(MultiplicativeExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def static dispatch String getString(UnaryExpression exp){
		(exp.feature ?: "") + exp.operand?.getString
	}
	
	def static dispatch String getString(ParExpression exp)'''
		(«exp.expr.getString»)'''
		
	def static dispatch String getString(SymbolReference exp){
		exp.ref.name
	}
	
	def static dispatch String getString(BuiltinFunctionCall exp)'''
		«exp.func»(«exp.argList.getString»)'''
	
	def static dispatch String getString(UnnamedFuncArguments exp)'''
		«FOR arg: exp.args SEPARATOR ','»«arg.getString»«ENDFOR»'''
	
	def static dispatch String getString(EstimateRange exp)'''
		«IF exp.exclusiveLow»(«ELSE»[«ENDIF» «exp.low?:''», «exp.initial», «exp.high?:''» «IF exp.exclusiveHigh»)«ELSE»]«ENDIF»
	'''
	
	def static dispatch String getString(WhenExpression exp)'''
		«FOR w : exp.when SEPARATOR ',\n'»«w.getString»«ENDFOR»«IF exp.other != null»,«ENDIF»
		«IF exp.other!=null»«exp.other.getString» otherwise«ENDIF»'''
	
	def static dispatch String getString(WhenClause exp)'''
		(«exp.value.getString») when «exp.cond.getString»'''

	
	def static dispatch String getString(BooleanLiteral exp){
		if(exp.isTrue)  "true" else "false"
	}
	
	def static dispatch String getString(NumberLiteral exp){
		exp.value
	}
	
	def static dispatch String getString(StringLiteral exp)'''
	"«exp.value»"'''
	
	def static dispatch String getString(VectorLiteral exp)'''
		[«exp.expression?.getString»]'''
	
	
	def static dispatch String getString(VectorExpression exp)'''
		«FOR e : exp.expressions SEPARATOR ','»
			«e.getString»«ENDFOR»'''
	
}