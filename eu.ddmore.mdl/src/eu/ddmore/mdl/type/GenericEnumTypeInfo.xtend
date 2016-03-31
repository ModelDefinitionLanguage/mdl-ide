package eu.ddmore.mdl.type

import java.util.Collections

class GenericEnumTypeInfo extends EnumTypeInfo{
	public static val GENERIC_ENUM_TYPE_NAME = "generic"
	
	
	new(){
		super(GENERIC_ENUM_TYPE_NAME, Collections::emptySet)
	}
	
	override isCompatible(TypeInfo otherType){
		if(otherType != null){
			switch(otherType.underlyingType){
				EnumTypeInfo: true
				default: false 
			}
		}
		else false
	}
	
}
