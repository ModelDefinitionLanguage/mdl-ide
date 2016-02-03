package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclBuiltinFunctionValidationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidUnnamedFunction(){
		val mcl = '''bar = mdlObj {
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
	def void testValidHyperblicTrigFunctions(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			
			VARIABILITY_LEVELS{
			}
			
			MODEL_PREDICTION{
				A = tanh(3.2)
				B = cosh(3.2)
				C = sinh(3.2)
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidMaxFunction(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
				sd
				Rp1
				th1
				alpha
				other
		        kspow = max(0, sd/Rp1-th1)^alpha       # nmol/mL/day
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidMinFunction(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
				sd
				Rp1
				th1
				alpha
				other
		        kspow = min(0, sd/Rp1-th1)^alpha       # nmol/mL/day
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testFunctionWithNoArgs(){
		val mcl = '''bar = mdlObj {
			COVARIATES{
				other
				cov = ln()
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
			"Function 'ln' has the wrong number of arguments. Expected 1."
		)
	}

	@Test
	def void testUnnamedFunctionWithNamedArgs(){
		val mcl = '''bar = mdlObj {
			COVARIATES{
				other
				cov = ln(val=other)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Named argument function 'ln' is not recognised."
		)
	}

	@Test
	def void testFunctionWithTooManyArgs(){
		val mcl = '''bar = mdlObj {
			COVARIATES{
				other
				cov = ln(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
			"Function 'ln' has the wrong number of arguments. Expected 1."
		)
	}

	@Test
	def void testUnrecognisedFunction(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				other
				cov = blah(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
				
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			Diagnostic::LINKING_DIAGNOSTIC
		)
	}


	@Test
	def void testUnrecognisedFunctionSameSymbolName(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				other
				cov = cov(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equationDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'cov' contains an expression that refers to itself."
		)
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'cov' is not recognised."
		)
		mcl.assertError(MdlPackage::eINSTANCE.unnamedArgument,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument '1' expected value of type 'Undefined' but was 'ref:Real'."
		)
		mcl.assertError(MdlPackage::eINSTANCE.unnamedArgument,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument '2' expected value of type 'Undefined' but was 'Int'."
		)
//		mcl.assertNoErrors
//		assertEquals("expected error num", 4, mcl.validate.size)
	}

	@Test
	def void testUnrecognisedFunctionOtherSymbolName(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				other
				cov = foo(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'foo' is not recognised."
		)
		mcl.assertError(MdlPackage::eINSTANCE.unnamedArgument,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument '1' expected value of type 'Undefined' but was 'ref:Real'."
		)
		mcl.assertError(MdlPackage::eINSTANCE.unnamedArgument,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument '2' expected value of type 'Undefined' but was 'Int'."
		)
		assertEquals("expected error num", 3, mcl.validate.size)
	}

	@Test
	def void testUnrecognisedNamedFunctionOtherSymbolName(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				other
				cov = foo(arg1=other, arg2=2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Named argument function 'foo' is not recognised."
		)
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument 'arg1' expected value of type 'Undefined' but was 'ref:Real'."
		)
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument 'arg2' expected value of type 'Undefined' but was 'Int'."
		)
		assertEquals("expected error num", 3, mcl.validate.size)
	}

	@Test
	def void testValidNamedFunction(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
			}
			
			INDIVIDUAL_VARIABLES{
				Cl = linear(pop = POP_CL, fixEff = [{coeff=BETA_CL_WT, cov=logtWT}], ranEff = [ETA_CL])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidTypeNamedFunction(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, fixEff = BETA_CL_WT, ranEff = [ETA_CL])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument 'fixEff' expected value of type 'vector:Sublist:fixEffAtts' but was 'ref:Real'"
		)
	}

	@Test
	def void testInValidLhsTransFunction(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				exp(BETA_CL_WT) = 1
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.transformedDefinition,
			MdlValidator::INVALID_LHS_FUNC,
			"'exp' cannot be used as a transformation function on the LHS of an equation"
		)
	}

	@Test
	def void testInValidTypeUnnamedFunction(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = seq(true, 1, 2)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.unnamedArgument,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument '1' expected value of type 'Real' but was 'Boolean'"
		)
	}

	@Test
	def void testValidOverloadedNamedFunction(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
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
		val mcl = '''bar = mdlObj {
			IDV{T}
			VARIABILITY_LEVELS{
			}

			INDIVIDUAL_VARIABLES{
				foo = linear()
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'linear' is not recognised."
		)
	}

	@Test
	def void testNamedFunctionWithWrongArg(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, wrong = [{coeff=BETA_CL_WT, covariate=logtWT}], ranEff = [ETA_CL])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_FUNCTION_ARGUMENT_NAME,
			"Unrecognised argument 'wrong'."
		)
	}

	@Test
	def void testInvalidNamedFunctionWithMissingArg(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
			}
			INDIVIDUAL_VARIABLES{
				Cl = linear(pop = POP_CL, fixEff = [{coeff=BETA_CL_WT, cov=logtWT}])
			}
			INDIVIDUAL_VARIABLES{
			}
		}'''.parse
		
		assertEquals("expected error num", 1, mcl.validate.size)
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
	def void testInvalidNamedOverloadedFunctionWithMissingArg(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
				ID : { type is parameter, level=1 }
			}
			
			GROUP_VARIABLES{
				POP_CL
				BETA_CL_WT
			}
			RANDOM_VARIABLE_DEFINITION(level=ID){
				ETA_CL ~ Normal(var=POP_CL)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.namedFuncArguments,
			MdlValidator::MANDATORY_NAMED_FUNC_ARG_MISSING,
			"Mandatory argument 'mean' is missing."
		)
		assertEquals("expected error num", 1, mcl.validate.size)
		
	}

	@Test
	def void testInvalidNamedOverloadedFunctionWithMissingArg2(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
				ID : { level=1, type is parameter }
			}
			
			GROUP_VARIABLES{
				POP_CL
				BETA_CL_WT
			}
			RANDOM_VARIABLE_DEFINITION(level=ID){
				ETA_CL ~ Normal(mean=POP_CL)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.namedFuncArguments,
			MdlValidator::MANDATORY_NAMED_FUNC_ARG_MISSING,
			"Mandatory argument 'sd' is missing."
		)
		assertEquals("expected error num", 1, mcl.validate.size)
		
	}

	@Test
	def void testNamedFunctionWithoutOptionalArg(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
			}

			INDIVIDUAL_VARIABLES{
				Cl = linear(pop = POP_CL, ranEff = [ETA_CL])
			}
			INDIVIDUAL_VARIABLES{
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testNamedFunctionWithDuplicateArg(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				other
				POP_CL
				BETA_CL_WT
				ETA_CL
			}
			
			
			INDIVIDUAL_VARIABLES{
				Cl = linear(pop = POP_CL, pop=other, fixEff = {coeff=BETA_CL_WT, covariate=logtWT}, ranEff = ETA_CL)
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
		val mcl = '''bar = mdlObj {
			IDV{T}
			COVARIATES{
				other
				foo = linear(10, 20, 30)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::UNRECOGNIZED_FUNCTION_NAME,
			"Simple function 'linear' is not recognised."
		)
	}

	@Test
	def void testInValidNamedFunctionSublistWronAttribsType(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
			}
			INDIVIDUAL_VARIABLES{
				Cl = linear(pop = POP_CL, fixEff = [{co=BETA_CL_WT, cov=logtWT}], ranEff = ETA_CL)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument 'fixEff' expected value of type 'vector:Sublist:fixEffAtts' but was 'vector:Undefined'."
		)
	}
	

}