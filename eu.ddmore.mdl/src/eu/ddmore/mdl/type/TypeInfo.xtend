package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data


@Data
abstract class TypeInfo{
		def abstract PrimitiveType getTheType()
		def abstract TypeInfo getUnderlyingType()
//		def abstract boolean isVector()
//		def abstract boolean isReference()
		def abstract boolean isCompatible(TypeInfo otherType)
		def abstract boolean isCompatibleElement(TypeInfo otherType)
		def abstract TypeInfo makeReference()
		def abstract String getTypeName()
		def abstract TypeInfo makeVector()
		def abstract TypeInfo makeMatrix()
} 
