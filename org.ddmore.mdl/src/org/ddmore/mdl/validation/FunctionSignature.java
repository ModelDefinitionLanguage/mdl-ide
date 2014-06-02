package org.ddmore.mdl.validation;

import java.util.List;

import org.ddmore.mdl.types.MdlDataType;
import org.ddmore.mdl.types.TargetCodeType;

public class FunctionSignature {
	String name;
	Integer numberOfParams; 
	MdlDataType type = MdlDataType.TYPE_VOID;
	Boolean passingByName = false;
	//By default we assume unnamed input parameters of type MdlDataType.TYPE_REAL
	List<FunctionParameter> params;
	TargetCodeType target = null;
	
	public FunctionSignature(String name, Integer numberOfParams){
		this.name = name;
		this.numberOfParams = numberOfParams;
	}
	
	public FunctionSignature(String name, Integer numberOfParams, MdlDataType type){
		this(name , numberOfParams);
		this.type = type;
	}
	
	public FunctionSignature(String name, List<FunctionParameter> params){
		this.name = name;
		this.params = params;
		if (params != null)
			this.numberOfParams = params.size();
	}
	
	public FunctionSignature(String name, List<FunctionParameter> params, MdlDataType type){
		this(name, params);
		this.type = type;
	}
	
	public FunctionSignature(String name, Integer numberOfParams, Boolean passingByName){
		this(name, numberOfParams);
		this.passingByName = passingByName;
	}

	public FunctionSignature(String name, List<FunctionParameter> params, Boolean passingByName){
		this(name, params);
		this.passingByName = passingByName;
	}
	
	public FunctionSignature(String name, List<FunctionParameter> params, MdlDataType type, Boolean passingByName){
		this(name, params, type);
		this.passingByName = passingByName;
	}
	
	public String getName(){
		return name;
	}
	
	public MdlDataType getType(){
		return type;
	}
	
	public Integer getNumberOfParams(){
		return numberOfParams;
	}
	
	public Boolean getPassingByName(){
		return passingByName;
	}

	public List<FunctionParameter> getParams(){
		return params;
	}
}
