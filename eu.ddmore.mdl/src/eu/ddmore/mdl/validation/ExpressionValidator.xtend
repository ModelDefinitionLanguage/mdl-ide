package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.IfExpression
import eu.ddmore.mdl.mdl.MatrixLiteral
import eu.ddmore.mdl.mdl.MatrixRow
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.RealLiteral
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.mdl.PiecewiseExpression

class ExpressionValidator extends AbstractDeclarativeValidator{

	override register(EValidatorRegistrar registrar){}
	
	@Check
	def validateIfElseWellFormed(IfExpression e){
		if(e.elseClause == null){
			if(e.ifelseClause.size < 2){
				error("More than one condition or an else clause is required in this expression.",
					MdlPackage.eINSTANCE.ifExpression_IfelseClause, MdlValidator::UNDER_DEFINED_IF_ELSE, '')
			}
		} 
	}
	
	@Check
	def validatePiecewiseWellFormed(PiecewiseExpression e){
		if(e.otherwise == null){
			if(e.when.size < 2){
				error("More than one condition or an otherwise clause is required in this piecewise expression.",
					MdlPackage.eINSTANCE.piecewiseExpression_When, MdlValidator::UNDER_DEFINED_IF_ELSE, '')
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
	
	@Check
	def validateMatrixUniform(MatrixLiteral it){
		var rowSize = -1
		for(r : rows){
			if(r instanceof MatrixRow){
				if(rowSize != -1 && rowSize != r.cells.size)
					error("The rows in this matrix must be the same size.",
						MdlPackage.eINSTANCE.matrixLiteral_Rows, MdlValidator::MATRIX_INCONSISTENT_ROW_SIZE, '')
				else
					rowSize = r.cells.size 
			}
		}
	}
	
	
}