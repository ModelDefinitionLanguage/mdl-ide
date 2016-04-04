package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.BlockDefinition
import eu.ddmore.mdllib.mdllib.StatementType
import eu.ddmore.mdllib.mdllib.StatementTypeDefn
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtend.lib.annotations.Data

class BlockDefinitionProvider {

	@Data
	static class StatementSpec{
		EClass statementType
		boolean expectHasRhs
		
//		new(EClass stmtType){
//			statementType = stmtType
//			expectHasRhs = false
//		}

		new(StatementTypeDefn std){
			statementType = getStmtClassFromType(std.stmtType)
			expectHasRhs = std.hasRhs
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
		
		def static getStmtClassFromType(StatementType st){
			val stmtClass = MdlPackage.eINSTANCE
			switch(st){
				case StatementType.ANON_LIST_STMT:
					stmtClass.anonymousListStatement
//				case StatementType.CAT_PROP_ASSIGN:
//					stmtClass.categoricalProbabilityAssign
				case StatementType.ENUM_DEFN:
					stmtClass.enumerationDefinition
				case StatementType.EQN_DEFN:
					stmtClass.equationDefinition
				case StatementType.LIST_DEFN:
					stmtClass.listDefinition
				case StatementType.PROP_STMT:
					stmtClass.propertyStatement
				case StatementType.RV_DEFN:
					stmtClass.randomVariableDefinition
				case StatementType.TRANS_DEFN:
					stmtClass.transformedDefinition
				default: null
			}
		}
		
	}
	
	@Data
	static class BlockSpec{
		String name
		int minNum
		int maxNum
		int minStmtNum
		int maxStmtNum
		boolean isStmtBlk
		List<StatementSpec> validStatementTypes

		new (BlockDefinition bd){
			this.name = bd.name
			this.minNum = bd.minNum
			this.maxNum = if(bd.maxNum == 0) Integer.MAX_VALUE else bd.maxNum
			this.minStmtNum = bd.minStmtNum
			this.maxStmtNum = if(bd.maxStmtNum == 0) Integer.MAX_VALUE else bd.maxStmtNum
			this.isStmtBlk = true
			this.validStatementTypes = new ArrayList<StatementSpec>
			bd.stmtTypes.forEach[
				validStatementTypes.add(new StatementSpec(it))
			]
		}
	

		def boolean isMandatory(){
			minNum > 0
		}
			
	}
	
	extension BlockUtils bu = new BlockUtils
	extension MdlLibUtils mlu = new MdlLibUtils 

//	val Map<String, Set<String> > BlkData
//	val Map<String, String> SubBlkData
//	val Map<String, BlockSpec> BlkDefns
	
	new(){
//		BlkData = BlockDefinitionTable::BlkData
//		SubBlkData =  BlockDefinitionTable::SubBlkData
//		BlkDefns = BlockDefinitionTable::BlkDefns
	}
	
	def isModelBlock(BlockStatement bs){
		val obj = bs.eContainer
		if(obj instanceof MclObject){
			val objType = obj.objId
			return objType.canContainBlock(bs.blkId)
//			return BlkData.containsKey(objType) && BlkData.get(objType).contains(identifier)
		}
		false
	}
	
	def isModelSubBlock(BlockStatement bs, MclObject mo){
//		SubBlkData.containsKey(identifier)
		!mo.objId.containmentDefnForObj.blkRefs.exists[
			name == bs.blkId.name
		]
	}
	
	def subBlockHasCorrectParent(BlockStatement it, BlockStatement parent){
		return parent.blkId.canContainBlock(blkId)
//		SubBlkData.containsKey(identifier) && SubBlkData.get(identifier) == parent.identifier
	}
	
	
	// OK Plan is to get the library definition from the object ref and then use it to get the list of mandatory 
	// blocks. Prob put the searching method in MdlLibUtils.-
	def getUnusedMandatoryBlocks(MclObject it){
		val mdlLib = libraryForObject
		// map of just the mandatory blocks
		val mandatoryBlock = mdlLib.getMandatoryBlockNamesForObject(objId)
//		if(BlkData.containsKey(mdlObjType)){
		if(!mandatoryBlock.isEmpty){
//			mandatoryBlock.addAll(BlkData.get(mdlObjType).filter[b|
//				BlkDefns.get(b).isMandatory
//			])
			for(blk : blocks){
				// remove mandatory block if it exists
				mandatoryBlock.remove(blk.identifier)
			}
		}
		mandatoryBlock
	}


	def BlockSpec getBlockDefn(BlockDefinition blkDefn){
//		BlkDefns.get(name)
		new BlockSpec(blkDefn)
	}	
}
