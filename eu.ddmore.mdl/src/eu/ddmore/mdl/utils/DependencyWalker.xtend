package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.PowerExpression
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenExpression
import java.util.ArrayList
import java.util.Collections
import java.util.List
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MappingPair

class DependencyWalker {

	def dispatch List<SymbolDefinition> getSymbolReferences(SymbolDefinition it){
		val sd = it
		switch(sd){
			EquationTypeDefinition:
				sd.expression?.symbolReferences ?: Collections::emptyList 
			RandomVariableDefinition:
				sd.distn?.symbolReferences ?: Collections::emptyList
			ListDefinition:
				sd.list?.symbolReferences ?: Collections::emptyList
			default: Collections::emptyList
		}
	}

    def dispatch List<SymbolDefinition> getSymbolReferences(Expression expr){
    	val retVal = new ArrayList<SymbolDefinition>
    	switch(expr){
    		OrExpression:{
    			retVal.addAll(expr.leftOperand.symbolReferences)
    			retVal.addAll(expr.rightOperand.symbolReferences)
    		}
    		AndExpression:{
    			retVal.addAll(expr.leftOperand.symbolReferences)
    			retVal.addAll(expr.rightOperand.symbolReferences)
    		}
    		EqualityExpression:{
    			retVal.addAll(expr.leftOperand.symbolReferences)
    			retVal.addAll(expr.rightOperand.symbolReferences)
    		}
    		RelationalExpression:{
    			retVal.addAll(expr.leftOperand.symbolReferences)
    			retVal.addAll(expr.rightOperand.symbolReferences)
    		}
    		AdditiveExpression:{
    			retVal.addAll(expr.leftOperand.symbolReferences)
    			retVal.addAll(expr.rightOperand.symbolReferences)
    		}
    		MultiplicativeExpression:{
    			retVal.addAll(expr.leftOperand.symbolReferences)
    			retVal.addAll(expr.rightOperand.symbolReferences)
    		}
    		PowerExpression:{
    			retVal.addAll(expr.leftOperand.symbolReferences)
    			retVal.addAll(expr.rightOperand.symbolReferences)
    		}
    		UnaryExpression:{
    			retVal.addAll(expr.operand.symbolReferences)
    		}
    		ParExpression:{
    			retVal.addAll(expr.expr.symbolReferences)
    		}
    		WhenExpression:{
    			for(w : expr.when){
    				retVal.addAll(w.symbolReferences)
    			}
    			if(expr.other != null)
    				retVal.addAll(expr.other.other.symbolReferences)
    		}
    		VectorLiteral:{
    			for(v : expr.expressions){
    				retVal.addAll(v.symbolReferences)
    			}
    		}
    		VectorElement:{
    			retVal.addAll(expr.element.symbolReferences)
    		}
    		SymbolReference:{
    			retVal.add(expr.ref)
    		}
    			
    	}
    	retVal
    }
    
    
    def dispatch List<SymbolDefinition> getSymbolReferences(IfExprPart it){
    	val retVal = new ArrayList<SymbolDefinition>
    	retVal.addAll(cond.symbolReferences)
		retVal.addAll(value.symbolReferences)
		retVal    			
    }
    
    def dispatch List<SymbolDefinition> getSymbolReferences(SubListExpression it){
    	val retVal = new ArrayList<SymbolDefinition>
		for(att : attributes){
			retVal.addAll(att.expression?.symbolReferences ?: Collections::emptyList)
		}
		retVal
    }
    
    def dispatch List<SymbolDefinition> getSymbolReferences(BuiltinFunctionCall it){
    	val retVal = new ArrayList<SymbolDefinition>
    	val a = argList
    	switch(a){
    		NamedFuncArguments:{
    			for(arg : a.arguments){
    				retVal.addAll(arg.expression.symbolReferences)
    			}
    		}
    		UnnamedFuncArguments:{
    			for(arg : a.args){
    				retVal.addAll(arg.argument.symbolReferences)
    			}
    		}
    	}
    	retVal
    }
    
    def dispatch List<SymbolDefinition> getSymbolReferences(MappingExpression it){
    	val retVal = new ArrayList<SymbolDefinition>
		for(att : it.attList){
			retVal.addAll(att.symbolReferences)
		}
		retVal
	}
    
    def dispatch List<SymbolDefinition> getSymbolReferences(MappingPair it){
    	val retVal = new ArrayList<SymbolDefinition>
    	retVal.addAll(leftOperand?.symbolReferences ?: Collections::emptyList)
    	retVal.addAll(srcColumn?.symbolReferences ?: Collections::emptyList)
    	retVal.addAll(rightOperand?.symbolReferences ?: Collections::emptyList)
		retVal
	}
    

	def dispatch List<SymbolDefinition> getSymbolReferences(AttributeList it){
    	val retVal = new ArrayList<SymbolDefinition>
		for(att : attributes){
			retVal.addAll(att.expression?.symbolReferences ?: Collections::emptyList)
		}
		retVal
	}

}