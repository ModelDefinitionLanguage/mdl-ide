package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclBlockValidationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testUnknownBlock(){
		val mcl = '''foo = mdlObj {
			DATA_INPUT_VARIABLES{  foo : { use is ignore } }
			
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			BlockValidator::UNKNOWN_BLOCK,
			"block 'DATA_INPUT_VARIABLES' cannot be used in an object of type mdlObj"
		)
	}

	@Test
	def void testMissingMandatoryBlocks(){
		val mcl = '''foo = mdlObj {
			`MODEL_PREDICTION {
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			BlockValidator::MANDATORY_BLOCK_MISSING,
			"mandatory block 'IDV' is missing in mdlObj 'foo'"
		)
	}

	@Test
	def void testFewerMinStatement(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}
			
			IDV{}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatementBody,
			BlockValidator::BLOCK_INCORRECT_STATEMENT_COUNT,
			"block 'IDV' has fewer statements than the 1 expected"
		)
	}

	@Test
	def void testExceedMaxStatements(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}
			
			IDV{ T; D}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatementBody,
			BlockValidator::BLOCK_INCORRECT_STATEMENT_COUNT,
			"block 'IDV' has more statements than the 1 expected"
		)
	}

	@Test
	def void testInvalidStatementTypeWithEqnRhs(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}
			
			IDV{ T = 2}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equationDefinition,
			BlockValidator::BLOCK_INVALID_STATEMENT_TYPE,
			"block 'IDV' does not permit statements of this type"
		)
	}

	@Test
	def void testInvalidStatementTypeWithExpectedList(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				T withCategories {foo, bar}
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.enumerationDefinition,
			BlockValidator::BLOCK_INVALID_STATEMENT_TYPE,
			"block 'INDIVIDUAL_VARIABLES' does not permit statements of this type"
		)
	}

	@Test
	def void testExceedMaxBlocks(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}
			
			IDV{}
			
			IDV{}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			BlockValidator::BLOCK_COUNT_EXCEEDED,
			"block 'IDV' is used more than is allowed. A maximum of 1 blocks are allowed"
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
			BlockValidator::WRONG_SUBBLOCK,
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
			BlockValidator::WRONG_PARENT_BLOCK,
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
	}

	@Test
	def void testWrongBlockBodyType(){
		val mcl = '''foo = mdlObj {
			IDV{  T }
			VARIABILITY_LEVELS{
			}
			MODEL_PREDICTION<<
				fooBar
			>>
		}'''.parse
		mcl.assertError(MdlPackage::eINSTANCE.blockTextBody,
			BlockValidator::BLOCK_WRONG_BODY_TYPE,
			"block '" + "MODEL_PREDICTION" + "' cannot define a verbatim text block. It must contains statements delimitted by '{' '}'"
		)
	}



}