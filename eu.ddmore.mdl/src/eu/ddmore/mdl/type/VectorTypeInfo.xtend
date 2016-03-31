package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.ToString

@ToString
class VectorTypeInfo extends TypeInfo{
	val TypeInfoClass typeClass
	val TypeInfo elementType
	
	new(TypeInfo elementType){
		this.typeClass = TypeInfoClass.Vector
		this.elementType = elementType
	}
	
	def getElementType(){
		this.elementType
	}
	
	override getUnderlyingType(){
		this
	}
	
	override boolean isCompatible(TypeInfo other){
		// use underlying type in case it is a reference 
		val otherType = other.underlyingType
		switch(otherType){
			// if both vectors then check type compatibility
			VectorTypeInfo: elementType.isCompatible(otherType.elementType)
			default: false
		}
	}
	
	override isCompatibleElement(TypeInfo otherElementType){
		this.elementType.isCompatible(otherElementType)
	}
	
	override getTypeName(){
		'vector:' + elementType.typeName
	}
	
	override makeReference(){
		new ReferenceTypeInfo(this);
	}
	
	override makeVector(){
		new VectorTypeInfo(this);
	}
	
	override makeMatrix(){
		new MatrixTypeInfo(this)
	}

	// equality is based on the list name
	override boolean equals(Object other){
		var retVal = false
		if(other !== null){
			if(this !== other){
				if(other instanceof VectorTypeInfo){
					retVal = this.elementType == other.elementType
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
	    val int prime = 31
	    var result = prime + if(this.elementType== null)  0 else this.elementType.hashCode()
	    result
	}
	
	override getTypeClass() {
		this.typeClass
	}
	
}

