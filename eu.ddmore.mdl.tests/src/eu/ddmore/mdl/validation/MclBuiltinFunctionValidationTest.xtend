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

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclBuiltinFunctionValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidUnnamedFunction(){
		val mcl = '''bar = mdlobj {
			IDV{T}
			
			COVARIATES{
				other
				cov = ln(other)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testFunctionWithNoArgs(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				other
				cov = ln()
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
			"Function 'ln' has the wrong number of arguments. Expected 1."
		)
	}

	@Test
	def void testUnnamedFunctionWithNamedArgs(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				other
				cov = ln(val=other)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Named argument function 'ln' is not recognised."
		)
	}

	@Test
	def void testFunctionWithTooManyArgs(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				other
				cov = ln(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
			"Function 'ln' has the wrong number of arguments. Expected 1."
		)
	}

	@Test
	def void testUnrecognisedFunction(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				other
				cov = blah(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
				
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'blah' is not recognised."
		)
	}


	@Test
	def void testUnrecognisedFunctionSameSymbolName(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				other
				cov = cov(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'cov' is not recognised."
		)
	}

	@Test
	def void testUnrecognisedFunctionOtherSymbolName(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				other
				cov = foo(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'foo' is not recognised."
		)
	}

	@Test
	def void testValidNamedFunction(){
		val mcl = '''bar = mdlobj {
			
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, fixEff = {{coeff=BETA_CL_WT, covariate=logtWT}}, ranEff = ETA_CL)
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidTypeNamedFunction(){
		val mcl = '''bar = mdlobj {
			
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, fixEff = BETA_CL_WT, ranEff = ETA_CL)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument 'fixEff' expected value of type 'Mapping' but was 'Real'"
		)
	}

	@Test
	def void testValidOverloadedNamedFunction(){
		val mcl = '''bar = mdlobj {
			
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter }
			}
			
			RANDOM_VARIABLE_DEFINITION(level = ID){
				foo ~ Normal(mean=0, sd=1)
				foo2 ~ Normal(mean=0, var=1)
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testNamedFunctionWithNoArgs(){
		val mcl = '''bar = mdlobj {
			VARIABILITY_LEVELS{
			}

			INDIVIDUAL_VARIABLES{
				foo = linear()
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'linear' is not recognised."
		)
	}

	@Test
	def void testNamedFunctionWithWrongArg(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, wrong = {{coeff=BETA_CL_WT, covariate=logtWT}}, ranEff = ETA_CL)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_FUNCTION_ARGUMENT_NAME,
			"Unrecognised argument 'wrong'."
		)
	}

	@Test
	def void testInvalidNamedFunctionWithMissingArg(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, fixEff = {{coeff=BETA_CL_WT, covariate=logtWT}})
			}
			INDIVIDUAL_VARIABLES{
			}
		}'''.parse
		
//		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
//			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
//			"Function 'linear' has the wrong number of arguments. Expected 3."
//		)
		mcl.assertError(MdlPackage::eINSTANCE.namedFuncArguments,
			MdlValidator::MANDATORY_NAMED_FUNC_ARG_MISSING,
			"Mandatory argument 'ranEff' is missing."
		)
	}

	@Test
	def void testNamedFunctionWithoutOptionalArg(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, ranEff = ETA_CL)
			}
			INDIVIDUAL_VARIABLES{
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testNamedFunctionWithDuplicateArg(){
		val mcl = '''bar = mdlobj {
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				other
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, pop=other, fixEff = {{coeff=BETA_CL_WT, covariate=logtWT}}, ranEff = ETA_CL)
			}

		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::MULTIPLE_IDENTICAL_FUNC_ARG,
			"Function argument 'pop' occurs more than once."
		)
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::MULTIPLE_IDENTICAL_FUNC_ARG,
			"Function argument 'pop' occurs more than once."
		)
	}

	@Test
	def void testNamedFunctionWithUnnamedArgs(){
		val mcl = '''bar = mdlobj {
			COVARIATES{
				other
				foo = linear(10, 20, 30)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'linear' is not recognised."
		)
	}


}