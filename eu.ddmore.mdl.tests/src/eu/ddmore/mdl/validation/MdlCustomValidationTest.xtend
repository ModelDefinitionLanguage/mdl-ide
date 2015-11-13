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
class MdlCustomValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	


	@Test
	def void testIncorrectUseOfTransFunction(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				logit(BETA_CL_WT) = ln(22)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.transformedDefinition,
			MdlValidator::INVALID_LHS_FUNC,
			"Use of a transformation function on the LHS of the equation is not permitted in this context"
		)
	}

	@Test
	def void testInconsistentTransFunctions(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				logit(BETA_CL_WT) = linear(trans is ln, pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.enumPair,
			MdlValidator::INVALID_LHS_FUNC,
			"transformation used on LHS ('logit') must match the RHS ('ln')"
		)
	}

	@Test
	def void testInconsistentNoRhsFunctions(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				probit(BETA_CL_WT) = linear(pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.transformedDefinition,
			MdlValidator::INVALID_LHS_FUNC,
			"Use of a transformation function on the LHS of the equation is not permitted in this context"
		)
	}

	@Test
	def void testInconsistentNoLhsFunctions(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = linear(trans is ln, pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INVALID_LHS_FUNC,
			"no transformation used on the LHS, so cannot use on the RHS of the equation"
		)
	}

	@Test
	def void testValidLhsTransLinearDefn(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				ln(BETA_CL_WT) = linear(trans is ln, pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidLhsNoneTransLinearDefn(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = linear(trans is none, pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidLhsNoTransLinearDefn(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = linear(pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidLhsTransGeneralDefn(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				ln(BETA_CL_WT) = general(grp=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidLhsNoTransGeneralDefn(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = general(grp=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

}