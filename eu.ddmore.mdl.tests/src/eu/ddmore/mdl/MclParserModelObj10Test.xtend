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
class MclParserModelObj10Test {
//	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	@Inject extension LibraryTestHelper<Mcl>
	
	val static CODE_SNIPPET = '''
Bernoulli_DIST_mdl = mdlObj{
   IDV{ CP }

   VARIABILITY_LEVELS{
	ID : { level=2, type is parameter }
	DV : { level=1, type is observation }
   }

   STRUCTURAL_PARAMETERS{
      POP_BASEP
      POP_BETA
   }# end STRUCTURAL_PARAMETERS

   VARIABILITY_PARAMETERS{
      PPV_EVENT
   }# end VARIABILITY_PARAMETERS


   RANDOM_VARIABLE_DEFINITION(level=ID){
      eta_PPV_EVENT ~ Normal(mean=0, var=PPV_EVENT )
   }# end RANDOM_VARIABLE_DEFINITION

   GROUP_VARIABLES{
	  BASE = ln(POP_BASEP/(1-POP_BASEP))
   }
   
   INDIVIDUAL_VARIABLES{
      LP = BASE + POP_BETA*CP + eta_PPV_EVENT
   }# end INDIVIDUAL_VARIABLES

   MODEL_PREDICTION{
	  P1 = 1/(1+exp(-LP))
	  # P1 = invLogit(LP)
   }# end MODEL_PREDICTION

   OBSERVATION{
     Y : { type is discrete withCategories { success, fail }, distn = Bernoulli(category=Y.success, probability=P1)}
   }# end ESTIMATION
} # end of model object
'''
	
	@Test
	def void testParsing(){
		val mcl = CODE_SNIPPET.loadLibAndParse
		
		mcl.assertNoErrors
		
	}

	
}