package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data

@Data
abstract class AbstractListTypeInfo extends TypeInfo{
	val String name
	
	protected new(String name){
		this.name = name
	}
	
	
	override getUnderlyingType(){
		this
	}
	
	abstract def boolean matchesList(AbstractListTypeInfo other)
	
	override isCompatible(TypeInfo other){
		// use underlying type in case it is a reference 
		val otherType = other.underlyingType
		switch(otherType){
			ListTypeInfo:  otherType.matchesList(this)
			PrimitiveTypeInfo:{
				PrimitiveTypeInfo::isPrimitiveCompatible(this.secondaryType, otherType.theType)
			}
				
			default:
				false 
		}
	}
	
	def abstract PrimitiveType getSecondaryType()
	
	def abstract ListSuperTypeInfo getListSuperType()
	
	override isCompatibleElement(TypeInfo elementType){
		false		
	}
	
	override getTheType(){
		PrimitiveType.List
	}
	
	override makeReference(){
		new ReferenceTypeInfo(this)
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
//		false
//	}
}

