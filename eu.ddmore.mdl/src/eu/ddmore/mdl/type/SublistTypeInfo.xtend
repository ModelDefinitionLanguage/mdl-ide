package eu.ddmore.mdl.type

import eu.ddmore.mdl.provider.AttributeDefn
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.SubListTypeDefinition
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.ToString

@ToString
class SublistTypeInfo extends TypeInfo {
	extension MdlLibUtils mlu = new MdlLibUtils
	
	val String name
	val TypeInfoClass theType
	val List<AttributeDefn> attributes
	val List<Map<String, Boolean>> nameSets 

	new(SubListTypeDefinition sd){
		name = sd.name
		theType = TypeInfoClass.Sublist
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

	new(String name, List<AttributeDefn> atts, List<Map<String, Boolean>> ns){
		this.name = name
		theType = TypeInfoClass.Sublist
		this.attributes = new ArrayList<AttributeDefn>
		this.attributes.addAll(atts)
		this.nameSets = new ArrayList<Map<String, Boolean>>
		ns.forEach[
			val sigMap = new HashMap<String, Boolean>
			keySet.forEach[sigMap.put(it, sigMap.get(it))]
			this.nameSets.add(sigMap)
		] 
	}

	def getName(){
		this.name
	}
	
	override getTypeClass(){
		this.theType
	}
	
	def getAttributes(){
		Collections::unmodifiableList(this.attributes)
	}
	
	def getNameSets(){
		Collections::unmodifiableList(this.nameSets)
	}
	
	
	override getUnderlyingType(){
		this
	}
	
	override isCompatible(TypeInfo other){
		if(other != null){
			// use underlying type in case it is a reference 
			val otherType = other.underlyingType
			switch(otherType){
				SublistTypeInfo: this.name == otherType.name
				default:
					false 
			}
		}
		else false
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

	// equality is based on the list name
	override boolean equals(Object other){
		var retVal = false
		if(other !== null){
			if(this !== other){
				if(other instanceof SublistTypeInfo){
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
