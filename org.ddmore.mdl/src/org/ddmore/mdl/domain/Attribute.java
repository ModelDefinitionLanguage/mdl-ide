/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * Container for specifying requirements for distribution attributes
 */
package org.ddmore.mdl.domain;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.types.MdlDataType;

public class Attribute {

	String name = "undefined";
	List<MdlDataType> types = new ArrayList<MdlDataType>();
	Boolean mandatory = false;
	String defaultValue = "";
	
	public Attribute(String name, MdlDataType type, Boolean mandatory){
		this.name = name;
		this.types.add(type);
		this.mandatory = mandatory;
	}	
	
	public Attribute(String name, List<MdlDataType> types, Boolean mandatory){
		this.name = name;
		this.types = types;
		this.mandatory = mandatory;
	}	
	
	public Attribute(String name, MdlDataType type, Boolean mandatory, String defaultValue){
		this(name, type, mandatory);
		this.defaultValue = defaultValue;
	}	
	
	public Attribute(String name, List<MdlDataType> types, Boolean mandatory, String defaultValue){
		this(name, types, mandatory);
		this.defaultValue = defaultValue;
	}
	
	public String getName(){
		return name;
	}
	
	public MdlDataType getType(){
		if (types.size() > 0)
			return types.get(0);
		return MdlDataType.TYPE_UNDEFINED;
	}
	
	public List<MdlDataType> getTypes(){
		return types;
	}
	
	public List<String> getTypeNames(){
		List<String> typeNames = new ArrayList<String>();
		for (MdlDataType type: types)
			typeNames.add(type.name());
		return typeNames;
	}

	public Boolean isMandatory(){
		return mandatory;
	}
	
	public String getDefaultValue(){
		return defaultValue;
	}
	
}
