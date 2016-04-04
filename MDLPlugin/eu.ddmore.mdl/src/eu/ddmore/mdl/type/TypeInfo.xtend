package eu.ddmore.mdl.type

abstract class TypeInfo{
	def abstract TypeInfoClass getTypeClass()
	def abstract TypeInfo getUnderlyingType()
	def abstract boolean isCompatible(TypeInfo otherType)
	def abstract boolean isCompatibleElement(TypeInfo otherType)
	def abstract TypeInfo makeReference()
	def abstract String getTypeName()
	def abstract TypeInfo makeVector()
	def abstract TypeInfo makeMatrix()
} 
