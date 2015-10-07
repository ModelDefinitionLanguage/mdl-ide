package eu.ddmore.converter.mdl2pharmml

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class Mcl2PharmMLConverterTest1 {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper

	extension ConverterTestHarness cth = new ConverterTestHarness
	
	static val useCases = #[
		"UseCase1", "UseCase2", "UseCase3", "UseCase4", "UseCase4_1", "UseCase5", "UseCase6", "UseCase7", "UseCase8", "UseCase8_4", "UseCase9"
	]

	@Before
	def void setUp(){
	}
	
	
	@After
	def void tearDown(){
	}
	
	private def validateConversion(String useCaseName){
		val mclFile = "src/eu/ddmore/converter/mdl2pharmml/" + useCaseName + ".mdl"
		val mcl = readFile(mclFile).parse
		mcl.assertNoErrors
		val pharmMLFile = "convertedFiles/" + useCaseName + ".xml" 
		mcl.convertTo(pharmMLFile)
		assertIsValid(pharmMLFile)
	} 
	
	@Test
	def void testUseCases(){
		for(uc : useCases){
			validateConversion(uc)
		}
	}

}