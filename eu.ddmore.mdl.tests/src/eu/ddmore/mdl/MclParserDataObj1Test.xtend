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
import eu.ddmore.mdl.utils.BlockUtils

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserDataObj1Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper

	extension BlockUtils bu = new BlockUtils

	
	val static CODE_SNIPPET = '''
	warfarin_PK_ODE_dat = dataObj {
	DECLARED_VARIABLES{GUT Y}

	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		WT : { use is covariate }
		AMT : { use is amt, variable = GUT }
		OCC : { use is varLevel }
	} # end DATA_INPUT_VARIABLES
	SOURCE {
	    foo : {file = "warfarin_conc.csv", 
        		inputFormat  is nonmemFormat } 
	} # end SOURCE
} # end data object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Test
	def void testBlocks(){
		val mcl = CODE_SNIPPET.parse
		val Deque<String> expectedBlks = newLinkedList("DECLARED_VARIABLES", "DATA_INPUT_VARIABLES", "SOURCE");
		for(blk : mcl.objects.last.blocks){
			Assert::assertEquals(expectedBlks.pop, blk.identifier)
		}
	}

}