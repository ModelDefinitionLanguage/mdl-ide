package eu.ddmore.mdl.type

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
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
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.validation.BuiltinFunctionProvider
import eu.ddmore.mdl.validation.ListDefinitionProvider
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.mdl.type.MclTypeProvider.*
import java.util.ArrayList
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.validation.ListDefinitionProvider.AttributeDefn

public class MclTypeProvider {

	extension BuiltinFunctionProvider typeProvider = new BuiltinFunctionProvider
	extension ListDefinitionProvider listProvider = new ListDefinitionProvider
	

	enum PrimitiveType {
		Int, String, Real, Deriv, Boolean, Pdf, Enum, List, Mapping, Sublist, Vector, Reference, Undefined
	}
	
	@Data
	static abstract class TypeInfo{
		def abstract PrimitiveType getTheType()
		def abstract TypeInfo getUnderlyingType()
		def abstract boolean isVector()
		def abstract boolean isReference()
		def abstract boolean isCompatible(TypeInfo otherType)
		def abstract boolean isCompatibleElement(TypeInfo otherType)
		def abstract TypeInfo makeReference()
		def abstract String getTypeName()
		def abstract TypeInfo makeVector()
	} 
	
	@Data @FinalFieldsConstructor
	static class PrimitiveTypeInfo extends TypeInfo{
		PrimitiveType theType
		
		override boolean isCompatible(TypeInfo other){
			// use underlying type in case it is a reference 
			val otherType = other.underlyingType
			val compType = compatibleTypes?.get(this.theType)
			if(compType != null)
				// check the underlying type in case this is a reference or some other form of indirection
				compType.contains(otherType.theType)
			else false
		}
		
		override isCompatibleElement(TypeInfo elementType){
			// no vectors in this class so always false
			false		
		}
		
		override getUnderlyingType(){
			this
		}
		
		override getTypeName(){
			theType.toString
		}
		
		override makeReference(){
			new ReferenceTypeInfo(this);
		}
		
		override makeVector(){
			new VectorTypeInfo(this);
		}
		
		override isVector(){
			false
		}
		
		override isReference(){
			false
		}
	}

	@Data @FinalFieldsConstructor
	static class VectorTypeInfo extends TypeInfo{
		PrimitiveType theType
		TypeInfo elementType
		
		new(TypeInfo elementType){
			this(PrimitiveType.Vector, elementType)
		}
		
		override getUnderlyingType(){
			this
		}
		
		override boolean isCompatible(TypeInfo other){
			// use underlying type in case it is a reference 
			val otherType = other.underlyingType
			switch(otherType){
				// if both vectors then check type compatibility
				VectorTypeInfo: elementType.isCompatible(otherType.elementType)
				default: false
			}
		}
		
		override isCompatibleElement(TypeInfo otherElementType){
			this.elementType.isCompatible(otherElementType)
		}
		
		override getTypeName(){
			'vector:' + elementType.typeName
		}
		
		override makeReference(){
			new ReferenceTypeInfo(this);
		}
		
		override makeVector(){
			new VectorTypeInfo(theType, elementType);
		}
		
		override isVector(){
			true
		}
		
		override isReference(){
			false
		}
	}

	@Data @FinalFieldsConstructor
	static class ReferenceTypeInfo extends TypeInfo{
//		PrimitiveType theType
		TypeInfo elementType
		
//		new(TypeInfo elementType){
//			this(elementType)
////			this(PrimitiveType.Reference, elementType)
//		}
		
		override getUnderlyingType(){
			elementType
		}
		
		override getTheType(){
			elementType.theType
		}
		
		override boolean isCompatible(TypeInfo otherType){
			switch(otherType){
				// if both refs then check type compatibility otherwise ref -> non-ref is incompatible 
				ReferenceTypeInfo: elementType.isCompatible(otherType.elementType)
				default: false
			}
//			if(otherType.isVector){
//				elementType.isCompatible(otherType.elementType)				
//			}
//				compType.contains(otherType.theType) && this.vector == otherType.isVector
//					&& if(this.reference) otherType.isReference else true
//			else false
		}
		
		override isCompatibleElement(TypeInfo otherElementType){
			false
		}
		
		override getTypeName(){
			'ref:' + elementType.typeName
		}
		
		override makeReference(){
			this
		}
		
		override makeVector(){
			new VectorTypeInfo(this);
		}
		
		override isVector(){
			false
		}
		
		override isReference(){
			true
		}
	}

	@Data @FinalFieldsConstructor
	static class EnumTypeInfo extends PrimitiveTypeInfo{
		String enumName
		
		new(String name){
			super(PrimitiveType.Enum)
			enumName = name
		}
		
		override isCompatible(TypeInfo otherType){
			switch(otherType){
				EnumTypeInfo: this.enumName == otherType.enumName
				default: false 
			}
		}
		
		override getTypeName(){
			"Enum:" + enumName
		}
		
	}

	@Data @FinalFieldsConstructor
	static class BuiltinEnumTypeInfo extends EnumTypeInfo{
		List<String> expectedValues
		
//		new(String name, List<String> expectedValues){
//			super(name)
//			this.expectedValues = expectedValues
//		}
		
	}

	@Data @FinalFieldsConstructor
	static class ListTypeInfo extends TypeInfo{
		String name
		PrimitiveType apparentType
		
		override getUnderlyingType(){
			this
		}
		
		override isCompatible(TypeInfo other){
			// use underlying type in case it is a reference 
			val otherType = other.underlyingType
			switch(otherType){
				ListTypeInfo:  this.name == otherType.name
				default:
					false 
			}
		}
		
		override isCompatibleElement(TypeInfo elementType){
			false		
		}
		
		override getTheType(){
			apparentType
		}
		
		override getTypeName(){
			"List:" + name
		}
		
		override makeReference(){
			new ReferenceTypeInfo(this)
		}

		override makeVector(){
			new VectorTypeInfo(this);
		}
		
		override isVector(){
			false
		}
		
		override isReference(){
			false
		}
	}
	
	@Data @FinalFieldsConstructor
	static class SublistTypeInfo extends TypeInfo {
		String name
		PrimitiveType theType
		List<AttributeDefn> attributes
		
		new(String name, List<AttributeDefn> attributes){
			this(name, PrimitiveType.Sublist, new ArrayList<AttributeDefn>(attributes))
		}
		
		override getUnderlyingType(){
			this
		}
		
		override isCompatible(TypeInfo other){
			// use underlying type in case it is a reference 
			val otherType = other.underlyingType
			switch(otherType){
				SublistTypeInfo: this.name == otherType.name
				default:
					false 
			}
		}
		
		override isCompatibleElement(TypeInfo elementType){
			false		
		}
		
		override getTypeName(){
			"Sublist:" + name
		}
		
		override makeReference(){
			new ReferenceTypeInfo(this)
		}

		override makeVector(){
			new VectorTypeInfo(this);
		}
		
		override isVector(){
			false
		}
		
		override isReference(){
			false
		}
	}
	
	public static val UNDEFINED_TYPE = new PrimitiveTypeInfo(PrimitiveType.Undefined)
	public static val INT_TYPE = new PrimitiveTypeInfo(PrimitiveType.Int)
	public static val BOOLEAN_TYPE = new PrimitiveTypeInfo(PrimitiveType.Boolean)
	public static val REAL_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real)
	public static val STRING_TYPE = new PrimitiveTypeInfo(PrimitiveType.String)
	public static val BOOL_TYPE = new PrimitiveTypeInfo(PrimitiveType.Boolean)
	public static val PDF_TYPE = new PrimitiveTypeInfo(PrimitiveType.Pdf)
	public static val REAL_VECTOR_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real).makeVector
	public static val INT_VECTOR_TYPE = new PrimitiveTypeInfo(PrimitiveType.Int).makeVector
	public static val MAPPING_TYPE =  new PrimitiveTypeInfo(PrimitiveType.Mapping)
	
	static val Map<PrimitiveType, Set<PrimitiveType>> compatibleTypes = #{
		PrimitiveType.Deriv -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.Deriv },
		PrimitiveType.Real -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.Deriv },
		PrimitiveType.Int -> #{ PrimitiveType.Int },
		PrimitiveType.String -> #{ PrimitiveType.String },
		PrimitiveType.Boolean -> #{ PrimitiveType.Boolean },
		PrimitiveType.Pdf -> #{ PrimitiveType.Pdf },
		PrimitiveType.Mapping -> #{ PrimitiveType.Mapping },
		PrimitiveType.Undefined -> #{  }
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
		ep.transformedDefinition -> REAL_TYPE,
		ep.randomVariableDefinition -> REAL_TYPE,
		ep.forwardDeclaration -> REAL_TYPE,
		ep.mappingExpression -> MAPPING_TYPE,
		ep.catValRefMappingExpression -> MAPPING_TYPE
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
		var allRefs = true
		for(Expression e : vl.expressions){
			val origType = e.typeFor
			// check to see if amy non refs present. If so resulting array type will be non-ref
			if(!origType.isReference) allRefs = false  
			val exprType = origType.underlyingType // just in case it is a reference
			if(refType == null)
				refType = exprType
			else{
				val promotedType = refType.getRichestPromotableType(exprType)
				if(promotedType != null && refType != promotedType)
					refType = promotedType
			}
		}
		val arrayType = (refType ?: UNDEFINED_TYPE)
		// if all the types are refs then reflect this in the vector type.
		if(allRefs) arrayType.makeReference.makeVector else arrayType.makeVector
	}
	
	def TypeInfo getTypeForSublist(SubListExpression e){
		BuiltinFunctionProvider::FIX_EFF_SUBLIST
	}
	
	def dispatch TypeInfo typeFor(Expression e){
		switch(e){
			SymbolReference:
				e.ref.typeFor.makeReference
			CategoryValueReference:
				e.ref.typeFor.makeReference
			ParExpression: e.expr.typeFor
			EnumExpression:
				e.typeOfBuiltinEnum
			BuiltinFunctionCall:
				e.functionType
			VectorElement:
				e.element.head.typeFor
			VectorLiteral:
				if(e.expressions.isEmpty) MclTypeProvider.REAL_VECTOR_TYPE
				else e.typeForArray
			SubListExpression:
				e.typeForSublist 
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
	
	def dispatch TypeInfo typeFor(CategoryValueDefinition sd){
//		return new EnumTypeInfo(exp.name)
		switch(sd){
			CategoryValueDefinition:{
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
		if(lhsType.theType == PrimitiveType.Enum){
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
	
	
	def checkAsOperator(Expression lhs, Expression rhs,  (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedAndExpression(INT_TYPE, lhs, leftErrorLambda)
		if(rhs?.typeFor ?: UNDEFINED_TYPE == MAPPING_TYPE){
			checkExpectedAndExpression(MAPPING_TYPE, rhs, rightErrorLambda)
		}
		else{
			checkExpectedAndExpression(REAL_TYPE.makeReference, rhs, rightErrorLambda)
		}
	}
	
	def checkWhenOperator(EnumPair at, CategoryValueReference lhs, Expression rhs,  (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedEnumType(lhs, leftErrorLambda)
		if(rhs != null){
			validateCategoricalMappingType(at, rhs, rightErrorLambda)
//			val attList = at.eContainer as AttributeList
//			val listDefn = attList.matchingListDefn
//			var expectedType = listDefn?.getCategoryMappingType(at.attributeName) ?: UNDEFINED_TYPE
//			checkExpectedAndExpression(expectedType, rhs, rightErrorLambda)
		}
	}
	
	def checkWhenOperator(EnumPair at, CategoryValueDefinition catValDefn,  (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		val actualType = catValDefn.typeFor
		if(actualType.theType != PrimitiveType.Enum){
			leftErrorLambda.apply(new EnumTypeInfo(catValDefn.name), actualType)
		}
		if(catValDefn.mappedTo != null){
			validateCategoricalMappingType(at, catValDefn.mappedTo, rightErrorLambda)
//			val attList = at.eContainer as AttributeList
//			val listDefn = attList.matchingListDefn
//			var expectedType = listDefn?.getCategoryMappingType(at.attributeName) ?: UNDEFINED_TYPE
//			checkExpectedAndExpression(expectedType, catValDefn.mappedTo, rightErrorLambda)
		}
	}

	private  def void validateCategoricalMappingType(EnumPair at, Expression mappingExpr, (TypeInfo, TypeInfo) => void typeErrorLambda){
		val attList = at.eContainer as AttributeList
		val listDefn = attList.matchingListDefn
		val attDefn = listDefn?.getAttributeDefinition(at.attributeName)
		if(attDefn.isCatMappingPossible){
			val expectedType = attDefn.catMappingType ?: UNDEFINED_TYPE
			checkExpectedAndExpression(expectedType, mappingExpr, typeErrorLambda)
		}
	}

	
	def dispatch void checkExpectedAndExpression(EnumTypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: UNDEFINED_TYPE
		if(!expectedType.isCompatible(actualType)){
			errorLambda.apply(expectedType, actualType)
		} 
	}
	
	def void checkExpectedEnumType(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: UNDEFINED_TYPE
		if(actualType.theType != PrimitiveType.Enum){
			errorLambda.apply(new EnumTypeInfo("any"), actualType)
		}
	}
	
	def dispatch void checkExpectedAndExpression(TypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: UNDEFINED_TYPE
		if(!expectedType.isCompatible(actualType)){
			errorLambda.apply(expectedType, actualType)
		} 
	}

	def checkAttributeTyping(ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		val attList = at.eContainer as AttributeList
		val listDefn = attList.matchingListDefn
		if(listDefn != null){
			val attType = listDefn.getAttributeType(at.attributeName)
			switch(at){
				ValuePair:
					checkExpectedAndExpression(attType, at.expression, errorLambda)				
			}
		}
	}

	def checkNamedFunctionArgumentTyping(ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(at.namedArgumentType, at.expression, errorLambda)				
	}

	def checkFunctionArgumentTyping(UnnamedArgument at, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(at.unamedArgumentType, at.argument, errorLambda)				
	}

}