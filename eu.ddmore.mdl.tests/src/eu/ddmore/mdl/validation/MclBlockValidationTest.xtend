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
		val mcl = '''foo = mdlObj {
			DATA_INPUT_VARIABLES{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			MdlValidator::UNKNOWN_BLOCK,
			"block 'DATA_INPUT_VARIABLES' cannot be used in an object of type mdlObj"
		)
	}

	@Test
	def void testMissingMandatoryBlocks(){
		val mcl = '''foo = mdlObj {
			DATA_INPUT_VARIABLES{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MANDATORY_BLOCK_MISSING,
			"mandatory block 'VARIABILITY_LEVELS' is missing in mdlObj 'foo'"
		)
	}

	@Test
	def void testUnknownSubBlock(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}
			MODEL_PREDICTION{
				MODEL_PREDICTION{}
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			MdlValidator::WRONG_SUBBLOCK,
			"block 'MODEL_PREDICTION' cannot be used as a sub-block"
		)
	}

	@Test
	def void testWrongParentBlock(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
				DEQ{}
			}
			MODEL_PREDICTION{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			MdlValidator::WRONG_PARENT_BLOCK,
			"sub-block 'DEQ' cannot be used in the 'VARIABILITY_LEVELS' block"
		)
	}

	@Test
	def void testSubBlockOk(){
		val mcl = '''foo = mdlObj {
			IDV{  T }
			VARIABILITY_LEVELS{
			}
			MODEL_PREDICTION{
				DEQ{}
				COMPARTMENT{}
			}
		}'''.parse
		mcl.assertNoErrors
//		mcl.assertNoErrors(MdlValidator::WRONG_PARENT_BLOCK)
//		mcl.assertNoErrors(MdlValidator::WRONG_SUBBLOCK)
	}



}