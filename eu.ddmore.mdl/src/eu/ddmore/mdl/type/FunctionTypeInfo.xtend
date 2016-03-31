package eu.ddmore.mdl.type

import eu.ddmore.mdl.type.TypeInfo
import java.util.List

import org.eclipse.xtend.lib.annotations.Data

@Data // using this to get an equals implementation on all the fields
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
	
}