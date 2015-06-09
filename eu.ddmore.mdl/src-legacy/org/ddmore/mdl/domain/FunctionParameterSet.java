package org.ddmore.mdl.domain;

import java.util.ArrayList;
import java.util.List;

public class FunctionParameterSet {
	List<FunctionParameter> parameterSet = new ArrayList<FunctionParameter>();

	public FunctionParameterSet(FunctionParameter parameter){
		this.parameterSet.add(parameter);
	}
	
	public FunctionParameterSet(List<FunctionParameter> parameterSet){
		this.parameterSet = parameterSet;
	}
	
	public List<FunctionParameter> getParameterSet(){
		return parameterSet;
	}
	
	boolean addParameter(FunctionParameter param){
		for (FunctionParameter p: parameterSet){
			if (p.getName() == param.getName()) return false;
		}
		parameterSet.add(param);
		return true;
	}

	boolean removeParameter(String paramName){
		FunctionParameter p = findParameterByName(paramName);
		if (p != null) {
			parameterSet.remove(p);
			return true;
		}
		return false;
	}

	public FunctionParameter findParameterByName(String paramName){
		for (FunctionParameter p: parameterSet){
			if (p.getName() == paramName) return p;
		}
		return null;
	}

}
