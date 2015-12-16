package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.validation.BlockDefinitionProvider.BlockSpec
import java.util.Map
import eu.ddmore.mdl.validation.BlockDefinitionProvider.StatementSpec
import java.util.Set

class BlockDefinitionTable {
	public static val COVARIATE_BLK_NAME = "COVARIATES"
	public static val OBS_BLK_NAME = "OBSERVATION"
	public static val DIV_BLK_NAME = "DATA_INPUT_VARIABLES"
	public static val DATA_DERIV_BLK_NAME = "DATA_DERIVED_VARIABLES"
	public static val MOG_OBJ_NAME = "OBJECTS"
	public static val VAR_LVL_BLK_NAME = "VARIABILITY_LEVELS"
	public static val IDV_BLK_NAME = "IDV"
	public static val MDL_PRED_BLK_NAME = "MODEL_PREDICTION"
	public static val MDL_DEQ_BLK = "DEQ" 
	public static val MDL_CMT_BLK = "COMPARTMENT"
	public static val MDL_STRUCT_PARAMS = "STRUCTURAL_PARAMETERS"
	public static val MDL_VAR_PARAMS = "VARIABILITY_PARAMETERS"
	public static val MDL_INDIV_PARAMS = "INDIVIDUAL_VARIABLES"
	public static val MDL_GRP_PARAMS = "GROUP_VARIABLES"
	public static val MDL_RND_VARS = "RANDOM_VARIABLE_DEFINITION"
	public static val PARAM_STRUCT_BLK = "STRUCTURAL"
	public static val PARAM_VARIABILITY_BLK = "VARIABILITY"
	public static val DATA_SRC_BLK = "SOURCE"
	public static val ESTIMATE_BLK = "ESTIMATE" 
	public static val SIMULATE_BLK = "SIMULATE" 
	public static val PRIOR_SOURCE_BLK = "PRIOR_SOURCE"
	public static val DES_STUDY_DESIGN = "STUDY_DESIGN"
	
	static val ep = MdlPackage::eINSTANCE 

	public val static Map<String, BlockSpec> BlkDefns = #{
			COVARIATE_BLK_NAME -> new BlockSpec(COVARIATE_BLK_NAME, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true),
				new StatementSpec(ep.enumerationDefinition),
				new StatementSpec(ep.randomVariableDefinition)
			]),
			VAR_LVL_BLK_NAME -> new BlockSpec(VAR_LVL_BLK_NAME, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition)
			]),
			MDL_STRUCT_PARAMS -> new BlockSpec(MDL_STRUCT_PARAMS, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true)
			]),
			MDL_VAR_PARAMS -> new BlockSpec(MDL_VAR_PARAMS, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true)
			]),
			MDL_RND_VARS -> new BlockSpec(MDL_RND_VARS, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.randomVariableDefinition)
			]),
			MDL_INDIV_PARAMS -> new BlockSpec(MDL_INDIV_PARAMS, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, true),
				new StatementSpec(ep.transformedDefinition)
			]),
			MDL_PRED_BLK_NAME -> new BlockSpec(MDL_PRED_BLK_NAME, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true),
				new StatementSpec(ep.listDefinition)
			]),
			OBS_BLK_NAME -> new BlockSpec(OBS_BLK_NAME, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, true),
				new StatementSpec(ep.transformedDefinition),
				new StatementSpec(ep.listDefinition)
			]),
			MDL_GRP_PARAMS -> new BlockSpec(MDL_GRP_PARAMS, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true)
			]),
			IDV_BLK_NAME -> new BlockSpec(IDV_BLK_NAME, 1, 1, 1, 1, #[
				new StatementSpec(ep.equationDefinition, false)
			]),
			DIV_BLK_NAME -> new BlockSpec(DIV_BLK_NAME, 0, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition)
			]),
			"DECLARED_VARIABLES" -> new BlockSpec("DECLARED_VARIABLES", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.enumerationDefinition),
				new StatementSpec(ep.equationDefinition, false)
			]),
			"DATA_DERIVED_VARIABLES" -> new BlockSpec("DATA_DERIVED_VARIABLES", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition)
			]),
			DATA_SRC_BLK -> new BlockSpec(DATA_SRC_BLK, 1, 1, 1, 1, #[
				new StatementSpec(ep.listDefinition, false)
			]),
			PARAM_VARIABILITY_BLK -> new BlockSpec(PARAM_VARIABILITY_BLK, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition)
			]),
			PARAM_STRUCT_BLK -> new BlockSpec(PARAM_STRUCT_BLK, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition)
			]),
			ESTIMATE_BLK -> new BlockSpec(ESTIMATE_BLK, 0, 1, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.propertyStatement)
			]),
			SIMULATE_BLK -> new BlockSpec(SIMULATE_BLK, 0, 1, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.propertyStatement)
			]),
			MOG_OBJ_NAME -> new BlockSpec(MOG_OBJ_NAME, 1, 1, 4, 5, #[
				new StatementSpec(ep.listDefinition)
			]),
			MDL_DEQ_BLK -> new BlockSpec(MDL_DEQ_BLK, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition),
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true)
			]),
			MDL_CMT_BLK -> new BlockSpec(MDL_CMT_BLK, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition),
				new StatementSpec(ep.anonymousListStatement)
			]),
			"FUNCTIONS" -> new BlockSpec("FUNCTIONS", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.userFunctionDefinition)
			]),
			"ADMINISTRATION" -> new BlockSpec("ADMINISTRATION", 1, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition),
				new StatementSpec(ep.equationDefinition, true),
				new StatementSpec(ep.equationDefinition, false)
			]),
			DES_STUDY_DESIGN -> new BlockSpec(DES_STUDY_DESIGN, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.propertyStatement),
				new StatementSpec(ep.listDefinition),
				new StatementSpec(ep.categoricalProbabilityAssign)
			]),
			"SAMPLING" -> new BlockSpec("SAMPLING", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition)
			]),
			"DESIGN_SPACES" -> new BlockSpec("DESIGN_SPACES", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition)
			]),
			"DESIGN_PARAMETERS" -> new BlockSpec("DESIGN_PARAMETERS", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true)
			]),
			"PRIOR_PARAMETERS" -> new BlockSpec("PRIOR_PARAMETERS", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true)
			]),
			"NON_CANONICAL_DISTRIBUTION" -> new BlockSpec("NON_CANONICAL_DISTRIBUTION", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true)
			]),
			"PRIOR_VARIABLE_DEFINITION" -> new BlockSpec("PRIOR_VARIABLE_DEFINITION", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true),
				new StatementSpec(ep.randomVariableDefinition)
			]),
			"INPUT_PRIOR_DATA" -> new BlockSpec("INPUT_PRIOR_DATA", 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false),
				new StatementSpec(ep.equationDefinition, true)
			]),
			PRIOR_SOURCE_BLK -> new BlockSpec(PRIOR_SOURCE_BLK, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition)
			])
	}	
	
	public val static Map<String, Set<String> > BlkData = #{
		MdlValidator::MDLOBJ -> #{
			COVARIATE_BLK_NAME, VAR_LVL_BLK_NAME, MDL_STRUCT_PARAMS,
			MDL_VAR_PARAMS, MDL_RND_VARS,
			MDL_INDIV_PARAMS, MDL_PRED_BLK_NAME,
			OBS_BLK_NAME, MDL_GRP_PARAMS, IDV_BLK_NAME, "FUNCTIONS"
		},
		MdlValidator::FUNCOBJ -> #{
			"FUNCTIONS"
		},
		MdlValidator::DATAOBJ -> #{
			DIV_BLK_NAME, "DECLARED_VARIABLES", "DATA_DERIVED_VARIABLES",
			DATA_SRC_BLK
		},
		MdlValidator::PARAMOBJ -> #{
			PARAM_VARIABILITY_BLK, "DECLARED_VARIABLES", PARAM_STRUCT_BLK
		},
		MdlValidator::TASKOBJ -> #{
			ESTIMATE_BLK, SIMULATE_BLK//, "OPTIMISE"
		},
		MdlValidator::MOGOBJ -> #{
			MOG_OBJ_NAME
		},
		MdlValidator::DESIGNOBJ -> #{
			"DECLARED_VARIABLES", "ADMINISTRATION", "STUDY_DESIGN", "SAMPLING", "DESIGN_SPACES", COVARIATE_BLK_NAME,
			"DESIGN_PARAMETERS"
		},
		MdlValidator::PRIOROBJ -> #{
			"PRIOR_PARAMETERS", "NON_CANONICAL_DISTRIBUTION",
			"PRIOR_VARIABLE_DEFINITION"
		}
	}

	public val static Map<String, String> SubBlkData = #{
		MDL_DEQ_BLK -> MDL_PRED_BLK_NAME, MDL_CMT_BLK -> MDL_PRED_BLK_NAME,
		PRIOR_SOURCE_BLK -> "NON_CANONICAL_DISTRIBUTION",
		"INPUT_PRIOR_DATA" -> "NON_CANONICAL_DISTRIBUTION" 
	}
	
	
}