/*
 * generated by Xtext
 */
package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockArgument
import eu.ddmore.mdl.mdl.BlockArguments
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CatValRefMapping
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ForwardDeclaration
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenClause
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import eu.ddmore.mdl.utils.MclUtils
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.BlockTextBody

//import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class MdlValidator extends AbstractMdlValidator {
	public val static MDLOBJ = 'mdlObj'
	public val static DATAOBJ = 'dataObj'
	public val static TASKOBJ = 'taskObj'
	public val static PARAMOBJ = 'parObj'
	public val static MOGOBJ = 'mogObj'
	public val static DESIGNOBJ = 'desObj'

	extension BlockArgumentDefinitionProvider movh = new BlockArgumentDefinitionProvider
	extension BlockDefinitionProvider blokHelper = new BlockDefinitionProvider
	extension ListDefinitionProvider listHelper = new ListDefinitionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	extension BuiltinFunctionProvider funcHelper = new BuiltinFunctionProvider
	extension MclTypeProvider typeProvider = new MclTypeProvider
	extension MclUtils mclUtils = new MclUtils

	public static val UNRECOGNISED_OBJECT_TYPE = "eu.ddmore.mdl.validation.UnrecognisedObjectType"

	// Block arguments validation
	public static val UNKNOWN_BLOCK_ARG_DECL = "eu.ddmore.mdl.validation.UnknownBlockArgDecl"
	public static val UNKNOWN_BLOCK_ARG_PROP = "eu.ddmore.mdl.validation.UnknownBlockArgProp"
	public static val MANDATORY_BLOCK_ARG_MISSING = "eu.ddmore.mdl.validation.MandatoryBlockArgMissing"
	public static val MANDATORY_BLOCK_PROP_MISSING = "eu.ddmore.mdl.validation.MandatoryBlockPropMissing"
	
	// List attribute validation
	public static val UNRECOGNIZED_PROPERTY_ATT  = "eu.ddmore.mdl.validation.UnrecognisedProperty"
	public static val MANDATORY_PROP_MISSING = "eu.ddmore.mdl.validation.MandatoryProputeMissing"
	public static val UNRECOGNIZED_LIST_ATT = "eu.ddmore.mdl.validation.UnrecognisedAttribute"
	public static val MANDATORY_LIST_ATT_MISSING = "eu.ddmore.mdl.validation.MandatoryAttributeMissing"
	public static val MANDATORY_LIST_KEY_ATT_MISSING = "eu.ddmore.mdl.validation.MandatoryKeyAttributeMissing"
	public static val LIST_NOT_ANONYMOUS = "eu.ddmore.mdl.validation.ListNotAnonymous"
	public static val LIST_NOT_NAMED = "eu.ddmore.mdl.validation.ListNotNamed"

	// Builtin Function validation
	public static val UNRECOGNIZED_FUNCTION_NAME = "eu.ddmore.mdl.validation.function.named.UnrecognisedFunctionName"
	public static val INCORRECT_NUM_FUNC_ARGS = "eu.ddmore.mdl.validation.function.IncorrectNumArgs"
	public static val MULTIPLE_IDENTICAL_FUNC_ARG = "eu.ddmore.mdl.validation.function.named.MultipleArgs"
	public static val UNRECOGNIZED_FUNCTION_ARGUMENT_NAME = "eu.ddmore.mdl.validation.function.named.UnrecognisedArgName"
	public static val MANDATORY_NAMED_FUNC_ARG_MISSING = "eu.ddmore.mdl.validation.function.named.MandatoryArgMissing"
	

	// Block validation
	public static val UNKNOWN_BLOCK = "eu.ddmore.mdl.validation.UnknownBlock"
	public static val WRONG_SUBBLOCK = "eu.ddmore.mdl.validation.WrongSubBlock"
	public static val WRONG_PARENT_BLOCK = "eu.ddmore.mdl.validation.WrongParentBlock"
	public static val MANDATORY_BLOCK_MISSING = "eu.ddmore.mdl.validation.MandatoryBlockMissing"
	public static val BLOCK_COUNT_EXCEEDED = "eu.ddmore.mdl.validation.BlockCountExceeded"
	public static val BLOCK_INCORRECT_STATEMENT_COUNT = "eu.ddmore.mdl.validation.BlockIncorrrectStatementCount"
	public static val BLOCK_INVALID_STATEMENT_TYPE = "eu.ddmore.mdl.validation.BlockInvalidStatementType"
	public static val BLOCK_WRONG_BODY_TYPE = "eu.ddmore.mdl.validation.WrongBodyType"

	// Validation of syntactic structures
	public static val INCORRECT_STATEMENT_CONTEXT = "eu.ddmore.mdl.validation.IncorrectStatementContext"
	public static val INCORRECT_LIST_CONTEXT = "eu.ddmore.mdl.validation.IncorrectListContext"
	public static val UNDER_DEFINED_IF_ELSE = "eu.ddmore.mdl.validation.UnderDefinedIfElse"
	public static val INVALID_CATEGORY_DEFINITION = "eu.ddmore.mdl.validation.IncompleteCategories"

	// Type validation
	public static val INCOMPATIBLE_TYPES = "eu.ddmore.mdl.validation.IncompatibleTypes"

	// MOG validation
	public static val MODEL_DATA_MISMATCH = "eu.ddmore.mdl.validation.mog.mismatch_mod_data"

	private static val VALID_OBJECT_TYPES = #[ MDLOBJ, PARAMOBJ, TASKOBJ, DATAOBJ, MOGOBJ, DESIGNOBJ ]

	def void setFoo(){}

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
	def validateMdlObjectHasCorrectBlocks(MclObject it){
		// check if mandatory blocks missing
		unusedMandatoryBlocks.forEach[blk, mand| error("mandatory block '" + blk + "' is missing in mdlObj '" + name + "'",
					MdlPackage.eINSTANCE.mclObject_Blocks, MANDATORY_BLOCK_MISSING, blk) ]
		// 		[expectedType, actualType |error("Expected " + expectedType.typeName + " type, but was " + actualType.typeName + ".", feature, INCOMPATIBLE_TYPES, expectedType.typeName) ]
		validateBlocksCounts([blk, maxLimit| error("block '" + blk + "' is used more than is allowed. A maximum of " + maxLimit + " blocks are allowed",
					MdlPackage.eINSTANCE.mclObject_Blocks, BLOCK_COUNT_EXCEEDED, blk) ])
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
	def validateAttributeList(AttributeList it){
		if(isKeyAttributeDefined){
			unusedMandatoryAttributes.forEach[name| error("mandatory attribute '" + name + "' is missing in list.",
				MdlPackage.eINSTANCE.attributeList_Attributes, MANDATORY_LIST_ATT_MISSING, name) ]
		}		
		else{
			error("mandatory key attribute is missing in list.",
				MdlPackage.eINSTANCE.attributeList_Attributes, MANDATORY_LIST_KEY_ATT_MISSING, "")
		}
	}

	@Check
	def validateListAnonymisation(AnonymousListStatement it){
		if(list.isNamedListExpected){
			error("a list with this key cannot be anonymous in this context",
				MdlPackage.eINSTANCE.anonymousListStatement_List, LIST_NOT_NAMED, "")
		}
	}

	@Check
	def validateListAnonymisation(ListDefinition it){
		if(list.isAnonymousListExpected){
			error("a list with this key cannot have a name in this context",
				MdlPackage.eINSTANCE.listDefinition_List, LIST_NOT_ANONYMOUS, "")
		}
	}

	@Check
	def validateProperties(BlockStatement it){
		unusedMandatoryProperties.forEach[name| error("mandatory property '" + name + "' is missing in block.",
				MdlPackage.eINSTANCE.blockStatement_Body, MANDATORY_PROP_MISSING, name) ]
	}

//	@Check
//	def validateAttributeList(AttributeList it){
//		if(isKeyAttributeDefined){
//			unusedMandatoryAttributes.forEach[name| error("mandatory attribute '" + name + "' is missing in list.",
//				MdlPackage.eINSTANCE.attributeList_Attributes, MANDATORY_LIST_ATT_MISSING, name) ]
//		}		
//		else{
//			error("mandatory key attribute is missing in list.",
//				MdlPackage.eINSTANCE.attributeList_Attributes, MANDATORY_LIST_KEY_ATT_MISSING, "")
//		}
//	}

	@Check
	def validateAttribute(ValuePair it){
		val parent = eContainer
		switch(parent){
			AttributeList:{
				if(!attributeRecognised){
					error("attribute '" + attributeName + "' is not recognised in this context.",
							MdlPackage.eINSTANCE.valuePair_ArgumentName, MdlValidator.UNRECOGNIZED_LIST_ATT, attributeName)
				}
			}
			PropertyStatement:{
				if(!isPropertyKnown){
					error("property '" + attributeName + "' is not recognised in this context.",
							MdlPackage.eINSTANCE.valuePair_ArgumentName, MdlValidator.UNRECOGNIZED_PROPERTY_ATT, attributeName)
				}
			}
			
		}
	}

	@Check
	def validateFunctionCall(BuiltinFunctionCall it){
		if(argList == null || argList instanceof UnnamedFuncArguments){
			checkUnnamedFunctionDefn(
				[fName| error("Simple function '" + fName + "' is not recognised.",
					MdlPackage.eINSTANCE.builtinFunctionCall_Func, UNRECOGNIZED_FUNCTION_NAME, fName)],
					 [fName, eArgNum | error("Function '" + fName + "' has the wrong number of arguments. Expected " + eArgNum + ".",
					MdlPackage.eINSTANCE.builtinFunctionCall_ArgList, INCORRECT_NUM_FUNC_ARGS, fName)]
					)
		}
		else{
			checkNamedFunctionDefn(
				[fName| error("Named argument function '" + fName + "' is not recognised.",
					MdlPackage.eINSTANCE.builtinFunctionCall_Func, UNRECOGNIZED_FUNCTION_NAME, fName)]
					)
		}
	}

	@Check
	def validateFunctionArgument(ValuePair it){
		if(eContainer instanceof NamedFuncArguments){
			checkNamedArguments(
				[fName| error("Unrecognised argument '" + argumentName + "'.",
				MdlPackage.eINSTANCE.valuePair_ArgumentName, UNRECOGNIZED_FUNCTION_ARGUMENT_NAME, fName)],
				[fName| error("Function argument '" + argumentName + "' occurs more than once.",
				MdlPackage.eINSTANCE.valuePair_ArgumentName, MULTIPLE_IDENTICAL_FUNC_ARG, fName)]
			)
		}
	}

	@Check
	def validateNamedFunctionArguments(NamedFuncArguments it){
		missingMandatoryArgumentNames.forEach[arg, mand| error("mandatory argument '" + arg + "' is missing.",
					MdlPackage.eINSTANCE.namedFuncArguments_Arguments, MANDATORY_NAMED_FUNC_ARG_MISSING, arg) ]
	}

	// Type handling	
	private def (TypeInfo, TypeInfo) => void typeError(EStructuralFeature feature){ 
		[expectedType, actualType |error("Expected " + expectedType.typeName + " type, but was " + actualType.typeName + ".", feature, INCOMPATIBLE_TYPES, expectedType.typeName) ]
	}
	
			
	@Check
	def validateCompatibleTypes(AndExpression e){
		checkBoolOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.andExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.andExpression_RightOperand))
	}
	
	@Check
	def validateCompatibleTypes(OrExpression e){
		checkBoolOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.orExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.orExpression_RightOperand))
	}
	
	@Check
	def validateCompatibleTypes(EqualityExpression e){
		checkRelationalOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.equalityExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.equalityExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(RelationalExpression e){
		checkRelationalOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.relationalExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.relationalExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(AdditiveExpression e){
		checkMathsOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.additiveExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.additiveExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(MultiplicativeExpression e){
		checkMathsOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.multiplicativeExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.multiplicativeExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(UnaryExpression e){
		checkUnaryOp(e.feature, e.operand, typeError(MdlPackage::eINSTANCE.unaryExpression_Operand))
	}
		
	@Check
	def validateCompatibleTypes(WhenClause e){
		checkExpectedBoolean(e.cond, typeError(MdlPackage::eINSTANCE.ifExprPart_Cond))
		checkExpectedReal(e.value, typeError(MdlPackage::eINSTANCE.ifExprPart_Value))
	}
		
	@Check
	def validateCompatibleTypes(MappingPair e){
		checkAsOperator(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.mappingPair_LeftOperand),
			typeError(MdlPackage::eINSTANCE.mappingPair_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(CatValRefMapping e){
		var parentAt = EcoreUtil2.getContainerOfType(e, EnumPair)
		if(parentAt != null)
			checkWhenOperator(parentAt, e.catRef, e.mappedTo, typeError(MdlPackage::eINSTANCE.catValRefMapping_CatRef),
				typeError(MdlPackage::eINSTANCE.catValRefMapping_MappedTo)
			)
	}
		
	@Check
	def validateCompatibleTypes(CategoryValueDefinition e){
		var parentAt = EcoreUtil2.getContainerOfType(e, EnumPair)
		if(parentAt != null)
			checkWhenOperator(parentAt, e, typeError(MdlPackage::eINSTANCE.categoryValueDefinition_Name),
				typeError(MdlPackage::eINSTANCE.categoryValueDefinition_MappedTo)
			)
	}
		
	@Check
	def validateCategoryDefinitionWellFormed(EnumPair parentAt){
		if(parentAt != null)
			checkCategoryDefinitionWellFormed(parentAt,
				[error("Unexpected category definition.", 
					MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator.INVALID_CATEGORY_DEFINITION, "") ],
				[error("Category definition is missing.", 
					MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator.INVALID_CATEGORY_DEFINITION, "") ]
			)
	}
		
	@Check
	def validateCompatibleTypes(EquationDefinition e){
		// only check if there is an RHS to check 
		if(e.expression != null)
			if(e.isVector)
				checkExpectedVector(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
			else
				checkExpectedReal(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
	}
		
	@Check
	def validateCompatibleTypes(RandomVariableDefinition e){
		checkExpectedPdf(e.distn, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
	}
		
	@Check
	def validateCompatibleTypes(TransformedDefinition e){
		checkExpectedRealTransform(e.transform, typeError(MdlPackage::eINSTANCE.transformedDefinition_Transform))
		checkExpectedReal(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
	}
		
	@Check
	def validateCompatibleVectorElement(VectorElement e){
		if(e.eContainer instanceof VectorLiteral){
			val vect = e.eContainer as VectorLiteral
			val vectType = vect.typeFor
			val exprType = e.typeFor
			if(!vectType.isCompatibleElement(exprType)){
				error("Element type '" + exprType.typeName + "' is incompatible with vector type '" + vectType.typeName + "'.",
					MdlPackage.eINSTANCE.vectorElement_Element, INCOMPATIBLE_TYPES, vectType.typeName)
			}			
		}
	}
	
	
	@Check
	def validUnnamedFuctionArgumentType(UnnamedArgument it){
		if(eContainer instanceof UnnamedFuncArguments){
			checkFunctionArgumentTyping([e, a|
				error("argument '" + (funcArgNum + 1) + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
						MdlPackage.eINSTANCE.unnamedArgument_Argument, INCOMPATIBLE_TYPES, a.typeName)
			])
		}
	}
	
	@Check
	def validateListAttributeTypes(ValuePair vp){
		val parent = vp.eContainer
		switch(parent){
			AttributeList:
				parent.checkAttributeTyping(vp, [e, a|
					error("attribute '" + vp.attributeName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, INCOMPATIBLE_TYPES, a.typeName)
				])
			NamedFuncArguments:
				vp.checkNamedFunctionArgumentTyping([e, a|
					error("argument '" + vp.attributeName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, INCOMPATIBLE_TYPES, a.typeName)
				])
			SubListExpression:
				parent.checkSublistAttributeTyping(vp, [e, a|
					error("attribute '" + vp.attributeName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, INCOMPATIBLE_TYPES, a.typeName)
				])
			PropertyStatement:
				parent.checkPropertyAttributeTyping(vp, [e, a|
					error("property '" + vp.attributeName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, INCOMPATIBLE_TYPES, a.typeName)
				])
		}
	}

	@Check
	def validateIfElseWellFormed(WhenExpression e){
		if(e.other == null){
			if(e.when.size < 2){
				error("More than one condition or an else statement is required in this expression.",
					MdlPackage.eINSTANCE.whenExpression_When, UNDER_DEFINED_IF_ELSE, '')
			}
		} 
	}
	
	@Check
	def validateCatDefinitionUsedCorrectlyInStatement(CategoryValueDefinition e){
		if(EcoreUtil2.getContainerOfType(e, EnumerationDefinition) != null && e.mappedTo != null){
			error("Cannot use category mappings in a statement.",
					MdlPackage.eINSTANCE.categoryValueDefinition_MappedTo, INCORRECT_STATEMENT_CONTEXT, e.name)
		}
		else if(EcoreUtil2.getContainerOfType(e, AttributeList) != null && e.mappedTo == null){
			error("A category definition in a list must have a mapping.",
					MdlPackage.eINSTANCE.categoryValueDefinition_Name, INCORRECT_LIST_CONTEXT, e.name)
		}
	}
	
	@Check
	def validateMog(MclObject mogObj){
		if(mogObj.isMogObject){
			val mogValidator = new MogValidator
			mogValidator.buildMog(mogObj)
			if(mogValidator.mdlObj != null && mogValidator.dataObj != null){
				// assume has a data obj and mdl obj
				mogValidator.validateCovariates[
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
				]
				mogValidator.validateObservations[
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
				]
				mogValidator.validateVariabilityLevels[
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
				]
				mogValidator.validateIndividualVariable[
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
				]
				mogValidator.validateDosing[
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
				]
			}
		}
	}
	
}
