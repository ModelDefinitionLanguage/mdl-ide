package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.ValuePair
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

// validates attributes in lists, functions and properties
class ListValidator extends AbstractMdlValidator {
	
	override register(EValidatorRegistrar registrar){}
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	
//	@Check
//	def validateInReferenceCorrect(MappingExpression it){
//		
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
	def validateDuplicateAttributes(ValuePair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if(attList.isAttributeDuplicated(it)){
			error("List attribute '" + argumentName + "' is used more than once.",
				MdlPackage.eINSTANCE.valuePair_ArgumentName, MdlValidator::DUPLICATE_ATTRIBUTE_NAME, argumentName
			)
		}
	}
	
	@Check
	def validateAttributeList(AttributeList it){
		if(isKeyAttributeDefined){
			unusedMandatoryAttributes.forEach[name| error("mandatory attribute '" + name + "' is missing in list.",
				MdlPackage.eINSTANCE.attributeList_Attributes, MdlValidator::MANDATORY_LIST_ATT_MISSING, name) ]
		}		
		else{
			error("mandatory key attribute is missing in list.",
				MdlPackage.eINSTANCE.attributeList_Attributes, MdlValidator::MANDATORY_LIST_KEY_ATT_MISSING, "")
		}
	}

	@Check
	def validateListAnonymisation(AnonymousListStatement it){
		if(list.isNamedListExpected){
			error("a list with this key cannot be anonymous in this context",
				MdlPackage.eINSTANCE.anonymousListStatement_List, MdlValidator::LIST_NOT_NAMED, "")
		}
	}

	@Check
	def validateListAnonymisation(ListDefinition it){
		if(list.isAnonymousListExpected){
			error("a list with this key cannot have a name in this context",
				MdlPackage.eINSTANCE.listDefinition_List, MdlValidator::LIST_NOT_ANONYMOUS, "")
		}
	}

}