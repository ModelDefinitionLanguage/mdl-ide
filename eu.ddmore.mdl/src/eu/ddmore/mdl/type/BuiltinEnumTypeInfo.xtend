package eu.ddmore.mdl.type

import java.util.Set
import org.eclipse.xtend.lib.annotations.Data

@Data 
class BuiltinEnumTypeInfo extends EnumTypeInfo{

	new (String name, Set<String> ev){
		super(name, ev)
	}

	def getExpectedValues(){
		super.categories
	}		
}

