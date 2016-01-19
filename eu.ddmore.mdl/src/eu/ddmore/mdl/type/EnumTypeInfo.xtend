package eu.ddmore.mdl.type

import java.util.HashSet
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

@Data @FinalFieldsConstructor
class EnumTypeInfo extends PrimitiveTypeInfo{
	String enumName
	Set<String> categories = new HashSet<String>
	
	new(String name, Set<String> categories){
		super(PrimitiveType.Enum)
		enumName = name
		this.categories.addAll(categories)
	}
	
	override isCompatible(TypeInfo otherType){
		if(this === otherType) return true
		switch(otherType){
			EnumTypeInfo: this.enumName == otherType.enumName
				&& isCompatibleCategories(otherType)
			EnumListTypeInfo:
				this.isCompatible(otherType.underlyingEnum)
			default: false 
		}
	}
	
	
	def boolean isCompatibleCategories(EnumTypeInfo otherType){
		for(cat : categories){
			if(!otherType.categories.exists[it == cat]) return false
		}
		true
	}
	
	override getTypeName(){
		"Enum:" + enumName
	}
	
}
