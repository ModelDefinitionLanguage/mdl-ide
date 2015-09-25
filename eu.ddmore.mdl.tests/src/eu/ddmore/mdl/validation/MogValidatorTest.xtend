package eu.ddmore.mdl.validation

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import eu.ddmore.mdl.MdlInjectorProvider
import com.google.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import eu.ddmore.mdl.mdl.Mcl
import org.junit.Test
import static org.junit.Assert.*

import org.junit.Before
import eu.ddmore.mdl.utils.MclUtils
import eu.ddmore.mdl.mdl.MdlPackage

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MogValidatorTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper

	extension MclUtils domu = new MclUtils


	int errorCount
	
	@Before
	def void setUp(){
		errorCount = 0
	}
	
	@Test
	def void testValidCovariateMatching(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
		
			DATA_INPUT_VARIABLES {
				SEX : { use is catCov withCategories{male when 0, female when 1} }
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					SEX withCategories{male, female}
					WT
					logWT = ln(WT/70)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		
		'''.parse
	
		mcl.assertNoErrors	
	
	}
		
	@Test
	def void testValidCovariateMatchingWithMatchingDayaVar(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{GUT Y}
		
			DATA_INPUT_VARIABLES {
				logWT : { use is covariate }
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
			
		foo = mdlobj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT
					logWT = ln(WT/70)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	@Test
	def void testInValidCovariateMatchingDataMissing(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
		
			DATA_INPUT_VARIABLES {
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
				
		foo = mdlobj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT
					logWT
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"covariate logWT has no match in dataObj"
		)
	}
		
	@Test
	def void testValidCovariateNoMdlCovars(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
		
			DATA_INPUT_VARIABLES {
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
				
		foo = mdlobj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	
	@Test
	def void testValidCovariateNoMatchingDataCovars(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
		
			DATA_INPUT_VARIABLES {
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					logWT = ln(70)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	@Test
	def void testInValidCovariateOfWrongType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DATA_INPUT_VARIABLES {
				SEX : { use is catCov withCategories{male when 0, female when 1} }
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES

			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT withCategories{male, female}
					SEX
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"covariate WT has an inconsistent type with its match in the dataObj"
		)
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"covariate SEX has an inconsistent type with its match in the dataObj"
		)
	}
		
	@Test
	def void testInValidCovariateOfInconsistentCategories(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DATA_INPUT_VARIABLES {
				SEX : { use is catCov withCategories{male when 0, female when 1} }
			} # end DATA_INPUT_VARIABLES

			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
		
		foo = mdlobj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					SEX withCategories{male, fem}
				}
		}
				mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"covariate SEX has an inconsistent type with its match in the dataObj"
		)
	}
		
	@Test
	def void testValidSingleObsMatching(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				DV : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}

	@Test
	def void testInvalidSingleObsMismatchingType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				DV : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y : { type is count, distn = Poisson(lambda = F), link is ln }
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Y has an inconsistent type with its match in the dataObj"
		)
	}

	@Test
	def void testInvalidSingleObsMismatchedName(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{ Z }
		
			DATA_INPUT_VARIABLES {
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Y has no match in dataObj"
		)
	}

	@Test
	def void testInvalidSingleObsMismatchedNoData(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Y has no match in dataObj"
		)
	}

	@Test
	def void testValidMultiObsContinuousMatching(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{ Y; Z }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y, 2 in DVID as Z } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
					Z = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}

	@Test
	def void testInvalidMultiObsContinuousMisMatchedName(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{ Y; X }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y, 2 in DVID as X } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
					Z = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Z has no match in dataObj"
		)
	}

	@Test
	def void testInvalidMultiObsContinuousMissing(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{ Y; Z }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
					Z = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Z has no match in dataObj"
		)
	}

	@Test
	def void testInvalidMultiObsContinuousMismatchType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataobj {
			DECLARED_VARIABLES{ Y; Z withCategories { a, b }}
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y, 2 in DVID as {Z.a when 0, Z.b when 1 } } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlobj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
					Z = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogobj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataobj }
				foo : { type is mdlobj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Z has an inconsistent type with its match in the dataObj"
		)
	}

}