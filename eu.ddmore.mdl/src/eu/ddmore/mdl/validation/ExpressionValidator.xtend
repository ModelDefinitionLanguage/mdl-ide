package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.WhenExpression
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class ExpressionValidator extends AbstractDeclarativeValidator{

	override register(EValidatorRegistrar registrar){}
	
	@Check
	def validateIfElseWellFormed(WhenExpression e){
		if(e.other == null){
			if(e.when.size < 2){
				error("More than one condition or an else statement is required in this expression.",
					MdlPackage.eINSTANCE.whenExpression_When, MdlValidator::UNDER_DEFINED_IF_ELSE, '')
			}
		} 
	}
	
	@Check
	def validateRealInRange(RealLiteral it){
		if(Double.isInfinite(value) || Double.isNaN(value)){
			error("This real number is too large or small for MDL.",
				MdlPackage.eINSTANCE.realLiteral_Value, MdlValidator::NUMBER_BEYOND_PRECISION_RANGE, '')
		}
	}
	
	
}