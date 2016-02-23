package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.ConstantLiteral
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.PowerExpression
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdllib.mdllib.Expression

class ConstantEvaluation {
	
	extension TypeSystemProvider mtp = new TypeSystemProvider
	
	def Double evaluateMathsExpression(Expression expr){
		if(expr != null && TypeSystemProvider::REAL_TYPE.isCompatible(expr.typeFor)){
			switch(expr){
	    		AdditiveExpression:{
	    			getAdditiveExpression(expr)
	    		}
	    		MultiplicativeExpression:{
	    			getMultiplicativeExpression(expr)
	    		}
	    		PowerExpression:{
	    			getPowerExpression(expr)
	    		}
	    		UnaryExpression:{
					evaluateUnaryMathsOp(expr.feature, expr.operand)
	    		}
	    		ParExpression:{
	   				expr.expr.evaluateMathsExpression
	    		}
	    		WhenExpression:{
	    			getWhenExpression(expr)
	    		}
	    		RealLiteral:{
	    			getRealLiteral(expr)
	    		}
	    		ConstantLiteral:{
	    			getConstantLiteral(expr)
	    		}
	    		IntegerLiteral:
	    			getIntegerLiteral(expr)
	    		SymbolReference:{
	    			getSymbolReference(expr)
	    		}
	    		default:
	    			null
			}
		}
		else null
	} 
	
	
	def Boolean evaluateLogicalExpression(Expression expr){
		if(expr != null && TypeSystemProvider::BOOLEAN_TYPE.isCompatible(expr.typeFor)){
			switch(expr){
    			OrExpression:
    				getOrExpression(expr)
	    		AndExpression:
	    			getAndExpression(expr)
	    		RelationalExpression:
	    			getRelationalExpression(expr)
	    		EqualityExpression:
	    			getEqualityExpression(expr)
	    		UnaryExpression:{
					evaluateUnaryLogicOp(expr.feature, expr.operand)
	    		}
	    		ParExpression:{
	   				expr.expr.evaluateLogicalExpression
	    		}
	    		BooleanLiteral:{
	    			getBooleanLiteral(expr)
	    		}
	    		default: null
	    	}
    	}
    	else null
	} 
	
	def getSymbolReference(SymbolReference it){
		val symbDefn = ref
		switch(symbDefn){
			EquationDefinition:
				symbDefn.expression.evaluateMathsExpression
			default:
				null
		}
	}
	
	
	def Boolean evaluateUnaryLogicOp(String operator, Expression operand){
		switch(operator){
			case '!':
				! evaluateLogicalExpression(operand)
			default:
				null
		}
	}
	
	def Double evaluateUnaryMathsOp(String operator, Expression operand){
		switch(operator){
			case '+':
				evaluateMathsExpression(operand)
			case '-':
				- evaluateMathsExpression(operand)
			default:
				null
		}
	}
	
	
	def evaluateBinaryBooleanOp(String operator, Expression lhs, Expression rhs){
		val lhsResult = lhs?.evaluateLogicalExpression
		val rhsResult = rhs?.evaluateLogicalExpression
		if(lhs != null && rhs != null){
			switch(operator){
				case '||':
					lhsResult || rhsResult
				case '&&':
					lhsResult && rhsResult
				default:
					null
			}
		}
		else null
	}
	
	def evaluateBinaryRelationalOp(String operator, Expression lhs, Expression rhs){
		val lhsResult = lhs?.evaluateMathsExpression
		val rhsResult = rhs?.evaluateMathsExpression
		if(lhs != null && rhs != null){
			switch(operator){
				case '<':
					lhsResult < rhsResult
				case '<=':
					lhsResult <= rhsResult
				case '>':
					lhsResult > rhsResult
				case '>=':
					lhsResult >= rhsResult
				case '==':
					lhsResult == rhsResult
				case '!=':
					lhsResult != rhsResult
				default:
					null
			}
		}
		else null
	}
	
	def evaluateBinaryLogicalOp(String operator, Expression lhs, Expression rhs){
		if(lhs != null && rhs != null){
			switch(operator){
				case '||',
				case '&&':
					evaluateBinaryBooleanOp(operator, lhs, rhs)
				case '<',
				case '<=',
				case '>',
				case '>=',
				case '==',
				case '!=':
					evaluateBinaryRelationalOp(operator, lhs, rhs)
				default:
					null
			}
		}
		else null
	}
	
	def evaluateBinaryMathsOp(String operator, Expression lhs, Expression rhs){
		val lhsResult = lhs?.evaluateMathsExpression
		val rhsResult = rhs?.evaluateMathsExpression
		if(lhsResult != null && rhsResult != null){
			switch(operator){
				case '+':
					lhsResult + rhsResult 
				case '-':
					lhsResult - rhsResult
				case '/':
					lhsResult / rhsResult
				case '*':
					lhsResult * rhsResult
				case '%':
					lhsResult % rhsResult
				case '^':{
					Math.pow(lhsResult, rhsResult)
				}
				
				default:
					null
			}
		}
		else null
	}
	
	
	def getMultiplicativeExpression(MultiplicativeExpression it){
		evaluateBinaryMathsOp(feature, leftOperand, rightOperand)
	}
	
	def getPowerExpression(PowerExpression it){
		evaluateBinaryMathsOp(feature, leftOperand, rightOperand)
	}
	
	def getAdditiveExpression(AdditiveExpression it){
		evaluateBinaryMathsOp(feature, leftOperand, rightOperand)
	}
	
	def getRelationalExpression(RelationalExpression it){
		evaluateBinaryLogicalOp(feature, leftOperand, rightOperand)
	}
	
	def getEqualityExpression(EqualityExpression it){
		evaluateBinaryLogicalOp(feature, leftOperand, rightOperand)
	}
	
	def getAndExpression(AndExpression it){
		evaluateBinaryLogicalOp(feature, leftOperand, rightOperand)
	}
	
	def getOrExpression(OrExpression it){
		evaluateBinaryLogicalOp(feature, leftOperand, rightOperand)
	}
		
	def getWhenExpression(WhenExpression it){
		for( w : when){
			if(w instanceof IfExprPart){
				if(w.cond.evaluateLogicalExpression){
					return w.value.evaluateMathsExpression
				}
			}
		}
		return other.other.evaluateMathsExpression
	}
	
	def getStringLiteral(StringLiteral it){
		value
	}
	
	def getEnumExpression(EnumExpression it){
		it.enumValue
	}
	
	def getIntegerLiteral(IntegerLiteral it){
		value.doubleValue
	}
	
	def getConstantLiteral(ConstantLiteral it) {
		switch(value){
			case("inf"): Double.POSITIVE_INFINITY
			case("exponentiale"): Math.E
			case("pi") : Math.PI
			default:
				null	
		}
	}
	
	def getRealLiteral(RealLiteral it){
		value
	}
	
	def getBooleanLiteral(BooleanLiteral it){
		isTrue
	}
    
}