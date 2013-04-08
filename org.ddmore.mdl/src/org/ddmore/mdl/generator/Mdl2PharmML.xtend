package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.primary
import org.ddmore.mdl.mdl.expression
import org.ddmore.mdl.mdl.function_declaration
import org.ddmore.mdl.mdl.formal_arguments
import org.ddmore.mdl.mdl.function_body
import org.ddmore.mdl.mdl.block
import org.ddmore.mdl.mdl.block_statement
import org.ddmore.mdl.mdl.function_call
import org.ddmore.mdl.mdl.variable_declaration
import org.ddmore.mdl.mdl.variable_name
import org.ddmore.mdl.mdl.any_expression
import org.ddmore.mdl.mdl.random_list
import org.ddmore.mdl.mdl.argument
import org.eclipse.emf.common.util.EList
import org.ddmore.mdl.mdl.mcl
import org.ddmore.mdl.mdl.mcl_obj
import org.ddmore.mdl.mdl.task_obj
import org.ddmore.mdl.mdl.param_obj
import org.ddmore.mdl.mdl.model_obj
import org.ddmore.mdl.mdl.data_obj

class Mdl2PharmML extends Mdl2Nonmem{
	
	//Print file name and analyse all MCL objects in the source file
  	def convertToPharmML(mcl m){
  		//Create a map of variables
  		for (o:m.objects){
  		}
  		var version = "1.001";
  		var date = "06.04.2013"
		val info = "mdl2pharmML " + version + " beta, last modification " + date + " Natallia Kokash (natallia.kokash@gmail.com)";  
		'''
		«info.print_XS_Comment»
		<?xml version="1.0" encoding="UTF-8"?>
		<PharmML 
			name="«m.eResource.fileName»"
			independentVar="t" 
			writtenVersion="0.1">
  			«FOR o:m.objects»«o.convertToPharmML»«ENDFOR»
		</PharmML>
		'''
	}
	
	//convertToPharmML MCL objects
	def convertToPharmML(mcl_obj o)'''
	«IF o.model_obj != null»«o.model_obj.convertToPharmML»«ENDIF»
	«IF o.data_obj != null»«o.data_obj.convertToPharmML»«ENDIF»
	«IF o.param_obj != null»«o.param_obj.convertToPharmML»«ENDIF»
	«IF o.task_obj != null»«o.task_obj.convertToPharmML»«ENDIF»
	'''
	
	def convertToPharmML(model_obj o)'''
	<ModelDefinition>
	«FOR b:o.blocks»
	«IF b.individual_model_obj_block != null»
		TODO: Process individual model block
	«ENDIF»
	«IF	b.model_prediction_obj_block != null»
		TODO: Process model prediction block
	«ENDIF»
	«IF	b.random_variable_definition_block != null»
		TODO: Process random variable definition block
	«ENDIF»

	«IF	b.group_variables != null»
		TODO: Process group variables
	«ENDIF»
	«IF	b.observation_block != null»
		TODO: Process observation block
	«ENDIF»
	«IF	b.estimation_block != null»
		«IF b.estimation_block.block != null»
			«b.estimation_block.block.print_msteps_EstimationStep»
		«ENDIF»
	«ENDIF»
	«IF	b.simulation_block != null»
		«IF b.simulation_block.block != null»
			«b.simulation_block.block.print_msteps_SimulationStep»
		«ENDIF»
	«ENDIF»
	«ENDFOR»
	</ModelDefinition>
	'''

	def convertToPharmML(data_obj obj) { }	

	def convertToPharmML(param_obj obj) { }

	def convertToPharmML(task_obj obj) { }

	
	def print_PharmML_NameSpaces()'''
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns="http://www.pharmml.org/2013/03/PharmML"
	xsi:schemaLocation="http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML"
	xmlns:math="http://www.pharmml.org/2013/03/Maths"
	xmlns:ct="http://www.pharmml.org/2013/03/CommonTypes"
	xmlns:mdef="http://www.pharmml.org/2013/03/ModelDefinition"
	xmlns:mstep="http://www.pharmml.org/2013/03/ModellingSteps"
	xmlns:design="http://www.pharmml.org/2013/03/TrialDesign"
	xmlns:uncert="http://www.pharmml.org/2013/03/Uncertainty"
	'''
	
	//***PharmML.ct_Annotation***//
	def print_XS_Comment(String text)'''
		<!--«text»-->
	'''
	
	//***PharmML.ct_Annotation***//
	def print_ct_AnnotationType(String text)'''
		<Description>«text»</Description>
	'''

	//***MDL.variable_declaration***//
	def print_ct_VariableDefinitionType(variable_declaration v)'''
		<Variable «v.identifier.print_ct_SymbId»>
			«v.identifier.toStr.print_ct_AnnotationType»
			«IF v.expression != null»
				«v.expression.print_ct_printRhsType»
			«ENDIF»	
			«IF v.random_list != null»
				«v.random_list.print_uncert_DistributionType»
			«ENDIF»	
		</Variable>
	'''
	
	def print_uncert_DistributionType(random_list list)'''
		TODO: print random list (distribution)	
	'''
	
	def print_ct_printRhsType(any_expression e)'''
		«IF e.expression != null»
			«e.expression.print_ct_printRhsType»
		«ENDIF»
		«IF e.list != null»
			TODO: print list
		«ENDIF»	
		«IF e.ode_list != null»
			TODO: print ode list
		«ENDIF»	
		«IF e.random_list != null»
			«e.random_list.print_uncert_DistributionType»
		«ENDIF»	
	'''
	def print_ct_printRhsType(expression e)'''
		«IF e.conditional_expression != null»
		«ENDIF»
		«IF e.string_expression != null»
			«e.string_expression.print_MathStringType»
		«ENDIF»
	'''
	
	def print_MathStringType(EList<String> list)'''
	
	'''

	
	//Get variable identifier from each declaration and each statement
	def printXML(block_statement st)'''
		«IF st.variable_declaration != null»
			«st.variable_declaration.print_ct_VariableDefinitionType»
		«ENDIF»
		«IF st.function_call != null»
			«st.function_call.print_Math_FunctionCallType»
		«ENDIF»
		«IF st.statement != null»
			TODO: parse MDL block
		«ENDIF»
	'''
	
	//***MDL.primary***//
	//MDL.primary.number
	def print_Math_ScalarType(primary p)'''
		«IF p.number != null»
			<Scalar value = "«p.number»"/>
		«ENDIF»	
	'''
	//MDL.primary.variable_name
	def print_ct_SymbId(primary p)'''
		«IF p.identifier != null»
			«p.identifier.print_ct_SymbId»
		«ENDIF»
	'''	
	//MDL.variable_name
	def print_ct_SymbId(variable_name name)'''
		symbId = "«name.toStr»"
	'''

	//MDL.function_declaration
	def printXML(function_declaration f)'''
	'''
	
	def printXML(formal_arguments args)'''
		«FOR a: args.identifiers»
		«ENDFOR»
	'''
	
	//function body: ESTIMATE or SIMULATE blocks
	def printXML(function_body body)'''
		«FOR b: body.blocks»
			«IF b.estimate_defn != null»
				«b.estimate_defn.print_msteps_SimulationStep»
			«ENDIF»
			«IF b.simulate_defn != null»
				«b.simulate_defn.print_msteps_EstimationStep»
			«ENDIF»
			
		«ENDFOR»
	'''
	
	def print_msteps_EstimationStep(block b)'''
		<EstimationStep>
		«FOR st: b.statements»
			TODO: print estimation steps
		«ENDFOR»
		</EstimationStep>
	'''

	def print_msteps_SimulationStep(block b)'''
		<SimulationStep>
		«FOR st: b.statements»
			TODO: print simulation steps
		«ENDFOR»
		</SimulationStep>
	'''
	
	def print_Math_FunctionCallType(function_call call)'''
		<FunctionCall>
			«call.funct_name.print_Math_VarType»
			«FOR arg: call.arguments.arguments»
				«arg.print_Math_FunctionArgumentType»
			«ENDFOR»
		</FunctionCall>
	'''
	
	def print_Math_VarType(String str)'''
		<Var «str.print_Math_symbId»/>
	'''

	def print_Math_FunctionArgumentType(argument arg)'''
		<FunctionArgument 
		«IF arg.identifier != null»«arg.identifier.print_Math_symbId»«ENDIF»>
		</FunctionArgument>
	'''
	
	def print_Math_symbId(String str)'''
		symbId = "«str»"
	'''
}