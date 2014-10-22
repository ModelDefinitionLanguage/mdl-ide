package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.MOGObject

class TrialDesignPrinter extends DataSetPrinter {
	/////////////////////////////////////////////////////////////////////////
	// II Trial Design
	//////////////////////////////////////////////////////////////////////////

	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mathPrinter, resolver);
	}	
	
	protected def print_design_TrialDesign(MOGObject mog){

		return "";
		/*
		'''
		<TrialDesign xmlns="«xmlns_design»">
			«print_design_Structure»
			«print_design_Population»
			«print_design_IndividualDosing»
		</TrialDesign>	
		'''
	 	*/
	}
	
	///////////////////////////
	// II.a Structure
	///////////////////////////
	/*protected def print_design_Structure()
	'''
	<Structure>
		«print_design_Epochs»
		«print_design_Arms»
		«print_design_Cells»
		«print_design_Segments»
		«print_design_Activities»
	</Structure>
	'''
	
	protected def print_design_Epochs()
	'''
	<Epoch oid="«BLK_DESIGN_EPOCH»">
		<Start><ct:Real>???</ct:Real></Start>
		<End><ct:Real>???</ct:Real></End>
		<Order>???</Order>
	</Epoch>
	'''

	protected def print_design_Arms()'''
	<Arm oid="«BLK_DESIGN_ARM»"/>
	'''
	
	protected def print_design_Cells()'''
	<Cell oid="«BLK_DESIGN_CELL»">
		<EpochRef oidRef="???"/>
		<ArmRef oidRef="???"/>
		<SegmentRef oidRef="???"/>
	</Cell>
	'''
	
	protected def print_design_Segments()'''
	<Segment oid="«BLK_DESIGN_SEGMENT»">
		<ActivityRef oidRef="???"/>
	</Segment>
	'''
	
	protected def print_design_Activities()'''
	<Activity oid="«BLK_DESIGN_ACTIVITY»">
	</Activity>
	'''
	//«s.print_design_Bolus»	

	protected def print_design_Bolus(SymbolDeclaration s)'''
	<Bolus>
		«print_design_DoseAmount("target", s)»
		«print_design_DosingTimes(s)»
		«print_design_SteadyState(s)»
	</Bolus>
	'''

	protected def print_design_DoseAmount(String target, SymbolDeclaration s)'''
	<DoseAmount inputType="«target»">
	</DoseAmount>
	'''
	
	protected def print_design_DosingTimes(SymbolDeclaration s)'''
	<DosingTimes>
		«IF s.symbolName != null»
			«print_ct_SymbolRef(s.symbolName.name)»
			«IF s.expression != null»
				«s.expression.print_Assign»
			«ENDIF»
		«ENDIF»
	</DosingTimes>	
	'''	

	protected def print_design_SteadyState(SymbolDeclaration s)'''
	«IF s.symbolName != null»
		<SteadyState>
			<EndTime>
				«print_ct_SymbolRef(s.symbolName.name)»
				«IF s.expression != null»
					«s.expression.print_Assign»
				«ENDIF»
			</EndTime>	
			<Interval>
				«print_ct_SymbolRef(s.symbolName.name)»
				«IF s.expression != null»
					«s.expression.print_Assign»
				«ENDIF»
			</Interval>	
		</SteadyState>
	«ENDIF»
	'''
	*/

	///////////////////////////
	// II.b Population
	///////////////////////////
	protected def print_design_Population(MclObject mObj)
	'''
	<Population>
		«mObj.print_design_IndividualTemplate»
	</Population>
	'''
	//Print mapping for the input variables with use=idv (individual)
	protected def print_design_IndividualTemplate(MclObject mObj){
		if (mObj.modelObject != null){
			var mappings = "";
			for (block: mObj.modelObject.blocks){
				if (block.inputVariablesBlock != null){
					for (s: block.inputVariablesBlock.variables){
						if (s.list != null && s.symbolName != null){
							var use = s.list.arguments.getAttribute(AttributeValidator::attr_use.name);
							if (use.length > 0){
								if (use.equals(UseType::ID)) 
									mappings = mappings + "IndividualMapping".print_design_Mapping(s.symbolName.name);
								if (use.equals(UseType::AMT))	
									mappings = mappings + "ArmMapping".print_design_Mapping(s.symbolName.name);
								//...	
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
		return ''''''
	}	

	//
	protected def print_design_Mapping(String mappingType, String ref)'''
	<«mappingType»>
		<ColumnRef columnIdRef="«ref»"/>
	</«mappingType»>
	'''

	///////////////////////////
	// II.c Individual Dosing
	///////////////////////////
	/* 
	protected def print_design_IndividualDosing()'''
	<IndividualDosing>
	</IndividualDosing>
	'''
	*/
	
}