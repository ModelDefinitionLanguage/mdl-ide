package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.Primary
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.TaskFunctionDeclaration
import org.ddmore.mdl.mdl.TaskFunctionBody
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.Argument
import org.eclipse.emf.common.util.EList
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.IndividualVariablesBlock
import org.ddmore.mdl.mdl.GroupVariablesBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.ParameterDeclaration
import org.ddmore.mdl.mdl.InputVariablesBlock
import org.ddmore.mdl.mdl.SimulateTask
import org.ddmore.mdl.mdl.EstimateTask
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.ModelObjectBlock

class Mdl2PharmML extends Mdl2Nonmem{
	
	val	xsi="http://www.w3.org/2001/XMLSchema-instance"; 
	val xsi_schemaLocation="http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML";
	val xmlns_pharmML="http://www.pharmml.org/2013/03/PharmML";
	val xmlns_math="http://www.pharmml.org/2013/03/Maths";
	val xmlns_ct="http://www.pharmml.org/2013/03/CommonTypes";
	val xmlns_mdef="http://www.pharmml.org/2013/03/ModelDefinition";
	val xmlns_mstep="http://www.pharmml.org/2013/03/ModellingSteps";
	val xmlns_design="http://www.pharmml.org/2013/03/TrialDesign";
	val xmlns_uncert="http://www.pharmml.org/2013/03/Uncertainty";
	
	//Print file name and analyse all MCL objects in the source file
  	def convertToPharmML(Mcl m){
  		var version = "1.001";
  		var date = "06.06.2013"
		val info = "mdl2pharmML " + version + " beta, last modification " + date + " Natallia Kokash (natallia.kokash@gmail.com)";  
		var printIndependent = m.isIndependentVariableDefined; 
		'''
		«info.print_XS_Comment»
		<?xml version="1.0" encoding="UTF-8"?>
		<PharmML 
			«print_PharmML_NameSpaces»
			name="«m.eResource.fileName»"
			«IF printIndependent»independentVar="t"«ENDIF»
			writtenVersion="0.1">
  			«FOR o:m.objects»«o.print_mdef_ModelDefinition»«ENDFOR»
		</PharmML>
		'''
	}
	
	//Print PharmML namespaces
	def print_PharmML_NameSpaces()
		'''
		xmlns:xsi="«xsi»" 
		xmlns="«xmlns_pharmML»"
		xsi:schemaLocation="«xsi_schemaLocation»"
		xmlns:math="«xmlns_math»"
		xmlns:ct="«xmlns_ct»"
		xmlns:mdef="«xmlns_mdef»"
		xmlns:mstep="«xmlns_mstep»"
		xmlns:design="«xmlns_design»"
		xmlns:uncert="«xmlns_uncert»"
		'''
	

	//Print mapping for the input variables with use=idv (individual)
	def printIndependentVariables(Mcl m){
		for (MclObject obj: m.objects){
			if (obj.modelObject != null){
				for (ModelObjectBlock block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (SymbolDeclaration s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.getAttribute("use");
									if (use.equalsIgnoreCase("idv")){
										val varName = s.identifier;
										return '''
										<Mapping columnName="«varName»">
											<Var xmlns="«xmlns_math»" symbId="t"/>
										</Mapping>
                						'''
                					}
								}
							}
						}
					}	
				}
			}
		}
	}	


	//convertToPharmML MCL objects (!!! add namespace to model definition)
	def print_mdef_ModelDefinition(MclObject o)'''
	<ModelDefinition xmlns="«xmlns_mdef»">
		«IF o.parameterObject != null»«o.parameterObject.print_mdef_ParameterModel»«ENDIF»
		«IF o.modelObject != null»
		«o.modelObject.print_mdef_StructuralModel»
		«o.modelObject.print_mdef_ObservationModel»
		«ENDIF»
	</ModelDefinition>
	'''
	
	//////////////////////////////////////////////////////////
	//PharmML types
	//////////////////////////////////////////////////////////
	
	//mml:FunctionDefinition
	def print_mml_FunctionDefinition(TaskFunctionDeclaration f)'''
	'''

	/////////////////////////////////////////////////////////
	//Design PharmML types
	/////////////////////////////////////////////////////////

	def print_design_Individuals(IndividualVariablesBlock block)'''
	<Individuals>
	</Individuals>
	'''
	
	def print_design_Group(GroupVariablesBlock variables)'''
	<Group>
	</Group>
	'''

	//////////////////////////////////////////////////////////
	//Distrib PharmML types
	//////////////////////////////////////////////////////////
	
	def print_uncert_DistributionType(RandomList list)'''
	'''
	
	/////////////////////////////////////////////////////////
	//mdef PharmML types
	/////////////////////////////////////////////////////////
	def print_mdef_VariabilityLevel()'''
	<VariabilityLevel id="indiv"/>
	'''	
	
	def print_mdef_CovariateModel()'''
	<CovariateModel>
	</CovariateModel>
	'''	
	
	//K?
	//omega_NAME
	def print_mdef_ParameterModel(ParameterObject obj)'''
	«FOR b:obj.blocks»			
		«IF b.structuralBlock != null»
		«val paramModel="p1"»
		<ParameterModel id=«paramModel»>
			«FOR st: b.structuralBlock.parameters»
				«st.print_mdef_Parameter»
			«ENDFOR»
		</ParameterModel>
		«ENDIF»
	«ENDFOR»
	''' 
	
	
	def print_mdef_Parameter(ParameterDeclaration s){
		var name = s.identifier;
		'''<Parameter symbId = \"«name»\"/>'''
	}	
	
	def print_mdef_StructuralModel(ModelObject obj)'''
	«FOR b:obj.blocks»
		«IF	b.structuralParametersBlock != null»		
			<StructuralModel>
			</StructuralModel>
		«ENDIF»
	«ENDFOR»	
	'''
	
	def print_mdef_ObservationModel(ModelObject obj)'''
	«FOR b:obj.blocks»
		«IF	b.observationBlock != null»		
			<ObservationModel>
			</ObservationModel>
		«ENDIF»
	«ENDFOR»
	'''	
	
	/////////////////////////////////////////////////////////
	//mstep PharmML types
	/////////////////////////////////////////////////////////
	def print_msteps_ModelingSteps(MclObject o)'''
	<ModellingSteps>
		«IF o.modelObject != null»«o.modelObject.print_msteps_Variable»«ENDIF»
		«IF o.taskObject != null»«o.taskObject.print_msteps_ModelingStepsContent»«ENDIF»
	</ModellingSteps>
	'''
	
	def print_msteps_Variable(ModelObject obj)'''
	<Variable>
		«obj.print_ct_DataSet»
	</Variable>
	'''

	def print_ct_DataSet(ModelObject obj)'''
	<DataSet>
		«FOR b:obj.blocks»
			«IF b.inputVariablesBlock != null»
				«b.inputVariablesBlock.print_ct_Definition» 
			«ENDIF»
		«ENDFOR»
	</DataSet>	
	'''
	
	def print_ct_Definition(InputVariablesBlock block){
		var input_vars = newHashMap  
		var i = 1;
		for (v: block.variables){
			val id = v.identifier
			if (input_vars.get(id) == null){
				input_vars.put(id, i);
				i = i + 1;
			}
		}
		'''
		<Definition>
		</Definition>	
		'''
	}

	def print_msteps_ModelingStepsContent(TaskObject obj)'''
	«FOR b: obj.blocks»
		«IF b.functionDeclaration != null»
			«b.functionDeclaration.functionBody.print_msteps_ModelingStepsContent»
		«ENDIF»
	«ENDFOR»
	'''
	
	def print_msteps_ModelingStepsContent(TaskFunctionBody body)'''
		«FOR b: body.blocks»
			«IF b.estimateBlock != null»
				«b.estimateBlock.print_msteps_SimulationStep»
			«ENDIF»
			«IF b.simulateBlock != null»
				«b.simulateBlock.print_msteps_EstimationStep»
			«ENDIF»
		«ENDFOR»
	'''
	def print_msteps_EstimationStep(SimulateTask task)'''
	<SimulationStep>
	</SimulationStep>
	'''

	def print_msteps_SimulationStep(EstimateTask task)'''
	<EstimationStep>
	</EstimationStep>
	'''

	//////////////////////////////////////////////////////////
	//Common PharmML types
	//////////////////////////////////////////////////////////
	
	//***PharmML.ct_Annotation***//
	def print_XS_Comment(String text)'''
		<!--«text»-->
	'''
	
	//***PharmML.ct_Annotation***//
	def print_ct_AnnotationType(String text)'''
	<Description>«text»</Description>
	'''

	//***MDL.variable_declaration***//
	def print_ct_VariableDefinitionType(SymbolDeclaration v)'''
	<Variable «v.identifier.print_ct_SymbId»>
		«v.identifier.print_ct_AnnotationType»
		«IF v.expression != null»
			«v.expression.print_ct_printRhsType»
		«ENDIF»	
	</Variable>
	'''
	
	def print_ct_printRhsType(AnyExpression e)'''
		«IF e.expression != null»
			«e.expression.print_ct_printRhsType»
		«ENDIF»
		«IF e.list != null»
		«ENDIF»	
		«IF e.odeList != null»
		«ENDIF»	
	'''
	
	def print_ct_printRhsType(Expression e)'''
		«IF e.conditionalExpression != null»
		«ENDIF»
	'''
	
	//MDL.primary.VariableName
	def print_ct_SymbId(Primary p)'''
		«IF p.symbol != null»
			«p.symbol.print_ct_SymbId»
		«ENDIF»
	'''	
	
	//MDL.VariableName
	def print_ct_SymbId(String name)'''
	symbId = "«name»"
	'''
	
	//MDL.VariableName
	def print_ct_SymbId(FullyQualifiedSymbolName name)'''
	symbId = "«name.toStr»"
	'''

	/////////////////////////////////////////////////////
	//Math PharmML types
	/////////////////////////////////////////////////////
	
	def print_MathStringType(EList<String> list)'''	
	<String>
	</String>	
	'''
	
	//***MDL.primary***//
	//MDL.primary.number
	def print_Math_ScalarType(Primary p)'''
	«IF p.number != null»
		<Scalar value = "«p.number»"/>
	«ENDIF»	
	'''
	
	def print_Math_FunctionCallType(FunctionCall call)'''
	<FunctionCall>
		«call.identifier.print_Math_VarType»
		«FOR arg: call.arguments.arguments»
			«arg.print_Math_FunctionArgumentType»
		«ENDFOR»
	</FunctionCall>
	'''

	def print_Math_VarType(FullyQualifiedSymbolName name)'''
		<Var «name.identifier.print_Math_symbId»/>
	'''

	def print_Math_FunctionArgumentType(Argument arg)'''
	<FunctionArgument 
		«IF arg.identifier != null»«arg.identifier.print_Math_symbId»«ENDIF»>
	</FunctionArgument>
	'''
	
	def print_Math_symbId(String str)'''
	symbId = "«str»"
	'''
	
	//////////////////////////////////////////////////////////
	//Helper functions
	//////////////////////////////////////////////////////////
	//Return true if an input variable with use=idv (individual) is edfined
	def isIndependentVariableDefined(Mcl m){
		for (MclObject obj: m.objects){
			if (obj.modelObject != null){
				for (ModelObjectBlock block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (SymbolDeclaration s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.getAttribute("use");
									if (use.equalsIgnoreCase("idv")){
										return true;
                					}
								}
							}
						}
					}	
				}
			}
		}
		return false;
	}	
}