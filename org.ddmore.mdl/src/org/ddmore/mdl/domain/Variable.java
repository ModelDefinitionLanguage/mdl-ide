package org.ddmore.mdl.domain;

import org.ddmore.mdl.types.MdlDataType;

public class Variable {
	String name;
	MdlDataType type = MdlDataType.TYPE_UNDEFINED; 
	
	public Variable(String name, MdlDataType type){
		this.name = name;
		this.type = type;
	}
	
	public String getName(){
		return name;
	}
	
	public MdlDataType getType(){
		return type;
	}
	
}
