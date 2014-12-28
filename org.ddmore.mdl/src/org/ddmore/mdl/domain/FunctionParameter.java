package org.ddmore.mdl.domain;

import org.ddmore.mdl.types.MdlDataType;

public class FunctionParameter {
	String name = "";
	ParameterPassingMethod passingMethod = ParameterPassingMethod.IN;
	String defaultValue = "";
	MdlDataType type = MdlDataType.TYPE_REAL;

	public FunctionParameter(String name){
		this.name = name;
	}
	
	public FunctionParameter(String name, MdlDataType type){
		this.name = name;
		this.type = type;
	}
	
	public FunctionParameter(String name,  MdlDataType type, ParameterPassingMethod passingMethod){
		this(name, type);
		this.passingMethod = passingMethod;
	}	
	
	public FunctionParameter(String name,  MdlDataType type, ParameterPassingMethod passingMethod, String defaultValue){
		this(name, type, passingMethod);
		this.defaultValue = defaultValue;
	}	
	
	public String getName(){
		return name;
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
