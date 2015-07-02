package eu.ddmore.mdl.type

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EstimateDefinition
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.NumberLiteral
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorContent
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenClause
import eu.ddmore.mdl.mdl.WhenExpression
import org.eclipse.xtend.lib.annotations.Data

public class MclTypeProvider {
	enum PrimitiveType {
		String, Real, Bool, VarLevel, Pdf
	}
	enum TypeProperty{
		Deriv, Vector, DataCol, Estimate, None
	}
	
	@Data
	static class TypeInfo{
		PrimitiveType theType
		TypeProperty typeProp 
	}
	
	static val ep = MdlPackage::eINSTANCE 
	
	static val typeTable = #{
		ep.numberLiteral -> new TypeInfo(PrimitiveType.Real, TypeProperty.None),
		ep.stringLiteral -> new TypeInfo(PrimitiveType.String, TypeProperty.None),
		ep.booleanLiteral -> new TypeInfo(PrimitiveType.Bool, TypeProperty.None),
		ep.vectorLiteral -> new TypeInfo(PrimitiveType.Real, TypeProperty.Vector),
		ep.equationDefinition -> new TypeInfo(PrimitiveType.Real, TypeProperty.None),
		ep.transformedDefinition -> new TypeInfo(PrimitiveType.Real, TypeProperty.None),
		ep.estimateDefinition -> new TypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		ep.randomVariableDefinition -> new TypeInfo(PrimitiveType.Real, TypeProperty.None)
	}
	
	static val functionTypeTable = #{
		'log' -> new TypeInfo(PrimitiveType.Real, TypeProperty.None),
		'abs' -> new TypeInfo(PrimitiveType.Real, TypeProperty.None),
		'exp' -> new TypeInfo(PrimitiveType.Real, TypeProperty.None),
		'Normal' -> new TypeInfo(PrimitiveType.Pdf, TypeProperty.None)
	}
	
	
	def TypeInfo typeFor(Expression e){
		switch(e){
			NumberLiteral,
			StringLiteral,
			BooleanLiteral:
				typeTable.get(e.eClass)  
			SymbolReference: e.ref.typeFor
		}
	}
	
	
	def typeFor(SymbolDefinition sd){
		switch(sd){
			EquationDefinition,
			TransformedDefinition,
			EstimateDefinition,
			RandomVariableDefinition: typeTable.get(sd.eClass)
		}
	}
	
	def PrimitiveType evalMathsOp(PrimitiveType lhs, PrimitiveType rhs){
		if(lhs == null || rhs == null) null
		else if(lhs != null){
			if(rhs != null && lhs != rhs) null
			else if(lhs == PrimitiveType.Real) lhs 
		}
	}
	
	def PrimitiveType evalBoolOp(PrimitiveType lhs, PrimitiveType rhs){
		if(lhs == null || rhs == null) null
		else if(lhs != null){
			if(rhs != null && lhs != rhs) null
			else if(lhs == PrimitiveType.Bool) lhs
		}
	}
	
	def PrimitiveType evalRelationalOp(PrimitiveType lhs, PrimitiveType rhs){
		if(lhs == null || rhs == null) null
		else if(lhs != null){
			if(rhs != null && lhs != rhs) null
			else if(lhs == PrimitiveType.Real) PrimitiveType.Bool
		}
	}
	
	def dispatch PrimitiveType evalTypeFor(Expression e){
		switch(e){
			NumberLiteral,
			StringLiteral,
			BooleanLiteral,
			VectorLiteral:
				e.typeFor.theType
			SymbolReference:
				// use the defined type to avoid recursing into all the symbol defns 
				e.ref.typeFor.theType
			default:
				e.evalTypeFor
		}
		
	}
	
	def dispatch PrimitiveType evalTypeFor(OrExpression e){
		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
		else e.leftOperand.evalTypeFor.evalBoolOp(e.rightOperand.evalTypeFor)
	}
	
	def dispatch PrimitiveType evalTypeFor(AndExpression e){
		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
		else e.leftOperand.evalTypeFor.evalBoolOp(e.rightOperand.evalTypeFor)
	}
	
	def dispatch PrimitiveType evalTypeFor(EqualityExpression e){
		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
		else e.leftOperand.evalTypeFor.evalRelationalOp(e.rightOperand.evalTypeFor)
	}
	
	def dispatch PrimitiveType evalTypeFor(RelationalExpression e){
		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
		else e.leftOperand.evalTypeFor.evalRelationalOp(e.rightOperand.evalTypeFor)
	}
	
	def dispatch PrimitiveType evalTypeFor(AdditiveExpression e){
		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
		else e.leftOperand.evalTypeFor.evalMathsOp(e.rightOperand.evalTypeFor)
	}
	
	def dispatch PrimitiveType evalTypeFor(MultiplicativeExpression e){
		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
		else e.leftOperand.evalTypeFor.evalMathsOp(e.rightOperand.evalTypeFor)
	}
	
	def dispatch PrimitiveType evalTypeFor(UnaryExpression e){
		if(e.feature == '!') PrimitiveType.Bool.evalBoolOp(e.operand.evalTypeFor)
		else PrimitiveType.Real.evalBoolOp(e.operand.evalTypeFor)
	}
	
	def dispatch PrimitiveType evalTypeFor(ParExpression e){
		e.expr.evalTypeFor
	}
	
	def dispatch PrimitiveType evalTypeFor(WhenExpression e){
		val nonReal = e.when.exists[wc| 
				val type=wc.evalTypeFor
				type != PrimitiveType.Real
		] 
		if(nonReal)
			null
		else if(e.other == null)
			PrimitiveType.Real
		else if(e.other.evalTypeFor ==  PrimitiveType.Real)
			PrimitiveType.Real
		else
			null
	}

	def dispatch PrimitiveType evalTypeFor(WhenClause e){
		val condTest = e.cond.evalTypeFor
		val valTest = e.value.evalTypeFor
		if(condTest == PrimitiveType.Bool && valTest == PrimitiveType.Real)
			PrimitiveType.Real
		else null 
	}
	
	def dispatch PrimitiveType evalTypeFor(BuiltinFunctionCall e){
		if(e.argList != null && e.argList.evalTypeFor == null) null
		else functionTypeTable.get(e.func).theType
	}
	
	def dispatch PrimitiveType evalTypeFor(UnnamedFuncArguments e){
		if(e.args.exists[arg| arg.evalTypeFor != PrimitiveType.Real]) null
		else PrimitiveType.Real
	}
	
	def dispatch PrimitiveType evalTypeFor(NamedFuncArguments e){
		// TODO: named argument checking needed
		PrimitiveType.Real
	}
	
	def dispatch PrimitiveType evalTypeFor(VectorLiteral e){
		if(e.expression.evalTypeFor == PrimitiveType.Real) PrimitiveType.Real
		else null
	}
	
	def dispatch PrimitiveType evalTypeFor(VectorContent e){
		if(e.expressions.exists[ve| ve.evalTypeFor != PrimitiveType.Real]) null
		else PrimitiveType.Real
	}
	
	
}