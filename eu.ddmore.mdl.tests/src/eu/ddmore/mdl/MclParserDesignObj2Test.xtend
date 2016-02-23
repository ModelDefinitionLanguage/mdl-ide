package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import java.util.Deque
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserDesignObj2Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
d1g=designObj{
	DECLARED_VARIABLES{
		Conc
		Effect
		Cmt
	}
	ADMINISTRATION{
		dose1 : {input=Cmt, amount=100, doseTime=[0], duration=[1]} 
	}
	SAMPLING{
	 	pkwin1 : { type is simple, outcome=Conc, sampleTime = [0.5,2] }
		pkwin2 : { type is simple, outcome=Conc, numberSamples=[0] }
		pkwin3 : { type is simple, outcome=Conc, sampleTime = [30]}
	 	pkwin4 : { type is simple, outcome=Conc, sampleTime = [49]}
		pkwin5 : { type is simple, outcome=Conc, numberSamples=[0]}
		pkwin6 : { type is simple, outcome=Conc, sampleTime = [180]}
		pdwin1 : { type is simple, outcome=Effect, sampleTime = [0.5,2]}
		pdwin2 : { type is simple, outcome=Effect, sampleTime = [14]}
		pdwin3 : { type is simple, outcome=Effect, numberSamples=[0]}
	 	pdwin4 : { type is simple, outcome=Effect, sampleTime = [110]}
		pdwin5 : { type is simple, outcome=Effect, sampleTime = [150]}
		pdwin6 : { type is simple, outcome=Effect,numberSamples=[0] }
	# Create sampling for PK and PD by stringing windows
		sampPK : {  type is derived, combination=[pkwin1,pkwin2,pkwin3,pkwin4,pkwin5,pkwin6] }
		sampPD : { type is derived, combination=[pdwin1,pdwin2,pdwin3,pdwin4,pdwin5,pdwin6] }
	# Create sampling for both responses by combining the PK and PD samples. Need to specify start/end so that the sampling are simultaneous
		sampPKPD : {type is complex, combination=[{sample=sampPK, start=0},{sample=sampPD, start=0}]}
	}
	DESIGN_SPACES{
		DS1 : { objRef=[dose1], element is amount, discrete=[10,100,200] }
		DS2 : { objRef=[dose1], element is duration, range=[0.5, 2] }
		DS3 : { objRef=[pkwin1,pdwin1], element is numberTimes, discrete=[1,2] }
		DS4 : { objRef=[pkwin1,pdwin1], element is sampleTime, range=[0.25,2] }
		DS5 : { objRef=[pkwin2,pdwin2], element is numberTimes, discrete=[0,1]}
		DS6 : { objRef=[pkwin2,pdwin2], element is sampleTime, range=[12,16] }
		DS7 : { objRef=[pkwin3,pdwin3], element is numberTimes, discrete=[1,2] }
		DS8 : { objRef=[pkwin3,pdwin3], element is sampleTime, range=[24,36] }
		DS9 : { objRef=[pkwin4,pdwin4], element is numberTimes, discrete=[1,2] }
		DS10 : { objRef=[pkwin4,pdwin4], element is sampleTime, range=[48,144] }
		DS11 : { objRef=[pkwin5,pdwin5], element is numberTimes, discrete=[0,1]}
		DS12 : { objRef=[pkwin5,pdwin5], element is sampleTime, range=[144,156] }
		DS13 : { objRef=[pkwin6,pdwin6], element is numberTimes, discrete=[0,1]}
		DS14 : { objRef=[pkwin6,pdwin6], element is sampleTime, range=[168,180] }
		DS15 : { objRef=[sampPK,sampPD], element is numberTimes, discrete=dseq(4,8,1) }
	}
	STUDY_DESIGN{
		set totalSize=100
		arm1 : {
			armSize=100,
			interventionSequence=[{
				admin=dose1
			}],
			samplingSequence=[{
				sample=pkwin5,
				start=0
				}
			]
		}
	}
}
		'''
	
	@Test
	def void testParsing(){
		val mcl = CODE_SNIPPET.parse
		mcl.assertNoErrors
//		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject, UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
//			"Objects of type 'desObj' are not currently supported for execution in R."
//		)
	}
	
	@Test
	def void testBlocks(){
		val mcl = CODE_SNIPPET.parse
		val Deque<String> expectedBlks = newLinkedList("DECLARED_VARIABLES", "ADMINISTRATION", "SAMPLING", "DESIGN_SPACES", "STUDY_DESIGN");
		for(blk : mcl.objects.last.blocks){
			Assert::assertEquals(expectedBlks.pop, blk.identifier)
		}
	}

	
}