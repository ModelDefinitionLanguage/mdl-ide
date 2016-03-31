package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.ElseClause
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.IfExpression
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import java.util.Collections
import java.util.Map
import java.util.Set
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.type.TypeInfoClass

class UnsupportedFeaturesValidator extends AbstractMdlValidator  {
	
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ConstantEvaluation ce = new ConstantEvaluation
	extension TypeSystemProvider mtp = new TypeSystemProvider 
	extension BlockUtils bu = new BlockUtils
	
	override register(EValidatorRegistrar registrar){}
	
	public static val WRT_ATT = 'wrt'
	private static val PERMITTED_X0_VALUE = 0.0

	public static val FEATURE_NOT_SUPPORTED = "eu.ddmore.mdl.validation.unsupported.feature"


//	static val unsupportedObjects = #{
//		MdlValidator::DESIGNOBJ
//	}
	static val unsupportedObjects = Collections::emptySet
	
	static val Map<String, Set<String>> unsupportedAttributes = Collections::emptyMap
//	#{
//		BlockDefinitionProvider::OBS_BLK_NAME -> #{
//			ListDefinitionTable::TTE_EVENT_ATT,
//			ListDefinitionTable::TTE_MAX_EVENT_ATT									
//		}
//	}

	@Check
	//Check for unsupported object names
	def checkUnsupported(MclObject it){
		if(unsupportedObjects.contains(mdlObjType)){
			warning("Objects of type '" + mdlObjType + "' are not currently supported for execution in R.", 
					MdlPackage.eINSTANCE.mclObject_ObjId,
					FEATURE_NOT_SUPPORTED, name)
		}			
	}
	
	
	private def validateWrt(AttributeList it){
		val wrtExpr = getAttributeExpression(ListDefinitionTable::WRT_ATT)
		if(wrtExpr instanceof SymbolReference){
			val mdlObj = EcoreUtil2::getContainerOfType(eContainer, MclObject)
			val idvName = mdlObj?.mdlIdv?.name
			if(idvName != null && wrtExpr.ref.name != null && idvName != wrtExpr.ref.name){
				warning("Derivative variables with an independent variable different to the model's IDV cannot be executed in R.", 
						MdlPackage.eINSTANCE.valuePair_Expression,
						FEATURE_NOT_SUPPORTED, wrtExpr.ref.name)
			}
		}
	}
	
	private def validateInitTime(AttributeList it){
		val x0Expr = getAttributeExpression(ListDefinitionTable::DERIV_INIT_TIME_ATT)
		if(x0Expr != null){
			val x0Value = x0Expr.evaluateMathsExpression 
			if(x0Value != null && x0Value.compareTo(PERMITTED_X0_VALUE) != 0){
				warning("Derivative variables with a non-zero initial time cannot be executed in R.", 
						MdlPackage.eINSTANCE.valuePair_Expression,
						FEATURE_NOT_SUPPORTED, x0Value.toString)
			}
		}
	}
	
	@Check
	//Check for unsupported object names
	def checkUnsupportedDifferentWrt(ValuePair it){
		if(attributeName == ListDefinitionTable::DERIV_TYPE_ATT){
			val blk = EcoreUtil2::getContainerOfType(eContainer, BlockStatement)
			val attList = EcoreUtil2::getContainerOfType(eContainer, AttributeList)
			// check first that this is a well constructed derivative list in the correct block etc.
			if(blk != null && attList != null && blk.isKeyAttributeDefined(attList)){
				validateWrt(attList)
				validateInitTime(attList)
			}
		}
	}

	@Check
	def checkUnsupportedIfElse(ElseClause it){
		if(value != null){
			if(value instanceof IfExpression){
				warning("Nested conditional expression cannot be executed in R. Consider changing to 'elseif'.", 
						MdlPackage.eINSTANCE.elseClause_Value,
						FEATURE_NOT_SUPPORTED, 'else')
			} 
		}
	}
	
	
	@Check
	// Check for unsupported attribute names
	def checkUnsupportedAttributes(ValuePair it){
		val blk = owningBlock?.identifier
		val nm = attributeName
		if(blk != null && nm != null){
			val attMap = unsupportedAttributes.get(blk)
			if(attMap != null && attMap.contains(nm)){
				warning("Attribute name '" + nm + "' is not currently supported for execution in R.", 
						MdlPackage.eINSTANCE.valuePair_ArgumentName,
						FEATURE_NOT_SUPPORTED, nm)
			}
		}
	}
	
//	static val UnsupportedFunctions = #{ 'tanh', 'sinh', 'cosh', 'floor', 'mean' }
	static val Set<String> UnsupportedFunctions = #{ }
	
//	@Check
//	// Check for unsupported functions
	def checkUnsupportedFunctions(SymbolReference it){
		if(UnsupportedFunctions.contains(func)){
			warning("Function '" + func + "' is not currently supported for execution in R.", 
////					MdlPackage.eINSTANCE.builtinFunctionCall_Func,
					MdlPackage.eINSTANCE.symbolReference_Ref,
					FEATURE_NOT_SUPPORTED, func)
		}
	}
	
	
	@Check
	def checkUnsupportedCategoryRelations(EqualityExpression it){
		val leftType = leftOperand?.typeFor
		val rightType = rightOperand?.typeFor
		if((leftType != null && leftType.typeClass == TypeInfoClass.Enum)  || 
			(rightType != null && rightType.typeClass == TypeInfoClass.Enum)){
			warning("Equivalence operators with categorical types are not supported for execution in R.",
				MdlPackage::eINSTANCE.equalityExpression_Feature,
				FEATURE_NOT_SUPPORTED, feature)
		}
	}
	

	static val DataNamingLookup = #{
		ListDefinitionTable::AMT_USE_VALUE -> 'AMT',
		ListDefinitionTable::IDV_USE_VALUE -> 'TIME',
		ListDefinitionTable::ID_USE_VALUE -> 'ID',
		ListDefinitionTable::CMT_USE_VALUE -> 'CMT',
		ListDefinitionTable::RATE_USE_VALUE -> 'RATE',
		ListDefinitionTable::SS_USE_VALUE -> 'SS',
		ListDefinitionTable::II_USE_VALUE -> 'II',
		ListDefinitionTable::ADDL_USE_VALUE -> 'ADDL',
		ListDefinitionTable::MDV_USE_VALUE -> 'MDV',
		ListDefinitionTable::OBS_USE_VALUE -> 'DV'
	} 
	
	
	@Check
	def checkUnsupportedColumnName(ListDefinition it){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(blk != null && blk.identifier == BlockDefinitionTable::DIV_BLK_NAME){
			// data mapping block
			val useValue = firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT)
			val expectedColumnName = DataNamingLookup.get(useValue)
			if(expectedColumnName != null && expectedColumnName != name){
				warning("Column definitions with use '" + useValue + "' must be named '" + expectedColumnName + "' otherwise execution in R will fail.",
					MdlLibPackage::eINSTANCE.symbolDefinition_Name,
					FEATURE_NOT_SUPPORTED, useValue)
			}
		}
	}

	@Check
	//Check for unsupported object names
	def checkExperimentalFeature(ListDefinition it){
		val attListType = typeOfList
		if(attListType.typeName == ListDefinitionTable::DISCRETE_LIST_TYPE || attListType.typeName == ListDefinitionTable::CATEGORICAL_LIST_TYPE){
			warning("This is an experimental feature and may change in the future. Models using this feature may not be compatible with later versions of MDL.",
					MdlPackage::eINSTANCE.listDefinition_List,
					MdlValidator::EXPERIMENTAL_FEATURE, "")
		}
	}

}
