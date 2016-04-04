package eu.ddmore.mdl.type

import java.util.Set
import org.eclipse.xtend.lib.annotations.ToString

@ToString
class CategoryTypeInfo extends AbstractCategoryTypeInfo {
	
	new(String name, Set<String> categories){
		super(name, categories)
	}
	
	override isCompatible(TypeInfo otherType){
		if(this === otherType) return true
		switch(otherType){
			CategoryTypeInfo: this.enumName == otherType.enumName
							&& isCompatibleCategories(otherType)
			CategoryListTypeInfo:
				this.isCompatible(otherType.underlyingEnum)
			RandomVariableTypeInfo:
				this.isCompatible(otherType.rvType)
			ReferenceTypeInfo:
				this.isCompatible(otherType.underlyingType)
			default: false 
		}
	}
	
	
	def boolean isCompatibleCategories(CategoryTypeInfo otherType){
		for(cat : categories){
			if(!otherType.categories.exists[it == cat]) return false
		}
		true
	}
	
	
}
