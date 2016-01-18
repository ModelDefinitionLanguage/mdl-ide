package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

@Data @FinalFieldsConstructor
class ReferenceTypeInfo extends TypeInfo{
	TypeInfo elementType
	
	override getUnderlyingType(){
		elementType
	}
	
	override getTheType(){
		elementType.theType
	}
	
	override boolean isCompatible(TypeInfo otherType){
		switch(otherType){
			// if both refs then check type compatibility otherwise ref -> non-ref is incompatible 
			ReferenceTypeInfo: elementType.isCompatible(otherType.elementType)
			default: false
		}
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
	
//	override isVector(){
//		false
//	}
//	
//	override isReference(){
//		true
//	}
}
