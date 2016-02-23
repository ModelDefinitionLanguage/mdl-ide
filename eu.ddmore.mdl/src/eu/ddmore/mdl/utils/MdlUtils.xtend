package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.CatValRefMappingExpression
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.validation.MdlValidator
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.List
import org.eclipse.xtext.EcoreUtil2

class MdlUtils {
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension DependencyWalker dw = new DependencyWalker
//	extension TypeSystemProvider mtp = new TypeSystemProvider
	
	
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
		val retVal = new ArrayList<SymbolDefinition>
		mdlObj.blocks.filter[identifier == BlockDefinitionTable::COVARIATE_BLK_NAME].forEach[(body as BlockStatementBody).statements.forEach[retVal.add(it as SymbolDefinition)]]
		retVal
	}	


	def boolean isDataCovariate(ListDefinition it){
		isMatchingDataUse(ListDefinitionTable::COV_USE_VALUE, ListDefinitionTable::CATCOV_USE_VALUE)
//		list.attributes.exists[argumentName == ListDefinitionTable::USE_TYPE.enumName && (
//			expression.convertToString == ListDefinitionTable::COV_USE_VALUE || expression.convertToString == ListDefinitionTable::CATCOV_USE_VALUE)]
	}

	def boolean isDataObservation(ListDefinition it){
		isMatchingDataUse(ListDefinitionTable::OBS_USE_VALUE)
//		list.attributes.exists[argumentName == ListDefinitionTable::USE_TYPE.enumName && (
//			expression.convertToString == ListDefinitionTable::OBS_USE_VALUE)]
	}

	def boolean isDataSourceBlock(BlockStatement stmt){
		stmt.identifier == BlockDefinitionTable::DATA_SRC_BLK
	}

	def boolean isMatchingDataUse(ListDefinition it, String ... useValue){
		list.attributes.exists[argumentName == ListDefinitionTable::USE_ATT && useValue.exists[uv | expression.enumValue == uv] ]
	}

	def getDataSourceStmt(MclObject it){
		val blk = blocks.findFirst[identifier== BlockDefinitionTable::DATA_SRC_BLK]
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
		getDataColumnDefn(ListDefinitionTable::COV_USE_VALUE, ListDefinitionTable::CATCOV_USE_VALUE)
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
		val idvs = getDataColumnDefn(ListDefinitionTable::IDV_USE_VALUE)
		if(idvs.empty) null
		else idvs.head
	}	
	
	def getMdlIdv(MclObject it){
		val retVal = new ArrayList<EquationDefinition>
		blocks.filter[identifier == BlockDefinitionTable::IDV_BLK_NAME].forEach[(body as BlockStatementBody).statements.forEach[if(it instanceof EquationDefinition) retVal.add(it)]]
		if(retVal.empty) null
		else retVal.head
	}	
	
	def getDataObservations(MclObject it){
		val retVal = new ArrayList<SymbolDefinition>
		for(obsLst :getDataColumnDefn(ListDefinitionTable::OBS_USE_VALUE)){
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
		for(obsLst :getDataColumnDefn(ListDefinitionTable::AMT_USE_VALUE)){
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
		getStatementsInBlock(BlockDefinitionTable::MDL_PRED_BLK_NAME)
//		val retVal = new ArrayList<Statement>
//		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::MDL_PRED_BLK_NAME]){
//			retVal.addAll(stmt.nonBlockStatements)
//		}
//		retVal
	}	
	
	def getMdlIndvParams(MclObject it){
		getStatementsInBlock(BlockDefinitionTable::MDL_INDIV_PARAMS)
//		val retVal = new ArrayList<Statement>
//		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::MDL_INDIV_PARAMS]){
//			retVal.addAll(stmt.nonBlockStatements)
//		}
//		retVal
	}	
	
	def getMdlStructuralParameters(MclObject it){
		getStatementsInBlock(BlockDefinitionTable::MDL_STRUCT_PARAMS)
//		val retVal = new ArrayList<Statement>
//		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::MDL_STRUCT_PARAMS]){
//			retVal.addAll(stmt.nonBlockStatements)
//		}
//		retVal
	}
	
	def getMdlVariabilityParameters(MclObject it){
		getStatementsInBlock(BlockDefinitionTable::MDL_VAR_PARAMS)
//		val retVal = new ArrayList<Statement>
//		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::MDL_VAR_PARAMS]){
//			retVal.addAll(stmt.nonBlockStatements)
//		}
//		retVal
	}
	
	private def getStatementsInBlock(MclObject it, String blkName){
		val retVal = new ArrayList<Statement>
		for(stmt : blocks.filter[identifier == blkName]){
			retVal.addAll(stmt.nonBlockStatements)
		}
		retVal
	}
	
	def getParamVariabilityParameters(MclObject it){
		getStatementsInBlock(BlockDefinitionTable::PARAM_VARIABILITY_BLK)
	}
	
	def isParVariabilityParam(Statement it){
		isParentBlockAsNamed(BlockDefinitionTable::PARAM_VARIABILITY_BLK)
	}

	def isParStructuralParam(Statement it){
		isParentBlockAsNamed(BlockDefinitionTable::PARAM_STRUCT_BLK)
	}

	private def isParentBlockAsNamed(Statement it, String name){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		blk?.identifier == name
	}

	def getParamStructuralParameters(MclObject it){
		getStatementsInBlock(BlockDefinitionTable::PARAM_STRUCT_BLK)
//		val retVal = new ArrayList<Statement>
//		for(stmt : blocks.filter[identifier == BlockDefinitionProvider::PARAM_STRUCT_BLK]){
//			retVal.addAll(stmt.nonBlockStatements)
//		}
//		retVal
	}
	
	def getModelPredictionBlocks(MclObject it){
		blocks.filter[identifier == BlockDefinitionTable::MDL_PRED_BLK_NAME]
	}
	
	def getMdlObservations(MclObject it){
		val retVal = new ArrayList<SymbolDefinition>
		for(obsStmt : blocks.filter[identifier == BlockDefinitionTable::OBS_BLK_NAME]){
			val body = obsStmt.body
			switch(body){
				BlockStatementBody:{
					body.statements.forEach[retVal.add(it as SymbolDefinition)]
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
							s.identifier == BlockDefinitionTable::MDL_CMT_BLK
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
		for(stmt : blocks.filter[identifier == BlockDefinitionTable::PARAM_STRUCT_BLK]){
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
		for(stmt : blocks.filter[identifier == BlockDefinitionTable::PARAM_VARIABILITY_BLK]){
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
		getDataColumnDefn(ListDefinitionTable::ID_USE_VALUE, ListDefinitionTable::VARLVL_USE_VALUE, ListDefinitionTable::OBS_USE_VALUE)
	}	

	def getMdlVariabilityLevels(MclObject it){
		val retVal = new ArrayList<ListDefinition>
		for(obsStmt : blocks.filter[identifier == BlockDefinitionTable::VAR_LVL_BLK_NAME]){
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

	def getDataColumnDefn(MclObject dataObj, String ... useValue){
		val retVal = new ArrayList<ListDefinition>
		for(divBlk : dataObj.blocks.filter[identifier == BlockDefinitionTable::DIV_BLK_NAME]){
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
		for(divBlk : blocks.filter[identifier == BlockDefinitionTable::DIV_BLK_NAME]){
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
		for(divBlk : blocks.filter[identifier == BlockDefinitionTable::DATA_DERIV_BLK_NAME]){
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

//	def getDataMappingForDoseVariable(MclObject it, SymbolDefinition doseVar){
//		val doseColumn = dataColumnDefinitions.findFirst[list.getAttributeEnumValue('use') == ListDefinitionTable::AMT_USE_VALUE]
//		val mappingAtt = doseColumn.list.getAttributeExpression(ListDefinitionTable::DEFINE_ATT)
//		if(mappingAtt != null){
//			
//		}
//	}


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
	
	def getDataMappingValueFromSymbol(MappingExpression it, String symbolName){
		for(mp : attList){
			if(mp.rightOperand.getSingleSymbolRef.name == symbolName){
				return mp.leftOperand
			}
		}
		null
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
	
	def SymbolDefinition getSymbolDefnFromCatValRef(CatValRefMappingExpression expr){
		var SymbolDefinition retVal = null
		if (!expr.attLists.isEmpty) {
			retVal = getSymbolDefnFromCatValRef(expr.attLists.head.catRef)
		}
		retVal
	}
	
	/**
	 * Get symbol defn that owns cat defn
	 */
        def SymbolDefinition getSymbolDefnFromCatValRef(CategoryValueReference catValRef){
                var catValDefn = catValRef.ref
                EcoreUtil2.getContainerOfType(catValDefn, SymbolDefinition)
        }
	
    def List<SymbolDefinition> getCovariateDependencies(Expression expr){
    	val retVal = new ArrayList<SymbolDefinition> 
    	expr.getSymbolReferences.forEach[ 
    		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
    		if(blk?.identifier == BlockDefinitionTable::COVARIATE_BLK_NAME) retVal.add(it)
    	]
    	retVal
    }
    
    def isMdlCompartmentMacro(Statement it){
    	owningBlock.identifier == BlockDefinitionTable::MDL_CMT_BLK
    }
    
    static val dosingMacros = #{ ListDefinitionTable::CMT_DIRECT_VALUE, ListDefinitionTable::CMT_DEPOT_VALUE }
    
    def isCmtDosingMacro(Statement it){
    	val stmt = it
    	if(stmt instanceof ListDefinition){
    		val macroType = stmt.list.getAttributeEnumValue(ListDefinitionTable::CMT_TYPE_ATT)
    		dosingMacros.contains(macroType)   
    	}
    	else false
    }

	def func(SymbolReference it){
		ref.name
	}

}
