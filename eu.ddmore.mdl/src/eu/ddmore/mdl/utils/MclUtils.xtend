package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.CatValRefMappingExpression
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.validation.BlockDefinitionProvider
import eu.ddmore.mdl.validation.ListDefinitionProvider
import eu.ddmore.mdl.validation.MdlValidator
import java.util.ArrayList
import java.util.List
import org.eclipse.xtext.EcoreUtil2

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import static extension eu.ddmore.mdl.utils.ExpressionConverter.*
import eu.ddmore.mdl.mdl.CategoryValueReference

class MclUtils {
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	
	
	def isMclObjectOfType(MclObject obj, String typeCode){
		obj.mdlObjType == typeCode	
	} 

	def isTaskObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::TASKOBJ)	
	}

	def isDataObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::DATAOBJ)	
	}

	def isParamObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::PARAMOBJ)	
	}

	def isModelObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::MDLOBJ)	
	}


	def isMogObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::MOGOBJ)	
	}


	def getModelObject(Mcl mcl){
		mcl.objects.findFirst[isModelObject]
	}

	def getDataObject(Mcl mcl){
		mcl.objects.findFirst[isDataObject]
	}

	def getParamObject(Mcl mcl){
		mcl.objects.findFirst[isParamObject]
	}

	def getTaskObject(Mcl mcl){
		mcl.objects.findFirst[isTaskObject]
	}

	def getMogObject(Mcl mcl){
		mcl.objects.findFirst[isMogObject]
	}

	def getMogObjects(Mcl mcl){
		mcl.objects.filter[isMogObject]
	}

	def getMdlCovariateDefns(MclObject mdlObj){
		val retVal = new ArrayList<Statement>
		mdlObj.blocks.filter[identifier == BlockDefinitionProvider::COVARIATE_BLK_NAME].forEach[(body as BlockStatementBody).statements.forEach[retVal.add(it)]]
		retVal
	}	


	def boolean isDataCovariate(ListDefinition it){
		isMatchingDataUse(ListDefinitionProvider::COV_USE_VALUE, ListDefinitionProvider::CATCOV_USE_VALUE)
//		list.attributes.exists[argumentName == ListDefinitionProvider::USE_TYPE.enumName && (
//			expression.convertToString == ListDefinitionProvider::COV_USE_VALUE || expression.convertToString == ListDefinitionProvider::CATCOV_USE_VALUE)]
	}

	def boolean isDataObservation(ListDefinition it){
		isMatchingDataUse(ListDefinitionProvider::OBS_USE_VALUE)
//		list.attributes.exists[argumentName == ListDefinitionProvider::USE_TYPE.enumName && (
//			expression.convertToString == ListDefinitionProvider::OBS_USE_VALUE)]
	}

	def boolean isMatchingDataUse(ListDefinition it, String ... useValue){
		list.attributes.exists[argumentName == ListDefinitionProvider::USE_TYPE.enumName &&	useValue.exists[uv | expression.convertToString == uv] ]
	}

	def getDataSourceStmt(MclObject it){
		val blk = blocks.findFirst[identifier== BlockDefinitionProvider::DATA_SRC_BLK]
		val stmts = blk.getStatementsFromBlock
		if(stmts.isEmpty) null
		else{
			val retVal = stmts.head
			if(retVal instanceof ListDefinition){
				retVal as ListDefinition
			}
			else null
		} 
	}

	def getDataCovariateDefns(MclObject it){
		getDataColumnDefn(ListDefinitionProvider::COV_USE_VALUE, ListDefinitionProvider::CATCOV_USE_VALUE)
//		val retVal = new ArrayList<ListDefinition>
//		dataObj.blocks.filter[identifier == BlockDefinitionProvider::DIV_BLK_NAME].forEach[(body as BlockStatementBody).statements.
//			filter[st|
//				switch(st){
//					ListDefinition:
//						st.isDataCovariate
//					default: false
//				}
//			]
//			.forEach[retVal.add(it as ListDefinition)]
//		]
//		retVal
	}
	
	def getDataIdv(MclObject it){
		val idvs = getDataColumnDefn(ListDefinitionProvider::IDV_USE_VALUE)
		if(idvs.empty) null
		else idvs.head
	}	
	
	def getMdlIdv(MclObject it){
		val retVal = new ArrayList<EquationDefinition>
		blocks.filter[identifier == BlockDefinitionProvider::IDV_BLK_NAME].forEach[(body as BlockStatementBody).statements.forEach[if(it instanceof EquationDefinition) retVal.add(it)]]
		if(retVal.empty) null
		else retVal.head
	}	
	
	def getDataObservations(MclObject it){
		val retVal = new ArrayList<SymbolDefinition>
		for(obsLst :getDataColumnDefn(ListDefinitionProvider::OBS_USE_VALUE)){
			val varRef = obsLst.list.getAttributeExpression('variable')
			if(varRef != null){
				retVal.add(varRef.singleSymbolRef) // expect a var ref here.
			}
			else{
				val defineRef = obsLst.list.getAttributeExpression('define')
				retVal.addAll(defineRef.mappedSymbolRef)
			}
		}
		retVal
	}

	def getDataDosingVariables(MclObject it){
		val retVal = new ArrayList<SymbolDefinition>
		for(obsLst :getDataColumnDefn(ListDefinitionProvider::AMT_USE_VALUE)){
			val varRef = obsLst.list.getAttributeExpression('variable')
			if(varRef != null){
				retVal.add(varRef.singleSymbolRef) // expect a var ref here.
			}
			else{
				val defineRef = obsLst.list.getAttributeExpression('define')
				retVal.addAll(defineRef.mappedSymbolRef)
			}
		}
		retVal
	}	

	def List<Statement> getNonBlockStatements(BlockStatement it){
		val retVal = new ArrayList<Statement>
		val body = body
		switch(body){
			BlockStatementBody:{
				for(stmt : body.statements){
					switch(stmt){
						BlockStatement:
							retVal.addAll(stmt.getNonBlockStatements)
						default:
							retVal.add(stmt)
					}
						
				}
			}
		}
		retVal
	}

	def getMdlPredictionVariables(MclObject it){
		val retVal = new ArrayList<Statement>
		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::MDL_PRED_BLK_NAME]){
			retVal.addAll(stmt.nonBlockStatements)
		}
		retVal
	}	
	
	def getMdlIndvParams(MclObject it){
		val retVal = new ArrayList<Statement>
		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::MDL_INDIV_PARAMS]){
			retVal.addAll(stmt.nonBlockStatements)
		}
		retVal
	}	
	
	def getModelPredictionBlocks(MclObject it){
		blocks.filter[identifier == BlockDefinitionProvider::MDL_PRED_BLK_NAME]
	}
	
	def getMdlObservations(MclObject it){
		val retVal = new ArrayList<Statement>
		for(obsStmt : blocks.filter[identifier == BlockDefinitionProvider::OBS_BLK_NAME]){
			val body = obsStmt.body
			switch(body){
				BlockStatementBody:{
					retVal.addAll(body.statements)
				}
			}
		}
		retVal
	}	

	def List<Statement> getMdlCompartmentStatements(MclObject it){
		val retVal = new ArrayList<Statement>
		for(b :  modelPredictionBlocks){
			for(s : (b.body as BlockStatementBody).statements.filter[s|
					switch(s){
						BlockStatement:
							s.identifier == BlockDefinitionProvider::MDL_CMT_BLK
						default: false
					}
				]){
				switch(s){
					BlockStatement:{
						retVal.addAll((s.body as BlockStatementBody).statements)
					}
				}
			}
		}
		retVal
	}

	def getParamStructuralParams(MclObject it){
		val retVal = new ArrayList<Statement>
		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::PARAM_STRUCT_BLK]){
			val body = stmt.body
			switch(body){
				BlockStatementBody:{
					retVal.addAll(body.statements)
				}
			}
		}
		retVal
	}

	def getParamVariabilityParams(MclObject it){
		val retVal = new ArrayList<Statement>
		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::PARAM_VARIABILITY_BLK]){
			val body = stmt.body
			switch(body){
				BlockStatementBody:{
					retVal.addAll(body.statements)
				}
			}
		}
		retVal
	}

	def getParamCorrelations(MclObject it){
		paramVariabilityParams.filter[s|
			switch(s){
				ListDefinition:{
					val varType = s.list.getAttributeEnumValue('type')
					varType == 'corr' || varType == 'cov'
				}
				default: false
			}
		]
	}

	def getDataVariabilityLevels(MclObject it){
		getDataColumnDefn(ListDefinitionProvider::ID_USE_VALUE, ListDefinitionProvider::VARLVL_USE_VALUE, ListDefinitionProvider::OBS_USE_VALUE)
	}	

	def getMdlVariabilityLevels(MclObject it){
		val retVal = new ArrayList<ListDefinition>
		for(obsStmt : blocks.filter[identifier == BlockDefinitionProvider::VAR_LVL_BLK_NAME]){
			val body = obsStmt.body
			switch(body){
				BlockStatementBody:{
					body.statements.forEach[s|if(s instanceof ListDefinition) retVal.add(s)]
				}
			}
		}
		retVal
	}	

	def getRandomVarLevel(SymbolDefinition it){
		val rvBlock = owningBlock
		val blkArgs = rvBlock.blkArgs
		for(arg : blkArgs.args){
			switch(arg){
				ValuePair:
					if(arg.argumentName == 'level') return arg.expression as SymbolReference
			}
		}
		null
	}

	def isParameterVarLevel(ListDefinition it){
		val enumValue = list.getAttributeEnumValue("type")
		enumValue == 'parameter'
	}

	def isObservationVarLevel(ListDefinition it){
		val enumValue = list.getAttributeEnumValue("type")
		enumValue == 'observation'
	}

	def isCompartmentInput(MclObject model, SymbolDefinition testVar){
		//@TODO: Finish this
		false
	}


	def getDataColumnDefn(MclObject dataObj, String ... useValue){
		val retVal = new ArrayList<ListDefinition>
		for(divBlk : dataObj.blocks.filter[identifier == BlockDefinitionProvider::DIV_BLK_NAME]){
			if(divBlk.body instanceof BlockStatementBody){
				for(divList : (divBlk.body as BlockStatementBody).statements){
					switch(divList){
						ListDefinition case(divList.isMatchingDataUse(useValue)):{
							retVal.add(divList)
						}
					}
				}
				
			}
		}

		retVal
	}
	
	def getDataColumnDefinitions(MclObject it){
		val retVal = new ArrayList<ListDefinition>
		for(divBlk : blocks.filter[identifier == BlockDefinitionProvider::DIV_BLK_NAME]){
			if(divBlk.body instanceof BlockStatementBody){
				for(divList : (divBlk.body as BlockStatementBody).statements){
					switch(divList){
						ListDefinition:{
							retVal.add(divList)
						}
					}
				}
				
			}
		}

		retVal
	}

	def getDataDerivedColumnDefinitions(MclObject it){
		val retVal = new ArrayList<ListDefinition>
		for(divBlk : blocks.filter[identifier == BlockDefinitionProvider::DATA_DERIV_BLK_NAME]){
			if(divBlk.body instanceof BlockStatementBody){
				for(divList : (divBlk.body as BlockStatementBody).statements){
					switch(divList){
						ListDefinition:{
							retVal.add(divList)
						}
					}
				}
				
			}
		}

		retVal
	}


	def findMdlSymbolDefn(MclObject it, String symbolName){
		for(blk : blocks){
			val retVal = blk.nonBlockStatements.findFirst[s|
				switch(s){
					SymbolDefinition: s.name == symbolName
					default: false
				}
			]
			if(retVal != null) return retVal as SymbolDefinition
		}
		null
	}
	
	
	def SymbolDefinition getSingleSymbolRef(Expression expr){
		switch(expr){
			SymbolReference:
				return expr.ref
			CatValRefMappingExpression:
				expr.symbolDefnFromCatValRef
			ParExpression:
				return expr.expr.singleSymbolRef
			default: null
		}
	}
	
	def List<SymbolDefinition> getMappedSymbolRef(Expression expr){
		val retVal = new ArrayList<SymbolDefinition>
		switch(expr){
			MappingExpression:{
				for(mp : expr.attList){
					retVal.add(mp.rightOperand.getSingleSymbolRef)
				}
			}
			CatValRefMappingExpression:{
				retVal.add(expr.symbolDefnFromCatValRef)
			}
		}
		retVal
	}
	
	def SymbolReference getMappedSymbol(MappingPair it){
		val ro = rightOperand
		switch(ro){
			SymbolReference:
				ro
			default: null
		}
	}
	
	// get varlevel from RAND_VAR_BLK
	def SymbolReference getVarLevel(BlockStatement it){
		for(arg : blkArgs.args){
			switch(arg){
				ValuePair case(arg.argumentName == 'level'):
					if(arg.expression instanceof SymbolReference){
						return arg.expression as SymbolReference
					}
			}
		}
		null
	}
	
	def SymbolDefinition getSymbolDefnFromCatValRef(CategoryValueReference expr){
		var SymbolDefinition retVal = null
		var catValDefn = expr.ref
		// now get symbol defn that owns cat defn
		retVal = EcoreUtil2.getContainerOfType(catValDefn, SymbolDefinition)
		retVal
	}

	def SymbolDefinition getSymbolDefnFromCatValRef(CatValRefMappingExpression expr){
		var SymbolDefinition retVal = null
		if(!expr.attLists.isEmpty){
			var catValDefn = expr.attLists.head.catRef.ref
			// now get symbol defn that owns cat defn
			retVal = EcoreUtil2.getContainerOfType(catValDefn, SymbolDefinition)
		}
		retVal
	}
}