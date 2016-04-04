package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.provider.PropertyDefinitionProvider
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class PropertyValidator extends AbstractDeclarativeValidator{

	override register(EValidatorRegistrar registrar){}

	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	
	@Check
	def validateProperties(BlockStatement it){
		if(!blkId.isFreeProps){
			unusedMandatoryProperties.forEach[name| error("mandatory property '" + name + "' is missing in block.",
					MdlPackage.eINSTANCE.blockStatement_Body,MdlValidator::MANDATORY_PROP_MISSING, name) ]
		}
	}

	
	
	
}