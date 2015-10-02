package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.ConstantLiteral
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenExpression
import static eu.ddmore.converter.mdl2pharmml.Constants.*

class PharmMLExpressionBuilder {
	
	def getSymbolReference(SymbolReference it)'''
		<ct:SymbRef symbIdRef="«ref.name»"/>
	'''
	
	def getExpressionAsEquation(Expression it)'''
		<Equation xmlns="«xmlns_math»">
			«pharmMLExpr»
		</Equation>
	'''
	
    def dispatch CharSequence getPharmMLExpr(Expression expr){
    	switch(expr){
    		OrExpression:
    			getOrExpression(expr)
    		AndExpression:
    			getAndExpression(expr)
    		EqualityExpression:
    			getEqualityExpression(expr)
    		RelationalExpression:{
    			getRelationalExpression(expr)
    		}
    		AdditiveExpression:{
    			getAdditiveExpression(expr)
    		}
    		MultiplicativeExpression:{
    			getMultiplicativeExpression(expr)
    		}
    		UnaryExpression:{
    			getUnaryExpression(expr)
    		}
    		ParExpression:{
    			getParExpression(expr)
    		}
    		WhenExpression:{
    			expr.getWhenExpression
    		}
    		VectorLiteral:{
    			getVectorLiteralExpression(expr)
    		}
    		VectorElement:{
    			expr.element.pharmMLExpr
    		}
    		BooleanLiteral:{
    			getBooleanLiteral(expr)
    		}
    		RealLiteral:{
    			getRealLiteral(expr)
    		}
    		ConstantLiteral:{
    			getConstantLiteral(expr)
    		}
    		IntegerLiteral:
    			getIntegerLiteral(expr)
    		StringLiteral:
    			getStringLiteral(expr)
    		SymbolReference:{
    			getSymbolReference(expr)
    		}
    			
    	}
    }
	
	def getPharmMLBinop(String operator){
		switch(operator){
			case '+': 'plus'
			case '-': 'minus'
			case '*': 'times'
			case '/': 'divide'
			case '^': 'power'
			case '||': 'or'
			case '&&': 'and'
			case '<': 'lt'
			case '<=': 'le'
			case '>': 'gt'
			case '>=': 'ge'
			case '==': 'eq'
			case '!=': 'ne'
			case '%': 'rem'
		}
	}
	
	def getPharmMLUniop(String operator){
		switch(operator){
			case '+': 'plus'
			case '-': 'minus'
			case '!': 'not'
		}
	}
	
	def isLogicOp(String operator){
		switch(operator){
			case '||',
			case '&&',
			case '<',
			case '<=',
			case '>',
			case '>=',
			case '==',
			case '!=',
			case '%',
			case '!': true
			default: false
		}
	}
	
	def getVectorLiteralExpression(VectorLiteral it)'''
		<ct:Vector>
			<ct:VectorElements>
				«FOR e : expressions»
					«e.pharmMLExpr»
				«ENDFOR»
			</ct:VectorElements>
		</ct:Vector>
	'''
	
	def getParExpression(ParExpression it)'''
		«expr.pharmMLExpr»
	'''
	
	def getUnaryExpression(UnaryExpression it)'''
		«IF feature.isLogicOp»
			<math:LogicUniop op=«feature.pharmMLUniop»>
		«ELSE»
			<math:Uniop op=«feature.pharmMLUniop»>
		«ENDIF»
			«operand.pharmMLExpr»
		«IF feature.isLogicOp»
			</math:LogicUniop>
		«ELSE»
			<math:Uniop>
		«ENDIF»
	'''
	
	def getMultiplicativeExpression(MultiplicativeExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getAdditiveExpression(AdditiveExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getRelationalExpression(RelationalExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getEqualityExpression(EqualityExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getAndExpression(AndExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getOrExpression(OrExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
		
	def getBinaryOperator(String feature, Expression leftOperand, Expression rightOperand)'''
		«IF feature.isLogicOp»
			<math:LogicBinop op=«feature.pharmMLBinop»>
		«ELSE»
			<math:Binop op=«feature.pharmMLBinop»>
		«ENDIF»
			«leftOperand.pharmMLExpr»
			«rightOperand.pharmMLExpr»
		«IF feature.isLogicOp»
			</math:LogicBinop>
		«ELSE»
			<math:Binop>
		«ENDIF»
	'''
	
	def getWhenExpression(WhenExpression it)'''
		<math:Piecewise>
			«FOR w : when»
				«w.whenClause»
			«ENDFOR»
			«IF other != null»
				<math:Piece>
					«other.pharmMLExpr»
					<math:Condition>
						<math:Otherwise/>
					</math:Condition>
				</math:Piece>
			«ENDIF»
		</math:Piecewise>
	'''
	
	def getWhenClause(IfExprPart it)'''
		<math:Piece>
			«value.pharmMLExpr»
			<math:Condition>
				«cond.pharmMLExpr»
			</math:Condition>
		</math:Piece>
	'''
	
	def getStringLiteral(StringLiteral it) '''
		<ct:String>«value»</ct:String>
	'''
	
	def getIntegerLiteral(IntegerLiteral it) '''
		<ct:Int>«value»</ct:Int>
	'''
	
	def getConstantLiteral(ConstantLiteral it) {
		val constType = switch(value){
			case("INF"): "infinity"
			default:
				"error:NotDefined"	
		}
		'''
		<math:Constant op=«constType»/>
		'''
	}
	
	def getRealLiteral(RealLiteral it)'''
		<ct:Real>«value»</ct:Real>
	'''
	
	def getBooleanLiteral(BooleanLiteral it)'''
		«IF isTrue» <ct:True/>
		«ELSE» <ct:False/>
		«ENDIF»
	'''
    
    def dispatch CharSequence getPharmMLExpr(BuiltinFunctionCall it){
    	var retVal = ''''''
    	val a = argList
    	switch(a){
    		NamedFuncArguments:
    			retVal += '''
						<math:FunctionCall>
							«func.localSymbolReference»
							«a.namedArguments»
						</math:FunctionCall>
    			''' 
    			
    		UnnamedFuncArguments:{
    			val opType = if(a.args.length == 1) "Binop" else "Uniop"
    			retVal += '''
    					<math:«opType» op="«func»">
    						«a.unnamedArguments»
    					<math:«opType»/>	
    					'''
    		}
    	}
    	retVal
    }

	
	def getNamedArguments(NamedFuncArguments it)'''
		«FOR arg : arguments»
			<math:FunctionArgument symbId="«arg.argumentName»">
				«arg.expression.pharmMLExpr»
			</math:FunctionArgument>
		«ENDFOR»
	'''
	
	def getUnnamedArguments(UnnamedFuncArguments it)'''
		«FOR arg : args»
			«arg.argument.pharmMLExpr»
		«ENDFOR»
	'''
	
	def getLocalSymbolReference(String v) '''
		<ct:SymbRef symbId="«v»""/>
	'''
}