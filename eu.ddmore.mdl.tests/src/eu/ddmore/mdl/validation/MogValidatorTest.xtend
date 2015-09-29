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
		warfarin_PK_ODE_dat = dataObj {
		
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
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					SEX withCategories{male, female}
					WT
					logWT = ln(WT/70)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		
		'''.parse
	
		mcl.assertNoErrors	
	
	}
		
	@Test
	def void testValidCovariateMatchingWithMatchingDayaVar(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
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
			
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT
					logWT = ln(WT/70)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	@Test
	def void testInValidCovariateMatchingDataMissing(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
		
			DATA_INPUT_VARIABLES {
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
				
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT
					logWT
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
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
		warfarin_PK_ODE_dat = dataObj {
		
			DATA_INPUT_VARIABLES {
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
				
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	
	@Test
	def void testValidCovariateNoMatchingDataCovars(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
		
			DATA_INPUT_VARIABLES {
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					logWT = ln(70)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	@Test
	def void testInValidCovariateOfWrongType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
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
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT withCategories{male, female}
					SEX
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
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
		warfarin_PK_ODE_dat = dataObj {
			DATA_INPUT_VARIABLES {
				SEX : { use is catCov withCategories{male when 0, female when 1} }
			} # end DATA_INPUT_VARIABLES

			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
		
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					SEX withCategories{male, fem}
				}
		}
				mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
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
		warfarin_PK_ODE_dat = dataObj {
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
		foo = mdlObj {
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
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}

	@Test
	def void testInvalidSingleObsMismatchingType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y withCategories {true, false} }
		
			DATA_INPUT_VARIABLES {
				DV : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
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
					Y : { type is count, distn = Poisson(lambda = F) }
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
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
		warfarin_PK_ODE_dat = dataObj {
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
		foo = mdlObj {
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
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
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
		warfarin_PK_ODE_dat = dataObj {
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
		foo = mdlObj {
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
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
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
		warfarin_PK_ODE_dat = dataObj {
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
		foo = mdlObj {
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
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}

	@Test
	def void testInvalidMultiObsContinuousMisMatchedName(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
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
		foo = mdlObj {
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
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
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
		warfarin_PK_ODE_dat = dataObj {
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
		foo = mdlObj {
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
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
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
		warfarin_PK_ODE_dat = dataObj {
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
		foo = mdlObj {
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
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Z has an inconsistent type with its match in the dataObj"
		)
	}

	@Test
	def void testValidDiscreteType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
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
					Z : { type is count, distn = Poisson(lambda=0.0) }
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelsMatch(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID : { use is id }
				DV : { use is dv, variable = Y }
				OCC : { use is varLevel }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					OCC : { type is parameter, level = 3 }
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testInalidVariabilityLevelsMissingInData(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID : { use is id }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"variability level DV has no match in dataObj"
		)
	}

	@Test
	def void testInvalidVariabilityLevelsWrongType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID : { use is varLevel }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is observation, level = 2 }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"variability level ID has an inconsistent type with its match in the dataObj"
		)
	}

	@Test
	def void testInvalidVariabilityLevelsWrongType2(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is parameter, level = 2 }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"variability level ID has an inconsistent type with its match in the dataObj"
		)
	}

}