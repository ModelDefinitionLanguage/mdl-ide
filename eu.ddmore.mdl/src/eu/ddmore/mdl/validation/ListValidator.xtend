package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.provider.PropertyDefinitionProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.utils.MdlUtils

// validates attributes in lists, functions and properties
class ListValidator extends AbstractMdlValidator {
	
	override register(EValidatorRegistrar registrar){}
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	extension TypeSystemProvider mtp = new TypeSystemProvider
	extension MdlUtils mu = new MdlUtils
	
	static val MappingToColumn = #{
		ListDefinitionTable::AMT_USE_VALUE -> ListDefinitionTable::CMT_COL_TYPE,
		ListDefinitionTable::OBS_USE_VALUE -> ListDefinitionTable::DVID_COL_TYPE
	} 
	
	@Check
	def validateInReferenceCorrect(MappingPair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if(attList != null){
			// check use type
			val useVal = attList.getAttributeEnumValue(ListDefinitionTable::USE_ATT)
			if(useVal != null){
				val expectedSrcType = MappingToColumn.get(useVal) 
				val srcColType = srcColumn?.ref?.typeFor
//				if(expectedSrcType != null && srcColType != null && !expectedSrcType.isCompatible(srcColType)){
//					error("Expected source column of type '" + expectedSrcType.typeName + "', but was '" + srcColType.typeName + "'.",
//						MdlPackage.eINSTANCE.mappingPair_SrcColumn, MdlValidator::INCOMPATIBLE_TYPES, srcColType.typeName)
//				}
				if(expectedSrcType != null && srcColType != null && !expectedSrcType.isArgumentCompatible(srcColType)){
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
		for(list : getAttributeLists){
			if(list.isAnonymousListExpected){
				error("a list with this key cannot have a name in this context",
					MdlPackage.eINSTANCE.listDefinition_List, MdlValidator::LIST_NOT_ANONYMOUS, "")
			}
		}
	}

	@Check
	def validateCategoryDefinitionWellFormed(EnumPair parentAt){
		if(parentAt != null)
			checkCategoryDefinitionWellFormed(parentAt,
				[error("Unexpected category definition.", 
					MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator::INVALID_CATEGORY_DEFINITION, "") ],
				[error("Category definition is missing.", 
					MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator::INVALID_CATEGORY_DEFINITION, "") ]
			)
	}
		
	def checkCategoryDefinitionWellFormed(EnumPair ep, () => void unexpectedCatDefnErrorLambda, () => void missingCatErrorLambda){
		val attList = EcoreUtil2.getContainerOfType(ep.eContainer, AttributeList)
		if(attList != null){
			val listDefn = attList.matchingListDefn
			val attDefn = listDefn?.getAttributeDefinition(ep.argumentName)
			if(ep.expression instanceof EnumExpression && attDefn != null){
				val mappingExpr = ep.expression as EnumExpression
				if(attDefn.isCatMappingPossible && mappingExpr.catDefn == null){
					missingCatErrorLambda.apply
				}
				else if(!attDefn.isCatMappingPossible && mappingExpr.catDefn != null){
					unexpectedCatDefnErrorLambda.apply
				}
			}
		}
	}
	

}