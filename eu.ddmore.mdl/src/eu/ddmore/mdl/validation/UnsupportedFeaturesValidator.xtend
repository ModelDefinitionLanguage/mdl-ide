package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.MclUtils
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class UnsupportedFeaturesValidator extends AbstractMdlValidator  {
	
	extension MclUtils mu = new MclUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	
	override register(EValidatorRegistrar registrar){}
	
	public static val FEATURE_NOT_SUPPORTED = "eu.ddmore.mdl.validation.unsupported.feature"


	static val unsupportedObjects = #{
//		MdlValidator::DESIGNOBJ
	}

	@Check
	//Check for unsupported object names
	def checkUnsupported(MclObject it){
		if(unsupportedObjects.contains(mdlObjType)){
			warning("Objects of type '" + mdlObjType + "' are not currently supported for execution in R.", 
					MdlPackage.eINSTANCE.mclObject_MdlObjType,
					FEATURE_NOT_SUPPORTED, name)
		}			
	}
	
	@Check
	//Check for unsupported object names
	def checkUnsupportedDifferentWrt(ValuePair it){
		if(attributeName == ListDefinitionProvider::DERIV_TYPE_ATT){
			val attList = EcoreUtil2::getContainerOfType(eContainer, AttributeList)
			// check first that this is a well constructed derivative list in the correct block etc.
			if(attList.isKeyAttributeDefined){
				val wrtExpr = attList.getAttributeExpression(ListDefinitionProvider::WRT_ATT)
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
		}
	}
	
}