package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import java.util.Deque
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.utils.BlockUtils

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserModelObj14Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper

	extension BlockUtils bu = new BlockUtils
	
	val static CODE_SNIPPET = '''
Hierarchical_model_mdl = mdlObj {
	IDV{ T }

	VARIABILITY_LEVELS{
		POP : { level=3, type is parameter }
		ID : { level=2, type is parameter }
		DV : { level=1, type is observation }
	} 

	COVARIATES(type is constant){
		WT ~ Normal(mean=w_pop, sd=omega_w)
	}

	STRUCTURAL_PARAMETERS {ws Vs k b BETA_WT=1} # end STRUCTURAL_PARAMETERS
	
	VARIABILITY_PARAMETERS { gV gw omega_w omega_V } # end VARIABILITY_PARAMETERS 


	RANDOM_VARIABLE_DEFINITION(level=POP){
		w_pop ~ Normal(mean = ws, sd = gw)
		V_pop ~ Normal(mean = Vs, sd = gV)
	} # end RANDOM_VARIABLE_DEFINITION 

	POPULATION_PARAMETERS{
		:: { type is continuous, variable=w_pop } 
		:: { type is continuous, variable=V_pop }
 	}

	RANDOM_VARIABLE_DEFINITION(level=ID) {
		ETA_BSV_WT ~ Normal(mean = 0, sd = omega_w)
		ETA_BSV_V ~ Normal(mean = 0, sd = omega_V)
	} # end RANDOM_VARIABLE_DEFINITION 

	INDIVIDUAL_VARIABLES { 
		V : {type is linear, trans is ln, pop=V_pop, fixEff={coeff=BETA_WT, cov=WT}, ranEff=ETA_BSV_V}
	} # end INDIVIDUAL_VARIABLES

	MODEL_PREDICTION {
		D
		f = D/V * exp(-k*T)
	} # end MODEL_PREDICTION
	
	RANDOM_VARIABLE_DEFINITION(level=DV){
	    EPS_Y ~ Normal(mean = 0, var = 1) # This maps the standard error model in PharmML. The error model is predefined.
	}

	OBSERVATION {
		Y = proportionalError(prediction=f, proportional=b, eps=EPS_Y)	} # end OBSERVATION
} # end of model object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
	@Test
	def void testBlocks(){
		val mcl = CODE_SNIPPET.parse
		val Deque<String> expectedBlks = newLinkedList("IDV", "VARIABILITY_LEVELS", "COVARIATES", "STRUCTURAL_PARAMETERS",
			"VARIABILITY_PARAMETERS", "RANDOM_VARIABLE_DEFINITION", "POPULATION_PARAMETERS", "RANDOM_VARIABLE_DEFINITION",
			"INDIVIDUAL_VARIABLES", "MODEL_PREDICTION", "RANDOM_VARIABLE_DEFINITION", "OBSERVATION"
		);
		for(blk : mcl.objects.last.blocks){
			Assert::assertEquals(expectedBlks.pop, blk.identifier)
		}
	}

	
}