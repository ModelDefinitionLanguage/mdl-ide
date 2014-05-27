package eu.ddmore.converter.mdl2pharmml.domain

/* A class to collect conditions for conditional statements */
class Piece {
	public var Piece parent = null;
	public var String condition = null;
	public var String expression = null;
	
	new (Piece _parent, String _expression, String _condition){
		parent = _parent;
		condition = _condition;
		expression = _expression;
	}	
}