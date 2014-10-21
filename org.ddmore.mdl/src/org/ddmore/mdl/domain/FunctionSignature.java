package org.ddmore.mdl.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.types.MdlDataType;

public class FunctionSignature {
	String name;
	MdlDataType type = MdlDataType.TYPE_UNDEFINED;
	Boolean passingByName = false;
	List<FunctionParameterSet> paramSets = new ArrayList<FunctionParameterSet>();
	Integer numberOfParams = 0; 
	List<Variable> returnedVariables = null;
	
	////////////////////////////////////
	//Undefined parameter set
	////////////////////////////////////
	
	//By default we assume unnamed input parameters of type MdlDataType.TYPE_EXPR
	public FunctionSignature(String name, Integer numberOfParams){
		this.name = name;
		this.numberOfParams = numberOfParams;
		List<FunctionParameter> params = new ArrayList<FunctionParameter>();
		for (int i = 0; i < numberOfParams; i++){
			params.add(new FunctionParameter("param" + i));
		}
		FunctionParameterSet defaultSet = new FunctionParameterSet(params);
		paramSets.add(defaultSet);
	}
	
	public FunctionSignature(String name, Integer numberOfParams, MdlDataType type){
		this(name, numberOfParams);
		this.type = type;
	}
	
	//////////////////////////////////////
	//One parameter set
	//////////////////////////////////////
	
	public FunctionSignature(String name, FunctionParameterSet paramSet){
		this.name = name;
		this.paramSets.add(paramSet);
		this.numberOfParams = paramSet.getParameterSet().size();
	}
	
	public FunctionSignature(String name, FunctionParameterSet paramSet, MdlDataType type){
		this(name, paramSet);
		this.type = type;
	}
		
	public FunctionSignature(String name, FunctionParameterSet paramSet, Boolean passingByName){
		this(name, paramSet);
		this.passingByName = passingByName;
	}
	
	public FunctionSignature(String name,  FunctionParameterSet paramSet, MdlDataType type, Boolean passingByName){
		this(name, paramSet, type);
		this.passingByName = passingByName;
	}
	
	public FunctionSignature(String name,  FunctionParameterSet paramSet, MdlDataType type, Boolean passingByName,
			List<Variable> returnedVariables){
		this(name, paramSet, type, passingByName);
		this.returnedVariables = returnedVariables;
	}

	//////////////////////////////////////
	//Multiple parameter sets
	//////////////////////////////////////
	
	public FunctionSignature(String name, List<FunctionParameterSet> paramSets){
		this.name = name;
		this.paramSets = paramSets;
		this.numberOfParams = paramSets.get(0).getParameterSet().size();
	}

	public FunctionSignature(String name, List<FunctionParameterSet> paramSets, MdlDataType type){
		this(name, paramSets);
		this.type = type;
	}
	
	public FunctionSignature(String name, List<FunctionParameterSet> paramSets, Boolean passingByName){
		this(name, paramSets);
		this.passingByName = passingByName;
	}
	
	public FunctionSignature(String name,  List<FunctionParameterSet> paramSets, MdlDataType type, Boolean passingByName){
		this(name, paramSets, type);
		this.passingByName = passingByName;
	}
	
	public FunctionSignature(String name,  List<FunctionParameterSet> paramSets, MdlDataType type, Boolean passingByName,
			List<Variable> returnedVariables){
		this(name, paramSets, type, passingByName);
		this.returnedVariables = returnedVariables;
	}
	
	////////////////////////////////////
	
	public List<FunctionParameterSet> getParamSet(){
		return paramSets;
	}
	
	public List<FunctionParameter> getDefaultParamSet(){
		if (paramSets.size() > 0)
			return paramSets.get(0).getParameterSet();
		else 
			return new ArrayList<FunctionParameter>();
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

	public Map<String, FunctionParameter> getAllParams(){
		Map<String, FunctionParameter> allParams = new HashMap<String, FunctionParameter>();
		for (FunctionParameterSet set: this.paramSets){
			for (FunctionParameter p: set.getParameterSet()){
				if (!allParams.containsKey(p.getName())){
					allParams.put(p.getName(), p);
				}
			}
		}
		return allParams;
	}

	public List<Variable> getReturnedVariables(){
		return returnedVariables;		
	}
	
	public List<String> getReturnedVariableNames(){
		List<String> varNames = new ArrayList<String>();
		for (Variable v: returnedVariables)
			varNames.add(v.getName());		
		return varNames;
	}

}
