package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.mdl.PropertyDeclaration
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.InputFormatType
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.MOGObject

class ModellingStepsPrinter extends DataSetPrinter{ 
	private var ModelObject mObj;
	private var ParameterObject pObj;
	private var DataObject dObj;
	private var TaskObject tObj;
	
	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mathPrinter, resolver);
	}
	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////

	def print_msteps_ModellingSteps(MOGObject mog){
		this.mObj = mog.getModelObject.modelObject;
		this.pObj = mog.getParameterObject.parameterObject;
		this.dObj = mog.getDataObject.dataObject;
		this.tObj = mog.getTaskObject.taskObject;
		
		var res = "";
		var dependencies = ""; 
		res  = res + print_ds_TargetTool(dObj);
		res = res + print_ds_TargetDataSet(mObj, dObj);
		var index = 1;
		for (b: tObj.blocks){
			if ((b.estimateBlock != null) || (b.simulateBlock != null)){
				var stepType = BLK_ESTIM_STEP;
				if (b.simulateBlock != null) stepType = BLK_SIMUL_STEP;
				if (stepType.equals(BLK_ESTIM_STEP)){
					res = res + print_msteps_EstimationStep(stepType + index, index);
				} else {
					res = res + print_msteps_SimulationStep(stepType + index, index);
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
	protected def print_msteps_EstimationStep(String stepId, Integer order)'''
	<EstimationStep oid="«stepId»">
		«print_mdef_TargetToolReference»
		«print_msteps_ParametersToEstimate»
		«print_msteps_Operation(order, OPERATION_EST_POP)»
	</EstimationStep>
	'''
		
	protected def print_msteps_ParametersToEstimate(){
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
					«FOR p: b.variabilityBlock.statements»
						«IF p.parameter != null»
							«print_msteps_ParameterEstimation(p.parameter)»
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
		'''	
	}
	
	protected def print_msteps_ParameterEstimation(SymbolDeclaration s){
		if (s.list != null && s.symbolName != null) {
			val fixed = s.list.arguments.isAttributeTrue(AttributeValidator::attr_fix.name);
			var value = s.list.arguments.getAttribute(AttributeValidator::attr_value.name);
			var lo = s.list.arguments.getAttribute(AttributeValidator::attr_lo.name);
			var hi = s.list.arguments.getAttribute(AttributeValidator::attr_hi.name);
			if (value.length == 0) value = "0";
			'''
				<ParameterEstimation>
					«print_ct_SymbolRef(s.symbolName.name)»
					<InitialEstimate fixed="«fixed»">
						<ct:Real>«value»</ct:Real>
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
	protected def print_msteps_SimulationStep(String stepId, Integer order)'''
	<SimulationStep  oid="«stepId»">
		«print_mdef_TargetToolReference»
	</SimulationStep>
	'''
	
	///////////////////////////////////////////////
	//General
	///////////////////////////////////////////////
	
	protected def print_mdef_TargetToolReference(){
		var oidRef = "";
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
		if (oidRef.length > 0)
		'''
			<TargetToolReference>
				<ct:OidRef oidRef="«oidRef»"/>
			</TargetToolReference>
		'''
	}	

	protected def print_msteps_Operation(Integer order, String opType){
		if (tObj == null) return "";
		'''
			«FOR b: tObj.blocks»
				«IF b.estimateBlock != null»
					<Operation order="«order»" opType="«opType»">
						«FOR s: b.estimateBlock.statements»
							«s.print_msteps_Property»
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
				«IF s.propertyName.name.equals(PropertyValidator::attr_task_algo.name)»
					«s.print_msteps_Algorithm»
				«ELSE»
					«print_msteps_Property(s.propertyName.name, s.expression)»
				«ENDIF»	
			«ENDIF»
		«ENDIF»
	'''
		
	protected def print_msteps_Property(String propertyName, AnyExpression expr)'''
		<Property name="«propertyName»">
			«print_Assign(expr)»
		</Property>
	'''

	protected def print_msteps_Algorithm(PropertyDeclaration s)
	'''
		«IF s.expression.vector != null»
			«FOR algoName: s.expression.vector.values»
				«IF algoName.string != null»
					<Algorithm definition="«algoName.string»"/>
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	'''
}