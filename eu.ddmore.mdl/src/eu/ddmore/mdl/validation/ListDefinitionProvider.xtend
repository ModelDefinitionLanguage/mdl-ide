package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.ListTypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.PrimitiveType
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.TypeProperty
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class ListDefinitionProvider {
//	static val CATEGORIES_KWD = "categories"
	
	static val USE_TYPE = new BuiltinEnumTypeInfo('use', #['covariate', 'amt', 'dv', 'dvid', 'cmt', 'mdv', 'idv', 'id', 'rate'])
	static val VARIABILITY_TYPE_TYPE = new BuiltinEnumTypeInfo('type', #['parameter', 'observation'])
	static val INPUT_FORMAT_TYPE = new BuiltinEnumTypeInfo('input', #['nonmemFormat'])
	static val COMP_TYPE_TYPE = new BuiltinEnumTypeInfo('cmpt', #['depot', 'compartment', 'elimination', 'transfer', 'distribution'])
	static val PARAM_VAR_TYPE_TYPE = new BuiltinEnumTypeInfo('vartype', #['COV', 'CORR'])
	static val OBS_TYPE_TYPE = new BuiltinEnumTypeInfo('obstype', #['categorical', 'count', 'discrete'])
	static val SAMPLING_TYPE_TYPE = new BuiltinEnumTypeInfo('sampletype', #['simple', 'complex'])
//	static val SAMPLE_OUTCOME_TYPE = new BuiltinEnumTypeInfo('sampouttype', #['conc', 'effect'])
	static val ELEMENT_TYPE = new BuiltinEnumTypeInfo('sampleelement', #['amount', 'duration', 'sampleTime', 'numberTimes'])
	
	static val COMP_LIST_TYPE = new ListTypeInfo("Compartment", PrimitiveType.Real)
	static val IDV_COL_TYPE = new ListTypeInfo("Idv", PrimitiveType.List)
	static val ADMINISTRATION_TYPE = new ListTypeInfo("Administration", PrimitiveType.List, TypeProperty.None)
	static val SAMPLING_TYPE = new ListTypeInfo("Sampling", PrimitiveType.List, TypeProperty.None)
	
	// @TODO: need an addition set of validations to make sure that DVID is used if AMT has a define attribute
	
	@Data @FinalFieldsConstructor
	static class AttributeDefn{
		String name
		String depAtt // other att that must be present
		boolean mandatory
		TypeInfo attType
		
//		new(String name, String depAtt, boolean mand){
//			this(name, depAtt, mand, MclTypeProvider::REAL_TYPE)
//		}
	}
	
	static class ListDefinition {
		String keyValue
		TypeInfo listType
		List<AttributeDefn> attributes 
	}
	
	static class BlockListDefinition {
		String key
		List<ListDefinition> listDefns
	}

//	static val Map<String, Map<String, ? extends TypeInfo>> attributeEnumTypeDefaults = #{
//		"DATA_INPUT_VARIABLES" -> #{
//				'covariate' -> USE_TYPE,
//				'amt' -> USE_TYPE,
//				'dv' -> USE_TYPE,
//				'dvid' -> USE_TYPE,
//				'mdv' -> USE_TYPE,
//				'cmt' -> USE_TYPE,
//				'idv' -> USE_TYPE,
//				'id' -> USE_TYPE,
//				'rate' -> USE_TYPE
//			}
//		 
//	}	

	// owning block -> key attribute -> key value -> attributes associated with key
	static val Map<String, BlockListDefinition> attributeDefnDefaults = #{ 
		"DATA_INPUT_VARIABLES" -> (
			new BlockListDefinition => [
				key = 'use'
				listDefns = newArrayList(
					new ListDefinition => [  keyValue='covariate' listType = new ListTypeInfo("Covariate", PrimitiveType.Real) attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					],
					new ListDefinition => [keyValue='amt' listType = new ListTypeInfo("Amt", PrimitiveType.Real) attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE), new AttributeDefn('define', null, false, MclTypeProvider::MAPPING_TYPE),
						 new AttributeDefn('variable', null, false, MclTypeProvider::REAL_TYPE.markReference)
						 ] 
					],
					new ListDefinition => [keyValue='dv' listType = new ListTypeInfo("Dv", PrimitiveType.List) attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE), new AttributeDefn('define', null, false, MclTypeProvider::MAPPING_TYPE),
						 new AttributeDefn('variable', null, false, MclTypeProvider::REAL_TYPE.markReference)
						 ] 
					],
					new ListDefinition => [keyValue='idv' listType = IDV_COL_TYPE attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					],
					new ListDefinition => [keyValue='cmt' listType = new ListTypeInfo("Cmt", PrimitiveType.List) attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					],
					new ListDefinition => [keyValue='id' listType = new ListTypeInfo("Id", PrimitiveType.List) attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					],
					new ListDefinition => [keyValue='mdv' listType = new ListTypeInfo("Mdv", PrimitiveType.List) attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					],
					new ListDefinition => [keyValue='rate' listType = new ListTypeInfo("Rate", PrimitiveType.List) attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					],
					new ListDefinition => [keyValue='dvid' listType = new ListTypeInfo("Dvid", PrimitiveType.List) attributes = #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					]
				)
			]
		),
		"DATA_DERIVED_VARIABLES" -> (
			new BlockListDefinition => [
				key = 'column'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null  listType = new ListTypeInfo("Column", PrimitiveType.List) attributes = #[
						 new AttributeDefn('column', null, true, IDV_COL_TYPE), new AttributeDefn('condition', null, false, MclTypeProvider::BOOLEAN_TYPE) 
						 ] 
					]
				)
			]
		),
		"SOURCE" -> (
			new BlockListDefinition => [
				key = 'file'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null  listType = new ListTypeInfo("Source", PrimitiveType.List) attributes = #[
						 new AttributeDefn('file', null, true, MclTypeProvider::STRING_TYPE), new AttributeDefn('inputFormat', null, true, INPUT_FORMAT_TYPE),
						 	new AttributeDefn('ignore', null, false, MclTypeProvider::STRING_TYPE) 
						 ] 
					]
				)
			]
		),
		"VARIABILITY_LEVELS" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null listType = new ListTypeInfo("VarLevel", PrimitiveType.List) attributes = #[
						 new AttributeDefn('type', null, true, VARIABILITY_TYPE_TYPE), new AttributeDefn('level', null, true, MclTypeProvider::INT_TYPE)
						 ]
					]
				)
			]
		),
		"COMPARTMENT" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue='depot' listType = new ListTypeInfo("Depot", PrimitiveType.Real) attributes = #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, true, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('to', null, true, ListDefinitionProvider.COMP_LIST_TYPE.markReference), new AttributeDefn('ka', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('tlag', null, true, MclTypeProvider::REAL_TYPE), new AttributeDefn('finput', null, false, MclTypeProvider::REAL_TYPE)
						 ]
					],
					new ListDefinition => [ keyValue='compartment' listType = new ListTypeInfo("Compartment", PrimitiveType.Real) attributes = #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, true, MclTypeProvider::INT_TYPE)
						 ]
					],
					new ListDefinition => [ keyValue='elimination' listType = new ListTypeInfo("Elimination", PrimitiveType.Real) attributes = #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, true, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('from', null, true, ListDefinitionProvider.COMP_LIST_TYPE.markReference), new AttributeDefn('v', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('cl', null, true, MclTypeProvider::REAL_TYPE)
						 ]
					],
					new ListDefinition => [ keyValue='distribution' listType = new ListTypeInfo("Distribution", PrimitiveType.Real) attributes = #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, true, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('kin', null, true, MclTypeProvider::REAL_TYPE), new AttributeDefn('kout', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('from', null, true, ListDefinitionProvider.COMP_LIST_TYPE.markReference)
						 ]
					]
				)
			]
		),
		"DEQ" -> (
			new BlockListDefinition => [
				key = 'deriv'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null listType = new ListTypeInfo("Derivative", PrimitiveType.Real, TypeProperty.Deriv) attributes = #[
						 new AttributeDefn('deriv', null, true, MclTypeProvider::REAL_TYPE), new AttributeDefn('init', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('x0', null, false, MclTypeProvider::REAL_TYPE)
						 ]
					]
				)
			]
		),
		"VARIABILITY" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null listType = new ListTypeInfo("CovarianceMat", PrimitiveType.List, TypeProperty.None) attributes = #[
						 new AttributeDefn('type', null, true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('parameter', null, true, MclTypeProvider::REAL_VECTOR_TYPE),
						 new AttributeDefn('value', null, true, MclTypeProvider::REAL_VECTOR_TYPE)
						 ]
					]
				)
			]
		),
		"OBSERVATION" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue='categorical' listType = new ListTypeInfo("CatObs", PrimitiveType.Int, TypeProperty.None) attributes = #[
						 new AttributeDefn('type', null, true, OBS_TYPE_TYPE)
						 ]
					]
				)
			]
		),
		"ADMINISTRATION" -> (
			new BlockListDefinition => [
				key = 'adm'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null listType = ADMINISTRATION_TYPE attributes = #[
						 new AttributeDefn('adm', null, true, MclTypeProvider::REAL_TYPE.markReference) , new AttributeDefn('amount', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('doseTime', null, false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('duration', null, false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('start', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('end', null, false, MclTypeProvider::REAL_TYPE)
						 ]
					]
				)
			]
		),
		"STUDY_DESIGN" -> (
			new BlockListDefinition => [
				key = 'armSize'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null listType = new ListTypeInfo("StudyDesign", PrimitiveType.List, TypeProperty.None) attributes = #[
						 new AttributeDefn('armSize', null, true, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('interventionSequence', null, true, MclTypeProvider::MAPPING_TYPE),
						 new AttributeDefn('samplingSequence', null, false, MclTypeProvider::MAPPING_TYPE)
						 ]
					]
				)
			]
		),
		"DESIGN_SPACES" -> (
			new BlockListDefinition => [
				key = 'name'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue=null listType = new ListTypeInfo("DesignSpace", PrimitiveType.List, TypeProperty.None) attributes = #[
						 new AttributeDefn('name', null, true, SAMPLING_TYPE.makeVector), new AttributeDefn('element', null, true, ELEMENT_TYPE),
						 new AttributeDefn('discrete', null, false, MclTypeProvider::INT_TYPE.makeVector),
						 new AttributeDefn('range', null, false, MclTypeProvider::REAL_TYPE.makeVector)
						 ]
					]
				)
			]
		),
		"SAMPLING" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefinition => [ keyValue='simple' listType = SAMPLING_TYPE attributes = #[
						 new AttributeDefn('type', null, true, SAMPLING_TYPE_TYPE), new AttributeDefn('outcome', null, true, MclTypeProvider::REAL_TYPE.markReference),
						 new AttributeDefn('sampleTime', null, false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('numberSamples', null, false, MclTypeProvider::INT_TYPE.makeVector)
						 ]
					],
					new ListDefinition => [ keyValue='complex' attributes = #[
						 new AttributeDefn('type', null, true, SAMPLING_TYPE_TYPE), new AttributeDefn('combination', null, true, SAMPLING_TYPE.makeVector)
						 ]
					]
				)
			]
		)
		
	}

	val Map<String, BlockListDefinition> attDefns
	val Map<String, Map<String, ? extends TypeInfo>> attEnumTypes

	new(){
		attDefns = attributeDefnDefaults
//		attEnumTypes = attributeEnumTypeDefaults
		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
		buildEnumTypes
	}

	new(Map<String, BlockListDefinition>  ad, Map<String, Map<String, ? extends TypeInfo>> enumTypes){
		attDefns = ad
//		attEnumTypes = enumTypes
		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
		buildEnumTypes
	}


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

	def ListDefinition getListDefinition(AttributeList it){
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
			return expectedAttributes.head
		}
		null
	}	
	
	def TypeInfo getTypeOfList(AttributeList attList){
		val listDefn = attList.listDefinition
		listDefn?.listType
	}
	
	def getOwningBlock(ValuePair it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	} 
	
	def getOwningBlock(EnumExpression it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	} 
	
	def getAttributeType(ListDefinition it, String attName){
		attributes.findFirst(ad | ad.name == attName)?.attType ?: MclTypeProvider::UNDEFINED_TYPE 
	}
	
	def getTypeOfBuiltinEnum(EnumExpression ee){
		val blockName = ee.owningBlock.identifier
		val enumValue = ee.convertToString
		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: MclTypeProvider::UNDEFINED_TYPE
		defnType
	}

//	def getTypeOfBuiltinEnum(Attribute att){
//		val blockName = att.owningBlock.identifier
//		val enumValue = att.attributeValueString
//		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: MclTypeProvider::UNDEFINED_TYPE
//		defnType
//	}
	
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
	
	def attributeExists(List<ValuePair> attributes, String queryName){
		attributes.exists[attrib|
			switch(attrib){
				ValuePair case attrib.argumentName == queryName: true
//				CategoricalDefinitionExpr case CATEGORIES_KWD == queryName: true 
				default: false
			}
		]
	}
	
	def getAttributeName(ValuePair attrib){
		switch(attrib){
			ValuePair: attrib.argumentName
//			CategoricalDefinitionExpr: CATEGORIES_KWD 
		}
	}

	def getAttributeValueString(ValuePair attrib){
		switch(attrib){
			ValuePair: attrib.expression.convertToString
//			CategoricalDefinitionExpr: attrib.convertToString
		}
	}

	def attributeDefnExists(ListDefinition it, ValuePair att){
		attributes.exists[attrib|
			attrib.name == att.attributeName
		]
	}
	
	def attributeExistsInAnyListDefn(BlockListDefinition it, ValuePair att){
		it.listDefns.exists[ld| ld.attributeDefnExists(att)]
	}

//	def getListDefnByKeyValue(List<ListDefinition> defns, String queryValue){
//		defns.filter[d | d.keyValue == queryValue]
//	}


	// gets the key value of an attribute list
	def getKeyValue(AttributeList attList, String key){
		attList.attributes.findFirst[at | at.attributeName == key].attributeValueString
	}
	
	// gets a list definition based on a key value combination 
	def getMatchingListDefn(AttributeList attList){
		val owningBlock = EcoreUtil2.getContainerOfType(attList.eContainer, BlockStatement)
		val blkDefn = attDefns.get(owningBlock.identifier)
		val listDefns = blkDefn?.listDefns ?: Collections.emptyList
		listDefns.findFirst[defn|
			for(a : attList.attributes){
				if(blkDefn.key == a.attributeName)
					if(defn.keyValue == null || defn.keyValue == a.attributeValueString){
						return true;
					}
			} 
			false
		]
	}

	// gets a list definition based on its key 
	def getListDefnByKeyValue(BlockListDefinition it, String keyValQuery){
		listDefns.findFirst[defn| defn.keyValue == keyValQuery ]
	}

	def	getListDefnByBlockAndKeyValue(String blockName, String keyValQuery){
		val blkDefn = attDefns.get(blockName)
		blkDefn?.listDefns.findFirst[defn| defn.keyValue == keyValQuery ]
	}
	

	def attributeRecognised(ValuePair it){
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