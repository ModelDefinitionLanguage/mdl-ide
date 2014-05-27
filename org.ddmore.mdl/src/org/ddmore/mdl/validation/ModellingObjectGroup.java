package org.ddmore.mdl.validation;

public class ModellingObjectGroup {
	String mdlObjName;
	String dataObjName;
	String paramObjName;
	
	public ModellingObjectGroup(String mdlObjName, String paramObjName, String dataObjName){
		this.mdlObjName = mdlObjName;
		this.paramObjName = paramObjName;
		this.dataObjName = dataObjName;
	}
	
	public String getMdlObjName(){
		return mdlObjName;
	}
	
	public String getParamObjName(){
		return paramObjName;
	}

	public String getDataObjName(){
		return dataObjName;
	}

}
