package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.ElifClause
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.FuncArguments
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenClause
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdl.mdl.MappingPair

public class MdlExpressionConverter extends ExpressionConverter {
	
    private final static MdlExpressionConverter INSTANCE = new MdlExpressionConverter()
    
    public def static String convertToString(Expression expr){
        INSTANCE.getString(expr)
    }
    
    public def static String convertToString(FuncArguments funcArgs) {
        INSTANCE.getString(funcArgs)
    }

	def dispatch String getString(CategoricalDefinitionExpr defn)'''
		withCategories {«FOR catValDefn : defn.categories SEPARATOR ', '»«catValDefn.getString»«ENDFOR»}'''

    def dispatch String getString(CategoryValueDefinition catValDefn) {
        catValDefn.name + if (catValDefn.mappedTo != null) " when " + catValDefn.mappedTo.getString else ""
    }
	
	override dispatch String getString(EnumExpression exp){
	  exp.enumValue + (if (exp.catDefn != null) " " + exp.catDefn.getString else "")
	}
	
	def dispatch String getString(BuiltinFunctionCall exp)'''
		«exp.func»(«exp.argList.getString»)'''

    def dispatch String getString(NamedFuncArguments exp)'''
        «FOR arg: exp.arguments SEPARATOR ', '»«arg.getArgumentName»=«arg.getExpression.getString»«ENDFOR»'''
	
	def dispatch String getString(UnnamedFuncArguments exp)'''
		«FOR arg: exp.args SEPARATOR ', '»«arg.argument.getString»«ENDFOR»'''
	
	def dispatch String getString(WhenExpression exp)'''
		«FOR w : exp.when SEPARATOR '\nelse'»«w.getString»«ENDFOR»
		«IF exp.other!=null»else «exp.other.getString»«ENDIF»'''
	
	def dispatch String getString(WhenClause exp)'''
		if («exp.cond.getString») then «exp.value.getString»'''
	
	def dispatch String getString(ElifClause exp)'''
		if («exp.cond.getString») then «exp.value.getString»'''

	def dispatch String getString(VectorLiteral exp)'''
		[«FOR e : exp.expressions SEPARATOR ','»«e.getString»«ENDFOR»]'''
	
	def dispatch String getString(VectorElement exp)'''
		«exp.element.head.getString»'''
		
//	def static dispatch String getString(VectorContent exp)'''
//		«FOR e : exp.expressions SEPARATOR ','»
//			«e.getString»«ENDFOR»'''

    override dispatch String getString(MappingPair mp)'''
        «mp.leftOperand.getString» in «mp.srcColumn.getString» as «mp.rightOperand.getString»'''
	
}