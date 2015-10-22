package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.MclObject
import java.util.HashSet
import java.util.Map
import java.util.List
import eu.ddmore.mdl.mdl.Statement

import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.emf.ecore.EClass
import java.util.Set
import java.util.HashMap

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
	
	
	@Data @FinalFieldsConstructor
	static class StatementSpec{
		EClass statementType
		boolean expectHasRhs
		int minNum
		int maxNum
		
		new(EClass stmtType, int min, int max){
			statementType = stmtType
			expectHasRhs = false
			minNum = min
			maxNum = max
		}
		
		def dispatch boolean isValidStatement(Statement stmt){
			stmt == statementType
		}
		
		def dispatch boolean isValidStatement(EquationDefinition stmt){
			if(stmt == statementType){
				(expectHasRhs && stmt.expression != null) || (!expectHasRhs && stmt.expression == null) 
			}
			else false
		}
		
	}
	
	@Data @FinalFieldsConstructor
	static class BlockSpec{
		String name
		int minNum
		int maxNum
		List<StatementSpec> validStatementTypes

		def boolean isMandatory(){
			minNum > 0
		}
			
	}
	
	static val ep = MdlPackage::eINSTANCE 

	val static Map<String, BlockSpec> BlkDefns = #{
			COVARIATE_BLK_NAME -> new BlockSpec(COVARIATE_BLK_NAME, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.equationDefinition, true, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.enumerationDefinition, 0, Integer.MAX_VALUE)
			]),
			VAR_LVL_BLK_NAME -> new BlockSpec(VAR_LVL_BLK_NAME, 1, Integer.MAX_VALUE, #[new StatementSpec(ep.listDefinition, 1, Integer.MAX_VALUE)]),
			MDL_STRUCT_PARAMS -> new BlockSpec(MDL_STRUCT_PARAMS, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.equationDefinition, true, 0, Integer.MAX_VALUE)
			]),
			MDL_VAR_PARAMS -> new BlockSpec(MDL_VAR_PARAMS, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.equationDefinition, true, 0, Integer.MAX_VALUE)
			]),
			MDL_RND_VARS -> new BlockSpec(MDL_RND_VARS, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.randomVariableDefinition, 0, Integer.MAX_VALUE)
			]),
			MDL_INDIV_PARAMS -> new BlockSpec(MDL_INDIV_PARAMS, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			MDL_PRED_BLK_NAME -> new BlockSpec(MDL_PRED_BLK_NAME, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.equationDefinition, true, 0, Integer.MAX_VALUE)
			]),
			OBS_BLK_NAME -> new BlockSpec(OBS_BLK_NAME, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.equationDefinition, true, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			MDL_GRP_PARAMS -> new BlockSpec(MDL_GRP_PARAMS, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.equationDefinition, true, 0, Integer.MAX_VALUE)
			]),
			IDV_BLK_NAME -> new BlockSpec(IDV_BLK_NAME, 0, 1, #[
				new StatementSpec(ep.equationDefinition, false, 1, 1)
			]),
			DIV_BLK_NAME -> new BlockSpec(DIV_BLK_NAME, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 1, Integer.MAX_VALUE)
			]),
			"DECLARED_VARIABLES" -> new BlockSpec("DECLARED_VARIABLES", 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.equationDefinition, false, 0, Integer.MAX_VALUE)
			]),
			"DATA_DERIVED_VARIABLES" -> new BlockSpec("DATA_DERIVED_VARIABLES", 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			DATA_SRC_BLK -> new BlockSpec(DATA_SRC_BLK, 1, 1, #[
				new StatementSpec(ep.listDefinition, false, 1, 1)
			]),
			PARAM_VARIABILITY_BLK -> new BlockSpec(PARAM_VARIABILITY_BLK, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			PARAM_STRUCT_BLK -> new BlockSpec(PARAM_STRUCT_BLK, 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			ESTIMATE_BLK -> new BlockSpec(ESTIMATE_BLK, 0, 1, #[
				new StatementSpec(ep.propertyStatement, 0, Integer.MAX_VALUE)
			]),
			SIMULATE_BLK -> new BlockSpec(SIMULATE_BLK, 0, 1, #[
				new StatementSpec(ep.propertyStatement, 0, Integer.MAX_VALUE)
			]),
			MOG_OBJ_NAME -> new BlockSpec(MOG_OBJ_NAME, 0, 1, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			MDL_DEQ_BLK -> new BlockSpec(MDL_DEQ_BLK, 0, 1, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			MDL_CMT_BLK -> new BlockSpec(MDL_CMT_BLK, 0, 1, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			"ADMINISTRATION" -> new BlockSpec("ADMINISTRATION", 1, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.equationDefinition, true, 0, Integer.MAX_VALUE),
				new StatementSpec(ep.equationDefinition, false, 0, Integer.MAX_VALUE)
			]),
			"STUDY_DESIGN" -> new BlockSpec("STUDY_DESIGN", 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			"SAMPLING" -> new BlockSpec("SAMPLING", 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			]),
			"DESIGN_SPACES" -> new BlockSpec("DESIGN_SPACES", 0, Integer.MAX_VALUE, #[
				new StatementSpec(ep.listDefinition, 0, Integer.MAX_VALUE)
			])
	}	
	
	val static Map<String, Set<String> > BlkData = #{
		MdlValidator::MDLOBJ -> #{
			COVARIATE_BLK_NAME, VAR_LVL_BLK_NAME, MDL_STRUCT_PARAMS,
			MDL_VAR_PARAMS, MDL_RND_VARS,
			MDL_INDIV_PARAMS, MDL_PRED_BLK_NAME,
			OBS_BLK_NAME, MDL_GRP_PARAMS, IDV_BLK_NAME
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
			"DECLARED_VARIABLES", "ADMINISTRATION", "STUDY_DESIGN", "SAMPLING", "DESIGN_SPACES"
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
			return BlkData.containsKey(objType) && BlkData.get(objType).contains(identifier)
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
			mandatoryBlock.addAll(BlkData.get(mdlObjType).filter[blk| BlkDefns.get(blk).isMandatory])
			for(blk : blocks){
				// remove mandatory block if it exists
				mandatoryBlock.remove(blk.identifier)
			}
		}
		mandatoryBlock
	}
	
	def validateBlocksCounts(MclObject it, (String, int) => void errLambda){
		val blkCount = new HashMap<String, Integer>

		blocks.forEach[
			if(!blkCount.containsKey(identifier)) blkCount.put(identifier, 0)
			blkCount.put(identifier, blkCount.get(identifier)+1) 
		]
		// now check if counts exceed maximums
		blkCount.keySet.forEach[name|
			val defn = BlkDefns.get(name)
			if(defn != null){
				if(blkCount.get(name) > defn.maxNum)
					errLambda.apply(name, defn.maxNum)
			}
		]
	}	

}