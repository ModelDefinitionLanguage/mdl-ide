/* 
 * MDL converter toolbox, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to convert mathematical expressions from MDL to PharmML
 */
package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.ConditionalExpression
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.SymbolName
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.Primary
import java.util.ArrayList
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.FunctionName
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import org.ddmore.mdl.validation.FunctionValidator
import org.ddmore.mdl.mdl.TaskObjectBlock
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.VariabilityType
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.types.DefaultValues
import org.ddmore.mdl.types.MdlDataType

class MathPrinter extends MdlPrinter{

 	extension ReferenceResolver resolver=null
    
    new(ReferenceResolver resolver) {
    	this.resolver = resolver
 	}
	
	//Generate function definition from a math expression like a + b*f
	def print_mdef_FunctionDefinition(Expression expr) { 
		var arguments = newHashSet;
		var iterator = expr.eAllContents();
	    while (iterator.hasNext()){
	    	var obj = iterator.next();
	    	if (obj instanceof SymbolName){
	    		var ref = obj as SymbolName;
	    		arguments.add(ref.name);
	    	}
	    }
		'''
		<FunctionDefinition xmlns:ct="«xmlns_ct»"
			symbId="combinedErrorModel" symbolType="«TYPE_REAL»">
			«FOR arg: arguments»
				<FunctionArgument symbId="«arg»" symbolType="«TYPE_REAL»"/>
			«ENDFOR»
			<Definition>
				«expr.print_Math_Equation»
			</Definition>
		</FunctionDefinition>
		'''
	}
	 

	//Print any MDL expression: math expression, list or ode list 
	//(for the lists selected attribute values will be typically printed, e.g., value or deriv)
	def CharSequence print_Math_Expr(AnyExpression e)'''
		«IF e.expression != null»
			«e.expression.print_Math_Expr»
		«ENDIF»
	'''
	
	def print_Math_Equation(AnyExpression expr)'''
		«IF MdlDataType::validateType(MdlDataType::TYPE_STRING, expr)»
			<ct:String>«expr.toStr»</ct:String>
		«ELSE» 
			«IF MdlDataType::isEnumType(expr)» 
				<ct:String>«expr.toStr.convertEnum»</ct:String>
			«ELSE»
				<Equation xmlns="«xmlns_math»">
					«expr.print_Math_Expr»
				</Equation>
			«ENDIF»
		«ENDIF»	
	'''	
	
	def print_Math_Equation(Expression expr)'''
		<Equation xmlns="«xmlns_math»">
			«expr.print_Math_Expr»
		</Equation>
	'''	
		
	//Print any MDL expression: math expression, list or ode list 
	//(for the lists selected attribute values will be typically printed, e.g., value or deriv)
	def CharSequence print_Assign(AnyExpression e)'''
		<ct:Assign>
			«e.print_Math_Equation»
		</ct:Assign>	
	'''	
	
	def print_Assign(Expression expr)'''
		<ct:Assign>
			«expr.print_Math_Equation»
		</ct:Assign>
	'''
	
	def print_Assign(String value)'''
		<ct:Assign>
			«value.print_ct_Value»
		</ct:Assign>
	'''
	
	def print_Categorical(List categories)'''
		<Categorical>
		«FOR c: categories.arguments.arguments»
			<Category>
				«c.argumentName.name»
			</Category>
		«ENDFOR»
		</Categorical>
	'''

	//+ Convert math functions to PharmML 
	def print_Math_FunctionCall(FunctionCall call){
		if (call.identifier.name.equals(FunctionValidator::funct_seq)){
			if (call.arguments != null){
				val params = call.arguments.arguments;
				var passedByName = true;
				for (param: params)
					if (param.argumentName == null) passedByName = false;
				//(start, stepSize, repetition) - not used in MDL?
			 	if (params.size == 3 && !passedByName)
			 		return print_ct_Sequence(
			 			params.get(0).expression.print_Math_Expr.toString, 
			 			params.get(1).expression.print_Math_Expr.toString, 
			 			params.get(2).expression.print_Math_Expr.toString
			 		);
			 	if (passedByName) {
			 		return print_ct_Sequence(
			 			call.arguments.getAttribute(FunctionValidator::param_seq_start.name),
			 			call.arguments.getAttribute(FunctionValidator::param_seq_stepSize.name),
			 			call.arguments.getAttribute(FunctionValidator::param_seq_end.name)
			 		);
			 	}	
		 	}
		 		
		} else {
			if (FunctionValidator::funct_standard1.contains(call.identifier.name) ||
				FunctionValidator::funct_standard2.contains(call.identifier.name)){
				//Convert standard mathematical functions to a PharmML operator with the same name;		
				return call.print_Math_FunctionCall_Standard;	
			} else {
				return call.print_Math_FunctionCall_UserDefined;
			}
		}
	}

	//Functions from the standardFunctions list are PharmML operators
	def print_Math_FunctionCall_Standard(FunctionCall call){
		var functName = call.identifier.name;
		if (call.identifier.name.equals("ln")) functName = "log";
		'''
			«IF call.arguments.arguments.size == 1»
				<Uniop op="«functName»">
					«call.arguments.arguments.get(0).expression.print_Math_Expr»
				</Uniop>
			«ELSE»
				«IF call.arguments.arguments.size == 2»
					<Binop op="«functName»">
						«call.arguments.arguments.get(0).expression.print_Math_Expr»
						«call.arguments.arguments.get(1).expression.print_Math_Expr»
					</Binop>
				«ENDIF»
			«ENDIF»
		'''
	}
	
	//+ Convert user defined math functions to PharmML 
	def print_Math_FunctionCall_UserDefined(FunctionCall call)
	'''
		<math:FunctionCall>
			«call.identifier.print_ct_SymbolRef»
			«FOR arg: call.arguments.arguments»
				«arg.print_Math_FunctionArgument»
			«ENDFOR»
		</math:FunctionCall>
	'''
	
	//+
	def print_Math_FunctionArgument(Argument arg)'''
	<FunctionArgument«IF arg.argumentName != null» symbId="«arg.argumentName.name»"«ENDIF»>
		«arg.expression.print_Math_Expr»
	</FunctionArgument>
	'''	
	
	//+
	def CharSequence print_Math_Expr(Expression expr)'''
		«IF expr.expression != null»
			«expr.expression.print_Math_LogicOr(0)»
		«ENDIF»
		«IF expr.conditional != null»
			«expr.conditional.print_Math_Piecewise»
		«ENDIF»
	'''
	
	//+
	def print_Math_Piecewise(ConditionalExpression expr)'''
		<Piecewise>
			«expr.thenExpression.print_Math_LogicOpPiece(expr.condition.print_Math_LogicOr(0).toString)»
			«IF expr.elseExpression != null»
			«expr.elseExpression.print_Math_LogicOpPiece(expr.condition.print_DualExpression.toString)»
			«ENDIF»
		</Piecewise>
	'''		
	
	//+
	def print_Math_LogicOpPiece(Expression expr, String condition)'''
		<Piece>
			«expr.print_Math_Expr»
			«IF condition != null»
				<Condition>
					«condition»
				</Condition>
			«ENDIF»
		</Piece>
	'''
	
	//+ (right associative)
	def CharSequence print_Math_LogicOr(OrExpression expr, int startIndex){
		if (expr.expression != null){
			if (expr.expression.size - startIndex > 1){
				val first = expr.expression.get(startIndex).print_Math_LogicAnd(0);
				val second = expr.print_Math_LogicOr(startIndex + 1);
				return 
				'''
				<LogicBinop op="or">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_LogicAnd(0)»'''
			}
		}
		return ''''''
	}
		
	//+ (right associative)
	def CharSequence print_Math_LogicAnd(AndExpression expr, int startIndex){
		if (expr.expression != null){
			if (expr.expression.size - startIndex > 1){
				val first = expr.expression.get(startIndex).print_Math_LogicOp();
				val second = expr.print_Math_LogicAnd(startIndex + 1);
				return 
				'''
				<LogicBinop op="and">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_LogicOp()»'''
			}
		}
		return ''''''
	}
	
	//+ 
	def CharSequence print_Math_LogicOp(LogicalExpression expr){
		if (expr.expression1 != null){
			if (expr.expression2 != null){
				return 
				'''
				<LogicBinop op="«expr.operator.convertOperator»">
					«expr.expression1.print_Math_AddOp(0)»
					«expr.expression2.print_Math_AddOp(0)»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression1.print_Math_AddOp(0)»'''
			}
		}
		return ''''''
	}
	
	//+ (left associative)
	def CharSequence print_Math_AddOp(AdditiveExpression expr, int offset) { 
		if (expr.expression != null){
			if (expr.expression.size > 0){
				if (expr.expression.size - offset > 1){
					val first = expr.print_Math_AddOp(offset + 1);
					val operator = expr.operator.get(expr.operator.size - 1 - offset).convertOperator;
					val second = expr.expression.get(expr.expression.size - 1 - offset).print_Math_MultOp(0);
					return 
					'''
					<Binop op="«operator»">
						«first»
						«second»
					</Binop>
					'''
				} else {
					return '''«expr.expression.get(0).print_Math_MultOp(0)»'''
				}
			}
		}
		if (expr.string != null){
			return 
			'''<ct:String>«expr.string»</ct:String>'''
		}
		return ''''''			
	}
	
	//+ (left associative)
	def CharSequence print_Math_MultOp(MultiplicativeExpression expr, int offset) { 
		if (expr.expression != null){
			if (expr.expression.size - offset > 1){
				val first = expr.print_Math_MultOp(offset + 1);
				val operator = expr.operator.get(expr.operator.size - 1 - offset).convertOperator;
				val second = expr.expression.get(expr.expression.size - 1 - offset).print_Math_PowerOp(0);
				return 
				'''
				<Binop op="«operator»">
					«first»
					«second»
				</Binop>
				'''
			} else {
				return '''«expr.expression.get(0).print_Math_PowerOp(0)»'''
			}
		}
		return ''''''		
	}
	
	//+ (right associative)
	def CharSequence print_Math_PowerOp(PowerExpression expr, int startIndex) { 
		if (expr.expression != null){
			if (expr.expression.size - startIndex > 1){
				return 
				'''
				<Binop op="«"^".convertOperator»">
					«expr.expression.get(startIndex).print_Math_UniOp»
					«expr.print_Math_PowerOp(startIndex + 1)»
				</Binop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_UniOp»'''
			}
		}
		return ''''''				
	}
	
	//+
	def CharSequence print_Math_UniOp(UnaryExpression expr)'''
		«IF expr.operator != null»
			<Uniop op="«expr.operator.convertOperator»">
				«expr.expression.print_Math_UniOp»
			</Uniop>
		«ENDIF»
		«IF expr.parExpression != null»
			«expr.parExpression.expression.print_Math_Expr»
		«ENDIF»
		«IF expr.functionCall != null»
			«expr.functionCall.print_Math_FunctionCall»
		«ENDIF»
		«IF expr.number != null»
			«expr.number.print_ct_Value»
		«ENDIF»
		«IF expr.symbol !=null»
			«expr.symbol.print_ct_SymbolRef»
		«ENDIF»
		«IF expr.attribute !=null»
			«expr.attribute.print_ct_SymbolRef»
		«ENDIF»
		«IF expr.constant != null»
			«expr.constant.print_ct_Constant»
		«ENDIF»
	'''

	def CharSequence print_Math_Primary(Primary p)'''
		«IF p.number != null»
			«p.number.print_ct_Value»
		«ENDIF»
		«IF p.symbol !=null»
			«p.symbol.print_ct_SymbolRef»
		«ENDIF»
		«IF p.string !=null»
			<ct:String>«p.string»</ct:String>
		«ENDIF»
		«IF p.vector != null»
			«p.vector.print_ct_Vector»
		«ENDIF»
	'''
	
	//+
	def print_ct_Vector(Vector vector)'''
		<ct:Vector>
			«FOR v: vector.values»
				«v.print_Math_Primary»
			«ENDFOR»
		</ct:Vector>
	'''
	
	//
	def print_ct_Sequence(String begin, String stepSize,  String end)'''
		<ct:Sequence>
			<ct:Begin>
				«begin»
			</ct:Begin>
			<ct:StepSize>
				«stepSize»
			</ct:StepSize>
			<ct:End>
				«end»
			</ct:End>
		</ct:Sequence>
	'''
	
	//+
	def print_ct_Value(String value){
		try{        			
        	if (value.indexOf(".") > -1){
        		Double::parseDouble(value);
				return '''<ct:Real>«value»</ct:Real>''';
        	} else {
	       		Integer::parseInt(value);
    	   		return '''<ct:Int>«value»</ct:Int>''';	
        	}
        }
		catch (NumberFormatException e) {
			return '''<ct:Id>«value»</ct:Id>''';
		}
	}
	
	def print_ct_Constant(String constant)'''
		«IF constant.toString.equals(DefaultValues::INDEPENDENT_VAR)»
			<ct:SymbRef symbIdRef="«constant»"/>
		«ELSE»
			<Constant op="«constant.convertConstant»"/>
		«ENDIF»
	'''
	
	def getValueType(String value){
		try{        			
        	if (value.indexOf(".") > -1){
        		Double::parseDouble(value);
				return Constants::TYPE_REAL;
        	} else {
	       		Integer::parseInt(value);
    	   		return Constants::TYPE_INT;	
        	}
        }
		catch (NumberFormatException e) {
			return Constants::TYPE_ID;
		}
	}
	
	def print_ct_Value(String value, String type)'''
		<ct:«type»>«value»</ct:«type»>
	'''
	
	//Negation of the expression x || y -> !x && !y 
	def print_DualExpression(OrExpression expr){
		var newAndExprs = new ArrayList<String>();
		for (andExpr: expr.expression){
			var dualLogicalExprs = new ArrayList<String>();
			for (logicalExpr: andExpr.expression){
				if (logicalExpr.expression2 != null){
					var first = logicalExpr.expression1.print_Math_AddOp(0).toString;
					var second = logicalExpr.expression2.print_Math_AddOp(0).toString;
					var operator = logicalExpr.operator.getDualOperator.convertOperator;
					dualLogicalExprs.add(print_Math_LogicOp(first, operator, second).toString);	
				} else {
					var res = "";
					if (logicalExpr.boolean != null) res = logicalExpr.boolean;
					if (logicalExpr.expression1 != null) res = logicalExpr.expression1.toStr;
					if (logicalExpr.negation == null){
						dualLogicalExprs.add( 
						'''
							<Uniop op="not">
								<«res»/>
							</Uniop>
						''')
					} else {
						dualLogicalExprs.add( 
						'''
							<«res»/>
						''')
					}
				}
			}
			newAndExprs.add(dualLogicalExprs.print_Math_LogicAnd(0).toString);		
		}
		return newAndExprs.print_Math_LogicOr(0);
	}
	
	// Expr1 >= Expr2 == Expr3 (conversion is left associative, more then 2 operands do not make much sense anyway)
	private def print_Math_LogicOp(String first, String operator, String second)
	'''
		<LogicBinop op="«operator»">
			«first»
			«second»
		</LogicBinop>
	'''
	
	//+ Expr1 || ... || Expr_n (right associative)
	private def CharSequence print_Math_LogicOr(ArrayList<String> exprs, int startIndex){
		if (exprs!= null){
			if (startIndex < exprs.size - 1){
				val first = exprs.get(startIndex);
				val second = exprs.print_Math_LogicOr(startIndex + 1);
				return 
				'''
				<LogicBinop op="or">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«exprs.get(startIndex)»'''
			}
		}
		return ''''''
	}
	
	
	//Expr1 && ... && Expr_n (left associative)
	private def CharSequence print_Math_LogicAnd(ArrayList<String> exprs, int startIndex){
		if (exprs!= null){
			if (startIndex < exprs.size - 1){
				val first = exprs.get(startIndex);
				val second = exprs.print_Math_LogicAnd(startIndex + 1);
				return 
				'''
				<LogicBinop op="and">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«exprs.get(startIndex)»'''
			}
		}
		return ''''''
	}
	
	//Here expr and condition are PharmML representation of MDL expressions
	def print_Math_LogicOpPiece(String expr, String condition)'''
		<Piece>
			«expr»
			«IF condition != null»
				<Condition>
					«condition»
				</Condition>
			«ENDIF»
		</Piece>
	'''	
						
	//+
	def print_ct_SymbolRef(String objName, String name)'''
		«var blkId = resolver.getReferenceBlock(objName, name)»
		<ct:SymbRef«IF blkId.length > 0» blkIdRef="«blkId»"«ENDIF» symbIdRef="«name»"/>
	'''

	//+
	def print_ct_SymbolRef(String name)'''
		«var blkId = resolver.getReferenceBlock(name)»
		<ct:SymbRef«IF blkId.length > 0» blkIdRef="«blkId»"«ENDIF» symbIdRef="«name»"/>
	'''
	
	//+
	def print_ct_SymbolRef(SymbolName ref)'''
		«var blkId = resolver.getReferenceBlock(ref.name)»
		<ct:SymbRef«IF blkId.length > 0» blkIdRef="«blkId»"«ENDIF» symbIdRef="«ref.name»"/>
	'''
	
	//+
	def print_ct_SymbolRef(FunctionName ref)'''
		«var blkId = resolver.getReferenceBlock(ref.name)»
		<ct:SymbRef«IF blkId.length > 0» blkIdRef="«blkId»"«ENDIF» symbIdRef="«ref.name»"/>
	'''
	
	//TODO: How to print attributes?
	def print_ct_SymbolRef(FullyQualifiedArgumentName ref)'''
		<Description>MDL reference to an attribute «ref.toStr»</Description>
		«var blkId = ""»
		«IF ref.parent != null»
			«blkId = resolver.getReferenceBlock(ref.parent.name)»
		«ENDIF»
		<ct:SymbRef «IF blkId.length > 0»blkIdRef="«blkId»"«ENDIF» 
			symbIdRef="«ref.parent.name».«ref.toStr»"/>
	'''
	
	def print_ct_Matrix(String matrixType, String rowNames, Arguments parameters, Boolean useDiagVarNames)
	'''
		<Matrix matrixType="«matrixType»">
			<ct:RowNames>
				«rowNames»
			</ct:RowNames>
			«IF parameters.arguments.size > 0»
				<ct:MatrixRow>
				«FOR i: 0..parameters.arguments.size - 1»
					«val symbol = parameters.arguments.get(i)»
					«IF useDiagVarNames && symbol.argumentName != null»
						«print_ct_SymbolRef(symbol.argumentName.name)»
					«ELSE»
						«print_Math_Expr(symbol.expression)»
					«ENDIF»
					«IF symbol.argumentName != null»
						</ct:MatrixRow>
						«IF i != parameters.arguments.size - 1»
							<ct:MatrixRow>
						«ENDIF»
					«ENDIF»
				«ENDFOR»	
			«ENDIF»
		</Matrix>
	'''		

	def convertMatrixType(String matrixType){
		if (matrixType.equals(VariabilityType::VAR.toString))
			return MATRIX_COV;
		if (matrixType.equals(VariabilityType::SD.toString))
			return MATRIX_STDEV;
		if (matrixType.equals(VariabilityType::CORR.toString))
			return MATRIX_CORR;
		if (matrixType.equals(VariabilityType::COV.toString))
			return MATRIX_COV;
		return MATRIX_COV;	
	}	
	
	protected def getProperty(TaskObjectBlock t, String name){
		if (t.estimateBlock != null){
			for (s: t.estimateBlock.statements){
				if (s.propertyName != null && s.propertyName.name.equals(name)){
					if (s.expression != null)
						return s.expression.toStr;
				}
			}
		}
		if (t.simulateBlock != null){
			for (s: t.simulateBlock.statements){
				if (s.propertyName != null && s.propertyName.name.equals(name)){
					if (s.expression != null){
						return s.expression.toStr;
					}
				}
			}
		}
		if (t.evaluateBlock != null){
			for (s: t.evaluateBlock.statements){
				if (s.propertyName != null && s.propertyName.name.equals(name)){
					if (s.expression != null){
						return s.expression.toStr;
					}
				}
			}
		}
		if (t.optimiseBlock != null){
			for (s: t.optimiseBlock.statements){
				if (s.propertyName != null && s.propertyName.name.equals(name)){
					if (s.expression != null){
						return s.expression.toStr;
					}
				}
			}
		}
		if (t.dataBlock != null){
			for (s: t.dataBlock.statements){
				if (s.propertyName != null && s.propertyName.name.equals(name)){
					if (s.expression != null){
						return s.expression.toStr;
					}
				}
			}
		}
		if (t.modelBlock != null){
			for (s: t.modelBlock.statements){
				if (s.propertyName != null && s.propertyName.name.equals(name)){
					if (s.expression != null){
						return s.expression.toStr;
					}
				}
			}
		}
		return "";
	}	
	
	
	//+Returns a dual operator for a given logical operator
	def getDualOperator(String operator){
		switch (operator){
			case "<": ">="
			case ">": "<="
			case "<=": ">"
			case ">=": "<"
			case "==": "!="
			case "!=": "=="
			default: operator
		}
	}
	
	//operators
	override convertOperator(String operator){
		switch (operator){
			case "<": "lt"
			case ">": "gt"
			case "<=": "leq"
			case ">=": "geq"
			case "==": "neq"
			case "!=": "eq"
			case "+": "plus"
			case "-": "minus"
			case "*": "times"
			case "/": "divide"
			case "^": "power"
			default: operator
		}
	}
	
	//delimeters
	def convertDelimiter(String id){
		switch (id){
			case ",":   "COMMA"
			case "\\t": "TAB"
			case "\\s": "SPACE"
			default: "SPACE"
		}
	}

	//file formats
	def convertFileFormat(String id){
		switch (id.toUpperCase){
			case "R": "R"
			case "XLS": "SIMCYP" 
			default: id.toUpperCase
		}
	}
	
	//constants
	def convertConstant(String name){
		switch (name){
			case "INF": "infinity"
			default: name
		}
	}
	
	def convertEnum(String type){
		switch (type){ 
			case UseType::AMT.toString: "dose"
			case UseType::YTYPE.toString: "dvid"
			case UseType::ITYPE.toString: "dvid"
			case UseType::OCC.toString: "occasion"
			case UseType::CENS.toString: "censoring"
			default: type
		}
	}	
}