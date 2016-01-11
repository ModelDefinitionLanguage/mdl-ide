package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

	@Data @FinalFieldsConstructor
class MatrixTypeInfo extends TypeInfo{
	PrimitiveType theType
	TypeInfo elementType
	
	new(TypeInfo elementType){
		this(PrimitiveType.Vector, elementType)
	}
	
	override getUnderlyingType(){
		this
	}
	
	override boolean isCompatible(TypeInfo other){
		// use underlying type in case it is a reference 
		val otherType = other.underlyingType
		switch(otherType){
			// if both vectors then check type compatibility
			MatrixTypeInfo: elementType.isCompatible(otherType.elementType)
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
		new VectorTypeInfo(theType, elementType);
	}
	
	override makeMatrix(){
		new MatrixTypeInfo(theType, elementType);
	}
	
//	override isVector(){
//		false
//	}
//	
//	override isReference(){
//		false
//	}
}

