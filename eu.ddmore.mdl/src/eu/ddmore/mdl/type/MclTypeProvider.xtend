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
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.validation.BuiltinFunctionProvider
import eu.ddmore.mdl.validation.ListDefinitionProvider
import java.util.Map
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.mdl.type.MclTypeProvider.*

public class MclTypeProvider {

	extension BuiltinFunctionProvider typeProvider = new BuiltinFunctionProvider
	extension ListDefinitionProvider listProvider = new ListDefinitionProvider
	

	enum PrimitiveType {
		Int, String, Real, Boolean, Pdf, Enum, EnumValue, List, Undefined
	}
	enum TypeProperty{
		Deriv, Estimate, Vector, None
	}
	
	@Data
	static abstract class TypeInfo{
		def abstract PrimitiveType getTheType()
		def abstract TypeProperty getTypeProp()
		def abstract boolean isReference()
		def abstract boolean isCompatible(TypeInfo otherType)
		def abstract boolean isCompatibleElement(TypeInfo otherType)
		def abstract TypeInfo markReference()
		def abstract String getTypeName()
		def abstract TypeInfo makeVector()
	} 
	
	@Data @FinalFieldsConstructor
	static class PrimitiveTypeInfo extends TypeInfo{
		PrimitiveType theType
		TypeProperty typeProp
		boolean reference
		
		new(PrimitiveType theType, TypeProperty typeProp){
			this(theType, typeProp, false)
		}
		
		override isCompatible(TypeInfo otherType){
			compatibleTypes.get(this.theType).contains(otherType.theType)
				&& compatibleTypeProps.get(this.typeProp).contains(otherType.typeProp)
		}
		
		override isCompatibleElement(TypeInfo elementType){
			if(this.typeProp != TypeProperty.Vector) throw new IllegalArgumentException("vectorType must have a vector property type")
			
			if(elementType.typeProp != TypeProperty.Vector)
				compatibleTypes.get(this.theType).contains(elementType.theType)
			else false		
		}
		
		override getTypeName(){
			(if(typeProp != TypeProperty.None) typeProp + ':' else '') + theType.toString
		}
		
		override markReference(){
			new PrimitiveTypeInfo(theType, typeProp, true);
		}
		
		override makeVector(){
			new PrimitiveTypeInfo(theType, TypeProperty.Vector, reference);
		}
	}

	@Data @FinalFieldsConstructor
	static class EnumTypeInfo extends PrimitiveTypeInfo{
		String enumName
		
		new(String name){
			this(name, false)
		}
		
		new(String name, boolean isRef){
			super(PrimitiveType.Enum, TypeProperty.None, isRef)
			enumName = name
		}
		
		new(String name, TypeProperty typeProp, boolean isRef){
			super(PrimitiveType.Enum, typeProp, isRef)
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
			(if(this.getTypeProp != TypeProperty.None) this.getTypeProp + ':' else '') + "Enum:" + enumName
		}
		
		override markReference(){
			new EnumTypeInfo (enumName, true)
		}
		
		override makeVector(){
			new EnumTypeInfo(enumName, TypeProperty.Vector, isReference);
		}
		
	}

//	@Data @FinalFieldsConstructor
//	static class VectorTypeInfo extends PrimitiveTypeInfo{
//		new(PrimitiveType type){
//			super(type, TypeProperty.Vector, false)
//		}
//		
//		new(PrimitiveType type, boolean isRef){
//			super(type, TypeProperty.Vector, isRef)
//		}
//		
//		override isCompatible(TypeInfo otherType){
//			switch(otherType){
//				VectorTypeInfo:
//					this.theType
//				default:
//					false 
//			}
//		}
//		
//		override getTypeName(){
//			"Enum:" + enumName
//		}
//		
//		override markReference(){
//			new EnumTypeInfo (enumName, true)
//		}
//	}

	@Data @FinalFieldsConstructor
	static class ListTypeInfo extends TypeInfo{
		String name
		PrimitiveType apparentType
		TypeProperty typeProp
		boolean reference
		
		new(String name, PrimitiveType appType){
			this(name, appType, TypeProperty.None, false)
		}
		
		new(String name, PrimitiveType appType, TypeProperty typeProp){
			this(name, appType, typeProp, false)
		}
		
		new(String name, PrimitiveType appType, boolean isRef){
			this(name, appType, TypeProperty.None, isRef)
		}
		
		override isCompatible(TypeInfo otherType){
			switch(otherType){
				ListTypeInfo:
					this.apparentType == otherType.theType
				default:
					false 
			}
		}
		
		override isCompatibleElement(TypeInfo elementType){
			if(this.typeProp != TypeProperty.Vector) throw new IllegalArgumentException("vectorType must have a vector property type")
			
			if(elementType.typeProp != TypeProperty.Vector)
				this.isCompatible(elementType)
			else false		
		}
		
		override getTheType(){
			apparentType
		}
		
		override getTypeProp(){
			typeProp
		}
		
		override getTypeName(){
			(if(typeProp != TypeProperty.None) typeProp + ':' else '') + "List:" + name
		}
		
		override markReference(){
			new ListTypeInfo(name, apparentType, typeProp, true)
		}

		override makeVector(){
			new ListTypeInfo(name, apparentType, TypeProperty.Vector, reference);
		}
	}
	


	public static val UNDEFINED_TYPE = new PrimitiveTypeInfo(PrimitiveType.Undefined, TypeProperty.None)
	public static val INT_TYPE = new PrimitiveTypeInfo(PrimitiveType.Int, TypeProperty.None)
	public static val BOOLEAN_TYPE = new PrimitiveTypeInfo(PrimitiveType.Boolean, TypeProperty.None)
	public static val REAL_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real, TypeProperty.None)
//	public static val DERIV_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real, TypeProperty.Deriv)
//	public static val ENUM_TYPE = new PrimitiveTypeInfo(PrimitiveType.Enum, TypeProperty.None)
	public static val STRING_TYPE = new PrimitiveTypeInfo(PrimitiveType.String, TypeProperty.None)
	public static val BOOL_TYPE = new PrimitiveTypeInfo(PrimitiveType.Boolean, TypeProperty.None)
	public static val PDF_TYPE = new PrimitiveTypeInfo(PrimitiveType.Pdf, TypeProperty.None)
	public static val REAL_VECTOR_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real, TypeProperty.Vector)
	public static val LIST_TYPE =  new PrimitiveTypeInfo(PrimitiveType.List, TypeProperty.None)
	
	static val Map<PrimitiveType, Set<PrimitiveType>> compatibleTypes = #{
		PrimitiveType.Real -> #{ PrimitiveType.Real, PrimitiveType.Int },
		PrimitiveType.Int -> #{ PrimitiveType.Int },
		PrimitiveType.String -> #{ PrimitiveType.String },
		PrimitiveType.Boolean -> #{ PrimitiveType.Boolean },
		PrimitiveType.Pdf -> #{ PrimitiveType.Pdf },
//		PrimitiveType.Enum -> #{ PrimitiveType.EnumValue, PrimitiveType.Enum },
//		PrimitiveType.EnumValue -> #{ PrimitiveType.EnumValue, PrimitiveType.Enum },
//		PrimitiveType.List -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.List },
		PrimitiveType.Undefined -> #{  }
	}
	
	static val Map<TypeProperty, Set<TypeProperty>> compatibleTypeProps = #{
		TypeProperty.None -> #{ TypeProperty.None, TypeProperty.Estimate, TypeProperty.Deriv },
		TypeProperty.Estimate -> #{ TypeProperty.None, TypeProperty.Estimate, TypeProperty.Deriv },
		TypeProperty.Deriv -> #{ TypeProperty.None, TypeProperty.Estimate, TypeProperty.Deriv },
		TypeProperty.Vector -> #{ TypeProperty.Vector }
	}
	
	static val ep = MdlPackage::eINSTANCE 
	
	static val typeTable = #{
		ep.realLiteral -> REAL_TYPE,
		ep.integerLiteral -> INT_TYPE,
		ep.stringLiteral -> STRING_TYPE,
		ep.booleanLiteral -> BOOL_TYPE,
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
		
		ep.estimateRange -> new PrimitiveTypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		ep.limitDefn -> new PrimitiveTypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		
//		ep.equationDefinition -> REAL_TYPE,
		ep.transformedDefinition -> REAL_TYPE,
		ep.randomVariableDefinition -> REAL_TYPE,
		ep.forwardDeclaration -> REAL_TYPE
//		ep.enumerationDefinition -> ENUM_TYPE,
//		ep.listDefinition -> LIST_TYPE
	}
	
	// returns the richest type if they are different or null if they are the same
	// of the types are incompatible.
	def getRichestPromotableType(TypeInfo refType, TypeInfo otherType){
		if(refType.theType == PrimitiveType.Real && otherType.theType == PrimitiveType.Int)
			refType
		else if(refType.theType == PrimitiveType.Int && otherType.theType == PrimitiveType.Real)
			otherType
		else null
	}
	
	def getTypeForArray(VectorLiteral vl){
		// first type determines array type unless one of the other elements is a type
		// that it could be promoted to. For example if the first element is an int
		// and a later type is a Real the the type for the vector as a whole is a Real.
		var TypeInfo refType = null
		for(Expression e : vl.expressions){
			val exprType = e.typeFor
			// can't have a vector here
			if(exprType.typeProp != TypeProperty.Vector){
				if(refType == null)
					refType = exprType
				else{
					val promotedType = refType.getRichestPromotableType(exprType)
					if(promotedType != null && refType != promotedType)
						refType = promotedType
				}
			}
		}
		(refType ?: UNDEFINED_TYPE).makeVector
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
			VectorElement:
				e.element.head.typeFor
			VectorLiteral:
				if(e.expressions.isEmpty) MclTypeProvider.REAL_VECTOR_TYPE
				else e.typeForArray
			default:
				typeTable.get(e.eClass) ?: MclTypeProvider.UNDEFINED_TYPE
		}
	}

//	def dispatch PrimitiveTypeInfo typeFor(EquationDefinition exp){
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
				if(sd.isVector) MclTypeProvider.REAL_VECTOR_TYPE else REAL_TYPE
			ListDefinition:
				getTypeOfList(sd.list)
			TransformedDefinition,
			ForwardDeclaration,
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
		checkExpectedAndExpression(MclTypeProvider.REAL_VECTOR_TYPE, exp, errorLambda)
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