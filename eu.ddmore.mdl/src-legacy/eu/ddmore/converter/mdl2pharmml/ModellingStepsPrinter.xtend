package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.mdl.PropertyDeclaration
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.VariabilityType

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
		if (mObj != null && dObj != null && pObj != null && tObj != null) {
//			res = res + dObj.print_ds_TargetTool;
			res = res + mog.print_ds_TargetDataSet;
			var index = 1;

			for (b: tObj.blocks){
				if ((b.estimateBlock != null) || (b.simulateBlock != null)){
					var stepType = BLK_ESTIM_STEP;
					if (b.simulateBlock != null) stepType = BLK_SIMUL_STEP;
					if (stepType.equals(BLK_ESTIM_STEP)){
						res = res + print_msteps_EstimationStep(stepType + index, index, mObj, dObj, pObj, tObj);
					} else {
						res = res + print_msteps_SimulationStep(stepType + index, index, mObj, dObj, pObj, tObj);
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
	protected def print_msteps_EstimationStep(String stepId, Integer order, ModelObject mObj, DataObject dObj, ParameterObject pObj, TaskObject tObj)'''
		<EstimationStep oid="«stepId»">
			«dObj.print_mdef_ExternalDataSetReference»
			«pObj.print_msteps_ParametersToEstimate»
			«tObj.print_msteps_EstimateOperations(order)»
«««			«mObj.print_ct_variableAssignment»
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
		if (s.name != null && s.list != null) {
			//Skip correlation definitions
			val type = s.list.arguments.getAttribute(AttributeValidator::attr_type.name);
			if (type.equals(VariabilityType::COV.toString) || type.equals(VariabilityType::CORR.toString)) return "";
	
			val fixed = s.list.arguments.isAttributeTrue(AttributeValidator::attr_fix.name);
			var value = s.list.arguments.getAttributeExpression(AttributeValidator::attr_value.name);
			var lo = s.list.arguments.getAttributeExpression(AttributeValidator::attr_lo.name);
			var hi = s.list.arguments.getAttributeExpression(AttributeValidator::attr_hi.name);
//			var estimate = "0".print_ct_Value;
//			if (value != null)
//				estimate = value.print_Math_Expr.toString();
			'''
				<ParameterEstimation>
					«print_ct_SymbolRef(s.name)»
					<InitialEstimate fixed="«fixed»">
							«value.print_Math_Equation»
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
	protected def print_msteps_SimulationStep(String stepId, Integer order, ModelObject mObj, DataObject dObj, ParameterObject pObj, TaskObject tObj)'''
		<SimulationStep  oid="«stepId»">
			«dObj.print_mdef_ExternalDataSetReference»
«««			«mObj.print_ct_variableAssignment»
		</SimulationStep>
	'''
	
//	///////////////////////////////////////////////
//	//General
//	///////////////////////////////////////////////	
//	protected def print_ct_variableAssignment(ModelObject mObj){
//		//For covariates that are not transformations but have expression
//		var res = "";
//		for (b: mObj.blocks){
//			if (b.covariateBlock != null){
//				for (s: b.covariateBlock.variables){
//					if (s.symbolName != null && s.expression != null){
//						if (cm_assigned_vars.contains(s.symbolName.name)){
//							res = '''
//								<ct:VariableAssignment>
//									«s.symbolName.print_ct_SymbolName»
//									«s.expression.print_Assign»
//								</ct:VariableAssignment>
//							'''
//						}
//					}
//				}
//			}
//		}
//		return res;
//	}
	
	protected def print_msteps_Property(PropertyDeclaration s)'''
		«IF s.propertyName != null && s.expression != null»
			«IF !s.propertyName.argName.equals(PropertyValidator::attr_task_algo.name)»
				<Property name="«s.propertyName.argName»">
					«s.expression.print_Assign»
				</Property>
			«ENDIF»	
		«ENDIF»
	'''
	
	protected def print_msteps_Algorithm(PropertyDeclaration s)'''
		«IF s.propertyName != null && s.expression != null»
			«IF s.propertyName.argName.equals(PropertyValidator::attr_task_algo.name)»
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