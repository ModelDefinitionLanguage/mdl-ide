package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoricalDefinition
import eu.ddmore.mdl.mdl.ElifClause
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EstimateRange
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.LimitDefn
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NumberLiteral
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorContent
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenClause
import eu.ddmore.mdl.mdl.WhenExpression

public class ExpressionConverter {
	
	public def static String convertToString(Expression it){
		getString
	}

	def static String convertToString(CategoricalDefinition defn)'''
		[«FOR c : defn.categories SEPARATOR ','»
			«(c as SymbolDefinition).name»«ENDFOR»]'''

	def static dispatch String getString(Expression exp){
		throw new IllegalStateException("Bug. Concrete dispatch method missing: " + exp.class.simpleName );
	}
	
	def static dispatch String getString(OrExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def static dispatch String getString(EnumExpression exp){
		exp.enumValue
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
		«IF !exp.isFixed»(«ENDIF»«exp.initial» «exp.limit?.getLimitString(exp.initial) ?: exp.initial»«IF !exp.isFixed»)«ENDIF»'''
	
	def static String getLimitString(LimitDefn limit, String initial)'''
		(«limit.low?:''», «initial», «limit.high?:''»)'''
	
	def static dispatch String getString(WhenExpression exp)'''
		«FOR w : exp.when SEPARATOR ',\n'»«w.getString»«ENDFOR»«IF exp.other != null»,«ENDIF»
		«IF exp.other!=null»«exp.other.getString» otherwise«ENDIF»'''
	
	def static dispatch String getString(WhenClause exp)'''
		(«exp.value.getString») when «exp.cond.getString»'''

	
	def static dispatch String getString(ElifClause exp)'''
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
	
	
	def static dispatch String getString(VectorContent exp)'''
		«FOR e : exp.expressions SEPARATOR ','»
			«e.getString»«ENDFOR»'''
	
}