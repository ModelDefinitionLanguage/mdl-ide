package eu.ddmore.mdl.type

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ForwardDeclaration
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MatrixElement
import eu.ddmore.mdl.mdl.MatrixLiteral
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SubListExpression
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
import eu.ddmore.mdl.validation.ListDefinitionProvider.AttributeDefn
import eu.ddmore.mdl.validation.ListDefinitionProvider.ListDefInfo
import eu.ddmore.mdl.validation.PropertyDefinitionProvider
import eu.ddmore.mdl.validation.SublistDefinitionProvider
import java.util.ArrayList
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

public class MclTypeProvider {

	extension BuiltinFunctionProvider typeProvider = new BuiltinFunctionProvider
	extension ListDefinitionProvider listProvider = new ListDefinitionProvider
	extension SublistDefinitionProvider subListProvider = new SublistDefinitionProvider
	extension PropertyDefinitionProvider propProvider = new PropertyDefinitionProvider
	

	enum PrimitiveType {
		Int, String, Real, Deriv, Boolean, Pdf, Pmf, Enum, List, Mapping, Sublist, Vector, Reference, Undefined
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
		Set<String> categories = new HashSet<String>
		
		new(String name, Set<String> categories){
			super(PrimitiveType.Enum)
			enumName = name
			this.categories.addAll(categories)
		}
		
		override isCompatible(TypeInfo otherType){
			if(this === otherType) return true
			switch(otherType){
				EnumTypeInfo: this.enumName == otherType.enumName
					&& isCompatibleCategories(otherType)
				EnumListTypeInfo:
					this.isCompatible(otherType.underlyingEnum)
				default: false 
			}
		}
		
		
		def boolean isCompatibleCategories(EnumTypeInfo otherType){
			for(cat : categories){
				if(!otherType.categories.exists[it == cat]) return false
			}
			true
		}
		
		override getTypeName(){
			"Enum:" + enumName
		}
		
	}

	@Data @FinalFieldsConstructor
	static class GenericEnumTypeInfo extends EnumTypeInfo{
		static val GENERIC_ENUM_TYPE_NAME = "generic"
		
		
		new(){
			super(GENERIC_ENUM_TYPE_NAME, Collections::emptySet)
		}
		
		override isCompatible(TypeInfo otherType){
			switch(otherType){
				EnumTypeInfo: true
				default: false 
			}
		}
		
	}


	
	@Data
	static class EnumListTypeInfo extends ListTypeInfo {
		val EnumTypeInfo underlyingEnum		

		new(String listName, EnumTypeInfo underlyingEnum) {
			super(listName, PrimitiveType.Enum)
			this.underlyingEnum = underlyingEnum
		}
		
		new(String listName) {
			super(listName, PrimitiveType.Enum)
			this.underlyingEnum = new GenericEnumTypeInfo
		}
		
		override isCompatible(TypeInfo otherType){
			switch(otherType){
				EnumTypeInfo:
					 underlyingEnum.isCompatible(otherType)
				default:
					super.isCompatible(otherType)
			}
		}
		
		def EnumListTypeInfo populateType(EnumTypeInfo realEnum){
			new EnumListTypeInfo(this.getName, realEnum)
		}
	}


	@Data 
	static class BuiltinEnumTypeInfo extends EnumTypeInfo{
//		List<String> expectedValues

		new (String name, Set<String> ev){
			super(name, ev)
//			this.expectedValues = new ArrayList<String>(ev)
		}

		def getExpectedValues(){
			super.categories
		}		
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
				PrimitiveTypeInfo:{
					// If other is primitive type then check compatibility
					val compType = compatibleTypes?.get(this.theType)
					if(compType != null)
						compType.contains(otherType.theType)
					else false
				}
					
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
		List<Map<String, Boolean>> nameSets 
		
		new(String name, List<AttributeDefn> attributes, List<Map<String, Boolean>> nameSets){
			//@TODO copy nameset list properly
			this(name, PrimitiveType.Sublist, new ArrayList<AttributeDefn>(attributes), nameSets)
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
	public static val PMF_TYPE = new PrimitiveTypeInfo(PrimitiveType.Pmf)
	public static val REAL_VECTOR_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real).makeVector
	public static val REAL_MATRIX_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real).makeVector.makeVector
	public static val INT_VECTOR_TYPE = new PrimitiveTypeInfo(PrimitiveType.Int).makeVector
	public static val MAPPING_TYPE =  new PrimitiveTypeInfo(PrimitiveType.Mapping)
	public static val GENERIC_ENUM_VALUE_TYPE =  new GenericEnumTypeInfo
	
	static val Map<PrimitiveType, Set<PrimitiveType>> compatibleTypes = #{
		PrimitiveType.Deriv -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.Deriv },
		PrimitiveType.Real -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.Deriv },
		PrimitiveType.Int -> #{ PrimitiveType.Int },
		PrimitiveType.String -> #{ PrimitiveType.String },
		PrimitiveType.Boolean -> #{ PrimitiveType.Boolean },
		PrimitiveType.Pdf -> #{ PrimitiveType.Pdf },
		PrimitiveType.Pmf -> #{ PrimitiveType.Pmf },
		PrimitiveType.Mapping -> #{ PrimitiveType.Mapping },
		PrimitiveType.Undefined -> #{  }
	}
	
	static val ep = MdlPackage::eINSTANCE 
	
	static val typeTable = #{
		ep.realLiteral -> REAL_TYPE,
		ep.integerLiteral -> INT_TYPE,
		ep.stringLiteral -> STRING_TYPE,
		ep.booleanLiteral -> BOOL_TYPE,
		ep.constantLiteral -> REAL_TYPE,
		ep.whenExpression -> REAL_TYPE,
		ep.whenClause -> REAL_TYPE,
		ep.relationalExpression -> BOOL_TYPE,
		ep.equalityExpression -> BOOL_TYPE,
		ep.andExpression -> BOOL_TYPE,
		ep.orExpression -> BOOL_TYPE,
		ep.additiveExpression -> REAL_TYPE,
		ep.multiplicativeExpression -> REAL_TYPE,
		ep.powerExpression -> REAL_TYPE,
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
		for(e : vl.expressions){
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
		// find attribute name and get the subtype from it.
		val subListType = e.findSublistMatch ?: UNDEFINED_TYPE
		return subListType
	}
	
	def dispatch TypeInfo typeFor(Expression e){
		if(e == null) return MclTypeProvider.UNDEFINED_TYPE 
		switch(e){
			SymbolReference:
				if(e.indexExpr == null)
					e.ref.typeFor.makeReference
				else{
					val t = e.ref.typeFor
					switch(t){
						VectorTypeInfo:
							t.elementType.makeReference
						default: UNDEFINED_TYPE
					}
				}
			CategoryValueReference:
				e.ref.typeFor.makeReference
			ParExpression: e.expr.typeFor
//			EnumExpression:
//				e.typeOfBuiltinEnum
			BuiltinFunctionCall:
				e.functionType
			VectorElement:
				e.element?.typeFor ?: MclTypeProvider.UNDEFINED_TYPE
			VectorLiteral:
				if(e.expressions.isEmpty) MclTypeProvider.REAL_VECTOR_TYPE
				else e.typeForArray
			MatrixElement:
				e.element?.typeFor ?: MclTypeProvider.UNDEFINED_TYPE
			MatrixLiteral:
				MclTypeProvider.REAL_VECTOR_TYPE.makeVector
			SubListExpression:
				e.typeForSublist 
			default:
				typeTable.get(e.eClass) ?: MclTypeProvider.UNDEFINED_TYPE
		}
	}
	
	def dispatch TypeInfo typeFor(EnumExpression e){
		var Object parent = EcoreUtil2.getContainerOfType(e, BuiltinFunctionCall)
		if(parent == null){
			parent = EcoreUtil2.getContainerOfType(e, SubListExpression)
		}
		if(parent == null){
			parent = EcoreUtil2.getContainerOfType(e, ListDefinition)
		}
		if(parent == null){
			parent = EcoreUtil2.getContainerOfType(e, AnonymousListStatement)
		}
		if(parent == null){
			parent = EcoreUtil2.getContainerOfType(e, PropertyStatement)
		}
		switch(parent){
			BuiltinFunctionCall:
				e.typeOfFunctionBuiltinEnum
			ListDefinition,
			AnonymousListStatement:
				e.typeOfAttributeBuiltinEnum
			SubListExpression:
				parent.getTypeOfAttributeBuiltinEnum(e)
			PropertyStatement:
				e.typeOfPropertyBuiltinEnum
			default:
				UNDEFINED_TYPE
		}
	}

	def dispatch TypeInfo typeFor(UnaryExpression it){
		switch(feature){
			case('!'): BOOL_TYPE
			case('+'),
			case('-'):{
				val operandType = operand?.typeFor.underlyingType
//				if(operandType == INT_TYPE)
//					INT_TYPE
//				else if(operandType == REAL_TYPE)
//					REAL_TYPE
				if(operandType.isCompatible(INT_TYPE)) return INT_TYPE
				else if(operandType.isCompatible(REAL_TYPE)) return REAL_TYPE
				else UNDEFINED_TYPE
			}
			default:
				throw new RuntimeException("Unrecognised unary operator.")
		}
	}
	
	def dispatch TypeInfo typeFor(SymbolDefinition sd){
		switch(sd){
			EquationDefinition:
				if(sd.isVector)
					MclTypeProvider.REAL_VECTOR_TYPE
				else if(sd.isMatrix)
					REAL_MATRIX_TYPE
				else REAL_TYPE
			ListDefinition:
				getTypeOfList(sd)
			TransformedDefinition,
			ForwardDeclaration:
				typeTable.get(sd.eClass)
//			RandomVariableDefinition: typeTable.get(sd.eClass)
			RandomVariableDefinition:
				if(sd.isVector) MclTypeProvider.REAL_VECTOR_TYPE else REAL_TYPE
			EnumerationDefinition:{
					val defn = sd.catDefn 
					switch(defn){
						CategoricalDefinitionExpr:
							new EnumTypeInfo(sd.name, defn.getCategoryNames)
						default: UNDEFINED_TYPE							
					}
				}
			default:
				UNDEFINED_TYPE
		}
	}
	
	def dispatch TypeInfo typeFor(CategoryValueDefinition sd){
//		return new EnumTypeInfo(exp.name)
		switch(sd){
			CategoryValueDefinition:{
				val enumDefn = EcoreUtil2.getContainerOfType(sd.eContainer, SymbolDefinition)
				val catDefn = EcoreUtil2.getContainerOfType(sd.eContainer, CategoricalDefinitionExpr)
				if(enumDefn != null && catDefn != null) new EnumTypeInfo(enumDefn.name, catDefn.getCategoryNames)
				else UNDEFINED_TYPE
			}
			default:
				UNDEFINED_TYPE
		}
	}
	
	def getCategoryNames(CategoricalDefinitionExpr catDefn){
		val catNames = new HashSet<String>
		catDefn.categories.forEach[catNames.add(name)]
		catNames
	}
	
	def TypeInfo getTypeOfList(ListDefinition it){
		val listDefn = list.listDefinition
		val type = listDefn?.listType ?: UNDEFINED_TYPE
		switch(type){
			EnumListTypeInfo:
				getPopulatedType(listDefn)
			ListTypeInfo: type
			default: UNDEFINED_TYPE
		}
	}
	
	def TypeInfo getPopulatedType(ListDefinition it, ListDefInfo listDefn){
		for(att : list.attributes){
			val expr = att.expression
			switch(expr){
				EnumExpression case expr.catDefn != null:
					if(expr.catDefn instanceof CategoricalDefinitionExpr){
						val catNames = (expr.catDefn as CategoricalDefinitionExpr).getCategoryNames
						if(listDefn.listType instanceof EnumListTypeInfo){
							return (listDefn.listType as EnumListTypeInfo).populateType(new EnumTypeInfo(name, catNames))	
						}
						else{
							return UNDEFINED_TYPE
						}
					}
			}
		}
		UNDEFINED_TYPE
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
	
	def checkExpectedMatrix(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(MclTypeProvider.REAL_MATRIX_TYPE, exp, errorLambda)
	}
	
	def checkExpectedIntl(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(INT_TYPE, exp, errorLambda)
	}
	
	def checkExpectedPdf(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(PDF_TYPE, exp, errorLambda)
	}

	def checkExpectedPdfVector(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(PDF_TYPE.makeVector, exp, errorLambda)
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
			leftErrorLambda.apply(new GenericEnumTypeInfo, actualType)
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
			errorLambda.apply(new GenericEnumTypeInfo, actualType)
		}
	}
	
	def dispatch void checkExpectedAndExpression(TypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: UNDEFINED_TYPE
		if(!expectedType.isCompatible(actualType)){
			errorLambda.apply(expectedType, actualType)
		} 
	}

	def checkAttributeTyping(AttributeList attList, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		val listDefn = attList.matchingListDefn
		if(listDefn != null){
			val attType = listDefn.getAttributeType(at.attributeName)
			switch(at){
				ValuePair:
					checkExpectedAndExpression(attType, at.expression, errorLambda)				
			}
		}
	}


	def checkPropertyAttributeTyping(PropertyStatement stmt, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		val attType = at.typeForProperty
		switch(at){
			ValuePair:
				checkExpectedAndExpression(attType, at.expression, errorLambda)				
		}
	}


	def checkSublistAttributeTyping(SubListExpression it, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		val subListDefn = findSublistMatch

		if(subListDefn != null){
			val attDefn = subListDefn.attributes.findFirst[name == at.attributeName]
			checkExpectedAndExpression(attDefn.attType, at.expression, errorLambda)
		}
	}

	def checkNamedFunctionArgumentTyping(ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(at.namedArgumentType, at.expression, errorLambda)				
	}

	def checkFunctionArgumentTyping(UnnamedArgument at, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(at.unamedArgumentType, at.argument, errorLambda)				
	}

}
