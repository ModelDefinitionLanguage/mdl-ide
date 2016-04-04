package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.ToString

@ToString
abstract class AbstractListTypeInfo extends TypeInfo{
	val String name
	val TypeInfoClass secondaryType
	
	protected new(String name){
		this(name, TypeInfoClass.Undefined)
	}
	
	protected new(String name, TypeInfoClass secondaryType){
		this.name = name
		this.secondaryType = secondaryType 
	}
	
	override getUnderlyingType(){
		this
	}
	
	abstract def boolean matchesList(AbstractListTypeInfo other)
	
	override isCompatible(TypeInfo other){
		if(other != null){
			// use underlying type in case it is a reference 
			val otherType = other.underlyingType
			switch(otherType){
				AbstractListTypeInfo:  otherType.matchesList(this)
				PrimitiveTypeInfo:{
					PrimitiveTypeInfo::isPrimitiveCompatible(this.secondaryType, otherType.typeClass)
				}
					
				default:
					false 
			}
		}
		else false
	}
	
	def protected getName(){
		this.name
	}
	
	def getSecondaryType(){
		secondaryType
	}
	
	def abstract ListSuperTypeInfo getListSuperType()
	
	override isCompatibleElement(TypeInfo elementType){
		false		
	}
	
	override getTypeClass(){
		TypeInfoClass.List
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
	
	// equality is based on the list name
	override boolean equals(Object other){
		var retVal = false
		if(other !== null){
			if(this !== other){
				if(other instanceof AbstractListTypeInfo){
					retVal = this.name == other.name
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
	    val int prime = 31
	    var result = prime + if(this.name== null)  0 else this.name.hashCode()
	    result
	}
}

