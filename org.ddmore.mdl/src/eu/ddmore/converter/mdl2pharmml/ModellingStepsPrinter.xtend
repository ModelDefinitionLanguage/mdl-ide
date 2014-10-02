package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.validation.AttributeValidator
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import eu.ddmore.converter.mdl2pharmml.domain.Operation
import org.ddmore.mdl.validation.PropertyValidator

class ModellingStepsPrinter extends DataSetPrinter{ 
	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mathPrinter, resolver);
	}	
	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////

	def print_msteps_ModellingSteps(Operation op){
		var res = "";
		res  = res + print_ds_TargetTool(op.mog.getDataObjName);
		res = res + print_ds_TargetDataSet(op.mog.getModelObjName, op.mog.getDataObjName);
		if (op.type.equals(BLK_ESTIM_STEP)){
			res = res + print_msteps_EstimationStep(op.mog.getModelObjName, op.mog.getDataObjName, op.mog.getTaskObjName, BLK_ESTIM_STEP + op.name);
		} else {
			res = res + print_msteps_SimulationStep(op.mog.getDataObjName, BLK_SIMUL_STEP + op.name);
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
	protected def print_msteps_EstimationStep(String pObjName, String dObjName, String tObjName, String stepId)'''
	<EstimationStep oid="«stepId»">
		«dObjName.print_mdef_TargetToolReference»
		«pObjName.print_msteps_ParametersToEstimate»
		«tObjName.print_msteps_Operation(1, OPERATION_EST_POP)»
	</EstimationStep>
	'''
		
	protected def print_msteps_ParametersToEstimate(String pObjName){
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
	
	protected def print_msteps_ParameterEstimation(String pObjName, SymbolDeclaration s){
		if (s.expression.list != null && s.symbolName != null) {
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
	}
	

	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	protected def print_msteps_SimulationStep(String dObjName, String stepId)'''
	<SimulationStep  oid="«stepId»">		
		«dObjName.print_mdef_TargetToolReference»
	</SimulationStep>
	'''
	
	///////////////////////////////////////////////
	//General
	///////////////////////////////////////////////

	protected def print_msteps_Operation(String tObjName, Integer order, String opType){
		var tObj = tObjName.getTaskObject;
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

	protected def print_msteps_Property(SymbolDeclaration s)
	'''
		«IF s.symbolName != null»
			«IF s.expression != null»
				«IF s.expression.expression != null»
					«print_msteps_Property(s.symbolName.name, s.expression.expression)»
				«ENDIF»
				«IF s.symbolName.name.equals(PropertyValidator::attr_task_algo.name)»
					«s.print_msteps_Algorithm»
				«ENDIF»	
			«ENDIF»
		«ENDIF»
	'''
		
	protected def print_msteps_Property(String propertyName, Expression expr)'''
		<Property name="«propertyName»">
			«print_Assign(expr)»
		</Property>
	'''

	protected def print_msteps_Algorithm(SymbolDeclaration s)
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