package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data

@Data
class ListTypeInfo extends TypeInfo{
	val String name
	val PrimitiveType secondaryType
	
	new(String name){
		this.name = name
		this.secondaryType = PrimitiveType.Undefined 
	}
	
	new(String name, PrimitiveType secondaryType){
		this.name = name
		this.secondaryType = secondaryType 
	}
	
	override getUnderlyingType(){
		this
	}
	
	override isCompatible(TypeInfo other){
		// use underlying type in case it is a reference 
		val otherType = other.underlyingType
		switch(otherType){
			ListTypeInfo:  this.name == otherType.name
			PrimitiveTypeInfo:{
				PrimitiveTypeInfo::isPrimitiveCompatible(this.secondaryType, otherType.theType)
			}
				
			default:
				false 
		}
	}
	
	def getSecondaryType(){
		secondaryType
	}
	
	override isCompatibleElement(TypeInfo elementType){
		false		
	}
	
	override getTheType(){
//		apparentType.theType
		PrimitiveType.List
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

