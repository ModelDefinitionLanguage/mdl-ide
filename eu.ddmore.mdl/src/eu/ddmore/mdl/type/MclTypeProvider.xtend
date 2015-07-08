package eu.ddmore.mdl.type

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoryDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ForwardDeclaration
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnaryExpression
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

public class MclTypeProvider {

	enum PrimitiveType {
		String, Real, Boolean, VarLevel, Pdf, Undefined
	}
	enum TypeProperty{
		Deriv, Vector, DataCol, Estimate, None
	}
	
	@Data @FinalFieldsConstructor
	static class TypeInfo{
		PrimitiveType theType
		TypeProperty typeProp
		boolean isReference
		
		new(PrimitiveType theType, TypeProperty typeProp){
			this.theType = theType
			this.typeProp = typeProp
			this.isReference = true
		}
	}

	def TypeInfo markReference(TypeInfo t){
		new TypeInfo(t.theType, t.typeProp, true)
	}

	public static val UNDEFINED_TYPE = new TypeInfo(PrimitiveType.Undefined, TypeProperty.None)
	public static val REAL_TYPE = new TypeInfo(PrimitiveType.Real, TypeProperty.None)
	public static val STRING_TYPE = new TypeInfo(PrimitiveType.String, TypeProperty.None)
	public static val BOOL_TYPE = new TypeInfo(PrimitiveType.Boolean, TypeProperty.None)
	public static val PDF_TYPE = new TypeInfo(PrimitiveType.Pdf, TypeProperty.None)
	public static val VAR_LEVEL_TYPE = new TypeInfo(PrimitiveType.VarLevel, TypeProperty.None)
	
	
	static val ep = MdlPackage::eINSTANCE 
	
	static val typeTable = #{
		ep.numberLiteral -> REAL_TYPE,
		ep.stringLiteral -> STRING_TYPE,
		ep.booleanLiteral -> BOOL_TYPE,
		ep.vectorLiteral -> new TypeInfo(PrimitiveType.Real, TypeProperty.Vector),
		ep.whenExpression -> REAL_TYPE,
		ep.whenClause -> REAL_TYPE,
		ep.relationalExpression -> BOOL_TYPE,
		ep.equalityExpression -> BOOL_TYPE,
		ep.andExpression -> BOOL_TYPE,
		ep.orExpression -> BOOL_TYPE,
		ep.additiveExpression -> REAL_TYPE,
		ep.multiplicativeExpression -> REAL_TYPE,
		ep.categoryDefinition -> REAL_TYPE, // @TODO: Do category typing properly
		
		ep.estimateRange -> new TypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		ep.limit -> new TypeInfo(PrimitiveType.Real, TypeProperty.Estimate),
		
		ep.equationDefinition -> REAL_TYPE,
		ep.transformedDefinition -> REAL_TYPE,
		ep.randomVariableDefinition -> REAL_TYPE,
		ep.forwardDeclaration -> REAL_TYPE,
		ep.listDefinition -> REAL_TYPE // @TODO: Do list typing properly
	}
	
	
	static val functionTypeTable = #{
		'log' -> REAL_TYPE,
		'abs' -> REAL_TYPE,
		'exp' -> REAL_TYPE,
		'Normal' -> PDF_TYPE,
		'linear' -> REAL_TYPE,
		'general' -> REAL_TYPE,
		'combinedError1' -> REAL_TYPE,
		'combinedError2' -> REAL_TYPE
	}
	
	
	def dispatch TypeInfo typeFor(Expression e){
		switch(e){
			SymbolReference: e.ref.typeFor.markReference
			ParExpression: e.expr.typeFor
			BuiltinFunctionCall:
				functionTypeTable.get(e.func) ?: MclTypeProvider.UNDEFINED_TYPE
			default:
				typeTable.get(e.eClass) ?: MclTypeProvider.UNDEFINED_TYPE
		}
	}
	
	def dispatch TypeInfo typeFor(UnaryExpression exp){
		switch(exp.feature){
			case('!'): BOOL_TYPE
			case('+'),
			case('-'): REAL_TYPE
			default:
				throw new RuntimeException("Unrecognised unary operator.")
		}
	}
	
	def dispatch TypeInfo typeFor(SymbolDefinition sd){
		switch(sd){
			EquationDefinition,
			TransformedDefinition,
			ForwardDeclaration,
			ListDefinition,
			CategoryDefinition,
			RandomVariableDefinition: typeTable.get(sd.eClass)
			default:
				UNDEFINED_TYPE
		}
	}
	
	def checkExpectedBoolean(Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		checkExpectedAndExpression(typeTable.get(MdlPackage::eINSTANCE.booleanLiteral).theType, exp, errorLambda)
//		checkExpectedType(exp, typeTable.get(MdlPackage::eINSTANCE.booleanLiteral).theType, errorLambda)
	}
	
	def checkExpectedReal(Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		checkExpectedAndExpression(typeTable.get(MdlPackage::eINSTANCE.numberLiteral).theType, exp, errorLambda)
//		checkExpectedType(exp, typeTable.get(MdlPackage::eINSTANCE.numberLiteral).theType, errorLambda)
	}
	
	def checkExpectedPdf(Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		checkExpectedAndExpression(PDF_TYPE.theType, exp, errorLambda)
	}

	def checkExpectedRealTransform(String transform, (PrimitiveType, PrimitiveType) => void errorLambda){
		val actualType = functionTypeTable.get(transform).theType ?: UNDEFINED_TYPE.theType
		val expectedType = PrimitiveType.Real
		if(actualType != expectedType)
			errorLambda.apply(expectedType, actualType ?: UNDEFINED_TYPE.theType)
	}
	
	def checkExpectedString(Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		checkExpectedAndExpression(typeTable.get(MdlPackage::eINSTANCE.stringLiteral).theType, exp, errorLambda)
//		checkExpectedType(exp, typeTable.get(MdlPackage::eINSTANCE.stringLiteral).theType, errorLambda)
	}
	
//	def checkExpectedType(Expression exp, PrimitiveType expectedType, (PrimitiveType, PrimitiveType) => void errorLambda) {
//		val actualType = exp?.typeFor.theType
//		if(actualType != expectedType)
//			errorLambda.apply(expectedType, actualType)
//	}
	
	def checkRelationalOp(Expression lhs, Expression rhs, (PrimitiveType, PrimitiveType) => void leftErrorLambda,
		(PrimitiveType, PrimitiveType) => void rightErrorLambda){
		checkExpectedAndExpression(PrimitiveType.Real, lhs, leftErrorLambda)
		checkExpectedAndExpression(PrimitiveType.Real, rhs, rightErrorLambda)
//		if(lhs == null || rhs == null) return
//		val lhsType = lhs.typeFor.theType
//		val rhsType = rhs.typeFor.theType
//		if(lhsType != PrimitiveType.Real)
//			leftErrorLambda.apply(PrimitiveType.Real, lhsType)
//		if(rhsType != PrimitiveType.Real)
//			rightErrorLambda.apply(PrimitiveType.Real, rhsType)
	}

	def checkMathsOp(Expression lhs, Expression rhs, (PrimitiveType, PrimitiveType) => void leftErrorLambda,
		(PrimitiveType, PrimitiveType) => void rightErrorLambda){
		checkExpectedAndExpression(PrimitiveType.Real, lhs, leftErrorLambda)
		checkExpectedAndExpression(PrimitiveType.Real, rhs, rightErrorLambda)
//		if(lhs == null || rhs == null) return
//		val lhsType = lhs.typeFor.theType
//		val rhsType = rhs.typeFor.theType
//		if(lhsType != PrimitiveType.Real)
//			leftErrorLambda.apply(PrimitiveType.Real, lhsType)
//		if(rhsType != PrimitiveType.Real)
//			rightErrorLambda.apply(PrimitiveType.Real, rhsType)
	}

	def checkBoolOp(Expression lhs, Expression rhs, (PrimitiveType, PrimitiveType) => void leftErrorLambda,
				(PrimitiveType, PrimitiveType) => void rightErrorLambda){
		checkExpectedAndExpression(PrimitiveType.Boolean, lhs, leftErrorLambda)
		checkExpectedAndExpression(PrimitiveType.Boolean, rhs, rightErrorLambda)
//		if(lhs == null || rhs == null) return
//		val lhsType = lhs.typeFor.theType
//		val rhsType = rhs.typeFor.theType
//		if(lhsType != PrimitiveType.Real)
//			leftErrorLambda.apply(PrimitiveType.Boolean, lhsType)
//		if(rhsType != PrimitiveType.Real)
//			rightErrorLambda.apply(PrimitiveType.Boolean, rhsType)
	}

	def checkUnaryOp(String feature, Expression operand, (PrimitiveType, PrimitiveType) => void errorLambda){
		if(feature == '!') checkExpectedAndExpression(PrimitiveType.Boolean, operand, errorLambda)
		else checkExpectedAndExpression(PrimitiveType.Real, operand, errorLambda)
	}
	
	
	def void checkExpectedAndExpression(PrimitiveType expectedType, Expression exp, (PrimitiveType, PrimitiveType) => void errorLambda){
		val actualType = exp?.typeFor?.theType ?: UNDEFINED_TYPE.theType
		if(actualType != expectedType)
			errorLambda.apply(expectedType, actualType ?: PrimitiveType.Undefined)
	}
	
//	def PrimitiveType evalMathsOp(PrimitiveType lhs, PrimitiveType rhs){
//		if(lhs == null || rhs == null) null
//		else if(lhs != null){
//			if(rhs != null && lhs != rhs) null
//			else if(lhs == PrimitiveType.Real) lhs 
//		}
//	}
//	
//	def PrimitiveType evalBoolOp(PrimitiveType lhs, PrimitiveType rhs){
//		if(lhs == null || rhs == null) null
//		else if(lhs != null){
//			if(rhs != null && lhs != rhs) null
//			else if(lhs == PrimitiveType.Boolean) lhs
//		}
//	}
//	
//	def PrimitiveType evalRelationalOp(PrimitiveType lhs, PrimitiveType rhs){
//		if(lhs == null || rhs == null) null
//		else if(lhs != null){
//			if(rhs != null && lhs != rhs) null
//			else if(lhs == PrimitiveType.Real) PrimitiveType.Boolean
//		}
//	}
	
//	def dispatch PrimitiveType evalTypeFor(Expression e){
//		switch(e){
//			NumberLiteral,
//			StringLiteral,
//			BooleanLiteral,
//			VectorLiteral:
//				e.typeFor.theType
//			SymbolReference:
//				// use the defined type to avoid recursing into all the symbol defns 
//				e.ref.typeFor.theType
//			default:
//				e.evalTypeFor
//		}
//		
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(OrExpression e){
//		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
//		else e.leftOperand.evalTypeFor.evalBoolOp(e.rightOperand.evalTypeFor)
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(AndExpression e){
//		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
//		else e.leftOperand.evalTypeFor.evalBoolOp(e.rightOperand.evalTypeFor)
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(EqualityExpression e){
//		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
//		else e.leftOperand.evalTypeFor.evalRelationalOp(e.rightOperand.evalTypeFor)
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(RelationalExpression e){
//		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
//		else e.leftOperand.evalTypeFor.evalRelationalOp(e.rightOperand.evalTypeFor)
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(AdditiveExpression e){
//		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
//		else e.leftOperand.evalTypeFor.evalMathsOp(e.rightOperand.evalTypeFor)
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(MultiplicativeExpression e){
//		if(e.rightOperand == null)	e.leftOperand.evalTypeFor
//		else e.leftOperand.evalTypeFor.evalMathsOp(e.rightOperand.evalTypeFor)
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(UnaryExpression e){
//		if(e.feature == '!') PrimitiveType.Boolean.evalBoolOp(e.operand.evalTypeFor)
//		else PrimitiveType.Real.evalBoolOp(e.operand.evalTypeFor)
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(ParExpression e){
//		e.expr.evalTypeFor
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(WhenExpression e){
//		val nonReal = e.when.exists[wc| 
//				val type=wc.evalTypeFor
//				type != PrimitiveType.Real
//		] 
//		if(nonReal)
//			null
//		else if(e.other == null)
//			PrimitiveType.Real
//		else if(e.other.evalTypeFor ==  PrimitiveType.Real)
//			PrimitiveType.Real
//		else
//			null
//	}
//
//	def dispatch PrimitiveType evalTypeFor(WhenClause e){
//		val condTest = e.cond.evalTypeFor
//		val valTest = e.value.evalTypeFor
//		if(condTest == PrimitiveType.Boolean && valTest == PrimitiveType.Real)
//			PrimitiveType.Real
//		else null 
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(BuiltinFunctionCall e){
//		if(e.argList != null && e.argList.evalTypeFor == null) null
//		else functionTypeTable.get(e.func).theType
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(UnnamedFuncArguments e){
//		if(e.args.exists[arg| arg.evalTypeFor != PrimitiveType.Real]) null
//		else PrimitiveType.Real
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(NamedFuncArguments e){
//		// TODO: named argument checking needed
//		PrimitiveType.Real
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(VectorLiteral e){
//		if(e.expression.evalTypeFor == PrimitiveType.Real) PrimitiveType.Real
//		else null
//	}
//	
//	def dispatch PrimitiveType evalTypeFor(VectorContent e){
//		if(e.expressions.exists[ve| ve.evalTypeFor != PrimitiveType.Real]) null
//		else PrimitiveType.Real
//	}
	
	
}