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
class MclParserModelObj6Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_SEXAGE_mdl = mdlObj {
	IDV{ CP }

	COVARIATES{
		WT
		AGE
		SEX withCategories {female, male, MISSING}
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
		DV : { level=1, type is observation }
	}

	STRUCTURAL_PARAMETERS{
		Beta
		Lgt0
		Lgt1
		Lgt2
	}# end STRUCTURAL_PARAMETERS

	VARIABILITY_PARAMETERS{
		PPV_EVENT
	}# end VARIABILITY_PARAMETERS


	RANDOM_VARIABLE_DEFINITION(level=ID){
		eta_PPV_EVENT ~ Normal(mean=0, var=PPV_EVENT )
	}# end RANDOM_VARIABLE_DEFINITION

	GROUP_VARIABLES{
		EDRUG = Beta * CP
		B0 = Lgt0
		B1 = B0 + Lgt1
	B2 = B1 + Lgt2
	}

	INDIVIDUAL_VARIABLES{
		A0 = B0 + EDRUG + eta_PPV_EVENT
		A1 = B1 + EDRUG + eta_PPV_EVENT
		A2 = B2 + EDRUG + eta_PPV_EVENT
	}# end INDIVIDUAL_VARIABLES

	MODEL_PREDICTION{
		P0 = 1/(1+exp(-A0))  #invLogit(A0)
		P1 = 1/(1+exp(-A1))  #invLogit(A1)
		P2 = 1/(1+exp(-A2))  #invLogit(A2)

		Prob0 = P0
		Prob1 = P1 - P0
		Prob2 = P2 - P1
		Prob3 = 1 - P2
	}# end MODEL_PREDICTION

	OBSERVATION{
		# 11) Now use category definitions to describe categorical observations
		PAIN : { type is categorical withCategories {mild when Prob0, moderate when Prob1, severe when Prob2, missing when Prob3} }
		
		TENDERNESS : { type is categorical withCategories {mild when Prob0, moderate when Prob1, severe when Prob2, missing when Prob3} } 
	}
} # end of model object
'''
	
	@Test
	def void testParsing(){
		val mcl = CODE_SNIPPET.parse
		
		mcl.assertNoErrors
		
	}

	
}