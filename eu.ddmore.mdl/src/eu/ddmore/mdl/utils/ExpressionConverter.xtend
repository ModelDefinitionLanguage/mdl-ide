package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.ConstantLiteral
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.PowerExpression
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.VectorElement
import org.eclipse.emf.ecore.EObject

public class ExpressionConverter {

    private final static ExpressionConverter INSTANCE = new ExpressionConverter()
    
    public def static String convertToString(Expression expr) {
        INSTANCE.getString(expr)
    }

    public def static Integer convertToInteger(Expression it) {
        val strRep = INSTANCE.getString(it)
        Integer::valueOf(strRep)
    }

    def dispatch String getString(EObject eobj){
        throw new IllegalStateException("Bug. Concrete dispatch method missing: " + eobj.class.simpleName);
    }

//  def dispatch String getString(Expression exp){
//      throw new IllegalStateException("Bug. Concrete dispatch method missing: " + exp.class.simpleName);
//  }

    def dispatch String getString(EnumExpression exp){
        exp.enumValue
    }
	
	def dispatch String getString(OrExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def dispatch String getString(AndExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def dispatch String getString(EqualityExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def dispatch String getString(RelationalExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def dispatch String getString(AdditiveExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def dispatch String getString(MultiplicativeExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def dispatch String getString(PowerExpression exp){
		exp.leftOperand.getString + (exp.feature ?: "") + exp.rightOperand?.getString
	}
	
	def dispatch String getString(UnaryExpression exp){
		(exp.feature ?: "") + exp.operand?.getString
	}
	
	def dispatch String getString(ParExpression exp)'''
		(«exp.expr.getString»)'''
		
	def dispatch String getString(SymbolReference exp)'''
		«exp.ref.name»«IF exp.argList != null»(«exp.argList.getString»)«ENDIF»'''
	
//	def static dispatch String getString(BuiltinFunctionCall exp)'''
//		«exp.func»(«exp.argList.getString»)'''
//	
//	def static dispatch String getString(UnnamedFuncArguments exp)'''
//		«FOR arg: exp.args SEPARATOR ','»«arg.getString»«ENDFOR»'''
//	
//	def static dispatch String getString(UnnamedArgument exp)'''
//		«exp.argument.getString»'''
//	
//	def static dispatch String getString(EstimateRange exp)'''
//		«IF !exp.isFixed»(«ENDIF»«exp.initial» «exp.limit?.getLimitString(exp.initial) ?: exp.initial»«IF !exp.isFixed»)«ENDIF»'''
//	
//	def static String getLimitString(LimitDefn limit, Expression initial)'''
//		(«limit.low?:''», «initial.getString», «limit.high?:''»)'''
//	
//	def static dispatch String getString(WhenExpression exp)'''
//		«FOR w : exp.when SEPARATOR ',\n'»«w.getString»«ENDFOR»«IF exp.other != null»,«ENDIF»
//		«IF exp.other!=null»«exp.other.getString» otherwise«ENDIF»'''
//	
//	def static dispatch String getString(WhenClause exp)'''
//		(«exp.value.getString») when «exp.cond.getString»'''
//
//	
//	def static dispatch String getString(ElifClause exp)'''
//		(«exp.value.getString») when «exp.cond.getString»'''

	
	def dispatch String getString(BooleanLiteral exp){
		if(exp.isTrue)  "true" else "false"
	}
	
	def dispatch String getString(RealLiteral exp){
		exp.value.toString
	}
	
	def dispatch String getString(ConstantLiteral exp){
		exp.value
	}
	
	def dispatch String getString(IntegerLiteral exp){
		exp.value.toString
	}
	
	def dispatch String getString(StringLiteral exp)'''
		«exp.value»'''
	
//	def static dispatch String getString(VectorLiteral exp)'''
//		[«FOR e : exp.expressions SEPARATOR ','»«e.getString»«ENDFOR»]'''
//	
	
	def dispatch String getString(VectorElement exp)'''
		«exp.element.getString»'''
		
	def dispatch String getString(CategoryValueReference exp)'''
		«exp.ref.getString»'''
		
	def dispatch String getString(CategoryValueDefinition exp)'''
		«exp.name»'''
		

//	def static dispatch String getString(VectorContent exp)'''
//		«FOR e : exp.expressions SEPARATOR ','»
//			«e.getString»«ENDFOR»'''

    // TODO: Used?
    def dispatch String getString(SubListExpression expr)'''
        {«FOR c : expr.attributes SEPARATOR ','»
            «c.getString»«ENDFOR»}'''

    // TODO: Used?
    def dispatch String getString(MappingExpression expr)'''
        {«FOR c : expr.attList SEPARATOR ','»
            «c.getString»«ENDFOR»}'''
            
    // TODO: Used?
    def dispatch String getString(MappingPair mp)'''
        «mp.leftOperand.getString» col=«mp.srcColumn.getString» varRef=«mp.rightOperand.getString»'''
        
    def dispatch String getString(ValuePair mp)'''
        «mp.argumentName»=«mp.expression.getString»'''
	
}
