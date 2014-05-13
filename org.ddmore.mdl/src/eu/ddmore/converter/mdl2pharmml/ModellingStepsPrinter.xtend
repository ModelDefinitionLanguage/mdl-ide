package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.TaskFunctionBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.validation.FunctionValidator
import org.ddmore.mdl.types.InputFormatType
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl
import org.apache.commons.io.FilenameUtils

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
							val dataParam = getProperty(bb, FunctionValidator::attr_task_data.name); 
							val modelParam = getProperty(bb, FunctionValidator::attr_task_model.name); 
							if ((dataParam.length > 0) && (modelParam.length > 0)){
								val data = args.getAttribute(dataParam);
								val model = args.getAttribute(modelParam);
								if (data.length > 0 && model.length > 0){
									res  = res + print_mdef_TargetTool(data);
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
		«print_mdef_TargetToolReference(BLK_TARGET_TOOL + dObjName)»
		«print_msteps_DataSet(dObjName, mObjName)»
		«print_msteps_ParametersToEstimate»
	</EstimationStep>
	'''
	
	def print_msteps_ParametersToEstimate()'''
	<ParametersToEstimate>
	</ParametersToEstimate>
	'''	
		
	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	def print_msteps_SimulationStep(String dObjName, String mObjName, String stepId)'''
	<SimulationStep  oid="«stepId»">		
		«print_mdef_TargetToolReference(BLK_TARGET_TOOL + dObjName)»
		«print_msteps_Observations»
	</SimulationStep>
	'''

	def print_msteps_Observations()'''
	<Observations>
	</Observations>
	'''	
	
	def print_mdef_TargetTool(String dObjName){
		val dObj = getDataObject(dObjName);
		if (dObj == null) return "";
		for (b: dObj.blocks){
			if (b.sourceBlock != null){
				if (b.sourceBlock.inlineBlock == null){
					val script = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_script.name);
					if (script.length > 0){
						val baseName = FilenameUtils::getBaseName(script);
						val path = FilenameUtils::getPath(script);
						return print_mdef_TargetTool(BLK_TARGET_TOOL + dObjName, "R", 
							BLK_TARGET_TOOL_DATA + dObjName, baseName, path
						);
					}
				}
			}
		}
	}

	def print_mdef_TargetTool(String ttOid, String toolName, String ttdOid, String ttdName, String ttdURL)'''
	<TargetTool oid="«ttOid»">
		<TargetToolName>«toolName»</TargetToolName>
		<ds:TargetToolData>
			<ds:ImportTargetData oid="«ttdOid»">
			<ds:name>«ttdName»</ds:name>
			<ds:url>«ttdURL»</ds:url>
			</ds:ImportTargetData>
		</ds:TargetToolData>
	</TargetTool>
	'''
	
	def print_mdef_TargetToolReference(String ttrOidRef)'''
	<TargetToolReference>
		<ct:OidRef oidRef="«ttrOidRef»"/>
	</TargetToolReference>
	'''
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
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
					val expectedVar = mObj.getModelInputVariable(columnId);
					if (expectedVar != null){
						var blkIdRef = mObjName.getReferenceBlock(expectedVar.symbolName.symbol.name);
						res = res + print_msteps_ColumnMapping(columnId, expectedVar.symbolName.symbol.name, blkIdRef);
					}
				}
			}
			if (b.dataDerivedBlock != null){
				var derivedVars = newHashSet;
				for (s: b.dataDerivedBlock.statements){
					var iterator = s.eResource.getAllContents();
				    while (iterator.hasNext()){
	    				val obj = iterator.next();
	    				if (obj instanceof SymbolDeclarationImpl){
	    					val symbol = obj as SymbolDeclaration;
	    					if (!derivedVars.contains(symbol.symbolName.name))
	    						derivedVars.add(symbol.symbolName.name);
						}
					}
				}
				for (s: derivedVars){
					val expectedVar = mObj.getModelInputVariable(s);
					if (expectedVar != null){
						var blkIdRef = mObjName.getReferenceBlock(expectedVar.symbolName.symbol.name);
						res = res + print_msteps_ColumnMapping(s, expectedVar.symbolName.symbol.name, blkIdRef);
					}
				}
			}
			
		}
		var oid = "ds." + dObjName;
		'''
		<NONMEMdataSet oid="«oid»">
			«res»
		</NONMEMdataSet>
		'''
	}
	
	def print_msteps_ColumnMapping(String columnId, String symbId, String blkIdRef)'''
		<ColumnMapping>
			<ds:ColumnRef columnIdRef="«columnId»"/>
			<ds:SymbRef «IF blkIdRef.length > 0» blkIdRef="«blkIdRef»" «ENDIF»symbIdRef="«symbId»"/>
		</ColumnMapping>
	'''
	
	//Return a corresponding model variable (matched by name or by alias name!)
	def getModelInputVariable(ModelObject mObj, String name){
		for (b: mObj.blocks){
			if (b.inputVariablesBlock != null){
				for (s: b.inputVariablesBlock.variables){
					if (s.symbolName.symbol.name.equals(name)){
						return s;
					}
					if (s.expression != null){
						if (s.expression.list != null){
							var alias = s.expression.list.arguments.getAttribute(AttributeValidator::attr_alias.name);
							if (alias.length > 0){
								if (alias.equals(name)) return s;
							}
						}
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
			if (b.sourceBlock != null){
				if (b.sourceBlock.list!=null){
					val inputFormat = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_inputformat.name);
					if (inputFormat.equals(InputFormatType::FORMAT_NONMEM)){
						res  = res + print_NONMEM_DataSet(dObjName, mObjName);
					}
					val file = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_file.name);
					if (file.length > 0){
						var delimeter = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_delimiter.name);
						if (delimeter.length == 0) delimeter = AttributeValidator::attr_delimiter.defaultValue;
						res = res + print_ds_ExternalSource("ds." + dObjName, file, file, inputFormat, delimeter);
					}
				}
			}
		}
		return res;
	}	
	
	
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