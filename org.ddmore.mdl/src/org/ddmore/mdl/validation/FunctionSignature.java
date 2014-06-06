package org.ddmore.mdl.validation;

import java.util.Arrays;
import java.util.List;

import org.ddmore.mdl.types.MdlDataType;
import org.ddmore.mdl.types.TargetCodeType;

public class FunctionSignature {
	String name;
	Integer numberOfParams = 0; 
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
		if (params != null){
			//max index (because there can be alternative named parameters on the same place)
			int count = 0;
			for (FunctionParameter p: params)
				if (p.getOrder() > count) count = p.getOrder();
			if (count > 0)
				this.numberOfParams = count + 1;
			else this.numberOfParams = params.size();
		}
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
	
	public Boolean isPassingByName(){
		return passingByName;
	}

	public List<FunctionParameter> getAllParams(){
		return params;
	}

	public List<FunctionParameter> getDefaultParams(){
		//return an ordered set of default parameters
		FunctionParameter[] orderedParams = new FunctionParameter[numberOfParams];
		for (FunctionParameter p: params){
			if (orderedParams.length > p.getOrder())
				if (orderedParams[p.getOrder()] == null)
					orderedParams[p.getOrder()] = p;
		}
		return Arrays.asList(orderedParams);
	}
}
