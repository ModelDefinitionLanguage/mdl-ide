/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * Container for specifying requirements for distribution attributes
 */
package org.ddmore.mdl.validation;

public class Attribute {

	String name = "undefined";
	DataType type = DataType.TYPE_UNDEFINED;
	Boolean mandatory = false;
	String defaultValue = "";
	
	public Attribute(String name, DataType type, Boolean mandatory){
		this.name = name;
		this.type = type;
		this.mandatory = mandatory;
	}	
	
	public Attribute(String name, DataType type, Boolean mandatory, String defaultValue){
		this(name, type, mandatory);
		this.defaultValue = defaultValue;
	}	
	
	public String getName(){
		return name;
	}
	
	public DataType getType(){
		return type;
	}

	public Boolean getMandatory(){
		return mandatory;
	}
	
	public String getDefaultValue(){
		return defaultValue;
	}
}
