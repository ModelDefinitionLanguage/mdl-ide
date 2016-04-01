package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.ToString
import com.google.common.base.Preconditions

@ToString
class RandomVariableTypeInfo extends TypeInfo {
	val TypeInfo rvType 
	
	new(TypeInfo rvType){
		Preconditions.checkArgument(rvType != null && rvType.class != RandomVariableTypeInfo)
		this.rvType = rvType
	}
	
	override getTypeClass() {
		TypeInfoClass.RandomVariable
	}
	
	def getRvType(){
		this.rvType
	}
	
	override getUnderlyingType() {
		this
	}
	
	override isCompatible(TypeInfo otherType) {
		if(otherType != null){
			switch(otherType){
				RandomVariableTypeInfo:
					this.rvType.isCompatible(otherType.rvType)
				default:
					this.rvType.isCompatible(otherType.underlyingType)
			}
		}
		else false
	}
	
	override isCompatibleElement(TypeInfo otherType) {
		false
	}
	
	override makeReference() {
		new ReferenceTypeInfo(this)
	}
	
	override getTypeName() {
		"RV:" + rvType.typeName
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
				if(other instanceof RandomVariableTypeInfo){
					retVal = this.rvType == other.rvType
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
	    val int prime = 31
	    var result = prime + if(this.rvType== null)  0 else this.rvType.hashCode()
	    result
	}
}