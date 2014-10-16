/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package eu.ddmore.converter.mdlprinting

import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.Block
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.Primary
import org.ddmore.mdl.mdl.ConditionalStatement
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.ConditionalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.ParExpression
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.DistributionArguments
import org.ddmore.mdl.mdl.DistributionArgument
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.ObservationBlock
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.Selector
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.FormalArguments
import org.ddmore.mdl.mdl.SimulationBlock
import org.ddmore.mdl.mdl.EstimationBlock
import org.apache.commons.io.FilenameUtils
import org.ddmore.mdl.mdl.VarType
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.TargetType
import org.ddmore.mdl.mdl.InputFormatType
import org.ddmore.mdl.mdl.VariabilityType
import org.ddmore.mdl.mdl.IndividualVarType
import org.ddmore.mdl.mdl.TrialType
import org.ddmore.mdl.mdl.DistributionType
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.mdl.PropertyDeclaration

class MdlPrinter {
	
	public var mdlVersion = "5.2.0";
	
	private static val MdlPrinter mdlPrinter = new MdlPrinter();
	protected new(){}
	public static def MdlPrinter getInstance(){
		return mdlPrinter;
	}
	    
	//Get MDL file name
	def fileName(Mcl m){
		var fileName = m.eResource.getURI().path;
		var baseName = FilenameUtils::getBaseName(fileName);
		return baseName;
	}	
	
	def isTrue(AnyExpression e){
		if (e.expression != null){
			if (e.expression.conditionalExpression.expression != null){
				val orExpr = e.expression.conditionalExpression.expression;
				val andExpr = orExpr.expression.get(0);
				val logicalExpr = andExpr.expression.get(0);	
				if (logicalExpr.boolean != null){	
					if ((logicalExpr.negation == null) && logicalExpr.boolean.equals("true")) return true;
					if ((logicalExpr.negation != null) && logicalExpr.boolean.equals("false")) return true;
				}
			}
		}
		return e.toStr.equals("true");
	}	
	
	def isAttributeTrue(Arguments args, String attrName){
		if (args != null)
			for (arg: args.arguments)
				if (arg.argumentName != null && arg.argumentName.name.equals(attrName)){
					return arg.expression.isTrue;
				}
		return false;
	}
	
	//Return value of an attribute with a given name
	def getAttribute(DistributionArguments args, String attrName){
		if (args != null)
			for (arg: args.arguments)
				if (arg.argumentName != null && arg.argumentName.name.equals(attrName)){
					return arg.toStr;
				}				
		return "";
	} 
	
	def String toStr(DistributionArgument arg){
		if (arg.distribution != DistributionType::NONE)
			return arg.distribution.toString;
		if (arg.expression != null)
			return arg.expression.toStr;	
		if (arg.component != null)
			return arg.component.toStr;
		return "";	
	}	
	
	//Return value of an attribute with a given name
	def getAttribute(Arguments args, String attrName){
		if (args != null)
			for (arg: args.arguments)
				if (arg.argumentName != null && arg.argumentName.name.equals(attrName))
					return arg.expression.toStr
		return "";
	}	
	
	//Return value of an attribute with a given name
	def getAttributeExpression(Arguments args, String attrName){
		if (args != null)
			for (arg: args.arguments)
				if (arg.argumentName != null && arg.argumentName.name.equals(attrName))
					return arg.expression
		return null;
	}	
	
	//Find reference to a data file 
	public def getDataSource(Mcl m){
		for (obj: m.objects){
			if (obj.dataObject != null){
				var res = obj.dataObject.getDataFile;
				if (res.length > 0) return res;
			}
		}
		return "";
	}
	
	//Find reference to a data file 
	public def getDataFile(DataObject dataObject){
		for (b: dataObject.blocks){
			if (b.sourceBlock != null){
				for (s: b.sourceBlock.statements){
					if (s.propertyName.name.equals(PropertyValidator::attr_file.name) && s.expression != null)
						return s.expression.toStr;
				}
			} 
		}
		return "";
	}
	
	//Find reference to a script file 
	public def getScriptFile(DataObject dataObject){
		for (b: dataObject.blocks){
			if (b.sourceBlock != null){
				for (s: b.sourceBlock.statements){
					if (s.propertyName.name.equals(PropertyValidator::attr_script.name) && s.expression != null)
						return s.expression.toStr;
				}
			} 
		}
		return "";
	}
	


	/////////////////////////////////////////////////////////////

    //Map variableDeclaration identifiers, identity for MDL printing, to be rewritten in super classes 
	def convertID(String id){
		return id;
	}
	
	//Map operators, identity for MDL printing, to be rewritten in super classes 
	def convertOperator(String op){
		return op;
	}
	
	def toStr(PropertyDeclaration p){
		return p.propertyName.name + " = " + p.expression.toStr;
	}
	
	def toStr(SymbolDeclaration v){
		var res = "";
		if (v.functionName != null){
			res = res + v.functionName.name.convertID + '('      
		}
		if (v.symbolName != null) {     
			res = res + v.symbolName.name.convertID;
		}
		if (v.argumentName != null) {     
			res = res + v.argumentName.toStr;
		}
		if (v.functionName != null){
			res = res + ')' 
		}
		var expr = ""; //First make sure that expression is not empty, than print "=" 
		if (v.expression != null){
			expr = v.expression.toStr;
			if (!expr.trim().equals(""))
				res = res + " = " + expr;
		}
		if (v.list != null){
			expr = v.list.toStr;
			if (!expr.trim().equals(""))
				res = res + " : " + expr;
		}
		if (v.randomList != null){
			expr = v.randomList.toStr;
			if (!expr.trim().equals(""))
				res = res + expr;
		}
		return res;
	}

	def toStr(EnumType t) {
		if (t.type != null)
			return t.type.toStr;
		if (t.use != UseType::NONE)
			return t.use.toString
		if (t.target != TargetType::NONE)
			return t.target.toString
		if (t.input != InputFormatType::NONE)
			return t.input.toString
		if (t.variability != VariabilityType::NONE)
			return t.variability.toString
		if (t.trial != TrialType::NONE)
			return t.trial.toString
		if (t.individualVar != IndividualVarType::NONE)
			return t.individualVar.toString
	}
	
	def toStr(VarType t) {
		if (t.categorical != null){
			return t.categorical
		}
		if (t.continuous != null){
			return t.continuous
		} 
		if (t.likelihood != null){
			return t.likelihood	
		} 
		if (t.m2LL != null){
			return t.m2LL	
		} 
	}
	
	def toStr(RandomList l){
		if (l.arguments != null){
			return "~" + "(" + l.arguments.toStr + ")";
		}
		return "";
	}

	def toStr(List l){
		if (l.arguments != null){
			return "{" + l.arguments.toStr + "}";
		}
		return "";
	}
	
	def String toStr(AnyExpression e){
		if (e.expression != null) return e.expression.toStr;
		if (e.list != null) return e.list.toStr; 
		if (e.vector != null) return e.vector.toStr; 
		if (e.type != null) return e.type.toStr; 
	}
	
	def String toStr(Expression e){
		return e.conditionalExpression.toStr
	}
	
	def toStr(ConditionalExpression e){ 
		var res = e.expression.toStr;
		if (e.expression1 != null){
			res  = res + "?" + e.expression1.toStr + ":" + e.expression2.toStr
		}
		return res;
	}

	def toStr(OrExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	def toStr(AndExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;	
	}
	
	//(==, !=, <, > etc.)
	def toStr(LogicalExpression e){
		var res = "";
		if (e.negation != null){
			res = res + e.negation;
		}
		if (e.boolean != null){
			res = res + e.boolean.toString;
		}
		if (e.expression1 != null){
			res  = res + e.expression1.toStr;
			if (e.expression2 != null){
				res = res + e.operator.convertOperator + e.expression2.toStr;
			}
		}
		return res;
	}
	
	def toStr(AdditiveExpression e){
		var res  = "";
		if (e.expression != null)
		{ 
			var iterator = e.expression.iterator();
			var operatorIterator  = e.operator.iterator();
			if (iterator.hasNext ) {
				res = iterator.next.toStr;
			}
			while (iterator.hasNext && operatorIterator.hasNext){
				res = res + operatorIterator.next.convertOperator + iterator.next.toStr;
			}
		}
		if (e.string != null){		
			res = toStr(e.string)
		}
		return res
	}
	
	/**
	 * By default, don't quote string expressions, since this class relates to conversion
	 * to PharmML. The Mdl2Nonmem subclass will override this to enclose string expressions
	 * in double quotes.
	 */
	def toStr(String str) {
	    return str
	}
	
	def toStr(MultiplicativeExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator  = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	def String toStr(PowerExpression e) { 
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator  = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	def String toStr(UnaryExpression e){
		var res = "";
		if (e.expression != null){
			res = res + e.operator.convertOperator + e.expression.toStr
		}
		if (e.parExpression != null){
			res = res + e.parExpression.toStr;
		}
		if (e.number != null){
			return e.number;
		}
		if (e.symbol != null){
			return e.symbol.name.convertID; 
		}
		if (e.constant != null){
			return e.constant.toString
		}
		if (e.functionCall != null) {
			return e.functionCall.toStr
		}
		if (e.attribute != null) {
			return e.attribute.toStr
		}
		return res;
	}	
	
	def toStr(FunctionCall call){
		var res = call.identifier.name.convertID;
		if (call.arguments != null)
		 	res = res + "(" + call.arguments.toStr + ")";
		 return res;	
	}
	
	def String toStr(Primary p){
		if (p.number != null){
			return  p.number;
		}
		if (p.symbol != null){
			return p.symbol.name; 
		}
		if (p.string != null){
			return p.string; 
		}
		if (p.vector != null) {
			return p.vector.toStr
		}
	}
	
	def toStr(FullyQualifiedArgumentName arg) { 
		var res = "";
		if (arg != null){
			if (arg.parent != null)
				res = arg.parent.name.convertID;
			for (s: arg.selectors){
				res = res + s.toStr
			}
		}
		return res;
	}
	
	def toStr(Selector s) { 
		if (s.argumentName != null)
			return "." + s.argumentName.name.convertID;
		if (s.selector != null)
			return "[" + s.selector + "]";
	}
	
	def toStr(Vector v) { 
		var res  = '[';
		var iterator = v.values.iterator();
		if (iterator.hasNext) {
			res = res + iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + ', ';
			res = res + iterator.next.toStr;
		}
		return res + ']';
	}
	
	def toStr(ParExpression e){
		return "(" + e.expression.toStr + ")";
	}
	
	def toStr(Arguments arg){
		var res  = "";
		var iterator = arg.arguments.iterator();
		if (iterator.hasNext ) {
			var a = iterator.next; 
			if (a.argumentName != null){
				res  = res + a.argumentName.name + " = ";
			}
			if (a.expression != null){
				res = res + a.expression.toStr;
			}
		}
		while (iterator.hasNext){
			res  = res + ', ';
			var a = iterator.next; 
			if (a.argumentName != null){
				res  = res + a.argumentName.name + " = ";
			}
			if (a.expression != null){
				res = res + a.expression.toStr;
			}
		}
		return res;
	}
	
	def toStr(DistributionArguments arg){
		var res  = "";
		var iterator = arg.arguments.iterator();
		if (iterator.hasNext ) {
			var a = iterator.next; 
			if (a.argumentName != null){
				res  = res + a.argumentName.name + " = ";
			}
			res = res + a.toStr;
		}
		while (iterator.hasNext){
			res  = res + ', ';
			var a = iterator.next; 
			if (a.argumentName != null){
				res  = res + a.argumentName.name + " = ";
			}
			res  = res + a.toStr;
		}
		return res;
	}			
	
	def toStr(FormalArguments arg) { 
		var res  = "";
		var iterator = arg.arguments.iterator();
		if (iterator.hasNext ) {
			var a = iterator.next; 
			if (a.name != null){
				res  = res + a.name;
			}
		}
		while (iterator.hasNext){
			res  = res + ', ';
			var a = iterator.next; 
			if (a.name != null){
				res  = res + a.name;
			}
		}
		return res;
	}
	
	
	def toStr(TargetBlock b){
		return b.externalCode.substring(3, b.externalCode.length - 3)
	}

	//////////////////////////////////////////////////////////////////////////
	//Printing
    //////////////////////////////////////////////////////////////////////////		 
	
	def CharSequence print(Block b)'''
		«FOR st: b.statements»
			«st.print»
		«ENDFOR»
	'''
	
	def print(ObservationBlock b)'''
		«FOR s: b.statements»
			«s.print»
		«ENDFOR»
	'''		
	
	def print(SimulationBlock b)'''
		«FOR s: b.statements»
			«s.print»
		«ENDFOR»
	'''	

	def print(EstimationBlock b)'''
		«FOR s: b.statements»
			«s.print»
		«ENDFOR»
	'''	
		
	def CharSequence print(BlockStatement st)'''
		«IF st.symbol != null»«st.symbol.print»«ENDIF»
		«IF st.functionCall != null»«st.functionCall.print»«ENDIF»
		«IF st.statement != null»«st.statement.print»«ENDIF»
		«IF st.targetBlock != null»«st.targetBlock.print»«ENDIF»
	'''

	def print(ConditionalStatement s)'''
		«IF s.expression != null»
			if («s.expression.print») {
				«IF s.ifStatement != null»
					«s.ifStatement.print»
				«ENDIF»
				«IF s.ifBlock != null»
					«s.ifBlock.print»
				«ENDIF»
			}«IF s.elseStatement != null || s.elseBlock != null» else {
				«IF s.elseStatement != null»
					«s.elseStatement.print»
				«ENDIF»
				«IF s.elseBlock != null»
					«s.elseBlock.print»
				«ENDIF»
			}
			«ENDIF»
		«ENDIF»
	'''
	
	def print(TargetBlock b)'''«b.toStr»'''	
		
	def print(FunctionCall call)'''«call.toStr»'''
		
    def print(SymbolDeclaration v)'''«v.toStr»'''

	def print(AnyExpression e)'''«e.toStr»'''
		
	def print(RandomList l)'''«l.toStr»'''

	def print(List l)'''«l.toStr»'''
	
	def print(Expression e)'''«e.toStr»'''
	
	def print(ConditionalExpression e)'''«e.toStr»'''

	def print(OrExpression e)'''«e.toStr»'''
	
	def print(AndExpression e)'''«e.toStr»'''
	
	def print(LogicalExpression e)'''«e.toStr»'''
	
	def print(AdditiveExpression e)'''«e.toStr»'''
	
	def print(MultiplicativeExpression e)'''«e.toStr»'''
	
	def print(PowerExpression e)'''«e.toStr»'''
	
	def print(UnaryExpression e)'''«e.toStr»'''

	def print(Primary p)'''«p.toStr»'''
	
	def print(ParExpression e)'''«e.toStr»'''
	
	def print(Arguments arg)'''«arg.toStr»'''
}
