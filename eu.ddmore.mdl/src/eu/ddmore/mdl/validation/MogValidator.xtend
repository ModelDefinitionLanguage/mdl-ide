package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.utils.MclUtils
import java.util.ArrayList
import org.eclipse.xtext.EcoreUtil2

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*

class MogValidator {
//	public static class MogProvider{
//		
//		def getModelObject(MclObject mog){
//			// assume mandatory and 
//			val objDefns = mog.getBlocksByName(BlockDefinitionProvider::MOG_OBJ_NAME).head
//			for(idx : 0 .. 3){
////				val symBolRef
////				if(objDeEquationDefinition )
////				switch(idx){
////					case 0:
////					
////				}
////				
//			}
//		}
//		
//	}


	extension ListDefinitionProvider listProvider = new ListDefinitionProvider 
	extension MclTypeProvider typeProvider = new MclTypeProvider 
	extension MclUtils mclu = new MclUtils



	public var MclObject mdlObj
	public var MclObject dataObj
	public var MclObject paramObj
	public var MclObject taskObj
	public var MclObject designObj
	
	new(){
		
	}
	
	def getMdlObjectOfType(MclObject obj, String mdlType){
		val mcl = EcoreUtil2.getContainerOfType(obj, Mcl)
		mcl.objects.findFirst[mdlObjType == mdlType]
	}

	def buildMog(MclObject it){
		val objDefns = getBlocksByName(BlockDefinitionProvider::MOG_OBJ_NAME)
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
	
//	def forAllMdlObjects(MclObject it, (String) => void actionLambda){
//				val objDefns = getBlocksByName(BlockDefinitionProvider::MOG_OBJ_NAME)
//		
//		// expect 4 mdl object
//		// expect one of data, mdl, param, task
//		//  or    one of design, mdl, param. task
//		var designCnt = 0
//		var dataCnt = 0
//		var taskCnt = 0
//		var mdlCnt = 0
//		var paramCnt = 0
//		for(obj : objDefns){
//			if(obj instanceof AttributeList){
//				val enumExpr = (obj as AttributeList).getAttributeExpression('type')
//				val objType = switch(enumExpr){
//					EnumExpression:
//						enumExpr.enumValue
//					default: null
//				}
//				actionLambda.apply(objType)
//			}
//		}
//	}
//	
//	def validateMogWellFormed(MclObject it){
//		
//		// expect 4 mdl object
//		// expect one of data, mdl, param, task
//		//  or    one of design, mdl, param. task
//		val designIdx = 0
//		val dataIdx = 1
//		val taskIdx = 2
//		val mdlIdx = 3
//		val paramIdx = 4
//		val cntArr = newIntArrayOfSize(5)
//		forAllMdlObjects[objType|
//				switch(objType){
//					case(MdlValidator::MDLOBJ):
//						cntArr.set(mdlIdx, cntArr.get(mdlIdx)+1)
//					case(MdlValidator::DATAOBJ):
//						cntArr.set(dataIdx, cntArr.get(dataIdx)+1)
//					case(MdlValidator::PARAMOBJ):
//						cntArr.set(paramIdx, cntArr.get(paramIdx)+1)
//					case(MdlValidator::TASKOBJ):
//						cntArr.set(taskIdx, cntArr.get(taskIdx)+1)
//					case(MdlValidator::DESIGNOBJ):
//						cntArr.set(designIdx, cntArr.get(designIdx)+1)
//				}
//			]
//		if(cntArr.get(mdlIdx) <1){
//			// mdl missin
//		}
//		else ifcntArr.get(mdlIdx) <1
//	}
	
	
	// MOG validation rules
	// for data object
	
	// 1) All covariates (unless they have an RHS) must be matched to the data object
	// 1a) All covariates must be of the same type.
	// 2) All observations must be matched to a data object - unless this is a simulation
	// 3) All uninitialised parameters in STRUCURAL_PARAMS must match STRUCTURAL in parObj
	// 4) All unitinitialised parameters in VARIABILITY_PARAMETERS must match VARIABILITY in parObj
	// 5) Variability levels of type parameter must be matched to dataObj.
	// 6) Variability parameters of type dv must be matched to data if an estimation (not simulation)
	// 7) If IDV is specified in the model then it must match TIME in the data.
	// 8) Dosing variables must be mapped to variables in to MODEL_PREDICTION block
	// 8a) Dosing variables in the model must have type non-vector REAL.
	// 9) All symbols in the model should be initialised at the end of assembly 
	 
//	def isReadyToValidate(){
//		mdlObj?.isModelObject && dataObj?.isDataObject && paramObj?.isParamObject && taskObj?.taskObject
//	}
	
	
//	def validateForEstimation((String, String) => void errorLambda){
//		if(!isReadyToValidate) throw new IllegalStateException("Class is not properly initialised")
//		
//		validateCovariates(errorLambda)
//	}
	
	
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
					if(!dataOb.isMatchingDataUse(ListDefinitionProvider::ID_USE_VALUE, ListDefinitionProvider::VARLVL_USE_VALUE)){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "variability level " + mdlOb.name +" has an inconsistent type with its match in the dataObj");
					}
				}
				else if(mdlOb.isObservationVarLevel)
					if(!dataOb.isMatchingDataUse(ListDefinitionProvider::OBS_USE_VALUE)){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "variability level " + mdlOb.name +" has an inconsistent type with its match in the dataObj");
					}
			}
		}
	}
	
	
//	def validateForSimulation(){
//		
//	}
}