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
class MclParserParamObj1Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_ODE_par = parobj {
	DECLARED_VARIABLES{ETA_CL ETA_V}

	# By default a parameter is to be estimated if fix is omitted
 	STRUCTURAL {
		POP_CL = <(0.1, 0.001 )>
		POP_V = <(8, 0.001)> 
		POP_KA = <(0.362, 0.001)>
		POP_TLAG = <(1, 0.001, 10)>
		BETA_CL_WT = 0.75
		BETA_V_WT = 1 
		RUV_PROP = <(0.1, 0 )>
		RUV_ADD = <(0.1, 0)> 
		} # end STRUCTURAL
	VARIABILITY(type is sd) {
		PPV_CL = <(, 0.1)> 
		PPV_V = <(0.1, 0, 1)>
		PPV_KA = 0.1
		PPV_TLAG = <(, 0.1)> 
	} # end VARIABILITY
} # end of parameter object 
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	
}