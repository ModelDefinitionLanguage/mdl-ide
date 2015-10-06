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

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class Mcl2PharmMLConverterTest1 {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper

//	extension MclUtils mu = new MclUtils
//	extension Mdl2Pharmml mpc = new Mdl2Pharmml

	extension ConverterTestHarness cth = new ConverterTestHarness
	

	@Before
	def void setUp(){
	}
	
	
	@After
	def void tearDown(){
	}
	
	@Test
	def void testUseCase1(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase1.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase1.xml")
	}

	@Test
	def void testUseCase2(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase2.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase2.xml")
	}

	@Ignore
	def void testUseCase3(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase3.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase3.xml")
	}

	@Test
	def void testUseCase4(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase4.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase4.xml")
	}

	@Ignore
	def void testUseCase5(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase5.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase5.xml")
	}

	@Test
	def void testUseCase6(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase6.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase6.xml")
	}

	@Test
	def void testUseCase7(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase7.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase7.xml")
	}

	@Test
	def void testUseCase8(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase8.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase8.xml")
	}

	@Test
	def void testUseCase9(){
		val mcl = readFile("src/eu/ddmore/converter/mdl2pharmml/UseCase9.mdl").parse
		mcl.assertNoErrors
		mcl.convertTo("convertedFiles/UseCase9.xml")
	}

}