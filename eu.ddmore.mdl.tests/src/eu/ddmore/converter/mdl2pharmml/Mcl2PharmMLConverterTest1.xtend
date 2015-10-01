package eu.ddmore.converter.mdl2pharmml

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.utils.MclUtils
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
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

	extension MclUtils mu = new MclUtils
	extension Mdl2Pharmml mpc = new Mdl2Pharmml

	var String mdlStr = ''


	@Before
	def void setUp(){
		var FileReader in = null
		try{
			in = new FileReader("src/eu/ddmore/converter/mdl2pharmml/UseCase1.mdl")
			var char[] buf = newCharArrayOfSize(2048)
			mdlStr = ""
			var c = 0
			while((c = in.read(buf, 0, buf.length)) != -1){
				mdlStr = mdlStr.concat(new String(buf, 0, c))
			}
		}
		finally{
			in?.close
		}
	}
	
	
	@After
	def void tearDown(){
		mdlStr = null
	}
	
	
	@Test
	def void testParsing(){
		val mcl = mdlStr.parse
		mcl.assertNoErrors
		
	}

	@Test
	def void testConversion(){
		val mcl = mdlStr.parse
		val mogObj = mcl.mogObject
		val output = mogObj.convertToPharmML
		var out = new BufferedWriter(new FileWriter("output.xml"))
		out.write(output.toString, 0, output.length)
		out.close
	}

}