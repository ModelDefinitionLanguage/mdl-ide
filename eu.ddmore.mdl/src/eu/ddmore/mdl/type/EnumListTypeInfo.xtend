package eu.ddmore.mdl.type

class EnumListTypeInfo extends ListTypeInfo {
	val EnumTypeInfo underlyingEnum		

	new(String listName, EnumTypeInfo underlyingEnum) {
		super(listName)
		this.underlyingEnum = underlyingEnum
	}
	
	new(String listName) {
		super(listName)
		this.underlyingEnum = new GenericEnumTypeInfo
	}
	
	def getUnderlyingEnum(){
		this.underlyingEnum
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
		new EnumListTypeInfo(this.name, realEnum)
	}
}


