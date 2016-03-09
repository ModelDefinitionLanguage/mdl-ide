package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import java.io.File
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.TemporaryFolder
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class DataFileValidationTest {
    
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
//	@Inject
//    private Provider<XtextResourceSet> resourceSetProvider;
    
    @Rule
    public TemporaryFolder workingFolder = new TemporaryFolder();
    
    private File dataFile;
	
	@Before
	def void setUp() {
	    // Have to copy the data file somewhere because when the
	    // tests are run via Maven, they are run against a
	    // built JAR file, so the file doesn't physically exist
        dataFile = new File(workingFolder.getRoot(), "count.csv");
        FileUtils.copyURLToFile(getClass().getResource("count.csv"), dataFile);
	}

	@Test
	def void testValidDataFile() {
		val mclText = '''bar = dataObj {
			
			
			DATA_INPUT_VARIABLES{
				ID : { use is id }
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = parse(mclText)
		
		mcl.assertNoIssues
	}

	@Test
	def void testValidWithWarningMissingDataFile() {
		val mclText = '''bar = dataObj {
			
			
			DATA_INPUT_VARIABLES{
				ID : { use is id }
			}
			
			SOURCE{
				srcFile : { file="nofile", inputFormat is nonmemFormat }
			}
			
		}'''
		
        val mcl = parse(mclText)
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage.eINSTANCE.assignPair, DataFileValidator::DATA_FILE_NOT_FOUND,
					"Cannot find data file: path may be incorrect.")
	}

    def private Mcl parse(String mclText) {
        // Have to fool the Xtext parser into thinking that the MDL fragment above
        // has actually been read in from a file, so the data file reference
        // can be resolved against it
        // Doesn't matter that the MDL file doesn't actually exist, for these tests
        parse(mclText, URI.createFileURI(new File(dataFile.parentFile, "myfile.mdl").toString()))
    }

}