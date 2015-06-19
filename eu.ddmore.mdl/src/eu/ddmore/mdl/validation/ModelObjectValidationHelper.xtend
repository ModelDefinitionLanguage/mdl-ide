package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.MclObject
import java.util.HashSet

class ModelObjectValidationHelper {
	val static BlkData = #{
		"COVARIATES" -> false, "VARIABILITY_LEVELS" -> true, "STRUCTURAL_PARAMETERS" -> false,
		"VARIABILITY_PARAMETERS" -> false, "RANDOM_VARIABLE_DEFINITION" -> false,
		"INDIVIDUAL_VARIABLES" -> false, "MODEL_PREDICTION" -> false,
		"OBSERVATION" -> false, "GROUP_VARIABLES" -> false
	}

	val static SubBlkData = #{
		"DEQ" -> "MODEL_PREDICTION", "COMPARTMENT" -> "MODEL_PREDICTION"
	}
	
	new(){
			
	}
	
	def isModelBlock(String identifier){
		BlkData.containsKey(identifier)
	}
	
	def isModelSubBlock(String identifier){
		SubBlkData.containsKey(identifier)
	}
	
	def subBlockHasCorrectParent(String subBlock, String parent){
		val expectedParent = SubBlkData.get(subBlock)
		if(expectedParent != null) expectedParent == parent else false
	}
	
	def getUnusedMandatoryBlocks(MclObject it){
		// map of just the mandatory blocks
		val mandatoryBlock = new HashSet<String>(BlkData.filter[blk, mand|mand == true].keySet)
		for(blk : blocks){
			// remove mandatory block if it exists
			mandatoryBlock.remove(blk.identifier)
		}
		mandatoryBlock
	}
	
}