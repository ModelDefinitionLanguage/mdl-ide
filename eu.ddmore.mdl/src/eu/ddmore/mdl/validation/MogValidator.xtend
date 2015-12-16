package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MclUtils
import java.util.ArrayList
import org.eclipse.xtext.EcoreUtil2

class MogValidator {

	extension ListDefinitionProvider listProvider = new ListDefinitionProvider 
	extension MclTypeProvider typeProvider = new MclTypeProvider 
	extension MclUtils mclu = new MclUtils
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils



	public var MclObject mdlObj
	public var MclObject dataObj
	public var MclObject paramObj
	public var MclObject taskObj
	public var MclObject designObj
	
	new(){
		
	}
	
	static def findMdlObject(MclObject obj, String name, String mdlType){
		val mcl = EcoreUtil2.getContainerOfType(obj, Mcl)
		mcl.objects.findFirst[mdlObjType == mdlType && it.name == name]
	}

	static def getMdlObjectOfType(MclObject obj, String mdlType){
		val mcl = EcoreUtil2.getContainerOfType(obj, Mcl)
		mcl.objects.findFirst[mdlObjType == mdlType]
	}

	def buildMog(MclObject it){
		val objDefns = getBlocksByName(BlockDefinitionTable::MOG_OBJ_NAME)
		for(obj : objDefns){
			if(obj.body instanceof BlockStatementBody){
				for(stmt : (obj.body as BlockStatementBody).statements)
					if(stmt instanceof ListDefinition){
						val lst = (stmt as ListDefinition).list
						val enumExpr = lst.getAttributeExpression('type')
						val objType = switch(enumExpr){
							EnumExpression:
								enumExpr.enumValue
							default: null
						}
						switch(objType){
							case(MdlValidator::MDLOBJ):
								mdlObj = getMdlObjectOfType(objType)
							case(MdlValidator::DATAOBJ):
								dataObj = getMdlObjectOfType(objType)
							case(MdlValidator::PARAMOBJ):
								paramObj = getMdlObjectOfType(objType)
							case(MdlValidator::TASKOBJ):
								taskObj = getMdlObjectOfType(objType)
							case(MdlValidator::DESIGNOBJ):
								designObj = getMdlObjectOfType(objType)
						}
					}
			}
		}
	}
	
	// MOG validation rules
	// for data object
	
	// 1) All covariates (unless they have an RHS) must be matched to the data object - Done
	// 1a) All covariates must be of the same type.  -Done
	// 2) All observations must be matched to a data object - unless this is a simulation  - Done (ignore simulation case)
	// 3) All uninitialised parameters in STRUCURAL_PARAMS must match STRUCTURAL in parObj - Done
	// 4) All unitinitialised parameters in VARIABILITY_PARAMETERS must match VARIABILITY in parObj - Done
	// 5) Variability levels of type parameter must be matched to dataObj. - Done
	// 6) Variability parameters of type dv must be matched to data if an estimation (not simulation) - Done (ignore simulation case)
	// 7) If IDV is specified in the model then it must match TIME in the data. - Done 
	// 8) Dosing variables must be mapped to variables in to MODEL_PREDICTION block
	// 8a) Dosing variables in the model must have type non-vector REAL.  - Done by checks preventing vector defns
	// 9) All symbols in the model should be initialised at the end of assembly 
	// 10) Dosing time match to a variable in model prediction
	// 11) Check that eta in par object are defined in model. 
	 
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
				errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "covariate " + mdlCov.name +" has no match in dataObj");
			}
			else if(!mdlCov.typeFor.isCompatible(dataCovar.typeFor)){
				errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "covariate " + mdlCov.name +" has an inconsistent type with its match in the dataObj");
			}
		}
	}
	
	def validateObservations((String, String) => void errorLambda){
		val dataObs = dataObj.dataObservations
		for(mdlOb : mdlObj.mdlObservations){
			if(mdlOb instanceof SymbolDefinition){
				val dataOb = dataObs.findFirst[name == (mdlOb as SymbolDefinition).name]
				if(dataOb == null){
					errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "observation " + mdlOb.name +" has no match in dataObj");
				}
				else if(!mdlOb.typeFor.isCompatible(dataOb.typeFor)){
					errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "observation " + mdlOb.name +" has an inconsistent type with its match in the dataObj");
				}
			}
		}
	}
	
	def validateVariabilityLevels((String, String) => void errorLambda){
		val dataVarLvls = dataObj.dataVariabilityLevels
		for(mdlOb : mdlObj.mdlVariabilityLevels){
			if(mdlOb instanceof SymbolDefinition){
				val dataOb = dataVarLvls.findFirst[name == (mdlOb as ListDefinition).name]
				if(dataOb == null){
					errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "variability level " + mdlOb.name +" has no match in dataObj");
				}
				else if(mdlOb.isParameterVarLevel){
					if(!dataOb.isMatchingDataUse(ListDefinitionTable::ID_USE_VALUE, ListDefinitionTable::VARLVL_USE_VALUE)){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "variability level " + mdlOb.name +" has an inconsistent type with its match in the dataObj");
					}
				}
				else if(mdlOb.isObservationVarLevel)
					if(!dataOb.isMatchingDataUse(ListDefinitionTable::OBS_USE_VALUE)){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "variability level " + mdlOb.name +" has an inconsistent type with its match in the dataObj");
					}
			}
		}
	}
	
	def validateIndividualVariable((String, String) => void errorLambda){
		val dataOb = dataObj.dataIdv
		val mdlOb = mdlObj.mdlIdv
		if(mdlOb != null){
			val mdlIdvName = (mdlOb as EquationDefinition).name
			// idv does not need to have the same name
			if(dataOb == null){ //} || dataOb.name != mdlIdvName){
				errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "independent variable " + mdlIdvName +" has no match in dataObj");
			}
		}
	}
	
	def validateDosing((String, String) => void errorLambda){
		val mdlStmts = mdlObj.mdlPredictionVariables
		for(dataDose : dataObj.dataDosingVariables){
			val stmt = mdlStmts.findFirst[if(it instanceof SymbolDefinition) (it as SymbolDefinition).name == dataDose.name else false]
			if(stmt == null){
				errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "dosing variable " + dataDose.name +" has no match in mdlObj");
			}
			else if(!stmt.typeFor.isCompatible(dataDose.typeFor)){
				errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "dosing variable " + dataDose.name +" has an inconsistent type with its match in the mdlObj");
			}
		}
	}
	
	def boolean isAssigned(Statement stmt){
		switch(stmt){
			EquationTypeDefinition: stmt.expression != null
			default: true
		}
	}
	
	
	def validateStructuralParameters((String, String) => void errorLambda, (String, String) => void warningLambda){
		for(mdlStmt : mdlObj.mdlStructuralParameters){
			if(mdlStmt instanceof SymbolDefinition){
				val parStmt = paramObj.findMdlSymbolDefn(mdlStmt.name)
				if(parStmt == null){
					if(!mdlStmt.isAssigned) 
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "parameter '" + mdlStmt.name +"' has no match in parObj");
				}
				if((parStmt as Statement).isParVariabilityParam){
					errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "Parameter '" + mdlStmt.name +"' in mdlObj cannot match a variability parameter in the parObj");
				}
				else if(!mdlStmt.typeFor.isCompatible(parStmt.typeFor)){
					errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "parameter '" + parStmt.name +"' has an inconsistent type with its match in the parObj");
				}
				else if(mdlStmt.isAssigned){
					warningLambda.apply(MdlValidator::MASKING_PARAM_ASSIGNMENT, "value assigned to parameter '" + parStmt.name +"' in mdlObj is overridden by value in parObj");
				}
			}
		}
	}
	
	def validateVariabilityParameters((String, String) => void errorLambda, (String, String) => void warningLambda){
		for(mdlStmt : mdlObj.mdlVariabilityParameters){
			if(mdlStmt instanceof SymbolDefinition){
				val parStmt = paramObj.findMdlSymbolDefn(mdlStmt.name)
				if(parStmt == null){
					if(!mdlStmt.isAssigned) 
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "parameter '" + mdlStmt.name +"' has no match in parObj");
				}
				else{
					if((parStmt as Statement).isParStructuralParam){
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "Parameter '" + mdlStmt.name +"' in mdlObj cannot match a structural parameter in the parObj");
					}
					else if(!mdlStmt.typeFor.isCompatible(parStmt.typeFor)){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "parameter '" + parStmt.name +"' has an inconsistent type with its match in the parObj");
					}
					else if(mdlStmt.isAssigned){
						warningLambda.apply(MdlValidator::MASKING_PARAM_ASSIGNMENT, "value assigned to parameter '" + parStmt.name +"' in mdlObj is overridden by value in parObj");
					}
				}
			}
		}
	}
	
}