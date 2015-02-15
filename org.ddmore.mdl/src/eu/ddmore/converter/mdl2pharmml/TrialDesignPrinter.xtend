package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.MOGObject
//import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.mdl.DesignObject
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.AnyExpression

class TrialDesignPrinter extends DataSetPrinter {
	/////////////////////////////////////////////////////////////////////////
	// II Trial Design
	//////////////////////////////////////////////////////////////////////////

	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mathPrinter, resolver);
	}	
	
	protected def print_design_TrialDesign(MOGObject mog){
		/* 
		var objects = Utils::getMOGObjects(mog);
		var dsObj = Utils::getDesignObject(objects);
		'''
		<TrialDesign xmlns="«Constants::xmlns_design»">
			«dsObj.print_design_Structure»
		</TrialDesign>	
		'''*/
	}
	
	protected def print_design_Structure(DesignObject dsObj){
		var activities = "";
		var arms = "";
		for (b: dsObj.blocks){
			if (b.adminBlock != null){
				for (s: b.adminBlock.variables){
					activities = activities + s.print_design_Activity
				}
			}
			if (b.studyDesignBlock != null){
				for (s: b.studyDesignBlock.variables){
					arms = arms + s.print_design_Arm
				}
			}
		}
		'''
		<Strcuture>
			«arms»
			«activities»
		</Structure>
		'''
	}
	
	protected def print_design_Arm(SymbolDeclaration s){
		if (s.symbolName != null){
			if (s.list != null){
				'''
				<Arm oid=«s.symbolName.name»>
				</Arm>
				'''
			}
		}
	}
	
	protected def print_design_Activity(SymbolDeclaration s){
		if (s.symbolName != null){
			if (s.list != null){
				var amount = s.list.arguments.getAttributeExpression(AttributeValidator::attr_amount.name);
				var doseTimes = s.list.arguments.getAttributeExpression(AttributeValidator::attr_doseTime.name);
				var steadyState = isTrue(s.list.arguments.getAttributeExpression(AttributeValidator::attr_steadyState.name)); 
				'''
				<Activity oid=«s.symbolName.name»>
					<Bolus>
						«IF amount != null»
							«amount.print_design_DoseAmount»
						«ENDIF»
						«IF doseTimes != null»
							«doseTimes.print_design_DoseAmount»
						«ENDIF»
						«IF steadyState»
							«s.print_design_SteadyState»
						«ENDIF»			
					</Bolus>
				</Activity>	
				'''		
			}
		}
	}
	
	protected def print_design_SteadyState(SymbolDeclaration s){
		var interval = s.list.arguments.getAttributeExpression(AttributeValidator::attr_interval.name); 
		var start = s.list.arguments.getAttributeExpression(AttributeValidator::attr_start.name); 
		var end = s.list.arguments.getAttributeExpression(AttributeValidator::attr_end.name); 
		'''
		<SteadyState>
			«IF start != null»
				<StartTime>
					«start.print_Assign»
				</StartTime>	
			«ENDIF»
			«IF end != null»
				<EndTime>
					«end.print_Assign»
				</EndTime>	
			«ENDIF»
			«IF interval != null»
				<Interval>
					«interval.print_Assign»
				</Interval>	
			«ENDIF»
		</SteadyState>
		'''
		
	}
	
	protected def print_design_DoseAmount(AnyExpression expr){
		var inputTarget = "parameter";
		'''
			<DoseAmount inputTarget=«inputTarget»>
				«expr.print_Assign»
			</DoseAmonut>
		'''
	}
	
	protected def print_design_DoseTime(AnyExpression expr){
		'''
			<DosingTimes>
				«expr.print_Assign»
			</DosingTimes>
		'''
	}
}