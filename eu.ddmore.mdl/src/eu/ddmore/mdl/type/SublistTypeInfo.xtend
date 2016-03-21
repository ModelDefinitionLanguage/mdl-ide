package eu.ddmore.mdl.type

import eu.ddmore.mdl.provider.AttributeDefn
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.SubListTypeDefinition
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.List
import java.util.Map

//@Data @FinalFieldsConstructor
class SublistTypeInfo extends TypeInfo {
	extension MdlLibUtils mlu = new MdlLibUtils
	
	val String name
	val PrimitiveType theType
	val List<AttributeDefn> attributes
	val List<Map<String, Boolean>> nameSets 

	new(SubListTypeDefinition sd){
		name = sd.name
		theType = PrimitiveType.Sublist
		this.attributes = new ArrayList<AttributeDefn>
		sd.attributes.forEach[
			val ti = it.attType.typeInfo
			this.attributes.add(new AttributeDefn(it.name, false, ti))
		]
		this.nameSets = new ArrayList<Map<String, Boolean>>
		sd.sigLists.forEach[
			val sigMap = new HashMap<String, Boolean>
			attRefs.forEach[ar|sigMap.put(ar.attRef.name, !ar.optional)]
			this.nameSets.add(sigMap)
		] 
	}

	def getName(){
		this.name
	}
	
	override getTheType(){
		this.theType
	}
	
	def getAttributes(){
		Collections::unmodifiableList(this.attributes)
	}
	
	def getNameSets(){
		Collections::unmodifiableList(this.nameSets)
	}
	
	
//	new(String name, List<AttributeDefn> attributes, List<Map<String, Boolean>> nameSets){
//		//@TODO copy nameset list properly
//		this(name, PrimitiveType.Sublist, new ArrayList<AttributeDefn>(attributes), nameSets)
//	}
	
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
