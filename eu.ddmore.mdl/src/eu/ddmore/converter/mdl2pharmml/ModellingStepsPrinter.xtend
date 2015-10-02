package eu.ddmore.converter.mdl2pharmml

import static eu.ddmore.converter.mdl2pharmml.Constants.*
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.validation.MogValidator
import eu.ddmore.mdl.utils.MclUtils
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.validation.ListDefinitionProvider

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToInteger
import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString


class ModellingStepsPrinter { 
	
	extension MclUtils mu = new MclUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider

	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////
	def print_msteps_ModellingSteps(MogValidator validator){
		var mObj = validator.mdlObj
		var pObj = validator.paramObj
		var dObj = validator.dataObj
		var tObj = validator.taskObj

		var res = "";
		var dependencies = ""; 
		if (mObj != null && dObj != null && pObj != null && tObj != null) {
//			res = res + dObj.print_ds_TargetTool;
//			res = res + mog.print_ds_TargetDataSet;
			var index = 1;

			for (b: tObj.blocks){
//				if ((b.estimateBlock != null) || (b.simulateBlock != null)){
					var stepType = BLK_ESTIM_STEP;
//					if (b.simulateBlock != null) stepType = BLK_SIMUL_STEP;
//					if (stepType.equals(BLK_ESTIM_STEP)){
						res = res + print_msteps_EstimationStep(stepType + index, index, mObj, dObj, pObj, tObj);
//					} else {
//						res = res + print_msteps_SimulationStep(stepType + index, index, mObj, dObj, pObj, tObj);
//					}
					dependencies  = dependencies +
					'''
					<mstep:Step>
						<ct:OidRef oidRef="«stepType + index»"/>
					</mstep:Step>
					'''
					index  = index + 1;
//				}
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
	protected def print_msteps_EstimationStep(String stepId, Integer order, MclObject mObj, MclObject dObj, MclObject pObj, MclObject tObj)'''
		<EstimationStep oid="«stepId»">
«««			«dObj.print_mdef_ExternalDataSetReference»
			«pObj.print_msteps_ParametersToEstimate»
«««			«tObj.print_msteps_EstimateOperations(order)»
«««			«mObj.print_ct_variableAssignment»
		</EstimationStep>
	'''
		
		
	def writeParameterEstimate(Statement stmt){
		switch(stmt){
			ListDefinition:
				'''
				<ParameterEstimation>
					«stmt.name.getLocalSymbolReference»
					<InitialEstimate fixed="«stmt.list.getAttributeExpression('fix')?.convertToString ?: 'false'»">
						«stmt.list.getAttributeExpression('value').expressionAsEquation»
					</InitialEstimate>
					«IF stmt.list.getAttributeExpression('lo') != null»
						<LowerBound>
							«stmt.list.getAttributeExpression('lo').expressionAsEquation»
						</LowerBound>
					«ENDIF»
					«IF stmt.list.getAttributeExpression('hi') != null»
						<UpperBound>
							«stmt.list.getAttributeExpression('hi').expressionAsEquation»
						</UpperBound>
					«ENDIF»
				</ParameterEstimation>
				'''
			default:''''''
		}
	}
		
	protected def print_msteps_ParametersToEstimate(MclObject pObj)'''
		<ParametersToEstimate>
			«FOR stmt: pObj.paramStructuralParams»
				«stmt.writeParameterEstimate»
			«ENDFOR»
			«FOR stmt: pObj.paramVariabilityParams»
				«stmt.writeParameterEstimate»
			«ENDFOR»
«««				«IF b.structuralBlock != null»
«««					«FOR p: b.structuralBlock.parameters»
«««						«p.print_msteps_ParameterEstimation»
«««					«ENDFOR»
«««				«ENDIF»
«««				«IF b.variabilityBlock != null»
«««					«FOR p: b.variabilityBlock.parameters»
«««						«p.print_msteps_ParameterEstimation»
«««					«ENDFOR»
«««				«ENDIF»
«««			«ENDFOR»
		</ParametersToEstimate>
	'''	
	
//	protected def print_msteps_ParameterEstimation(SymbolDeclaration s){
//		if (s.name != null && s.list != null) {
//			//Skip correlation definitions
//			val type = s.list.arguments.getAttribute(AttributeValidator::attr_type.name);
//			if (type.equals(VariabilityType::COV.toString) || type.equals(VariabilityType::CORR.toString)) return "";
//	
//			val fixed = s.list.arguments.isAttributeTrue(AttributeValidator::attr_fix.name);
//			var value = s.list.arguments.getAttributeExpression(AttributeValidator::attr_value.name);
//			var lo = s.list.arguments.getAttributeExpression(AttributeValidator::attr_lo.name);
//			var hi = s.list.arguments.getAttributeExpression(AttributeValidator::attr_hi.name);
////			var estimate = "0".print_ct_Value;
////			if (value != null)
////				estimate = value.print_Math_Expr.toString();
//			'''
//				<ParameterEstimation>
//					«print_ct_SymbolRef(s.name)»
//					<InitialEstimate fixed="«fixed»">
//							«value.print_Math_Equation»
//					</InitialEstimate>
//					«IF lo != null»
//						<LowerBound>
//							«lo.print_Math_Equation»
//						</LowerBound>
//					«ENDIF»
//					«IF hi != null»
//						<UpperBound>
//							«hi.print_Math_Equation»
//						</UpperBound>
//					«ENDIF»
//				</ParameterEstimation>
//			'''
//		}
//	}
//	
//	protected def print_msteps_EstimateOperations(MclObject tObj, Integer order)'''
//		«FOR b: tObj.blocks»
//			«IF b.estimateBlock != null»
//				<Operation order="«order»" opType="«OPERATION_EST_POP»">
//					«FOR s: b.estimateBlock.statements»
//						«s.print_msteps_Property»
//					«ENDFOR»
//					«FOR s: b.estimateBlock.statements»
//						«s.print_msteps_Algorithm»
//					«ENDFOR»
//				</Operation>
//			«ENDIF»
//		«ENDFOR»
//	'''

	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
//	protected def print_msteps_SimulationStep(String stepId, Integer order, MclObject mObj, MclObject dObj, MclObject pObj, MclObject tObj)'''
//		<SimulationStep  oid="«stepId»">
//			«dObj.print_mdef_ExternalDataSetReference»
//«««			«mObj.print_ct_variableAssignment»
//		</SimulationStep>
//	'''
	
//	///////////////////////////////////////////////
//	//General
//	///////////////////////////////////////////////	
//	protected def print_ct_variableAssignment(MclObject mObj){
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
	
//	protected def print_msteps_Property(PropertyDeclaration s)'''
//		«IF s.propertyName != null && s.expression != null»
//			«IF !s.propertyName.argName.equals(PropertyValidator::attr_task_algo.name)»
//				<Property name="«s.propertyName.argName»">
//					«s.expression.print_Assign»
//				</Property>
//			«ENDIF»	
//		«ENDIF»
//	'''
//	
//	protected def print_msteps_Algorithm(PropertyDeclaration s)'''
//		«IF s.propertyName != null && s.expression != null»
//			«IF s.propertyName.argName.equals(PropertyValidator::attr_task_algo.name)»
//				«IF s.expression.vector != null && s.expression.vector.expression != null &&
//					s.expression.vector.expression.expressions != null »
//					«FOR algoName: s.expression.vector.expression.expressions»
//						«IF algoName != null»
//							<Algorithm definition="«algoName.toStr»"/>
//						«ENDIF»
//					«ENDFOR»
//				«ENDIF»
//			«ENDIF»	
//		«ENDIF»
//	'''
}