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
class MclParserModelObj5Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_SEXAGE_mdl = mdlObj {
	IDV{T}
	
	COVARIATES{
		WT
		AGE
		SEX withCategories {female, male, MISSING}
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}

	VARIABILITY_LEVELS{
		ID : { type is parameter, level=2 }
		DV : { type is observation, level=1 }
	}

	STRUCTURAL_PARAMETERS {
		POP_CL
		POP_V
		POP_KA
		POP_TLAG
		BETA_CL_WT
		BETA_CL_AGE
		BETA_V_WT
		POP_FCL_FEM
		RUV_PROP
		RUV_ADD
	} # end STRUCTURAL_PARAMETERS
	
	VARIABILITY_PARAMETERS {
		PPV_CL
		PPV_V
		PPV_KA
		PPV_TLAG
	} # end VARIABILITY_PARAMETERS 

	GROUP_VARIABLES{
		FAGECL = exp(BETA_CL_AGE * tAGE)
		FSEXCL = if(SEX == SEX.female) then POP_FCL_FEM  else 1
		GRPCL = ln(POP_CL * FAGECL * FSEXCL ) + BETA_CL_WT * logtWT
		GRPV = POP_V * (WT/70)^BETA_V_WT
	}

	RANDOM_VARIABLE_DEFINITION(level=ID) {
		ETA_CL ~ Normal(mean = 0, sd = PPV_CL)
		ETA_V ~ Normal(mean = 0, sd = PPV_V)
		ETA_KA ~ Normal(mean = 0, sd = PPV_KA)
		ETA_TLAG ~ Normal(mean = 0, sd = PPV_TLAG) # We define correlation here
	} # end RANDOM_VARIABLE_DEFINITION 
	
	INDIVIDUAL_VARIABLES { # This maps to the "Type 1" individual parameter definition in PharmML
		CL = exp(GRPCL + ETA_CL)
		V = exp(GRPV + ETA_V)
	    KA = exp(POP_KA + ETA_KA)
	    TLAG = exp(POP_TLAG + ETA_TLAG)
	} # end INDIVIDUAL_VARIABLES

	
	MODEL_PREDICTION {
		DEQ{
			RATEIN = if(T >= TLAG) then GUT * KA else 0
			GUT : { deriv =(- RATEIN), init = 0, x0 = 0 } 
			CENTRAL : { deriv = (RATEIN - CL * CENTRAL / V), init = 0, x0 = 0 }  
		}
	    CC = CENTRAL / V 
	} # end MODEL_PREDICTION
	
	RANDOM_VARIABLE_DEFINITION(level=DV) {
		EPS_Y ~ Normal(mean = 0, var = 1) # This maps the standard error model in PharmML. The error model is predefined. 
	} # end RANDOM_VARIABLE_DEFINITION 

	OBSERVATION {
	    Y = combinedError1(additive = RUV_ADD, proportional = RUV_PROP, eps = EPS_Y, prediction = CC ) 
	} # end OBSERVATION
} # end of model object
'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	
}