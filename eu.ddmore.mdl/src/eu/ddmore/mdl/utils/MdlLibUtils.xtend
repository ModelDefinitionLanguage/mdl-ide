package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.CategoryListTypeInfo
import eu.ddmore.mdl.type.FunctionTypeInfo
import eu.ddmore.mdl.type.GeneralCategoryTypeInfo
import eu.ddmore.mdl.type.GeneralCategoryValueTypeInfo
import eu.ddmore.mdl.type.ListSuperTypeInfo
import eu.ddmore.mdl.type.ListTypeInfo
import eu.ddmore.mdl.type.SublistTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdllib.mdllib.AbstractTypeDefinition
import eu.ddmore.mdllib.mdllib.BlockContainer
import eu.ddmore.mdllib.mdllib.BlockDefinition
import eu.ddmore.mdllib.mdllib.ContainmentDefn
import eu.ddmore.mdllib.mdllib.FunctionSpec
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdllib.mdllib.ListTypeDefinition
import eu.ddmore.mdllib.mdllib.ObjectDefinition
import eu.ddmore.mdllib.mdllib.SubListTypeDefinition
import eu.ddmore.mdllib.mdllib.TypeClass
import eu.ddmore.mdllib.mdllib.TypeDefinition
import eu.ddmore.mdllib.mdllib.TypeSpec
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import org.eclipse.xtext.EcoreUtil2

class MdlLibUtils {

//	val Map<String, ? extends TypeInfo> listDefns = #{
//		ListDefinitionTable::PRIOR_SOURCE_TYPE.name -> ListDefinitionTable::PRIOR_SOURCE_TYPE
//	}
	
//	def private getScalarType(TypeSpec it){
//		val typeName = typeName.name
//		switch(typeName){
//			case 'Int':
//				TypeSystemProvider::INT_TYPE
//			case 'Real':
//				TypeSystemProvider::REAL_TYPE
//			case 'Boolean':
//				TypeSystemProvider::BOOLEAN_TYPE
//			case 'String':
//				TypeSystemProvider::STRING_TYPE
//			case 'Pdf':
//				TypeSystemProvider::PDF_TYPE
//			case 'Pmf':
//				TypeSystemProvider::PMF_TYPE
//			default:
//				TypeSystemProvider::UNDEFINED_TYPE
//		}
//	}


	def TypeInfo getTypeInfo(AbstractTypeDefinition it){
		val typeClass = typeClass
		val td = it
		switch(td){
			ListTypeDefinition:
				switch(typeClass){
					case TypeClass.LIST:{
						if(td.isIsSuper){
							if(td.altType != null)
								new ListSuperTypeInfo(td.name, td.altType.typeInfo.typeClass)
							else
								new ListSuperTypeInfo(td.name)
						}
						else if(td.isEnumList){
							new CategoryListTypeInfo(td.name)
						}
						else{
							val altTypeInfo = td.altType?.typeInfo
							if(td.superRef != null && altTypeInfo != null)
								new ListTypeInfo(td.name, altTypeInfo.typeClass, td.superRef.typeInfo)
							else if(td.superRef == null && td.altType != null) new ListTypeInfo(td.name, altTypeInfo.typeClass)
							else if(td.superRef != null && td.altType == null)
								new ListTypeInfo(td.name, td.superRef.typeInfo)
							else
								new ListTypeInfo(td.name)
						}
					}
				default:
					TypeSystemProvider::UNDEFINED_TYPE
				}
			SubListTypeDefinition:
				switch(typeClass){
					case TypeClass.SUBLIST:
						createSublistType(td)
					default:
						TypeSystemProvider::UNDEFINED_TYPE
				}
				
			TypeDefinition:
				switch(typeClass){
					case TypeClass.INT:
						TypeSystemProvider::INT_TYPE
					case TypeClass.REAL:
						TypeSystemProvider::REAL_TYPE
					case TypeClass.BOOLEAN:
						TypeSystemProvider::BOOLEAN_TYPE
					case TypeClass.STRING:
						TypeSystemProvider::STRING_TYPE
					case TypeClass.PDF:
						TypeSystemProvider::PDF_TYPE
					case TypeClass.PMF:
						TypeSystemProvider::PMF_TYPE
					case TypeClass.ENUM:
						createBuiltinEnum(td)
					case TypeClass.CATEGORY:
						createGeneralCategoryType(td)
					case TypeClass.CATEGORY_VALUE:
						createGeneralCategoryValueType(td)
					case TypeClass.DERIV:
						TypeSystemProvider::DERIV_TYPE
					case TypeClass.MAPPING:
						TypeSystemProvider::MAPPING_TYPE 
					default:
						TypeSystemProvider::UNDEFINED_TYPE
				}
		}
	}
	
	def createGeneralCategoryType(TypeDefinition td) {
		new GeneralCategoryTypeInfo(td.name)
	}

	def createGeneralCategoryValueType(TypeDefinition td) {
		new GeneralCategoryValueTypeInfo(td.name)
	}

	def TypeInfo getTypeInfo(TypeSpec it){
		val typeClass = typeName.typeClass
		switch(typeClass){
//			case TypeClass.INT:
//				TypeSystemProvider::INT_TYPE
//			case TypeClass.REAL:
//				TypeSystemProvider::REAL_TYPE
//			case TypeClass.BOOLEAN:
//				TypeSystemProvider::BOOLEAN_TYPE
//			case TypeClass.STRING:
//				TypeSystemProvider::STRING_TYPE
//			case TypeClass.PDF:
//				TypeSystemProvider::PDF_TYPE
//			case TypeClass.PMF:
//				TypeSystemProvider::PMF_TYPE
			case TypeClass.VECTOR:
				if(elementType != null && cellType == null && functionSpec == null){
					// element type specified and well formed
					val elType = elementType.typeInfo
					if(elType == TypeSystemProvider::UNDEFINED_TYPE) TypeSystemProvider::UNDEFINED_TYPE
					else elType.makeVector
				}
				else if(elementType == null && cellType == null && functionSpec == null){
					// no element spec and well formed so default to Real 
					TypeSystemProvider::REAL_VECTOR_TYPE
				}
				else TypeSystemProvider::UNDEFINED_TYPE
			case TypeClass.MATRIX:
				if(cellType != null && elementType == null && functionSpec == null){
					// element type specified and well formed
					val elType = cellType.typeInfo
					if(elType == TypeSystemProvider::UNDEFINED_TYPE) TypeSystemProvider::UNDEFINED_TYPE
					else elType.makeMatrix
				}
				else if(cellType == null && elementType == null && functionSpec == null){
					// no element spec and well formed so default to Real 
					TypeSystemProvider::REAL_MATRIX_TYPE
				}
				else TypeSystemProvider::UNDEFINED_TYPE
			case TypeClass.REFERENCE:
				if(elementType != null && cellType == null && functionSpec == null){
					// element type specified and well formed
					val elType = elementType.typeInfo
					if(elType == TypeSystemProvider::UNDEFINED_TYPE) TypeSystemProvider::UNDEFINED_TYPE
					else elType.makeReference
				}
				else if(elementType == null && cellType == null && functionSpec == null){
					// no element spec and well formed so default to Real 
					TypeSystemProvider::REAL_TYPE.makeReference
				}
				else TypeSystemProvider::UNDEFINED_TYPE
			case TypeClass.FUNCTION:
				if(argSpecs != null && rtnSpec != null){
					val argList = new ArrayList<TypeInfo>
					argSpecs.forEach[a|
						argList.add(a.typeInfo)
					]
					new FunctionTypeInfo(argList, rtnSpec.typeInfo)
				}
//			case TypeClass.ENUM:
//				createBuiltinEnum(typeName)
//			case TypeClass.SUBLIST:
//				SublistDefinitionTable::instance.getSublist(typeName.name) ?: TypeSystemProvider::UNDEFINED_TYPE
//			case TypeClass.CATEGORY:
//				TypeSystemProvider::GENERIC_ENUM_VALUE_TYPE 
//			case TypeClass.LIST:
//				listDefns.get(typeName.name) ?: TypeSystemProvider::UNDEFINED_TYPE
			default:
				typeName.typeInfo
		} 
	}
	
	def getTypeInfo(FunctionSpec it){
		val argList = new ArrayList<TypeInfo>
		argument.arguments.forEach[a|
			argList.add(a.typeSpec.typeInfo)
		]
		new FunctionTypeInfo(argList, returnType.typeInfo)
	}
	
	def private SublistTypeInfo createSublistType(SubListTypeDefinition it){
		new SublistTypeInfo(it)
	}
	
	def private BuiltinEnumTypeInfo createBuiltinEnum(TypeDefinition it){
		val enumVals = new HashSet<String>
		enumArgs.forEach[enumVals.add(name)]
		
		new BuiltinEnumTypeInfo(name, enumVals)
	}
	
	def Library getLibraryForObject(MclObject obj){
		EcoreUtil2.getContainerOfType(obj.objId, Library)
	}
	
	def List<String> getMandatoryBlockNamesForObject(Library it, ObjectDefinition objDefn){
		val retVal = new ArrayList<String>
		containDefns.filter[
			parentRef.name == objDefn.name
		].forEach[
			blkRefs.forEach[ 
				if(minNum > 0)
					retVal.add(it.name)
			]
		]
		
		retVal
	}

	
	def List<String> getMandatoryArgumentNames(BlockDefinition it){
		val retVal = new ArrayList<String>
		arguments.filter[
			optional == false
		].forEach[
			retVal.add(it.name)
		]
		
		retVal
	}


	def ContainmentDefn getContainmentDefnForObj(BlockContainer od){
		val lib = od.eContainer as Library
		lib.containDefns.findFirst[
			parentRef.name == od.name
		]
	}

	def boolean canContainBlock(BlockContainer it, BlockDefinition blkDefn){
		val cd = containmentDefnForObj
		if(cd != null){
			cd.blkRefs.exists[
				name == blkDefn.name
			]
		}
		else false
	}

}