package eu.ddmore.mdl.type

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoryDefinition
import eu.ddmore.mdl.mdl.CategoryReference
import eu.ddmore.mdl.mdl.EnumerationDefinition
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
import eu.ddmore.mdl.validation.BuiltinFunctionProvider
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import java.util.Map
import java.util.Set
import org.eclipse.xtext.EcoreUtil2

public class MclTypeProvider {

	extension BuiltinFunctionProvider typeProvider = new BuiltinFunctionProvider
	

	enum PrimitiveType {
		Int, String, Real, Boolean, Vector, Pdf, Enum, EnumValue, List, Undefined
	}
	enum TypeProperty{
		Deriv, Estimate, None
	}
	
	@Data @FinalFieldsConstructor
	static class TypeInfo{
		PrimitiveType theType
		TypeProperty typeProp
		boolean isReference
		
		new(PrimitiveType theType, TypeProperty typeProp){
			this(theType, typeProp, false)
		}
		
		def isCompatible(TypeInfo otherType){
			compatibleTypes.get(this.theType).contains(otherType.theType)
		}
		
		def getTypeName(){
			theType.toString
		}
		
		def markReference(){
			new TypeInfo(theType, typeProp, true);
		}
	}

	@Data @FinalFieldsConstructor
	static class EnumTypeInfo extends TypeInfo{
		String enumName
		
		new(String name){
			this(name, false)
		}
		
		new(String name, boolean isRef){
			super(PrimitiveType.Enum, TypeProperty.None, isRef)
			enumName = name
		}
		
		override isCompatible(TypeInfo otherType){
			switch(otherType){
				EnumTypeInfo:
					this.enumName == otherType.enumName
				default:
					false 
			}
		}
		
		override getTypeName(){
			"Enum:" + enumName
		}
		
		override markReference(){
			new EnumTypeInfo (enumName, true)
		}
	}

	public static val UNDEFINED_TYPE = new TypeInfo(PrimitiveType.Undefined, TypeProperty.None)
	public static val INT_TYPE = new TypeInfo(PrimitiveType.Int, TypeProperty.None)
	public static val BOOLEAN_TYPE = new TypeInfo(PrimitiveType.Boolean, TypeProperty.None)
	public static val REAL_TYPE = new TypeInfo(PrimitiveType.Real, TypeProperty.None)
//	public static val DERIV_TYPE = new TypeInfo(PrimitiveType.Real, TypeProperty.Deriv)
//	public static val ENUM_TYPE = new TypeInfo(PrimitiveType.Enum, TypeProperty.None)
	public static val STRING_TYPE = new TypeInfo(PrimitiveType.String, TypeProperty.None)
	public static val BOOL_TYPE = new TypeInfo(PrimitiveType.Boolean, TypeProperty.None)
	public static val PDF_TYPE = new TypeInfo(PrimitiveType.Pdf, TypeProperty.None)
	public static val VECTOR_TYPE = new TypeInfo(PrimitiveType.Vector, TypeProperty.None)
	public static val LIST_TYPE =  new TypeInfo(PrimitiveType.List, TypeProperty.None)
	
	static val Map<PrimitiveType, Set<PrimitiveType>> compatibleTypes = #{
		PrimitiveType.Real -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.List },
		PrimitiveType.Int -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.List },
		PrimitiveType.String -> #{ PrimitiveType.String },
		PrimitiveType.Vector -> #{ PrimitiveType.Vector },
		PrimitiveType.Boolean -> #{ PrimitiveType.Boolean },
		PrimitiveType.Pdf -> #{ PrimitiveType.Pdf },
//		PrimitiveType.Enum -> #{ PrimitiveType.EnumValue, PrimitiveType.Enum },
//		PrimitiveType.EnumValue -> #{ PrimitiveType.EnumValue, PrimitiveType.Enum },
		PrimitiveType.List -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.List },
		PrimitiveType.Undefined -> #{  }
	}
	
	static val ep = MdlPackage::eINSTANCE 
	
	static val typeTable = #{
		ep.realLiteral -> REAL_TYPE,
		ep.integerLiteral -> INT_TYPE,
		ep.stringLiteral -> STRING_TYPE,
		ep.booleanLiteral -> BOOL_TYPE,
		ep.vectorLiteral -> VECTOR_TYPE,
		ep.whenExpression -> REAL_TYPE,
		ep.whenClause -> REAL_TYPE,
		ep.relationalExpression -> BOOL_TYPE,
		ep.equalityExpression -> BOOL_TYPE,
		ep.andExpression -> BOOL_TYPE,
		ep.orExpression -> BOOL_TYPE,
		ep.additiveExpression -> REAL_TYPE,
		ep.multiplicativeExpression -> REAL_TYPE,
//		ep.categoryDefinition -> ENUM_TYPE,
//		ep.derivativeDefinition -> DERIV_TYPE,
		
		ep.estimateRange -> new TypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		ep.limitDefn -> new TypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		
//		ep.equationDefinition -> REAL_TYPE,
		ep.transformedDefinition -> REAL_TYPE,
		ep.randomVariableDefinition -> REAL_TYPE,
		ep.forwardDeclaration -> REAL_TYPE,
//		ep.enumerationDefinition -> ENUM_TYPE,
		ep.listDefinition -> LIST_TYPE
	}
	
	def dispatch TypeInfo typeFor(Expression e){
		switch(e){
			SymbolReference:
				e.ref.typeFor.markReference
			CategoryReference:
				e.ref.typeFor.markReference
			ParExpression: e.expr.typeFor
			BuiltinFunctionCall:
				e.functionType
			default:
				typeTable.get(e.eClass) ?: MclTypeProvider.UNDEFINED_TYPE
		}
	}

//	def dispatch TypeInfo typeFor(EquationDefinition exp){
//		if(exp.isVector){
//			VECTOR_TYPE
//		}
//		else{
//			REAL_TYPE
//		}
//	}

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
			EquationDefinition:
				if(sd.isVector) VECTOR_TYPE else REAL_TYPE
			TransformedDefinition,
			ForwardDeclaration,
			ListDefinition,
//			DerivativeDefinition,
			RandomVariableDefinition: typeTable.get(sd.eClass)
			EnumerationDefinition:
				new EnumTypeInfo(sd.name)
			default:
				UNDEFINED_TYPE
		}
	}
	
	def dispatch TypeInfo typeFor(CategoryDefinition sd){
//		return new EnumTypeInfo(exp.name)
		switch(sd){
			CategoryDefinition:{
				val enumDefn = EcoreUtil2.getContainerOfType(sd.eContainer, SymbolDefinition)
				new EnumTypeInfo(enumDefn.name)
			}
			default:
				UNDEFINED_TYPE
		}
	}
	
	def checkExpectedBoolean(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(BOOLEAN_TYPE, exp, errorLambda)
	}
	
	def checkExpectedReal(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(REAL_TYPE, exp, errorLambda)
	}
	
	def checkExpectedVector(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(VECTOR_TYPE, exp, errorLambda)
	}
	
	def checkExpectedIntl(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(INT_TYPE, exp, errorLambda)
	}
	
	def checkExpectedPdf(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(PDF_TYPE, exp, errorLambda)
	}

	def checkExpectedRealTransform(String transform, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = transform.transformFunctionType
		val expectedType = REAL_TYPE
		if(actualType != expectedType)
			errorLambda.apply(expectedType, actualType ?: UNDEFINED_TYPE)
	}
	
	def checkExpectedString(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(STRING_TYPE, exp, errorLambda)
	}
	
	def checkRelationalOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
			(TypeInfo, TypeInfo) => void rightErrorLambda){
		val lhsType = lhs?.typeFor ?: UNDEFINED_TYPE
//		val rhsType = rhs?.typeFor?.theType ?: UNDEFINED_TYPE.theType
		if(lhsType.theType == PrimitiveType.Enum || lhsType.theType == PrimitiveType.EnumValue){
			checkExpectedAndExpression(lhsType, lhs, leftErrorLambda)
			checkExpectedAndExpression(lhsType, rhs, rightErrorLambda)
		}
		else{
			checkExpectedAndExpression(REAL_TYPE, lhs, leftErrorLambda)
			checkExpectedAndExpression(REAL_TYPE, rhs, rightErrorLambda)
		}
		
//		if(lhsType != REAL_TYPE.theType && lhsType != ENUM_TYPE.theType)
//			leftErrorLambda.apply(PrimitiveType.Real, lhsType)
//		if(rhsType != REAL_TYPE.theType && rhsType != ENUM_TYPE.theType)
//			rightErrorLambda.apply(PrimitiveType.Real, rhsType)
//		if(rhsType != lhsType)
//			leftErrorLambda.apply(lhsType, rhsType)
	}

	def checkMathsOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
		(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedAndExpression(REAL_TYPE, lhs, leftErrorLambda)
		checkExpectedAndExpression(REAL_TYPE, rhs, rightErrorLambda)
	}

	def checkBoolOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedAndExpression(BOOLEAN_TYPE, lhs, leftErrorLambda)
		checkExpectedAndExpression(BOOLEAN_TYPE, rhs, rightErrorLambda)
	}

	def checkUnaryOp(String feature, Expression operand, (TypeInfo, TypeInfo) => void errorLambda){
		if(feature == '!') checkExpectedAndExpression(BOOLEAN_TYPE, operand, errorLambda)
		else checkExpectedAndExpression(REAL_TYPE, operand, errorLambda)
	}
	
	def dispatch void checkExpectedAndExpression(EnumTypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: UNDEFINED_TYPE
		if(!expectedType.isCompatible(actualType)){
			errorLambda.apply(expectedType, actualType)
		} 
	}
	
	def dispatch void checkExpectedAndExpression(TypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: UNDEFINED_TYPE
		if(!expectedType.isCompatible(actualType)){
			errorLambda.apply(expectedType, actualType)
		} 
	}

}