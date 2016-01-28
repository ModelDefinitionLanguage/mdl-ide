package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdllib.mdlLib.SymbolDefinition
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.MdlUtils
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class ValueSelectorValidator extends AbstractMdlValidator {

	override register(EValidatorRegistrar registrar){}
	
	extension MdlUtils mu = new MdlUtils
	extension ConstantEvaluation ce = new ConstantEvaluation
	
	
	private def numValues(MappingExpression it, int value){
		var valCntr = 0
		for(mp : attList){
			val mapValue = mp.leftOperand.evaluateMathsExpression
			if(mapValue != null && mapValue.intValue == value) valCntr++
		}
		return valCntr
	}
	
	@Check
	def validateUniqueTestValues(MappingPair it){
		val mapExpr = EcoreUtil2.getContainerOfType(eContainer, MappingExpression)
		val mapValue = leftOperand.evaluateMathsExpression
		if(mapValue != null && mapExpr.numValues(mapValue.intValue) > 1){
			error("Test value '" + mapValue.intValue + "' cannot be used more than once in the same value selection expression.",
					MdlPackage.eINSTANCE.mappingPair_LeftOperand,
					MdlValidator::DUPLICATE_SELECTION_TEST_VALUE, mapValue.intValue.toString)
		}
	}
	
	
	private def numRefs(MappingExpression it, SymbolDefinition value){
		var valCntr = 0
		for(mp : attList){
			val mapValue = mp.rightOperand.singleSymbolRef
			if(mapValue != null && mapValue == value) valCntr++
		}
		return valCntr
	}
	
	@Check
	def validateUniqueSelectionRef(MappingPair it){
		val mapExpr = EcoreUtil2.getContainerOfType(eContainer, MappingExpression)
		val mapValue = rightOperand.singleSymbolRef
		if(mapValue != null && mapExpr.numRefs(mapValue) > 1){
			error("Selection variable '" + mapValue.name + "' cannot be used more than once in the same value selection expression.",
					MdlPackage.eINSTANCE.mappingPair_LeftOperand,
					MdlValidator::DUPLICATE_SELECTION_REF, mapValue.name)
		}
	}
	
	private def numValues(CategoricalDefinitionExpr it, int value){
		var valCntr = 0
		for(mp : categories){
			if(mp.mappedTo != null && mp.mappedTo.singleSymbolRef == null){
				val mapValue = mp.mappedTo.evaluateMathsExpression
				if(mapValue != null && mapValue.intValue == value) valCntr++
			}
		}
		return valCntr
	}
	
	private def numRefs(CategoricalDefinitionExpr it, SymbolDefinition value){
		var valCntr = 0
		for(mp : categories){
			if(mp.mappedTo != null && mp.mappedTo.singleSymbolRef != null){
				val mapValue = mp.mappedTo.singleSymbolRef
				if(mapValue != null && mapValue == value) valCntr++
			}
		}
		return valCntr
	}
	
	@Check
	def validateUniqueTestValues(CategoryValueDefinition it){
		if(mappedTo != null){
			val mapExpr = EcoreUtil2.getContainerOfType(eContainer, CategoricalDefinitionExpr)
			val ref = mappedTo.singleSymbolRef
			if(ref == null){
				val mapValue = mappedTo.evaluateMathsExpression
				if(mapValue != null && mapExpr.numValues(mapValue.intValue) > 1){
					error("Test value '" + mapValue.intValue + "' cannot be used more than once in the same category definition expression.",
						MdlPackage.eINSTANCE.categoryValueDefinition_MappedTo,
						MdlValidator::DUPLICATE_SELECTION_TEST_VALUE, mapValue.intValue.toString)
				}
			}
			else if(mapExpr.numRefs(ref) > 1){
				error("Test ref '" + ref.name + "' cannot be used more than once in the same category definition expression.",
					MdlPackage.eINSTANCE.categoryValueDefinition_MappedTo,
					MdlValidator::DUPLICATE_SELECTION_TEST_VALUE, ref.name)
			}
		}
	}
	
	
}