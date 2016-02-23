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
class MclParserModelObj12Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	MODEL_PREDICTION{
		AVAR::Real
	}
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real, arg3::String)::Real is
			# the function can contain only a single expression
		    arg2 * arg1  # return type is Real
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		#Z = userFunc(1, ln(2.0), "foo") + 22.2
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
		DV : { level=1, type is observation }
	} 

}
		'''
	
	@Test
	def void testParsing(){
		val mdl = CODE_SNIPPET.parse
		mdl.assertNoErrors
	}
	
//	@Test
//	// needed to stop initialisation failure
//	def void testDummy(){
//	}
}