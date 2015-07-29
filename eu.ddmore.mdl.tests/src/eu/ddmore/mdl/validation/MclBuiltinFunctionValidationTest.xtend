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
	def void testValidUnanmedFunction(){
		val mcl = '''bar = mdlobj {
			IDV{T}
			
			COVARIATES{
				other
				cov = log(other)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testFunctionWithNoArgs(){
		val mcl = '''bar = mdlobj(idv T) {
			COVARIATES{
				other
				cov = log()
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
			"Function 'log' has the wrong number of arguments. Expected 1."
		)
	}

	@Test
	def void testFunctionWithTooManyArgs(){
		val mcl = '''bar = mdlobj(idv T) {
			COVARIATES{
				other
				cov = log(other, 2)
				foo = exp(22)
			}
			
			VARIABILITY_LEVELS{
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.builtinFunctionCall,
			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
			"Function 'log' has the wrong number of arguments. Expected 1."
		)
	}

	@Test
	def void testUnrecognisedFunction(){
		val mcl = '''bar = mdlobj(idv T) {
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
			"A function of name 'blah' is not recognised."
		)
	}


	@Test
	def void testUnrecognisedFunctionSameSymbolName(){
		val mcl = '''bar = mdlobj(idv T) {
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
			"A function of name 'cov' is not recognised."
		)
	}

	@Test
	def void testUnrecognisedFunctionOtherSymbolName(){
		val mcl = '''bar = mdlobj(idv T) {
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
			"A function of name 'foo' is not recognised."
		)
	}

}