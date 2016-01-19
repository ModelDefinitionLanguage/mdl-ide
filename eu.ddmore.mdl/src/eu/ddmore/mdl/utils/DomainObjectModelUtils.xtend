package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockBody
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.BlockTextBody
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.VectorElement
import java.util.Collections
import org.eclipse.xtext.EcoreUtil2

class DomainObjectModelUtils {
	
	def getStatements(BlockStatement it){
		val b = body
		switch(b){
			BlockStatementBody:
				b.statements
			default:
				Collections::emptyList
		}
	} 
	
	def getBlockText(BlockStatement it){
		(body as BlockTextBody).text
	} 

	def getParentOfBlockStatement(BlockStatement it){
		if(eContainer instanceof BlockBody)	eContainer.eContainer
		else eContainer
	}
	
	
	def getOwningValuePair(Expression it){
		EcoreUtil2.getContainerOfType(eContainer, ValuePair)
	}
	
	def getOwningBlock(EnumExpression it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	} 
	
	def getOwningBlock(SymbolDefinition it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	}
	
	def getOwningBlock(Statement it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	}
	
	def getParentStatement(AttributeList it){
		EcoreUtil2.getContainerOfType(it, BlockStatement)
//		eContainer.eContainer.eContainer as BlockStatement
	}

	def getParentBlock(ValuePair it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
//		eContainer.eContainer.eContainer.eContainer as BlockStatement
	}
	
	def getParentBlock(Statement it){
		EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
	}
	
	def getParentList(ValuePair it){
		EcoreUtil2.getContainerOfType(it, AttributeList)
//		eContainer as AttributeList
	}
	
	def getParentFunction(ValuePair it){
		EcoreUtil2.getContainerOfType(it, SymbolReference)
	}
	
	// for unnamed function arguments
	def getParentFunction(UnnamedArgument it){
		EcoreUtil2.getContainerOfType(it, SymbolReference)
	}
	
	def getFuncArgNum(UnnamedArgument it){
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
	
	def getFunctionArgumentValue(SymbolReference it, String argName){
		val args = argList
		switch(args){
			NamedFuncArguments:{
				args.arguments.findFirst[argumentName == argName]?.expression
			}
			default: null
		}
	}
	
	def getBlocksByName(MclObject mdlObj, String blkName){
		mdlObj.blocks.filter[identifier == blkName]
	}

	def getStatementsFromBlock(BlockStatement it){
		val b = body
		switch(b){
			BlockStatementBody:
				b.statements
			default:
				Collections::emptyList
		}
			
	}

	def getVectorElementAsSymbolReference(Expression expr){
		switch(expr){
			VectorElement:{
				val e = expr.element
				switch(e){
					SymbolReference: e as SymbolReference
					default: null
				}
			}
			default: null
		}
	}


	def getStippedQuotes(String str){
		if(str.length == 2){
			// only quotes so return empty string
			""
		}
		else str.substring(1, str.length-2)
	}
}