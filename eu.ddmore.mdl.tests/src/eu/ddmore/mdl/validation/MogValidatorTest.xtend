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

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import org.junit.Before

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MogValidatorTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
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
		'''.parse
	
		mcl.assertNoErrors	
	
		val validator = new MogValidator
		validator.mdlObj = mcl.modelObject
		validator.dataObj = mcl.dataObject
		validator.paramObj = mcl.modelObject
		validator.taskObj = mcl.modelObject
		
		validator.validateCovariates[errorCount++]
		
		assertEquals(0, errorCount)
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
		'''.parse
	
		mcl.assertNoErrors	
	
		val validator = new MogValidator
		validator.mdlObj = mcl.modelObject
		validator.dataObj = mcl.dataObject
		validator.paramObj = mcl.modelObject
		validator.taskObj = mcl.modelObject
		
		validator.validateCovariates[errorCount++]
		
		assertEquals(0, errorCount)
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
		'''.parse
	
		mcl.assertNoErrors	
	
		val validator = new MogValidator
		validator.mdlObj = mcl.modelObject
		validator.dataObj = mcl.dataObject
		validator.paramObj = mcl.modelObject
		validator.taskObj = mcl.modelObject
		
		validator.validateCovariates[t, m| if(t == MdlValidator::MODEL_DATA_MISMATCH) errorCount++]
		
		assertEquals(1, errorCount)
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
		'''.parse
	
		mcl.assertNoErrors	
	
		val validator = new MogValidator
		validator.mdlObj = mcl.modelObject
		validator.dataObj = mcl.dataObject
		validator.paramObj = mcl.modelObject
		validator.taskObj = mcl.modelObject
		
		validator.validateCovariates[t, m| if(t == MdlValidator::MODEL_DATA_MISMATCH) errorCount++]
		
		assertEquals(0, errorCount)
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
		'''.parse
	
		mcl.assertNoErrors	
	
		val validator = new MogValidator
		validator.mdlObj = mcl.modelObject
		validator.dataObj = mcl.dataObject
		validator.paramObj = mcl.modelObject
		validator.taskObj = mcl.modelObject
		
		validator.validateCovariates[t, m| if(t == MdlValidator::MODEL_DATA_MISMATCH) errorCount++]
		
		assertEquals(0, errorCount)
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
		'''.parse
	
		mcl.assertNoErrors	
	
		val validator = new MogValidator
		validator.mdlObj = mcl.modelObject
		validator.dataObj = mcl.dataObject
		validator.paramObj = mcl.modelObject
		validator.taskObj = mcl.modelObject
		
		validator.validateCovariates[t, m| if(t == MdlValidator::INCOMPATIBLE_TYPES) errorCount++]
		
		assertEquals(2, errorCount)
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
		'''.parse
	
		mcl.assertNoErrors	
	
		val validator = new MogValidator
		validator.mdlObj = mcl.modelObject
		validator.dataObj = mcl.dataObject
		validator.paramObj = mcl.modelObject
		validator.taskObj = mcl.modelObject
		
		validator.validateCovariates[t, m| if(t == MdlValidator::INCOMPATIBLE_TYPES) errorCount++]
		
		assertEquals(1, errorCount)
	}
		
}