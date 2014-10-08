package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import eu.ddmore.converter.mdl2pharmml.domain.Operation
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.mdl.PropertyDeclaration
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.MclObject

class ModellingStepsPrinter extends DataSetPrinter{ 
	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mathPrinter, resolver);
	}	
	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////

	def print_msteps_ModellingSteps(Operation op){
		var res = "";
		res  = res + print_ds_TargetTool(op.mog.getDataObject);
		res = res + print_ds_TargetDataSet(op.mog.getModelObject, op.mog.getDataObject);
		if (op.type.equals(BLK_ESTIM_STEP)){
			res = res + print_msteps_EstimationStep(op.mog, BLK_ESTIM_STEP + op.name);
		} else {
			res = res + print_msteps_SimulationStep(op.mog, BLK_SIMUL_STEP + op.name);
		}
		'''
		<ModellingSteps xmlns="«xmlns_mstep»">
			«res»
		</ModellingSteps>		
		'''	
	}

	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	protected def print_msteps_EstimationStep(MOGObject mog, String stepId)'''
	<EstimationStep oid="«stepId»">
		«mog.getDataObject.print_mdef_TargetToolReference»
		«mog.getParameterObject.print_msteps_ParametersToEstimate»
		«mog.getTaskObject.print_msteps_Operation(1, OPERATION_EST_POP)»
	</EstimationStep>
	'''
		
	protected def print_msteps_ParametersToEstimate(MclObject pObj){
		if (pObj.parameterObject == null) return "";
		'''
		<ParametersToEstimate>
			«FOR b: pObj.parameterObject.blocks»
				«IF b.structuralBlock != null»
					«FOR p: b.structuralBlock.parameters»
						«pObj.print_msteps_ParameterEstimation(p)»
					«ENDFOR»
				«ENDIF»
				«IF b.variabilityBlock != null»
					«FOR p: b.variabilityBlock.statements»
						«IF p.parameter != null»
							«pObj.print_msteps_ParameterEstimation(p.parameter)»
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
		'''	
	}
	
	protected def print_msteps_ParameterEstimation(MclObject pObj, SymbolDeclaration s){
		if (s.list != null && s.symbolName != null) {
			val fixed = s.list.arguments.isAttributeTrue(AttributeValidator::attr_fix.name);
			var value = s.list.arguments.getAttribute(AttributeValidator::attr_value.name);
			if (value.length == 0) value = "0";
			'''
				<ParameterEstimation>
					«print_ct_SymbolRef(pObj.objectName.name, s.symbolName.name)»
					<InitialEstimate fixed="«fixed»">
						<ct:Real>«value»</ct:Real>
					</InitialEstimate>
				</ParameterEstimation>
			'''
		}
	}
	

	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	protected def print_msteps_SimulationStep(MOGObject mog, String stepId)'''
	<SimulationStep  oid="«stepId»">		
		«mog.getDataObject.print_mdef_TargetToolReference»
	</SimulationStep>
	'''
	
	///////////////////////////////////////////////
	//General
	///////////////////////////////////////////////

	protected def print_msteps_Operation(MclObject tObj, Integer order, String opType){
		if (tObj.taskObject == null) return "";
	'''
		«FOR b: tObj.taskObject.blocks»
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
				«print_msteps_Property(s.propertyName.name, s.expression)»
				«IF s.propertyName.name.equals(PropertyValidator::attr_task_algo.name)»
					«s.print_msteps_Algorithm»
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