package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.EnumListTypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.ListTypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.PrimitiveType
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.mdl.validation.SublistDefinitionProvider.*

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class ListDefinitionProvider {

	public static val COV_USE_VALUE = 'covariate'
	public static val OBS_USE_VALUE = 'dv'
	public static val CATCOV_USE_VALUE = 'catCov'
	public static val IDV_USE_VALUE = 'idv'
	public static val ID_USE_VALUE = 'id'
	public static val VARLVL_USE_VALUE = 'varLevel'

	public static val USE_TYPE = new BuiltinEnumTypeInfo('use', #{COV_USE_VALUE, 'amt', OBS_USE_VALUE, 'dvid', 'cmt', 'mdv', IDV_USE_VALUE, ID_USE_VALUE, 'rate', 'ignore', VARLVL_USE_VALUE, CATCOV_USE_VALUE, 'rate', 'ss', 'ii', 'addl'})
	static val DDV_USE_TYPE = new BuiltinEnumTypeInfo('use', #{COV_USE_VALUE, 'doseTime' })
	static val VARIABILITY_TYPE_TYPE = new BuiltinEnumTypeInfo('type', #{'parameter', 'observation'})
	static val INPUT_FORMAT_TYPE = new BuiltinEnumTypeInfo('input', #{'nonmemFormat'})
	static val COMP_TYPE_TYPE = new BuiltinEnumTypeInfo('cmpt', #{'depot', 'compartment', 'elimination', 'transfer', 'distribution', 'direct', 'input', 'effect'})
	static val PARAM_VAR_TYPE_TYPE = new BuiltinEnumTypeInfo('vartype', #{'cov', 'corr','sd', 'var'})
	static val OBS_TYPE_TYPE = new BuiltinEnumTypeInfo('obstype', #{'categorical', 'count', 'discrete', 'tte'})
	static val SAMPLING_TYPE_TYPE = new BuiltinEnumTypeInfo('sampletype', #{'simple', 'complex', 'derived'})
	static val ELEMENT_TYPE = new BuiltinEnumTypeInfo('sampleelement', #{'amount', 'duration', 'sampleTime', 'numberTimes'})
//	static val LINK_FUNC_TYPE = new BuiltinEnumTypeInfo('linkFunc', #{'identity', 'ln', 'logit', 'probit'})
	static val TTE_EVENT_TYPE = new BuiltinEnumTypeInfo('tteEvent', #{'exact', 'intervalCensored'})
	static val MOG_OBJ_TYPE_TYPE = new BuiltinEnumTypeInfo('type', #{ MdlValidator::MDLOBJ, MdlValidator::DATAOBJ, MdlValidator::PARAMOBJ, MdlValidator::TASKOBJ, MdlValidator::DESIGNOBJ })

	static val COMP_LIST_TYPE = new ListTypeInfo("Compartment", PrimitiveType.Real)
	static val IDV_COL_TYPE = new ListTypeInfo("Idv", PrimitiveType.List)
	static val AMT_COL_TYPE = new ListTypeInfo("Amt", PrimitiveType.Real)
	public static val ADMINISTRATION_TYPE = new ListTypeInfo("Administration", PrimitiveType.List)
	public static val SAMPLING_TYPE = new ListTypeInfo("SimpleSampling", PrimitiveType.List)
	public static val CPLX_SAMPLING_TYPE = new ListTypeInfo("ComplexSampling", PrimitiveType.List)
	public static val DERIV_SAMPLING_TYPE = new ListTypeInfo("DerivedSampling", PrimitiveType.List)

	
	// @TODO: need an addition set of validations to make sure that DVID is used if AMT has a define attribute
	
	@Data @FinalFieldsConstructor
	static class AttributeDefn{
		String name
		String depAtt // other att that must be present
		boolean mandatory
		TypeInfo attType
		TypeInfo catMappingType
		boolean catMappingMandatory
		
		new(String name, String depAtt, boolean mand, TypeInfo attType){
			this(name, depAtt, mand, attType, null, false)
		}
		
		def isCatMappingMandatory(){
			return catMappingType != null && catMappingMandatory
		}
		
		def isCatMappingOptional(){
			return catMappingType != null && !catMappingMandatory
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
	}
	
	static class BlockListDefinition {
		String key
		List<ListDefInfo> listDefns
	}

	// owning block -> key attribute -> key value -> attributes associated with key
	static val Map<String, BlockListDefinition> attributeDefnDefaults = #{ 
		"DATA_INPUT_VARIABLES" -> (
			new BlockListDefinition => [
				key = 'use'
				listDefns = newArrayList(
					new ListDefInfo('covariate', new ListTypeInfo("Covariate", PrimitiveType.Real),#[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo('catCov', new EnumListTypeInfo("CatCovariate"), #[
						 new AttributeDefn('use', null, true, USE_TYPE, MclTypeProvider::INT_TYPE, true)
						 ] 
					),
					new ListDefInfo ('amt', AMT_COL_TYPE,  #[
						 new AttributeDefn('use', null, true, USE_TYPE), new AttributeDefn('define', null, false, MclTypeProvider::MAPPING_TYPE),
						 new AttributeDefn('variable', null, false, MclTypeProvider::REAL_TYPE.makeReference)
						 ] 
					),
					new ListDefInfo ('dv', new ListTypeInfo("Dv", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE), new AttributeDefn('define', null, false, MclTypeProvider::MAPPING_TYPE),
						 new AttributeDefn('variable', null, false, MclTypeProvider::REAL_TYPE.makeReference)
						 ] 
					),
					new ListDefInfo ('idv', IDV_COL_TYPE,  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('cmt', new ListTypeInfo("Cmt", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('id', new ListTypeInfo("Id", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('varLevel', new ListTypeInfo("VarLevel", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('mdv', new ListTypeInfo("Mdv", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('rate', new ListTypeInfo("Rate", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('ss', new ListTypeInfo("SteadyState", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('ii', new ListTypeInfo("InterDosInterval", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('addl', new ListTypeInfo("AdditionImplicitDoses", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('dvid', new ListTypeInfo("Dvid", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('ignore', new ListTypeInfo("Ignore", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, USE_TYPE)
						 ] 
					)
				)
			]
		),
		"DATA_DERIVED_VARIABLES" -> (
			new BlockListDefinition => [
				key = 'use'
				listDefns = newArrayList(
					new ListDefInfo ('doseTime', new ListTypeInfo("DoseTime", PrimitiveType.List),  #[
						 new AttributeDefn('use', null, true, DDV_USE_TYPE),
						 new AttributeDefn('idvColumn', null, true, IDV_COL_TYPE.makeReference),
						 new AttributeDefn('amtColumn', null, true, AMT_COL_TYPE.makeReference) 
						 ] 
					)
				)
			]
		),
		"SOURCE" -> (
			new BlockListDefinition => [
				key = 'file'
				listDefns = newArrayList(
					new ListDefInfo (null, new ListTypeInfo("Source", PrimitiveType.List),  #[
						 new AttributeDefn('file', null, true, MclTypeProvider::STRING_TYPE), new AttributeDefn('inputFormat', null, true, INPUT_FORMAT_TYPE),
						 	new AttributeDefn('ignore', null, false, MclTypeProvider::STRING_TYPE) 
						 ] 
					)
				)
			]
		),
		"VARIABILITY_LEVELS" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefInfo (null, new ListTypeInfo("VarLevel", PrimitiveType.List),  #[
						 new AttributeDefn('type', null, true, VARIABILITY_TYPE_TYPE), new AttributeDefn('level', null, true, MclTypeProvider::INT_TYPE)
						 ]
					)
				)
			]
		),
		"COMPARTMENT" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefInfo ('input', new ListTypeInfo("Input", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('ktr', null, true, MclTypeProvider::REAL_TYPE), new AttributeDefn('mtt', null, true, MclTypeProvider::REAL_TYPE)
						 ]
					),
					new ListDefInfo ('effect', new ListTypeInfo("Effect", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('from', null, true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference), new AttributeDefn('keq', null, true, MclTypeProvider::REAL_TYPE)
						 ]
					),
					new ListDefInfo ('depot', new ListTypeInfo("Depot", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('to', null, true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference), new AttributeDefn('ka', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('tlag', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('finput', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('modelDur', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('modelRate', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('ktr', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('mtt', null, false, MclTypeProvider::REAL_TYPE)
						 ]
					),
					new ListDefInfo ('transfer', new ListTypeInfo("Transfer", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('kt', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('from', null, true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference),
						 new AttributeDefn('to', null, true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference)
						 ]
					),
					new ListDefInfo ('compartment', new ListTypeInfo("Compartment", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, false, MclTypeProvider::INT_TYPE)
						 ]
					),
					new ListDefInfo ('elimination', new ListTypeInfo("Elimination", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('from', null, true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference), new AttributeDefn('v', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('cl', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('k', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('vm', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('km', null, false, MclTypeProvider::REAL_TYPE)
						 ]
					),
					new ListDefInfo ('distribution', new ListTypeInfo("Distribution", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('kin', null, true, MclTypeProvider::REAL_TYPE), new AttributeDefn('kout', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('from', null, true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference)
						 ]
					),
					new ListDefInfo ('direct', new ListTypeInfo("Direct", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', null, false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('tlag', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('finput', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('to', null, true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference)
						 ]
					)
				)
			]
		),
		"DEQ" -> (
			new BlockListDefinition => [
				key = 'deriv'
				listDefns = newArrayList(
					new ListDefInfo (null, new ListTypeInfo("Derivative", PrimitiveType.Deriv),  #[
						 new AttributeDefn('deriv', null, true, MclTypeProvider::REAL_TYPE), new AttributeDefn('init', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('x0', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('wrt', null, false, MclTypeProvider::REAL_TYPE.makeReference)
						 ]
					)
				)
			]
		),
		"VARIABILITY" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefInfo ('corr', new ListTypeInfo("CovarMatrix", PrimitiveType.List),  #[
						 new AttributeDefn('type', null, true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('parameter', null, true, MclTypeProvider::REAL_VECTOR_TYPE),
						 new AttributeDefn('value', null, true, MclTypeProvider::REAL_VECTOR_TYPE)
						 ]
					),
					new ListDefInfo ('cov', new ListTypeInfo("CorrMatrix", PrimitiveType.List),  #[
						 new AttributeDefn('type', null, true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('parameter', null, true, MclTypeProvider::REAL_VECTOR_TYPE),
						 new AttributeDefn('value', null, true, MclTypeProvider::REAL_VECTOR_TYPE)
						 ]
					),
					new ListDefInfo ('sd', new ListTypeInfo("SDEstimate", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('value', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('lo', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('hi', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('fix', null, false, MclTypeProvider::BOOLEAN_TYPE)
						 ]
					),
					new ListDefInfo ('var', new ListTypeInfo("VarEstimate", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('value', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('lo', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('hi', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('fix', null, false, MclTypeProvider::BOOLEAN_TYPE)
						 ]
					)
				)
			]
		),
		"STRUCTURAL" -> (
			new BlockListDefinition => [
				key = 'value'
				listDefns = newArrayList(
					new ListDefInfo (null, new ListTypeInfo("StructuralEstimate", PrimitiveType.Real),  #[
						 new AttributeDefn('value', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('lo', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('hi', null, false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('fix', null, false, MclTypeProvider::BOOLEAN_TYPE)
						 ]
					)
				)
			]
		),
		"OBJECTS" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefInfo (null, new ListTypeInfo("MdlObjInMog", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, MOG_OBJ_TYPE_TYPE)
						]
					)
				)
			]
		),
		"OBSERVATION" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefInfo ('categorical', new EnumListTypeInfo("CatObs"),  #[
						 new AttributeDefn('type', null, true, OBS_TYPE_TYPE, MclTypeProvider::REAL_TYPE.makeReference, true)
						 ]
					),
					new ListDefInfo ('count', new ListTypeInfo("CountObs", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, OBS_TYPE_TYPE),
						 new AttributeDefn('distn', null, true, MclTypeProvider::PMF_TYPE)
						 ]
					),
					new ListDefInfo ('discrete', new ListTypeInfo("DiscreteObs", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, OBS_TYPE_TYPE),
						 new AttributeDefn('distn', null, true, MclTypeProvider::PMF_TYPE)
						 ]
					),
					new ListDefInfo ('tte', new ListTypeInfo("DiscreteObs", PrimitiveType.Real),  #[
						 new AttributeDefn('type', null, true, OBS_TYPE_TYPE),
						 new AttributeDefn('hazard', null, true, MclTypeProvider::REAL_TYPE.makeReference),
						 new AttributeDefn('event', null, true, TTE_EVENT_TYPE),
						 new AttributeDefn('maxEvent', null, false, MclTypeProvider::REAL_TYPE)
						 ]
					)
				)
			]
		),
		"ADMINISTRATION" -> (
			new BlockListDefinition => [
				key = 'adm'
				listDefns = newArrayList(
					new ListDefInfo (null, ADMINISTRATION_TYPE,  #[
						 new AttributeDefn('adm', null, true, MclTypeProvider::REAL_TYPE.makeReference) , new AttributeDefn('amount', null, true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('doseTime', null, false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('duration', null, false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('start', null, false, MclTypeProvider::REAL_TYPE), new AttributeDefn('end', null, false, MclTypeProvider::REAL_TYPE)
						 ]
					)
				)
			]
		),
		"STUDY_DESIGN" -> (
			new BlockListDefinition => [
				key = 'armSize'
				listDefns = newArrayList(
					new ListDefInfo (null, new ListTypeInfo("StudyDesign", PrimitiveType.List),  #[
						 new AttributeDefn('armSize', null, true, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('interventionSequence', null, true, getSublist(INTERVENTION_SEQ_SUBLIST).makeVector),
						 new AttributeDefn('samplingSequence', null, false, getSublist(SAMPLING_SEQ_SUBLIST).makeVector)
						 ]
					)
				)
			]
		),
		"DESIGN_SPACES" -> (
			new BlockListDefinition => [
				key = 'element'
				listDefns = newArrayList(
					new ListDefInfo ('amount', new ListTypeInfo("DesignSpaceAmt", PrimitiveType.List),  #[
						 new AttributeDefn('admins', null, true, ADMINISTRATION_TYPE.makeVector),
						 new AttributeDefn('element', null, true, ELEMENT_TYPE),
						 new AttributeDefn('discrete', null, false, MclTypeProvider::INT_TYPE.makeVector)
						 ]
					),
					new ListDefInfo ('duration', new ListTypeInfo("DesignSpaceDur", PrimitiveType.List),  #[
						 new AttributeDefn('admins', null, true, ADMINISTRATION_TYPE.makeVector),
						 new AttributeDefn('element', null, true, ELEMENT_TYPE),
						 new AttributeDefn('range', null, false, MclTypeProvider::REAL_TYPE.makeVector)
						 ]
					),
					new ListDefInfo ('numberTimes', new ListTypeInfo("DesignSpaceNum", PrimitiveType.List),  #[
						 new AttributeDefn('element', null, true, ELEMENT_TYPE),
						 new AttributeDefn('samples', null, true, SAMPLING_TYPE.makeVector),
						 new AttributeDefn('discrete', null, false, MclTypeProvider::INT_TYPE.makeVector)
						 ]
					),
					new ListDefInfo ('sampleTime', new ListTypeInfo("DesignSpaceSample", PrimitiveType.List),  #[
						 new AttributeDefn('element', null, true, ELEMENT_TYPE),
						 new AttributeDefn('samples', null, true, SAMPLING_TYPE.makeVector),
						 new AttributeDefn('range', null, false, MclTypeProvider::REAL_TYPE.makeVector)
						 ]
					)
				)
			]
		),
		"SAMPLING" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefInfo ('simple', SAMPLING_TYPE,  #[
						 new AttributeDefn('type', null, true, SAMPLING_TYPE_TYPE), new AttributeDefn('outcome', null, true, MclTypeProvider::REAL_TYPE.makeReference),
						 new AttributeDefn('sampleTime', null, false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('numberSamples', null, false, MclTypeProvider::INT_TYPE.makeVector)
						 ]
					),
					new ListDefInfo('complex', CPLX_SAMPLING_TYPE, #[
						 new AttributeDefn('type', null, true, SAMPLING_TYPE_TYPE), new AttributeDefn('combination', null, true, SAMPLING_TYPE.makeVector)
						 ]
					),
					new ListDefInfo('derived', DERIV_SAMPLING_TYPE, #[
						 new AttributeDefn('type', null, true, SAMPLING_TYPE_TYPE), new AttributeDefn('combination', null, true, CPLX_SAMPLING_TYPE.makeVector)
						 ]
					)
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
	

	def getOwningBlock(ValuePair it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	} 
	
	def getOwningBlock(EnumExpression it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	} 
	
	def getAttributeType(ListDefInfo it, String attName){
		attributes.findFirst(ad | ad.name == attName)?.attType ?: MclTypeProvider::UNDEFINED_TYPE 
	}
	
	def getAttributeDefinition(ListDefInfo it, String attName){
		attributes.findFirst(ad | ad.name == attName) 
	}
	
	def TypeInfo getTypeOfAttributeBuiltinEnum(EnumExpression ee){
		val blockName = ee.owningBlock.identifier
		val enumValue = ee.convertToString
		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: MclTypeProvider::UNDEFINED_TYPE
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
				// expect AttributeList->ListDefinition|AnaonolymousListStatement->BlockStatement
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
			var expectedAttributes = new ArrayList<ListDefInfo>()
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
			ValuePair: attrib.expression?.convertToString
//			CategoricalDefinitionExpr: attrib.convertToString
		}
	}

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
	
	def getAttributeExpression(AttributeList it, String attName){
		attributes.findFirst[argumentName == attName]?.expression
	}

	def getAttributeEnumValue(AttributeList it, String attName){
		val enumExp = getAttributeExpression(attName)
		switch(enumExp){
			EnumExpression:
				return enumExp.enumValue
			default: null
		}
	}


	def attributeRecognised(ValuePair it){
		// expect Attribute->AttributeList->ListDefInfo|AnaonolymousListStatement->BlockStatement
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

	def checkCategoryDefinitionWellFormed(EnumPair ep, () => void unexpectedCatDefnErrorLambda, () => void missingCatErrorLambda){
		if(ep.eContainer instanceof AttributeList){
			val attList = ep.eContainer as AttributeList
			val listDefn = attList.matchingListDefn
			val attDefn = listDefn?.getAttributeDefinition(ep.argumentName)
			val mappingExpr = ep.expression as EnumExpression
			if(attDefn.isCatMappingMandatory && mappingExpr.catDefn == null){
				missingCatErrorLambda.apply
			}
			else if(!attDefn.isCatMappingPossible && mappingExpr.catDefn != null){
				unexpectedCatDefnErrorLambda.apply
			}
		}
	}

}