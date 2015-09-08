package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import java.util.Deque
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclParserDesignObj1Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_design = desobj {
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
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
	@Test
	def void testBlocks(){
		val mcl = CODE_SNIPPET.parse
		val Deque<String> expectedBlks = newLinkedList("DECLARED_VARIABLES", "ADMINISTRATION", "STUDY_DESIGN");
		for(blk : mcl.objects.last.blocks){
			Assert::assertEquals(expectedBlks.pop, blk.identifier)
		}
	}

	
}