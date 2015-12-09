package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.StringLiteral
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
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.mdl.validation.SublistDefinitionProvider.*
import eu.ddmore.mdl.utils.DomainObjectModelUtils

class ListDefinitionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils

	public static val USE_ATT = 'use'
	public static val DEFINE_ATT = 'define'
	public static val VARIABLE_ATT = 'variable'
	public static val COV_USE_VALUE = 'covariate'
	public static val AMT_USE_VALUE = 'amt'
	public static val IGNORE_USE_VALUE = 'ignore'
	public static val OBS_USE_VALUE = 'dv'
	public static val DVID_USE_VALUE = 'dvid'
	public static val RATE_USE_VALUE = 'rate'
	public static val II_USE_VALUE = 'ii'
	public static val SS_USE_VALUE = 'ss'
	public static val ADDL_USE_VALUE = 'addl'
	public static val MDV_USE_VALUE = 'mdv'
	public static val CATCOV_USE_VALUE = 'catCov'
	public static val IDV_USE_VALUE = 'idv'
	public static val ID_USE_VALUE = 'id'
	public static val VARLVL_USE_VALUE = 'varLevel'
	public static val CMT_USE_VALUE = 'cmt'
	public static val DOSE_TIME_USE_VALUE = 'doseTime'
	public static val IDV_COL_ATT = 'idvColumn'
	public static val AMT_COL_ATT = 'amtColumn'
	public static val CMT_TYPE_ATT = 'type'
	public static val OBS_TYPE_ATT = 'type'
	public static val VAR_LVL_TYPE_ATT = 'type'
	public static val VAR_LVL_LEVEL_ATT = 'level'
	public static val DERIV_TYPE_ATT = 'deriv'
	public static val WRT_ATT = 'wrt'
	public static val DERIV_INIT_TIME_ATT = 'x0'
	public static val COUNT_OBS_VALUE = 'count'
	public static val DISCRETE_OBS_VALUE = 'discrete'
	public static val CATEGORICAL_OBS_VALUE = 'categorical'
	public static val TTE_OBS_VALUE = 'tte'
	public static val TTE_EVENT_ATT = 'event'
	public static val TTE_MAX_EVENT_ATT = 'maxEvent'
	public static val VAR_LVL_PARAM_VALUE = 'parameter' 
	public static val VAR_LVL_OBS_VALUE = 'observation' 
	

	public static val USE_TYPE = new BuiltinEnumTypeInfo('divUse', #{COV_USE_VALUE, AMT_USE_VALUE, OBS_USE_VALUE, DVID_USE_VALUE, CMT_USE_VALUE, MDV_USE_VALUE, IDV_USE_VALUE,
		ID_USE_VALUE, RATE_USE_VALUE, IGNORE_USE_VALUE, VARLVL_USE_VALUE, CATCOV_USE_VALUE, SS_USE_VALUE, II_USE_VALUE, ADDL_USE_VALUE
	})
	static val DDV_USE_TYPE = new BuiltinEnumTypeInfo('ddvUse', #{'doseTime' })
	static val VARIABILITY_TYPE_TYPE = new BuiltinEnumTypeInfo('varLvlType', #{VAR_LVL_PARAM_VALUE, VAR_LVL_OBS_VALUE})
	static val INPUT_FORMAT_TYPE = new BuiltinEnumTypeInfo('input', #{'nonmemFormat'})
	static val COMP_TYPE_TYPE = new BuiltinEnumTypeInfo('compType', #{'depot', 'compartment', 'elimination', 'transfer', 'distribution', 'direct', 'effect'})
	static val PARAM_VAR_TYPE_TYPE = new BuiltinEnumTypeInfo('vartype', #{'cov', 'corr','sd', 'var'})
	static val OBS_TYPE_TYPE = new BuiltinEnumTypeInfo('obstype', #{CATEGORICAL_OBS_VALUE, COUNT_OBS_VALUE, DISCRETE_OBS_VALUE, TTE_OBS_VALUE})
	static val SAMPLING_TYPE_TYPE = new BuiltinEnumTypeInfo('sampletype', #{'simple', 'complex', 'derived'})
	static val ELEMENT_TYPE = new BuiltinEnumTypeInfo('sampleelement', #{'amount', 'duration', 'sampleTime', 'numberTimes'})
//	static val LINK_FUNC_TYPE = new BuiltinEnumTypeInfo('linkFunc', #{'identity', 'ln', 'logit', 'probit'})
//	static val TTE_EVENT_TYPE = new BuiltinEnumTypeInfo('tteEvent', #{'rightCensored', 'intervalCensored'})
	static val MOG_OBJ_TYPE_TYPE = new BuiltinEnumTypeInfo('objType', #{ MdlValidator::MDLOBJ, MdlValidator::DATAOBJ, MdlValidator::PARAMOBJ, MdlValidator::TASKOBJ, MdlValidator::DESIGNOBJ })
//	static val TARGET_TYPE = new BuiltinEnumTypeInfo('target', #{'MLXTRAN_CODE', 'NMTRAN_CODE'})
	public static val DERIV_TYPE = new ListTypeInfo("Derivative", PrimitiveType.Deriv)
	public static val COUNT_LIST_TYPE = new ListTypeInfo("CountObs", PrimitiveType.Real) 
	public static val DISCRETE_LIST_TYPE = new EnumListTypeInfo("DiscreteObs")
	public static val CATEGORICAL_LIST_TYPE = new EnumListTypeInfo("CatObs")

	static val COMP_LIST_TYPE = new ListTypeInfo("Compartment", PrimitiveType.Real)
	static val IDV_COL_TYPE = new ListTypeInfo("Idv", PrimitiveType.List)
	static val AMT_COL_TYPE = new ListTypeInfo("Amt", PrimitiveType.Real)
	public static val CMT_COL_TYPE = new ListTypeInfo("Cmt", PrimitiveType.List)
	public static val DVID_COL_TYPE = new ListTypeInfo("Dvid", PrimitiveType.List)
	public static val ADMINISTRATION_TYPE = new ListTypeInfo("Administration", PrimitiveType.List)
	public static val SAMPLING_TYPE = new ListTypeInfo("SimpleSampling", PrimitiveType.List)
	public static val CPLX_SAMPLING_TYPE = new ListTypeInfo("ComplexSampling", PrimitiveType.List)
	public static val DERIV_SAMPLING_TYPE = new ListTypeInfo("DerivedSampling", PrimitiveType.List)

	
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
	
	static class BlockListDefinition {
		String key
		List<ListDefInfo> listDefns
	}

	// owning block -> key attribute -> key value -> attributes associated with key
	static val Map<String, BlockListDefinition> attributeDefnDefaults = #{ 
		"DATA_INPUT_VARIABLES" -> (
			new BlockListDefinition => [
				key = USE_ATT
				listDefns = newArrayList(
					new ListDefInfo('covariate', new ListTypeInfo("Covariate", PrimitiveType.Real),#[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo('catCov', new EnumListTypeInfo("CatCovariate"), #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE, MclTypeProvider::INT_TYPE, true)
						 ] 
					),
					new ListDefInfo ('amt', AMT_COL_TYPE,  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE), new AttributeDefn(DEFINE_ATT, false, MclTypeProvider::MAPPING_TYPE),
						 new AttributeDefn(VARIABLE_ATT, false, MclTypeProvider::REAL_TYPE.makeReference)
						 ],
						 #[#{ USE_ATT -> true, DEFINE_ATT -> true },
						 	#{ USE_ATT -> true, VARIABLE_ATT -> true }
						 ],
						 false
					),
					new ListDefInfo ('dv', new ListTypeInfo("Dv", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE), new AttributeDefn(DEFINE_ATT, false, MclTypeProvider::MAPPING_TYPE),
						 new AttributeDefn(VARIABLE_ATT, false, MclTypeProvider::REAL_TYPE.makeReference)
						 ],
						 #[#{ USE_ATT -> true, DEFINE_ATT -> true },
						 	#{ USE_ATT -> true, VARIABLE_ATT -> true }
						 ],
						 false
					),
					new ListDefInfo (IDV_USE_VALUE, IDV_COL_TYPE,  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo (CMT_USE_VALUE, CMT_COL_TYPE,  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo (ID_USE_VALUE, new ListTypeInfo("Id", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('varLevel', new ListTypeInfo("VarLevel", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('mdv', new ListTypeInfo("Mdv", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('rate', new ListTypeInfo("Rate", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('ss', new ListTypeInfo("SteadyState", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('ii', new ListTypeInfo("InterDosInterval", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('addl', new ListTypeInfo("AdditionImplicitDoses", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo (DVID_USE_VALUE, DVID_COL_TYPE,  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo ('ignore', new ListTypeInfo("Ignore", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					)
				)
			]
		),
		"DATA_DERIVED_VARIABLES" -> (
			new BlockListDefinition => [
				key = USE_ATT
				listDefns = newArrayList(
					new ListDefInfo ('doseTime', new ListTypeInfo("DoseTime", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, DDV_USE_TYPE),
						 new AttributeDefn(IDV_COL_ATT, true, IDV_COL_TYPE.makeReference),
						 new AttributeDefn(AMT_COL_ATT, true, AMT_COL_TYPE.makeReference) 
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
						 new AttributeDefn('file', true, MclTypeProvider::STRING_TYPE), new AttributeDefn('inputFormat', true, INPUT_FORMAT_TYPE)//,
//						 	new AttributeDefn('ignore', false, MclTypeProvider::STRING_TYPE) 
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
						 new AttributeDefn(VAR_LVL_TYPE_ATT, true, VARIABILITY_TYPE_TYPE), new AttributeDefn(VAR_LVL_LEVEL_ATT, true, MclTypeProvider::INT_TYPE)
						 ]
					)
				)
			]
		),
		"COMPARTMENT" -> (
			new BlockListDefinition => [
				key = CMT_TYPE_ATT
				listDefns = newArrayList(
					new ListDefInfo ('direct', new ListTypeInfo("Direct", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE),
						 new AttributeDefn('modelCmt', false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('to', true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference),
						 new AttributeDefn('modelDur', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('tlag', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('finput', false, MclTypeProvider::REAL_TYPE)
						 ],
						 #[
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'to' -> true, 'modelDur' -> false, 'tlag' -> false, 'finput' -> false }
						 ],
						 false
					),
					new ListDefInfo ('effect', new ListTypeInfo("Effect", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('from', true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference),
						 new AttributeDefn('keq', true, MclTypeProvider::REAL_TYPE)
						 ],
						 #[
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'from' -> true, 'keq' -> true }
						 ],
						 false
					),
					new ListDefInfo ('depot', new ListTypeInfo("Depot", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('to', true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference),
//						 new AttributeDefn('target', true, DERIV_TYPE.makeReference),
						 new AttributeDefn('ka', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('tlag', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('finput', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('modelDur', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('ktr', false, MclTypeProvider::REAL_TYPE), new AttributeDefn('mtt', false, MclTypeProvider::REAL_TYPE)
						 ],
						 #[
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'to' -> true, 'ka' -> true, 'tlag' -> false, 'finput' -> false },
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'to' -> true, 'ktr' -> true, 'mtt' -> true },
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'to' -> true, 'modelDur' -> true, 'tlag' -> false, 'finput' -> false }//,
//						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'target' -> true, 'tlag' -> false, 'finput' -> false },
//						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'target' -> true, 'ka' -> true, 'tlag' -> false, 'finput' -> false },
//						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'target' -> true, 'ktr' -> true, 'mtt' -> true },
//						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'target' -> true, 'modelDur' -> true, 'tlag' -> false, 'finput' -> false }
						 ],
						 false
					),
					new ListDefInfo ('transfer', new ListTypeInfo("Transfer", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('kt', true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('from', true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference),
						 new AttributeDefn('to', true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference)
						 ],
						 true
					),
					new ListDefInfo ('compartment', new ListTypeInfo("Compartment", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, MclTypeProvider::INT_TYPE)
						 ]
					),
					new ListDefInfo ('elimination', new ListTypeInfo("Elimination", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('from', true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference),
						 new AttributeDefn('v', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('cl', false, MclTypeProvider::REAL_TYPE), new AttributeDefn('k', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('vm', false, MclTypeProvider::REAL_TYPE), new AttributeDefn('km', false, MclTypeProvider::REAL_TYPE)
						 ],
						 #[
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'from' -> true, 'v' -> false, 'k' -> true },
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'from' -> true, 'v' -> false, 'cl' -> true },
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'from' -> true, 'vm' -> true, 'km' -> true }
						 ],
						 true
					),
					new ListDefInfo ('distribution', new ListTypeInfo("Distribution", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('kin', true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('kout', true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('from', true, ListDefinitionProvider.COMP_LIST_TYPE.makeReference)
						 ]
					)
				)
			]
		),
		"DEQ" -> (
			new BlockListDefinition => [
				key = DERIV_TYPE_ATT
				listDefns = newArrayList(
					new ListDefInfo (null, DERIV_TYPE,  #[
						 new AttributeDefn(DERIV_TYPE_ATT, true, MclTypeProvider::REAL_TYPE), new AttributeDefn('init', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn(DERIV_INIT_TIME_ATT, false, MclTypeProvider::REAL_TYPE), new AttributeDefn(WRT_ATT, false, MclTypeProvider::REAL_TYPE.makeReference)
						 ]
					)
				)
			]
		),
		"MODEL_PREDICTION" -> (
			new BlockListDefinition => [
				key = DERIV_TYPE_ATT
				listDefns = newArrayList(
					new ListDefInfo (null, DERIV_TYPE,  #[
						 new AttributeDefn(DERIV_TYPE_ATT, true, MclTypeProvider::REAL_TYPE), new AttributeDefn('init', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn(DERIV_INIT_TIME_ATT, false, MclTypeProvider::REAL_TYPE), new AttributeDefn(WRT_ATT, false, MclTypeProvider::REAL_TYPE.makeReference)
						 ]
					)
				)
			]
		),
		"VARIABILITY" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefInfo ('cov', new ListTypeInfo("CovarMatrix", PrimitiveType.List),  #[
						 new AttributeDefn('type', true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('parameter', true, MclTypeProvider::REAL_VECTOR_TYPE),
						 new AttributeDefn('value', true, MclTypeProvider::REAL_VECTOR_TYPE)
						 ]
					),
					new ListDefInfo ('corr', new ListTypeInfo("CorrMatrix", PrimitiveType.List),  #[
						 new AttributeDefn('type', true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('parameter', true, MclTypeProvider::REAL_VECTOR_TYPE),
						 new AttributeDefn('value', true, MclTypeProvider::REAL_VECTOR_TYPE)
						 ]
					),
					new ListDefInfo ('sd', new ListTypeInfo("SDEstimate", PrimitiveType.Real),  #[
						 new AttributeDefn('type', true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('value', true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('lo', false, MclTypeProvider::REAL_TYPE), new AttributeDefn('hi', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('fix', false, MclTypeProvider::BOOLEAN_TYPE)
						 ]
					),
					new ListDefInfo ('var', new ListTypeInfo("VarEstimate", PrimitiveType.Real),  #[
						 new AttributeDefn('type', true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('value', true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('lo', false, MclTypeProvider::REAL_TYPE), new AttributeDefn('hi', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('fix', false, MclTypeProvider::BOOLEAN_TYPE)
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
						 new AttributeDefn('value', true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('lo', false, MclTypeProvider::REAL_TYPE), new AttributeDefn('hi', false, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('fix', false, MclTypeProvider::BOOLEAN_TYPE)
						 ]
					)
				)
			]
		),
		"OBJECTS" -> (
			new BlockListDefinition => [
				key = 'type'
				listDefns = newArrayList(
					new ListDefInfo (null, new ListTypeInfo("MdlObjInMog", PrimitiveType.List),  #[
						 new AttributeDefn('type', true, MOG_OBJ_TYPE_TYPE)
						]
					)
				)
			]
		),
		"OBSERVATION" -> (
			new BlockListDefinition => [
				key = OBS_TYPE_ATT
				listDefns = newArrayList(
					new ListDefInfo (CATEGORICAL_OBS_VALUE, CATEGORICAL_LIST_TYPE,  #[
						 new AttributeDefn(OBS_TYPE_ATT, true, OBS_TYPE_TYPE, MclTypeProvider::REAL_TYPE.makeReference, true)
						 ]
					),
					new ListDefInfo (COUNT_OBS_VALUE, COUNT_LIST_TYPE,  #[
						 new AttributeDefn(OBS_TYPE_ATT, true, OBS_TYPE_TYPE),
						 new AttributeDefn('distn', true, MclTypeProvider::PMF_TYPE)
						 ]
					),
					new ListDefInfo (DISCRETE_OBS_VALUE, DISCRETE_LIST_TYPE,  #[
						 new AttributeDefn(OBS_TYPE_ATT, true, OBS_TYPE_TYPE, MclTypeProvider::UNDEFINED_TYPE, false),
						 new AttributeDefn('distn', true, MclTypeProvider::PMF_TYPE)
						 ]
					),
					new ListDefInfo (TTE_OBS_VALUE, new ListTypeInfo("TteObs", PrimitiveType.Real),  #[
						 new AttributeDefn(OBS_TYPE_ATT, true, OBS_TYPE_TYPE),
						 new AttributeDefn('hazard', true, MclTypeProvider::REAL_TYPE.makeReference)//,
//						 new AttributeDefn('event', false, TTE_EVENT_TYPE),
//						 new AttributeDefn('maxEvent', false, MclTypeProvider::REAL_TYPE)
						 ],
						 #[
						 	#{ OBS_TYPE_ATT -> true, 'hazard' -> true }//,
//						 	#{ OBS_TYPE_ATT -> true, 'hazard' -> true, 'event' -> true, 'maxEvent' -> false }
						 ],
						 false
					)
				)
			]
		),
//		"ESTIMATE" -> (
//			new BlockListDefinition => [
//				key = 'target'
//				listDefns = newArrayList(
//					new ListDefInfo (null, TARGET_TYPE,  #[
//						 new AttributeDefn('target', true,  TARGET_TYPE),
//						 new AttributeDefn('version', false, MclTypeProvider::STRING_TYPE),
//						 new AttributeDefn('algo', false, MclTypeProvider::STRING_TYPE),
//						 new AttributeDefn('tol', false, MclTypeProvider::REAL_TYPE)
//						 ]
//					)
//				)
//			]
//		),
//		"SIMULATE" -> (
//			new BlockListDefinition => [
//				key = 'target'
//				listDefns = newArrayList(
//					new ListDefInfo (null, TARGET_TYPE,  #[
//						 new AttributeDefn('target', true,  TARGET_TYPE),
//						 new AttributeDefn('version', false, MclTypeProvider::STRING_TYPE),
//						 new AttributeDefn('algo', false, MclTypeProvider::STRING_TYPE),
//						 new AttributeDefn('tol', false, MclTypeProvider::REAL_TYPE)
//						 ]
//					)
//				)
//			]
//		),
		"ADMINISTRATION" -> (
			new BlockListDefinition => [
				key = 'adm'
				listDefns = newArrayList(
					new ListDefInfo (null, ADMINISTRATION_TYPE,  #[
						 new AttributeDefn('adm', true, MclTypeProvider::REAL_TYPE.makeReference) , new AttributeDefn('amount', true, MclTypeProvider::REAL_TYPE),
						 new AttributeDefn('doseTime', false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('duration', false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('start', false, MclTypeProvider::REAL_TYPE), new AttributeDefn('end', false, MclTypeProvider::REAL_TYPE)
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
						 new AttributeDefn('armSize', true, MclTypeProvider::INT_TYPE),
						 new AttributeDefn('interventionSequence', true, getSublist(INTERVENTION_SEQ_SUBLIST).makeVector),
						 new AttributeDefn('samplingSequence', false, getSublist(SAMPLING_SEQ_SUBLIST).makeVector)
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
						 new AttributeDefn('admins', true, ADMINISTRATION_TYPE.makeVector),
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('discrete', false, MclTypeProvider::INT_TYPE.makeVector)
						 ]
					),
					new ListDefInfo ('duration', new ListTypeInfo("DesignSpaceDur", PrimitiveType.List),  #[
						 new AttributeDefn('admins', true, ADMINISTRATION_TYPE.makeVector),
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('range', false, MclTypeProvider::REAL_TYPE.makeVector)
						 ]
					),
					new ListDefInfo ('numberTimes', new ListTypeInfo("DesignSpaceNum", PrimitiveType.List),  #[
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('samples', true, SAMPLING_TYPE.makeVector),
						 new AttributeDefn('discrete', false, MclTypeProvider::INT_TYPE.makeVector)
						 ]
					),
					new ListDefInfo ('sampleTime', new ListTypeInfo("DesignSpaceSample", PrimitiveType.List),  #[
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('samples', true, SAMPLING_TYPE.makeVector),
						 new AttributeDefn('range', false, MclTypeProvider::REAL_TYPE.makeVector)
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
						 new AttributeDefn('type', true, SAMPLING_TYPE_TYPE), new AttributeDefn('outcome', true, MclTypeProvider::REAL_TYPE.makeReference),
						 new AttributeDefn('sampleTime', false, MclTypeProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('numberSamples', false, MclTypeProvider::INT_TYPE.makeVector)
						 ]
					),
					new ListDefInfo('complex', CPLX_SAMPLING_TYPE, #[
						 new AttributeDefn('type', true, SAMPLING_TYPE_TYPE), new AttributeDefn('combination', true, SAMPLING_TYPE.makeVector)
						 ]
					),
					new ListDefInfo('derived', DERIV_SAMPLING_TYPE, #[
						 new AttributeDefn('type', true, SAMPLING_TYPE_TYPE), new AttributeDefn('combination', true, CPLX_SAMPLING_TYPE.makeVector)
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
	
	def getAttributeType(ListDefInfo it, String attName){
		attributes.findFirst(ad | ad.name == attName)?.attType ?: MclTypeProvider::UNDEFINED_TYPE 
	}
	
	def getAttributeDefinition(ListDefInfo it, String attName){
		attributes.findFirst(ad | ad.name == attName) 
	}
	
	
	def TypeInfo getAttributeType(ValuePair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if(attList != null){
			val listDefn = attList.matchingListDefn
			listDefn?.getAttributeType(attributeName) ?: MclTypeProvider::UNDEFINED_TYPE
		}
		else MclTypeProvider::UNDEFINED_TYPE
	}
	
	def TypeInfo getTypeOfAttributeBuiltinEnum(EnumExpression ee){
		val blockName = ee.owningBlock.identifier
		val enumValue = ee.enumValue
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

	def checkCategoryDefinitionWellFormed(EnumPair ep, () => void unexpectedCatDefnErrorLambda, () => void missingCatErrorLambda){
		val attList = EcoreUtil2.getContainerOfType(ep.eContainer, ListDefinition)
		if(attList != null){
			val listDefn = attList.list.matchingListDefn
			val attDefn = listDefn?.getAttributeDefinition(ep.argumentName)
			if(ep.expression instanceof EnumExpression && attDefn != null){
				val mappingExpr = ep.expression as EnumExpression
				if(attDefn.isCatMappingPossible && mappingExpr.catDefn == null){
					missingCatErrorLambda.apply
				}
				else if(!attDefn.isCatMappingPossible && mappingExpr.catDefn != null){
					unexpectedCatDefnErrorLambda.apply
				}
			}
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
