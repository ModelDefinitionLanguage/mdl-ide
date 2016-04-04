package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserDesignObj1Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_design = designObj {
	DECLARED_VARIABLES{
		bsv_lvl; GUT; oral1
	}
	
	# trial design
	# arm 1 is placebo
	# arm 2 is 2.5 mg dosing time given, then interdose (II) period, the number of doses (addl)
	# arm 3 is 5 mg
	# arm 4 is 10 mg

	# wgt covariate is simulating

	DESIGN_PARAMETERS{
		# again there is some repetition here so allowing a variable defn here would be useful
		doseStart = 0
		doseEnd = 24
		doseGap = 2
		baseAmt = 2.5
		WT_MEAN = 85.5
		WT_VAR = 19
		epochStart = 0
		epochEnd = 24
«««		varlevel = bsv_lvl
	}

	COVARIATES{
	    WT ~ Normal(mean=WT_MEAN, var=WT_VAR)
	}


	ADMINISTRATION{

		# could use an adm type instead so do
		dreg1 : { type is simple, input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg2 : { type is simple, input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg3 : { type is simple, input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg4 : { type is simple, input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
	}

	STUDY_DESIGN{
		arm1 : {
		     	armSize = 10,
		     	interventionSequence = {
		       		admin = dreg1,
		       		start = [epochStart, epochEnd]
		      	}
		}
		arm2 : {
		     armSize = 20,
		     interventionSequence = {
		     		admin = dreg2,
		       		start = [epochStart, epochEnd]
		     }
		}
		arm3 : {
		     armSize = 20,
		     interventionSequence = {
			       admin = dreg3,
			       start = [epochStart, epochEnd]
			 }
		}
		arm4 : {
		     armSize = 20,
		     interventionSequence = {
			       admin = dreg4,
			       start = [epochStart, epochEnd]
			 }
		}
	}
}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
	
}
