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
class MclParserFunctionObj1Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
foo = funcObj {
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc = function(int arg1, real arg2, string arg3) {
			# the function can contain only a single expression
		    arg2 + arg1  # return types have to match type of func name
		}
	}
}
		'''
	
	@Test
	def void testParsing(){
		val mdl = CODE_SNIPPET.parse
		mdl.assertNoErrors
	}
	
}