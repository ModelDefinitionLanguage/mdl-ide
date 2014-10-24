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
import org.ddmore.mdl.mdl.DistributionType;
import org.ddmore.mdl.mdl.EnumType; 
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.ExpressionBranch;
import org.ddmore.mdl.mdl.IndividualVarType;
import org.ddmore.mdl.mdl.InputFormatType;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.TargetType;
import org.ddmore.mdl.mdl.TrialType;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VariabilityType;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.validation.FunctionValidator;
import org.ddmore.mdl.validation.Utils;
import eu.ddmore.converter.mdlprinting.MdlPrinter;

public enum MdlDataType {

    TYPE_UNDEFINED,
    /*Basic*/
    TYPE_STRING, TYPE_INT, TYPE_REAL, TYPE_BOOLEAN,
    //Restrictions of basic (to comply with PharmML)
    TYPE_NAT, TYPE_PNAT, TYPE_PREAL, TYPE_PROBABILITY,
    //References to variables and mathematical expressions
	TYPE_REF, TYPE_EXPR,  //TODO remove after type checking is supported
	//References to objects
	TYPE_OBJ_REF, TYPE_OBJ_REF_MODEL, TYPE_OBJ_REF_DATA, TYPE_OBJ_REF_PARAM, TYPE_OBJ_REF_TASK, TYPE_OBJ_REF_DESIGN,
	//Nested lists
	TYPE_LIST, TYPE_RANDOM_LIST, 
    
	/*Vectors*/
    //Numeric vectors
    TYPE_VECTOR_STRING, TYPE_VECTOR_INT, TYPE_VECTOR_REAL, //TYPE_VECTOR_BOOLEAN, 
    //Restricted vectors
    TYPE_VECTOR_NAT, TYPE_VECTOR_PNAT, TYPE_VECTOR_PREAL, TYPE_VECTOR_PROBABILITY,
    //Reference vectors
    TYPE_VECTOR_REF, TYPE_VECTOR_EXPR,

	/*String restrictions*/
	TYPE_TRANS,          //{log, logit, probit}

    /*Enumerations*/
	TYPE_VAR_TYPE,       //{continuous, categorical, likelihood, M2LL}
	TYPE_USE,            //see 'UseType' in MDL grammar
	TYPE_TARGET,         //{NMTRAN_CODE, MLXTRAN_CODE, PML_CODE, BUGS_CODE, R_CODE MATLAB_CODE}
	TYPE_RANDOM_EFFECT,  //{VAR, SD, CORR, COV} 
	TYPE_INPUT_FORMAT,   //{nonmemFormat, eventFormat}
	TYPE_DISTRIBUTION,   //see 'Distribution' in MDL grammar
	TYPE_INDIVIDUAL_VAR, //{linear, gaussian}
	TYPE_CONTINUOUS, 	 //{continuous}
	TYPE_TRIAL           //{simple, sequential, combined}
	;
    
	static public boolean validateType(MdlDataType type, Expression expr){
		Boolean res = validateType(type, expr.getExpression());
		if (expr.getWhenBranches() != null){
			for (ExpressionBranch e: expr.getWhenBranches()){
				res = res && validateType(type, e.getExpression());
			}
		}
		if (expr.getElseExpression() != null){
			res = res && validateType(type, expr.getElseExpression());
		}
		return res;
	}
	
	private static boolean validateType(MdlDataType type, OrExpression expr){
		switch(type){
			case TYPE_UNDEFINED: return true;
			//Basic
			case TYPE_STRING: return isString(expr);
			case TYPE_INT:  return isInteger(expr);   
			case TYPE_REAL: return isReal(expr);     
			case TYPE_BOOLEAN: return isBoolean(expr);
			//Restrictions
			case TYPE_NAT:   return isNatural(expr);  
			case TYPE_PNAT:  return isPositiveNatural(expr);  
			case TYPE_PREAL: return isPositiveReal(expr); 
			case TYPE_PROBABILITY: return isProbability(expr);
			//References
			case TYPE_REF: return isReference(expr);  
			case TYPE_EXPR: return true;  
			//References to objects
			case TYPE_OBJ_REF: return isObjectReference(expr);
			case TYPE_OBJ_REF_MODEL: validObjectTypeReference(expr, TYPE_OBJ_REF_MODEL);
			case TYPE_OBJ_REF_DATA: return validObjectTypeReference(expr, TYPE_OBJ_REF_DATA);
			case TYPE_OBJ_REF_PARAM: return validObjectTypeReference(expr, TYPE_OBJ_REF_PARAM);
			case TYPE_OBJ_REF_TASK: return validObjectTypeReference(expr, TYPE_OBJ_REF_TASK);
			case TYPE_OBJ_REF_DESIGN: return validObjectTypeReference(expr, TYPE_OBJ_REF_DESIGN);
			//String restriction
			case TYPE_TRANS: return isTransformationOperator(expr);  
			default: return false;
		}
	}
	
	static public boolean validateType(MdlDataType type, Vector expr){
		switch(type){
			case TYPE_VECTOR_INT: return isVectorInteger(expr);
			case TYPE_VECTOR_REAL: return isVectorReal(expr);
			case TYPE_VECTOR_STRING: return isVectorString(expr);  
			//Vectors of references
			case TYPE_VECTOR_REF: return isVectorReference(expr);  
			case TYPE_VECTOR_EXPR: return true;
			//Vectors of restrictions
			case TYPE_VECTOR_NAT: return isVectorNat(expr);
			case TYPE_VECTOR_PNAT: return isVectorPNat(expr);
			case TYPE_VECTOR_PREAL: return isVectorPReal(expr);
			case TYPE_VECTOR_PROBABILITY: return isVectorProbability(expr);
			default: return false;
		}
	}

	static public boolean validateType(MdlDataType type, EnumType expr){
		switch(type){
			case TYPE_VAR_TYPE: return expr.getType() != null;
			case TYPE_CONTINUOUS: return (expr.getType() != null && (expr.getType().getContinuous() != null));
			case TYPE_USE: return (expr.getUse() != UseType.NO_USE);
			case TYPE_TARGET: return (expr.getTarget() != TargetType.NO_TARGET);
			case TYPE_RANDOM_EFFECT: return (expr.getVariability() != VariabilityType.NO_VARIABILITY);
			case TYPE_INPUT_FORMAT: return (expr.getInput() != InputFormatType.NO_INPUT_FORMAT);
			case TYPE_TRIAL: return (expr.getTrial() != TrialType.NO_TRIAL);
			case TYPE_INDIVIDUAL_VAR: return (expr.getIndividualVar() != IndividualVarType.NO_INDIVIDUAL_VAR);
			case TYPE_DISTRIBUTION: return (expr.getDistribution() != DistributionType.NO_DISTRIBUTION);
			default: return false; 
		}
	}
	
	static public boolean validateType(MdlDataType type, RandomList expr){
		return (type == TYPE_RANDOM_LIST);
	}	
	
	public static boolean isEnumType(AnyExpression expr) {
		List<MdlDataType> types = Arrays.asList(
			TYPE_VAR_TYPE,
			TYPE_CONTINUOUS,
			TYPE_USE,
			TYPE_TARGET,
			TYPE_RANDOM_EFFECT,
			TYPE_INPUT_FORMAT,
			TYPE_TRIAL,
			TYPE_INDIVIDUAL_VAR,
			TYPE_DISTRIBUTION 
		);
		return validateType(types, expr);
	}
	
	static public boolean validateType(MdlDataType type, AnyExpression expr){
		if (expr.getExpression() != null)
			return validateType(type, expr.getExpression());
		if ((expr.getList() != null) && (type == TYPE_LIST)) return true;
		if (expr.getVector() != null)
			return validateType(type, expr.getVector());
		if (expr.getType() != null)
			return validateType(type, expr.getType());
		return false;
	}
	
	static public boolean validateType(List<MdlDataType> types, AnyExpression expr){
		for (MdlDataType type: types){
			if (validateType(type, expr)) return true;
		}
		return false;
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

	private static boolean isVectorString(Vector v){
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorString(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getString() == null) return false;
			}
		}
		return true;	
	}

	/////////////////////////////////////////////////////////////////////////////////////
	//Validate references
	/////////////////////////////////////////////////////////////////////////////////////
	
	private static boolean isObjectReference(OrExpression expr) {
		SymbolName s = getReference(expr);
		if (s != null) {
			Mcl mcl = (Mcl) expr.eResource().getContents().get(0);
			if (mcl != null)
				return Utils.getDeclaredObjects(mcl).containsKey(s.getName());
		}
		return false;
	}
	
	private static boolean validObjectTypeReference(OrExpression orExpr, MdlDataType type) {
		SymbolName s = getReference(orExpr);
		if (s != null) {
			Mcl mcl = (Mcl) orExpr.eResource().getContents().get(0);
			if (mcl != null)
				return (Utils.getDeclaredObjects(mcl).get(s.getName()) == type);
		}
		return false;
	}

	private static boolean isReference(OrExpression expr) {
			return (getReference(expr) != null);
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
	private static boolean isBoolean(OrExpression orExpr) {
		if (orExpr.getExpression().size() > 1) return true;
		AndExpression andExpr = orExpr.getExpression().get(0);
		if (andExpr.getExpression().size() > 1) return true;
		LogicalExpression logicExpr = andExpr.getExpression().get(0);
		if (logicExpr.getBoolean() != null) return true;
		if ((logicExpr.getExpression1() != null) && (logicExpr.getExpression2() != null)) return true;
		if (logicExpr.getExpression1().getExpression().size() == 1){
			if (logicExpr.getExpression1().getExpression().get(0).getExpression().size() == 1){
				PowerExpression p = logicExpr.getExpression1().getExpression().get(0).getExpression().get(0);
				if (p.getExpression().get(0).getParExpression() != null)
					return isBoolean(p.getExpression().get(0).getParExpression().getExpression());
			}
		}
		return false;
	}
	
	private static boolean isPositiveNatural(OrExpression orExpr) {
		return isPositiveNatural(MdlPrinter.getInstance().toStr(orExpr));
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

	private static boolean isNatural(OrExpression orExpr) {
		return isNatural(MdlPrinter.getInstance().toStr(orExpr));
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

	private static boolean isInteger(OrExpression orExpr) {
		return isInteger(MdlPrinter.getInstance().toStr(orExpr));
	}
	
	private static boolean isNumericConstant(String value) {
		if (value.equals("INF")) return true;
		return false;
	}
		
	private static boolean isInteger(String value) {
		try{
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e){
			return isNumericConstant(value);
		}
	}
	
	private static boolean isProbability(OrExpression orExpr) {
		return isProbability(MdlPrinter.getInstance().toStr(orExpr));
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
	
	private static boolean isPositiveReal(OrExpression orExpr) {
		return isPositiveReal(MdlPrinter.getInstance().toStr(orExpr));
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
	
	private static boolean isReal(OrExpression orExpr) {
		return isReal(MdlPrinter.getInstance().toStr(orExpr));
	}
	
	private static boolean isReal(String value) {
		try{
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e){
			return isNumericConstant(value);
		}
	}	
	
	private static boolean isString(OrExpression orExpr) {
		if (orExpr.getExpression().size() > 1) return false;
		AndExpression andExpr = orExpr.getExpression().get(0);
		if (andExpr.getExpression().size() > 1) return false;
		LogicalExpression logicExpr = andExpr.getExpression().get(0);
		if (logicExpr.getExpression1() != null){
			if (logicExpr.getExpression2() != null) return false;
			if (logicExpr.getExpression1().getString() != null) return true;
			return false;
		}
		return false;	
	}	
	
	private static boolean isTransformationOperator(OrExpression expr){
		String trans = MdlPrinter.getInstance().toStr(expr);
		return (FunctionValidator.funct_standard1.contains(trans));
	}
}
