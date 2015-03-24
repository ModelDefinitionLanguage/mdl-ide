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
		var pObj = Utils::getParameterObject(objects);
		var dObj = Utils::getDataObject(objects);
		var tObj = Utils::getTaskObject(objects);

		var res = "";
		var dependencies = ""; 
		if (tObj != null && pObj != null && dObj != null) {
			res = res + dObj.print_ds_TargetTool;
			res = res + mog.print_ds_TargetDataSet;
			var index = 1;

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
			«dObj.print_mdef_TargetToolReference»
			«pObj.print_msteps_ParametersToEstimate»
			«tObj.print_msteps_EstimateOperations(order)»
		</EstimationStep>
	'''
		
	protected def print_msteps_ParametersToEstimate(ParameterObject pObj)'''
		<ParametersToEstimate>
			«FOR b: pObj.blocks»
				«IF b.structuralBlock != null»
					«FOR p: b.structuralBlock.parameters»
						«p.print_msteps_ParameterEstimation»
					«ENDFOR»
				«ENDIF»
				«IF b.variabilityBlock != null»
					«FOR p: b.variabilityBlock.parameters»
						«p.print_msteps_ParameterEstimation»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
	'''	
	
	protected def print_msteps_ParameterEstimation(SymbolDeclaration s){
		if (s.symbolName != null && s.list != null) {
			val fixed = s.list.arguments.isAttributeTrue(AttributeValidator::attr_fix.name);
			var value = s.list.arguments.getAttributeExpression(AttributeValidator::attr_value.name);
			var lo = s.list.arguments.getAttributeExpression(AttributeValidator::attr_lo.name);
			var hi = s.list.arguments.getAttributeExpression(AttributeValidator::attr_hi.name);
			var estimate = "0".print_ct_Value;
			if (value != null){
				estimate = value.print_Math_Expr.toString();
			}
			'''
				<ParameterEstimation>
					«print_ct_SymbolRef(s.symbolName.name)»
					<InitialEstimate fixed="«fixed»">
						«estimate»
					</InitialEstimate>
					«IF lo != null»
						<LowerBound>
							«lo.print_Math_Equation»
						</LowerBound>
					«ENDIF»
					«IF hi != null»
						<UpperBound>
							«hi.print_Math_Equation»
						</UpperBound>
					«ENDIF»
				</ParameterEstimation>
			'''
		}
	}
	
	protected def print_msteps_EstimateOperations(TaskObject tObj, Integer order)'''
		«FOR b: tObj.blocks»
			«IF b.estimateBlock != null»
				<Operation order="«order»" opType="«OPERATION_EST_POP»">
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

	protected def print_msteps_Property(PropertyDeclaration s)'''
		«IF s.propertyName != null && s.expression != null»
			«IF !s.propertyName.name.equals(PropertyValidator::attr_task_algo.name)»
				<Property name="«s.propertyName.name»">
					«s.expression.print_Assign»
				</Property>
			«ENDIF»	
		«ENDIF»
	'''
	
	protected def print_msteps_Algorithm(PropertyDeclaration s)'''
		«IF s.propertyName != null && s.expression != null»
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
	'''
}