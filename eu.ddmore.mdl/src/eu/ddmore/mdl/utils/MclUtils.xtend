package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.validation.MdlValidator
import eu.ddmore.mdl.mdl.Statement
import java.util.ArrayList
import eu.ddmore.mdl.validation.BlockDefinitionProvider
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.validation.ListDefinitionProvider
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.Mcl
import static extension eu.ddmore.mdl.utils.ExpressionConverter.*
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.MappingExpression
import java.util.List
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.CatValRefMappingExpression
import org.eclipse.xtext.EcoreUtil2

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
	
	def SymbolDefinition getSingleSymbolRef(Expression expr){
		switch(expr){
			SymbolReference:
				return expr.ref
			CatValRefMappingExpression:
				expr.getSymbolDefnFromCatValRef
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
		}
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