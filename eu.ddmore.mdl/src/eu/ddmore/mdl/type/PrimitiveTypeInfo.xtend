package eu.ddmore.mdl.type

import java.util.Map
import java.util.Set

import org.eclipse.xtend.lib.annotations.ToString

@ToString
class PrimitiveTypeInfo extends TypeInfo{
	val protected static Map<TypeInfoClass, Set<TypeInfoClass>> compatibleTypes = #{
		TypeInfoClass.Deriv -> #{ TypeInfoClass.Real, TypeInfoClass.Int, TypeInfoClass.Deriv },
		TypeInfoClass.Real -> #{ TypeInfoClass.Real, TypeInfoClass.Int, TypeInfoClass.Deriv },
		TypeInfoClass.Int -> #{ TypeInfoClass.Int },
		TypeInfoClass.String -> #{ TypeInfoClass.String },
		TypeInfoClass.Boolean -> #{ TypeInfoClass.Boolean },
		TypeInfoClass.Pdf -> #{ TypeInfoClass.Pdf },
		TypeInfoClass.Pmf -> #{ TypeInfoClass.Pmf },
		TypeInfoClass.Mapping -> #{ TypeInfoClass.Mapping },
		TypeInfoClass.Undefined -> #{  }
	}

	val TypeInfoClass typeClass
	
	new(TypeInfoClass typeClass){
		this.typeClass = typeClass
	}
	
	override getTypeClass(){
		this.typeClass
	}
	
	override boolean isCompatible(TypeInfo otherType){
		if(otherType != null){
			// use underlying type in case it is a reference 
//			val otherType = other.underlyingType
			switch(otherType){
				AbstractListTypeInfo:
					isPrimitiveCompatible(this.getTypeClass, otherType.secondaryType)
				RandomVariableTypeInfo:
					isCompatible(otherType.rvType)
				ReferenceTypeInfo:
					isCompatible(otherType.underlyingType)
				default:{
					isPrimitiveCompatible(this.getTypeClass, otherType.typeClass)
				}
					
			}
		}
		else false
	}
	
	static def isPrimitiveCompatible(TypeInfoClass thisType, TypeInfoClass otherType){
		val compType = compatibleTypes?.get(thisType)
		if(compType != null)
			// check the underlying type in case this is a reference or some other form of indirection
			compType.contains(otherType)
		else false
	}
	
	
	override isCompatibleElement(TypeInfo elementType){
		// no vectors in this class so always false
		false		
	}
	
	override getUnderlyingType(){
		this
	}
	
	override getTypeName(){
		getTypeClass.toString
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
				if(other instanceof PrimitiveTypeInfo){
					retVal = this.typeClass == other.typeClass
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
	    val int prime = 31
	    var result = prime + if(this.typeClass == null)  0 else this.typeClass.hashCode()
	    result
	}

}
