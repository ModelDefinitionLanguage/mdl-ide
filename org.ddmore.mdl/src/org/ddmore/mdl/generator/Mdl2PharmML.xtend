package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.Primary
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.TaskFunctionDeclaration
import org.ddmore.mdl.mdl.TaskFunctionBody
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.eclipse.emf.common.util.EList
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.IndividualVariablesBlock
import org.ddmore.mdl.mdl.GroupVariablesBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.InputVariablesBlock
import org.ddmore.mdl.mdl.SimulateTask
import org.ddmore.mdl.mdl.EstimateTask
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.ModelObjectBlock
import org.ddmore.mdl.mdl.BlockStatement

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
	
	val writtenVersion = "0.1";
	var Mcl mcl = null;
	//Print file name and analyse all MCL objects in the source file
  	def convertToPharmML(Mcl m){
  		mcl = m;
  		
  		//Create a map of variables
  		m.prepareCollections;
  		
  		var version = "1.002";
  		var date = "20.11.2013";
		val info = "mdl2pharmML " + version + " beta, last modification " + date + " Natallia Kokash (natallia.kokash@gmail.com)";  
		var printIndependent = m.isIndependentVariableDefined; 
		'''
		«info.print_XS_Comment»
		<?xml version="1.0" encoding="UTF-8"?>
		<PharmML 
			«print_PharmML_NameSpaces»
			name="«m.eResource.fileName»"
			«IF printIndependent»independentVar="t"«ENDIF»
			writtenVersion="«writtenVersion»">
  			«print_mdef_ModelDefinition»
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
	def print_Math_Mapping(Mcl m){
		for (MclObject obj: m.objects){
			if (obj.modelObject != null){
				for (ModelObjectBlock block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (SymbolDeclaration s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.arguments.getAttribute("use");
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
	def print_mdef_ModelDefinition()'''
	<ModelDefinition xmlns="«xmlns_mdef»">
		«print_mdef_VariabilityLevel»
		«print_mdef_ParameterModel»
	</ModelDefinition>
	'''
	//	«o.modelObject.print_mdef_ObservationModel»
	
	def print_msteps_ObjectiveDataSet(MclObject o, String content)'''
	<ObjectiveDataSet dataSetRef="">
		«content»
	</ObjectiveDataSet>
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
	def print_mdef_ParameterModel(){
		var parameterModel = "";
		var structuralModel = "";
		//ParameterModel
		var i = 1;
		var blockName = "p" + i;
		for(s: theta_vars.keySet) {
			var paramName = s as String;
			if (paramName != null){
				var varName = paramName;
				var operator = "";
				var _index = paramName.indexOf('_');
				if (_index > 0) {
					varName = paramName.substring(_index + 1);
					var idv = varName.findIndividualVariable;
					if (idv != null){
						operator = idv.defineTransformationOperator;
						structuralModel = structuralModel + varName.print_mdef_StructuralParameter(blockName);
					}
				}			
				parameterModel = parameterModel + 
				'''
				<ParameterModel id="«blockName»">
					«print_mdef_ModelParameter(paramName, varName, blockName, operator)»
				</ParameterModel>
				'''
			}
			i = i + 1;
		}
		'''
		«parameterModel»
		«IF structuralModel.length > 0»
			<StructuralModel id="main">
				«structuralModel»
			</StructuralModel>
		«ENDIF»
		'''
	}
	
	def findIndividualVariable(String varName){
		//find paramName in INDIVIDUAL_VARIABLES
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				for (ModelObjectBlock b: o.modelObject.blocks){
					if(b.individualVariablesBlock != null){
						for (BlockStatement st: b.individualVariablesBlock.statements){
							if (st.symbol != null){
								var name = st.symbol.identifier;
								if (name.equalsIgnoreCase(varName)){
									return st.symbol;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	def defineTransformationOperator(SymbolDeclaration idv){
		if (idv.function != null){
			//explicit definition in the form funct(ID)=expr;
			return idv.function;
		} else {
			/*if (st.symbol.expression != null){
			//TODO: Parse expr to detect exp(x) function?
			}*/
			return "ln";
		}
	}
	
	def defineDistribution(String name){
		//find paramName in RANDOM_VARIABLES_DEFINITION
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				for (ModelObjectBlock b: o.modelObject.blocks){
					if(b.randomVariableDefinitionBlock != null){
						for (SymbolDeclaration s: b.randomVariableDefinitionBlock.variables){
							var variance = s.randomList.arguments.getAttribute("variance");
							if (variance.equalsIgnoreCase(name)){
								return s.randomList.arguments;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	def print_mdef_ModelParameter(String paramName, String varName, String blockName, String operator)'''
		«IF paramName != null»
			<Parameter symbId = "«paramName»"/>
			«IF varName != null»
				<Parameter symbId = "omega_«varName»"/>
				<Parameter symbId = "«varName»"«IF operator != null»«IF operator.length > 0»transformation="«operator»">«ENDIF»«ENDIF» 
					«print_Math_Equation(operator, paramName)»
				</Parameter> 
			«ENDIF»
		«ENDIF»
	'''

	def print_mdef_StructuralParameter(String varName, String blockName)'''
		<Parameter symbId = "«varName»">;
			<Var block="«blockName»" xmlns="«xmlns_math»" symbId="«varName»"/>';
		</Parameter>
	'''
	
	def print_Math_Equation(String operator, String paramName)'''
		<Equation xmlns="«xmlns_math»" writtenVersion="«writtenVersion»">;
			«operator.print_Math_UniOp(paramName)» 
		</Equation>"
	'''
	
	def print_Math_UniOp(String operator, String paramName)'''
		<Uniop op="«operator»">
			<Var symbId="«paramName»"/>
		</Uniop>
	'''
	
	def print_Math_RandomEffect(String paramName, String levelID)'''
		<RandomEffect symbId="eta_«paramName»" levelId = "«levelID»">
		«var args = paramName.defineDistribution»
		«IF args != null»
			«var distrType = args.getAttribute("type")»
			«var mean = args.getAttribute("mean")»		
			«print_uncert_Distribution(distrType, "omega_" + paramName, mean)»
		«ENDIF»
		</RandomEffect>
	'''
	
	def print_uncert_Distribution(String distrType, String paramName, String mean)'''
		<Distribution xmlns="«xmlns_uncert»" writtenVersion = "«writtenVersion»>";
			<«distrType»>
				<Mean>
					<Scalar xmlns="«xmlns_math»" value="«mean»"/>
				</Mean>
				<StdDev>
					<Var xmlns="«xmlns_math»" symbId="«paramName»"/>
				</StrDev>
			</«distrType»>
		</Distribution>"
	'''		

	/* 
	def print_mdef_ObservationModel(ModelObject obj)'''
	«FOR b:obj.blocks»
		«IF	b.observationBlock != null»		
			<ObservationModel>
			</ObservationModel>
		«ENDIF»
	«ENDFOR»
	'''	*/
	
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
	
	//////////////////////////////////////////////////////////////////////
	//Helper functions
	//////////////////////////////////////////////////////////////////////
	
	//Return true if an input variable with use=idv (individual) is defined
	def isIndependentVariableDefined(Mcl m){
		for (obj: m.objects){
			if (obj.modelObject != null){
				for (ModelObjectBlock block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (SymbolDeclaration s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.arguments.getAttribute("use");
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