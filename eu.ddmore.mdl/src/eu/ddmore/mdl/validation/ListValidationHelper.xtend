package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.Attribute
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.ValuePair
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.List
import org.eclipse.xtend.lib.annotations.Data

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr

class ListValidationHelper {
	static val CATEGORIES_KWD = "categories"
	
	// @TODO: need an addition set of validations to make sure that DVID is used if AMT has a define attribute
	
	@Data
	static class AttributeDefn{
		String name
		String depAtt // other att that must be present
		boolean mandatory
	}
	
	static class ListDefinition {
		String keyValue
		List<AttributeDefn> attributes 
	}
	
	static class BlockListDefinition {
		String key
		List<ListDefinition> listDefns
	}
	
	// owning block -> key attribute -> key value -> attributes associated with key
	static val attDefns = #{ 
		"DATA_INPUT_VARIABLES" -> (
			new BlockListDefinition => [
				key = 'use'
				listDefns = newArrayList(
					new ListDefinition => [  keyValue='covariate' attributes = #[
						 new AttributeDefn('use', null, true), new AttributeDefn('categorical', null, false)//,
//						 new AttributeDefn('use', null, true), new AttributeDefn('type', null, false), new AttributeDefn('categorical', null, false)//,
//						 new AttributeDefn('define', 'categories', false)
						 ] 
					],
					new ListDefinition => [keyValue='amt' attributes = #[
						 new AttributeDefn('use', null, true), new AttributeDefn('define', null, true)
						 ] 
					],
					new ListDefinition => [keyValue='dv' attributes = #[
						 new AttributeDefn('use', null, true), new AttributeDefn('define', null, false)//, new AttributeDefn('variable', null, true)
						 ] 
					],
					new ListDefinition => [keyValue='idv' attributes = #[
						 new AttributeDefn('use', null, true)
						 ] 
					],
					new ListDefinition => [keyValue='cmt' attributes = #[
						 new AttributeDefn('use', null, true)
						 ] 
					],
					new ListDefinition => [keyValue='id' attributes = #[
						 new AttributeDefn('use', null, true)
						 ] 
					],
					new ListDefinition => [keyValue='mdv' attributes = #[
						 new AttributeDefn('use', null, true)
						 ] 
					],
					new ListDefinition => [keyValue='rate' attributes = #[
						 new AttributeDefn('use', null, true)
						 ] 
					],
					new ListDefinition => [keyValue='dvid' attributes = #[
						 new AttributeDefn('use', null, true)
						 ] 
					]
				)
			]
		),
		"DATA_DERIVED_VARIABLES" -> (
			new BlockListDefinition => [
				key = 'column'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null attributes = #[
						 new AttributeDefn('column', null, true), new AttributeDefn('condition', null, false),
						 	new AttributeDefn('categories', null, false), new AttributeDefn('define', 'categories', false) 
						 ] 
					]
				)
			]
		),
		"SOURCE" -> (
			new BlockListDefinition => [
				key = 'file'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null attributes = #[
						 new AttributeDefn('file', null, true), new AttributeDefn('inputformat', null, true),
						 	new AttributeDefn('ignore', null, false) 
						 ] 
					]
				)
			]
		),
		"VARIABILITY_LEVELS" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null attributes = #[
						 new AttributeDefn('type', null, true), new AttributeDefn('level', null, true)
						 ]
					]
				)
			]
		),
		"COMPARTMENT" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue='depot' attributes = #[
						 new AttributeDefn('type', null, true), new AttributeDefn('modelCmt', null, true),
						 new AttributeDefn('output', null, true), new AttributeDefn('ka', null, true),
						 new AttributeDefn('tlag', null, true), new AttributeDefn('finput', null, false)
						 ]
					],
					new ListDefinition => [ keyValue='compartment' attributes = #[
						 new AttributeDefn('type', null, true), new AttributeDefn('modelCmt', null, true)
						 ]
					],
					new ListDefinition => [ keyValue='elimination' attributes = #[
						 new AttributeDefn('type', null, true), new AttributeDefn('modelCmt', null, true),
						 new AttributeDefn('input', null, true), new AttributeDefn('v', null, true),
						 new AttributeDefn('cl', null, true)
						 ]
					],
					new ListDefinition => [ keyValue='distribution' attributes = #[
						 new AttributeDefn('type', null, true), new AttributeDefn('modelCmt', null, true),
						 new AttributeDefn('kin', null, true), new AttributeDefn('kout', null, true),
						 new AttributeDefn('input', null, true)
						 ]
					]
				)
			]
		),
		"DEQ" -> (
			new BlockListDefinition => [
				key = 'deriv'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null attributes = #[
						 new AttributeDefn('deriv', null, true), new AttributeDefn('init', null, false),
						 new AttributeDefn('x0', null, false)
						 ]
					]
				)
			]
		),
		"VARIABILITY" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null attributes = #[
						 new AttributeDefn('type', null, true), new AttributeDefn('parameter', null, true),
						 new AttributeDefn('value', null, true)
						 ]
					]
				)
			]
		),
		"OBSERVATION" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue='categorical' attributes = #[
//						 new AttributeDefn('type', null, true), new AttributeDefn('categories', null, true),
//						 new AttributeDefn('probabilities', null, true)
						 new AttributeDefn('type', null, true) //, new AttributeDefn('predictions', null, true)
						 ]
					]
				)
			]
		),
		"COVARIATES" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue='categorical' attributes = #[
						 new AttributeDefn('type', null, true)//, new AttributeDefn('categories', null, true)
						 ]
					],
					new ListDefinition => [ keyValue='continuous' attributes = #[
						 new AttributeDefn('type', null, true), new AttributeDefn('value', null, true) 
						 ]
					]
				)
			]
		)
	}
	
	def isKeyAttributeDefined(AttributeList it){
				// expect AttributeList->ListDefinition|AnaonolymousListStatement->BlockStatement
		val parent = parentStatement
		if(attDefns.containsKey(parent.identifier)){
			val iter = attributes.iterator
			val expectedAttributes = new ArrayList<ListDefinition>()
			while(iter.hasNext && expectedAttributes.isEmpty){
				val att = iter.next
				val blockDefn = attDefns.get(parent.identifier)
				expectedAttributes.addAll(blockDefn.listDefns.filter[at|
					switch(att){
						ValuePair case att.argumentName == blockDefn.key:{
							if(at.keyValue != null){
								val keyVal = att.expression.convertToString
								keyVal == at.keyValue
							}
							else true
						}
						default: false
					}
				])
			}
			return !expectedAttributes.isEmpty
		}
		true
	}
	
	// Method assumes that there is a key. Check for this first
	def getUnusedMandatoryAttributes(AttributeList it) {
		// expect AttributeList->ListDefinition|AnaonolymousListStatement->BlockStatement
		val parent = parentStatement
		val unused = new HashSet
		if(attDefns.containsKey(parent.identifier)){
			val iter = attributes.iterator
			var expectedAttributes = new ArrayList<ListDefinition>()
			while(iter.hasNext && expectedAttributes.isEmpty){
				val att = iter.next
				val blockDefn = attDefns.get(parent.identifier)
				expectedAttributes.addAll(blockDefn.listDefns.filter[at| 
					switch(att){
						ValuePair case att.argumentName == blockDefn.key:{
							if(at.keyValue != null){
								val keyVal = att.expression.convertToString
								keyVal == at.keyValue
							}
							else true
						}
						default: false
					}
				])
			}
			val listDefinition = expectedAttributes.head
			// define a map of all expected atts based on name
			val expectedAttMap = new HashMap<String, AttributeDefn>();
			listDefinition.attributes.forEach[att| expectedAttMap.put(att.name, att)]
			
			// go through and find if dependencies honoured if not then add missing dep to unused list
			// also check that if dep present the att is present
			expectedAttMap.values.forEach([att|
				if(attributes.attributeExists(att.name)){
					if(att.depAtt != null){
						// exists and has a dependency which must be there
						if(!attributes.attributeExists(att.depAtt)){
							// dep missing so record as a missing att
							unused.add(att.depAtt)
						}
					}
				}
				else if(att.isMandatory){
					// mandatory but missing
					unused.add(att.name)
				}
				else if(att.depAtt != null && attributes.attributeExists(att.depAtt)){
					// att is missing, but its dependency exists
					unused.add(att.name)
				}
			])
//			// got through each attribute and if missing and mandatory then add to missing list
//			expectedAttMap.values.forEach[att|
//				if(att.isMandatory && !attributes.attributeExists(att.name)){
//					unused.add(att.name)
//				}
//			]
		}
		unused
	}
	
	def attributeExists(List<Attribute> attributes, String queryName){
		attributes.exists[attrib|
			switch(attrib){
				ValuePair case attrib.argumentName == queryName: true
				CategoricalDefinitionExpr case CATEGORIES_KWD == queryName: true 
				default: false
			}
		]
	}
	
	def getAttributeName(Attribute attrib){
		switch(attrib){
			ValuePair: attrib.argumentName
			CategoricalDefinitionExpr: CATEGORIES_KWD 
		}
	}

	def getAttributeValueString(Attribute attrib){
		switch(attrib){
			ValuePair: attrib.expression.convertToString
			CategoricalDefinitionExpr: attrib.convertToString
		}
	}

	def attributeDefnExists(ListDefinition it, Attribute att){
		attributes.exists[attrib|
			attrib.name == att.attributeName
		]
	}
	
	def attributeExistsInAnyListDefn(BlockListDefinition it, Attribute att){
		it.listDefns.exists[ld| ld.attributeDefnExists(att)]
	}

	def getListDefnByKeyValue(List<ListDefinition> defns, String queryValue){
		defns.filter[d | d.keyValue == queryValue]
	}


	def getKeyValue(AttributeList attList, String key){
		attList.attributes.findFirst[at | at.attributeName == key].attributeValueString
	}
	
	def getListDefnByKeyValue(BlockListDefinition it, String keyValQuery){
		listDefns.findFirst[defn| defn.keyValue == keyValQuery ]
	}

	def attributeRecognised(Attribute it){
		// expect Attribute->AttributeList->ListDefinition|AnaonolymousListStatement->BlockStatement
		// get key from parent list
//		val parentList = eContainer as AttributeList
//		val parentBlock = eContainer.eContainer.eContainer as BlockStatement
		if (attDefns.containsKey(parentBlock.identifier)) {
			val blkDefn = attDefns.get(parentBlock.identifier)
			val keyVal = parentList.getKeyValue(blkDefn.key)
			val listDefn = blkDefn.getListDefnByKeyValue(keyVal)
			if(listDefn != null){
				// there is a listDefn so check if attribute is in list
				listDefn.attributeDefnExists(it)
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

}