package org.ddmore.mdl.domain;

import org.ddmore.mdl.types.MdlDataType;

public class FunctionParameter {
	String name = "";
	Integer order  = -1;
	ParameterPassingMethod passingMethod = ParameterPassingMethod.IN;
	String defaultValue = "";
	MdlDataType type = MdlDataType.TYPE_REAL;

	public FunctionParameter(String name, Integer order, MdlDataType type){
		this.name = name;
		this.order = order;
		this.type = type;
	}
	
	public FunctionParameter(String name, Integer order, MdlDataType type, ParameterPassingMethod passingMethod){
		this(name, order, type);
		this.passingMethod = passingMethod;
	}	
	
	public String getName(){
		return name;
	}
	
	public Integer getOrder(){
		return order;
	}	
	
	public MdlDataType getType(){
		return type;
	}

	public Boolean isInputParameter(){
		return (passingMethod == ParameterPassingMethod.IN || passingMethod == ParameterPassingMethod.IN_OUT);
	}

	public Boolean isOutputParameter(){
		return (passingMethod == ParameterPassingMethod.OUT || passingMethod == ParameterPassingMethod.IN_OUT);
	}
	
	public String getDefaultValue(){
		return defaultValue;
	}
}
