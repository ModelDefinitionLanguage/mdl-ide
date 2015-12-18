package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

@Data @FinalFieldsConstructor
class ListTypeInfo extends TypeInfo{
	String name
	PrimitiveType apparentType
	
	override getUnderlyingType(){
		this
	}
	
	override isCompatible(TypeInfo other){
		// use underlying type in case it is a reference 
		val otherType = other.underlyingType
		switch(otherType){
			ListTypeInfo:  this.name == otherType.name
			PrimitiveTypeInfo:{
				// @TODO: This breaks encapsulation. Create a more elegant solution that doesn't
				// If other is primitive type then check compatibility
				val compType = PrimitiveTypeInfo::compatibleTypes?.get(this.theType)
				if(compType != null)
					compType.contains(otherType.theType)
				else false
			}
				
			default:
				false 
		}
	}
	
	override isCompatibleElement(TypeInfo elementType){
		false		
	}
	
	override getTheType(){
		apparentType
	}
	
	override getTypeName(){
		"List:" + name
	}
	
	override makeReference(){
		new ReferenceTypeInfo(this)
	}

	override makeVector(){
		new VectorTypeInfo(this);
	}
	
	override isVector(){
		false
	}
	
	override isReference(){
		false
	}
}

