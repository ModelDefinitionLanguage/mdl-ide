package eu.ddmore.mdl.type

import java.util.List
import org.eclipse.xtend.lib.annotations.ToString

@ToString
class FunctionTypeInfo extends TypeInfo {
	val TypeInfoClass typeClass
	val List<TypeInfo> argTypes
	val TypeInfo rtnType
	
	new(List<TypeInfo> argTypes, TypeInfo rtnType){
		this.typeClass = TypeInfoClass.Function
		this.argTypes = argTypes
		this.rtnType = rtnType
	}
	
	override getTypeClass() {
		this.typeClass
	}
	
	override getUnderlyingType() {
		this
	}
	
	override isCompatible(TypeInfo otherType) {
		if(otherType instanceof FunctionTypeInfo){
			val otherIter = otherType.argTypes.iterator
			for(arg : this.argTypes){
				if(otherIter.hasNext){
					val otherArg = otherIter.next
					if(otherArg != arg){
						return false
					}
				}
				else return false // diff num args
			}
			if(otherIter.hasNext) return false // diff num args
			if(this.rtnType != otherType.rtnType){
				return false // diff return args
			}
		}
		true
	}
	
	override isCompatibleElement(TypeInfo otherType) {
		false
	}
	
	override makeReference() {
		new ReferenceTypeInfo(this)
	}
	
	override getTypeName() {
		var argStr = '''«FOR a : argTypes SEPARATOR ','»«a.typeName»«ENDFOR»'''
		"Function(" + argStr + ")::" + rtnType.typeName
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
				if(other instanceof FunctionTypeInfo){
					retVal = this.typeClass == other.typeClass
								&& this.rtnType == other.rtnType
								&& this.argTypes == other.argTypes
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
    	val prime = 31;
    	var result = prime + if(this.typeClass == null)  0 else this.typeClass.hashCode()
    	result = prime * result + if(this.rtnType == null)  0 else this.rtnType.hashCode()
    	result = prime * result + if(this.argTypes == null)  0 else this.argTypes.hashCode()
    	return result;
	}
}