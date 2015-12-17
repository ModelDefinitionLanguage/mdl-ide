package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.provider.ListDefinitionProvider
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class StatementValidator extends AbstractDeclarativeValidator{

	override register(EValidatorRegistrar registrar){}

	extension ListDefinitionProvider listHelper = new ListDefinitionProvider
	
	@Check
	def validateCatDefinitionUsedCorrectlyInStatement(CategoryValueDefinition e){
		if(EcoreUtil2.getContainerOfType(e, EnumerationDefinition) != null && e.mappedTo != null){
			error("Cannot use category mappings in a statement.",
					MdlPackage.eINSTANCE.categoryValueDefinition_MappedTo, MdlValidator::INCORRECT_STATEMENT_CONTEXT, e.name)
		}
		else if(e.isMappingMandatory && e.mappedTo == null){
			error("A category definition must have a mapping in this context.",
					MdlPackage.eINSTANCE.categoryValueDefinition_Name, MdlValidator::INCORRECT_LIST_CONTEXT, e.name)
		}
		else if(e.isMappingForbidden && e.mappedTo != null){
			error("A category definition cannot have a mapping in this context.",
					MdlPackage.eINSTANCE.categoryValueDefinition_Name, MdlValidator::INCORRECT_LIST_CONTEXT, e.name)
		}
	}
	
//	@Check
//	def validateNoVectorDefinitions(EquationDefinition it){
//		if(isVector){
//			error("Vector symbol definitions are not supported in this version of the language.",
//					MdlPackage.eINSTANCE.equationDefinition_Vector, eu.ddmore.mdl.validation.MdlValidator.UNUSED_FEATURE, name)
//		}
//	}
	
	
	
}