package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclBlockValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testUnknownBlock(){
		val mcl = '''foo = mdlobj {
			DATA_INPUT_VARIABLES{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::UNKNOWN_BLOCK,
			"unrecognised block in mdlobj 'foo'"
		)
	}

	@Test
	def void testMissingMandatoryBlocks(){
		val mcl = '''foo = mdlobj {
			DATA_INPUT_VARIABLES{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MANDATORY_BLOCK_MISSING,
			"mandatory block 'VARIABILITY_LEVELS' is missing in mdlobj 'foo'"
		)
	}


}