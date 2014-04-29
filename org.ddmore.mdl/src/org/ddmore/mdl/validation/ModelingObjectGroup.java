package org.ddmore.mdl.validation;

public class ModelingObjectGroup {
	String mdlObj;
	String dataObj;
	String paramObj;
	
	ModelingObjectGroup(String mdlObj, String paramObj, String dataObj){
		this.mdlObj = mdlObj;
		this.paramObj = paramObj;
		this.dataObj = dataObj;
	}
	
	public String getMdlObj(){
		return mdlObj;
	}
	
	public String getParamObj(){
		return paramObj;
	}

	public String getDataObj(){
		return dataObj;
	}

}
