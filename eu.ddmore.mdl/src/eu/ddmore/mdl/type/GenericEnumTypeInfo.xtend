package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import java.util.Collections

@Data @FinalFieldsConstructor
class GenericEnumTypeInfo extends EnumTypeInfo{
	static val GENERIC_ENUM_TYPE_NAME = "generic"
	
	
	new(){
		super(GENERIC_ENUM_TYPE_NAME, Collections::emptySet)
	}
	
	override isCompatible(TypeInfo otherType){
		switch(otherType){
			EnumTypeInfo: true
			default: false 
		}
	}
	
}
