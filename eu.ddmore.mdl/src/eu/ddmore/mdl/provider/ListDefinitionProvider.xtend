package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdllib.mdllib.Expression
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

class ListDefinitionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils


	
	@Data @FinalFieldsConstructor
	static class AttributeDefn{
		String name
//		String depAtt // other att that must be present
		boolean mandatory
		TypeInfo attType
		TypeInfo catMappingType
		boolean catMappingMandatory
		
//		new(String name, String depAtt, boolean mand, TypeInfo attType){
//			this(name, depAtt, mand, attType, null, false)
//		}
		
		new(String name, boolean mand, TypeInfo attType){
			this(name, mand, attType, null, false)
		}
		
		def isCatMappingMandatory(){
			return catMappingType != null && catMappingMandatory
		}
		
		def isCatMappingForbidden(){
			return catMappingType == null || (catMappingType != null && !catMappingMandatory)
		}
		
		def isCatMappingPossible(){
			return catMappingType != null
		}
	}
	
	@Data @FinalFieldsConstructor
	static class ListDefInfo {
		val String keyValue
		TypeInfo listType
		List<AttributeDefn> attributes
		List<Map<String, Boolean>> attributeSets
		boolean isAnonymous
		
		new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes){
			this(keyValue, listType, attributes, false)
		}
		
		new(String keyValue, TypeInfo listType, List<AttributeDefn> attributes, boolean isListAnonymous){
			this.keyValue = keyValue
			this.listType = listType
			this.attributes = attributes
			attributeSets = new ArrayList<Map<String, Boolean>>()
			val onlySet = new HashMap<String, Boolean>
			attributeSets.add(onlySet)
			for(att : attributes){
				onlySet.put(att.name, att.mandatory)
			}
			isAnonymous = isListAnonymous
		}
	}
	
	@Data @FinalFieldsConstructor
	static class BlockListDefinition {
		String key
		List<ListDefInfo> listDefns
	}

	// owning block -> key attribute -> key value -> attributes associated with key

	val Map<String, BlockListDefinition> attDefns
	val Map<String, Map<String, ? extends TypeInfo>> attEnumTypes

	new(){
		attDefns = ListDefinitionTable::attributeDefnDefaults
//		attEnumTypes = attributeEnumTypeDefaults
		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
		buildEnumTypes
	}

//	new(Map<String, BlockListDefinition>  ad, Map<String, Map<String, ? extends TypeInfo>> enumTypes){
//		attDefns = ad
////		attEnumTypes = enumTypes
//		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
//		buildEnumTypes
//	}


	def void setAttDefns(Map<String, Map<String, ? extends TypeInfo>> newAttDefn){
		attDefns = newAttDefn
		buildEnumTypes
	}

	private def buildEnumTypes(){
		for(blkName : attDefns.keySet){
			val valueMap = new HashMap<String, BuiltinEnumTypeInfo>
			attEnumTypes.put(blkName, valueMap)
			val bd = attDefns.get(blkName)
			for(a : bd.listDefns){
				for(att : a.attributes){
					if(att.attType instanceof BuiltinEnumTypeInfo){
						val builtinType = att.attType as BuiltinEnumTypeInfo
						for(v : builtinType.expectedValues){
							valueMap.put(v, builtinType)
						}
					}
				}
			}
		}
		attEnumTypes
	} 

	def ListDefInfo getListDefinition(AttributeList it){
		val parent = parentStatement
		if(attDefns.containsKey(parent.identifier)){
			val iter = attributes.iterator
			val expectedAttributes = new ArrayList<ListDefInfo>()
			while(iter.hasNext && expectedAttributes.isEmpty){
				val att = iter.next
				val blockDefn = attDefns.get(parent.identifier)
				expectedAttributes.addAll(blockDefn.listDefns.filter[at|
					switch(att){
						ValuePair case att.argumentName == blockDefn.key:{
							if(at.keyValue != null){
								val keyVal = att.expression.enumValue
								keyVal == at.keyValue
							}
							else true
						}
						default: false
					}
				])
			}
			return expectedAttributes.head
		}
		null
	}	
	

	def getOwningBlock(ValuePair it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	} 
	
	def getOwningListDefinition(ValuePair it){
		EcoreUtil2.getContainerOfType(eContainer, ListDefinition)
	} 
	
	def TypeInfo getAttributeType(ListDefInfo it, String attName){
		attributes.findFirst(ad | ad.name == attName)?.attType ?: TypeSystemProvider::UNDEFINED_TYPE 
	}
	
	def getAttributeDefinition(ListDefInfo it, String attName){
		attributes.findFirst(ad | ad.name == attName) 
	}
	
	
	def TypeInfo getAttributeType(ValuePair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if(attList != null){
			val listDefn = attList.matchingListDefn
			listDefn?.getAttributeType(attributeName) ?: TypeSystemProvider::UNDEFINED_TYPE
		}
		else TypeSystemProvider::UNDEFINED_TYPE
	}
	
	def TypeInfo getTypeOfAttributeBuiltinEnum(EnumExpression ee){
		val blockName = ee.owningBlock.identifier
		val enumValue = ee.enumValue
		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: TypeSystemProvider::UNDEFINED_TYPE
		defnType
	}

	def getTypeOfEnumAttribute(String blockName, String attributeName){
		val defns = attDefns.get(blockName)
		for(lst :defns.listDefns){
			val attDefn = lst.attributes.findFirst[a | a.name == attributeName]
			if(attDefn != null){
				return attDefn.attType
			} 
		}
		null
	}

//	def getTypeOfBuiltinEnum(Attribute att){
//		val blockName = att.owningBlock.identifier
//		val enumValue = att.attributeValueString
//		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: MclTypeProvider::UNDEFINED_TYPE
//		defnType
//	}
	
	
	def isKeyAttributeDefined(AttributeList it){
		val parent = parentStatement
		var found = false
		if(attDefns.containsKey(parent.identifier)){
			val iter = attributes.iterator
			while(iter.hasNext && !found){
				val att = iter.next
				val blockDefn = attDefns.get(parent.identifier)
				found = blockDefn.listDefns.exists[at|
					switch(att){
						ValuePair case att.argumentName == blockDefn.key:{
							if(at.keyValue != null){
								val keyVal = att.expression.enumValue
								keyVal == at.keyValue
							}
							else true
						}
						default: false
					}
				]
			}
		}
		return found
	}
	
	def getKeyAttribute(AttributeList it){
		val parent = parentStatement
		var String retVal = null
		if(attDefns.containsKey(parent.identifier)){
			val iter = attributes.iterator
			while(iter.hasNext && retVal == null){
				val att = iter.next
				val blockDefn = attDefns.get(parent.identifier)
				if(blockDefn.listDefns.exists[at|
					switch(att){
						ValuePair case att.argumentName == blockDefn.key:{
							if(at.keyValue != null){
								val keyVal = att.expression.enumValue
								keyVal == at.keyValue
							}
							else true
						}
						default: false
					}
				]){
					retVal = blockDefn.key
				}
			}
		}
		return retVal
	}


	def Set<String> findUnusedMandatoryArguments(AttributeList sle, ListDefInfo listDefn){
		val attNames = sle.attributeNames
		val unusedMandatoryAtts = new HashSet<String>()
		for(nameSet : listDefn.attributeSets){
			unusedMandatoryAtts.clear
			nameSet.forEach[name, mand| if(mand) unusedMandatoryAtts.add(name)]
			val iter = attNames.iterator
			while(iter.hasNext && !unusedMandatoryAtts.isEmpty){
				val at = iter.next
				unusedMandatoryAtts.remove(at)
			}
			// all mandatory atts used up so report this 
			if(unusedMandatoryAtts.isEmpty) return unusedMandatoryAtts
		}
		// normally get here is can't match any list in which case report the unused mandatory attributes
		unusedMandatoryAtts
	}

	// Method assumes that there is a key. Check for this first
	def getUnusedMandatoryAttributes(AttributeList it) {
		// expect AttributeList->ListDefinition|AnaonolymousListStatement->BlockStatement
		val parent = parentStatement
		val unused = new HashSet
		if(attDefns.containsKey(parent.identifier)){
			val iter = attributes.iterator
			var expectedAttributes = new ArrayList<ListDefInfo>()
			while(iter.hasNext && expectedAttributes.isEmpty){
				val att = iter.next
				val blockDefn = attDefns.get(parent.identifier)
				expectedAttributes.addAll(blockDefn.listDefns.filter[at| 
					switch(att){
						ValuePair case att.argumentName == blockDefn.key:{
							if(at.keyValue != null){
								val keyVal = att.expression.enumValue
								keyVal == at.keyValue
							}
							else true
						}
						default: false
					}
				])
			}
			val listDefinition = expectedAttributes.head
			unused.addAll(findUnusedMandatoryArguments(listDefinition))
		}
		unused
	}


	def isAttributeDuplicated(AttributeList owningList, ValuePair it){
		if(owningList != null){
			return owningList.attributes.filter[a| a.argumentName == argumentName].size > 1 
		}
		false
	}
	
	
//	// Method assumes that there is a key. Check for this first
//	def getUnusedMandatoryAttributes(AttributeList it) {
//		// expect AttributeList->ListDefinition|AnaonolymousListStatement->BlockStatement
//		val parent = parentStatement
//		val unused = new HashSet
//		if(attDefns.containsKey(parent.identifier)){
//			val iter = attributes.iterator
//			var expectedAttributes = new ArrayList<ListDefInfo>()
//			while(iter.hasNext && expectedAttributes.isEmpty){
//				val att = iter.next
//				val blockDefn = attDefns.get(parent.identifier)
//				expectedAttributes.addAll(blockDefn.listDefns.filter[at| 
//					switch(att){
//						ValuePair case att.argumentName == blockDefn.key:{
//							if(at.keyValue != null){
//								val keyVal = att.expression.convertToString
//								keyVal == at.keyValue
//							}
//							else true
//						}
//						default: false
//					}
//				])
//			}
//			val listDefinition = expectedAttributes.head
//			// define a map of all expected atts based on name
//			val expectedAttMap = new HashMap<String, AttributeDefn>();
//			listDefinition.attributes.forEach[att| expectedAttMap.put(att.name, att)]
//			
//			// go through and find if dependencies honoured if not then add missing dep to unused list
//			// also check that if dep present the att is present
//			expectedAttMap.values.forEach([att|
//				if(attributes.attributeExists(att.name)){
//					if(att.depAtt != null){
//						// exists and has a dependency which must be there
//						if(!attributes.attributeExists(att.depAtt)){
//							// dep missing so record as a missing att
//							unused.add(att.depAtt)
//						}
//					}
//				}
//				else if(att.isMandatory){
//					// mandatory but missing
//					unused.add(att.name)
//				}
//				else if(att.depAtt != null && attributes.attributeExists(att.depAtt)){
//					// att is missing, but its dependency exists
//					unused.add(att.name)
//				}
//			])
////			// got through each attribute and if missing and mandatory then add to missing list
////			expectedAttMap.values.forEach[att|
////				if(att.isMandatory && !attributes.attributeExists(att.name)){
////					unused.add(att.name)
////				}
////			]
//		}
//		unused
//	}
	
	private def attributeExists(List<ValuePair> attributes, String queryName){
		attributes.exists[attrib|
			switch(attrib){
				ValuePair case attrib.argumentName == queryName: true
//				CategoricalDefinitionExpr case CATEGORIES_KWD == queryName: true 
				default: false
			}
		]
	}
	
	def hasAttribute(AttributeList it, String name){
		attributeExists(attributes, name)
	}
	
	def getAttributeName(ValuePair attrib){
		switch(attrib){
			ValuePair: attrib.argumentName
//			CategoricalDefinitionExpr: CATEGORIES_KWD 
		}
	}

	def List<String> getAttributeNames(AttributeList it){
		val attNames = new ArrayList<String>
		attributes.forEach([at|attNames.add(at.argumentName)])
		attNames
	}

//	def getAttributeValueString(ValuePair attrib){
//		switch(attrib){
//			ValuePair: attrib.expression?.convertToString
////			CategoricalDefinitionExpr: attrib.convertToString
//		}
//	}

	def attributeDefnExists(ListDefInfo it, ValuePair att){
		attributes.exists[attrib|
			attrib.name == att.attributeName
		]
	}
	
	def attributeExistsInAnyListDefn(BlockListDefinition it, ValuePair att){
		it.listDefns.exists[ld| ld.attributeDefnExists(att)]
	}

//	def getListDefnByKeyValue(List<ListDefInfo> defns, String queryValue){
//		defns.filter[d | d.keyValue == queryValue]
//	}


//	// gets the key value of an attribute list
//	def getKeyValue(AttributeList attList, String key){
//		attList.attributes.findFirst[at | at.attributeName == key].attributeValueString
//	}
	
	// gets a list definition based on a key value combination 
	def getMatchingListDefn(AttributeList attList){
		val owningBlock = EcoreUtil2.getContainerOfType(attList.eContainer, BlockStatement)
		val blkDefn = attDefns.get(owningBlock.identifier)
		val listDefns = blkDefn?.listDefns ?: Collections.emptyList
		listDefns.findFirst[defn|
			for(a : attList.attributes){
				if(blkDefn.key == a.attributeName)
					if(defn.keyValue == null || defn.keyValue == a.expression.enumValue){
						return true;
					}
			} 
			false
		]
	}


	def getAllMatchingListDefns(BlockStatement owningBlock, String attName){
		val retVal = new ArrayList<AttributeDefn>
		val blkDefn = attDefns.get(owningBlock.identifier)
		for(ld : blkDefn?.listDefns ?: Collections::emptyList){
			ld.attributes.forEach[ad|
				if(ad.name == attName) retVal.add(ad) 
			]
		}
		retVal
	}

	// gets a list definition based on its key 
	def getListDefnByKeyValue(BlockListDefinition it, String keyValQuery){
		listDefns.findFirst[defn|
			// if keyValue is null then there can only be one list defn in the block
			// so we match it without comparing key values
			defn.keyValue == null || defn.keyValue == keyValQuery
		]
	}

	def	getListDefnByBlockAndKeyValue(String blockName, String keyValQuery){
		val blkDefn = attDefns.get(blockName)
		blkDefn?.listDefns.findFirst[defn| defn.keyValue == keyValQuery ]
	}
	
	def getAttributeExpression(AttributeList it, String attName){
		attributes.findFirst[argumentName == attName]?.expression
	}

	def getAttributeEnumValue(AttributeList it, String attName){
		val enumExp = getAttributeExpression(attName)
		enumExp?.enumValue
//		switch(enumExp){
//			EnumExpression:
//				return enumExp.enumValue
//			default: null
//		}
	}
	
	def getAttributeExpessionAsString(AttributeList it, String attName){
		val expr = getAttributeExpression(attName)
		if(expr instanceof StringLiteral){
			return expr.value
		}
		return null
	}

	def getEnumValue(Expression expr){
		if(expr instanceof EnumExpression){
			return expr.enumValue
		}
		else null
	}
	
	def getStringValue(Expression expr){
		if(expr instanceof StringLiteral){
			return expr.value
		}
		return null
	} 
	

	def Map<String, Boolean> findMatchingAttributeSet(AttributeList it, ListDefInfo defn){
		// find the best matching att set
		var Map<String, Boolean> retVal = Collections::emptyMap 
		var maxMatchCnt = -1 // make it -1 so that if no matches are found later the max value is still set
		for(attSet : defn.attributeSets){
			val nameList = new HashSet<String>(attSet.keySet)
			var matchCnt = 0;
			for(attName : attributeNames){
				if(nameList.remove(attName)){
					matchCnt += 1
				}
			}
//			if(nameList.isEmpty){
//				// everything matched
//				return attSet
//			}
//			else 
			if(matchCnt > maxMatchCnt){
				// store best match to be returned a better one not found
				maxMatchCnt = matchCnt
				retVal = attSet
			}
		}
		retVal
	}

	def isAttributeRecognised(ValuePair it){
		// expect Attribute->AttributeList->ListDefInfo|AnaonolymousListStatement->BlockStatement
		// get key from parent list
		if (attDefns.containsKey(parentBlock.identifier)) {
			val blkDefn = attDefns.get(parentBlock.identifier)
			val keyVal = parentList.getAttributeEnumValue(blkDefn.key)
			val listDefn = blkDefn.getListDefnByKeyValue(keyVal)
			if(listDefn != null){
				// there is a listDefn so check if attribute is in list
//				listDefn.attributeDefnExists(it)
				parentList.findMatchingAttributeSet(listDefn).keySet.exists[n|argumentName == n]
			}
			else{
				// no list definition so need to check all attributes in list definition
				blkDefn.attributeExistsInAnyListDefn(it)
			} 
		}
		else{
			false
		}
	}

	def isMappingMandatory(CategoryValueDefinition it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		val ep = EcoreUtil2.getContainerOfType(eContainer, EnumPair)
		val listDefn = attList?.matchingListDefn
		if(listDefn != null && ep != null){
			val attDefn = listDefn.getAttributeDefinition(ep.argumentName)
			return attDefn.isCatMappingMandatory
		}
		false
	}

	def isMappingForbidden(CategoryValueDefinition it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		val ep = EcoreUtil2.getContainerOfType(eContainer, EnumPair)
		val listDefn = attList?.matchingListDefn
		if(listDefn != null && ep != null){
			val attDefn = listDefn.getAttributeDefinition(ep.argumentName)
			return attDefn.isCatMappingForbidden
		}
		false
	}

	def isAnonymousListExpected(AttributeList it){
		val listDefn = matchingListDefn
		if(listDefn != null){
			listDefn.isAnonymous
		}
		else{
			// is can't get the definition (presumably because the list is mal-formed) then assume no.
			false
		}
	}

	def isNamedListExpected(AttributeList it){
		val listDefn = matchingListDefn
		if(listDefn != null){
			!listDefn.isAnonymous
		}
		else{
			// is can't get the definition (presumably because the list is mal-formed) then no.
			false
		}
	}

}
