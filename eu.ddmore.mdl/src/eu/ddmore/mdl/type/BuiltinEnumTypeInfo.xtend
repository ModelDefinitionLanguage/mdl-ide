package eu.ddmore.mdl.type

import java.util.Set

class BuiltinEnumTypeInfo extends EnumTypeInfo{

	new (String name, Set<String> ev){
		super(name, ev)
	}

	def getExpectedValues(){
		super.categories
	}
	
	def containsValue(String value){
		this.categories.contains(value)
	}		
}

