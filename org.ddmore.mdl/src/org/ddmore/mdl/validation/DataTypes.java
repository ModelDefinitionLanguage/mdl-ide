/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * MDL type checking
 */
package org.ddmore.mdl.validation;

public class DataTypes {

	public static final Integer TYPE_UNDEFINED = 0;
	public static final Integer TYPE_STRING = 1;
	public static final Integer TYPE_INT    = 2;
	public static final Integer TYPE_REAL   = 3;
	public static final Integer TYPE_CATEGORICAL = 4;

	public static final Integer TYPE_VECTOR_UNDEFINED = 10;
	public static final Integer TYPE_VECTOR_STRING = 11;
	public static final Integer TYPE_VECTOR_INTEGER = 12;
	public static final Integer TYPE_VECTOR_REAL = 13;
	public static final Integer TYPE_VECTOR_CATEGORICAL = 14;	
	
	//Check that double value is not passed to an attribute where integer is expected 
	static boolean isIntegerValue(Number value){
		if (value.doubleValue() == value.intValue()) return true;
		return false;
	}

}
