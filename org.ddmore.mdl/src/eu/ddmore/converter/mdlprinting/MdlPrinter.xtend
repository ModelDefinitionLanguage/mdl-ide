/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package eu.ddmore.converter.mdlprinting

import java.util.ArrayList
import org.apache.commons.io.FilenameUtils
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.ArgumentExpression
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.EventType
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.ImportObjectStatement
import org.ddmore.mdl.mdl.IndividualVarType
import org.ddmore.mdl.mdl.InputFormatType
import org.ddmore.mdl.mdl.LevelType
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.NamedArguments
import org.ddmore.mdl.mdl.NonContinuousType
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.ParExpression
import org.ddmore.mdl.mdl.PkMacroType
import org.ddmore.mdl.mdl.PkParameterType
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.PropertyDeclaration
import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.SymbolName
import org.ddmore.mdl.mdl.TrialType
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.UnnamedArguments
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.VarType
import org.ddmore.mdl.mdl.VariabilityType
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.VectorExpression
import org.ddmore.mdl.validation.PropertyValidator
import org.eclipse.xtext.xbase.lib.Pair
import org.ddmore.mdl.mdl.TargetType
import org.ddmore.mdl.mdl.SymbolRef

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
	
	def isCategorical(AnyExpression e){
		if (e != null && e.type != null && e.type.type != null && e.type.type.categorical != null) return true;
		return false;
	}
	
	def getCategoricalNames(AnyExpression argExpr){
		if (argExpr.isCategorical) {
			var categoricalNames = new ArrayList<String>();
			if (argExpr.getType().getType().getCategories() != null){
				for (c: argExpr.getType().getType().getCategories())
					categoricalNames.add(c.getCategoryName().getName());
			}
			return categoricalNames;
		}
		return null;
	}
	
	def isContinuous(AnyExpression e){
		if (e != null && e.type != null && e.type.type != null && e.type.type.continuous != null) return true;
		return false;
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
	
	def isTrue(ArgumentExpression e){
		if (e.expression != null)
			return e.expression.isTrue;
		return false;	
	}	
	
	def isAttributeTrue(Arguments args, String attrName){
		if (args != null)
			if (args.namedArguments != null){
				for (arg: args.namedArguments.arguments){
					return arg.expression.isTrue;
				}
			}
			if (args.unnamedArguments != null){
				for (arg: args.unnamedArguments.arguments){
					return arg.isTrue;
				}
			}
		return false;
	}
	
	//Returns a set of attribute pairs (unnamed or with given names for named attributes)
	def getAttributePairs(AnyExpression expr, String attr1, String attr2){
		var res = new ArrayList<Pair<AnyExpression, AnyExpression>>();
		if (expr == null) return res;
		if (expr.list != null){
			val pair = expr.list.getAttributePair(attr1, attr2);
			res.add(pair);
		}
		if (expr.vector != null && expr.vector.expression.lists != null){
			for (pairExpr: expr.vector.expression.lists){
				val pair = pairExpr.getAttributePair(attr1, attr2);
				if (pair != null) res.add(pair);
			}
		}
		return res;
	}
	
	//Returns a pair of attributes (unnamed or with given names for named attributes)
	protected def getAttributePair(List pair, String attr1, String attr2){
		var AnyExpression key = null;
		var AnyExpression value = null;
		if (pair.arguments.unnamedArguments != null){//unnamed
			var pairArgs = pair.arguments.unnamedArguments.arguments;
			if (pairArgs.size >= 2){
				key = pairArgs.get(0).expression; 
				value =	pairArgs.get(1).expression;
			}
		} else {//named
			var pairArgs = pair.arguments.namedArguments.arguments;
			key = pairArgs.getAttributeExpression(attr1);
			value = pairArgs.getAttributeExpression(attr2);
		}	
		if (key != null && value != null)
			return new Pair<AnyExpression, AnyExpression>(key, value);
		return null;
	}
	
	//Return value of an attribute with a given name
	def getAttribute(Arguments args, String attrName){
		if (args != null && args.namedArguments != null)
			for (arg: args.namedArguments.arguments)
				if (arg.argumentName.name.equals(attrName) && arg.expression != null)
					return arg.expression.toStr
		return "";
	}	
	
	//Return value of an attribute with a given name
	def getAttribute(java.util.List<Argument> args, String attrName){
		if (args != null)
			for (arg: args)
				if (arg.argumentName.name.equals(attrName) && arg.expression != null)
					return arg.expression.toStr
		return "";
	}
	
	//Return value of an attribute with a given name
	def getAttributeExpression(Arguments args, String attrName){
		if (args != null && args.getNamedArguments != null)
			for (arg: args.namedArguments.arguments)
				if (arg.argumentName.name.equals(attrName) && arg.expression != null)
					if (arg.expression.expression != null)
						return arg.expression.expression;

		return null;
	}
	
	//Return value of an attribute with a given name
	def getAttributeRandomList(Arguments args, String attrName){
		if (args != null && args.getNamedArguments != null)
			for (arg: args.namedArguments.arguments)
				if (arg.argumentName.name.equals(attrName) && arg.expression != null)
					if(arg.expression.randomList != null)
						return arg.expression.randomList;

		return null;
	}
	
	def getAttributeExpression(java.util.List<Argument> args, String attrName){
		if (args != null)
			for (arg: args)
				if (arg.argumentName.name.equals(attrName) && arg.expression != null)
					if (arg.expression.expression != null)
						return arg.expression.expression;
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
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * By default, don't quote string expressions, since this class relates to conversion
	 * to PharmML. The Mdl2Nonmem subclass will override this to enclose string expressions
	 * in double quotes.
	 */
	def toStr(String str) {
	    return str
	}
	
	def toStr(PropertyDeclaration p){
		return p.propertyName.name + " = " + p.expression.toStr;
	}
	
	def toStr(SymbolDeclaration v){
		var res = "";
		if (v.symbolName != null) 
			res = res + v.symbolName.toStr;
		var expr = ""; 
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
		var res = "";
		if (st.symbolName != null)
			res = st.symbolName.toStr;
		if (st.objectName != null)
			res = res + " = " + st.objectName.name;
//		if (st.importURI != null){
//			res  = res + " from file " + st.importURI;
//		}
		return res;
	}
	
	def String toStr(SymbolName s){
		var res = s.name.convertID;
//		if (s.index != null){
//			res  = res + '[' + s.index + ']';
//		}
		return res;
	}
	
	def String toStr(SymbolRef s){
		return s.symbolRef.toStr;
	}
	
//	def String toStr(FullyQualifiedSymbolName s){
//		var res = s.parent.toStr + "." + s.symbolName.toStr;
//		return res;
//	}
	
	def toStr(EnumType t) {
		if (t.type != null)
			return t.type.toStr;
		if (t.use != UseType::NO_USE)
			return t.use.toString;
		if (t.target != TargetType::NO_TARGET)
			return t.target.toString;
		if (t.input != InputFormatType::NO_INPUT_FORMAT)
			return t.input.toString;
		if (t.variability != VariabilityType::NO_VARIABILITY)
			return t.variability.toString;
		if (t.individualVar != IndividualVarType::NO_INDIVIDUAL_VAR)
			return t.individualVar.toString;
		if (t.level != LevelType::NO_LEVEL)
			return t.level.toString;
		if (t.trial != TrialType::NO_TRIAL)
			return t.trial.toString;
		if (t.pkParameter != PkParameterType::NO_PARAM)
			return t.pkParameter.toString;
		if (t.pkMacro != PkMacroType::NO_MACRO)
			return t.pkMacro.toString;
		if (t.event != EventType::NO_EVENT)
			return t.event.toString;
		if (t.nonContinuous != NonContinuousType::NO_TYPE)
			return t.nonContinuous.toString;	
	}
	
	def toStr(VarType t) {
		if (t.categorical != null){
			var res = t.categorical;
			if (!t.categories.nullOrEmpty){
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
//		if (t.likelihood != null){
//			return t.likelihood	
//		} 
//		if (t.m2LL != null){
//			return t.m2LL	
//		} 
//		if (t.tte != null){
//			return t.tte;
//		}
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
	}
	
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
	
//	def String toStr(SignedNumericValue v){
//		var res = "";
//		if (v.operator != null) res = v.operator;
//		if (v.value != null) res = res + v.value;
//		if (v.skip != null) res = v.skip;
//		return res;
//	}
	
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
			while (iterator.hasNext) res  = res + 
				'''
				, «iterator.next.toStr»
				''';
		}
		return res;
	}
	
	
	def toStr(ParExpression e){
		return "(" + e.expression.toStr + ")";
	}
	
	def String toStr(Arguments arg){
		if (arg.namedArguments != null){
			return arg.namedArguments.toStr;
		}
		if (arg.unnamedArguments != null){
			return arg.unnamedArguments.toStr;
		}
		return "";
	}
	
	def String toStr(NamedArguments args){
		var res = "";
		var iterator = args.arguments.iterator();
		if (iterator.hasNext ) {
			var a = iterator.next; 
			if (a.expression != null) 
				if (a.argumentName != null)
					res  = a.argumentName.name + " = " + a.expression.toStr
				else res  = res + a.expression.toStr;	
		}
		while (iterator.hasNext){
			res  = res + ', ';
			var a = iterator.next; 
			if (a.expression != null) 
				if (a.argumentName != null)
					res  = res + a.argumentName.name + " = " + a.expression.toStr
				else res  = res + a.expression.toStr;	
		}
		return res;	
	}
	
	def String toStr(UnnamedArguments args){
		var res = "";
		var iterator = args.arguments.iterator();
		if (iterator.hasNext ) {
			var a = iterator.next; 
			res  = a.expression.toStr;
		}
		while (iterator.hasNext){
			var a = iterator.next;
			if (a.expression != null) 
				res  = res + ', ' + a.expression.toStr;
		}
		return res;	
	}
	
	def String toStr(ArgumentExpression a){
		var res = "";
		if (a != null){
			if (a.expression != null){
				res = res + a.expression.toStr;
			}
			if (a.randomList != null){
				res = res + a.randomList.toStr;
			}
		}
		return res;
	}
	
//	def toStr(TargetBlock b){
//		return b.externalCode.substring(3, b.externalCode.length - 3)
//	}
}
