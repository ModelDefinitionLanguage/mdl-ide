package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclParserTaskObj1Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
nonmem_task = taskobj {
	ESTIMATE{
		target = "NONMEM"
		properties="
			; this is defined in INI format
			[method]
				algorithm=SAEM
				typeIndPar=MAP
				approximationSE=linear
				seed=19245

			[compute]
			estimationSE=TRUE
			estimationIndPar=TRUE
			plotGraphs=TRUE
	
			[softwareSettings]
			TOL=3
			NOABORT=TRUE
		"
	}
}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	
}