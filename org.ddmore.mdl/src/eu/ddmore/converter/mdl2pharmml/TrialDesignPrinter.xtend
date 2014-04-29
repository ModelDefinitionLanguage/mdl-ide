package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.SymbolModification
import org.ddmore.mdl.mdl.Mcl
import java.util.ArrayList
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.types.UseType

class TrialDesignPrinter extends DataSetPrinter {
	/////////////////////////////////////////////////////////////////////////
	// II Trial Design
	//////////////////////////////////////////////////////////////////////////

	new(Mcl mcl, MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mcl, mathPrinter, resolver);
	}	
	
	def print_design_TrialDesign()'''
	<TrialDesign>
		«print_design_Structure»
		«print_design_Population»
		«print_design_IndividualDosing»
	</TrialDesign>	
	'''
	
	///////////////////////////
	// II.a Structure
	///////////////////////////
	def print_design_Structure()
	'''
	<Structure>
		«print_design_Epoch()»
		«print_design_Arms»
		«print_design_Cells»
		«print_design_Segments»
		«print_design_Activities»
	</Structure>
	'''
	
	def print_design_Epoch(){
		return print_design_Epoch("", "", "", "");
	}
	
	def print_design_Epoch(String name, String start, String end, String order)
	'''
	<Epoch oid="«name»">
		«start.print_design_Start»
		«start.print_design_End»
		<Order>«order»</Order>
	</Epoch>
	'''

	def print_design_Start(String value)'''
	<Start>
		<ct:Real«value»></ct:Real>
	</Start>
	'''

	def print_design_End(String value)'''
	<End>
		<ct:Real«value»></ct:Real>
	</End>
	'''
	
	//TODO
	def print_design_Arms(){
		print_design_Arm("");
	}
		
	def print_design_Arm(String name)'''
	<Arm oid=«name»/>
	'''
	
	//TODO
	def print_design_Cells(){
		print_design_Cell("", "", "", "")
	}
	
	def print_design_Cell(String name, String epochRef, String armRef, String segmentRef)'''
	<Cell oid="«name»">
		<EpochRef oidRef="«epochRef»"/>
		<ArmRef oidRef="«armRef»"/>
		<SegmentRef oidRef="«segmentRef»"/>
	</Cell>
	'''
	
	//TODO
	def print_design_Segments(){
		print_design_Segment("", "");
	}
	
	def print_design_Segment(String name, String activityRef)'''
	<Segment oid="«name»">
		«IF !activityRef.equals("")»
			<ActivityRef oidRef="«activityRef»"/>
		«ENDIF»
	</Segment>
	'''
	
	//TODO
	def print_design_Activities(){
		print_design_Activity("");
	}
	
	def print_design_Activity(String name)'''
	<Activity oid="«name»">
		«print_design_Bolus»
	</Activity>
	'''

	//TODO - define structure
	def print_design_Bolus()'''
	<Bolus>
		«print_design_DoseAmount»
		«print_design_DosingTimes(null)»
		«print_design_SteadyState(null, null)»
	</Bolus>
	'''

	def print_design_DoseAmount()'''
	<DoseAmount inputType="target">
		<ct:SymbRef symbIdRef="" blkIdRef=""/>
	</DoseAmount>
	'''
	
	def print_design_DosingTimes(SymbolDeclaration s)'''
	<DosingTimes>
		«s.print_design_Assign("")»
	</DosingTimes>	
	'''
	
	def print_design_Assign(SymbolDeclaration s, String blkIdRef)'''
	«IF s != null»
		<ct:SymbRef symbIdRef="«s.symbolName.name»«IF blkIdRef.length > 0» blkIdRef="«blkIdRef»"«ENDIF»"/>
		«IF s.expression.expression != null»
			«print_Assign(s.expression.expression)»
		«ENDIF»
	«ENDIF»
	'''

	def print_design_Assign(SymbolModification s)'''
	«IF s != null»
		«print_ct_SymbolRef(s.symbolName)»
		«val value = getAttributeExpression(s.list.arguments, AttributeValidator::attr_value.name)»
		«IF value != null»
			«IF value.expression != null»
				«print_Assign(value.expression)»
			«ENDIF»
		«ENDIF»
	«ENDIF»
	'''

	def print_design_SteadyState(SymbolDeclaration endTime, SymbolDeclaration interval)'''
	<SteadyState>
		«endTime.print_design_EndTime»
		«interval.print_design_Interval»
	</SteadyState>
	'''

	def print_design_EndTime(SymbolDeclaration s)'''
	<EndTime>
		«s.print_design_Assign("")»
	</EndTime>	
	'''

	def print_design_Interval(SymbolDeclaration s)'''
	<Interval>
		«s.print_design_Assign("")»
	</Interval>	
	'''

	///////////////////////////
	// II.b Population
	///////////////////////////
	def print_design_Population()
		//«print_VariabilityReference(?)»
	'''
	<Population>
		«print_design_IndividualTemplate»
		«print_design_DataSet»
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
							if (s.list != null){
								var use = getAttribute(s.list.arguments, AttributeValidator::attr_use.name);
								if (use.length > 0){
									if (use.equals(UseType::USE_ID)) 
										mappings = mappings + "IndividualMapping".print_design_Mapping(s.symbolName.symbol.name);
									if (use.equals(UseType::USE_AMT))	
										mappings = mappings + "ArmMapping".print_design_Mapping(s.symbolName.symbol.name);
									//...	
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

	//
	def print_design_DataSet(){
		var names = newArrayList;
		var types = newArrayList;
		var values = new ArrayList<String[]>();
		print_DataSet(names, types, values);
	}
	

	///////////////////////////
	// II.c Individual Dosing
	///////////////////////////
	def print_design_IndividualDosing()
	'''
	<IndividualDosing>
	</IndividualDosing>
	'''
	
}