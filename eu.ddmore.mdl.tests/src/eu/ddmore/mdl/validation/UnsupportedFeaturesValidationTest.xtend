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
warfarin_design = desObj {
	DECLARED_VARIABLES{
		bsv_lvl; GUT; oral1
	}
	
	# trial design
	# arm 1 is placebo
	# arm 2 is 2.5 mg dosing time given, then interdose (II) period, the number of doses (addl)
	# arm 3 is 5 mg
	# arm 4 is 10 mg

	# wgt covariate is simulating

	ADMINISTRATION{
		# again there is some repetition here so allowing a variable defn here would be useful
		doseStart = 0
		doseEnd = 24
		doseGap = 2
		baseAmt = 2.5

		# could use an adm type instead so do
		dreg1 : { adm=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg2 : { adm=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg3 : { adm=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
		dreg4 : { adm=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0, start=doseStart, end=doseEnd}
	}

	STUDY_DESIGN{
		WT_MEAN = 85.5
		WT_VAR = 19
	    WT ~ Normal(mean=WT_MEAN, var=WT_VAR)
		# it would be helpful if I could put in some vars here to help make defn of similar vars easier.
		epochStart = 0
		epochEnd = 24
		varlevel = bsv_lvl
		arm1 : {
		     	armSize = 10,
		     	interventionSequence = [
		       	{
		       		interventionList = [dreg1],
		       		start = [epochStart],
		       		end = [epochEnd]
		      	}
		     ]
		}
		arm2 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
		     		interventionList = [dreg2],
		       		start = [epochStart],
		       		end = [epochEnd]}
		     ]
		}
		arm3 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
			       interventionList = [dreg3],
			       start = [epochStart],
			       end = [epochEnd]
			     }
		     ]
		}
		arm4 : {
		     armSize = 20,
		     interventionSequence = [
		     	{
			       interventionList = [dreg4],
			       start = [epochStart],
			       end = [epochEnd]
			     }
		     ]
		}
	}
}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject, UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
			"Objects of type 'desObj' are not currently supported for execution in R."
		)
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

	@Test
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
				TSEX = piecewise(piece=[{condition=sex == sex.f, value=1}], otherwise=0)
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
	def void testValidIfElseWithElse(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}

			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo = if(true) then 1 else 0
			}# end MODEL_PREDICTION
			
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.whenExpression,
			MdlValidator::UNSUPPORTED_FEATURE,
			"A conditional expression is not supported. Use a piecewise function instead."
		)
	}

	@Test
	def void testValidIfElseWithElseIf(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}
			
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo = if(true) then 1 elseif(false) then 0
			}# end MODEL_PREDICTION
			
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.whenExpression,
			MdlValidator::UNSUPPORTED_FEATURE,
			"A conditional expression is not supported. Use a piecewise function instead."
		)
	}



}
