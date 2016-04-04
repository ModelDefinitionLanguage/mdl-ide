package eu.ddmore.mdl.type

class GeneralCategoryTypeInfo extends AbstractCategoryTypeInfo {
	public static val GENERIC_ENUM_TYPE_NAME = "generic"
	
	
	new(String name){
		super(name)
	}

	new(){
		super(GENERIC_ENUM_TYPE_NAME)
	}
	
	override isCompatible(TypeInfo otherType){
		if(otherType != null){
			switch(otherType){
				CategoryTypeInfo: true
				ReferenceTypeInfo:
					this.isCompatible(otherType.underlyingType)
				RandomVariableTypeInfo:
					this.isCompatible(otherType.rvType)
				default: false 
			}
		}
		else false
	}
	
}
