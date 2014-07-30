/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * Container for specifying requirements for distribution attributes
 */
package org.ddmore.mdl.validation;

import org.ddmore.mdl.types.MdlDataType;

public class Attribute {

	String name = "undefined";
	MdlDataType type = MdlDataType.TYPE_UNDEFINED;
	Boolean mandatory = false;
	String defaultValue = "";
	Boolean isPrivate = false; //Can be used to control access to list attributes
	Boolean isFinal = false;   //TODO: make all attributes by default invisible and not modifiable
	
	public Attribute(String name, MdlDataType type, Boolean mandatory){
		this.name = name;
		this.type = type;
		this.mandatory = mandatory;
	}	
	
	public Attribute(String name, MdlDataType type, Boolean mandatory, String defaultValue){
		this(name, type, mandatory);
		this.defaultValue = defaultValue;
	}	
	
	public String getName(){
		return name;
	}
	
	public MdlDataType getType(){
		return type;
	}

	public Boolean isMandatory(){
		return mandatory;
	}
	
	public String getDefaultValue(){
		return defaultValue;
	}
	
	public Boolean isPrivate(){
		return isPrivate;
	}
	
	public Boolean isFinal(){
		return isFinal;
	}
}
