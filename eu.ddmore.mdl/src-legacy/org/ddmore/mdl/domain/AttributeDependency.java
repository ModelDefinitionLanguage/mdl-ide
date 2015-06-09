package org.ddmore.mdl.domain;

import java.util.ArrayList;
import java.util.List;

public class AttributeDependency {
	String attrName = null;
	List<String> values = null;
	
	public AttributeDependency(String attrName, List<String> values){
		this.attrName = attrName;
		this.values = values;
	}
	
	public AttributeDependency(String dependency, String value){
		this.attrName = dependency;
		this.values = new ArrayList<String>();
		values.add(value);
	}
	
	public List<String> getValues(){
		return values;
	}
	
	public String getAttrName(){
		return attrName;
	}
	
	public boolean containsValue(String value){
		for (String attrValue: this.values)
			if (attrValue.equals(value)) return true;
		return false;
	}
}
