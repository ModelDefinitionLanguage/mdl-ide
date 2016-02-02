package eu.ddmore.mdl.utils

import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.mdllib.mdllib.TypeSpec
import eu.ddmore.mdl.type.TypeSystemProvider

class MdlLibUtils {
	

	def TypeInfo getTypeInfo(TypeSpec it){
		val typeName = typeName.name
		switch(typeName){
			case 'Int':
				TypeSystemProvider::INT_TYPE
			case 'Real':
				TypeSystemProvider::REAL_TYPE
			case 'Boolean':
				TypeSystemProvider::BOOLEAN_TYPE
			case 'String':
				TypeSystemProvider::STRING_TYPE
			case 'Vector':
				if(elementType != null && functionSpec == null){
					// element type specified and well formed
					val elType = elementType.typeInfo
					if(elType == TypeSystemProvider::UNDEFINED_TYPE) TypeSystemProvider::UNDEFINED_TYPE
					else elType.makeVector
				}
				else if(elementType == null && functionSpec == null){
					// no element spec and well formed so default to Real 
					TypeSystemProvider::REAL_VECTOR_TYPE
				}
				else TypeSystemProvider::UNDEFINED_TYPE
			case 'Matrix':
				if(elementType != null && functionSpec == null){
					// element type specified and well formed
					val elType = elementType.typeInfo
					if(elType == TypeSystemProvider::UNDEFINED_TYPE) TypeSystemProvider::UNDEFINED_TYPE
					else elType.makeMatrix
				}
				else if(elementType == null && functionSpec == null){
					// no element spec and well formed so default to Real 
					TypeSystemProvider::REAL_MATRIX_TYPE
				}
				else TypeSystemProvider::UNDEFINED_TYPE
			case 'Reference':
				if(elementType != null && functionSpec == null){
					// element type specified and well formed
					val elType = elementType.typeInfo
					if(elType == TypeSystemProvider::UNDEFINED_TYPE) TypeSystemProvider::UNDEFINED_TYPE
					else elType.makeReference
				}
				else if(elementType == null && functionSpec == null){
					// no element spec and well formed so default to Real 
					TypeSystemProvider::REAL_TYPE.makeReference
				}
				else TypeSystemProvider::UNDEFINED_TYPE
			default:
				TypeSystemProvider::UNDEFINED_TYPE
		} 
	}
	
	
	
}