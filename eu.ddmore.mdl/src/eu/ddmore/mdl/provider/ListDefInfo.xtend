package eu.ddmore.mdl.provider

import eu.ddmore.mdl.type.TypeInfo
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

@Data @FinalFieldsConstructor
class ListDefInfo {
//		val String keyValue
	TypeInfo listType
	List<AttributeDefn> attributes
	List<Map<String, Boolean>> attributeSets
	boolean isAnonymous
	String catAttribute
	TypeInfo catMappingType
	boolean catMappingMandatory
	
	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes){
		this(keyValue, listType, attributes, false, null, null, false)
	}
	
	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, List<Map<String, Boolean>> attributeSets){
//			this(keyValue, listType, attributes, attributeSets, false, null, null, false)
		this(listType, attributes, attributeSets, false, null, null, false)
	}

	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, String catAtt, TypeInfo catType, boolean catMapMand){
		this(keyValue, listType, attributes, false, catAtt, catType, catMapMand)
	}

	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, boolean isListAnonymous, String catAtt, TypeInfo catType, boolean catMapMand){
//			this.keyValue = keyValue
		this.listType = listType
		this.attributes = attributes
		attributeSets = new ArrayList<Map<String, Boolean>>()
		val onlySet = new HashMap<String, Boolean>
		attributeSets.add(onlySet)
		for(att : attributes){
			onlySet.put(att.name, att.mandatory)
		}
		isAnonymous = isListAnonymous
		this.catAttribute = catAtt
		this.catMappingType = catType
		this.catMappingMandatory = catMapMand
	}

	new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, List<Map<String, Boolean>> attributeSets, boolean isListAnonymous){
//			this.keyValue = keyValue
		this.listType = listType
		this.attributes = attributes
		this.attributeSets = attributeSets
		this.isAnonymous = isListAnonymous
		this.catAttribute = null
		this.catMappingType = null
		this.catMappingMandatory = false
	}


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
