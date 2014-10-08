package eu.ddmore.converter.mdl2pharmml.domain

import org.ddmore.mdl.mdl.MOGObject

class Operation {
	public var String name;
	public var String type;
	public var MOGObject mog;
	
	new (String name, String type, MOGObject mog){
		this.name = name;
		this.type = type;
		this.mog = mog;
	}
	
}