package org.ddmore.mdl.domain;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ddmore.mdl.types.MdlDataType;

public class Variable {
	String name;
	MdlDataType type = MdlDataType.TYPE_UNDEFINED; 
	HashMap<String, Variable> dependencies = new HashMap<String, Variable>();
	
	public Variable(String name, List<String> dependencies){
		this.name = name;
		for (String varName: dependencies){
			if (!this.dependencies.containsKey(varName))
				this.dependencies.put(varName, null);
		}
	}	
	
	public Variable(String name, ArrayList<Variable> dependencies){
		this.name = name;
		for (Variable var: dependencies){
			this.dependencies.put(var.getName(), var);
		}
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
	
	public boolean deriveType(){
		for (Variable var: dependencies.values()){
			if (var == null) return false;
		}
		//No unknown dependencies, can compute actual type
		return true;
	}
	
	public boolean addDependency(Variable newDependency){
		if (!dependencies.containsKey(newDependency.getName())){
			dependencies.put(newDependency.getName(), newDependency);
			return true;
		}
		return false;
	}

	public void clearDependencies(){
		dependencies.clear();
	}

	public HashMap<String, Variable> getDependencies(){
		return dependencies;
	}
}
