package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.BlockTextBody
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.MclObject
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
	
	val Map<String, Set<String> > BlkData
	val Map<String, String> SubBlkData
	val Map<String, BlockSpec> BlkDefns
	
	new(){
		BlkData = BlockDefinitionTable::BlkData
		SubBlkData =  BlockDefinitionTable::SubBlkData
		BlkDefns = BlockDefinitionTable::BlkDefns
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
