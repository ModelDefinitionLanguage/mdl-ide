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
class MclParserParamObj1Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_ODE_par = parObj {
	DECLARED_VARIABLES{ETA_CL ETA_V}

	# By default a parameter is to be estimated if fix is omitted
		STRUCTURAL {
			POP_CL : { value = 0.1, lo=0.001 }
			POP_V : { value = 8, lo=0.001 }
			POP_KA : { value = 0.362, lo=0.001 }
			POP_TLAG : { value = 1, lo=0.001, hi=10 }
			BETA_CL_WT : { value = 0.75, fix=true }
			BETA_V_WT : { value = 1, fix=true }
			RUV_PROP : { value = 0.1, lo=0 }
			RUV_ADD : { value = 0.1, lo=0 }
		} # end STRUCTURAL
		VARIABILITY {
			PPV_CL : { type is sd, value=0.1 } 
			PPV_V : { type is sd, value=0.1, lo=0, hi=1 } 
			PPV_KA : { type is sd, value=0.1, fix=true } 
			PPV_TLAG : { type is sd, value=0.1, fix=false } 
		} # end VARIABILITY
} # end of parameter object 
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	
}