package eu.ddmore.mdl.type

import eu.ddmore.mdl.type.TypeInfo

import org.eclipse.xtend.lib.annotations.Data

@Data // using this to get an equals implementation on all the fields
class RandomVariableTypeInfo extends TypeInfo {
	
	
	
	override getTypeClass() {
		TypeInfoClass.RandomVariable
	}
	
	override getUnderlyingType() {
		return this
	}
	
	override isCompatible(TypeInfo otherType) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override isCompatibleElement(TypeInfo otherType) {
		false
	}
	
	override makeReference() {
		new ReferenceTypeInfo(this)
	}
	
	override getTypeName() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override makeVector() {
		new VectorTypeInfo(this)
	}
	
	override makeMatrix() {
		new MatrixTypeInfo(this)
	}
	
}