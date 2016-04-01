package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.ToString

@ToString
class GeneralCategoryValueTypeInfo extends CategoryValueTypeInfo {
	
	new(String valueName){
		super(new UndefinedCategoryTypeInfo, valueName)
	}
	
	override isCompatible(TypeInfo otherType) {
		switch(otherType){
			CategoryValueTypeInfo:
				true
			ReferenceTypeInfo:
				this.isCompatible(otherType.underlyingType)
			RandomVariableTypeInfo:
				this.isCompatible(otherType.rvType)
			default:
				false
		}
	}
	
}