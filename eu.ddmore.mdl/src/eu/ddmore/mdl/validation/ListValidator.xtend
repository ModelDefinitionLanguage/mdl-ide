package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.MclTypeProvider
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

// validates attributes in lists, functions and properties
class ListValidator extends AbstractMdlValidator {
	
	override register(EValidatorRegistrar registrar){}
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	extension MclTypeProvider mtp = new MclTypeProvider
	
	static val MappingToColumn = #{
		ListDefinitionProvider::AMT_USE_VALUE -> ListDefinitionProvider::CMT_COL_TYPE,
		ListDefinitionProvider::OBS_USE_VALUE -> ListDefinitionProvider::DVID_COL_TYPE
	} 
	
	@Check
	def validateInReferenceCorrect(MappingPair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if(attList != null){
			// check use type
			val useVal = attList.getAttributeEnumValue(ListDefinitionProvider::USE_ATT)
			if(useVal != null){
				val expectedSrcType = MappingToColumn.get(useVal) 
				val srcColType = srcColumn?.ref?.typeFor
				if(expectedSrcType != null && srcColType != null && !expectedSrcType.isCompatible(srcColType)){
					error("Expected source column of type '" + expectedSrcType.typeName + "', but was '" + srcColType.typeName + "'.",
						MdlPackage.eINSTANCE.mappingPair_SrcColumn, MdlValidator::INCOMPATIBLE_TYPES, srcColType.typeName)
				}
			}
		}
	}
	
	
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