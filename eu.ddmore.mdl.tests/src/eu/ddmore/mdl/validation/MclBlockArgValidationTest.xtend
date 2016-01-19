package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclBlockArgValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Ignore
	def void testValidArgs(){
		val mcl = '''bar = mdlObj ( idv T ){
			VARIABILITY_LEVELS{
				ID : {type is parameter, level=2 }
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidTaskBlockProps(){
		val mcl = '''bar = taskObj{
			SIMULATE{
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Ignore // not supported now
	def void testUnknownBlockArg(){
		val mcl = '''bar = mdlObj ( foo T ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.argumentDefinition,
			BlockValidator::UNKNOWN_BLOCK_ARG_DECL,
			"unrecognised variable declaration type 'foo'"
		)
//		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
//			MdlValidator::MANDATORY_BLOCK_ARG_MISSING,
//			"mandatory argument 'idv' is missing in mdlObj 'bar'"
//		)
	}

	@Ignore
	// not supported now
	def void testUnknownBlockArgWithOptionIdv(){
		val mcl = '''bar = mdlObj ( foo T ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.argumentDefinition,
			BlockValidator::UNKNOWN_BLOCK_ARG_DECL,
			"unrecognised variable declaration type 'foo'"
		)
		mcl.assertNoErrors(MdlPackage::eINSTANCE.mclObject,
			BlockValidator::MANDATORY_BLOCK_ARG_MISSING,
			"mandatory argument 'idv' is missing in mdlObj 'bar'"
		)
	}

	@Ignore
	def void tesNoBlockArg(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			BlockValidator::MANDATORY_BLOCK_ARG_MISSING,
			"mandatory argument 'idv' is missing in mdlObj 'foo'"
		)
	}

	@Ignore // not supported now
	def void testUnknownBlockArgProp(){
		val mcl = '''bar = mdlObj ( idv T, prop = "value" ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			BlockValidator::UNKNOWN_BLOCK_ARG_PROP//,
//			"unrecognised property 'prop'"
		)
	}

	@Ignore
	def void testUnknownBlockArgPropAndMandBlockArg(){
		val mcl = '''bar = mdlObj ( foo T, prop = "value" ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			BlockValidator::UNKNOWN_BLOCK_ARG_PROP,
			"unrecognised property 'prop'"
		)
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			BlockValidator::MANDATORY_BLOCK_ARG_MISSING,
			"mandatory argument 'idv' is missing in mdlObj 'bar'"
		)
		mcl.assertError(MdlPackage::eINSTANCE.argumentDefinition,
			BlockValidator::UNKNOWN_BLOCK_ARG_DECL,
			"unrecognised variable declaration type 'foo'"
		)
	}

	@Ignore // not supported now
	def void testUnknownBlockArgPropAndMandBlockArgOptionalIdv(){
		val mcl = '''bar = mdlObj ( foo T, prop = "value" ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			BlockValidator::UNKNOWN_BLOCK_ARG_PROP,
			"unrecognised property 'prop'"
		)
		mcl.assertNoErrors(MdlPackage::eINSTANCE.mclObject,
			BlockValidator::MANDATORY_BLOCK_ARG_MISSING)
		mcl.assertError(MdlPackage::eINSTANCE.argumentDefinition,
			BlockValidator::UNKNOWN_BLOCK_ARG_DECL,
			"unrecognised variable declaration type 'foo'"
		)
	}

	@Ignore // not supported now
	def void testUnusedBlockArgProps(){
		val mcl = '''bar = mdlObj ( idv T ){
			VARIABILITY_LEVELS{
			}
			RANDOM_VARIABLE_DEFINITION{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			BlockValidator::MANDATORY_BLOCK_PROP_MISSING,
			"mandatory property 'level' is missing in block 'RANDOM_VARIABLE_DEFINITION'"
		)
	}

	@Test
	def void testUnusedBlockArgWithUnkPropProps(){
		val mcl = '''bar = mdlObj ( idv T ){
			VARIABILITY_LEVELS{
			}
			RANDOM_VARIABLE_DEFINITION(prop = 3){
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			BlockValidator::MANDATORY_BLOCK_PROP_MISSING,
			"mandatory property 'level' is missing in block 'RANDOM_VARIABLE_DEFINITION'"
		)
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			BlockValidator::UNKNOWN_BLOCK_ARG_PROP,
			"unrecognised property 'prop'"
		)
	}

}