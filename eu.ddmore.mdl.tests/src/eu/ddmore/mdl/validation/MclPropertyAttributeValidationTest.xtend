package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.MdlAndLibInjectorProvider

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclPropertyAttributeValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidAttributes(){
		val mcl = '''bar = taskObj {
			ESTIMATE{
				set algo is saem
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testMissingMandatoryAttribute(){
		val mcl = '''bar = taskObj {
			ESTIMATE{

			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
			MdlValidator::MANDATORY_PROP_MISSING,
			"mandatory property 'algo' is missing in block"
		)
	}

	@Test
	def void testUnrecognizedAttribute(){
		val mcl = '''bar = taskObj {
			ESTIMATE{
				set target is nonmem, version = "7.3", solver = 2
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_PROPERTY_ATT,
			"property 'solver' is not recognised in this context"
		)
	}

	@Test
	def void testUnrecognizedAttributeValue(){
		val mcl = '''bar = taskObj {
			ESTIMATE{
				set target is monolix, algo is foo
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"property 'algo' expected value of type 'Enum:estAlgo' but was 'Undefined'"
		)
	}

	@Test
	def void testInValidPropertyValue(){
		val mcl = '''bar = taskObj {
			ESTIMATE{
				set target is monolix, version = "7.3", algo=1
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"property 'algo' expected value of type 'Enum:estAlgo' but was 'Int'"
		)
	}


}