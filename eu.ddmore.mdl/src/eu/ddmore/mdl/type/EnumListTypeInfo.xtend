package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data

@Data
class EnumListTypeInfo extends ListTypeInfo {
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


