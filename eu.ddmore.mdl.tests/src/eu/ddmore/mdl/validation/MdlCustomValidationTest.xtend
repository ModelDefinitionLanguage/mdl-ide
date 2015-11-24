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
			IDV{T}
			
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
			IDV{T}
			
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
			IDV{T}
			
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
			IDV{T}
			
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
			IDV{T}
			
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
	}

	@Test
	def void testInvalidCategorialCovInEquality(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
				sex withCategories { m, f }
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				TSEX = if(sex <= sex.f) then 1 else 0
			}
			
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = general(grp=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.relationalExpression,
			MdlValidator::INVALID_ENUM_RELATION_OPERATOR,
			"Cannot use inequality operators with categorical types"
		)
	}

	@Test
	def void testValidDataDefinitions(){
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
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidMissingAmtDataDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y; INPUT }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      TIME: {use is idv}
      TRT: {use is covariate}
      AMT: {use is ignore }
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
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse

		mcl.assertError(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'amt' is required by this column definition with 'use is rate'.")
		mcl.assertError(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'amt' is required by this column definition with 'use is ss'.")
		mcl.assertError(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'amt' is required by this column definition with 'use is addl'.")
		mcl.assertError(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'amt' is required by this column definition with 'use is ii'.")
	}

}