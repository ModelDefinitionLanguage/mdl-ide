/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * MDL type checking
 */
package org.ddmore.mdl.types;

import org.ddmore.mdl.generator.MathPrinter;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.DistributionArgument;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.Vector;

public enum MdlDataType {

    TYPE_UNDEFINED,
    //Basic
    TYPE_STRING, TYPE_INT, TYPE_REAL, TYPE_BOOLEAN,
    //Restrictions of basic (to comply with PharmML)
    TYPE_NAT,TYPE_PNAT, TYPE_PREAL, TYPE_PROBABILITY,
    //Numeric vectors
    TYPE_VECTOR_INTEGER, TYPE_VECTOR_REAL, TYPE_VECTOR_BOOLEAN,
    //Restricted vectors
    TYPE_VECTOR_NAT, TYPE_VECTOR_PNAT, TYPE_VECTOR_PREAL,TYPE_VECTOR_PROBABILITY,
    //References to variables and objects, mathematical expressions
	TYPE_REF, TYPE_OBJ_REF, TYPE_EXPR, 
	//Nested lists
	TYPE_LIST, TYPE_ODE, TYPE_RANDOM_LIST, TYPE_DISTRIBUTION,
	//Enumerations
	TYPE_USE,           //{...}
	TYPE_VAR_TYPE,      //{continuous, categorical, LIKELIHOOD}
	TYPE_TARGET,        //{...}
	TYPE_INTERP,        //{constant, linear, nearest, spline, pchip, cubic}
	TYPE_RANDOM_EFFECT, //{var, sd} 
	TYPE_INPUT_FORMAT   //{nonmemFormat, eventFormat}
	;
    
	//Validates required type or reference
	static public boolean validateType(MdlDataType type, DistributionArgument arg){
		if (arg.getValue() != null){
			Primary p = arg.getValue();
			switch(type){
				case TYPE_UNDEFINED: return true;
				case TYPE_INT:  return (p.getNumber() != null)? isInteger(p.getNumber()): (p.getSymbol() != null);   
				case TYPE_REAL: return (p.getNumber() != null)? isReal(p.getNumber()): (p.getSymbol() != null);     
				case TYPE_NAT:   return (p.getNumber() != null)? isNatural(p.getNumber()): (p.getSymbol() != null);  
				case TYPE_PNAT:  return (p.getNumber() != null)? isPositiveNatural(p.getNumber()): (p.getSymbol() != null);  
				case TYPE_PREAL: return (p.getNumber() != null)? isPositiveReal(p.getNumber()): (p.getSymbol() != null); 
				case TYPE_PROBABILITY:  return (p.getNumber() != null)? isProbability(p.getNumber()): (p.getSymbol() != null);
				case TYPE_VECTOR_INTEGER: return (p.getVector() != null)? isVectorInteger(p.getVector()): false;
				case TYPE_VECTOR_REAL: return (p.getVector() != null)? isVectorReal(p.getVector()): false;
				case TYPE_VECTOR_NAT: return (p.getVector() != null)? isVectorNat(p.getVector()): false;
				case TYPE_VECTOR_PNAT: return (p.getVector() != null)? isVectorPNat(p.getVector()): false;
				case TYPE_VECTOR_PREAL: return (p.getVector() != null)? isVectorPReal(p.getVector()): false;
				case TYPE_VECTOR_PROBABILITY: return (p.getVector() != null)? isVectorProbability(p.getVector()): false;
				case TYPE_REF: return (p.getSymbol() != null)? true: false;  
				default:	return false; 
			}
		}
		if (arg.getDistribution() != null){
			if (type.equals(MdlDataType.TYPE_DISTRIBUTION)) return true;
		}
		if (arg.getComponent() != null){
			if (type.equals(MdlDataType.TYPE_RANDOM_LIST)) return true;
		}
		return false;
	}
	
	static public boolean validateType(MdlDataType type, AnyExpression expr){
		switch(type){
			case TYPE_UNDEFINED: return true;
			case TYPE_STRING: return (expr.getExpression() != null)? isString(expr.getExpression()): false;
			case TYPE_INT:  return (expr.getExpression() != null)? isInteger(expr.getExpression()): false;   
			case TYPE_REAL: return (expr.getExpression() != null)? isReal(expr.getExpression()): false;     
			case TYPE_BOOLEAN: return (expr.getExpression() != null)? isBoolean(expr.getExpression()): false;
			case TYPE_NAT:   return (expr.getExpression() != null)? isNatural(expr.getExpression()): false;  
			case TYPE_PNAT:  return (expr.getExpression() != null)? isPositiveNatural(expr.getExpression()): false;  
			case TYPE_PREAL: return (expr.getExpression() != null)? isPositiveReal(expr.getExpression()): false; 
			case TYPE_PROBABILITY:  return (expr.getExpression() != null)? isProbability(expr.getExpression()): false;
			case TYPE_VECTOR_INTEGER: return (expr.getVector() != null)? isVectorInteger(expr.getVector()): false;
			case TYPE_VECTOR_REAL: return (expr.getVector() != null)? isVectorReal(expr.getVector()): false;
			case TYPE_VECTOR_NAT: return (expr.getVector() != null)? isVectorNat(expr.getVector()): false;
			case TYPE_VECTOR_PNAT: return (expr.getVector() != null)? isVectorPNat(expr.getVector()): false;
			case TYPE_VECTOR_PREAL: return (expr.getVector() != null)? isVectorPReal(expr.getVector()): false;
			case TYPE_VECTOR_PROBABILITY: return (expr.getVector() != null)? isVectorProbability(expr.getVector()): false;
			case TYPE_REF: return (expr.getExpression() != null)? isReference(expr.getExpression()): false;  
			case TYPE_EXPR: return (expr.getExpression() != null)? true: false;  
			case TYPE_LIST: return (expr.getList() != null)? true: false;  
			case TYPE_ODE: return (expr.getOdeList() != null)? true: false;
			case TYPE_USE: return (expr.getType() != null)? isUseType(expr.getType()): false;
			case TYPE_VAR_TYPE: return (expr.getType() != null)? isVarType(expr.getType()): false;
			case TYPE_TARGET: return (expr.getType() != null)? isTargetType(expr.getType()): false;
			case TYPE_INTERP: return (expr.getType() != null)? isInterp(expr.getType()): false;
			case TYPE_RANDOM_EFFECT: return (expr.getType() != null)? isRandomEffect(expr.getType()): false;
			case TYPE_INPUT_FORMAT: return (expr.getType() != null)? isInputType(expr.getType()): false;
			
			default:	return false; 
		}
	}

	private static boolean isInputType(EnumType type) {
		if ((type.getInput() != null) && 
			(InputFormatType.FORMAT_VALUES.contains(type.getInput().getIdentifier()))) return true;
		return false;
	}
	
	private static boolean isInterp(EnumType type) {
		if ((type.getInterpolation() != null) && 
			(InterpolationType.INTERP_VALUES.contains(type.getInterpolation().getIdentifier()))) return true;
		return false;
	}

	private static boolean isRandomEffect(EnumType type) {
		if ((type.getVariability() != null) && (
			RandomEffectType.RE_VALUES.contains(type.getVariability().getIdentifier()))) return true;
		return false;
	}

	private static boolean isTargetType(EnumType type) {
		if ((type.getTarget() != null && 
			(TargetCodeType.TARGET_VALUES.contains(type.getTarget().getIdentifier())))) return true;
		return false;
	}

	private static boolean isVarType(EnumType type) {
		if ((type.getCategorical() != null) || (type.getContinuous() != null) || (type.getLikelihood() != null)) return true;
		return false;
	}

	private static boolean isUseType(EnumType type) {
		if ((type.getUse() != null) &&
			(UseType.USE_VALUES.contains(type.getUse().getIdentifier()))) return true;
		return false;
	}

	private static boolean isReference(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isReference(expr.getConditionalExpression().getExpression1()) 
						&& isReference(expr.getConditionalExpression().getExpression2());
			return isReference(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			if (orExpr.getExpression().size() > 1) return false;
			AndExpression andExpr = orExpr.getExpression().get(0);
			if (andExpr.getExpression().size() > 1) return false;
			LogicalExpression logicExpr = andExpr.getExpression().get(0);
			if (logicExpr.getExpression1() != null){
				if (logicExpr.getExpression2() != null) return false;
				if (logicExpr.getExpression1().getString() != null) return false;
				if(logicExpr.getExpression1().getExpression().size() > 1) return false;
				MultiplicativeExpression multExpr = logicExpr.getExpression1().getExpression().get(0);
				if (multExpr.getExpression().size() > 1) return false;
				PowerExpression powerExpr = multExpr.getExpression().get(0);
				if (powerExpr.getExpression().size() > 1) return false;
				UnaryExpression unaryExpr = powerExpr.getExpression().get(0);
				if (unaryExpr.getSymbol() != null) return true;
			}
		}
		return false;
	}

	private static boolean isVectorReal(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() == null) return false;
			}
		}
		return true;
	}
	
	private static boolean isVectorNat(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorNat(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() == null) return false;
				try{
					Integer x = Integer.parseInt(p.getNumber());
					if (x < 0) return false;
				} catch (NumberFormatException e){
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean isVectorPNat(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorNat(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() == null) return false;
				try{
					Integer x = Integer.parseInt(p.getNumber());
					if (x <= 0) return false;
				} catch (NumberFormatException e){
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean isVectorPReal(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorPReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() == null) return false;
				try{
					Double x = Double.parseDouble(p.getNumber());
					if (x <= 0) return false;
				} catch (NumberFormatException e){
					return false;
				}
			}
		}
		return true;
	}
	
	
	private static boolean isVectorProbability(Vector v) {
		Double total = 0.;
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorPReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() == null) return false;
				try{
					Double x = Double.parseDouble(p.getNumber());
					if ((x < 0) || (x > 1)) return false;
					total += x;
				} catch (NumberFormatException e){
					return false;
				}
			}
		}
		return (total.equals(1.));
	}
	
	
	private static boolean isVectorInteger(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorInteger(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() == null) return false;
				try{
					Integer.parseInt(p.getNumber());
				} catch (NumberFormatException e){
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isBoolean(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isBoolean(expr.getConditionalExpression().getExpression1()) 
						&& isBoolean(expr.getConditionalExpression().getExpression2());
			return isBoolean(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			if (orExpr.getExpression().size() > 1) return true;
			AndExpression andExpr = orExpr.getExpression().get(0);
			if (andExpr.getExpression().size() > 1) return true;
			LogicalExpression logicExpr = andExpr.getExpression().get(0);
			if (logicExpr.getBoolean() != null) return true;
			if ((logicExpr.getExpression1() != null) && (logicExpr.getExpression2() != null)) return true;
		}
		return false;
	}
	
	private static boolean isPositiveNatural(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isPositiveNatural(expr.getConditionalExpression().getExpression1()) 
						&& isPositiveNatural(expr.getConditionalExpression().getExpression2());
			return isPositiveNatural(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isPositiveNatural(MathPrinter.toStr(orExpr));
		}
	}
	
	private static boolean isPositiveNatural(String value) {
		try{
			Integer x = Integer.parseInt(value);
			if (x > 0) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}

	private static boolean isNatural(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isNatural(expr.getConditionalExpression().getExpression1()) 
						&& isNatural(expr.getConditionalExpression().getExpression2());
			return isNatural(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isNatural(MathPrinter.toStr(orExpr));
		}
	}	
	
	private static boolean isNatural(String value) {
		try{
			Integer x = Integer.parseInt(value);
			if (x >= 0) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}

	private static boolean isInteger(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isInteger(expr.getConditionalExpression().getExpression1()) 
						&& isInteger(expr.getConditionalExpression().getExpression2());
			return isInteger(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isInteger(MathPrinter.toStr(orExpr));
		}
	}
	
	private static boolean isInteger(String value) {
		try{
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	
	private static boolean isProbability(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isProbability(expr.getConditionalExpression().getExpression1()) 
					&& isProbability(expr.getConditionalExpression().getExpression2());
			return isProbability(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isProbability(MathPrinter.toStr(orExpr));
		}
	}
	
	private static boolean isProbability(String value) {
		try{
			Double x = Double.parseDouble(value);
			if ((x >= 0) && (x <=1)) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}
	
	private static boolean isPositiveReal(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isPositiveReal(expr.getConditionalExpression().getExpression1()) 
					&& isPositiveReal(expr.getConditionalExpression().getExpression2());
			return isPositiveReal(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isPositiveReal(MathPrinter.toStr(orExpr));
		}
	}
	
	private static boolean isPositiveReal(String value) {
		try{
			Double x = Double.parseDouble(value);
			if (x > 0 ) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}
	
	private static boolean isReal(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isReal(expr.getConditionalExpression().getExpression1()) 
					&& isReal(expr.getConditionalExpression().getExpression2());
			return isReal(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isReal(MathPrinter.toStr(orExpr));
		}
	}
	
	private static boolean isReal(String value) {
		try{
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}	
	
	private static boolean isString(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isString(expr.getConditionalExpression().getExpression1()) 
						&& isString(expr.getConditionalExpression().getExpression2());
			return isString(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			if (orExpr.getExpression().size() > 1) return false;
			AndExpression andExpr = orExpr.getExpression().get(0);
			if (andExpr.getExpression().size() > 1) return false;
			LogicalExpression logicExpr = andExpr.getExpression().get(0);
			if (logicExpr.getExpression1() != null){
				if (logicExpr.getExpression2() != null) return false;
				if (logicExpr.getExpression1().getString() != null) return true;
				return false;
			}
		}
		return false;	
	}	
}
