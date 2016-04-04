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
import org.eclipse.xtext.diagnostics.Diagnostic

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclObjectValidationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidArgs(){
		val mcl = '''bar = mdlObj{
			IDV{T}
			VARIABILITY_LEVELS{
				ID : {type is parameter, level=2 }
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testUnknownBlockArg(){
		val mcl = '''bar = foobar {
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
//		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
//			BlockValidator::UNKNOWN_BLOCK, "block 'VARIABILITY_LEVELS' has unknown object type"
//		)
//		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
//			BlockValidator::UNRECOGNISED_OBJECT_TYPE,
//			"unrecognised object type 'foobar'"
//		)
		mcl.assertError(MdlPackage::eINSTANCE.mclObject, Diagnostic.LINKING_DIAGNOSTIC)
	}


}