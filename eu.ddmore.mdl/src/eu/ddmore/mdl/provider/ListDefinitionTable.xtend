package eu.ddmore.mdl.provider

import eu.ddmore.mdl.provider.ListDefinitionProvider.AttributeDefn
import eu.ddmore.mdl.provider.ListDefinitionProvider.BlockListDefinition
import eu.ddmore.mdl.provider.ListDefinitionProvider.ListDefInfo
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.EnumListTypeInfo
import eu.ddmore.mdl.type.ListSuperTypeInfo
import eu.ddmore.mdl.type.ListTypeInfo
import eu.ddmore.mdl.type.PrimitiveType
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.validation.MdlValidator
import java.util.Map

class ListDefinitionTable {
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
	
	public static val CMT_DIRECT_VALUE = 'direct'
	public static val CMT_DEPOT_VALUE = 'depot'
	

	public static val USE_TYPE = new BuiltinEnumTypeInfo('divUse', #{COV_USE_VALUE, AMT_USE_VALUE, OBS_USE_VALUE, DVID_USE_VALUE, CMT_USE_VALUE, MDV_USE_VALUE, IDV_USE_VALUE,
		ID_USE_VALUE, RATE_USE_VALUE, IGNORE_USE_VALUE, VARLVL_USE_VALUE, CATCOV_USE_VALUE, SS_USE_VALUE, II_USE_VALUE, ADDL_USE_VALUE
	})
	static val DDV_USE_TYPE = new BuiltinEnumTypeInfo('ddvUse', #{'doseTime' })
	static val VARIABILITY_TYPE_TYPE = new BuiltinEnumTypeInfo('varLvlType', #{VAR_LVL_PARAM_VALUE, VAR_LVL_OBS_VALUE})
	static val INPUT_FORMAT_TYPE = new BuiltinEnumTypeInfo('input', #{'nonmemFormat'})
	static val PRIOR_INPUT_FORMAT_TYPE = new BuiltinEnumTypeInfo('priorInput', #{'RList'})
	static val COMP_TYPE_TYPE = new BuiltinEnumTypeInfo(CMT_TYPE_ATT, #{CMT_DEPOT_VALUE, 'compartment', 'elimination', 'transfer', 'distribution', CMT_DIRECT_VALUE, 'effect'})
	static val PARAM_VAR_TYPE_TYPE = new BuiltinEnumTypeInfo('vartype', #{'cov', 'corr','sd', 'var'})
	static val OBS_TYPE_TYPE = new BuiltinEnumTypeInfo('obstype', #{CATEGORICAL_OBS_VALUE, COUNT_OBS_VALUE, DISCRETE_OBS_VALUE, TTE_OBS_VALUE})
	static val SAMPLING_TYPE_TYPE = new BuiltinEnumTypeInfo('sampletype', #{'simple', 'complex', 'derived'})
	static val ELEMENT_TYPE = new BuiltinEnumTypeInfo('sampleelement', #{'amount', 'duration', 'sampleTime', 'numberTimes', 'covariate', 'catCov',
																			'numberArms', 'armSize', 'parameter', 'doseTime'
																		})
//	static val LINK_FUNC_TYPE = new BuiltinEnumTypeInfo('linkFunc', #{'identity', 'ln', 'logit', 'probit'})
//	static val TTE_EVENT_TYPE = new BuiltinEnumTypeInfo('tteEvent', #{'rightCensored', 'intervalCensored'})
	static val MOG_OBJ_TYPE_TYPE = new BuiltinEnumTypeInfo('objType', #{ MdlValidator::MDLOBJ, MdlValidator::DATAOBJ, MdlValidator::PARAMOBJ, MdlValidator::TASKOBJ, MdlValidator::DESIGNOBJ })
//	static val TARGET_TYPE = new BuiltinEnumTypeInfo('target', #{'MLXTRAN_CODE', 'NMTRAN_CODE'})
	public static val DERIV_SUPER_LIST = new ListSuperTypeInfo("DerivSuper") 
	public static val DERIV_TYPE = new ListTypeInfo("Derivative", PrimitiveType.Deriv, DERIV_SUPER_LIST)
	public static val COUNT_LIST_TYPE = new ListTypeInfo("CountObs", PrimitiveType.Real) 
	public static val DISCRETE_LIST_TYPE = new EnumListTypeInfo("DiscreteObs")
	public static val CATEGORICAL_LIST_TYPE = new EnumListTypeInfo("CatObs")

	public static val COMP_LIST_TYPE = new ListTypeInfo("Compartment", PrimitiveType.Real, DERIV_SUPER_LIST)
	public static val IDV_COL_TYPE = new ListTypeInfo("Idv")
	public static val AMT_COL_TYPE = new ListTypeInfo("Amt")
	public static val CMT_COL_TYPE = new ListTypeInfo("Cmt")
	public static val DVID_COL_TYPE = new ListTypeInfo("Dvid")
	public static val ADMINISTRATION_TYPE = new ListTypeInfo("Administration")
	public static val SAMPLING_SUPER_LIST = new ListSuperTypeInfo("SamplingSuper") 
	public static val SAMPLING_TYPE = new ListTypeInfo("SimpleSampling", SAMPLING_SUPER_LIST)
//	public static val CPLX_SAMPLING_TYPE = new ListTypeInfo("ComplexSampling", PrimitiveType.List)
	public static val DERIV_SAMPLING_TYPE = new ListTypeInfo("DerivedSampling", SAMPLING_SUPER_LIST)
	public static val PRIOR_SOURCE_TYPE = new ListTypeInfo("PriorSource")
	public static val STUDY_DESIGN_LIST_TYPE = new ListTypeInfo("StudyDesign")
	
	public static val Map<String, BlockListDefinition> attributeDefnDefaults = #{ 
		"DATA_INPUT_VARIABLES" -> (
			new BlockListDefinition(USE_ATT, newArrayList(
					new ListDefInfo('covariate', new ListTypeInfo("Covariate", PrimitiveType.Real),#[
						 new AttributeDefn(USE_ATT, true, USE_TYPE)
						 ] 
					),
					new ListDefInfo('catCov', new EnumListTypeInfo("CatCovariate"), #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE, TypeSystemProvider::INT_TYPE, true)
						 ] 
					),
					new ListDefInfo ('amt', AMT_COL_TYPE,  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE), new AttributeDefn(DEFINE_ATT, false, TypeSystemProvider::MAPPING_TYPE),
						 new AttributeDefn(VARIABLE_ATT, false, TypeSystemProvider::REAL_TYPE.makeReference)
						 ],
						 #[#{ USE_ATT -> true, DEFINE_ATT -> true },
						 	#{ USE_ATT -> true, VARIABLE_ATT -> true }
						 ],
						 false
					),
					new ListDefInfo ('dv', new ListTypeInfo("Dv", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, USE_TYPE), new AttributeDefn(DEFINE_ATT, false, TypeSystemProvider::MAPPING_TYPE),
						 new AttributeDefn(VARIABLE_ATT, false, TypeSystemProvider::REAL_TYPE.makeReference)
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
			)
		),
		"DATA_DERIVED_VARIABLES" -> (
			new BlockListDefinition(USE_ATT, newArrayList(
					new ListDefInfo ('doseTime', new ListTypeInfo("DoseTime", PrimitiveType.List),  #[
						 new AttributeDefn(USE_ATT, true, DDV_USE_TYPE),
						 new AttributeDefn(IDV_COL_ATT, true, IDV_COL_TYPE.makeReference),
						 new AttributeDefn(AMT_COL_ATT, true, AMT_COL_TYPE.makeReference) 
						 ] 
					)
				)
			)
		),
		"SOURCE" -> (
			new BlockListDefinition('file', newArrayList(
					new ListDefInfo (null, new ListTypeInfo("Source", PrimitiveType.List),  #[
						 new AttributeDefn('file', true, TypeSystemProvider::STRING_TYPE), new AttributeDefn('inputFormat', true, INPUT_FORMAT_TYPE)//,
//						 	new AttributeDefn('ignore', false, MclTypeProvider::STRING_TYPE) 
						 ] 
					)
				)
			)
		),
		BlockDefinitionTable::PRIOR_SOURCE_BLK -> (
			new BlockListDefinition('file', newArrayList(
					new ListDefInfo (null, PRIOR_SOURCE_TYPE,  #[
						 new AttributeDefn('file', true, TypeSystemProvider::STRING_TYPE),
						 new AttributeDefn('inputFormat', true, PRIOR_INPUT_FORMAT_TYPE),
						 new AttributeDefn('format', true, SublistDefinitionTable::instance.getSublist(SublistDefinitionTable::PRIOR_FORMAT_SUBLIST).makeVector)
						 ] 
					)
				)
			)
		),
		"VARIABILITY_LEVELS" -> (
			new BlockListDefinition('type', newArrayList(
					new ListDefInfo (null, new ListTypeInfo("VarLevel", PrimitiveType.List),  #[
						 new AttributeDefn(VAR_LVL_TYPE_ATT, true, VARIABILITY_TYPE_TYPE), new AttributeDefn(VAR_LVL_LEVEL_ATT, true, TypeSystemProvider::INT_TYPE)
						 ]
					)
				)
			)
		),
		"COMPARTMENT" -> (
			new BlockListDefinition(CMT_TYPE_ATT, newArrayList(
					new ListDefInfo (CMT_DIRECT_VALUE, new ListTypeInfo("Direct", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE),
						 new AttributeDefn('modelCmt', false, TypeSystemProvider::INT_TYPE),
						 new AttributeDefn('to', true, DERIV_SUPER_LIST.makeReference),
						 new AttributeDefn('modelDur', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('tlag', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('finput', false, TypeSystemProvider::REAL_TYPE)
						 ],
						 #[
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'to' -> true, 'modelDur' -> false, 'tlag' -> false, 'finput' -> false }
						 ],
						 false
					),
					new ListDefInfo ('effect', new ListTypeInfo("Effect", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, TypeSystemProvider::INT_TYPE),
						 new AttributeDefn('from', true, DERIV_SUPER_LIST.makeReference),
						 new AttributeDefn('keq', true, TypeSystemProvider::REAL_TYPE)
						 ],
						 #[
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'from' -> true, 'keq' -> true }
						 ],
						 false
					),
					new ListDefInfo (CMT_DEPOT_VALUE, new ListTypeInfo("Depot", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, TypeSystemProvider::INT_TYPE),
						 new AttributeDefn('to', true, DERIV_SUPER_LIST.makeReference),
//						 new AttributeDefn('target', true, DERIV_TYPE.makeReference),
						 new AttributeDefn('ka', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('tlag', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('finput', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('modelDur', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('ktr', false, TypeSystemProvider::REAL_TYPE), new AttributeDefn('mtt', false, TypeSystemProvider::REAL_TYPE)
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
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, TypeSystemProvider::INT_TYPE),
						 new AttributeDefn('kt', true, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('from', true, DERIV_SUPER_LIST.makeReference),
						 new AttributeDefn('to', true, DERIV_SUPER_LIST.makeReference)
						 ],
						 true
					),
					new ListDefInfo ('compartment', COMP_LIST_TYPE,  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, TypeSystemProvider::INT_TYPE)
						 ]
					),
					new ListDefInfo ('elimination', new ListTypeInfo("Elimination", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, TypeSystemProvider::INT_TYPE),
						 new AttributeDefn('from', true, DERIV_SUPER_LIST.makeReference),
						 new AttributeDefn('v', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('cl', false, TypeSystemProvider::REAL_TYPE), new AttributeDefn('k', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('vm', false, TypeSystemProvider::REAL_TYPE), new AttributeDefn('km', false, TypeSystemProvider::REAL_TYPE)
						 ],
						 #[
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'from' -> true, 'v' -> false, 'k' -> true },
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'from' -> true, 'v' -> false, 'cl' -> true },
						 	#{ CMT_TYPE_ATT -> true, 'modelCmt' -> false, 'from' -> true, 'vm' -> true, 'km' -> true }
						 ],
						 true
					),
					new ListDefInfo ('distribution', new ListTypeInfo("Distribution", PrimitiveType.Real),  #[
						 new AttributeDefn(CMT_TYPE_ATT, true, COMP_TYPE_TYPE), new AttributeDefn('modelCmt', false, TypeSystemProvider::INT_TYPE),
						 new AttributeDefn('kin', true, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('kout', true, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('from', true, DERIV_SUPER_LIST.makeReference)
						 ]
					)
				)
			)
		),
		"DEQ" -> (
			new BlockListDefinition(DERIV_TYPE_ATT, newArrayList(
					new ListDefInfo (null, DERIV_TYPE,  #[
						 new AttributeDefn(DERIV_TYPE_ATT, true, TypeSystemProvider::REAL_TYPE), new AttributeDefn('init', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn(DERIV_INIT_TIME_ATT, false, TypeSystemProvider::REAL_TYPE), new AttributeDefn(WRT_ATT, false, TypeSystemProvider::REAL_TYPE.makeReference)
						 ]
					)
				)
			)
		),
		"MODEL_PREDICTION" -> (
			new BlockListDefinition(DERIV_TYPE_ATT, newArrayList(
					new ListDefInfo (null, DERIV_TYPE,  #[
						 new AttributeDefn(DERIV_TYPE_ATT, true, TypeSystemProvider::REAL_TYPE), new AttributeDefn('init', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn(DERIV_INIT_TIME_ATT, false, TypeSystemProvider::REAL_TYPE), new AttributeDefn(WRT_ATT, false, TypeSystemProvider::REAL_TYPE.makeReference)
						 ]
					)
				)
			)
		),
		"VARIABILITY" -> (
			new BlockListDefinition('type', newArrayList(
					new ListDefInfo ('cov', new ListTypeInfo("CovarMatrix", PrimitiveType.List),  #[
						 new AttributeDefn('type', true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('parameter', true, TypeSystemProvider::REAL_VECTOR_TYPE),
						 new AttributeDefn('value', true, TypeSystemProvider::REAL_VECTOR_TYPE)
						 ]
					),
					new ListDefInfo ('corr', new ListTypeInfo("CorrMatrix", PrimitiveType.List),  #[
						 new AttributeDefn('type', true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('parameter', true, TypeSystemProvider::REAL_VECTOR_TYPE),
						 new AttributeDefn('value', true, TypeSystemProvider::REAL_VECTOR_TYPE)
						 ]
					),
					new ListDefInfo ('sd', new ListTypeInfo("SDEstimate", PrimitiveType.Real),  #[
						 new AttributeDefn('type', true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('value', true, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('lo', false, TypeSystemProvider::REAL_TYPE), new AttributeDefn('hi', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('fix', false, TypeSystemProvider::BOOLEAN_TYPE)
						 ]
					),
					new ListDefInfo ('var', new ListTypeInfo("VarEstimate", PrimitiveType.Real),  #[
						 new AttributeDefn('type', true, PARAM_VAR_TYPE_TYPE), new AttributeDefn('value', true, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('lo', false, TypeSystemProvider::REAL_TYPE), new AttributeDefn('hi', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('fix', false, TypeSystemProvider::BOOLEAN_TYPE)
						 ]
					)
				)
			)
		),
		"STRUCTURAL" -> (
			new BlockListDefinition('value', newArrayList(
					new ListDefInfo (null, new ListTypeInfo("StructuralEstimate", PrimitiveType.Real),  #[
						 new AttributeDefn('value', true, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('lo', false, TypeSystemProvider::REAL_TYPE), new AttributeDefn('hi', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('fix', false, TypeSystemProvider::BOOLEAN_TYPE)
						 ]
					)
				)
			)
		),
		"OBJECTS" -> (
			new BlockListDefinition('type', newArrayList(
					new ListDefInfo (null, new ListTypeInfo("MdlObjInMog", PrimitiveType.List),  #[
						 new AttributeDefn('type', true, MOG_OBJ_TYPE_TYPE)
						]
					)
				)
			)
		),
		"OBSERVATION" -> (
			new BlockListDefinition(OBS_TYPE_ATT, newArrayList(
					new ListDefInfo (CATEGORICAL_OBS_VALUE, CATEGORICAL_LIST_TYPE,  #[
						 new AttributeDefn(OBS_TYPE_ATT, true, OBS_TYPE_TYPE, TypeSystemProvider::REAL_TYPE.makeReference, true)
						 ]
					),
					new ListDefInfo (COUNT_OBS_VALUE, COUNT_LIST_TYPE,  #[
						 new AttributeDefn(OBS_TYPE_ATT, true, OBS_TYPE_TYPE),
						 new AttributeDefn('distn', true, TypeSystemProvider::PMF_TYPE)
						 ]
					),
					new ListDefInfo (DISCRETE_OBS_VALUE, DISCRETE_LIST_TYPE,  #[
						 new AttributeDefn(OBS_TYPE_ATT, true, OBS_TYPE_TYPE, TypeSystemProvider::UNDEFINED_TYPE, false),
						 new AttributeDefn('distn', true, TypeSystemProvider::PMF_TYPE)
						 ]
					),
					new ListDefInfo (TTE_OBS_VALUE, new ListTypeInfo("TteObs", PrimitiveType.Real),  #[
						 new AttributeDefn(OBS_TYPE_ATT, true, OBS_TYPE_TYPE),
						 new AttributeDefn('hazard', true, TypeSystemProvider::REAL_TYPE.makeReference)//,
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
			)
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
			new BlockListDefinition('input', newArrayList(
					new ListDefInfo (null, ADMINISTRATION_TYPE,  #[
						 new AttributeDefn('input', true, TypeSystemProvider::REAL_TYPE.makeReference) , new AttributeDefn('amount', true, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('doseTime', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('duration', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('start', false, TypeSystemProvider::REAL_TYPE),
						 new AttributeDefn('end', false, TypeSystemProvider::REAL_TYPE)
						 ]
					)
				)
			)
		),
		"STUDY_DESIGN" -> (
			new BlockListDefinition('interventionSequence', newArrayList(
					new ListDefInfo (null, STUDY_DESIGN_LIST_TYPE,  #[
						 new AttributeDefn('armSize', false, TypeSystemProvider::INT_TYPE),
						 new AttributeDefn('interventionSequence', true, SublistDefinitionTable::instance.getSublist(SublistDefinitionTable::INTERVENTION_SEQ_SUBLIST).makeVector),
						 new AttributeDefn('samplingSequence', false, SublistDefinitionTable::instance.getSublist(SublistDefinitionTable::SAMPLING_SEQ_SUBLIST).makeVector)
						 ]
					)
				)
			)
		),
		"DESIGN_SPACES" -> (
			new BlockListDefinition('element', newArrayList(
					new ListDefInfo ('catCov', new ListTypeInfo("DesignSpaceAmt", PrimitiveType.List),  #[
						 new AttributeDefn('objRef', true, TypeSystemProvider::GENERIC_ENUM_VALUE_TYPE.makeReference),
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('discrete', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::REAL_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('covariate', new ListTypeInfo("DesignSpaceAmt", PrimitiveType.List),  #[
						 new AttributeDefn('objRef', true, TypeSystemProvider::REAL_TYPE.makeReference),
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('discrete', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::REAL_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('amount', new ListTypeInfo("DesignSpaceAmt", PrimitiveType.List),  #[
						 new AttributeDefn('objRef', true, ADMINISTRATION_TYPE.makeReference.makeVector),
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('discrete', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::REAL_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('doseTime', new ListTypeInfo("DesignSpaceAmt", PrimitiveType.List),  #[
						 new AttributeDefn('objRef', true, ADMINISTRATION_TYPE.makeReference.makeVector),
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('discrete', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::REAL_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('duration', new ListTypeInfo("DesignSpaceDur", PrimitiveType.List),  #[
						 new AttributeDefn('objRef', true, ADMINISTRATION_TYPE.makeReference.makeVector),
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('discrete', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::REAL_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('numberTimes', new ListTypeInfo("DesignSpaceNum", PrimitiveType.List),  #[
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('objRef', true, SAMPLING_TYPE.makeReference.makeVector),
						 new AttributeDefn('discrete', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::REAL_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('sampleTime', new ListTypeInfo("DesignSpaceSample", PrimitiveType.List),  #[
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('objRef', true, SAMPLING_TYPE.makeReference.makeVector),
						 new AttributeDefn('discrete', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::REAL_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('numberArms', new ListTypeInfo("DesignSpaceSample", PrimitiveType.List),  #[
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('objRef', true, STUDY_DESIGN_LIST_TYPE.makeReference.makeVector),
						 new AttributeDefn('discrete', false, TypeSystemProvider::INT_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::INT_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('armSize', new ListTypeInfo("DesignSpaceSample", PrimitiveType.List),  #[
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('objRef', true, STUDY_DESIGN_LIST_TYPE.makeReference.makeVector),
						 new AttributeDefn('discrete', false, TypeSystemProvider::INT_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::INT_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					),
					new ListDefInfo ('parameter', new ListTypeInfo("DesignSpaceSample", PrimitiveType.List),  #[
						 new AttributeDefn('element', true, ELEMENT_TYPE),
						 new AttributeDefn('objRef', true, TypeSystemProvider::REAL_TYPE.makeReference.makeVector),
						 new AttributeDefn('discrete', false, TypeSystemProvider::INT_TYPE.makeVector),
						 new AttributeDefn('range', false, TypeSystemProvider::INT_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'objRef' -> true, 'element' -> true, 'discrete' ->true },
						 	#{ 'objRef' -> true, 'element' -> true, 'range' ->true }
						 ],
						 false
					)
				)
			)
		),
		"SAMPLING" -> (
			new BlockListDefinition('type', newArrayList(
					new ListDefInfo ('simple', SAMPLING_TYPE,  #[
						 new AttributeDefn('type', true, SAMPLING_TYPE_TYPE),
						 new AttributeDefn('outcome', true, TypeSystemProvider::REAL_TYPE.makeReference),
						 new AttributeDefn('sampleTime', false, TypeSystemProvider::REAL_TYPE.makeVector),
						 new AttributeDefn('numberTimes', false, TypeSystemProvider::INT_TYPE),
						 new AttributeDefn('numberSamples', false, TypeSystemProvider::INT_TYPE.makeVector)
						 ],
						 #[
						 	#{ 'type' -> true, 'outcome' -> true, 'sampleTime' ->false, 'numberTimes' ->false, 'numberSamples' ->false }
						 ],
						 false
					),
//					new ListDefInfo ('simple', SAMPLING_TYPE,  #[
//						 new AttributeDefn('type', true, SAMPLING_TYPE_TYPE),
//						 new AttributeDefn('outcome', true, MclTypeProvider::REAL_TYPE.makeReference),
//						 new AttributeDefn('sampleTime', false, MclTypeProvider::REAL_TYPE.makeVector),
//						 new AttributeDefn('numberTimes', false, MclTypeProvider::INT_TYPE),
//						 new AttributeDefn('numberSamples', false, MclTypeProvider::INT_TYPE.makeVector)
//						 ]
//					),
//					new ListDefInfo('complex', CPLX_SAMPLING_TYPE, #[
//						 new AttributeDefn('type', true, SAMPLING_TYPE_TYPE),
//						 new AttributeDefn('combination', true, SublistDefinitionTable::getSublist(SublistDefinitionTable::SAMPLING_SEQ_SUBLIST).makeVector),
//						 new AttributeDefn('numberTimes', false, TypeSystemProvider::INT_TYPE)
//						 ]
//					)//,
					new ListDefInfo('derived', DERIV_SAMPLING_TYPE, #[
						 new AttributeDefn('type', true, SAMPLING_TYPE_TYPE),
						 new AttributeDefn('combination', true, SublistDefinitionTable::instance.getSublist(SublistDefinitionTable::COMPLEX_COMBINATION_SUBLIST).makeVector)
//						 new AttributeDefn('combination', true, SAMPLING_SUPER_LIST.makeReference.makeVector),
//						 new AttributeDefn('numberTimes', false, TypeSystemProvider::INT_TYPE)
						 ]
					)
				)
			)
		)
		
	}
	
}