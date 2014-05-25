package eu.ddmore.converter.mdl2pharmml

class Operation {
	public var String name;
	public var String type;
	public var String mObjName;
	public var String pObjName;
	public var String dObjName;
	
	new (String name, String type, String mObjName, String pObjName, String dObjName){
		this.name = name;
		this.type = type;
		this.mObjName = mObjName;
		this.pObjName = pObjName;
		this.dObjName = dObjName;	
	}
	
}