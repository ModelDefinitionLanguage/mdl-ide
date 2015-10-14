package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.MclObject
import java.util.HashSet
import java.util.Map

class BlockDefinitionProvider {
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
	
	
	
	
	val static Map<String, Map<String, Boolean> > BlkData = #{
		MdlValidator::MDLOBJ -> #{
			COVARIATE_BLK_NAME -> false, VAR_LVL_BLK_NAME -> true, MDL_STRUCT_PARAMS -> false,
			MDL_VAR_PARAMS -> false, MDL_RND_VARS -> false,
			MDL_INDIV_PARAMS -> false, MDL_PRED_BLK_NAME -> false,
			OBS_BLK_NAME -> false, MDL_GRP_PARAMS -> false, IDV_BLK_NAME -> false
		},
		MdlValidator::DATAOBJ -> #{
			DIV_BLK_NAME -> true, "DECLARED_VARIABLES" -> false, "DATA_DERIVED_VARIABLES" -> false,
			DATA_SRC_BLK -> true
		},
		MdlValidator::PARAMOBJ -> #{
			PARAM_VARIABILITY_BLK -> false, "DECLARED_VARIABLES" -> false, PARAM_STRUCT_BLK -> false
		},
		MdlValidator::TASKOBJ -> #{
			ESTIMATE_BLK -> false, SIMULATE_BLK -> false, "OPTIMISE" -> false
		},
		MdlValidator::MOGOBJ -> #{
			MOG_OBJ_NAME -> true
		},
		MdlValidator::DESIGNOBJ -> #{
			"DECLARED_VARIABLES" -> false, "ADMINISTRATION" -> true, "STUDY_DESIGN" -> true, "SAMPLING" -> false, "DESIGN_SPACES" -> false
		}
	}

	val static SubBlkData = #{
		MDL_DEQ_BLK -> MDL_PRED_BLK_NAME, MDL_CMT_BLK -> MDL_PRED_BLK_NAME
	}
	
	new(){
			
	}
	
	def isModelBlock(BlockStatement it){
		if(eContainer instanceof MclObject){
			val objType = (eContainer as MclObject).mdlObjType
			return BlkData.containsKey(objType) && BlkData.get(objType).containsKey(identifier)
		}
		false
	}
	
	def isModelSubBlock(BlockStatement it){
		SubBlkData.containsKey(identifier)
	}
	
	def subBlockHasCorrectParent(BlockStatement it, BlockStatement parent){
		SubBlkData.containsKey(identifier) && SubBlkData.get(identifier) == parent.identifier
	}
	
	def getUnusedMandatoryBlocks(MclObject it){
		// map of just the mandatory blocks
		val mandatoryBlock = new HashSet<String>
		if(BlkData.containsKey(mdlObjType)){
			mandatoryBlock.addAll(BlkData.get(mdlObjType).filter[blk, mand|mand].keySet)
			for(blk : blocks){
				// remove mandatory block if it exists
				mandatoryBlock.remove(blk.identifier)
			}
		}
		mandatoryBlock
	}
	
	

}