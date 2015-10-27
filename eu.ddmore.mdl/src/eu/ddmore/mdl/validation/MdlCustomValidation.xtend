package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.TransformedDefinition
import org.eclipse.xtext.EcoreUtil2

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class MdlCustomValidation {

	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider 

	def checkNonTransformedIndiv(EquationDefinition transDefn, EnumPair transArg, () => void incompatibleTransforms) {
		val call = EcoreUtil2.getContainerOfType(transArg.eContainer, BuiltinFunctionCall)
		if(call != null && transDefn != null && transArg.argumentName == 'trans'){
			val transExpr = transArg.expression
			if(transExpr != null && isValidTransformFunction(transExpr.convertToString) && call.func == 'linear'){
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

	static val transOnBothFuncs = #{ 'linear', 'combinedError1', 'combinedError2', 'additiveError', 'proportionalError' }
	static val transOnLHSFuncs = #{ 'general' }
	
	def isValidRhsTransformPermitted(TransformedDefinition it) {
		val expr = expression
		switch(expr){
			BuiltinFunctionCall case(transOnBothFuncs.contains(expr.func)):{
				expr.getArgumentEnumValue('trans')?.isValidTransformFunction
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
				val rhsTrans = transExpr.convertToString
				if(transDefn.transform != rhsTrans){
					incompatibleTransforms.apply(transDefn.transform, rhsTrans)
				}
			}
		}
	}
	
}