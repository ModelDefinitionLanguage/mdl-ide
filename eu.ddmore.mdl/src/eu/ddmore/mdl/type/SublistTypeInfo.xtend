package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import java.util.List
import eu.ddmore.mdl.provider.ListDefinitionProvider.AttributeDefn
import java.util.Map
import java.util.ArrayList

@Data @FinalFieldsConstructor
class SublistTypeInfo extends TypeInfo {
	String name
	PrimitiveType theType
	List<AttributeDefn> attributes
	List<Map<String, Boolean>> nameSets 
	
	new(String name, List<AttributeDefn> attributes, List<Map<String, Boolean>> nameSets){
		//@TODO copy nameset list properly
		this(name, PrimitiveType.Sublist, new ArrayList<AttributeDefn>(attributes), nameSets)
	}
	
	override getUnderlyingType(){
		this
	}
	
	override isCompatible(TypeInfo other){
		// use underlying type in case it is a reference 
		val otherType = other.underlyingType
		switch(otherType){
			SublistTypeInfo: this.name == otherType.name
			default:
				false 
		}
	}
	
	override isCompatibleElement(TypeInfo elementType){
		false		
	}
	
	override getTypeName(){
		"Sublist:" + name
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
