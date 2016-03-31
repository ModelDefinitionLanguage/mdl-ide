package eu.ddmore.mdl.type

class GeneralCategoryTypeInfo extends AbstractCategoryTypeInfo {
	public static val GENERIC_ENUM_TYPE_NAME = "generic"
	
	
	new(){
		super(GENERIC_ENUM_TYPE_NAME)
	}
	
	override isCompatible(TypeInfo otherType){
		if(otherType != null){
			switch(otherType.underlyingType){
				CategoryTypeInfo: true
				default: false 
			}
		}
		else false
	}
	
}
