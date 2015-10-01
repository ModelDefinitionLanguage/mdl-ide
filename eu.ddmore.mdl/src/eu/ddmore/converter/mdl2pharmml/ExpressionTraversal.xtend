package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdl.mdl.WhenClause
import eu.ddmore.mdl.mdl.ElifClause
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.SymbolReference

class ExpressionVisitor{
	def dispatch void accept(OrExpression expr) {}
	def dispatch void accept(AndExpression expr) {}
	def dispatch void accept(EqualityExpression expr) {}
	def dispatch void accept(RelationalExpression expr) {}

}

class ExpressionBuilder{
	def void orOperator(String operator) {}
	
	def andOperator(String string) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def equalityOperator(String string) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def relationalOperator(String string) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def additiveOperator(String string) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def multiplicativeOperator(String string) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def unaryOperator(String string) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def leftParenthesis() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def rightParenthesis() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}


class ExpressionTraversal {
	
	var ExpressionBuilder builder
	
    def dispatch void getSymbolReferences(Expression expr){
    	switch(expr){
    		OrExpression:{
    			expr.leftOperand.symbolReferences
    			builder.orOperator(expr.feature)
    			expr.rightOperand.symbolReferences
    		}
    		AndExpression:{
    			expr.leftOperand.symbolReferences
    			builder.andOperator(expr.feature)
    			expr.rightOperand.symbolReferences
    		}
    		EqualityExpression:{
    			expr.leftOperand.symbolReferences
    			builder.equalityOperator(expr.feature)
    			expr.rightOperand.symbolReferences
    		}
    		RelationalExpression:{
    			expr.leftOperand.symbolReferences
    			builder.relationalOperator(expr.feature)
    			expr.rightOperand.symbolReferences
    		}
    		AdditiveExpression:{
    			expr.leftOperand.symbolReferences
    			builder.additiveOperator(expr.feature)
    			expr.rightOperand.symbolReferences
    		}
    		MultiplicativeExpression:{
    			expr.leftOperand.symbolReferences
    			builder.multiplicativeOperator(expr.feature)
    			expr.rightOperand.symbolReferences
    		}
    		UnaryExpression:{
    			builder.unaryOperator(expr.feature)
    			expr.operand.symbolReferences
    		}
    		ParExpression:{
    			builder.leftParenthesis
    			expr.expr.symbolReferences
    			builder.rightParenthesis
    		}
    		WhenExpression:{
    			for(w : expr.when){
    				w.symbolReferences
    			}
    			expr.other.symbolReferences
    		}
    		WhenClause:{
				expr.value.symbolReferences    			
    		}
    		ElifClause:{
				expr.value.symbolReferences    			
    		}
    		VectorLiteral:{
    			for(v : expr.expressions){
    				v.symbolReferences
    			}
    		}
    		VectorElement:{
    			expr.element.head.symbolReferences
    		}
    		SymbolReference:{
    			expr.ref
    		}
    			
    	}
    }
    
    def dispatch void  getSymbolReferences(BuiltinFunctionCall it){
    	val a = argList
    	switch(a){
    		NamedFuncArguments:{
    			for(arg : a.arguments){
    				arg.expression.symbolReferences
    			}
    		}
    		UnnamedFuncArguments:{
    			for(arg : a.args){
    				arg.argument.symbolReferences
    			}
    		}
    	}
    }

}