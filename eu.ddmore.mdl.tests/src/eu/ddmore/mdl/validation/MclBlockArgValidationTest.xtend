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
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclBlockArgValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidArgs(){
		val mcl = '''bar = mdlobj ( idv T ){
			VARIABILITY_LEVELS{
				ID : {type is parameter, level=2 }
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Ignore
	def void testUnknownBlockArg(){
		val mcl = '''bar = mdlobj ( foo T ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.forwardDeclaration,
			MdlValidator::UNKNOWN_BLOCK_ARG_DECL,
			"unrecognised variable declaration type 'foo'"
		)
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MANDATORY_BLOCK_ARG_MISSING,
			"mandatory argument 'idv' is missing in mdlobj 'bar'"
		)
	}

	@Test
	def void testUnknownBlockArgWithOptionIdv(){
		val mcl = '''bar = mdlobj ( foo T ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.forwardDeclaration,
			MdlValidator::UNKNOWN_BLOCK_ARG_DECL,
			"unrecognised variable declaration type 'foo'"
		)
		mcl.assertNoErrors(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MANDATORY_BLOCK_ARG_MISSING,
			"mandatory argument 'idv' is missing in mdlobj 'bar'"
		)
	}

	@Ignore
	def void tesNoBlockArg(){
		val mcl = '''foo = mdlobj {
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MANDATORY_BLOCK_ARG_MISSING,
			"mandatory argument 'idv' is missing in mdlobj 'foo'"
		)
	}

	@Test
	def void testUnknownBlockArgProp(){
		val mcl = '''bar = mdlobj ( idv T, prop = "value" ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNKNOWN_BLOCK_ARG_PROP//,
//			"unrecognised property 'prop'"
		)
	}

	@Ignore
	def void testUnknownBlockArgPropAndMandBlockArg(){
		val mcl = '''bar = mdlobj ( foo T, prop = "value" ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNKNOWN_BLOCK_ARG_PROP,
			"unrecognised property 'prop'"
		)
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MANDATORY_BLOCK_ARG_MISSING,
			"mandatory argument 'idv' is missing in mdlobj 'bar'"
		)
		mcl.assertError(MdlPackage::eINSTANCE.forwardDeclaration,
			MdlValidator::UNKNOWN_BLOCK_ARG_DECL,
			"unrecognised variable declaration type 'foo'"
		)
	}

	@Test
	def void testUnknownBlockArgPropAndMandBlockArgOptionalIdv(){
		val mcl = '''bar = mdlobj ( foo T, prop = "value" ){
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNKNOWN_BLOCK_ARG_PROP,
			"unrecognised property 'prop'"
		)
		mcl.assertNoErrors(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MANDATORY_BLOCK_ARG_MISSING)
		mcl.assertError(MdlPackage::eINSTANCE.forwardDeclaration,
			MdlValidator::UNKNOWN_BLOCK_ARG_DECL,
			"unrecognised variable declaration type 'foo'"
		)
	}

	@Test
	def void testUnusedBlockArgProps(){
		val mcl = '''bar = mdlobj ( idv T ){
			VARIABILITY_LEVELS{
			}
			RANDOM_VARIABLE_DEFINITION{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			MdlValidator::MANDATORY_BLOCK_PROP_MISSING,
			"mandatory property 'level' is missing in block 'RANDOM_VARIABLE_DEFINITION'"
		)
	}

	@Test
	def void testUnusedBlockArgWithUnkPropProps(){
		val mcl = '''bar = mdlobj ( idv T ){
			VARIABILITY_LEVELS{
			}
			RANDOM_VARIABLE_DEFINITION(prop = 3){
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			MdlValidator::MANDATORY_BLOCK_PROP_MISSING,
			"mandatory property 'level' is missing in block 'RANDOM_VARIABLE_DEFINITION'"
		)
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNKNOWN_BLOCK_ARG_PROP,
			"unrecognised property 'prop'"
		)
	}

}