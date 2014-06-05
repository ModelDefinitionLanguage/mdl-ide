package org.ddmore.mdl.validation;

import java.util.Arrays;
import java.util.List;

public class ModellingObjectGroup {
	String mdlObjName;
	String dataObjName;
	String paramObjName;
	String taskObjName;
	
	public ModellingObjectGroup(String mdlObjName, String paramObjName, 
			String dataObjName, String taskObjName){
		this.mdlObjName = mdlObjName;
		this.paramObjName = paramObjName;
		this.dataObjName = dataObjName;
		this.taskObjName = taskObjName;
	}
	
	public String getModelObjName(){
		return mdlObjName;
	}
	
	public String getParamObjName(){
		return paramObjName;
	}

	public String getDataObjName(){
		return dataObjName;
	}
	
	public String getTaskObjName(){
		return taskObjName;
	}
	
	public List<String> getObjectNames(){
		return Arrays.asList(mdlObjName, paramObjName, dataObjName, taskObjName);
	}

}
