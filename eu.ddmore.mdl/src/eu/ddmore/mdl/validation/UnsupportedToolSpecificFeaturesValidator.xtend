package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.MdlUtils
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.utils.BlockUtils

class UnsupportedToolSpecificFeaturesValidator extends AbstractMdlValidator  {
	
	override register(EValidatorRegistrar registrar){}
	
	extension MdlUtils mu = new MdlUtils
	extension BlockUtils bu = new BlockUtils
	
	def isGeneralIdv(EquationTypeDefinition it){
		val eq = expression
		if(eq instanceof SymbolReference){
			eq.func == 'general'
		}
		else false
	}
	
	def isExplicitIdv(EquationTypeDefinition it){
		val eq = expression
		if(eq instanceof SymbolReference){
			eq.func != 'linear' && eq.func != 'general'
		}
		else true
	}
	
	@Check
	def checkMonolixUnsupportedIdv(EquationTypeDefinition it){
		val owningBlock = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(owningBlock != null && owningBlock.identifier == BlockDefinitionTable::MDL_INDIV_PARAMS){
			if(expression != null){
				// check for explicit and general defns
				if(isGeneralIdv){
					warning("General individual parameter definition is not currently supported by MONOLIX.", 
							MdlPackage.eINSTANCE.equationTypeDefinition_Expression,
							MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX, name)
				}
				else if(isExplicitIdv){
					warning("Explicit individual parameter definition is not currently supported by MONOLIX.", 
							MdlPackage.eINSTANCE.equationTypeDefinition_Expression,
							MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX, name)
				}
			}
		}
	}
	
	static val StandardErrorFuctions = #{  
		'combinedError1', 'combinedError2', 'additiveError', 'proportionalError' 
	}
	

	def isStandardResidualError(EquationTypeDefinition it){
		val eq = expression
		if(eq instanceof SymbolReference){
			StandardErrorFuctions.contains(eq.func)
		}
		else false
	}
	
	@Check
	def checkMonolixUnsupportedObs(EquationTypeDefinition it){
		val owningBlock = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(owningBlock != null && owningBlock.identifier == BlockDefinitionTable::OBS_BLK_NAME){
			// check for explicit and general defns
			if(expression != null && !isStandardResidualError){
				warning("Only the pre-defined error models are currently supported by MONOLIX.", 
						MdlPackage.eINSTANCE.equationTypeDefinition_Expression,
						MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX, name)
			}
		}
	}

}
