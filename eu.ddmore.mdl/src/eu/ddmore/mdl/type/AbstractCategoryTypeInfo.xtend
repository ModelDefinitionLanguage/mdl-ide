package eu.ddmore.mdl.type

import java.util.HashSet
import java.util.Set
import java.util.Collections

import org.eclipse.xtend.lib.annotations.ToString

@ToString
abstract class AbstractCategoryTypeInfo extends TypeInfo{
	val TypeInfoClass typeClass
	val String enumName
	val Set<String> categories
	
	protected new(String name){
		this.typeClass = TypeInfoClass.Category
		enumName = name
		this.categories = Collections::emptySet
	}
	
	protected new(String name, Set<String> categories){
		this.typeClass = TypeInfoClass.Category
		enumName = name
		this.categories = new HashSet<String>(categories)
	}
	
//	override isCompatible(TypeInfo otherType){
//		if(this === otherType) return true
//		switch(otherType){
//			AbstractCategoryTypeInfo: this.enumName == otherType.enumName
//							&& isCompatibleCategories(otherType)
//			ReferenceTypeInfo:
//				this.isCompatible(otherType.underlyingType)
//			default: false 
//		}
//	}
	
	def String getEnumName(){
		this.enumName
	}
	
	def boolean isCompatibleCategories(AbstractCategoryTypeInfo otherType){
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
		"Category:" + enumName
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
				if(other instanceof AbstractCategoryTypeInfo){
					retVal = this.enumName == other.enumName
								&& isCompatibleCategories(other)
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
