package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import java.util.Map
import java.util.Set

@Data @FinalFieldsConstructor
class PrimitiveTypeInfo extends TypeInfo{
	protected static val Map<PrimitiveType, Set<PrimitiveType>> compatibleTypes = #{
		PrimitiveType.Deriv -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.Deriv },
		PrimitiveType.Real -> #{ PrimitiveType.Real, PrimitiveType.Int, PrimitiveType.Deriv },
		PrimitiveType.Int -> #{ PrimitiveType.Int },
		PrimitiveType.String -> #{ PrimitiveType.String },
		PrimitiveType.Boolean -> #{ PrimitiveType.Boolean },
		PrimitiveType.Pdf -> #{ PrimitiveType.Pdf },
		PrimitiveType.Pmf -> #{ PrimitiveType.Pmf },
		PrimitiveType.Mapping -> #{ PrimitiveType.Mapping },
		PrimitiveType.Undefined -> #{  }
	}

	PrimitiveType theType
	
	override boolean isCompatible(TypeInfo other){
		// use underlying type in case it is a reference 
		val otherType = other.underlyingType
		switch(otherType){
			ListTypeInfo:
				isPrimitiveCompatible(this.theType, otherType.secondaryType)
			default:{
				isPrimitiveCompatible(this.theType, otherType.theType)
			}
				
		}
	}
	
	static def isPrimitiveCompatible(PrimitiveType thisType, PrimitiveType otherType){
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
		theType.toString
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
	
//	override isVector(){
//		false
//	}
//	
//	override isReference(){
//		false
//	}
}
