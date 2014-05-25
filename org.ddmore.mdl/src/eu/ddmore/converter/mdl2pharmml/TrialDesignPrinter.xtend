package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.types.UseType
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.ModelObject
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*

class TrialDesignPrinter extends DataSetPrinter {
	private var String mObjName;
	private var String pObjName;
	private var ModelObject mObj;
	private var ParameterObject pObj;
	/////////////////////////////////////////////////////////////////////////
	// II Trial Design
	//////////////////////////////////////////////////////////////////////////

	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mathPrinter, resolver);
	}	
	
	protected def print_design_TrialDesign(String mObjName, String pObjName){
		this.mObjName = mObjName;
		this.pObjName = pObjName;
		this.mObj = getModelObject(mObjName);
		this.pObj = getParamObject(pObjName);
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
	protected def print_design_Structure()
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
	<Epoch oid="«BLK_DESIGN_EPOCH + mObjName»">
		<Start><ct:Real>???</ct:Real></Start>
		<End><ct:Real>???</ct:Real></End>
		<Order>???</Order>
	</Epoch>
	'''

	protected def print_design_Arms()'''
	<Arm oid="«BLK_DESIGN_ARM + mObjName»"/>
	'''
	
	protected def print_design_Cells()'''
	<Cell oid="«BLK_DESIGN_CELL + mObjName»">
		<EpochRef oidRef="???"/>
		<ArmRef oidRef="???"/>
		<SegmentRef oidRef="???"/>
	</Cell>
	'''
	
	protected def print_design_Segments()'''
	<Segment oid="«BLK_DESIGN_SEGMENT + mObjName»">
		<ActivityRef oidRef="???"/>
	</Segment>
	'''
	
	protected def print_design_Activities()'''
	<Activity oid="«BLK_DESIGN_ACTIVITY + mObjName»">
	</Activity>
	'''
	//«print_design_Bolus(mObjName, s)»	

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
		«print_ct_SymbolRef(mObjName, s.symbolName.name)»
		«IF s.expression.expression != null»
			«s.expression.expression.print_Assign»
		«ENDIF»
	</DosingTimes>	
	'''	

	protected def print_design_Assign(SymbolDeclaration s)'''
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

	protected def print_design_SteadyState(SymbolDeclaration s)'''
	<SteadyState>
		<EndTime>
			«print_ct_SymbolRef(s.symbolName.name)»
			«IF s.expression.expression != null»
				«s.expression.expression.print_Assign»
			«ENDIF»
		</EndTime>	
		<Interval>
			«print_ct_SymbolRef(s.symbolName.name)»
			«IF s.expression.expression != null»
				«s.expression.expression.print_Assign»
			«ENDIF»
		</Interval>	
	</SteadyState>
	'''

	///////////////////////////
	// II.b Population
	///////////////////////////
	protected def print_design_Population()
	'''
	<Population>
		«print_design_IndividualTemplate»
	</Population>
	'''
	//Print mapping for the input variables with use=idv (individual)
	protected def print_design_IndividualTemplate(){
		if (mObj != null){
			var mappings = "";
			for (block: mObj.blocks){
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
	protected def print_design_IndividualDosing()'''
	<IndividualDosing>
	</IndividualDosing>
	'''
	
}