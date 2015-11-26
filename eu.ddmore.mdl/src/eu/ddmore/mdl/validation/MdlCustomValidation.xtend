package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.impl.ListDefinitionImpl
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.PrimitiveType
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.utils.MclUtils

class MdlCustomValidation extends AbstractMdlValidator {

	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension MclTypeProvider mtp = new MclTypeProvider
	extension MclUtils mu = new MclUtils 

	override register(EValidatorRegistrar registrar){}

	@Check
	def validateTransforms(TransformedDefinition it){
		if(!isValidTransform){
			error("'" + transform + "' cannot be used as a transformation function on the LHS of an equation",
				MdlPackage::eINSTANCE.transformedDefinition_Transform, MdlValidator::INVALID_LHS_FUNC, transform)
		}
		if(!isLhsTransformPermitted || !isValidRhsTransformPermitted){
			error("Use of a transformation function on the LHS of the equation is not permitted in this context",
				MdlPackage::eINSTANCE.transformedDefinition_Transform, MdlValidator::INVALID_LHS_FUNC, transform)
		}
	}
	
	@Check
	def validateIndividualParameterDefinitions(EnumPair it){
		val stmt = EcoreUtil2.getContainerOfType(eContainer, Statement)
		switch(stmt){
			TransformedDefinition:
				checkConsistentLinearTransformation(stmt, it,
					[lhs, rhs| error("transformation used on LHS ('" + lhs + "') must match the RHS ('" + rhs + "')",
						MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator::INVALID_LHS_FUNC, lhs)
					]
				)
			EquationDefinition:
				checkNonTransformedIndiv(stmt, it,
					[error("no transformation used on the LHS, so cannot use on the RHS of the equation",
						MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator::INVALID_LHS_FUNC, "")
					]
				)
		}
	}

	@Check
	def validateCategoryRelations(RelationalExpression it){
		val leftType = leftOperand?.typeFor
		val rightType = rightOperand?.typeFor
		if((leftType != null && leftType.theType == PrimitiveType.Enum)  || 
			(rightType != null && rightType.theType == PrimitiveType.Enum)){
			error("Cannot use inequality operators with categorical types", MdlPackage::eINSTANCE.relationalExpression_Feature,
				MdlValidator::INVALID_ENUM_RELATION_OPERATOR, feature)
		}
	}

	def checkNonTransformedIndiv(EquationDefinition transDefn, EnumPair transArg, () => void incompatibleTransforms) {
		val call = EcoreUtil2.getContainerOfType(transArg.eContainer, BuiltinFunctionCall)
		if(call != null && transDefn != null && transArg.argumentName == 'trans'){
			val transExpr = transArg.expression
			if(transExpr != null && isValidTransformFunction(transExpr.enumValue) && transOnBothFuncs.contains(call.func)){
				incompatibleTransforms.apply
			}
		}
	}
	
	def isLhsTransformPermitted(TransformedDefinition it) {
		val expr = expression
		switch(expr){
			BuiltinFunctionCall case(transOnBothFuncs.contains(expr.func)),
			BuiltinFunctionCall case(transOnLHSFuncs.contains(expr.func)):
				true
			default:
				false
		}
	}

	static val transOnBothFuncs = #{ 'linear'  }
	static val transOnLHSFuncs = #{ 'general', 'combinedError1', 'combinedError2', 'additiveError', 'proportionalError' }
	
	def isValidRhsTransformPermitted(TransformedDefinition it) {
		val expr = expression
		switch(expr){
			BuiltinFunctionCall case(transOnBothFuncs.contains(expr.func)):{
				val transExpr = expr.getArgumentEnumValue('trans')
				if(transExpr != null) transExpr.isValidTransformFunction else false
			}
				
			BuiltinFunctionCall case(transOnLHSFuncs.contains(expr.func)):
				true
			default:
				false
		}
	}
	
	def checkConsistentLinearTransformation(TransformedDefinition transDefn, EnumPair transArg, (String, String) => void incompatibleTransforms) {
		val call = EcoreUtil2.getContainerOfType(transArg.eContainer, BuiltinFunctionCall)
		if(call != null && transDefn != null && transArg.argumentName == 'trans'){
			val transExpr = transArg.expression
			if(transExpr != null && transOnBothFuncs.contains(call.func)){
				val rhsTrans = transExpr.enumValue
				if(transDefn.transform != rhsTrans){
					incompatibleTransforms.apply(transDefn.transform, rhsTrans)
				}
			}
		}
	}

	static val UseDeps = #{
		'rate' -> #{'amt'},
		'ii' -> #{'ss', 'amt'},
		'ss' -> #{ 'amt' },
		'addl' -> #{ 'amt', 'ii' }
	}


	@Check
	def validateDataUseHasDependecies(ListDefinitionImpl it){
		val block = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(block.identifier == BlockDefinitionProvider::DIV_BLK_NAME){
			val enumVal = list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT)
			if(enumVal != null && UseDeps.containsKey(enumVal)){
				for(depUse : UseDeps.get(enumVal)){
					if(!block.hasListWithUse(depUse)){
						error("A data column of use '" + depUse + "' is required by this column definition with 'use is " + enumVal + "'.",
							MdlPackage::eINSTANCE.listDefinition_List, MdlValidator::DEPENDENT_USE_MISSING, enumVal)
					}
				}
			}
		}
	}
	
	
	def hasListWithUse(BlockStatement it, String useValue){
		val bdy = body
		if(bdy instanceof BlockStatementBody){
			for(stmt : bdy.statements){
				if(stmt instanceof ListDefinition){
					val useVal = stmt.list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT)
					if(useVal == useValue) return true
				}
			}
		}
		else false
	}
	
	
	@Check
	// check that a covariate is used in the fixed eff sublist
	def validateCovariateFixedEffect(ValuePair it){
		if(attributeName == SublistDefinitionProvider::COV_ATT){
			val expr = expression
			if(expr instanceof SymbolReference){
				val subList = EcoreUtil2.getContainerOfType(eContainer, SubListExpression)
				if(subList != null && subList.typeFor.isCompatible(SublistDefinitionProvider::getSublist(SublistDefinitionProvider::FIX_EFF_SUBLIST))){
					// now check reference variable belongs to covariates block
					val refBlk = EcoreUtil2.getContainerOfType(expr.ref.eContainer, BlockStatement)
					if(refBlk != null && refBlk.identifier != BlockDefinitionProvider::COVARIATE_BLK_NAME){
						// ref cov is not in cov block so assume it not a covariate
						error("Attribute '" + attributeName + "' expects a reference to a covariate. '" + expr.ref.name + "' is not a covariate.",
							MdlPackage::eINSTANCE.valuePair_Expression,
							MdlValidator::INCOMPATIBLE_VARIABLE_REF, expr.ref.name)
					} 
				}
			}
		}
	}
	
	
	val UniqueUseValue = #{
		ListDefinitionProvider::AMT_USE_VALUE,
		ListDefinitionProvider::CMT_USE_VALUE,
		ListDefinitionProvider::DVID_USE_VALUE,
		ListDefinitionProvider::OBS_USE_VALUE,
		ListDefinitionProvider::ID_USE_VALUE,
		ListDefinitionProvider::ADDL_USE_VALUE,
		ListDefinitionProvider::II_USE_VALUE,
		ListDefinitionProvider::SS_USE_VALUE,
		ListDefinitionProvider::IDV_USE_VALUE,
		ListDefinitionProvider::RATE_USE_VALUE,
		ListDefinitionProvider::MDV_USE_VALUE
	}
	
	
	def isDuplicateUse(BlockStatement owningBlock, AttributeList refList, ValuePair queryUse){
		for (stmt : owningBlock.nonBlockStatements){
			if(stmt instanceof ListDefinition){
				val matchExpr = stmt.list.getAttributeExpression(ListDefinitionProvider::USE_ATT)
				val enumVal = queryUse.expression.enumValue
				if(matchExpr != queryUse.expression &&  enumVal != null && enumVal == matchExpr.enumValue){
					return true
				} 
			}
		}
		false
	}
	
	@Check
	// check if certain uses are already used in data definition
	def validateUseValueNotDuplicated(ValuePair it){
		val owningBlock = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val owningList = EcoreUtil2.getContainerOfType(eContainer, ListDefinition)
		if(owningBlock != null && owningBlock.identifier == BlockDefinitionProvider::DIV_BLK_NAME &&
			owningList != null && attributeName == ListDefinitionProvider::USE_ATT){
			val enumVal = owningList.list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT)
			if(UniqueUseValue.contains(enumVal)){
				if(owningBlock.isDuplicateUse(owningList.list, it)){
					error("Only one column definition can have a 'use' attribute set to '" + enumVal + "'.",
						MdlPackage::eINSTANCE.valuePair_Expression,
						MdlValidator::DUPLICATE_UNIQUE_USE_VALUE, enumVal)
				}
			}
		}
	}
}