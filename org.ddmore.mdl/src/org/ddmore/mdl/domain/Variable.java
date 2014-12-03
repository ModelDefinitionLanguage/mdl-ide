package org.ddmore.mdl.domain;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ddmore.mdl.types.MdlDataType;

public class Variable {
	String name;
	MdlDataType type = MdlDataType.TYPE_UNDEFINED; 
	HashMap<String, Variable> dependencies = null;
	List<String> tmpDependencies = null;
	
	public Variable(String name, List<String> tmpDependencies){
		this.name = name;
		this.tmpDependencies = tmpDependencies;
	}	
	
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
	
	public boolean addDependency(Variable newDependency){
		if (!dependencies.containsKey(newDependency.getName())){
			dependencies.put(newDependency.getName(), newDependency);
			return true;
		}
		return false;
	}

	public boolean addTmpDependency(String newTmpDependency){
		if (!tmpDependencies.contains(newTmpDependency)){
			tmpDependencies.add(newTmpDependency);
			return true;
		}
		return false;
	}
	
	public void clearDependencies(){
		dependencies.clear();
		dependencies = null;
	}

	public void clearTmpDependencies(){
		tmpDependencies.clear();
		tmpDependencies = null;
	}

	public HashMap<String, Variable> getDependencies(){
		return dependencies;
	}

	public List<String> getTmpDependencies(){
		return tmpDependencies;
	}
	
}
