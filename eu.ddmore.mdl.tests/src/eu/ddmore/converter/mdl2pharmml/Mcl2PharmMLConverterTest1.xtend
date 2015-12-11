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
import org.junit.Ignore
import java.io.File

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class Mcl2PharmMLConverterTest1 {
	static val CONVERTED_OUTPUT_DIR="convertedFiles/"
	static val USE_CASE_DIR="src/eu/ddmore/converter/mdl2pharmml/"
	
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper

	extension ConverterTestHarness cth = new ConverterTestHarness
	
	@Before
	def void setUp(){
		val convertedDir = new File(CONVERTED_OUTPUT_DIR)
		if(!convertedDir.exists) convertedDir.mkdir
	}
	
	
	@After
	def void tearDown(){
	}
	
	private def validateConversion(String useCaseName){
		val mclFile = USE_CASE_DIR + useCaseName + ".mdl"
		val mcl = readFile(mclFile).parse
		mcl.assertNoErrors
		val pharmMLFile = CONVERTED_OUTPUT_DIR + useCaseName + ".xml" 
		mcl.convertTo(pharmMLFile)
		assertIsValid(pharmMLFile)
	} 
	
	@Test
	def void testUseCase1(){
		validateConversion("UseCase1")
	}

	@Test
	def void testUseCase2(){
		validateConversion("UseCase2")
	}

	@Test
	def void testUseCase2_1(){
		validateConversion("UseCase2_1")
	}

	@Test
	def void testUseCase2_2(){
		validateConversion("UseCase2_2")
	}

	@Test
	def void testUseCase3(){
		validateConversion("UseCase3")
	}

	@Test
	def void testUseCase4(){
		validateConversion("UseCase4")
	}

	@Test
	def void testUseCase4_1(){
		validateConversion("UseCase4_1")
	}

	@Test
	def void testUseCase5(){
		validateConversion("UseCase5")
	}

	@Ignore
	def void testUseCase5_1(){
		validateConversion("UseCase5_1")
	}

	@Ignore
	def void testUseCase5_2(){
		validateConversion("UseCase5_2")
	}

	@Ignore
	def void testUseCase5_3(){
		validateConversion("UseCase5_3")
	}

	@Test
	def void testUseCase6(){
		validateConversion("UseCase6")
	}

	@Test
	def void testUseCase7(){
		validateConversion("UseCase7")
	}

	@Test
	def void testUseCase8(){
		validateConversion("UseCase8")
	}

	@Test
	def void testUseCase8_1(){
		validateConversion("UseCase8_1")
	}

	@Test
	def void testUseCase8_4(){
		validateConversion("UseCase8_4")
	}

	@Test
	def void testUseCase9(){
		validateConversion("UseCase9")
	}

	@Test
	def void testUseCase10(){
		validateConversion("UseCase10")
	}

	@Test
	def void testUseCase10_1(){
		validateConversion("UseCase10_1")
	}

	@Test
	def void testUseCase11(){
		validateConversion("UseCase11")
	}

	@Test
	def void testUseCase12(){
		validateConversion("UseCase12")
	}

	@Test
	def void testUseCase12_1(){
		validateConversion("UseCase12_1")
	}

	@Test
	def void testUseCase12_2(){
		validateConversion("UseCase12_2")
	}

	@Test
	def void testUseCase13(){
		validateConversion("UseCase13")
	}

	@Test
	def void testUseCaseMixedObs(){
		validateConversion("UseCaseMixedObs")
	}

	@Test
	def void testUseCase13_1(){
		validateConversion("UseCase13_1")
	}

	@Test
	def void testUseCase14(){
		validateConversion("UseCase14")
	}

	@Ignore
	def void testUseCase14_1(){
		validateConversion("UseCase14_1")
	}

	@Ignore
	def void testUseCase14_2(){
		validateConversion("UseCase14_2")
	}

	@Test
	def void testUseCase15(){
		validateConversion("UseCase15")
	}

	@Test
	def void testUseCase16(){
		validateConversion("UseCase16")
	}

	@Test
	def void testUseCase17(){
		validateConversion("UseCase17")
	}

	@Ignore // compartment dosing to ODEs not supported in 4.1
	def void testUseCase101(){
		validateConversion("UseCase101")
	}

	@Test
	def void testUseCase102(){
		validateConversion("UseCase102")
	}

	@Test
	def void testUseCase103(){
		validateConversion("UseCase103")
	}

	@Test
	def void testUseCase104(){
		validateConversion("UseCase104")
	}

	@Test
	def void testUseCase105(){
		validateConversion("UseCase105")
	}
	
	@Test
	def void testUseCase106(){
		validateConversion("UseCase106")
	}
	
	@Test
	def void testUseCase107(){
		validateConversion("UseCase107")
	}
	
	@Test
	def void testUseCase108(){
		validateConversion("UseCase108")
	}
	
	@Test
	def void testUseCase109(){
		validateConversion("UseCase109")
	}

	@Test
	def void testUseCase110(){
		validateConversion("UseCase110")
	}
}
