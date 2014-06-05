/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * MDL type checking
 */
package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.DistributionArgument;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.validation.MdlJavaValidator;
import org.ddmore.mdl.validation.Utils;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

public enum MdlDataType {

    TYPE_UNDEFINED,
    TYPE_VOID,
    /*Basic*/
    TYPE_STRING, TYPE_INT, TYPE_REAL, TYPE_BOOLEAN,
    //Restrictions of basic (to comply with PharmML)
    TYPE_NAT, TYPE_PNAT, TYPE_PREAL, TYPE_PROBABILITY,
    //References to variables and objects, mathematical expressions
	TYPE_REF, TYPE_OBJ_REF, TYPE_OBJ_REF_MODEL, TYPE_OBJ_REF_DATA, TYPE_OBJ_REF_PARAM, TYPE_EXPR, 
	//Nested lists
	TYPE_LIST, TYPE_ODE, TYPE_RANDOM_LIST, 
    
	/*Vectors*/
    //Numeric vectors
    TYPE_VECTOR_STRING, TYPE_VECTOR_INT, TYPE_VECTOR_REAL, TYPE_VECTOR_BOOLEAN, 
    //Restricted vectors
    TYPE_VECTOR_NAT, TYPE_VECTOR_PNAT, TYPE_VECTOR_PREAL,TYPE_VECTOR_PROBABILITY,
    //Reference vectors
    TYPE_VECTOR_REF,
	
    /*Enumerations*/
	TYPE_USE,           //see 'UseType' in MDL grammar
	TYPE_VAR_TYPE,      //{continuous, categorical, likelihood, M2LL}
	TYPE_TARGET,        //{NMTRAN_CODE, MLXTRAN_CODE, PML_CODE, BUGS_CODE, R_CODE MATLAB_CODE}
	TYPE_INTERP,        //{constant, linear, nearest, spline, pchip, cubic}
	TYPE_RANDOM_EFFECT, //{VAR, SD} 
	TYPE_INPUT_FORMAT,  //{nonmemFormat, eventFormat}
	TYPE_DISTRIBUTION   //see 'Distribution' in MDL grammar
	;
    
    public final static List<String> CONSTANTS = Arrays.asList(
    		"INF", "-INF", "T", "NEWIND", "IREP", "ICALL", "MIXEST", "MIXNUM");
	
	//Validates required type or reference
	static public boolean validateType(MdlDataType type, DistributionArgument arg){
		if (arg.getValue() != null){
			Primary p = arg.getValue();
			switch(type){
				case TYPE_UNDEFINED: return true;
				//Basic
				case TYPE_INT:  return (p.getNumber() != null)? isInteger(p.getNumber()): (p.getSymbol() != null);   
				case TYPE_REAL: return (p.getNumber() != null)? isReal(p.getNumber()): (p.getSymbol() != null);     
				//Restrictions
				case TYPE_NAT:   return (p.getNumber() != null)? isNatural(p.getNumber()): (p.getSymbol() != null);  
				case TYPE_PNAT:  return (p.getNumber() != null)? isPositiveNatural(p.getNumber()): (p.getSymbol() != null);  
				case TYPE_PREAL: return (p.getNumber() != null)? isPositiveReal(p.getNumber()): (p.getSymbol() != null); 
				case TYPE_PROBABILITY:  return (p.getNumber() != null)? isProbability(p.getNumber()): (p.getSymbol() != null);
				//References
				case TYPE_REF: return (p.getSymbol() != null)? true: false;  
				//Vectors
				case TYPE_VECTOR_INT: return (p.getVector() != null)? isVectorInteger(p.getVector()): false;
				case TYPE_VECTOR_REAL: return (p.getVector() != null)? isVectorReal(p.getVector()): false;
				//Vectors of restrictions
				case TYPE_VECTOR_NAT: return (p.getVector() != null)? isVectorNat(p.getVector()): false;
				case TYPE_VECTOR_PNAT: return (p.getVector() != null)? isVectorPNat(p.getVector()): false;
				case TYPE_VECTOR_PREAL: return (p.getVector() != null)? isVectorPReal(p.getVector()): false;
				case TYPE_VECTOR_PROBABILITY: return (p.getVector() != null)? isVectorProbability(p.getVector()): false;
				//Vectors of references
				case TYPE_VECTOR_REF: return (p.getVector() != null)? isVectorReference(p.getVector()): false;  
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
			//Basic
			case TYPE_STRING: return (expr.getExpression() != null)? isString(expr.getExpression()): false;
			case TYPE_INT:  return (expr.getExpression() != null)? isInteger(expr.getExpression()): false;   
			case TYPE_REAL: return (expr.getExpression() != null)? isReal(expr.getExpression()): false;     
			case TYPE_BOOLEAN: return (expr.getExpression() != null)? isBoolean(expr.getExpression()): false;
			//Restrictions
			case TYPE_NAT:   return (expr.getExpression() != null)? isNatural(expr.getExpression()): false;  
			case TYPE_PNAT:  return (expr.getExpression() != null)? isPositiveNatural(expr.getExpression()): false;  
			case TYPE_PREAL: return (expr.getExpression() != null)? isPositiveReal(expr.getExpression()): false; 
			case TYPE_PROBABILITY:  return (expr.getExpression() != null)? isProbability(expr.getExpression()): false;
			//References
			case TYPE_REF: return (expr.getExpression() != null)? isReference(expr.getExpression()): false;  
			case TYPE_EXPR: return (expr.getExpression() != null)? true: false;  
			//References to objects
			case TYPE_OBJ_REF: return (expr.getExpression() != null)? isObjectReference(expr.getExpression()): false;
			case TYPE_OBJ_REF_MODEL: return (expr.getExpression() != null)? isModelObjectReference(expr.getExpression()): false;
			case TYPE_OBJ_REF_DATA: return (expr.getExpression() != null)? isDataObjectReference(expr.getExpression()): false;
			case TYPE_OBJ_REF_PARAM: return (expr.getExpression() != null)? isParameterObjectReference(expr.getExpression()): false;
			//Nested lists
			case TYPE_LIST: return (expr.getList() != null)? true: false;  
			case TYPE_ODE: return (expr.getOdeList() != null)? true: false;
			//Vectors
			case TYPE_VECTOR_INT: return (expr.getVector() != null)? isVectorInteger(expr.getVector()): false;
			case TYPE_VECTOR_REAL: return (expr.getVector() != null)? isVectorReal(expr.getVector()): false;
			//Vectors of restrictions
			case TYPE_VECTOR_NAT: return (expr.getVector() != null)? isVectorNat(expr.getVector()): false;
			case TYPE_VECTOR_PNAT: return (expr.getVector() != null)? isVectorPNat(expr.getVector()): false;
			case TYPE_VECTOR_PREAL: return (expr.getVector() != null)? isVectorPReal(expr.getVector()): false;
			case TYPE_VECTOR_PROBABILITY: return (expr.getVector() != null)? isVectorProbability(expr.getVector()): false;
			
			//Enumerations
			case TYPE_USE: return (expr.getType() != null)? isUseType(expr.getType()): false;
			case TYPE_VAR_TYPE: return (expr.getType() != null)? isVarType(expr.getType()): false;
			case TYPE_TARGET: return (expr.getType() != null)? isTargetType(expr.getType()): false;
			case TYPE_INTERP: return (expr.getType() != null)? isInterp(expr.getType()): false;
			case TYPE_RANDOM_EFFECT: return (expr.getType() != null)? isRandomEffect(expr.getType()): false;
			case TYPE_INPUT_FORMAT: return (expr.getType() != null)? isInputType(expr.getType()): false;
			default: return false; 
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//Validate vector types
	//Note: references in numeric vectors are allowed and not checked!
	////////////////////////////////////////////////////////////////////////////////
	private static boolean isVectorReal(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorReal(p.getVector());
				if (!ok) return false;
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
				if (p.getNumber() != null)
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
				if (p.getNumber() != null)
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
				if (p.getNumber() != null)
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
		boolean containsReference = false;
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorPReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() != null)
					try{
						Double x = Double.parseDouble(p.getNumber());
						if ((x < 0) || (x > 1)) return false;
						total += x;
					} catch (NumberFormatException e){
						return false;
					}
				else containsReference = true;
			}
		}
		return ((containsReference && (total <= 1.)) || (total == 1.));
	}
		
	private static boolean isVectorInteger(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorInteger(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() != null) 
					try{
						Integer.parseInt(p.getNumber());
					} catch (NumberFormatException e){
						return false;
					}
			}
		}
		return true;
	}
	
	private static boolean isVectorReference(Vector v){
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorReference(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getSymbol() == null) return false;
			}
		}
		return true;	
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//Validate enumerations
	//////////////////////////////////////////////////////////////////////////////////

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
		if (type.getType() != null) return true;
		return false;
	}

	private static boolean isUseType(EnumType type) {
		if ((type.getUse() != null) &&
			(UseType.USE_VALUES.contains(type.getUse().getIdentifier()))) return true;
		return false;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	//Validate references
	/////////////////////////////////////////////////////////////////////////////////////
	
	private static boolean isObjectReference(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() == null){
			SymbolName s = getReference(expr.getConditionalExpression().getExpression());
			if (s!= null) {
				Mcl mcl = (Mcl) expr.eResource().getContents().get(0);
				if (mcl != null)
					return Utils.getDeclaredObjects(mcl).containsKey(s.getName());
			}
		}
		return false;
	}
	
	private static boolean isModelObjectReference(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() == null){
			SymbolName s = getReference(expr.getConditionalExpression().getExpression());
			if (s!= null) {
				Mcl mcl = (Mcl) expr.eResource().getContents().get(0);
				if (mcl != null)
					return (Utils.getDeclaredObjects(mcl).get(s.getName()) == MdlDataType.TYPE_OBJ_REF_MODEL);
			}
		}
		return false;
	}

	private static boolean isDataObjectReference(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() == null){
			SymbolName s = getReference(expr.getConditionalExpression().getExpression());
			if (s!= null) {
				Mcl mcl = (Mcl) expr.eResource().getContents().get(0);
				if (mcl != null)
					return (Utils.getDeclaredObjects(mcl).get(s.getName()) == MdlDataType.TYPE_OBJ_REF_DATA);
			}
		}
		return false;
	}

	private static boolean isParameterObjectReference(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() == null){
			SymbolName s = getReference(expr.getConditionalExpression().getExpression());
			if (s!= null) {
				Mcl mcl = (Mcl) expr.eResource().getContents().get(0);
				if (mcl != null)
					return (Utils.getDeclaredObjects(mcl).get(s.getName()) == MdlDataType.TYPE_OBJ_REF_PARAM);
			}
		}
		return false;
	}

	private static boolean isReference(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null){
				return 
					isReference(expr.getConditionalExpression().getExpression1()) && 
					isReference(expr.getConditionalExpression().getExpression2());
			}
			return isReference(expr.getConditionalExpression().getExpression1());
		} else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			if (getReference(orExpr) != null) return true;
		}
		return false;
	}

	public static SymbolName getReference(OrExpression orExpr) {
		if (orExpr.getExpression().size() > 1) return null;
		AndExpression andExpr = orExpr.getExpression().get(0);
		if (andExpr.getExpression().size() > 1) return null;
		LogicalExpression logicExpr = andExpr.getExpression().get(0);
		if (logicExpr.getExpression1() != null){
			if (logicExpr.getExpression2() != null) return null;
			if (logicExpr.getExpression1().getString() != null) return null;
			if(logicExpr.getExpression1().getExpression().size() > 1) return null;
			MultiplicativeExpression multExpr = logicExpr.getExpression1().getExpression().get(0);
			if (multExpr.getExpression().size() > 1) return null;
			PowerExpression powerExpr = multExpr.getExpression().get(0);
			if (powerExpr.getExpression().size() > 1) return null;
			UnaryExpression unaryExpr = powerExpr.getExpression().get(0);
			if (unaryExpr.getSymbol() != null) return unaryExpr.getSymbol();
		}
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////
	//Validate basic types
	//////////////////////////////////////////////////////////////////////////////////
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
			return isPositiveNatural(MdlPrinter.getInstance().toStr(orExpr));
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
			return isNatural(MdlPrinter.getInstance().toStr(orExpr));
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
			return isInteger(MdlPrinter.getInstance().toStr(orExpr));
		}
	}
	
	private static boolean isInteger(String value) {
		try{
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e){
			return CONSTANTS.contains(value);
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
			return isProbability(MdlPrinter.getInstance().toStr(orExpr));
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
			return isPositiveReal(MdlPrinter.getInstance().toStr(orExpr));
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
			return isReal(MdlPrinter.getInstance().toStr(orExpr));
		}
	}
	
	private static boolean isReal(String value) {
		try{
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e){
			return CONSTANTS.contains(value);
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
