package eu.ddmore.mdl.type

class CategoryListTypeInfo extends ListTypeInfo {
	val CategoryTypeInfo underlyingEnum		

	new(String listName, CategoryTypeInfo underlyingEnum) {
		super(listName)
		this.underlyingEnum = underlyingEnum
	}
	
	new(String listName) {
		super(listName)
		this.underlyingEnum = new UndefinedCategoryTypeInfo
	}
	
	def getUnderlyingEnum(){
		this.underlyingEnum
	}
	
	override isCompatible(TypeInfo otherType){
		switch(otherType){
			CategoryTypeInfo:
				 underlyingEnum.isCompatible(otherType)
			default:
				super.isCompatible(otherType)
		}
	}
	
	def CategoryListTypeInfo populateType(CategoryTypeInfo realEnum){
		new CategoryListTypeInfo(this.name, realEnum)
	}
}


