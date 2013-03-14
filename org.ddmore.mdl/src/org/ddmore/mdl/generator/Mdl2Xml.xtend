package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.primary
import org.ddmore.mdl.mdl.expression
import org.ddmore.mdl.mdl.function_declaration
import org.ddmore.mdl.mdl.formal_arguments
import org.ddmore.mdl.mdl.function_body
import org.ddmore.mdl.mdl.block
import org.ddmore.mdl.mdl.block_statement
import org.ddmore.mdl.mdl.statement
import org.ddmore.mdl.mdl.function_call
import org.ddmore.mdl.mdl.variable_declaration
import org.ddmore.mdl.mdl.variable_name

class MDLXML extends MdlPrinting {

	//primary: NUMBER or variable name
	def printXML(primary p)'''
		«IF p.literal != null»
			<Scalar value=«p.literal»>
			</Scalar>
		«ENDIF»	
		«IF p.identifier != null»
			«p.identifier.printXML»
		«ENDIF»
	'''
	def printXML(variable_name name)'''
		<Variable varName="«name.toStr»"/>
	'''

	//Function declaration: formal arguments, function body	
	def printXML(function_declaration f)'''
		<Description></Description>
		<FunctionDefinition>
            «IF f.formal_arguments != null»
            	«f.formal_arguments.printXML»
            «ENDIF»
			<Definition>
				«f.function_body.printXML»
			</Definition>
		</FunctionDefinition>		
	'''
	
	def printXML(formal_arguments args)'''
		«FOR a: args.identifiers»
			<FunctionParameter paramName="«a»" symbolType="scalar">
				<Description></Description>
			</FunctionParameter>
		«ENDFOR»
	'''
	
	//function body: ESTIMATE or SIMULATE blocks
	def printXML(function_body body)'''
		«FOR b: body.blocks»
			«IF b.estimate_defn != null»
				«b.estimate_defn.printXML»
			«ENDIF»
			«IF b.simulate_defn != null»
				«b.simulate_defn.printXML»
			«ENDIF»
			
		«ENDFOR»
	'''
	
	//Block = block statements*
	def printXML(block b)'''
		«FOR st: b.statements»
			«st.printXML»
		«ENDFOR»
	'''
	
	//Get variable identifier from each declaration and each statement
	def printXML(block_statement st)'''
		«IF st.variable_declaration != null»
			«st.variable_declaration.printXML»
		«ENDIF»
		«IF st.function_call != null»
			«st.function_call.printXML»
		«ENDIF»
		«IF st.statement != null»
			«st.statement.printXML»
		«ENDIF»
	'''
	
	def printXML(function_call call)'''
		<Function functionName="«call.funct_name»">
		«FOR arg: call.arguments.arguments»
			<FunctionParameter varName="«arg.identifier»">
				<Constant value="«arg.expression.toStr»"/>
			</FunctionParameter>
		«ENDFOR»
		</Function>
	'''

	def printXML(variable_declaration declaration) { }

	def printXML(statement statement) { }	
	
	def toXML(expression expr)'''
		...
	'''

}