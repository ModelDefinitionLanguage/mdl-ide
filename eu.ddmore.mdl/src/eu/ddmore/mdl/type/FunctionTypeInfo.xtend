package eu.ddmore.mdl.type

import eu.ddmore.mdl.type.TypeInfo
import java.util.List

class FunctionTypeInfo extends TypeInfo {
	val PrimitiveType theType
	val List<TypeInfo> argTypes
	val TypeInfo rtnType
	
	new(List<TypeInfo> argTypes, TypeInfo rtnType){
		this.theType = PrimitiveType.Function
		this.argTypes = argTypes
		this.rtnType = rtnType
	}
	
	override getTheType() {
		this.theType
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
	
}