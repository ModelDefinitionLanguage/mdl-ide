package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.CatValRefMapping
import eu.ddmore.mdl.mdl.CatValRefMappingExpression
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.ElifClause
import eu.ddmore.mdl.mdl.ElseClause
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.FuncArguments
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdl.mdl.IfExpression
import eu.ddmore.mdl.mdl.IfClause

public class MdlExpressionConverter extends ExpressionConverter {
	
    extension MdlUtils mclUtils = new MdlUtils
    
    private final static MdlExpressionConverter INSTANCE = new MdlExpressionConverter()
    
    public def static String convertToString(Expression expr){
        INSTANCE.getString(expr)
    }
    
    public def static String convertToString(FuncArguments funcArgs) {
        INSTANCE.getString(funcArgs)
    }

    /**
     * Override the method from the superclass so that strings are enclosed in double quotes when writing out MDL.
     */
    override dispatch String getString(StringLiteral exp)'''
	"«exp.value»"'''

    def dispatch String getString(CategoricalDefinitionExpr defn)'''
        withCategories {«FOR catValDefn : defn.categories SEPARATOR ', '»«catValDefn.getString»«ENDFOR»}'''

    def dispatch override String getString(CategoryValueDefinition catValDefn) {
        catValDefn.name + if (catValDefn.mappedTo != null) " when " + catValDefn.mappedTo.getString else ""
    }
    
    def dispatch String getString(CatValRefMappingExpression catValRefMappingExpr)'''
        {«FOR catValRefMapping : catValRefMappingExpr.attLists SEPARATOR ', '»«catValRefMappingExpr.getSymbolDefnFromCatValRef.name».«catValRefMapping.getString»«ENDFOR»}'''
    
    def dispatch String getString(CatValRefMapping catValRefMapping)'''
        «catValRefMapping.catRef.ref.getString» when «catValRefMapping.mappedTo.getString»'''
       
    def dispatch override String getString(CategoryValueReference catValRef)'''
        «catValRef.getSymbolDefnFromCatValRef.name».«catValRef.ref.getString»'''

	override dispatch String getString(EnumExpression exp){
        exp.enumValue + (if (exp.catDefn != null) " " + exp.catDefn.getString else "")
	}
	
//	def dispatch String getString(BuiltinFunctionCall exp)'''
//		«exp.func»(«exp.argList.getString»)'''

    def dispatch String getString(NamedFuncArguments exp)'''
        «FOR arg: exp.arguments SEPARATOR ', '»«arg.getString»«ENDFOR»'''
	
	def dispatch String getString(UnnamedFuncArguments exp)'''
		«FOR arg: exp.args SEPARATOR ', '»«arg.argument.getString»«ENDFOR»'''
	
	def dispatch String getString(IfExpression exp)'''
		«FOR w : exp.ifelseClause SEPARATOR ' else'»«w.getString»«ENDFOR»«IF exp.elseClause!=null» else «exp.elseClause.getString»«ENDIF»'''
	
	def dispatch String getString(IfClause exp)'''
		if («exp.cond.getString») then «exp.value.getString»'''
	
	def dispatch String getString(ElifClause exp)'''
		if («exp.cond.getString») then «exp.value.getString»'''

	def dispatch String getString(ElseClause exp)'''
		«exp.value.getString»'''

	def dispatch String getString(VectorLiteral exp)'''
		[«FOR e : exp.expressions SEPARATOR ','»«e.getString»«ENDFOR»]'''
	
    override dispatch String getString(SubListExpression expr)'''
        {«FOR c : expr.attributes SEPARATOR ', '»«c.getString»«ENDFOR»}'''

    override dispatch String getString(MappingExpression expr)'''
        {«FOR c : expr.attList SEPARATOR ', '»«c.getString»«ENDFOR»}'''

    override dispatch String getString(MappingPair mp)'''
        «mp.leftOperand.getString» in «mp.srcColumn.getString» as «mp.rightOperand.getString»'''
        
    def dispatch String getString(EnumPair mp)'''
        «mp.argumentName» is «mp.expression.getString»'''
	
}
