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
class MclParserModelObj8Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_SEXAGE_mdl = mdlObj {
	IDV{ T }

	VARIABILITY_LEVELS{
		ID : { level=3, type is parameter }
		BOV : { level=2, type is parameter }
		DV : { level=1, type is observation }
	}

	COVARIATES{
		WT
		logtWT = ln(WT/70)
	}

	STRUCTURAL_PARAMETERS {
		POP_CL
		POP_V
		POP_KA
		POP_TLAG
		BETA_CL_WT
		BETA_V_WT
		RUV_PROP
		RUV_ADD
	} # end STRUCTURAL_PARAMETERS
	
	VARIABILITY_PARAMETERS {
		PPV_CL
		PPV_V
		PPV_KA
		PPV_TLAG
	} # end VARIABILITY_PARAMETERS 
	
	RANDOM_VARIABLE_DEFINITION(level=ID) {
		ETA_CL ~ Normal(mean = 0, sd = PPV_CL)
		ETA_V ~ Normal(mean = 0, sd = PPV_V)
		ETA_KA ~ Normal(mean = 0, sd = PPV_KA)
		ETA_TLAG ~ Normal(mean = 0, sd = PPV_TLAG)
	} # end RANDOM_VARIABLE_DEFINITION 
	
	INDIVIDUAL_VARIABLES { # This maps to the "Type 3" individual parameter definition in PharmML
	    ln(CL) = linear(trans is ln, pop = POP_CL, fixEff = [{coeff=BETA_CL_WT , cov = logtWT }], ranEff = [ETA_CL])
	    logit(V) = linear(trans is logit, pop = POP_V, fixEff =  [{coeff=BETA_V_WT , cov = logtWT }] , ranEff = [ETA_V])
	    KA = linear(pop = POP_KA, ranEff = [ETA_KA])
	    probit(TLAG) = linear(trans is probit, pop = POP_TLAG, ranEff = [ETA_TLAG]) 
	} # end INDIVIDUAL_VARIABLES
	
	MODEL_PREDICTION {
	    D # dosing variable
	    DT # dosing time
	    k = CL/V
	    CC = if(T - DT < TLAG) then 0 
	         else (D/V) * (KA/(KA-k) * (exp(-k * (T -DT-TLAG) - exp(-KA*(T-DT-TLAG)))))
	} # end MODEL_PREDICTION

	RANDOM_VARIABLE_DEFINITION(level=DV){
		EPS_Y ~ Normal(mean = 0, var = 1) # This maps the standard error model in PharmML. The error model is predefined. 
	}

	OBSERVATION {
	    Y = additiveError(additive = RUV_ADD, eps = EPS_Y, prediction = CC) 
	} # end OBSERVATION

} # end of model object
'''
	
	@Test
	def void testParsing(){
		val mcl = CODE_SNIPPET.parse
		
		mcl.assertNoErrors
		
	}

	
}