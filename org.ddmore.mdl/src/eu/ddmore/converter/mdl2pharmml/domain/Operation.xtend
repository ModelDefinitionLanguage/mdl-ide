package eu.ddmore.converter.mdl2pharmml.domain

import org.ddmore.mdl.validation.ModellingObjectGroup

class Operation {
	public var String name;
	public var String type;
	public var ModellingObjectGroup mog;
	
	new (String name, String type, ModellingObjectGroup mog){
		this.name = name;
		this.type = type;
		this.mog = mog;
	}
	
}