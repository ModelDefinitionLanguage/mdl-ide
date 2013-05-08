/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.block
import org.ddmore.mdl.mdl.block_statement
import org.ddmore.mdl.mdl.statement
import org.ddmore.mdl.mdl.conditional_expression
import org.ddmore.mdl.mdl.list
import org.ddmore.mdl.mdl.conditional_or_expression
import org.ddmore.mdl.mdl.expression
import org.ddmore.mdl.mdl.conditional_and_expression
import org.ddmore.mdl.mdl.relational_expression
import org.ddmore.mdl.mdl.additive_expression
import org.ddmore.mdl.mdl.multiplicative_expression
import org.ddmore.mdl.mdl.unary_expression
import org.ddmore.mdl.mdl.primary
import org.ddmore.mdl.mdl.par_expression
import org.ddmore.mdl.mdl.arguments
import org.ddmore.mdl.mdl.variable_name
import org.ddmore.mdl.mdl.variable_declaration
import org.ddmore.mdl.mdl.function_call
import org.ddmore.mdl.mdl.any_expression
import org.ddmore.mdl.mdl.random_list
import org.ddmore.mdl.mdl.ode_list
import org.ddmore.mdl.mdl.power_expression
import org.ddmore.mdl.mdl.verbatim_block
import org.ddmore.mdl.mdl.target_block
import org.eclipse.emf.ecore.resource.Resource
import org.ddmore.mdl.mdl.mcl
import org.ddmore.mdl.mdl.argument
import org.ddmore.mdl.mdl.model_obj

class MdlPrinting {

	//Get MDL file name in upper-case
	def fileNameUpperCase(mcl m){
		m.eResource.fileName.toUpperCase()
	}
	
	//Get MDL file name
	def fileName(Resource resource){
		var fileName = resource.getURI().lastSegment
		fileName.substring(0, fileName.lastIndexOf('.'))
	}
	
	//Find reference to a data file 
	def getDataSource(Resource resource){
		for(m: resource.allContents.toIterable.filter(typeof(mcl))) {
			for (obj: m.objects){
				if (obj.data_obj != null){
					for (b: obj.data_obj.blocks){
						if (b.file_block != null){
							for (s: b.file_block.block.blocks){
								if (s.statement != null){
									return s.statement.getDataSource;
								}
							}
						} 
					}
				}
			}
		}		
		return "";
	}
	
	def getDataSource(block_statement s){
		if (s.variable_declaration != null)
			if (s.variable_declaration.identifier.toStr.equalsIgnoreCase("data"))
				if (s.variable_declaration.expression != null)
					if (s.variable_declaration.expression.list != null){
						val data = s.variable_declaration.expression.list.getVariableAttribute("source");
						if (data != null)
							return data.toString();
					}
		return "";
	}
		
	//Return value of a list attribute with a given name
	def getVariableAttribute(list v, String attr_name){
		if (v.arguments != null)
			return v.arguments.selectAttribute(attr_name)
		return null;
	}	

	//Return value of a random list attribute with a given name
	def getVariableAttribute(random_list v, String attr_name){
		if (v.arguments != null)
			return v.arguments.selectAttribute(attr_name)
		return null;
	}	
	
	//Return value of a list attribute with a given name
	def getVariableAttribute(ode_list v, String attr_name){
		if (v.arguments != null)
			return v.arguments.selectAttribute(attr_name)
		return null;
	}	
	
	//Return value of an attribute with a given name
	def selectAttribute(arguments a, String attr_name){
		for (arg: a.arguments)
			if (arg.identifier.equalsIgnoreCase(attr_name))
				return arg.expression.toStr
	}	
	
	def isArgumentExpression(argument a){
		if (a.expression.expression != null){
			if (a.expression.expression.conditional_expression != null) return true;
		}
		return false;
	}
	
	
	//Return true if there are definitions in MODEL PREDICTION or OBSERVATION blocks,
	def isErrorNonEmpty(model_obj o){
    	for (mob:o.blocks){
	    	if (mob.model_prediction_obj_block != null){
				for (s: mob.model_prediction_obj_block.block.statements){
					if (s.statement != null) return true;
				}
	    	}
	    	if (mob.observation_block != null){
				for (s: mob.observation_block.block.statements){
					if (s.statement != null) return true;
				}
	    	}
		}
    	return false;
    } 
	
    //Check if there are definitions in ODE block
    def isODEDefined(model_obj o){
		for (mob: o.blocks){
			if (mob.model_prediction_obj_block != null){
				var b = mob.model_prediction_obj_block.block;
		    	if (b != null){
		    		for (s: b.statements){
		    			if (s.ode_block != null) return true;
		    		}
		    	}
		    }
		}
    	return false;
    }

	def isPKDefined(model_obj o){
		for (mob: o.blocks){
			if (mob.group_variables != null){
				if (mob.group_variables.block != null){
					return true;
				}
			}
			if (mob.individual_model_obj_block != null){
				if (mob.individual_model_obj_block.block != null){
					return true;
				}
			}
		}
		return false;
	}

    //Check if LIBRARY block is defined
    def isLibraryDefined(model_obj o){
    	for (mob: o.blocks){
			if (mob.model_prediction_obj_block != null){
				for (s: mob.model_prediction_obj_block.block.statements){
				    if (s.library_block != null) return true;
				}
			}
    	}
    	return false;
    }
	
	/////////////////////////////////////////////////////////////

    //Map variable identifiers, identity for MDL printing, to be rewritten in super classes 
	def convertID(String id){
		return id;
	}
	
	//Map operators, identity for MDL printing, to be rewritten in super classes 
	def convertOperator(String op){
		return op;
	}
	
	//Convert a variable name Name1.Name2... to a string 
	//!!! Does not print selectors!
	def toStr(variable_name name){
		var res = ""; 
		var iterator = name.identifier.iterator();
		if (iterator.hasNext ) {
			res = iterator.next;
		}
		while (iterator.hasNext){
			res  = res + "." + iterator.next;
		}
		return res;
	}

    //toStr variable declaration
	def toStr(variable_declaration v){
		var res = "";
		if (v.identifier != null) {
			res = res + v.identifier.toStr;
		}
		if (v.expression != null){
			res = res + " = " + v.expression.toStr;
		}
		if (v.random_list != null){
			res  = res + v.random_list.toStr;
		}
		return res;
	}	
	
	//toStr any MDL expression
	def toStr(any_expression e){
		var res = "";
		if (e.expression != null){
			res = res + e.expression.toStr;
		}
		if (e.list != null){
			res  = res + e.list.toStr;
		}
		if (e.random_list != null){
			res = res + e.random_list.toStr;
		}
		if (e.ode_list != null){
			res = res + e.ode_list.toStr;
		}
		return res;
	}
	
	//toSTr random list	
	def toStr(random_list l){
		if (l.arguments != null){
			return "~" + "(" + l.arguments.toStr + ")";
		}
		return "";
	}

	//toStr list
	def toStr(list l){
		if (l.arguments != null){
			return "list" + "(" + l.arguments.toStr + ")";
		}
		return "";
	}
	
	//toStr ODE list
	def toStr(ode_list l){
		if (l.arguments != null){ 
			return "ode" + "(" + l.arguments.toStr + ")"
		}
	}
	
	//toStr arithmetic expression
	def toStr(expression e){
		var res = "";
		if (e.conditional_expression != null){
			res = res + e.conditional_expression.toStr
		}
		if (e.string_expression != null){
			var iterator = e.string_expression.iterator();
			if (iterator.hasNext ) {
				res = iterator.next;
			}
			while (iterator.hasNext){
				res  = res + " + " + iterator.next;
			}
		}
		return res;
	}
	
	//toStr conditional expression
	def toStr(conditional_expression e){ 
		var res = e.conditional_or_expression.toStr;
		if (e.expression1 != null){
			res  = res + "?" + e.expression1.toStr + ":" + e.expression2.toStr
		}
		return res;
	}

    //toStr OR expression
	def toStr(conditional_or_expression e){
		var res = "";
		var iterator = e.conditional_and_expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	//toStr AND expression
	def toStr(conditional_and_expression e){
		var res = "";
		var iterator = e.relational_expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;	
	}
	
	//toStr relational expression (==, !=, <, > etc.)
	def toStr(relational_expression e){
		var res = "";
		if (e.negation != null){
			res = res + e.negation;
		}
		if (e.boolean != null){
			res = res + e.boolean.toString;
		}
		var iterator = e.additive_expression.iterator();
		var operatorIterator = e.relational_op.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}	

	//toStr +, - expresion
	def toStr(additive_expression e){
		var res  = "";
		var iterator = e.multiplicative_expression.iterator();
		var operatorIterator  = e.additive_op.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	//toStr *, /, % expression
	def toStr(multiplicative_expression e){
		var res = "";
		var iterator = e.power_expression.iterator();
		var operatorIterator  = e.multiplicative_op.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	//Print power (a^b^c^...) expression
	def String toStr(power_expression e) { 
		var res = "";
		var iterator = e.unary_expression.iterator();
		var operatorIterator  = e.power_op.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	//toStr a unary expression
	def toStr(unary_expression e){
		var res = "";
		if (e.unary_expression != null){
			res = res + e.operator.convertOperator + e.unary_expression.toStr
		}
		if (e.par_expression != null){
			res = res + e.par_expression.toStr;
		}
		if (e.function_call != null) {
			res = res + e.function_call.toStr
		}
		if (e.primary != null) {
			res = res + e.primary.toStr
		}
		return res;
	}	
	
	//toStr function call
	def toStr(function_call call){
		return call.funct_name.convertID + "(" + call.arguments.toStr + ")";
	}
	
	//toStr a value or an identifier
	//Convert variables in expressions if needed!
	def toStr(primary p){
		var res = "";
		if (p.number != null){
			res  = res + p.number;
		}
		if (p.identifier != null){
			res = res + p.identifier.toStr.convertID; 
		}
		return res;
	}
	
	//toStr expression in parenthesis 
	def toStr(par_expression e){
		return "(" + e.expression.toStr + ")";
	}
	
	//toStr list arguments without change
	def toStr(arguments arg){
		var res  = "";
		var iterator = arg.arguments.iterator();
		if (iterator.hasNext ) {
			var a = iterator.next; 
			if (a.identifier != null){
				res  = res + a.identifier + " = ";
			}
			if (a.expression != null){
				res = res + a.expression.toStr;
			}
		}
		while (iterator.hasNext){
			res  = res + ', ';
			var a = iterator.next; 
			if (a.identifier != null){
				res  = res + a.identifier + " = ";
			}
			if (a.expression != null){
				res = res + a.expression.toStr;
			}
		}
		return res;
	}	
	

	//////////////////////////////////////////////////////////////////////////
	//Printing
    //////////////////////////////////////////////////////////////////////////
    
    //Print verbatim block
	def print(verbatim_block b)'''
	«IF b.external_code != null»
	«var printedCode = b.external_code.substring(3, b.external_code.length - 3)»
	«printedCode»
	«ENDIF»
	«IF b.block != null»
	«b.block.print»
	«ENDIF»
	'''	
	
	//Print verbatim target specific code
	def print(target_block b)'''
	«var printedCode = b.external_code.substring(3, b.external_code.length - 3)»
	«printedCode»
	'''	
	
	//Print block
	def print(block b)'''
		«FOR st: b.statements»
			«st.print»
		«ENDFOR»
	'''
	
	//Print block variables
	def printVariables(block b, String separator)'''
		«FOR s: b.statements SEPARATOR separator»
			«IF s.variable_declaration != null»
				«s.variable_declaration.print»
			«ENDIF»
		«ENDFOR»
	'''	
	
	//Print variable names
	def printVariableNames(block b, String separator)'''
		«FOR s: b.statements SEPARATOR separator»
			«IF s.variable_declaration != null»«s.variable_declaration.identifier.toStr.convertID»«ENDIF»
		«ENDFOR»
	'''	
	
	//Get variable identifier from each declaration and each statement
	def print(block_statement st)'''
		«IF st.variable_declaration != null»«st.variable_declaration.print»«ENDIF»
		«IF st.function_call != null»«st.function_call.print»«ENDIF»
		«IF st.statement != null»«st.statement.print»«ENDIF»
		«IF st.verbatim_block != null»«st.verbatim_block.print»«ENDIF»
		'''

	//Print function call
	def print(function_call call)'''«call.funct_name»(«call.arguments.print»)'''
	
	//Print MDL statement
	def print(statement s)'''
		«IF s.block != null»
			«s.block.print»
		«ENDIF»
		«IF s.par_expression != null»
			if «s.par_expression.print»
				«s.if_statement.print»
			«IF s.else_statement != null»
			else 
				«s.else_statement.print»
			«ENDIF»
		«ENDIF»
	'''
	
	//Print variable name
	def print(variable_name name)'''«name.toStr»'''	

    //Print variable declaration
	def print(variable_declaration v)'''«v.toStr»'''

	//Print any expression	
	def print(any_expression e)'''«e.toStr»'''
		
	//Print random list
	def print(random_list l)'''«l.toStr»'''

	//Print list
	def print(list l)'''«l.toStr»'''
	
	//print ODE list
	def print(ode_list l)'''«l.toStr»'''

	//print arithmetic expression
	def print(expression e)'''«e.toStr»'''
	
	//print conditional (bool? statement1 : statement2) expression
	def print(conditional_expression e)'''«e.toStr»'''

	//Print OR expression
	def print(conditional_or_expression e)'''«e.toStr»'''
	
	//Print AND expression
	def print(conditional_and_expression e)'''«e.toStr»'''
	
	//Print relational expression (==, !=, <, > etc.)
    def print(relational_expression e)'''«e.toStr»'''
	
	//Print +, - expresion
	def print(additive_expression e)'''«e.toStr»'''
	
	//Print *, /, % expression
	def print(multiplicative_expression e)'''«e.toStr»'''
	
	def print(power_expression e)'''«e.toStr»'''
	
	//Print a unary expression
	def print(unary_expression e)'''«e.toStr»'''

	//Print a value or an identifier
	def print(primary p)'''«p.toStr»'''
	
	//Print expression in parenthesis 
	def print(par_expression e)'''«e.toStr»'''
	
	//Print list arguments without change
	def print(arguments arg)'''«arg.toStr»'''
}
