/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package eu.ddmore.converter.mdlprinting

import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.ParExpression
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.ObservationBlock
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.DataObject
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
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.mdl.PropertyDeclaration
import org.ddmore.mdl.mdl.PkParameterType
//import org.ddmore.mdl.mdl.Matching
import org.ddmore.mdl.mdl.SignedNumericValue
import org.ddmore.mdl.mdl.VectorExpression
import org.ddmore.mdl.mdl.PkMacroType
import org.ddmore.mdl.mdl.LevelType
import org.ddmore.mdl.mdl.SymbolName
import org.ddmore.mdl.mdl.ImportObjectStatement
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName

class MdlPrinter {
	
	public var mdlVersion = "6.0";
	
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
			val orExpr = e.expression.expression;
			val andExpr = orExpr.expression.get(0);
			val logicalExpr = andExpr.expression.get(0);	
			if (logicalExpr.boolean != null){	
				if ((logicalExpr.negation == null) && logicalExpr.boolean.equals("true")) return true;
				if ((logicalExpr.negation != null) && logicalExpr.boolean.equals("false")) return true;
			}
		}
		return e.toStr.equals("true");
	}	
	
	def isAttributeTrue(Arguments args, String attrName){
		if (args != null)
			for (arg: args.arguments)
				if (arg.argumentName != null 
					&& arg.argumentName.name.equals(attrName) 
					&& arg.expression != null){
					return arg.expression.isTrue;
				}
		return false;
	}
	
	//Return value of an attribute with a given name
	def getAttribute(Arguments args, String attrName){
		if (args != null)
			for (arg: args.arguments)
				if (arg.argumentName != null 
					&& arg.argumentName.name.equals(attrName)
					&& arg.expression != null)
					return arg.expression.toStr
		return "";
	}	
	
	//Return value of an attribute with a given name
	def getAttributeExpression(Arguments args, String attrName){
		if (args != null)
			for (arg: args.arguments)
				if (arg.argumentName != null 
					&& arg.argumentName.name.equals(attrName)
					&& arg.expression != null
				)
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
			res = res + v.symbolName.toStr;
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
	
	def String toStr(ImportObjectStatement st){
		var res = st.symbolName.toStr + " = " + st.objectName.name;
		if (st.importURI != null){
			res  = res + " from file " + st.importURI;
		}
		return res;
	}
	
	def String toStr(SymbolName s){
		var res = s.name.convertID;
		if (s.index != null){
			res  = res + '[' + s.index + ']';
		}
		return res;
	}
	
	def String toStr(FullyQualifiedSymbolName s){
		var res = s.parent.toStr + "." + s.symbolName.toStr;
		return res;
	}
	
	def toStr(EnumType t) {
		if (t.type != null)
			return t.type.toStr;
		if (t.use != UseType::NO_USE)
			return t.use.toString
		if (t.target != TargetType::NO_TARGET)
			return t.target.toString
		if (t.input != InputFormatType::NO_INPUT_FORMAT)
			return t.input.toString
		if (t.variability != VariabilityType::NO_VARIABILITY)
			return t.variability.toString
		if (t.individualVar != IndividualVarType::NO_INDIVIDUAL_VAR)
			return t.individualVar.toString
		if (t.level != LevelType::NO_LEVEL)
			return t.level.toString
		if (t.trial != TrialType::NO_TRIAL)
			return t.trial.toString
		if (t.pkParameter != PkParameterType::NO_PARAM)
			return t.pkParameter.toString
		if (t.pkMacro != PkMacroType::NO_MACRO)
			return t.pkMacro.toString
	}
	
	def toStr(VarType t) {
		if (t.categorical != null){
			var res = t.categorical;
			if (t.categories != null){
				res = res + "("; 
				var iterator = t.categories.iterator();
				if (iterator.hasNext ) {
					var i = iterator.next; 
					if (i.categoryName != null) res  = res + i.categoryName.name;
					//if (i.expression != null) res = res + " = " + i.expression.toStr;
				}
				while (iterator.hasNext){
					res  = res + ', ';
					var i = iterator.next; 
					if (i.categoryName != null) res  = res + i.categoryName.name;
					//if (i.expression != null) res = res + " = " + i.expression.toStr;
				}
				res = res + ")";
			}
			return res;
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
			return "~" + l.type + "(" + l.arguments.toStr + ")";
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
		//if (e.matching != null) return e.matching.toStr;
	}
	
	/* 
	def String toStr(Matching e){
		var res = '''«e.symbolName.name» <=> «e.condition.toStr»''';
		return res;		
	}*/
	
	def String toStr(Expression e){
		var res = e.expression.toStr;
		if (e.condition != null){
			res = res + ''' when «e.condition.toStr»'''
			if (e.whenBranches != null)
				for (b: e.whenBranches){
						res = res + 
						'''
						, «b.expression.toStr» when «e.condition.toStr»
						'''
				}		
			if (e.elseExpression != null)
				res = res + 
				''' otherwise «e.elseExpression.toStr»'''
		}
		return res;
	}
	
	def toStr(OrExpression e){
		var res = "";
		if (e.expression != null){
			var iterator = e.expression.iterator();
			var operatorIterator = e.operator.iterator();
			if (iterator.hasNext ) {
				res = iterator.next.toStr;
			}
			while (iterator.hasNext && operatorIterator.hasNext){
				res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
			}
		}
		return res;
	}
	
	def toStr(AndExpression e){
		var res = "";
		if (e.expression != null){
			var iterator = e.expression.iterator();
			var operatorIterator = e.operator.iterator();
			if (iterator.hasNext ) {
				res = iterator.next.toStr;
			}
			while (iterator.hasNext && operatorIterator.hasNext){
				res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
			}
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
	
	def String toStr(SignedNumericValue v){
		var res = "";
		if (v.operator != null) res = v.operator;
		if (v.value != null) res = res + v.value;
		if (v.skip != null) res = v.skip;
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
			return e.symbol.toStr; 
		}
		if (e.constant != null){
			return e.constant.toString
		}
		if (e.functionCall != null) {
			return e.functionCall.toStr
		}
		return res;
	}	
	
	def toStr(FunctionCall call){
		var res = call.identifier.name.convertID;
		if (call.arguments != null)
		 	res = res + "(" + call.arguments.toStr + ")";
		 return res;	
	}
	
	def toStr(Vector v) {
		return '[' + v.expression.toStr + ']';
	}
	
	def String toStr(VectorExpression e){
		var res = "";
		if (e.expressions != null) {
			var iterator = e.expressions.iterator();
			if (iterator.hasNext ) res  = res + iterator.next.toStr;
			while (iterator.hasNext) res  = res + ', ' + iterator.next.toStr; 
		}
		if (e.lists != null) {
			var iterator = e.lists.iterator();
			if (iterator.hasNext ) res  = res + iterator.next.toStr;
			while (iterator.hasNext) res  = res + ', ' + iterator.next.toStr; 
		}
		/*  if (e.vectors != null) {
			var iterator = e.vectors.iterator();
			if (iterator.hasNext ) res  = res + iterator.next.toStr;
			while (iterator.hasNext) res  = res + ', ' + iterator.next.toStr; 
		}
		if (e.matchings != null) {
			var iterator = e.matchings.iterator();
			if (iterator.hasNext) res  = res + iterator.next.toStr;
			while (iterator.hasNext) res  = res + ', ' + iterator.next.toStr; 
		}*/
		return res;
	}
	
	
	def toStr(ParExpression e){
		return "(" + e.expression.toStr + ")";
	}
	
	def String toStr(Arguments arg){
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
			if (a.randomList != null){
				res = res + a.randomList.toStr;
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
			if (a.randomList != null){
				res = res + a.randomList.toStr;
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
	
	def print(ObservationBlock b)'''
		«FOR s: b.variables»
			«s.print»
		«ENDFOR»
	'''		
	
	def print(SimulationBlock b)'''
		«FOR s: b.variables»
			«s.print»
		«ENDFOR»
	'''	

	def print(EstimationBlock b)'''
		«FOR s: b.variables»
			«s.print»
		«ENDFOR»
	'''	
		
	def print(TargetBlock b)'''«b.toStr»'''	
		
	def print(FunctionCall call)'''«call.toStr»'''
		
    def print(SymbolDeclaration v)'''«v.toStr»'''

	def print(AnyExpression e)'''«e.toStr»'''
		
	def print(RandomList l)'''«l.toStr»'''

	def print(List l)'''«l.toStr»'''
	
	def print(Expression e)'''«e.toStr»'''
	
	def print(OrExpression e)'''«e.toStr»'''
	
	def print(AndExpression e)'''«e.toStr»'''
	
	def print(LogicalExpression e)'''«e.toStr»'''
	
	def print(AdditiveExpression e)'''«e.toStr»'''
	
	def print(MultiplicativeExpression e)'''«e.toStr»'''
	
	def print(PowerExpression e)'''«e.toStr»'''
	
	def print(UnaryExpression e)'''«e.toStr»'''

	def print(ParExpression e)'''«e.toStr»'''
	
	def print(Arguments arg)'''«arg.toStr»'''
}
