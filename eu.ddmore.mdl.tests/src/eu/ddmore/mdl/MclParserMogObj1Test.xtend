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
class MclParserMogObj1Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
mobj = mdlObj {
	VARIABILITY_LEVELS{
	} 
}

pobj = parObj {
}

dobj = dataObj {
	DATA_INPUT_VARIABLES{}
	SOURCE{}
}

tobj = taskObj {
}

mgobj = mogObj {
	OBJECTS{
		mobj
		pobj
		dobj
		tobj
	}
}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
}