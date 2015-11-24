package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.TransformedDefinition
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.EValidatorRegistrar
import org.eclipse.xtext.validation.Check
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.PrimitiveType

class MdlCustomValidation extends AbstractMdlValidator {

	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension MclTypeProvider mtp = new MclTypeProvider 

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
	
}