package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserTaskObj2Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
nonmem_task = taskObj {
	ESTIMATE {
«««		set target is monolix, version = "4.3.2", algo is saem
		set algo is saem
		TARGET_SETTINGS(target="MONOLIX", settingsFile=["aFile.txt"]){
			set anyProp = "anyVal"
		}
		TARGET_SETTINGS(target="ANYT", settingsFile="aFile.txt"){
			set anyProp = "anyValss", aVa = 4
		}
		TARGET_SETTINGS(target="NONMEM"){
			set anyProp = "anyVal"
		}	}
}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	
}