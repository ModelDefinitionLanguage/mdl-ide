package eu.ddmore.mdl.type

import java.util.Collections

class UndefinedCategoryTypeInfo extends CategoryTypeInfo {
	
	new(){
		super("Undefined", Collections::emptySet)
	}
	
	override isCompatible(TypeInfo otherType) {
		false
	}
	
}