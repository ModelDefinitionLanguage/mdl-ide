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
class DataFileValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidDataFile(){
//		val testFile = this.class.getResource("count.csv").file
		val testFile = "src/eu/ddmore/mdl/validation/count.csv"
//		System.err.println("Test path = " + testFile)
		val mcl = '''bar = dataObj {
			
			
			DATA_INPUT_VARIABLES{
				ID : { use is id }
			}
			
			SOURCE{
				srcFile : { file="«testFile»", inputFormat is nonmemFormat }
			}
			
		}'''.parse
		
		mcl.assertNoIssues
	}

	@Test
	def void testValidWithWarningMissingDataFile(){
		val mcl = '''bar = dataObj {
			
			
			DATA_INPUT_VARIABLES{
				ID : { use is id }
			}
			
			SOURCE{
				srcFile : { file="nofile", inputFormat is nonmemFormat }
			}
			
		}'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage.eINSTANCE.assignPair, DataFileValidation::DATA_FILE_NOT_FOUND,
					"Cannot find data file: path may be incorrect."
					)
	}


}