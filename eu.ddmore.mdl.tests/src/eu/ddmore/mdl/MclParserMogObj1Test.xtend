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
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserMogObj1Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
mobj = mdlObj {
	IDV{T}
	VARIABILITY_LEVELS{
	} 
}

pobj = parObj {
}

dobj = dataObj {
	DATA_INPUT_VARIABLES{  foo : { use is idv } }
	SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
}

tobj = taskObj {
}

mgobj = mogObj {
	OBJECTS{
		mobj : { type is mdlObj }
		pobj : { type is parObj }
		dobj : { type is dataObj }
		tobj : { type is taskObj }
	}
}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
}