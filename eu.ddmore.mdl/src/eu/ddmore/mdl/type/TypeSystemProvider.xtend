package eu.ddmore.mdl.type

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.IndexSpec
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MatrixElement
import eu.ddmore.mdl.mdl.MatrixLiteral
import eu.ddmore.mdl.mdl.MatrixRow
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider.ListDefInfo
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.provider.PropertyDefinitionProvider
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import eu.ddmore.mdl.utils.CycleDetectionUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.FunctionDefnBody
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.HashSet
import java.util.List
import org.eclipse.xtext.EcoreUtil2
import eu.ddmore.mdl.mdl.FuncArgumentDefinition

public class TypeSystemProvider {

	extension BuiltinFunctionProvider typeProvider = new BuiltinFunctionProvider
	extension ListDefinitionProvider listProvider = new ListDefinitionProvider
	extension SublistDefinitionProvider subListProvider = new SublistDefinitionProvider
	extension PropertyDefinitionProvider propProvider = new PropertyDefinitionProvider
	extension CycleDetectionUtils cdu = new CycleDetectionUtils	
	extension MdlLibUtils mlu = new MdlLibUtils
	
	public static val UNDEFINED_TYPE = new PrimitiveTypeInfo(PrimitiveType.Undefined)
	public static val INT_TYPE = new PrimitiveTypeInfo(PrimitiveType.Int)
	public static val BOOLEAN_TYPE = new PrimitiveTypeInfo(PrimitiveType.Boolean)
	public static val REAL_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real)
	public static val STRING_TYPE = new PrimitiveTypeInfo(PrimitiveType.String)
	public static val BOOL_TYPE = new PrimitiveTypeInfo(PrimitiveType.Boolean)
	public static val PDF_TYPE = new PrimitiveTypeInfo(PrimitiveType.Pdf)
	public static val PMF_TYPE = new PrimitiveTypeInfo(PrimitiveType.Pmf)
	public static val REAL_VECTOR_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real).makeVector
	public static val REAL_MATRIX_TYPE = new PrimitiveTypeInfo(PrimitiveType.Real).makeMatrix
	public static val INT_VECTOR_TYPE = new PrimitiveTypeInfo(PrimitiveType.Int).makeVector
	public static val MAPPING_TYPE =  new PrimitiveTypeInfo(PrimitiveType.Mapping)
	public static val GENERIC_ENUM_VALUE_TYPE =  new GenericEnumTypeInfo
	
	
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
	
	def getTypeForMatrix(MatrixLiteral vl){
		val retVal = getTypeForArrayOfElements(vl.rows)
		retVal.makeMatrix
	}
	
	private def getTypeForArrayOfElements(List<Expression> elements){
		// first type determines array type unless one of the other elements is a type
		// that it could be promoted to. For example if the first element is an int
		// and a later type is a Real the the type for the vector as a whole is a Real.
		var TypeInfo refType = null
		var allRefs = true
		for(e : elements){
			val origType = e.typeFor
			// check to see if amy non refs present. If so resulting array type will be non-ref
			if(!(origType instanceof ReferenceTypeInfo)) allRefs = false  
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
		if(allRefs) arrayType.makeReference else arrayType
	}
	
	def getTypeForMatrixRow(MatrixRow vl){
		getTypeForArrayOfElements(vl.cells)
	}

	def getTypeForArray(VectorLiteral vl){
		val arrayType = getTypeForArrayOfElements(vl.expressions)
		arrayType.makeVector
//		// first type determines array type unless one of the other elements is a type
//		// that it could be promoted to. For example if the first element is an int
//		// and a later type is a Real the the type for the vector as a whole is a Real.
//		var TypeInfo refType = null
//		var allRefs = true
//		for(e : vl.expressions){
//			val origType = e.typeFor
//			// check to see if amy non refs present. If so resulting array type will be non-ref
//			if(!(origType instanceof ReferenceTypeInfo)) allRefs = false  
//			val exprType = origType.underlyingType // just in case it is a reference
//			if(refType == null)
//				refType = exprType
//			else{
//				val promotedType = refType.getRichestPromotableType(exprType)
//				if(promotedType != null && refType != promotedType)
//					refType = promotedType
//			}
//		}
//		val arrayType = (refType ?: UNDEFINED_TYPE)
//		// if all the types are refs then reflect this in the vector type.
//		if(allRefs) arrayType.makeReference.makeVector else arrayType.makeVector
	}
	
	def TypeInfo getTypeForSublist(SubListExpression e){
		// find attribute name and get the subtype from it.
		val subListType = e.findSublistMatch ?: UNDEFINED_TYPE
		return subListType
	}
	
	
	private def isSingleVectorElement(IndexSpec it){
		rowIdx != null && rowIdx.begin != null && rowIdx.end == null
	}
	
	private def isSingleMatrixElement(IndexSpec it){
		rowIdx != null && rowIdx.begin != null && rowIdx.end == null &&
		colIdx != null && colIdx.begin != null && colIdx.end == null
	}
	
	private def getTypeOfSymbolRef(SymbolReference e){
//		// check if ref and defn the same. If so this will cause a cycle.
//		val defn = EcoreUtil2.getContainerOfType(e.eContainer, SymbolDefinition)
//		var TypeInfo retVal = UNDEFINED_TYPE
//		if(defn != e.ref){ 
//			retVal =
				if(e.indexExpr == null)
					e.ref.typeFor.makeReference
				else{
					val t = e.ref.typeFor
					switch(t){
						VectorTypeInfo:
							if(e.indexExpr.isSingleVectorElement)
								t.elementType.makeReference
							else t.makeReference
						MatrixTypeInfo:{
							if(e.indexExpr.isSingleMatrixElement)
								t.elementType.makeReference
							else t.makeReference
						}
						
						default: UNDEFINED_TYPE
					}
				}
//		}
//		retVal
	}
	
	def dispatch TypeInfo typeFor(Expression e){
		if(e == null) return TypeSystemProvider.UNDEFINED_TYPE 
		switch(e){
			SymbolReference:
				e.typeOfSymbolRef
			CategoryValueReference:
				e.ref.typeFor.makeReference
			ParExpression: e.expr.typeFor
//			EnumExpression:
//				e.typeOfBuiltinEnum
//			BuiltinFunctionCall:
//				e.functionType
			VectorElement:
				e.element?.typeFor ?: TypeSystemProvider.UNDEFINED_TYPE
			VectorLiteral:
				if(e.expressions.isEmpty) TypeSystemProvider.REAL_VECTOR_TYPE
				else e.typeForArray
			MatrixElement:
				e.cell?.typeFor ?: TypeSystemProvider.UNDEFINED_TYPE
			MatrixRow:
				if(e.cells.isEmpty) TypeSystemProvider.REAL_VECTOR_TYPE
				else e.typeForMatrixRow
			MatrixLiteral:
				if(e.rows.isEmpty) TypeSystemProvider.REAL_VECTOR_TYPE
				else e.typeForMatrix
			SubListExpression:
				e.typeForSublist 
			default:
				typeTable.get(e.eClass) ?: TypeSystemProvider.UNDEFINED_TYPE
		}
	}
	
	def dispatch TypeInfo typeFor(EnumExpression e){
//		var Object parent = EcoreUtil2.getContainerOfType(e, BuiltinFunctionCall)
		var Object parent = EcoreUtil2.getContainerOfType(e, SymbolReference)
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
			SymbolReference:
				e.typeOfFunctionBuiltinEnum
//			BuiltinFunctionCall:
//				e.typeOfFunctionBuiltinEnum
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
				if(operandType.isCompatible(INT_TYPE)) return INT_TYPE
				else if(operandType.isCompatible(REAL_TYPE)) return REAL_TYPE
				else UNDEFINED_TYPE
			}
			default:
				throw new RuntimeException("Unrecognised unary operator.")
		}
	}
	
	def dispatch TypeInfo typeFor(SymbolDefinition sd){
		val retVal = switch(sd){
			EquationDefinition:
				{
					if(sd.expression != null){
						// type inferred from assigned type
						if(sd.hasNonDerivCycle([], [!it.isDerivativeDefinition]))
							// can't infer type if cycle in definition
							UNDEFINED_TYPE
						else
							sd.expression.typeFor
					}
					else{
						// not assigned value so defaults to REAL unless
						// an explicit type is declared
						if(sd.typeSpec != null){
							sd.typeSpec.typeInfo
						}
						else REAL_TYPE
					}
				}
			ListDefinition:
				getTypeOfList(sd)
			TransformedDefinition:
//			ArgumentDefinition:
				typeTable.get(sd.eClass)
			RandomVariableDefinition:
				{
					if(sd.distn != null){
						if(sd.hasNonDerivCycle([], [!it.isDerivativeDefinition]))
							// can't infer type if cycle in definition
							UNDEFINED_TYPE
						else{
							val distnType = sd.distn.typeFor.underlyingType
							switch(distnType){
								VectorTypeInfo case(distnType.elementType == PDF_TYPE): REAL_VECTOR_TYPE
								MatrixTypeInfo case(distnType.elementType == PDF_TYPE): REAL_MATRIX_TYPE
								PrimitiveTypeInfo case(distnType == PDF_TYPE): REAL_TYPE
								default:
									UNDEFINED_TYPE
									
							}
						}
					}
					else UNDEFINED_TYPE
				}
			EnumerationDefinition:{
					val defn = sd.catDefn 
					switch(defn){
						CategoricalDefinitionExpr:
							new EnumTypeInfo(sd.name, defn.getCategoryNames)
						default: UNDEFINED_TYPE							
					}
				}
			FunctionDefnBody:
				sd.funcDefn.returnType
			FuncArgumentDefinition:
				sd.typeSpec.typeInfo
			default:
				UNDEFINED_TYPE
		}
		// strip off any reference as this is a var definition an so cannot be a ref by definition.
		retVal.underlyingType
	}
	
//	def dispatch TypeInfo typeFor(TypeSpec it){
		
//		if(elementType == null && cellType == null){
//			typeFromSpecName(typeName.name)
//		}
//		else if(elementType != null){
//			if(typeName == '::Vector'){
//				val elType = elementType.typeFor
//				elType.makeVector
//			}
//			else UNDEFINED_TYPE
//		}
//		else if(cellType != null){
//			if(typeName == '::Matrix'){
//				val elType = cellType.typeFor
//				elType.makeMatrix
//			}
//			else UNDEFINED_TYPE
//		}
//		else UNDEFINED_TYPE
//	}
	
	static val specNameLookup = #{
		'::Int' -> INT_TYPE,
		'::Real' -> REAL_TYPE,
		'::Pdf' -> PDF_TYPE,
		'::String' -> STRING_TYPE,
		'::Boolean' -> BOOLEAN_TYPE,
		'::Vector' -> REAL_VECTOR_TYPE,
		'::Matrix' -> REAL_MATRIX_TYPE
	} 
	
	def typeFromSpecName(String specName){
		val specType = specNameLookup.get(specName) ?: UNDEFINED_TYPE
		specType	
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
	
    def isDerivativeDefinition(SymbolDefinition sd){
    	if(sd instanceof ListDefinition){
    		// this avoid a recursion as cycle detection is done for other symb defn types.
	    	val lstType = sd.typeFor
    		lstType?.typeName == ListDefinitionTable::DERIV_TYPE.typeName
    	}
    }

	def boolean isArgumentCompatible(TypeInfo argType, TypeInfo valueType){
		switch(argType){
			ListTypeInfo:
				// arg is list so check underlying type or value matches
				argType == valueType.underlyingType
			ReferenceTypeInfo:
				// is ref so check if value is ref and that underlying types match
				if(valueType instanceof ReferenceTypeInfo)
					// check that underlying tyes are argument compatible
					isArgumentCompatible(argType.underlyingType, valueType.underlyingType)
				else false
			default:
				// no special case handling so check for expression compatibility
				argType.isCompatible(valueType)
		}
	}
}
