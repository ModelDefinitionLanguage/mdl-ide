package eu.ddmore.mdl.provider

import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.ListTypeDefinition
import java.util.ArrayList
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import java.util.HashMap

@Data 
class ListDefInfo {
	extension MdlLibUtils mlu = new MdlLibUtils
	
	
//		val String keyValue
	val ListTypeDefinition _ltd
	val TypeInfo listType
	val List<MdlListAttributeDefn> attributes
	val List<Map<String, Boolean>> attributeSets
//	val boolean isAnonymous
//	val String catAttribute
	val TypeInfo catMappingType
//	val boolean catMappingMandatory
	
	new(ListTypeDefinition ltd){
		this._ltd = ltd
		this.listType = ltd.typeInfo
		this.attributes = new ArrayList<MdlListAttributeDefn>
		ltd.attributes.forEach[ attributes.add(new MdlListAttributeDefn(it)) ]
		this.attributeSets = new ArrayList<Map<String, Boolean>>
		ltd.sigLists.forEach[ls|
			val asMap = new HashMap<String, Boolean>
			attributeSets.add(asMap)
			ls.attRefs.forEach[asMap.put(attRef.name, !optional)]
		]
//		this.catAttribute = ltd.catAtt.name
		this.catMappingType = ltd.catMapType?.typeInfo
//		this.catMappingMandatory = !ltd.catMappingOptional
	}
	
	
	def isAnonymous(){
		_ltd.isAnonymous
	}
	
	def isCatMappingMandatory(){
		!this._ltd.catMappingOptional
	}
	
	def getCatAttribute(){
		_ltd.catAtt?.name
	}
	
	
//	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes){
//		this(keyValue, listType, attributes, false, null, null, false)
//	}
//	
//	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, List<Map<String, Boolean>> attributeSets){
////			this(keyValue, listType, attributes, attributeSets, false, null, null, false)
//		this(listType, attributes, attributeSets, false, null, null, false)
//	}
//
//	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, String catAtt, TypeInfo catType, boolean catMapMand){
//		this(keyValue, listType, attributes, false, catAtt, catType, catMapMand)
//	}
//
//	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, boolean isListAnonymous, String catAtt, TypeInfo catType, boolean catMapMand){
////			this.keyValue = keyValue
//		this.listType = listType
//		this.attributes = attributes
//		attributeSets = new ArrayList<Map<String, Boolean>>()
//		val onlySet = new HashMap<String, Boolean>
//		attributeSets.add(onlySet)
//		for(att : attributes){
//			onlySet.put(att.name, att.mandatory)
//		}
//		isAnonymous = isListAnonymous
//		this.catAttribute = catAtt
//		this.catMappingType = catType
//		this.catMappingMandatory = catMapMand
//	}
//
//	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, List<Map<String, Boolean>> attributeSets, boolean isListAnonymous){
////			this.keyValue = keyValue
//		this.listType = listType
//		this.attributes = attributes
//		this.attributeSets = attributeSets
//		this.isAnonymous = isListAnonymous
//		this.catAttribute = null
//		this.catMappingType = null
//		this.catMappingMandatory = false
//	}


	def isCatMappingMandatory(String attName){
		return attName == catAttribute && catMappingType != null && catMappingMandatory
	}
	
	def isCatMappingForbidden(String attName){
		return attName == catAttribute && catMappingType == null || (catMappingType != null && !catMappingMandatory)
	}
	
	def isCatMappingPossible(String attName){
		return attName == catAttribute && catMappingType != null
	}
}
