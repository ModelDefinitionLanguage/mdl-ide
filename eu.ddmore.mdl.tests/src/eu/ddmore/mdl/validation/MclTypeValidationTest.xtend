package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.validation.MdlValidator
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclTypeValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidNumericalEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C = T
				A = B + C - 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidWhenEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = if(B > 0 && false) then B + C - 22 elseif(C == B || 22 < 0) then B^180 else 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidRelation(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = C == "B"
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was String."
		)
	}
	
	@Test
	def void testValidUniopExpressionOK(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = if(!true) then 0 
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidUniopExpressionInvalid(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = !0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.unaryExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Boolean type, but was Real."
		)
	}
	
	@Test
	def void testValidUniopExpressionInvalidNumber(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = +true
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.unaryExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was Boolean."
		)
	}
	
	@Test
	def void testValidUniopExpressionInvalidMalformed(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = +
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.unaryExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was Undefined."
		)
	}
	
	@Test
	def void testValidUniopExpressionInvalidBool(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = !
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.unaryExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Boolean type, but was Undefined."
		)
	}
	
	@Test
	def void testInvalidWhenEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = if(B > 0 && "false") then B + C - 22 if(C == B || 22 < 0) then B^180 else 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.andExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Boolean type, but was String."
		)
	}
	
	@Ignore
	def void testInValidFunctionArgumentExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				log(B) = 26
				C
				log(A) =  exp("B") + C - 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.unnamedFuncArguments,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was String."
		)
	}
	
	@Test
	def void testValidFunctionEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				log(B) = 26
				C
				log(A) =  exp(B) + C - 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidDistnFunctionExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
				ID : { type is parameter, level = 1 }
			}
		
			RANDOM_VARIABLE_DEFINITION(level=ID){
				foo ~ Normal(mean=0, sd=1)
			}
			
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidVectorEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = 26
				C
				log(A) =  [ 20, C, B]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidVectorEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = 26
				C
				log(A) =  [ 20, true, B]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.vectorContent,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was Boolean."
		)
	}
	
	@Test
	def void testValidEnumExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX with {male, female}
			}
		
			
			MODEL_PREDICTION{
				V = if(SEX == SEX.male) then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidEnumNumExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX with {male, female}
			}
		
			
			MODEL_PREDICTION{
				V = if(SEX == 2) then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Enum type, but was Real."
		)
	}
	
	@Test
	def void testInValidNumEnumExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX with {male, female}
			}
		
			
			MODEL_PREDICTION{
				V = if(2 == SEX) then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was Enum."
		)
	}
	
	@Test
	def void testInValidEnumStringExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX with {male, female}
			}
		
			
			MODEL_PREDICTION{
				V = if(SEX == "2") then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Enum type, but was String."
		)
	}
	
}