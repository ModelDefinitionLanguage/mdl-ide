package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.BlockArgument
import eu.ddmore.mdl.mdl.BlockArguments
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.BlockTextBody
import eu.ddmore.mdl.mdl.ForwardDeclaration
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.provider.BlockDefinitionProvider

class BlockValidator extends AbstractDeclarativeValidator{

	override register(EValidatorRegistrar registrar){}
	
	extension BlockArgumentDefinitionProvider movh = new BlockArgumentDefinitionProvider
	extension BlockDefinitionProvider blokHelper = new BlockDefinitionProvider
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils

	public static val UNRECOGNISED_OBJECT_TYPE = "eu.ddmore.mdl.validation.UnrecognisedObjectType"

	// Block arguments validation
	public static val UNKNOWN_BLOCK_ARG_DECL = "eu.ddmore.mdl.validation.UnknownBlockArgDecl"
	public static val UNKNOWN_BLOCK_ARG_PROP = "eu.ddmore.mdl.validation.UnknownBlockArgProp"
	public static val MANDATORY_BLOCK_ARG_MISSING = "eu.ddmore.mdl.validation.MandatoryBlockArgMissing"
	public static val MANDATORY_BLOCK_PROP_MISSING = "eu.ddmore.mdl.validation.MandatoryBlockPropMissing"
	// Block validation
	public static val UNKNOWN_BLOCK = "eu.ddmore.mdl.validation.UnknownBlock"
	public static val WRONG_SUBBLOCK = "eu.ddmore.mdl.validation.WrongSubBlock"
	public static val WRONG_PARENT_BLOCK = "eu.ddmore.mdl.validation.WrongParentBlock"
	public static val MANDATORY_BLOCK_MISSING = "eu.ddmore.mdl.validation.MandatoryBlockMissing"
	public static val BLOCK_COUNT_EXCEEDED = "eu.ddmore.mdl.validation.BlockCountExceeded"
	public static val BLOCK_INCORRECT_STATEMENT_COUNT = "eu.ddmore.mdl.validation.BlockIncorrrectStatementCount"
	public static val BLOCK_INVALID_STATEMENT_TYPE = "eu.ddmore.mdl.validation.BlockInvalidStatementType"
	public static val BLOCK_WRONG_BODY_TYPE = "eu.ddmore.mdl.validation.WrongBodyType"

	
	static val VALID_OBJECT_TYPES = #[ MdlValidator::MDLOBJ, MdlValidator::PARAMOBJ, MdlValidator::TASKOBJ, MdlValidator::DATAOBJ, MdlValidator::MOGOBJ,
		MdlValidator::DESIGNOBJ, MdlValidator::PRIOROBJ, MdlValidator::FUNCOBJ
	]


	@Check
	def validateMdlObjArguments(MclObject it){
		if (mdlObjType != null) {
			if (!VALID_OBJECT_TYPES.contains(mdlObjType)) {
				error("unrecognised object type '" + mdlObjType + "'", MdlPackage.eINSTANCE.mclObject_MdlObjType,
					UNRECOGNISED_OBJECT_TYPE, mdlObjType)
			}
			blkArgs.unusedMandatoryObjVarDecl.forEach [ blk, mand |
				error("mandatory argument '" + blk + "' is missing in " + mdlObjType + " '" + name + "'",
					MdlPackage.eINSTANCE.mclObject_BlkArgs, MANDATORY_BLOCK_ARG_MISSING, blk)
			]
			blkArgs.unusedMandatoryPropertyArguments.forEach [ blk, mand |
				error("mandatory property '" + blk + "' is missing in " + mdlObjType + " '" + name + "'",
					MdlPackage.eINSTANCE.mclObject_BlkArgs, MANDATORY_BLOCK_PROP_MISSING, blk)
			]
		}
	}
	
	@Check
	def validateMdlObjBlockArgs(BlockStatement it){
		blkArgs.unusedMandatoryObjVarDecl.forEach[blk, mand| error("mandatory argument '" + blk + "' is missing in block '" + identifier + "'",
					MdlPackage.eINSTANCE.blockStatement_BlkArgs, MANDATORY_BLOCK_ARG_MISSING, blk) ]
		blkArgs.unusedMandatoryPropertyArguments.forEach[blk, mand| error("mandatory property '" + blk + "' is missing in block '" + identifier + "'",
					MdlPackage.eINSTANCE.blockStatement_BlkArgs, MANDATORY_BLOCK_PROP_MISSING, blk) ]
	}

	@Check
	def validateMdlObjBlocks(BlockStatement it){
		val parent = parentOfBlockStatement
		switch(parent){
			MclObject case !isModelBlock:
					error("block '" + identifier + "' cannot be used in an object of type " + parent.mdlObjType,
						MdlPackage.eINSTANCE.blockStatement_Identifier, UNKNOWN_BLOCK, identifier)
			BlockStatement: {
				if (!isModelSubBlock) {
					error("block '" + identifier + "' cannot be used as a sub-block",
						MdlPackage.eINSTANCE.blockStatement_Identifier, WRONG_SUBBLOCK,
						identifier)
				} else if (!subBlockHasCorrectParent(parent)) {
					// recognised sub-block but in the wrong place
					error("sub-block '" + identifier + "' cannot be used in the '" + parent.identifier + "' block",
						MdlPackage.eINSTANCE.blockStatement_Identifier,
						WRONG_PARENT_BLOCK, identifier)
				}
			}
				
		}
	}

	@Check
	def validateBlockStatementCounts(BlockStatementBody it){
		validateMinBlocksStatementCounts[blk, minLimit| error("block '" + blk + "' has fewer statements than the " + minLimit + " expected",
					MdlPackage.eINSTANCE.blockStatementBody_Statements, BLOCK_INCORRECT_STATEMENT_COUNT, blk)]
		validateMaxBlocksStatementCounts[blk, maxLimit| error("block '" + blk + "' has more statements than the " + maxLimit + " expected",
					MdlPackage.eINSTANCE.blockStatementBody_Statements, BLOCK_INCORRECT_STATEMENT_COUNT, blk)]
	}

	@Check
	def validateBlockBodyType(BlockStatementBody it){
		validateBlockBodyType[blk| error("block '" + blk + "' cannot contain statements. It must define verbatim text block: '<<' '>>'",
					MdlPackage.eINSTANCE.blockStatementBody_Statements, BLOCK_WRONG_BODY_TYPE, blk)]
	}

	@Check
	def validateBlockBodyType(BlockTextBody it){
		validateBlockBodyType[blk| error("block '" + blk + "' cannot define a verbatim text block. It must contains statements delimitted by '{' '}'",
					MdlPackage.eINSTANCE.blockTextBody_Text, BLOCK_WRONG_BODY_TYPE, blk)]
	}

	@Check
	def validateBlockStatementType(Statement it){
		val feature = switch(it){
			SymbolDefinition: MdlPackage.eINSTANCE.symbolDefinition_Name
			AnonymousListStatement: MdlPackage.eINSTANCE.anonymousListStatement_List 
			default: null
		}
		if(feature != null)
			validateExpectedStatementType[blk| error("block '" + blk + "' does not permit statements of this type",
						feature, BLOCK_INVALID_STATEMENT_TYPE, blk)]
	}

	

	@Check
	def validateBlockArgument(BlockArgument blkArg){
		if (blkArg.eContainer instanceof BlockArguments && (blkArg.eContainer.eContainer instanceof MclObject || blkArg.eContainer.eContainer instanceof BlockStatement)) {
			switch (blkArg) {
				ForwardDeclaration case !blkArg.isValidObjVarDecl: {
					error("unrecognised variable declaration type '" + blkArg.declType + "'",
						MdlPackage.eINSTANCE.forwardDeclaration_DeclType, UNKNOWN_BLOCK_ARG_DECL, blkArg.declType)
				}
				ValuePair case !blkArg.isValidBlkArgProperty: {
					error("unrecognised property '" + blkArg.argumentName + "'",
						MdlPackage.eINSTANCE.valuePair_ArgumentName, UNKNOWN_BLOCK_ARG_PROP, blkArg.argumentName)
				}
			}
		}
	}
	
	@Check
	def validateMdlObjectHasCorrectBlocks(MclObject it){
		// check if mandatory blocks missing
		unusedMandatoryBlocks.forEach[blk, mand| error("mandatory block '" + blk + "' is missing in mdlObj '" + name + "'",
					MdlPackage.eINSTANCE.mclObject_Blocks, MANDATORY_BLOCK_MISSING, blk) ]
		// 		[expectedType, actualType |error("Expected " + expectedType.typeName + " type, but was " + actualType.typeName + ".", feature, INCOMPATIBLE_TYPES, expectedType.typeName) ]
		validateBlocksCounts([blk, maxLimit| error("block '" + blk + "' is used more than is allowed. A maximum of " + maxLimit + " blocks are allowed",
					MdlPackage.eINSTANCE.mclObject_Blocks, BLOCK_COUNT_EXCEEDED, blk) ])
	}

}