package eu.ddmore.mdl.type

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoryDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ForwardDeclaration
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnaryExpression
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import eu.ddmore.mdl.mdl.CategoryReference
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.validation.BuiltinFunctionProvider

public class MclTypeProvider {

	extension BuiltinFunctionProvider typeProvider = new BuiltinFunctionProvider
	

	enum PrimitiveType {
		String, Real, Boolean, VarLevel, Pdf, Enum, Undefined
	}
	enum TypeProperty{
		Deriv, Vector, DataCol, Estimate, None
	}
	
	@Data @FinalFieldsConstructor
	static class TypeInfo{
		PrimitiveType theType
		TypeProperty typeProp
		boolean isReference
		
		new(PrimitiveType theType, TypeProperty typeProp){
			this.theType = theType
			this.typeProp = typeProp
			this.isReference = true
		}
	}

	def TypeInfo markReference(TypeInfo t){
		new TypeInfo(t.theType, t.typeProp, true)
	}

	public static val UNDEFINED_TYPE = new TypeInfo(PrimitiveType.Undefined, TypeProperty.None)
	public static val REAL_TYPE = new TypeInfo(PrimitiveType.Real, TypeProperty.None)
	public static val ENUM_TYPE = new TypeInfo(PrimitiveType.Enum, TypeProperty.None)
	public static val STRING_TYPE = new TypeInfo(PrimitiveType.String, TypeProperty.None)
	public static val BOOL_TYPE = new TypeInfo(PrimitiveType.Boolean, TypeProperty.None)
	public static val PDF_TYPE = new TypeInfo(PrimitiveType.Pdf, TypeProperty.None)
	public static val VAR_LEVEL_TYPE = new TypeInfo(PrimitiveType.VarLevel, TypeProperty.None)
	public static val VECTOR_TYPE = new TypeInfo(PrimitiveType.Real, TypeProperty.Vector)
	
	
	static val ep = MdlPackage::eINSTANCE 
	
	static val typeTable = #{
		ep.numberLiteral -> REAL_TYPE,
		ep.stringLiteral -> STRING_TYPE,
		ep.booleanLiteral -> BOOL_TYPE,
		ep.vectorLiteral -> new TypeInfo(PrimitiveType.Real, TypeProperty.Vector),
		ep.whenExpression -> REAL_TYPE,
		ep.whenClause -> REAL_TYPE,
		ep.relationalExpression -> BOOL_TYPE,
		ep.equalityExpression -> BOOL_TYPE,
		ep.andExpression -> BOOL_TYPE,
		ep.orExpression -> BOOL_TYPE,
		ep.additiveExpression -> REAL_TYPE,
		ep.multiplicativeExpression -> REAL_TYPE,
		ep.categoryDefinition -> ENUM_TYPE, // @TODO: Do category typing properly
		
		ep.estimateRange -> new TypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		ep.limitDefn -> new TypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		
		ep.equationDefinition -> REAL_TYPE,
		ep.transformedDefinition -> REAL_TYPE,
		ep.randomVariableDefinition -> REAL_TYPE,
		ep.forwardDeclaration -> REAL_TYPE,
		ep.enumerationDefinition -> ENUM_TYPE,
		ep.listDefinition -> REAL_TYPE // @TODO: Do list typing properly
	}
	
	
//	static val functionTypeTable = #{
//		'ln' -> REAL_TYPE,
//		'abs' -> REAL_TYPE,
//		'exp' -> REAL_TYPE,
//		'Normal' -> PDF_TYPE,
//		'linear' -> REAL_TYPE,
//		'general' -> REAL_TYPE,
//		'combinedError1' -> REAL_TYPE,
//		'combinedError2' -> REAL_TYPE
//	}
	
	
	def dispatch TypeInfo typeFor(Expression e){
		switch(e){
			SymbolReference: e.ref.typeFor.markReference
			CategoryReference:
				e.ref.typeFor.markReference
			ParExpression: e.expr.typeFor
			BuiltinFunctionCall:
				e.func.functionType
			default:
				typeTable.get(e.eClass) ?: MclTypeProvider.UNDEFINED_TYPE
		}
	}
	
	def dispatch TypeInfo typeFor(UnaryExpression exp){
		switch(exp.feature){
			case('!'): BOOL_TYPE
			case('+'),
			case('-'): REAL_TYPE
			default:
				throw new RuntimeException("Unrecognised unary operator.")
		}
	}
	
	def dispatch TypeInfo typeFor(SymbolDefinition sd){
		switch(sd){
			EquationDefinition,
			TransformedDefinition,
			ForwardDeclaration,
			ListDefinition,
			CategoryDefinition,
			EnumerationDefinition,
			RandomVariableDefinition: typeTable.get(sd.eClass)
			default:
				UNDEFINED_TYPE
		}
	}
	
	def dispatch TypeInfo typeFor(CategoryDefinition sd){
		switch(sd){
			CategoryDefinition: typeTable.get(sd.eClass)
			default:
				UNDEFINED_TYPE
		}
	}
	
	def checkExpectedBoolean(Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		checkExpectedAndExpression(typeTable.get(MdlPackage::eINSTANCE.booleanLiteral).theType, exp, errorLambda)
	}
	
	def checkExpectedReal(Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		checkExpectedAndExpression(typeTable.get(MdlPackage::eINSTANCE.numberLiteral).theType, exp, errorLambda)
	}
	
	def checkExpectedPdf(Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		checkExpectedAndExpression(PDF_TYPE.theType, exp, errorLambda)
	}

	def checkExpectedRealTransform(String transform, (PrimitiveType, PrimitiveType) => void errorLambda){
		val actualType = transform.functionType.theType
		val expectedType = PrimitiveType.Real
		if(actualType != expectedType)
			errorLambda.apply(expectedType, actualType ?: UNDEFINED_TYPE.theType)
	}
	
	def checkExpectedString(Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		checkExpectedAndExpression(typeTable.get(MdlPackage::eINSTANCE.stringLiteral).theType, exp, errorLambda)
	}
	
	def checkRelationalOp(Expression lhs, Expression rhs, (PrimitiveType, PrimitiveType) => void leftErrorLambda,
			(PrimitiveType, PrimitiveType) => void rightErrorLambda){
		val lhsType = lhs?.typeFor?.theType ?: UNDEFINED_TYPE.theType
		val rhsType = rhs?.typeFor?.theType ?: UNDEFINED_TYPE.theType
		if(lhsType != REAL_TYPE.theType && lhsType != ENUM_TYPE.theType)
			leftErrorLambda.apply(PrimitiveType.Real, lhsType)
		if(rhsType != REAL_TYPE.theType && rhsType != ENUM_TYPE.theType)
			rightErrorLambda.apply(PrimitiveType.Real, rhsType)
		if(rhsType != lhsType)
			leftErrorLambda.apply(lhsType, rhsType)
	}

	def checkMathsOp(Expression lhs, Expression rhs, (PrimitiveType, PrimitiveType) => void leftErrorLambda,
		(PrimitiveType, PrimitiveType) => void rightErrorLambda){
		checkExpectedAndExpression(PrimitiveType.Real, lhs, leftErrorLambda)
		checkExpectedAndExpression(PrimitiveType.Real, rhs, rightErrorLambda)
	}

	def checkBoolOp(Expression lhs, Expression rhs, (PrimitiveType, PrimitiveType) => void leftErrorLambda,
				(PrimitiveType, PrimitiveType) => void rightErrorLambda){
		checkExpectedAndExpression(PrimitiveType.Boolean, lhs, leftErrorLambda)
		checkExpectedAndExpression(PrimitiveType.Boolean, rhs, rightErrorLambda)
	}

	def checkUnaryOp(String feature, Expression operand, (PrimitiveType, PrimitiveType) => void errorLambda){
		if(feature == '!') checkExpectedAndExpression(PrimitiveType.Boolean, operand, errorLambda)
		else checkExpectedAndExpression(PrimitiveType.Real, operand, errorLambda)
	}
	
	
	def void checkExpectedAndExpression(PrimitiveType expectedType, Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		val actualType = exp?.typeFor?.theType ?: UNDEFINED_TYPE.theType
		if(actualType != expectedType)
			errorLambda.apply(expectedType, actualType ?: PrimitiveType.Undefined)
	}
	
	
}