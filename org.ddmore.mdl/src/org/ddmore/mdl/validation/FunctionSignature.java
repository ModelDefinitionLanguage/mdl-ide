package org.ddmore.mdl.validation;

import java.util.ArrayList;

import org.ddmore.mdl.types.MdlDataType;

public class FunctionSignature {
	String name;
	Integer numberOfParams; 
	ArrayList<Attribute> input_params;
	ArrayList<Attribute> output_params;
	MdlDataType returnType = MdlDataType.TYPE_UNDEFINED;
	
	FunctionSignature(String name, Integer numberOfParams){
		this.name = name;
		this.numberOfParams = numberOfParams;
	}

	FunctionSignature(String name, ArrayList<Attribute> input_params){
		this.name = name;
		this.input_params = input_params;
	}

	FunctionSignature(String name, ArrayList<Attribute> input_params, ArrayList<Attribute> output_params){
		this.name = name;
		this.input_params = input_params;
		this.output_params = output_params;
	}
}
