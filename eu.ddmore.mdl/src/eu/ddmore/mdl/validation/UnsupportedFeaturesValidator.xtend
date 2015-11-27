package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.ElseClause
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.MclUtils
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.ValuePair
import java.util.Map
import java.util.Set
import java.util.Collections
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.PrimitiveType
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.BlockStatement

class UnsupportedFeaturesValidator extends AbstractMdlValidator  {
	
	extension MclUtils mu = new MclUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ConstantEvaluation ce = new ConstantEvaluation
	extension MclTypeProvider mtp = new MclTypeProvider 
	
	override register(EValidatorRegistrar registrar){}
	
	private static val PERMITTED_X0_VALUE = 0.0

	public static val FEATURE_NOT_SUPPORTED = "eu.ddmore.mdl.validation.unsupported.feature"


	static val unsupportedObjects = #{
		MdlValidator::DESIGNOBJ
	}
	
	static val Map<String, Set<String>> unsupportedAttributes = Collections::emptyMap
//	#{
//		BlockDefinitionProvider::OBS_BLK_NAME -> #{
//			ListDefinitionProvider::TTE_EVENT_ATT,
//			ListDefinitionProvider::TTE_MAX_EVENT_ATT									
//		}
//	}

	@Check
	//Check for unsupported object names
	def checkUnsupported(MclObject it){
		if(unsupportedObjects.contains(mdlObjType)){
			warning("Objects of type '" + mdlObjType + "' are not currently supported for execution in R.", 
					MdlPackage.eINSTANCE.mclObject_MdlObjType,
					FEATURE_NOT_SUPPORTED, name)
		}			
	}
	
	
	private def validateWrt(AttributeList it){
		val wrtExpr = getAttributeExpression(ListDefinitionProvider::WRT_ATT)
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
		val x0Expr = getAttributeExpression(ListDefinitionProvider::DERIV_INIT_TIME_ATT)
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
		if(attributeName == ListDefinitionProvider::DERIV_TYPE_ATT){
			val attList = EcoreUtil2::getContainerOfType(eContainer, AttributeList)
			// check first that this is a well constructed derivative list in the correct block etc.
			if(attList.isKeyAttributeDefined){
				validateWrt(attList)
				validateInitTime(attList)
			}
		}
	}

	@Check
	def checkUnsupportedIfElse(ElseClause it){
		if(other != null){
			if(other instanceof WhenExpression){
				warning("Nested conditional expression cannot be executed in R. Consider changing to 'elseif'.", 
						MdlPackage.eINSTANCE.elseClause_Other,
						FEATURE_NOT_SUPPORTED, 'else')
			} 
		}
	}
	
	
	@Check
	// Check for unsupported attribute names
	def checkUnsupportedAttributes(ValuePair it){
		val blk = owningBlock.identifier
		val nm = attributeName
		if(blk != null && nm != null){
			if(unsupportedAttributes.get(blk)?.contains(nm)){
				warning("Attribute name '" + nm + "' is not currently supported for execution in R.", 
						MdlPackage.eINSTANCE.valuePair_ArgumentName,
						FEATURE_NOT_SUPPORTED, nm)
			}
		}
	}
	
	static val UnsupportedFunctions = #{ 'tanh', 'sinh', 'cosh' }
	
	@Check
	// Check for unsupported functions
	def checkUnsupportedFunctions(BuiltinFunctionCall it){
		if(UnsupportedFunctions.contains(func)){
			warning("Function '" + func + "' is not currently supported for execution in R.", 
					MdlPackage.eINSTANCE.builtinFunctionCall_Func,
					FEATURE_NOT_SUPPORTED, func)
		}
	}
	
	
	@Check
	def checkUnsupportedCategoryRelations(EqualityExpression it){
		val leftType = leftOperand?.typeFor
		val rightType = rightOperand?.typeFor
		if((leftType != null && leftType.theType == PrimitiveType.Enum)  || 
			(rightType != null && rightType.theType == PrimitiveType.Enum)){
			warning("Equivalence operators with categorical types are not supported for execution in R.",
				MdlPackage::eINSTANCE.equalityExpression_Feature,
				FEATURE_NOT_SUPPORTED, feature)
		}
	}
	

	static val DataNamingLookup = #{
		ListDefinitionProvider::AMT_USE_VALUE -> 'AMT',
		ListDefinitionProvider::IDV_USE_VALUE -> 'TIME',
		ListDefinitionProvider::ID_USE_VALUE -> 'ID',
		ListDefinitionProvider::CMT_USE_VALUE -> 'CMT',
		ListDefinitionProvider::RATE_USE_VALUE -> 'RATE',
		ListDefinitionProvider::SS_USE_VALUE -> 'SS',
		ListDefinitionProvider::II_USE_VALUE -> 'II',
		ListDefinitionProvider::ADDL_USE_VALUE -> 'ADDL',
		ListDefinitionProvider::MDV_USE_VALUE -> 'MDV',
		ListDefinitionProvider::OBS_USE_VALUE -> 'DV'
	} 
	
	
	@Check
	def checkUnsupportedColumnName(ListDefinition it){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(blk != null && blk.identifier == BlockDefinitionProvider::DIV_BLK_NAME){
			// data mapping block
			val useValue = list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT)
			val expectedColumnName = DataNamingLookup.get(useValue)
			if(expectedColumnName != null && expectedColumnName != name){
				warning("Column definitions with use '" + useValue + "' must be named '" + expectedColumnName + "' otherwise execution in R will fail.",
					MdlPackage::eINSTANCE.symbolDefinition_Name,
					FEATURE_NOT_SUPPORTED, useValue)
			}
		}
	}

	@Check
	def validateNoConditions(WhenExpression it){
		error("A conditional expression is not supported. Use a piecewise function instead.",
				MdlPackage.eINSTANCE.whenExpression_When, MdlValidator::UNSUPPORTED_FEATURE, "")
	}
}
