package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.TaskFunctionBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.Arguments

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
		<ModellingSteps>
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
							val dataParam = getProperty(bb, "data"); 
							val modelParam = getProperty(bb, "model"); 
							if ((dataParam.length > 0) && (modelParam.length > 0)){
								val data = args.getAttribute(dataParam);
								val model = args.getAttribute(modelParam);
								if (data.length > 0 && model.length > 0){
									if (bb.estimateBlock != null){
										res = res + print_msteps_EstimationStep(data, model, "estimStep_" + functionName);
									} else {
										res = res + print_msteps_SimulationStep(data, model, "simulStep_" + functionName);
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
		«print_msteps_DataSet(dObjName, mObjName)»
	</EstimationStep>
	'''
		
	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	def print_msteps_SimulationStep(String dObjName, String mObjName, String stepId)'''
	<SimulationStep  oid="«stepId»">		
		«print_msteps_VariableAssignments»	
		«print_msteps_Observations»
	</SimulationStep>
	'''

	//TODO
	def print_msteps_VariableAssignments() { 
		//call print_msteps_VariableAssignment
	}	

	//TODO
	def print_msteps_Observations() { 
		//call print_msteps_Observation
	}
	
	def print_msteps_VariableAssignment(SymbolDeclaration s, String blockId)'''
	<ct:VariableAssignment>
	</ct:VariableAssignment>
	'''
	
	def print_msteps_Observation(String ref, String blockID)'''
	<Observations>
		<Timepoints>
			«print_ct_Sequence("", "", "")»
		</Timepoints>
		<Continuous>
			<ct:SymbRef symbIdRef="«ref»"«IF blockID.length > 0» blkIdRef="«blockID»"«ENDIF»/>
		</Continuous>
	</Observations>
	'''	
	
	///////////////////////////////////////////////
	// III.c Step Dependencies
	////////////////////////////////////////////////
	def print_msteps_StepDependencies()'''
	<StepDependencies>
	</StepDependencies>
	'''
	
	def print_msteps_Step(String ref)'''
	<Step>
		<ct:OidRef oidRef="«ref»"/>
	</Step>
	'''
	
	//+
	def print_msteps_ObjectiveDataSet()'''
	<ObjectiveDataSet dataSetRef="">
	</ObjectiveDataSet>
	'''
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	//Assume that 
	// DATA_INPUT_VARIABLES contains attribute 'mapping' with the name of a corresponding variable
	// MODEL_INPUT_VARIABLES contains attribute 'use'
	def print_NONMEM_DataSet(String dObjName, String mObjName){
		val dObj = getDataObject(dObjName);
		val mObj = getModelObject(mObjName);
		if (dObj == null || mObj == null) return "";
		var res = "";
		for (b: dObj.blocks){
			if (b.dataInputBlock != null){
				for (s: b.dataInputBlock.variables){
					var columnId = s.symbolName.name;
					var symbId = s.symbolName.name;
					if (s.expression != null){
						if (s.expression.list != null){
							val newName = s.expression.list.arguments.getAttribute("mapping");
							if (newName.length > 0)
								symbId = newName;
						}
					}
					val expectedVar = mObj.getModelInputVariable(symbId);
					var blkIdRef = mObjName.getReferenceBlock(symbId);
					if (expectedVar != null){
						res = res + print_msteps_ColumnMapping(columnId, symbId, blkIdRef);
					}
				}
			}
		}
		'''
		<NONMEMdataSet>
			«res»
		</NONMEMdataSet>
		'''
	}
	
	def print_msteps_ColumnMapping(String columnId, String symbId, String blkIdRef)'''
		<NMColumnMapping>
			<ds:ColumnRef columnIdRef="«columnId»"/>
			<ds:SymbRef «IF blkIdRef.length > 0» blkIdRef="«blkIdRef»" «ENDIF»symbIdRef="«symbId»"/>
		</NMColumnMapping>
	'''
	
	//Return a corresponding model variable
	def getModelInputVariable(ModelObject mObj, String name){
		for (b: mObj.blocks){
			if (b.inputVariablesBlock != null){
				for (s: b.inputVariablesBlock.variables){
					if (s.symbolName.name.equals(name)){
						return s;
					}
				}
			}
		}
		return null;
	}
    
    //+ Print data set
	def print_msteps_DataSet(String dObjName, String mObjName){
		val dObj = getDataObject(dObjName);
		val mObj = getModelObject(mObjName);
		if (dObj == null || mObj == null) return "";
		var res = "";
		for (b: dObj.blocks)	{
			if (b.fileBlock != null){
				for (s: b.fileBlock.statements){
					if (s.variable.symbolName.name.equals("data")){
						if (s.variable.expression != null){
							if (s.variable.expression.list != null){
								val source = s.variable.expression.list.arguments.getAttribute("source");
								val inputformat = s.variable.expression.list.arguments.getAttribute("inputformat");
								if (inputformat.equals(ENUM_FORMAT_NONMEM)){
									res  = res + print_NONMEM_DataSet(dObjName, mObjName);
								}
								if (source.length > 0){
									//res = res + source.print_msteps_ExternalSource;
								}
							}
						}
					}
				}
			}
		}
		return res;
	}
	
	def print_msteps_ExternalSource(String source)'''				
		«val dotIndex = source.indexOf('.')»
		<ExternalSource url="file=///«source»" 
			format="«source.substring(dotIndex + 1)»"/>	
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
	
	def getModelObject(String name){
		for (o: mcl.objects){
			if (o.objectName.name.equals(name)) return o.modelObject;
		}
		return null;
	}
	
	def getDataObject(String name){
		for (o: mcl.objects){
			if (o.objectName.name.equals(name)) return o.dataObject;
		}
		return null;
	}
	
	def getTaskObject(String name){
		for (o: mcl.objects){
			if (o.objectName.name.equals(name)) return o.taskObject;
		}
		return null;
	}
	
}