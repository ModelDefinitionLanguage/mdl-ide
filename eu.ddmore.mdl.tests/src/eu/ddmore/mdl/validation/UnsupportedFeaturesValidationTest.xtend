package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.mdl.MdlPackage

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class UnsupportedFeaturesValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	


	@Test
	def void testDesignObjectUnsupported(){
		val mcl = '''
warfarin_design = desObj {
	DECLARED_VARIABLES{
		bsv_lvl; GUT; oral1
	}
	
	# trial design
	# arm 1 is placebo
	# arm 2 is 2.5 mg dosing time given, then interdose (II) period, the number of doses (addl)
	# arm 3 is 5 mg
	# arm 4 is 10 mg

	# wgt covariate is simulating

	ADMINISTRATION{
		# again there is some repetition here so allowing a variable defn here would be useful
		doseStart = 0
		doseEnd = 24
		doseGap = 2
		baseAmt = 2.5

		# could use an adm type instead so do
		dreg1 : { adm=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg2 : { adm=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg3 : { adm=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg4 : { adm=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
	}

	STUDY_DESIGN{
		WT_MEAN = 85.5
		WT_VAR = 19
	    WT ~ Normal(mean=WT_MEAN, var=WT_VAR)
		# it would be helpful if I could put in some vars here to help make defn of similar vars easier.
		epochStart = 0
		epochEnd = 24
		varlevel = bsv_lvl
		arm1 : {
		     	armSize = 10,
		     	interventionSequence = [
		       	{
		       		interventionList = [dreg1],
		       		start = [epochStart],
		       		end = [epochEnd]
		      	}
		     ]
		}
		arm2 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
		     		interventionList = [dreg2],
		       		start = [epochStart],
		       		end = [epochEnd]}
		     ]
		}
		arm3 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
			       interventionList = [dreg3],
			       start = [epochStart],
			       end = [epochEnd]
			     }
		     ]
		}
		arm4 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
			       interventionList = [dreg4],
			       start = [epochStart],
			       end = [epochEnd]
			     }
		     ]
		}
	}
}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject, UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Objects of type 'desObj' are not currently supported for execution in R."
		)
	}

	@Test
	def void testWarningWhenWrtDifferent(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
				X : { deriv = 1, wrt = Y }
			}
		}'''.parse
		
		mcl.assertWarning(MdlPackage::eINSTANCE.valuePair,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Derivative variables with an independent variable different to the model's IDV cannot be executed in R."
		)
	}

	@Test
	def void testWarningWhenNonZerox0(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
				X : { deriv = 1, x0 = 1.0 }
			}
		}'''.parse
		
		mcl.assertWarning(MdlPackage::eINSTANCE.valuePair,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Derivative variables with a non-zero initial time cannot be executed in R."
		)
	}

	@Test
	def void testNoWarningWhenZerox0(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
				X : { deriv = 1, x0 = 0.0 }
			}
		}'''.parse
		
		mcl.assertNoIssues
	}

	@Test
	def void testWarningWhenElseFollowedByIf(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
				X = if(Y < 0) then -1 else if(Y > 0) then 1 else 0
			}
		}'''.parse
		
		mcl.assertWarning(MdlPackage::eINSTANCE.elseClause,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Nested conditional expression cannot be executed in R. Consider changing to 'elseif'."
		)
	}

}