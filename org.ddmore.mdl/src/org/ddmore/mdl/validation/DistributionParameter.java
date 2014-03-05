/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * Container for specifying requirements for distribution attributes
 */
package org.ddmore.mdl.validation;

public class DistributionParameter {

	String name = "undefined";
	Integer type = DataTypes.TYPE_UNDEFINED;
	Number minValue = null;
	Number maxValue = null;
	Boolean mandatory = false;
	
	DistributionParameter(String _name, Integer _type, Boolean _mandatory){
		name = _name;
		type = _type;
		mandatory = _mandatory;
	}	
	
	DistributionParameter(String _name, Integer _type, Boolean _mandatory, Number _minValue, Number _maxValue){
		this(_name, _type, _mandatory);
		minValue = _minValue;
		maxValue = _maxValue;
	}	

	/*DistributionParameter(String _name, Integer _type, Boolean _mandatory, Number _minValue, Number _maxValue, String _dimension){
		this(_name, _type, _mandatory, _minValue, _maxValue);
		dimension = _dimension;
	}*/	

	//Check that numeric value is in the range
	boolean checkDoubleValueRange(Number value){
		if ((value.doubleValue() >= minValue.doubleValue()) && (value.doubleValue() <= maxValue.doubleValue())) return true;
		return false;
	}
}
