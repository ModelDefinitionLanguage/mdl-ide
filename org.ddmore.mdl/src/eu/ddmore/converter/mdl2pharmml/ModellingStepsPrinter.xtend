package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import eu.ddmore.converter.mdl2pharmml.domain.Operation

class ModellingStepsPrinter extends DataSetPrinter{ 
	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mathPrinter, resolver);
	}	
	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////

	def print_msteps_ModellingSteps(Operation op){
		var res = "";
		res  = res + print_ds_TargetTool(op.dObjName);
		res = res + print_ds_TargetDataSet(op.mObjName, op.dObjName);
		if (op.type.equals(BLK_ESTIM_STEP)){
			res = res + print_msteps_EstimationStep(op.pObjName, op.dObjName, BLK_ESTIM_STEP + op.name);
		} else {
			res = res + print_msteps_SimulationStep(op.dObjName, BLK_SIMUL_STEP + op.name);
		}
		'''
		<ModellingSteps xmlns="«xmlns_mstep»">
			«res»
		</ModellingSteps>		
		'''	
	}
	//«print_msteps_StepDependencies»

	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	protected def print_msteps_EstimationStep(String pObjName, String dObjName, String stepId)'''
	<EstimationStep oid="«stepId»">
		«dObjName.print_mdef_TargetToolReference»
		«pObjName.print_msteps_ParametersToEstimate»
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
	protected def print_msteps_SimulationStep(String dObjName, String stepId)'''
	<SimulationStep  oid="«stepId»">		
		«dObjName.print_mdef_TargetToolReference»
	</SimulationStep>
	'''
	//«print_msteps_Observations»

	protected def print_msteps_Observations()'''
	<Observations>
	</Observations>
	'''	
}