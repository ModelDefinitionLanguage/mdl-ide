package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockBody
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.BlockTextBody
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import org.eclipse.xtext.EcoreUtil2
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.validation.MdlValidator
import eu.ddmore.mdl.validation.BlockDefinitionProvider
import eu.ddmore.mdl.mdl.EquationDefinition
import java.util.ArrayList
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.validation.ListDefinitionProvider

import static extension eu.ddmore.mdl.utils.ExpressionConverter.*
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.Statement

class DomainObjectModelUtils {
	
	static def getStatements(BlockStatement it){
		(body as BlockStatementBody).statements
	} 
	
	static def getBlockText(BlockStatement it){
		(body as BlockTextBody).text
	} 

	static def getParentOfBlockStatement(BlockStatement it){
		if(eContainer instanceof BlockBody)	eContainer.eContainer
		else eContainer
	}
	
	static def getParentStatement(AttributeList it){
		EcoreUtil2.getContainerOfType(it, BlockStatement)
//		eContainer.eContainer.eContainer as BlockStatement
	}

	static def getParentBlock(ValuePair it){
		EcoreUtil2.getContainerOfType(it, BlockStatement)
//		eContainer.eContainer.eContainer.eContainer as BlockStatement
	}
	
	static def getParentList(ValuePair it){
		EcoreUtil2.getContainerOfType(it, AttributeList)
//		eContainer as AttributeList
	}
	
	static def getParentFunction(ValuePair it){
		EcoreUtil2.getContainerOfType(it, BuiltinFunctionCall)
	}
	
	// for unnamed function arguments
	static def getParentFunction(UnnamedArgument it){
		EcoreUtil2.getContainerOfType(it, BuiltinFunctionCall)
	}
	
	static def getFuncArgNum(UnnamedArgument it){
		val argList = parentFunction.argList
		var idx = -1;
		switch(argList){
			UnnamedFuncArguments:{
				for(a : argList.args){
					idx++
					if(a == it){
						return idx
					}
				}
			}
			default: idx		
		}
	}

	static def isMclObjectOfType(MclObject obj, String typeCode){
		obj.mdlObjType == typeCode	
	} 

	static def isTaskObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::TASKOBJ)	
	}

	static def isDataObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::DATAOBJ)	
	}

	static def isParamObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::PARAMOBJ)	
	}

	static def isModelObject(MclObject obj){
		obj.isMclObjectOfType(MdlValidator::MDLOBJ)	
	}


	static def getModelObject(Mcl mcl){
		mcl.objects.findFirst[isModelObject]
	}

	static def getDataObject(Mcl mcl){
		mcl.objects.findFirst[isDataObject]
	}

	static def getParamObject(Mcl mcl){
		mcl.objects.findFirst[isParamObject]
	}

	static def getTaskObject(Mcl mcl){
		mcl.objects.findFirst[isTaskObject]
	}

	static def getMdlCovariateDefns(MclObject mdlObj){
		val retVal = new ArrayList<Statement>
		mdlObj.blocks.filter[identifier == BlockDefinitionProvider::COVARIATE_BLK_NAME].forEach[(body as BlockStatementBody).statements.forEach[retVal.add(it)]]
		retVal
	}	


	static def boolean isDataCovariate(ListDefinition it){
		list.attributes.exists[argumentName == ListDefinitionProvider::USE_TYPE.enumName && expression.convertToString == ListDefinitionProvider::COV_USE_VALUE]
	}

	static def getDataCovariateDefns(MclObject dataObj){
		val retVal = new ArrayList<ListDefinition>
		dataObj.blocks.filter[identifier == BlockDefinitionProvider::DIV_BLK_NAME].forEach[(body as BlockStatementBody).statements.
			filter[st|
				switch(st){
					ListDefinition:
						st.isDataCovariate
					default: false
				}
			]
			.forEach[retVal.add(it as ListDefinition)]
		]
		retVal
	}	
}