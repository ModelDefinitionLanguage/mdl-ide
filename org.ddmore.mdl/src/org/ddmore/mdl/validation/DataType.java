/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * MDL type checking
 */
package org.ddmore.mdl.validation;

import org.ddmore.mdl.mdl.AnyExpression;

public enum DataType {

    TYPE_UNDEFINED,
    //Basic
    TYPE_STRING, TYPE_INT, TYPE_REAL, TYPE_BOOLEAN,
    //PharmML basic
    TYPE_NAT,TYPE_PNAT, TYPE_PREAL, TYPE_PROBABILITY,
    //Numeric vectors
    TYPE_VECTOR_INTEGER, TYPE_VECTOR_REAL, TYPE_VECTOR_BOOLEAN,
    //TYPE_VECTOR_UNDEFINED, //TYPE_VECTOR_STRING,
    //PharmML vectors
    TYPE_VECTOR_NAT, TYPE_VECTOR_PNAT, TYPE_VECTOR_PREAL,TYPE_VECTOR_PROBABILITY,
    //Validation of attribute values
	TYPE_ID, TYPE_EXPR,
	//Composite/nested attributes
	TYPE_LIST, TYPE_ODE, TYPE_DISTRIBUTION,
	//Enums
	TYPE_USE,     //Check grammar for UseType
	TYPE_CC,      //continuous vs. categorical
	TYPE_TARGET,  //Check grammar for TargetLanguage 
	//Restricted strings (why are they not enumerations?)
	TYPE_FILE,  //file URI
	TYPE_INPUT, //input format (e.g., "NONMEM")
	TYPE_IDV,   //idv in DESIGN block (e.g., "linear")
	TYPE_RANDOM_EFFECT; //Random effects "VAR" vs. "SD" - why strings?
	
	//Check that double value is not passed to an attribute where integer is expected 
	static boolean isIntegerValue(Number value){
		if (value.doubleValue() == value.intValue()) return true;
		return false;
	}
	
	static boolean validateType(DataType type, AnyExpression expr){
		switch(type){
		case TYPE_STRING: 
		case TYPE_INT:    
		case TYPE_REAL:   
		case TYPE_BOOLEAN:
		case TYPE_NAT:
		case TYPE_PNAT:
		case TYPE_PREAL:
		case TYPE_PROBABILITY:
		case TYPE_VECTOR_INTEGER:
		case TYPE_VECTOR_REAL:
		case TYPE_VECTOR_NAT:
		case TYPE_VECTOR_PNAT:
		case TYPE_VECTOR_PREAL:
		case TYPE_VECTOR_PROBABILITY:
		case TYPE_ID:
		case TYPE_EXPR:
		case TYPE_LIST:
		case TYPE_ODE:
		case TYPE_DISTRIBUTION:
		case TYPE_USE:
		case TYPE_CC:
		case TYPE_TARGET:
		case TYPE_FILE:
		case TYPE_INPUT:
		case TYPE_IDV:
		case TYPE_RANDOM_EFFECT:
		default:	return true; //for now!
		}
	}

}
