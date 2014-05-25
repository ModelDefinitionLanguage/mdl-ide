package org.ddmore.mdl.validation;

import java.util.ArrayList;

import org.ddmore.mdl.types.MdlDataType;

public class FunctionSignature {
	String name;
	Integer numberOfParams; 
	ArrayList<Attribute> inputParams;
	ArrayList<Attribute> outputParams;
	MdlDataType type = MdlDataType.TYPE_UNDEFINED;
	
	public FunctionSignature(String name, Integer numberOfParams){
		this.name = name;
		this.numberOfParams = numberOfParams;
	}

	public FunctionSignature(String name, ArrayList<Attribute> inputParams, ArrayList<Attribute> outputParams){
		this.name = name;
		this.inputParams = inputParams;
		this.outputParams = outputParams;
	}
	
	public FunctionSignature(String name, ArrayList<Attribute> inputParams, ArrayList<Attribute> outputParams, MdlDataType type){
		this(name, inputParams, outputParams);
		this.type = type;
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

	public ArrayList<Attribute> getInputParams(){
		return inputParams;
	}
	
	public ArrayList<Attribute> getOutputParams(){
		return outputParams;
	}

}
