package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.BlockTextBody
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.Statement
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

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
	public static val PRIOR_SOURCE_BLK = "PRIOR_SOURCE"
	public static val DES_STUDY_DESIGN = "STUDY_DESIGN"
	
	@Data @FinalFieldsConstructor
	static class StatementSpec{
		EClass statementType
		boolean expectHasRhs
		
		new(EClass stmtType){
			statementType = stmtType
			expectHasRhs = false
		}
		
		def dispatch boolean isValidStatement(Statement stmt){
			stmt.eClass == statementType
		}
		
		def dispatch boolean isValidStatement(EquationDefinition stmt){
			if(stmt.eClass == statementType){
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
		int minStmtNum
		int maxStmtNum
		boolean isStmtBlk
		List<StatementSpec> validStatementTypes

		new (String name, int minNum, int maxNum, int minStmtNum, int maxStmtNum, List<StatementSpec> validStatementTypes){
			this.name = name
			this.minNum = minNum
			this.maxNum = maxNum
			this.minStmtNum = minStmtNum
			this.maxStmtNum = maxStmtNum
			this.isStmtBlk = true
			this.validStatementTypes = validStatementTypes
		}
	

		def boolean isMandatory(){
			minNum > 0
		}
			
	}
	
	static val ep = MdlPackage::eINSTANCE 

	val static Map<String, BlockSpec> BlkDefns = #{
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
	
	val static Map<String, Set<String> > BlkData = #{
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

	val static Map<String, String> SubBlkData = #{
		MDL_DEQ_BLK -> MDL_PRED_BLK_NAME, MDL_CMT_BLK -> MDL_PRED_BLK_NAME,
		PRIOR_SOURCE_BLK -> "NON_CANONICAL_DISTRIBUTION",
		"INPUT_PRIOR_DATA" -> "NON_CANONICAL_DISTRIBUTION" 
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
			mandatoryBlock.addAll(BlkData.get(mdlObjType).filter[b|
				BlkDefns.get(b).isMandatory
			])
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

	def validateMaxBlocksStatementCounts(BlockStatementBody it, (String, int) => void errLambda){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val defn = BlkDefns.get(blk.identifier)
		if(defn != null)
			if(statements.size > defn.maxStmtNum){
				errLambda.apply(blk.identifier, defn.maxStmtNum)
			}		
	}

	def validateMinBlocksStatementCounts(BlockStatementBody it, (String, int) => void errLambda){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val defn = BlkDefns.get(blk.identifier)
		if(defn != null)
			if(statements.size < defn.minStmtNum){
				errLambda.apply(blk.identifier, defn.minStmtNum)
			}		
	}

	def validateExpectedStatementType(Statement it, (String) => void errLambda){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val defn = BlkDefns.get(blk.identifier)
		if(defn != null){
			for(stmtSpec : defn.validStatementTypes){
				if(stmtSpec.isValidStatement(it)) return
			}	
			errLambda.apply(blk.identifier)
		}
	}
	
	def validateBlockBodyType(BlockStatementBody it, (String) => void errLambda){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val defn = BlkDefns.get(blk.identifier)
		if(defn != null){
			if(!defn.isStmtBlk) errLambda.apply(blk.identifier)
		}
	}
	
	def validateBlockBodyType(BlockTextBody it, (String) => void errLambda){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val defn = BlkDefns.get(blk.identifier)
		if(defn != null){
			if(defn.isStmtBlk) errLambda.apply(blk.identifier)
		}
	}
	
}
