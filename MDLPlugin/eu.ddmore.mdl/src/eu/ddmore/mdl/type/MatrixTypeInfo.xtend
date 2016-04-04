package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.ToString

@ToString
class MatrixTypeInfo extends TypeInfo{
	val TypeInfoClass typeClass
	val TypeInfo elementType
	
	new(TypeInfo elementType){
		typeClass = TypeInfoClass.Matrix
		this.elementType = elementType
	}
	
	def getElementType(){
		this.elementType
	}
	
	override getUnderlyingType(){
		this
	}
	
	override boolean isCompatible(TypeInfo otherType){
		// use underlying type in case it is a reference 
//		val otherType = other.underlyingType
		if(otherType != null){
			switch(otherType){
				// if both vectors then check type compatibility
				MatrixTypeInfo: elementType.isCompatible(otherType.elementType)
				ReferenceTypeInfo:
					this.isCompatible(otherType.underlyingType)
				RandomVariableTypeInfo:
					this.isCompatible(otherType.rvType)
				default: false
			}
		}
		else false
	}
	
	override isCompatibleElement(TypeInfo otherElementType){
		this.elementType.isCompatible(otherElementType)
	}
	
	override getTypeName(){
		'matrix:' + elementType.typeName
	}
	
	override makeReference(){
		new ReferenceTypeInfo(this);
	}
	
	override makeVector(){
		new VectorTypeInfo(this);
	}
	
	override makeMatrix(){
		new MatrixTypeInfo(this);
	}


	// equality is based on the list name
	override boolean equals(Object other){
		var retVal = false
		if(other !== null){
			if(this !== other){
				if(other instanceof MatrixTypeInfo){
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

