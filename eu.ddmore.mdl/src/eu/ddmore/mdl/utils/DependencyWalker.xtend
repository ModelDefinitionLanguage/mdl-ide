package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.IndexRange
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.PowerExpression
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.Collections
import java.util.List

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
    			retVal.addAll(expr.leftOperand?.symbolReferences ?: Collections::emptyList)
    			retVal.addAll(expr.rightOperand?.symbolReferences ?: Collections::emptyList)
    		}
    		AndExpression:{
    			retVal.addAll(expr.leftOperand?.symbolReferences ?: Collections::emptyList)
    			retVal.addAll(expr.rightOperand?.symbolReferences ?: Collections::emptyList)
    		}
    		EqualityExpression:{
    			retVal.addAll(expr.leftOperand?.symbolReferences  ?: Collections::emptyList)
    			retVal.addAll(expr.rightOperand?.symbolReferences ?: Collections::emptyList)
    		}
    		RelationalExpression:{
    			retVal.addAll(expr.leftOperand?.symbolReferences ?: Collections::emptyList)
    			retVal.addAll(expr.rightOperand?.symbolReferences ?: Collections::emptyList)
    		}
    		AdditiveExpression:{
    			retVal.addAll(expr.leftOperand?.symbolReferences ?: Collections::emptyList)
    			retVal.addAll(expr.rightOperand?.symbolReferences  ?: Collections::emptyList)
    		}
    		MultiplicativeExpression:{
    			retVal.addAll(expr.leftOperand?.symbolReferences ?: Collections::emptyList)
    			retVal.addAll(expr.rightOperand?.symbolReferences ?: Collections::emptyList)
    		}
    		PowerExpression:{
    			retVal.addAll(expr.leftOperand?.symbolReferences ?: Collections::emptyList)
    			retVal.addAll(expr.rightOperand?.symbolReferences ?: Collections::emptyList)
    		}
    		UnaryExpression:{
    			retVal.addAll(expr.operand?.symbolReferences ?: Collections::emptyList)
    		}
    		ParExpression:{
    			retVal.addAll(expr.expr?.symbolReferences ?: Collections::emptyList)
    		}
    		WhenExpression:{
    			for(w : expr.when){
    				retVal.addAll(w.symbolReferences)
    			}
    			if(expr.other != null)
    				retVal.addAll(expr.other?.other?.symbolReferences ?: Collections::emptyList)
    		}
    		VectorLiteral:{
    			for(v : expr.expressions){
    				retVal.addAll(v.symbolReferences)
    			}
    		}
    		VectorElement:{
    			retVal.addAll(expr.element?.symbolReferences ?: Collections::emptyList)
    		}
    		SymbolReference:{
//    			if(expr.ref != null) retVal.add(expr.ref)
    			if(expr.ref != null) retVal.addAll(expr.ref.symbolReferences)
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
    
    def dispatch List<SymbolDefinition> getSymbolReferences(SymbolReference it){
    	val retVal = new ArrayList<SymbolDefinition>
    	retVal.add(ref)
    	if(argList != null){
	    	val a = argList
	    	switch(a){
	    		NamedFuncArguments:{
	    			for(arg : a.arguments){
	    				retVal.addAll(arg.expression?.symbolReferences ?: Collections::emptyList)
	    			}
	    		}
	    		UnnamedFuncArguments:{
	    			for(arg : a.args){
	    				retVal.addAll(arg.argument?.symbolReferences ?: Collections::emptyList)
	    			}
	    		}
	    	}
    	}
    	if(indexExpr != null){
    		retVal.addAll(indexExpr.rowIdx?.defsFromIndex ?: Collections::emptyList)
    		retVal.addAll(indexExpr.colIdx?.defsFromIndex ?: Collections::emptyList)
    	}
    	retVal
    }
    
    def private getDefsFromIndex(IndexRange it){
    	val retVal = new ArrayList<SymbolDefinition>
   		retVal.addAll(begin.symbolReferences ?: Collections::emptyList)
   		retVal.addAll(end?.symbolReferences ?: Collections::emptyList)
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