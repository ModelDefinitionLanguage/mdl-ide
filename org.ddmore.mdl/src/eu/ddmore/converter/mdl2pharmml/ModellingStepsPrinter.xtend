package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.TaskFunctionBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.validation.FunctionValidator
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator

class ModellingStepsPrinter extends DataSetPrinter{ 
	
	new(Mcl mcl, MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mcl, mathPrinter, resolver);
	}	
	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////
	//Choose an operation: estimate vs. simulate
	def print_msteps_ModellingSteps(){
		var res = "";
		for (o: mcl.objects){
			if (o.telObject != null){
				for (st: o.telObject.statements){
					val functionName = st.expression.identifier.function.name;
					if (st.expression.identifier.object != null){
						val tObj = getTaskObject(st.expression.identifier.object.name);
						res  = res + print_msteps_ModellingSteps(tObj, functionName, st.expression.arguments);
					} else {
						for (tObj: mcl.objects){
							if (tObj.taskObject != null){
								res  = res + print_msteps_ModellingSteps(tObj.taskObject, functionName, st.expression.arguments);
							}
						}
					}
				}
			}
		}
		'''
		<ModellingSteps xmlns="«xmlns_mstep»">
			«res»
		</ModellingSteps>		
		'''	
	}	
	
	def print_msteps_ModellingSteps(TaskObject tObj, String functionName, Arguments args){
		var res = "";
		for (b: tObj.blocks){
			if (b.functionDeclaration != null){
				if (b.functionDeclaration.functionName.name.equals(functionName)){
					for (bb: b.functionDeclaration.functionBody.blocks){
						if ((bb.estimateBlock != null) || (bb.simulateBlock != null)){
							val dataParam = getProperty(bb, FunctionValidator::attr_task_data.name); 
							val modelParam = getProperty(bb, FunctionValidator::attr_task_model.name); 
							val paramParam = getProperty(bb, FunctionValidator::attr_task_parameter.name); 
							if (dataParam.length > 0 && modelParam.length > 0 && paramParam.length > 0){
								val dObjName = args.getAttribute(dataParam);
								val mObjName = args.getAttribute(modelParam);
								val pObjName = args.getAttribute(paramParam);
								if (dObjName.length > 0 && mObjName.length > 0 && pObjName.length > 0){
									res = res + print_ds_TargetTool(dObjName);
									res = res + print_ds_DataSet(dObjName, mObjName);
									if (bb.estimateBlock != null){
										res = res + print_msteps_EstimationStep(dObjName, mObjName, pObjName, BLK_ESTIM_STEP + functionName);
									} else {
										res = res + print_msteps_SimulationStep(dObjName, mObjName, pObjName, BLK_SIMUL_STEP + functionName);
									}
								}
							}
						}
					}
				}
			}
		}
		return res;
	}
	//«print_msteps_StepDependencies»

	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	def print_msteps_EstimationStep(String dObjName, String mObjName, String pObjName, String stepId)'''
	<EstimationStep oid="«stepId»">
		«dObjName.print_mdef_TargetToolReference»
		«pObjName.print_msteps_ParametersToEstimate»
	</EstimationStep>
	'''
	
	def print_msteps_ParametersToEstimate(String pObjName){
		var pObj = pObjName.getParamObject;
		if (pObj == null) return "";
		'''
		<ParametersToEstimate>
			«FOR b: pObj.blocks»
				«IF b.structuralBlock != null»
					«FOR p: b.structuralBlock.parameters»
						«pObjName.print_msteps_ParameterEstimation(p)»
					«ENDFOR»
				«ENDIF»
				«IF b.variabilityBlock != null»
					«FOR p: b.variabilityBlock.statements»
						«IF p.parameter != null»
							«pObjName.print_msteps_ParameterEstimation(p.parameter)»
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
		'''	
	}
	
	def print_msteps_ParameterEstimation(String pObjName, SymbolDeclaration s){
		if (s.expression.list == null) return "";
		val fixed = s.expression.list.arguments.isAttributeTrue(AttributeValidator::attr_fix.name);
		var value = s.expression.list.arguments.getAttribute(AttributeValidator::attr_value.name);
		if (value.length == 0) value = "0";
		'''
			<ParameterEstimation>
				«print_ct_SymbolRef(pObjName, s.symbolName.name)»
				<InitialEstimate fixed="«fixed»">
					<ct:Real>«value»</ct:Real>
				</InitialEstimate>
			</ParameterEstimation>
		'''
	}
		
	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	def print_msteps_SimulationStep(String dObjName, String mObjName, String pObjName, String stepId)'''
	<SimulationStep  oid="«stepId»">		
		«dObjName.print_mdef_TargetToolReference»
	</SimulationStep>
	'''
	//«print_msteps_Observations»

	def print_msteps_Observations()'''
	<Observations>
	</Observations>
	'''	
	
	def getProperty(TaskFunctionBlock t, String name){
		if (t.estimateBlock != null){
			for (s: t.estimateBlock.statements){
				if (s.symbol != null){
					if (s.symbol.symbolName.name.equals(name)){
						if (s.symbol.expression != null){
							return s.symbol.expression.toStr;
						}
					}
				}
			}
		}
		if (t.simulateBlock != null){
			for (s: t.simulateBlock.statements){
				if (s.symbol != null){
					if (s.symbol.symbolName.name.equals(name)){
						if (s.symbol.expression != null){
							return s.symbol.expression.toStr;
						}
					}
				}
			}
		}
		if (t.executeBlock != null){
			for (s: t.executeBlock.statements){
				if (s.symbol != null){
					if (s.symbol.symbolName.name.equals(name)){
						if (s.symbol.expression != null){
							return s.symbol.expression.toStr;
						}
					}
				}
			}
		}
		return "";
	}
}