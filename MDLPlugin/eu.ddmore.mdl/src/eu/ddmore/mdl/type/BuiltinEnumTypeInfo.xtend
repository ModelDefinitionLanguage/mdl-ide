package eu.ddmore.mdl.type

import java.util.Collections
import java.util.HashSet
import java.util.Set

class BuiltinEnumTypeInfo extends TypeInfo{
	val TypeInfoClass typeClass
	val String enumName
	val Set<String> categories = new HashSet<String>

	new (String name, Set<String> ev){
		this.typeClass = TypeInfoClass.Builtin
		this.enumName = name
		this.categories.addAll(ev)
	}

	def getExpectedValues(){
		this.categories
	}
	
	def containsValue(String value){
		this.categories.contains(value)
	}
		
	override isCompatible(TypeInfo otherType){
		if(this === otherType) return true
		switch(otherType){
			BuiltinEnumTypeInfo: this.enumName == otherType.enumName
			ReferenceTypeInfo:
				this.isCompatible(otherType.underlyingType)
			default: false 
		}
	}
	
	def boolean isCompatibleCategories(CategoryTypeInfo otherType){
		for(cat : categories){
			if(!otherType.categories.exists[it == cat]) return false
		}
		true
	}
	
	override getTypeClass(){
		this.typeClass
	}
	
	def getCategories(){
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
				if(other instanceof BuiltinEnumTypeInfo){
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
