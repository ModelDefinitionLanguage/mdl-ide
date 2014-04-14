package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.Primary
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.ConditionalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.ParExpression
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.Distribution
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.Selector

//This class repeats some of the methods of eu.ddmore.converter.mdl.MdlPrinter
//We need math printing in both projects!!!
class MathPrinter {
	
	static def toStr(FullyQualifiedSymbolName name){
		var res = ""; 
		if (name.object != null){
			res  = name.object.name + "$" ;
		}
		res = res + name.symbol.name;
		return res;
	}
	
	static def toStr(EnumType type) {
		if (type.categorical != null){
			return type.categorical.identifier
		} 
		if (type.continuous != null){
			return type.continuous.identifier
		} 
		if (type.use != null){
			return type.use.toStr
		} 
		if (type.likelihood != null){
			return type.likelihood.identifier	
		} 
		if (type.target != null){
			return type.target.identifier 
		} 
	}
		
	static def String toStr(Distribution d) { 
		return d.identifier
	}

	static def String toStr(UseType l) { 
		return l.identifier
	}
	
	static def String toStr(Expression e){
		return e.conditionalExpression.toStr
	}
	
	static def toStr(ConditionalExpression e){ 
		var res = e.expression.toStr;
		if (e.expression1 != null){
			res  = res + "?" + e.expression1.toStr + ":" + e.expression2.toStr
		}
		return res;
	}

	static def toStr(OrExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next + iterator.next.toStr;
		}
		return res;
	}
	
	static def toStr(AndExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next + iterator.next.toStr;
		}
		return res;	
	}
	
	//(==, !=, <, > etc.)
	static def toStr(LogicalExpression e){
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
				res = res + e.operator + e.expression2.toStr;
			}
		}
		return res;
	}
	
	static def toStr(AdditiveExpression e){
		var res  = "";
		if (e.expression != null)
		{ 
			var iterator = e.expression.iterator();
			var operatorIterator  = e.operator.iterator();
			if (iterator.hasNext ) {
				res = iterator.next.toStr;
			}
			while (iterator.hasNext && operatorIterator.hasNext){
				res  = res + operatorIterator.next + iterator.next.toStr;
			}
		}
		if (e.string != null){		
			res  = e.string; 
		}
		return res;
	}
	
	static def toStr(MultiplicativeExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator  = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + operatorIterator.next + iterator.next.toStr;
		}
		return res;
	}
	
	static def String toStr(PowerExpression e) { 
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator  = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + operatorIterator.next+ iterator.next.toStr;
		}
		return res;
	}
	
	static def String toStr(UnaryExpression e){
		var res = "";
		if (e.expression != null){
			res = res + e.operator + e.expression.toStr
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
		if (e.functionCall != null) {
			return e.functionCall.toStr
		}
		if (e.attribute != null) {
			return e.attribute.toStr
		}
		return res;
	}	
	
	static def toStr(FunctionCall call){
		return call.identifier.toStr + "(...)";
	}
	
	static def String toStr(Primary p){
		if (p.number != null){
			return  p.number;
		}
		if (p.symbol != null){
			return p.symbol.toStr; 
		}
		if (p.vector != null) {
			return p.vector.toStr
		}
	}
	
	static def toStr(FullyQualifiedArgumentName name) { 
		var res = name.parent.toStr;
		for (s: name.selectors){
			res = res + s.toStr
		}
		return res;
	}
	
	static def toStr(Selector s) { 
		if (s.argumentName != null)
			return "." + s.argumentName.name;
		if (s.selector != null)
			return "[" + s.selector + "]";
	}
	
	static def toStr(Vector v) { 
		var res  = v.identifier + '(';
		var iterator = v.values.iterator();
		if (iterator.hasNext) {
			res = res + iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + ', ';
			res = res + iterator.next.toStr;
		}
		return res + ')';
	}
	
	static def toStr(ParExpression e){
		return "(" + e.expression.toStr + ")";
	}
	
	static def toStr(TargetBlock b){
		return b.externalCode.substring(3, b.externalCode.length - 3)
	}
}