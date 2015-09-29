package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.MclObject
import java.util.HashSet
import java.util.Map

class BlockDefinitionProvider {
	public static val COVARIATE_BLK_NAME = "COVARIATES"
	public static val OBS_BLK_NAME = "OBSERVATION"
	public static val DIV_BLK_NAME = "DATA_INPUT_VARIABLES"
	public static val MOG_OBJ_NAME = "OBJECTS"
	public static val VAR_LVL_BLK_NAME = "VARIABILITY_LEVELS"
	public static val IDV_BLK_NAME = "IDV"
	public static val MDL_PRED_BLK_NAME = "MODEL_PREDICTION"
	
	
	val static Map<String, Map<String, Boolean> > BlkData = #{
		MdlValidator::MDLOBJ -> #{
			COVARIATE_BLK_NAME -> false, "VARIABILITY_LEVELS" -> true, "STRUCTURAL_PARAMETERS" -> false,
			"VARIABILITY_PARAMETERS" -> false, "RANDOM_VARIABLE_DEFINITION" -> false,
			"INDIVIDUAL_VARIABLES" -> false, MDL_PRED_BLK_NAME -> false,
			OBS_BLK_NAME -> false, "GROUP_VARIABLES" -> false, IDV_BLK_NAME -> false
		},
		MdlValidator::DATAOBJ -> #{
			DIV_BLK_NAME -> true, "DECLARED_VARIABLES" -> false, "DATA_DERIVED_VARIABLES" -> false,
			"SOURCE" -> true
		},
		MdlValidator::PARAMOBJ -> #{
			"VARIABILITY" -> false, "DECLARED_VARIABLES" -> false, "STRUCTURAL" -> false
		},
		MdlValidator::TASKOBJ -> #{
			"ESTIMATE" -> false, "SIMULATE" -> false, "OPTIMISE" -> false
		},
		MdlValidator::MOGOBJ -> #{
			MOG_OBJ_NAME -> true
		},
		MdlValidator::DESIGNOBJ -> #{
			"DECLARED_VARIABLES" -> false, "ADMINISTRATION" -> true, "STUDY_DESIGN" -> true, "SAMPLING" -> false, "DESIGN_SPACES" -> false
		}
	}

	val static SubBlkData = #{
		"DEQ" -> "MODEL_PREDICTION", "COMPARTMENT" -> "MODEL_PREDICTION"
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