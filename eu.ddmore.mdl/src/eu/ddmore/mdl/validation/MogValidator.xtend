package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.MclObject
import java.util.ArrayList

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolDefinition

class MogValidator {

	extension MclTypeProvider typeProvider = new MclTypeProvider 



	public var MclObject mdlObj
	public var MclObject dataObj
	public var MclObject paramObj
	public var MclObject taskObj
	
	
	// MOG validation rules
	// for data object
	
	// 1) All covariates (unless they have an RHS) must be matched to the data object
	// 1a) All covariates must be of the same type.
	// 2) All observations must be matched to a data object - unless this is a simulation
	// 3) All uninitialised parameters in STRUCURAL_PARAMS must match STRUCTURAL in parobj
	// 4) All unitinitialised parameters in VARIABILITY_PARAMETERS must match VARIABILITY in parobj
	// 5) Variability levels of type parameter must be matched to dataobj.
	// 6) Variability parameters of type dv must be matched to data if an estimation (not simulation)
	// 7) If IDV is specified in the model then it must match TIME in the data.
	// 8) Dosing variables must be mapped to variables in to MODEL_PREDICTION block
	// 8a) Dosing variables in the model must have type non-vector REAL.
	// 9) All symbols in the model should be initialised at the end of assembly 
	 
	def isReadyToValidate(){
		mdlObj?.isModelObject && dataObj?.isDataObject && paramObj?.isParamObject && taskObj?.taskObject
	}
	
	
	def validateForEstimation((String, String) => void errorLambda){
		if(!isReadyToValidate) throw new IllegalStateException("Class is not properly initialised")
		
		validateCovariates(errorLambda)
	}
	
	
	def validateCovariates((String, String) => void errorLambda){
		val expectedMdlCovars = new ArrayList<SymbolDefinition>
		
		mdlObj.mdlCovariateDefns.forEach[
			switch(it){
				EquationDefinition case expression == null: expectedMdlCovars.add(it)
				EnumerationDefinition: expectedMdlCovars.add(it)
			}
		]
		val dataCovars = dataObj.dataCovariateDefns
		for(mdlCov : expectedMdlCovars){
			val dataCovar = dataCovars.findFirst[name == mdlCov.name]
			if(dataCovar == null){
				errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "covariate " + mdlCov +" has no match in dataObj");
			}
			else if(!mdlCov.typeFor.isCompatible(dataCovar.typeFor)){
				errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "covariate " + mdlCov +" has an inconsistent type with its match in the dataObj");
			}
		}
	}
	
	
	def validateForSimulation(){
		
	} 
	
	
}