package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.TaskFunctionBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.validation.FunctionValidator

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
					val functionName = st.expression.identifier.symbol.name;
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
							if ((dataParam.length > 0) && (modelParam.length > 0)){
								val data = args.getAttribute(dataParam);
								val model = args.getAttribute(modelParam);
								if (data.length > 0 && model.length > 0){
									res = res + print_ds_TargetTool(data);
									res = res + print_ds_DataSet(data, model);
									if (bb.estimateBlock != null){
										res = res + print_msteps_EstimationStep(data, model, BLK_ESTIM_STEP + functionName);
									} else {
										res = res + print_msteps_SimulationStep(data, model, BLK_SIMUL_STEP + functionName);
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
	def print_msteps_EstimationStep(String dObjName, String mObjName, String stepId)'''
	<EstimationStep oid="«stepId»">
		«dObjName.print_mdef_TargetToolReference»
	</EstimationStep>
	'''
	//«print_msteps_ParametersToEstimate»
	
	def print_msteps_ParametersToEstimate()'''
	<ParametersToEstimate>
	</ParametersToEstimate>
	'''	
		
	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	def print_msteps_SimulationStep(String dObjName, String mObjName, String stepId)'''
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