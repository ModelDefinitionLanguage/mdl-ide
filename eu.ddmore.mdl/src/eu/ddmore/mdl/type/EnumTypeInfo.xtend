package eu.ddmore.mdl.type

import java.util.HashSet
import java.util.Set
import java.util.Collections

import org.eclipse.xtend.lib.annotations.ToString

@ToString
class EnumTypeInfo extends TypeInfo{
	val TypeInfoClass typeClass
	val String enumName
	val Set<String> categories = new HashSet<String>
	
	new(String name, Set<String> categories){
		this.typeClass = TypeInfoClass.Enum
		enumName = name
		this.categories.addAll(categories)
	}
	
	override isCompatible(TypeInfo otherType){
		if(this === otherType) return true
		switch(otherType){
			EnumTypeInfo: this.enumName == otherType.enumName
				&& isCompatibleCategories(otherType)
			EnumListTypeInfo:
				this.isCompatible(otherType.underlyingEnum)
			ReferenceTypeInfo:
				this.isCompatible(otherType.underlyingType)
			default: false 
		}
	}
	
	
	def boolean isCompatibleCategories(EnumTypeInfo otherType){
		for(cat : categories){
			if(!otherType.categories.exists[it == cat]) return false
		}
		true
	}
	
	override getTypeClass(){
		this.typeClass
	}
	
	def protected getCategories(){
		Collections::unmodifiableSet(this.categories);
	}
	
	override getTypeName(){
		"Enum:" + enumName
	}
	
	override isCompatibleElement(TypeInfo elementType){
		// no vectors in this class so always false
		false		
	}
	
	override getUnderlyingType(){
		this
	}
	
	override makeReference(){
		new ReferenceTypeInfo(this);
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
				if(other instanceof EnumTypeInfo){
					retVal = this.enumName == other.enumName
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
	    val int prime = 31
	    var result = prime + if(this.enumName == null)  0 else this.enumName.hashCode()
	    result
	}
}
