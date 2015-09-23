package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.ElifClause
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenClause
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.ConstantLiteral
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.CategoryValueDefinition

public class ExpressionConverter {
	
	public def static String convertToString(Expression it){
		getString
	}

	def static dispatch String getString(CategoricalDefinitionExpr defn)'''
		withCategories {«FOR catValDefn : defn.categories SEPARATOR ', '»«catValDefn.getString»«ENDFOR»}'''

    def static dispatch String getString(CategoryValueDefinition catValDefn) {
        catValDefn.name + if (catValDefn.mappedTo != null) " when " + catValDefn.mappedTo.getString else ""
    }

	def static dispatch String getString(Expression exp){
		throw new IllegalStateException("Bug. Concrete dispatch method missing: " + exp.class.simpleName );
	}
	
	def static dispatch String getString(OrExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def static dispatch String getString(EnumExpression exp){
	  exp.enumValue + (if (exp.catDefn != null) " " + exp.catDefn.getString else "")
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

    def static dispatch String getString(NamedFuncArguments exp)'''
        «FOR arg: exp.arguments SEPARATOR ', '»«arg.getArgumentName»=«arg.getExpression.getString»«ENDFOR»'''
	
	def static dispatch String getString(UnnamedFuncArguments exp)'''
		«FOR arg: exp.args SEPARATOR ', '»«arg.getString»«ENDFOR»'''
	
	def static dispatch String getString(UnnamedArgument exp)'''
		«exp.argument.getString»'''
	
//	def static dispatch String getString(EstimateRange exp)'''
//		«IF !exp.isFixed»(«ENDIF»«exp.initial» «exp.limit?.getLimitString(exp.initial) ?: exp.initial»«IF !exp.isFixed»)«ENDIF»'''
	
//	def static String getLimitString(LimitDefn limit, Expression initial)'''
//		(«limit.low?:''», «initial.getString», «limit.high?:''»)'''
	
	def static dispatch String getString(WhenExpression exp)'''
		«FOR w : exp.when SEPARATOR '\nelse'»«w.getString»«ENDFOR»
		«IF exp.other!=null»else «exp.other.getString»«ENDIF»'''
	
	def static dispatch String getString(WhenClause exp)'''
		if («exp.cond.getString») then «exp.value.getString»'''

	
	def static dispatch String getString(ElifClause exp)'''
		if («exp.cond.getString») then «exp.value.getString»'''

	
	def static dispatch String getString(BooleanLiteral exp){
		if (exp.isTrue) "true" else "false"
	}
	
	def static dispatch String getString(RealLiteral exp){
		exp.value.toString
	}
	
	def static dispatch String getString(ConstantLiteral exp){
		exp.value
	}
	
	def static dispatch String getString(IntegerLiteral exp){
		exp.value.toString
	}
	
	def static dispatch String getString(StringLiteral exp)'''
	"«exp.value»"'''
	
	def static dispatch String getString(VectorLiteral exp)'''
		[«FOR e : exp.expressions SEPARATOR ','»«e.getString»«ENDFOR»]'''
	
	
	def static dispatch String getString(VectorElement exp)'''
		«exp.element.head.getString»'''
		

//	def static dispatch String getString(VectorContent exp)'''
//		«FOR e : exp.expressions SEPARATOR ','»
//			«e.getString»«ENDFOR»'''
	
}