package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.mdl.MdlPackage
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class UnsupportedFeaturesValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	


	@Test
	def void testDesignObjectUnsupported(){
		val mcl = '''
warfarin_design = designObj {
	DECLARED_VARIABLES{
		bsv_lvl; GUT; oral1
	}
	
	# trial design
	# arm 1 is placebo
	# arm 2 is 2.5 mg dosing time given, then interdose (II) period, the number of doses (addl)
	# arm 3 is 5 mg
	# arm 4 is 10 mg

	# wgt covariate is simulating

	DESIGN_PARAMETERS{
		WT_MEAN = 85.5
		WT_VAR = 19
		doseStart = 0
		doseEnd = 24
		doseGap = 2
		baseAmt = 2.5
		epochStart = 0
		epochEnd = 24
«««		varlevel = bsv_lvl
	}

	COVARIATES{
	    WT ~ Normal(mean=WT_MEAN, var=WT_VAR)
	}

	ADMINISTRATION{
		# again there is some repetition here so allowing a variable defn here would be useful

		# could use an adm type instead so do
		dreg1 : { input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg2 : { input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg3 : { input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg4 : { input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
	}

	STUDY_DESIGN{
		arm1 : {
		     	armSize = 10,
		     	interventionSequence = [
		       	{
		       		admin = dreg1,
		       		start = epochStart,
		       		end = epochEnd
		      	}
		     ]
		}
		arm2 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
		     		admin = dreg2,
		       		start = epochStart,
		       		end = epochEnd
		       	}
		     ]
		}
		arm3 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
			       admin = dreg3,
			       start = epochStart,
			       end = epochEnd
			     }
		     ]
		}
		arm4 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
			       admin = dreg4,
			       start = epochStart,
			       end = epochEnd
			     }
		     ]
		}
	}
}
	'''.parse
		mcl.assertNoErrors
		mcl.assertNoIssues
//		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject, UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
//			"Objects of type 'desObj' are not currently supported for execution in R."
//		)
	}

	@Test
	def void testWarningWhenWrtDifferent(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
				X : { deriv = 1, wrt = Y }
			}
		}'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.valuePair,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Derivative variables with an independent variable different to the model's IDV cannot be executed in R."
		)
	}

	@Test
	def void testWarningWhenNonZerox0(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
				X : { deriv = 1, x0 = 1.0 }
			}
		}'''.parse
		
		mcl.assertWarning(MdlPackage::eINSTANCE.valuePair,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Derivative variables with a non-zero initial time cannot be executed in R."
		)
	}

	@Test
	def void testNoWarningWhenZerox0(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
				X : { deriv = 1, x0 = 0.0 }
			}
		}'''.parse
		
		mcl.assertNoIssues
	}

	@Test
	def void testWarningWhenElseFollowedByIf(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
				X = if(Y < 0) then -1 else if(Y > 0) then 1 else 0
			}
		}'''.parse
		
		mcl.assertWarning(MdlPackage::eINSTANCE.elseClause,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Nested conditional expression cannot be executed in R. Consider changing to 'elseif'.")
	}

	@Ignore
	// now removed so can't test this rather 
	def void testWarningUnsupportedAttribute(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y
			}
			
			OBSERVATION{
				o : { type is tte, hazard=Y, event is rightCensored, maxEvent = inf }
			}
		}'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.valuePair,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Attribute name 'event' is not currently supported for execution in R."
		)
		mcl.assertWarning(MdlPackage::eINSTANCE.valuePair,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Attribute name 'maxEvent' is not currently supported for execution in R."
		)
	}

	@Ignore
	def void testWarningUnsupportedFunction(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				Y = tanh(1.2)
				Z = sinh(1.2)
				A = cosh(1.2)
			}
			
		}'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.builtinFunctionCall,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Function 'tanh' is not currently supported for execution in R."
		)
		mcl.assertWarning(MdlPackage::eINSTANCE.builtinFunctionCall,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Function 'sinh' is not currently supported for execution in R."
		)
		mcl.assertWarning(MdlPackage::eINSTANCE.builtinFunctionCall,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Function 'cosh' is not currently supported for execution in R."
		)
	}

	@Test
	def void testValidCategorialCovEquality(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
				sex withCategories { m, f }
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				TSEX = if(sex == sex.f) then 1 else 0
			}
			
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = general(grp=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.equalityExpression,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Equivalence operators with categorical types are not supported for execution in R.")
	}

	@Test
	def void testSupportedColumnNamesDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y; INPUT }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      TIME: {use is idv}
      TRT: {use is covariate}
      AMT: {use is amt, variable = INPUT }
      RATE: {use is rate}
      SS: {use is ss}
      ADDL: {use is addl}
      II: {use is ii}
      WT: {use is covariate}
      DVID: {use is dvid}
      DV: {use is dv, variable=Y}
      MDV: {use is mdv}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="src/eu/ddmore/mdl/validation/count.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse
		
		mcl.assertNoIssues
	}


	@Test
	def void testUnsupportedColumnNamesDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y; INPUT }

   DATA_INPUT_VARIABLES{
      IDA: {use is id}
      TIMER: {use is idv}
      TRT: {use is covariate}
      AMTA: {use is amt, variable = INPUT }
      RATEA: {use is rate}
      SSA: {use is ss}
      ADDLA: {use is addl}
      IIA: {use is ii}
      WT: {use is covariate}
      DVIDA: {use is dvid}
      DVA: {use is dv, variable=Y}
      MDVA: {use is mdv}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="src/eu/ddmore/mdl/validation/count.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'id' must be named 'ID' otherwise execution in R will fail.")
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'idv' must be named 'TIME' otherwise execution in R will fail.")
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'amt' must be named 'AMT' otherwise execution in R will fail.")
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'rate' must be named 'RATE' otherwise execution in R will fail.")
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'ss' must be named 'SS' otherwise execution in R will fail.")
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'addl' must be named 'ADDL' otherwise execution in R will fail.")
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'ii' must be named 'II' otherwise execution in R will fail.")
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'mdv' must be named 'MDV' otherwise execution in R will fail.")
		mcl.assertWarning(MdlPackage::eINSTANCE.symbolDefinition,
			UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Column definitions with use 'dv' must be named 'DV' otherwise execution in R will fail.")
	}


	@Test
	def void testExperimentalDiscreteFeature(){
		val mcl = '''
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
 
   INDIVIDUAL_VARIABLES{
      logit(indiv_BASE) = linear(pop= POP_BASEP, ranEff=[eta_PPV_EVENT], trans is logit) 
   }# end INDIVIDUAL_VARIABLES

   MODEL_PREDICTION{
  	  LP = logit(indiv_BASE) + POP_BETA*CP
	  P1 = invLogit(LP)
   }# end MODEL_PREDICTION

   OBSERVATION{
     Y : { type is discrete withCategories {success, fail} , distn = Bernoulli(category=Y.success, probability=P1) }
   }# end ESTIMATION
} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::EXPERIMENTAL_FEATURE,
			"This is an experimental feature and may change in the future. Models using this feature may not be compatible with later versions of MDL.")
	}

	@Test
	def void testExperimentalCategoricalFeature(){
		val mcl = '''
Categorical_DIST_mdl = mdlObj{
   IDV{ CP }

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
	  B0 = Lgt0
	  B1 = B0 + Lgt1
	  B2 = B1 + Lgt2
   }
   
   INDIVIDUAL_VARIABLES{
      indiv_B0 = general(grp=B0, ranEff = [eta_PPV_EVENT])
      indiv_B1 = general(grp=B1, ranEff = [eta_PPV_EVENT])
      indiv_B2 = general(grp=B2, ranEff = [eta_PPV_EVENT])
   }# end INDIVIDUAL_VARIABLES

   MODEL_PREDICTION{
	  EDRUG = Beta * CP
	  
	  A0 = indiv_B0 + EDRUG
	  A1 = indiv_B1 + EDRUG
	  A2 = indiv_B2 + EDRUG
	  	  	  
	  P0 = invLogit(A0)
	  P1 = invLogit(A1)
	  P2 = invLogit(A2)
	  
	  Prob0 = P0
	  Prob1 = P1 - P0
	  Prob2 = P2 - P1
	  Prob3 = 1 - P2
   }# end MODEL_PREDICTION

   OBSERVATION{
	   Y : {type is categorical withCategories{ c0 when Prob0, c1 when Prob1, c2 when Prob2, c3 when Prob3} } 
    }# end ESTIMATION

} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::EXPERIMENTAL_FEATURE,
			"This is an experimental feature and may change in the future. Models using this feature may not be compatible with later versions of MDL.")
	}


}
