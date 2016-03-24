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
import eu.ddmore.mdl.type.ListTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.BlockDefinition
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.Library
import java.util.ArrayList
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.xtext.EcoreUtil2

class ListDefinitionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension MdlLibUtils mlu = new MdlLibUtils
//	extension BlockUtils bu = new BlockUtils


	
	
	

	// owning block -> key attribute -> key value -> attributes associated with key

//	val Map<String, BlockListDefinition> attDefns
//	val Map<String, Map<String, ? extends TypeInfo>> attEnumTypes

	new(){
//		attDefns = ListDefinitionTable::attributeDefnDefaults
////		attEnumTypes = attributeEnumTypeDefaults
//		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
//		buildEnumTypes
	}

//	new(Map<String, BlockListDefinition>  ad, Map<String, Map<String, ? extends TypeInfo>> enumTypes){
//		attDefns = ad
////		attEnumTypes = enumTypes
//		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
//		buildEnumTypes
//	}


//	def void setAttDefns(Map<String, Map<String, ? extends TypeInfo>> newAttDefn){
//		attDefns = newAttDefn
//		buildEnumTypes
//	}
//	
//	private def buildEnumTypes(){
//		for(blkName : attDefns.keySet){
//			val valueMap = new HashMap<String, BuiltinEnumTypeInfo>
//			attEnumTypes.put(blkName, valueMap)
//			val bd = attDefns.get(blkName)
//			for(a : bd.listDefns){
//				for(att : a.attributes){
//					if(att.attType instanceof BuiltinEnumTypeInfo){
//						val builtinType = att.attType as BuiltinEnumTypeInfo
//						for(v : builtinType.expectedValues){
//							valueMap.put(v, builtinType)
//						}
//					}
//				}
//			}
//		}
//		attEnumTypes
//	} 


	private def ValuePair getKeyValuePair(BlockStatement bs, AttributeList al){
		al.attributes.findFirst[argumentName == bs.blkId.keyAttName]
	}

	def ListDefInfo getListDefinition(AttributeList it){
		val parent = parentStatement
		val blkDefn = new BlockListDefinition(parent.blkId)
		val keyAtt = parent.getKeyValuePair(it)
		blkDefn.getListDefnByValue(keyAtt.expression.enumValue)
//		if(attDefns.containsKey(parent.identifier)){
//			val iter = attributes.iterator
//			val expectedAttributes = new ArrayList<ListDefInfo>()
//			while(iter.hasNext && expectedAttributes.isEmpty){
//				val att = iter.next
//				val blockDefn = attDefns.get(parent.identifier)
//				val keyVal = att.expression.enumValue
//				val ld = blockDefn.getListDefnByValue(keyVal)
//				if(ld != null) expectedAttributes.add(ld)
////				expectedAttributes.addAll(blockDefn.listDefns.filter[at|
////					switch(att){
////						ValuePair case att.argumentName == blockDefn.key:{
////							if(blockDefn.keyValue != null){
////								val keyVal = att.expression.enumValue
////								keyVal == blockDefn
////							}
////							else true
////						}
////						default: false
////					}
////				])
//			}
//			return expectedAttributes.head
//		}
//		null
	}	
	

	def getOwningBlock(ValuePair it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	} 
	
	def getOwningListDefinition(ValuePair it){
		EcoreUtil2.getContainerOfType(eContainer, ListDefinition)
	} 
	
	def TypeInfo getAttributeType(ListDefInfo it, String attName){
		attributes.findFirst[ad | 
			ad.name == attName
		]?.attType ?: TypeSystemProvider::UNDEFINED_TYPE 
	}
	
	def getAttributeDefinition(ListDefInfo it, String attName){
		attributes.findFirst[ad | ad.name == attName] 
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
//		val blockName = ee.owningBlock.blkId
		val attList = EcoreUtil2.getContainerOfType(ee.eContainer, AttributeList)
		if(attList != null){
			val listDefn = getListDefinition(attList)
			if(listDefn != null){
				val vp = EcoreUtil2.getContainerOfType(ee.eContainer, ValuePair)
				val enumType = listDefn.getAttributeType(vp.argumentName)
				if(enumType instanceof BuiltinEnumTypeInfo){
					val enumValue = ee.enumValue
					return if(enumType.categories.contains(enumValue)) enumType else TypeSystemProvider::UNDEFINED_TYPE
				}
			} 
		}
		TypeSystemProvider::UNDEFINED_TYPE
//		val enumValue = ee.enumValue
//		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: TypeSystemProvider::UNDEFINED_TYPE
//		defnType
	}

//	def getTypeOfEnumAttribute(String blockName, String attributeName){
//		val defns = attDefns.get(blockName)
//		for(lst :defns.listDefns){
//			val attDefn = lst.attributes.findFirst[a | a.name == attributeName]
//			if(attDefn != null){
//				return attDefn.attType
//			} 
//		}
//		null
//	}

//	def getTypeOfBuiltinEnum(Attribute att){
//		val blockName = att.owningBlock.identifier
//		val enumValue = att.attributeValueString
//		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: MclTypeProvider::UNDEFINED_TYPE
//		defnType
//	}
	
	
	def isKeyAttributeDefined(BlockStatement parent, AttributeList it){
//		val parent = parentStatement
		attributes.exists[parent.blkId.keyAttName == argumentName]
//		var found = false
//		if(attDefns.containsKey(parent.identifier)){
//			val iter = attributes.iterator
//			while(iter.hasNext && !found){
//				val att = iter.next
//				val blockDefn = attDefns.get(parent.identifier)
//				val keyVal = att.expression.enumValue
//				found = att.argumentName == blockDefn.key &&
//					blockDefn.getListDefnByKeyValue(keyVal) != null 
////				
////				found = blockDefn.listDefns.exists[at|
////					switch(att){
////						ValuePair case att.argumentName == blockDefn.key:{
////							if(at.keyValue != null){
////								val keyVal = att.expression.enumValue
////								keyVal == at.keyValue
////							}
////							else true
////						}
////						default: false
////					}
////				]
//			}
//		}
//		return found
	}
	
	def getKeyAttribute(BlockStatement parent, AttributeList it){
//		val parent = parentStatement
		parent.blkId.keyAttName
//		var String retVal = null
//		if(attDefns.containsKey(parent.identifier)){
//			val iter = attributes.iterator
//			while(iter.hasNext && retVal == null){
//				val att = iter.next
//				val blockDefn = attDefns.get(parent.identifier)
//				val keyVal = att.expression.enumValue
//				if(att.argumentName == blockDefn.key &&
//					blockDefn.getListDefnByKeyValue(keyVal) != null){
//						retVal = att.argumentName
//				} 
//				
////				if(blockDefn.listDefns.exists[at|
////					switch(att){
////						ValuePair case att.argumentName == blockDefn.key:{
////							if(at.keyValue != null){
////								val keyVal = att.expression.enumValue
////								keyVal == at.keyValue
////							}
////							else true
////						}
////						default: false
////					}
////				]){
////					retVal = blockDefn.key
////				}
//			}
//		}
//		return retVal
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
		val unused = new HashSet<String>
		val blockDefn = new BlockListDefinition(parent.blkId)
		if(attributes.exists[argumentName == parent.blkId.keyAttName]){
			// key present
			val iter = attributes.iterator
			var expectedAttributes = new ArrayList<ListDefInfo>()
			while(iter.hasNext && expectedAttributes.isEmpty){
				val att = iter.next
				val keyVal = att.expression.enumValue
				val ld = blockDefn.getListDefnByValue(keyVal)
				if(ld != null) expectedAttributes.add(ld)
//				expectedAttributes.addAll(blockDefn.listDefns.filter[at| 
//					switch(att){
//						ValuePair case att.argumentName == blockDefn.key:{
//							if(at.keyValue != null){
//								val keyVal = att.expression.enumValue
//								keyVal == at.keyValue
//							}
//							else true
//						}
//						default: false
//					}
//				])
			}
			if(!expectedAttributes.isEmpty){
				val listDefinition = expectedAttributes.head
				unused.addAll(findUnusedMandatoryArguments(listDefinition))
			}
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
	def ListDefInfo getMatchingListDefn(AttributeList attList){
		val owningBlock = EcoreUtil2.getContainerOfType(attList.eContainer, BlockStatement)
		val blkDefn = new BlockListDefinition(owningBlock.blkId)
//		val listDefns = blkDefn?.listDefns ?: Collections.emptyList
//		listDefns.findFirst[defn|
			for(a : attList.attributes){
				if(a.argumentName == blkDefn.key){
					val lstDefn = blkDefn.getListDefnByValue(a.expression.enumValue)
					if(lstDefn != null) return lstDefn
				}
//				if(blkDefn.key == a.attributeName)
//					if(defn.keyValue == null || defn.keyValue == a.expression.enumValue){
//						return true;
//					}
			} 
//			false
//		]
		null
	}


	def getAllMatchingListDefns(BlockStatement owningBlock, String attName){
		val retVal = new ArrayList<MdlListAttributeDefn>
		val blkDefn = new BlockListDefinition(owningBlock.blkId)
		for(ld : blkDefn?.listDefns ?: Collections::emptyList){
			ld.attributes.forEach[ad|
				if(ad.name == attName) retVal.add(ad) 
			]
		}
		retVal
	}
	
	
	def TypeInfo getListDefinitionByTypeName(BlockDefinition bd, String lstTypeName){
		val lib = EcoreUtil2.getContainerOfType(bd.eContainer, Library)
		val retVal = lib.typeDefns.findFirst[
			name == lstTypeName
		]
		val ti = retVal?.typeInfo
		if(ti instanceof ListTypeInfo){
			ti
		}
		else null
	}

	// gets a list definition based on its key 
	def getListDefnByKeyValue(BlockListDefinition it, String keyValQuery){
		getListDefnByValue(keyValQuery)
//		listDefns.findFirst[defn|
//			// if keyValue is null then there can only be one list defn in the block
//			// so we match it without comparing key values
//			defn.keyValue == null || defn.keyValue == keyValQuery
//		]
	}

//	def	getListDefnByBlockAndKeyValue(String blockName, String keyValQuery){
//		val blkDefn = attDefns.get(blockName)
//		blkDefn.getListDefnByValue(keyValQuery)
////		blkDefn?.listDefns.findFirst[defn| defn.keyValue == keyValQuery ]
//	}
	
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
		val parent = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if (attList.attributes.exists[parent.blkId.keyAttName == argumentName]) {
			val blkDefn = new BlockListDefinition(parent.blkId)
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
			return listDefn.isCatMappingMandatory(ep.argumentName)
		}
		false
	}

	def isMappingForbidden(CategoryValueDefinition it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		val ep = EcoreUtil2.getContainerOfType(eContainer, EnumPair)
		val listDefn = attList?.matchingListDefn
		if(listDefn != null && ep != null){
			return listDefn.isCatMappingForbidden(ep.argumentName)
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
