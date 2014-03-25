/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * Container for specifying requirements for distribution attributes
 */
package org.ddmore.mdl.validation;

public class Attribute {

	String name = "undefined";
	DataType type = DataType.TYPE_UNDEFINED;
	Boolean mandatory = false;
	
	Attribute(String _name, DataType _type, Boolean _mandatory){
		name = _name;
		type = _type;
		mandatory = _mandatory;
	}	
}
