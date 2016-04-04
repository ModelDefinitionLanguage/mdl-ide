package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.ToString

@ToString
class ReferenceTypeInfo extends TypeInfo{
	val TypeInfo elementType
	
	
	new(TypeInfo refElement){
		this.elementType = refElement
	}
	
	override getUnderlyingType(){
		elementType
	}
	
	override getTypeClass(){
//		TypeInfoClass.Reference
		//@TODO: This is wrong. Should really return the class of the ref type. But this break everything just now.
		this.underlyingType.typeClass
	}
	
	override boolean isCompatible(TypeInfo otherType){
		elementType.isCompatible(otherType?.underlyingType)
	}
	
	override isCompatibleElement(TypeInfo otherElementType){
		false
	}
	
	override getTypeName(){
		'ref:' + elementType.typeName
	}
	
	override makeReference(){
		this
	}
	
	override makeVector(){
		new VectorTypeInfo(this);
	}
	
	override makeMatrix(){
		new MatrixTypeInfo(this)
	}

	// equality is based on the elementType
	override boolean equals(Object other){
		var retVal = false
		if(other !== null){
			if(this !== other){
				if(other instanceof ReferenceTypeInfo){
					retVal = this.elementType == other.elementType
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
	    val int prime = 31
	    var result = prime + if(this.elementType == null)  0 else this.elementType.hashCode()
	    result
	}
}
