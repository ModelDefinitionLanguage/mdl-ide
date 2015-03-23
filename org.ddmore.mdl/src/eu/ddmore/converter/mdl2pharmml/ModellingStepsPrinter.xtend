package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.mdl.PropertyDeclaration
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.InputFormatType
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.validation.Utils

class ModellingStepsPrinter extends DataSetPrinter{ 
	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mathPrinter, resolver);
	}
	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////

	def print_msteps_ModellingSteps(MOGObject mog){
		var objects = Utils::getMOGObjects(mog);
		var mObj = Utils::getModelObject(objects);
		var pObj = Utils::getParameterObject(objects);
		var dObj = Utils::getDataObject(objects);
		var tObj = Utils::getTaskObject(objects);

		var res = "";
		var dependencies = ""; 
		res  = res + print_ds_TargetTool(dObj);
		res = res + print_ds_TargetDataSet(mObj, dObj, mog);
		var index = 1;

		if (tObj != null) {
			for (b: tObj.blocks){
				if ((b.estimateBlock != null) || (b.simulateBlock != null)){
					var stepType = BLK_ESTIM_STEP;
					if (b.simulateBlock != null) stepType = BLK_SIMUL_STEP;
					if (stepType.equals(BLK_ESTIM_STEP)){
						res = res + print_msteps_EstimationStep(stepType + index, index, dObj, pObj, tObj);
					} else {
						res = res + print_msteps_SimulationStep(stepType + index, index, dObj);
					}
					dependencies  = dependencies +
					'''
					<mstep:Step>
						<ct:OidRef oidRef="«stepType + index»"/>
					</mstep:Step>
					'''
					index  = index + 1;
				}
			}
		}
		'''
		<ModellingSteps xmlns="«xmlns_mstep»">
			«res»
			«IF dependencies.length > 0»
				<mstep:StepDependencies>
					«dependencies»
				</mstep:StepDependencies>
			«ENDIF»
		</ModellingSteps>		
		'''	
	}

	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	protected def print_msteps_EstimationStep(String stepId, Integer order, DataObject dObj, ParameterObject pObj, TaskObject tObj)'''
	<EstimationStep oid="«stepId»">
		«print_mdef_TargetToolReference(dObj)»
		«print_msteps_ParametersToEstimate(pObj)»
		«print_msteps_Operation(order, OPERATION_EST_POP, tObj)»
	</EstimationStep>
	'''
		
	protected def print_msteps_ParametersToEstimate(ParameterObject pObj){
		if (pObj == null) return "";
		'''
		<ParametersToEstimate>
			«FOR b: pObj.blocks»
				«IF b.structuralBlock != null»
					«FOR p: b.structuralBlock.parameters»
						«print_msteps_ParameterEstimation(p)»
					«ENDFOR»
				«ENDIF»
				«IF b.variabilityBlock != null»
					«FOR p: b.variabilityBlock.parameters»
						«print_msteps_ParameterEstimation(p)»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
		'''	
	}
	
	protected def print_msteps_ParameterEstimation(SymbolDeclaration s){
		if (s.list != null && s.symbolName != null) {
			val fixed = s.list.arguments.isAttributeTrue(AttributeValidator::attr_fix.name);
			var value = s.list.arguments.getAttributeExpression(AttributeValidator::attr_value.name);
			var lo = s.list.arguments.getAttribute(AttributeValidator::attr_lo.name);
			var hi = s.list.arguments.getAttribute(AttributeValidator::attr_hi.name);
			var estimate = "0";
			if (value != null){
				estimate = value.print_Math_Expr.toString();
			}
			'''
				<ParameterEstimation>
					«print_ct_SymbolRef(s.symbolName.name)»
					<InitialEstimate fixed="«fixed»">
						«estimate»
					</InitialEstimate>
					«IF lo.length > 0»
						<LowerBound>
							<ct:Real>«lo»</ct:Real>
						</LowerBound>
					«ENDIF»
					«IF hi.length > 0»
						<UpperBound>
							<ct:Real>«hi»</ct:Real>
						</UpperBound>
					«ENDIF»
				</ParameterEstimation>
			'''
		}
	}

	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	protected def print_msteps_SimulationStep(String stepId, Integer order, DataObject dObj)'''
	<SimulationStep  oid="«stepId»">
		«print_mdef_TargetToolReference(dObj)»
	</SimulationStep>
	'''
	
	///////////////////////////////////////////////
	//General
	///////////////////////////////////////////////
	
	protected def print_mdef_TargetToolReference(DataObject dObj){
		var oidRef = "";
		if (dObj != null){
			for (b: dObj.blocks)	{
				if (b.sourceBlock != null){
					for (s: b.sourceBlock.statements){
						if (s.propertyName.name.equals(PropertyValidator::attr_inputformat.name) && s.expression != null){
							if (s.expression.toStr.equals(InputFormatType::NONMEM_FORMAT.toString))
								oidRef = BLK_DS_NONMEM_DATASET;
						}
					}
				}
			}
		}
		if (oidRef.length > 0)
		'''
			<TargetToolReference>
				<ct:OidRef oidRef="«oidRef»"/>
			</TargetToolReference>
		'''
	}	

	//First print properties, then define algorithm!
	protected def print_msteps_Operation(Integer order, String opType, TaskObject tObj){
		if (tObj == null) return "";
		'''
			«FOR b: tObj.blocks»
				«IF b.estimateBlock != null»
					<Operation order="«order»" opType="«opType»">
						«FOR s: b.estimateBlock.statements»
							«s.print_msteps_Property»
						«ENDFOR»
						«FOR s: b.estimateBlock.statements»
							«s.print_msteps_Algorithm»
						«ENDFOR»
					</Operation>
				«ENDIF»
			«ENDFOR»
		'''
	}

	protected def print_msteps_Property(PropertyDeclaration s)
	'''
		«IF s.propertyName != null»
			«IF s.expression != null»
				«IF !s.propertyName.name.equals(PropertyValidator::attr_task_algo.name)»
					<Property name="«s.propertyName.name»">
						«print_Assign(s.expression)»
					</Property>
				«ENDIF»	
			«ENDIF»
		«ENDIF»
	'''
	
	protected def print_msteps_Algorithm(PropertyDeclaration s)
	'''
		«IF s.propertyName != null»
			«IF s.expression != null»
				«IF s.propertyName.name.equals(PropertyValidator::attr_task_algo.name)»
					«IF s.expression.vector != null && s.expression.vector.expression != null &&
						s.expression.vector.expression.expressions != null »
						«FOR algoName: s.expression.vector.expression.expressions»
							«IF algoName != null»
								<Algorithm definition="«algoName.toStr»"/>
							«ENDIF»
						«ENDFOR»
					«ENDIF»
				«ENDIF»	
			«ENDIF»
		«ENDIF»
	'''
}