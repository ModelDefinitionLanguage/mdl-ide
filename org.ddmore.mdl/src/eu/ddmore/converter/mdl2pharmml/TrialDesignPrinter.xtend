package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.types.UseType
import org.ddmore.mdl.mdl.AnyExpression

class TrialDesignPrinter extends DataSetPrinter {
	/////////////////////////////////////////////////////////////////////////
	// II Trial Design
	//////////////////////////////////////////////////////////////////////////

	new(Mcl mcl, MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mcl, mathPrinter, resolver);
	}	
	
	def print_design_TrialDesign(String objName)'''
	<TrialDesign xmlns="«xmlns_design»">
		«objName.print_design_Structure»
		«print_design_Population»
		«print_design_IndividualDosing»
	</TrialDesign>	
	'''
	
	///////////////////////////
	// II.a Structure
	///////////////////////////
	def print_design_Structure(String objName)
	'''
	<Structure>
		«objName.print_design_Epochs»
		«objName.print_design_Arms»
		«objName.print_design_Cells»
		«objName.print_design_Segments»
		«objName.print_design_Activities»
	</Structure>
	'''
	
	def print_design_Epochs(String objName)
	'''
	<Epoch oid="«BLK_DESIGN_EPOCH + objName»">
		<Start><ct:Real>???</ct:Real></Start>
		<End><ct:Real>???</ct:Real></End>
		<Order>???</Order>
	</Epoch>
	'''

	def print_design_Arms(String objName)'''
	<Arm oid="«BLK_DESIGN_ARM + objName»"/>
	'''
	
	def print_design_Cells(String objName)'''
	<Cell oid="«BLK_DESIGN_CELL + objName»">
		<EpochRef oidRef="???"/>
		<ArmRef oidRef="???"/>
		<SegmentRef oidRef="???"/>
	</Cell>
	'''
	
	def print_design_Segments(String objName)'''
	<Segment oid="«BLK_DESIGN_SEGMENT + objName»">
		<ActivityRef oidRef="???"/>
	</Segment>
	'''
	
	def print_design_Activities(String objName)'''
	<Activity oid="«BLK_DESIGN_ACTIVITY + objName»">
	</Activity>
	'''
	//«print_design_Bolus(objName, s)»
	

	def print_design_Bolus(String objName, SymbolDeclaration s)'''
	<Bolus>
		«print_design_DoseAmount(objName, "target", s)»
		«print_design_DosingTimes(objName, s)»
		«print_design_SteadyState(objName, s)»
	</Bolus>
	'''

	def print_design_DoseAmount(String objName, String target, SymbolDeclaration s)'''
	<DoseAmount inputType="«target»">
	</DoseAmount>
	'''
	
	def print_design_DosingTimes(String objName, SymbolDeclaration s)'''
	<DosingTimes>
		«print_ct_SymbolRef(objName, s.symbolName.name)»
		«IF s.expression.expression != null»
			«s.expression.expression.print_Assign»
		«ENDIF»
	</DosingTimes>	
	'''	

	def print_design_Assign(SymbolDeclaration s)'''
		«print_ct_SymbolRef(s.symbolName)»
		«IF s.expression != null»
			«var AnyExpression value = null»
			«IF s.expression.list != null»
				«value = getAttributeExpression(s.expression.list.arguments, AttributeValidator::attr_value.name)»
			«ELSE»
				«IF s.expression.expression != null»
					«value = s.expression»
				«ENDIF»
			«ENDIF»
			«IF value != null»
				«IF value.expression != null»
					«value.expression.print_Assign»
				«ENDIF»
			«ENDIF»
		«ENDIF»
	'''

	def print_design_SteadyState(String objName, SymbolDeclaration s)'''
	<SteadyState>
		<EndTime>
			«print_ct_SymbolRef(objName, s.symbolName.name)»
			«IF s.expression.expression != null»
				«s.expression.expression.print_Assign»
			«ENDIF»
		</EndTime>	
		<Interval>
			«print_ct_SymbolRef(objName, s.symbolName.name)»
			«IF s.expression.expression != null»
				«s.expression.expression.print_Assign»
			«ENDIF»
		</Interval>	
	</SteadyState>
	'''

	///////////////////////////
	// II.b Population
	///////////////////////////
	def print_design_Population()
	'''
	<Population>
		«print_design_IndividualTemplate»
	</Population>
	'''
	//Print mapping for the input variables with use=idv (individual)
	def print_design_IndividualTemplate(){
		var mappings = "";
		for (obj: mcl.objects){
			if (obj.modelObject != null){
				for (block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = getAttribute(s.expression.list.arguments, AttributeValidator::attr_use.name);
									if (use.length > 0){
										if (use.equals(UseType::USE_ID)) 
											mappings = mappings + "IndividualMapping".print_design_Mapping(s.symbolName.name);
										if (use.equals(UseType::USE_AMT))	
											mappings = mappings + "ArmMapping".print_design_Mapping(s.symbolName.name);
										//...	
	                				}
								}
							}
						}
					}	
				}
			}
		}
		return
		'''
		<IndividualTemplate>
			«mappings»
		</IndividualTemplate>
		'''
	}	

	//
	def print_design_Mapping(String mappingType, String ref)'''
	<«mappingType»>
		<ColumnRef columnIdRef="«ref»"/>
	</«mappingType»>
	'''

	///////////////////////////
	// II.c Individual Dosing
	///////////////////////////
	def print_design_IndividualDosing()'''
	<IndividualDosing>
	</IndividualDosing>
	'''
	
}