package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.Attribute
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.CategoricalDefinition
import eu.ddmore.mdl.mdl.ValuePair
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.List
import org.eclipse.xtend.lib.annotations.Data

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

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
		String key
		String keyValue
		List<AttributeDefn> attributes 
	}
	
	// owning block -> key attribute -> key value -> attributes associated with key
	static val attDefns = #{ 
		"DATA_INPUT_VARIABLES" -> #[
				new ListDefinition => [ key= 'use' keyValue='covariate' attributes = #[
					 new AttributeDefn('use', null, true), new AttributeDefn('type', null, false), new AttributeDefn('categories', null, false),
					 new AttributeDefn('define', 'categories', false)
					 ] 
				],
				new ListDefinition => [ key= 'use' keyValue='amt' attributes = #[
					 new AttributeDefn('use', null, true), new AttributeDefn('define', null, false), new AttributeDefn('variable', null, false)
					 ] 
				],
				new ListDefinition => [ key= 'use' keyValue='dv' attributes = #[
					 new AttributeDefn('use', null, true), new AttributeDefn('variable', null, false)
					 ] 
				],
				new ListDefinition => [ key= 'use' keyValue='idv' attributes = #[
					 new AttributeDefn('use', null, true)
					 ] 
				],
				new ListDefinition => [ key= 'use' keyValue='id' attributes = #[
					 new AttributeDefn('use', null, true)
					 ] 
				],
				new ListDefinition => [ key= 'use' keyValue='mdv' attributes = #[
					 new AttributeDefn('use', null, true)
					 ] 
				],
				new ListDefinition => [ key= 'use' keyValue='rate' attributes = #[
					 new AttributeDefn('use', null, true)
					 ] 
				],
				new ListDefinition => [ key= 'use' keyValue='dvid' attributes = #[
					 new AttributeDefn('use', null, true), new AttributeDefn('define', null, true)
					 ] 
				]
			],
		"DATA_DERIVED_VARIABLES" -> #[
				new ListDefinition => [ key= 'column' keyValue=null attributes = #[
					 new AttributeDefn('column', null, true), new AttributeDefn('condition', null, false),
					 	new AttributeDefn('categories', null, false), new AttributeDefn('define', 'categories', false) 
					 ] 
				]
			]
		}
	
	def isKeyAttributeDefined(AttributeList it){
				// expect AttributeList->ListDefinition|AnaonolymousListStatement->BlockStatement
		val parent = eContainer.eContainer as BlockStatement
		if(attDefns.containsKey(parent.identifier)){
			val iter = attributes.iterator
			var expectedAttributes = new ArrayList<ListDefinition>()
			while(iter.hasNext && expectedAttributes.isEmpty){
				val att = iter.next
				expectedAttributes.addAll(attDefns.get(parent.identifier).filter[at| 
					switch(att){
						ValuePair case att.argumentName == at.key:{
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
		val parent = eContainer.eContainer as BlockStatement
		val unused = new HashSet
		if(attDefns.containsKey(parent.identifier)){
			val iter = attributes.iterator
			var expectedAttributes = new ArrayList<ListDefinition>()
			while(iter.hasNext && expectedAttributes.isEmpty){
				val att = iter.next
				expectedAttributes.addAll(attDefns.get(parent.identifier).filter[at| 
					switch(att){
						ValuePair case att.argumentName == at.key:{
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
				CategoricalDefinition case CATEGORIES_KWD == queryName: true 
				default: false
			}
		]
	}

}