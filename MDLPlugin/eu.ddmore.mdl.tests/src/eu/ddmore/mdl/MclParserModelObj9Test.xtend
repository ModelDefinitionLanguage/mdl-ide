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
class MclParserModelObj9Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
Poisson_DIST_mdl = mdlObj{
   IDV{ CP }

   VARIABILITY_LEVELS{
	ID : { level=2, type is parameter }
	DV : { level=1, type is observation }
   }

   STRUCTURAL_PARAMETERS{
      POP_BASECOUNT
      POP_BETA
   }# end STRUCTURAL_PARAMETERS

   VARIABILITY_PARAMETERS{
      PPV_EVENT
   }# end VARIABILITY_PARAMETERS


   RANDOM_VARIABLE_DEFINITION(level=ID){
      eta_PPV_EVENT ~ Normal(mean=0, var=PPV_EVENT )
   }# end RANDOM_VARIABLE_DEFINITION
   
   INDIVIDUAL_VARIABLES{
      logLAMBDA=ln(POP_BASECOUNT) + POP_BETA*CP + eta_PPV_EVENT
   }# end INDIVIDUAL_VARIABLES

   RANDOM_VARIABLE_DEFINITION(level=DV){
   		Y ~ Poisson(lambda = logLAMBDA)
   }

   OBSERVATION{
	  :: { type is count, variable = Y}
   }# end ESTIMATION

} # end of model object
'''
	
	@Test
	def void testParsing(){
		val mcl = CODE_SNIPPET.parse
		
		mcl.assertNoErrors
		
	}

	
}