package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.ToString

@ToString
class CategoryValueTypeInfo extends TypeInfo {
	val CategoryTypeInfo owningCategory
	val String valueName
	
	new(CategoryTypeInfo owningCategory, String valueName){
		this.owningCategory = owningCategory
		this.valueName = valueName
	}
	
	def getOwningCategory(){
		owningCategory
	}
	
	def getValueName(){
		valueName
	}
	
	override getTypeClass() {
		TypeInfoClass.CategoryValue
	}
	
	override getUnderlyingType() {
		this
	}
	
	override isCompatible(TypeInfo otherType) {
		switch(otherType){
			CategoryValueTypeInfo:
				this.owningCategory == otherType.owningCategory
				&& this.valueName == otherType.valueName
			ReferenceTypeInfo:
				isCompatible(otherType.underlyingType)
			default:
				false
		}
	}
	
	override isCompatibleElement(TypeInfo otherType) {
		false
	}
	
	override makeReference() {
		new ReferenceTypeInfo(this)
	}
	
	override getTypeName() {
		owningCategory.typeName + ":" + this.valueName
	}
	
	override makeVector() {
		new VectorTypeInfo(this)
	}
	
	override makeMatrix() {
		new MatrixTypeInfo(this)
	}
	
	override boolean equals(Object other){
		var retVal = false
		if(other !== null){
			if(this !== other){
				if(other instanceof CategoryValueTypeInfo){
					retVal = this.owningCategory == other.owningCategory
								&& this.valueName == other.valueName
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
    	val prime = 31;
    	var result = prime + if(this.owningCategory== null)  0 else this.owningCategory.hashCode()
    	result = prime * result + if(this.valueName== null)  0 else this.valueName.hashCode()
    	return result;
	}
}