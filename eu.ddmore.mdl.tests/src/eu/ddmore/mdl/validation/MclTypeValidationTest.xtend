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
import org.junit.Ignore
import org.junit.Test
import static org.junit.Assert.*
import org.junit.runner.RunWith
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.type.TypeSystemProvider

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclTypeValidationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	extension TypeSystemProvider tsp = new TypeSystemProvider

	@Test
	def void testValidNumericalEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
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
	def void testValidScientificNotationEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C = T
				A = B + C - 22.02e-5
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidScientificNotationNumberAboveRange(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C = T
				A = B + C - 22.02e555
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.realLiteral,
			MdlValidator::NUMBER_BEYOND_PRECISION_RANGE,
			"This real number is too large or small for MDL."
		)
	}
	
	@Ignore
	// this is not caught properly. Need to work on it.
	def void testInValidScientificNotationNumberBelowRange(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C = T
				A = B + C - 22.02e-555777
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.realLiteral,
			MdlValidator::NUMBER_BEYOND_PRECISION_RANGE,
			"This real number is too large or small for MDL."
		)
	}
	
	@Test
	def void testValidLargeIntegerBeyondRange(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C = T
				A = B + C - 2220000000000000000000000000000000000000000000000000000000
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.integerLiteral, Diagnostic::SYNTAX_DIAGNOSTIC)
	}
	
	@Test
	def void testValidIfEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = if(B > 0 && false) then B + C - 22 elseif(C == B || 22 < inf) then B^180 else 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		val blkBody = mcl.objects.head.blocks.last.body
		if(blkBody instanceof BlockStatementBody){
			val stmt = blkBody.statements.last
			if(stmt instanceof EquationDefinition){
				assertEquals("expected lhs type", stmt.typeFor, TypeSystemProvider::REAL_TYPE)
				assertEquals("expected rhs type", stmt.expression.typeFor, TypeSystemProvider::REAL_TYPE)
			}
			else fail("Test not formulated as expected")
		}
		else fail("Test not formulated as expected")
	}
	
	@Test
	def void testValidIfDerivExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				DEQ{
					B : { deriv = C, init = 33 }
				}
				C
				A = if(B > 0 && false) then B + C - 22 elseif(C == B || 22 < 0) then B^180 else 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		val blkBody = mcl.objects.head.blocks.last.body
		if(blkBody instanceof BlockStatementBody){
			val stmt = blkBody.statements.last
			if(stmt instanceof EquationDefinition){
				assertEquals("expected lhs type", stmt.typeFor, TypeSystemProvider::REAL_TYPE)
				assertEquals("expected rhs type", stmt.expression.typeFor, TypeSystemProvider::REAL_TYPE)
			}
			else fail("Test not formulated as expected")
		}
		else fail("Test not formulated as expected")
	}
	
	@Test
	def void testValidPiecewiseEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = piecewise{{ B + C - 22 when (B > 0 && false); B^180 when (C == B || 22 < inf); otherwise 22 }}
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		val blkBody = mcl.objects.head.blocks.last.body
		if(blkBody instanceof BlockStatementBody){
			val stmt = blkBody.statements.last
			if(stmt instanceof EquationDefinition){
				assertEquals("expected lhs type", stmt.typeFor, TypeSystemProvider::REAL_TYPE)
				assertEquals("expected rhs type", stmt.expression.typeFor, TypeSystemProvider::REAL_TYPE)
			}
			else fail("Test not formulated as expected")
		}
		else fail("Test not formulated as expected")
	}
	
	@Test
	def void testValidWithDerivExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				DEQ{
					B : { deriv = C, init = 33 }
				}
				C
				A = piecewise{{ B + C - 22 when (B > 0 && false); B^180 when (C == B || 22 < inf); otherwise 22 }}			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		val blkBody = mcl.objects.head.blocks.last.body
		if(blkBody instanceof BlockStatementBody){
			val stmt = blkBody.statements.last
			if(stmt instanceof EquationDefinition){
				assertEquals("expected lhs type", stmt.typeFor, TypeSystemProvider::REAL_TYPE)
				assertEquals("expected rhs type", stmt.expression.typeFor, TypeSystemProvider::REAL_TYPE)
			}
			else fail("Test not formulated as expected")
		}
		else fail("Test not formulated as expected")
	}
	
	@Test
	def void testValidWithNegativeDerivExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				DEQ{
					B : { deriv = -C, init = 33 }
					C : { deriv = 1, init = 33 }
				}
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidWithSelfRefereingDerivExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				DEQ{
					B : { deriv = B, init = 33 }
				}
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidWithVarLvlExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
				ID : { type is parameter, level=1 }
			}
		
			
			MODEL_PREDICTION{
				DEQ{
					B : { deriv = C, init = 33 }
				}
				C = ID
				A = if(B > 0 && false) then B + C - 22 elseif(C == B || 22 < 0) then B^180 else 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'deriv' expected value of type 'Real' but was 'ref:List:VarLevel'."
		)
		mcl.assertError(MdlPackage::eINSTANCE.additiveExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was ref:List:VarLevel."
		)
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was ref:List:VarLevel."
		)
	}
	
	@Test
	def void testInValidRelation(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
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
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = if(!true) then 0 else 1
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidUniopExpressionInvalid(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
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
			"Expected Boolean type, but was Int."
		)
	}
	
	@Test
	def void testValidUniopExpressionInvalidNumber(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
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
		warfarin_PK_SEXAGE_mdl = mdlObj {
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
		warfarin_PK_SEXAGE_mdl = mdlObj {
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
	def void testInvalidIfEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = if("false") then B + C - 22 elseif(C == B || 22 < 0) then B^180 else 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoError(Diagnostic.SYNTAX_DIAGNOSTIC)
		mcl.assertError(MdlPackage::eINSTANCE.ifExprPart,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Boolean type, but was String."
		)
	}
	
	@Test
	def void testInvalidWhenConditionExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = piecewise{{ B when "false"; B^180 when (C == B || 22 < 0); otherwise 22 }}
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoError(Diagnostic.SYNTAX_DIAGNOSTIC)
		mcl.assertError(MdlPackage::eINSTANCE.PWClause,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Boolean type, but was String."
		)
	}
	
	@Test
	def void testInvalidWhenValueExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = piecewise{{ "B" when A > 0; B^180 when (C == B || 22 < 0); otherwise 22 }}
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoError(Diagnostic.SYNTAX_DIAGNOSTIC)
		mcl.assertError(MdlPackage::eINSTANCE.PWClause,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was String."
		)
	}
	
	@Test
	def void testInvalidOtherwiseValueExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = piecewise{{ B when B < C; (C == B || 22 < 0) when B^180; otherwise "A String" }}
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoError(Diagnostic.SYNTAX_DIAGNOSTIC)
		mcl.assertError(MdlPackage::eINSTANCE.PWClause,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was Boolean."
		)
	}
	
	@Test
	def void testInValidFunctionArgumentExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
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
		
		mcl.assertError(MdlPackage::eINSTANCE.unnamedArgument,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument '1' expected value of type 'Real' but was 'String'."
		)
	}
	
	@Test
	def void testInValidNamedFunctionSublistArgumentType(){
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
				ETA_CL			}
			
			INDIVIDUAL_VARIABLES{
				Cl = linear(pop = POP_CL, fixEff = BETA_CL_WT, ranEff = ETA_CL)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument 'fixEff' expected value of type 'vector:Sublist:fixEffAtts' but was 'ref:Real'"
		)
	}
	
	@Test
	def void testInValidNamedFunctionSimpleArgumentType(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = false, fixEff = [{coeff=BETA_CL_WT, cov=logtWT}], ranEff = ETA_CL)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument 'pop' expected value of type 'Real' but was 'Boolean'"
		)
	}
	
	@Test
	def void testInValidNamedFunctionSublistNotVectType(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
				ID : { type is parameter, level = 1 }
			}
			
			
			STRUCTURAL_PARAMETERS{
				POP_CL
				BETA_CL_WT
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
				ETA_CL ~ Normal(mean=POP_CL, sd=1)
			}
			
			INDIVIDUAL_VARIABLES{
				Cl = linear(pop = POP_CL, fixEff = {coeff=BETA_CL_WT, cov=logtWT}, ranEff = ETA_CL)
			}
		}'''.parse
		
		mcl.assertNoErrors
//		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
//			MdlValidator::INCOMPATIBLE_TYPES,
//			"argument 'fixEff' expected value of type 'vector:Sublist:fixEffAtts' but was 'Sublist:fixEffAtts'"
//		)
	}
	
	@Test
	def void testInValidNamedFunctionSublistAttributeType(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
				logtWT
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				POP_CL
				BETA_CL_WT
				ETA_CL
				Cl = linear(pop = POP_CL, fixEff = [{coeff=0.0, cov=logtWT}], ranEff = ETA_CL)
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'coeff' expected value of type 'ref:Real' but was 'Real'."
		)
	}
	

	@Test
	def void testValidDistnFunctionExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
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
	def void testValidLogNormalDistnFunctionExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
				ID : { type is parameter, level = 1 }
			}
		
			STRUCTURAL_PARAMETERS{
				POP_V
				OMEGA_V
			}
		
			RANDOM_VARIABLE_DEFINITION(level=ID){
				foo ~ LogNormal(mean=ln(POP_V), var=OMEGA_V)
			}
			
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidVectorEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = 26
				C
				A=  [ 20, C, B]
			}
			
		} # end of model object
		'''.parse
		
//		mcl.assertError(MdlPackage::eINSTANCE.equationDefinition, MdlValidator::UNUSED_FEATURE)
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidMatrixEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B::Matrix
				A =  inverse(B)
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidExplicitMatrixTyping(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B::Matrix[[::Real]]
				A =  inverse(B)
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInvalidExplicitMatrixTyping(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B::Matrix[[::String]]
				A =  inverse(B)
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.unnamedArgument,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument '1' expected value of type 'matrix:Real' but was 'ref:matrix:String'."
		)
	}
	
	@Test
	def void testValidMatrixLiteralEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A =  [[ 20, C, B; 1, 2, 5.7 ]]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidMatrixCellIndexing(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.2, 2.4; 1, 2, 5.7 ]]
				C = ln(A[2, 2])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidMatrixSubMatrixIndexing1(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.2, 2.4; 1, 2, 5.7 ]]
				C = inverse(A[1:2, 2])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidMatrixSubMatrixIndexing2(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.2, 2.4; 1, 2, 5.7 ]]
				C = inverse(A[1:2, 2])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidMatrixSubMatrixIndexing3(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.2, 2.4; 1, 2, 5.7 ]]
				C = inverse(A[1:2, 2:3])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidMatrixSubMatrixIndexing4(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.2, 2.4; 1, 2, 5.7 ]]
				C = inverse(A[1:2, ])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidMatrixSubMatrixIndexing5(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.2, 2.4; 1, 2, 5.7 ]]
				C = inverse(A[, 2:3])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidMatrixNonIntegerIndexing1(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.2, 2.4; 1, 2, 5.7 ]]
				C = ln(A[1.3, 2])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.indexRange,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Index value must be an 'Int' type, but was 'Real'."
		)
	}
	
	@Test
	def void testValidMatrixNonIntegerIndexing2(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.2, 2.4; 1, 2, 5.7 ]]
				C = exp(A[1.3:3, 2])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.indexRange,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Index value must be an 'Int' type, but was 'Real'."
		)
	}
	
	@Test
	def void testInValidMatrixLiteralIncompatibleTypes(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C = "foo"
				A =  [[ 20, C, B; 1, 2, 5.7 ]]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.matrixElement,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Cell type 'ref:String' is incompatible with matrix type 'matrix:Real'."
		)
	}
	
	@Test
	def void testInValidMatrixInconsistentSize(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [[ 20, 2.1, 45; 1, 2]]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.matrixLiteral,
			MdlValidator::MATRIX_INCONSISTENT_ROW_SIZE,
			"The rows in this matrix must be the same size."
		)
	}
	
	@Test
	def void testValidEmptyMatrixLiteral(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A = [[]]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidEmptyVectorLiteral(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A = []
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidVectorEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = 26
				C
				A =  [ 20, true, B]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.vectorElement,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Element type 'Boolean' is incompatible with vector type 'Vector:Int'."
		)
	}
	
	@Test
	def void testInValidVectorEquationExpression2(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = 26.0
				C
				A =  [ 20, true, B]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.vectorElement,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Element type 'Boolean' is incompatible with vector type 'Vector:Real'."
		)
	}

	@Test
	def void testValidVectorSingleIndexing(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, 1.0, 2.0]
				C = ln(A[1])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidVectorNonIntegerSingleIndexing(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, 1.0, 2.0]
				C = ln(A[1.0])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.indexRange,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Index value must be an 'Int' type, but was 'Real'."
		)
	}
	
	@Test
	def void testInValidVectorNonIntegerSingleIndexing2(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, 1.0, 2.0]
				B = "S"
				C = ln(A[B])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.indexRange,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Index value must be an 'Int' type, but was 'ref:String'."
		)
	}
	
	@Ignore("This behaviour is not supported. Expressions evaluate to Real at the moment.")
	def void testValidIndexExpression1(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, 1.0, 2.0]
				B = 20
				C = ln(A[1 + 4 * B])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidIndexExpression2(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, 1.0, 2.0]
				B = 20
				C = ln(A[B])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	
	@Test
	def void testValidIndexExpression3(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, 1.0, 2.0]
				B = 20
				C = ln(A[toInt(1 + 4 * B)])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidVectorRangeIndexing(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, 1.0, 2.0]
				C = mean(A[1:2])
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	
	@Test
	def void testValidVectorTypeExplictDeclaration1(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A::Vector
				C = mean(A)
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	
	@Test
	def void testValidVectorTypeExplictDeclaration2(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A::Vector[::Real]
				C = mean(A)
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	
	@Test
	def void testInValidVectorTypeExplictDeclaration3(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A::Vector[::String]
				C = mean(A)
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.unnamedArgument,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument '1' expected value of type 'vector:Real' but was 'ref:vector:String'."
		)
	}
	
	
	@Test
	def void testInValidVectorNestedVectorEquationDefinition(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, [ 0 ], 26]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.vectorElement,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Element type 'vector:Int' is incompatible with vector type 'vector:Int'."
		)
	}
	
	@Test
	def void testInValidVectorNestedVectorRefEquationDefinition(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				A =  [ 20, C, 26]
				C = [ 1, 2, 3]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.vectorElement,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Element type 'ref:vector:Int' is incompatible with vector type 'vector:Int'."
		)
	}
	
	@Test
	def void testInValidInconsistentVectorLiteral(){
		val mcl = 	'''
		d1g=designObj{
			STUDY_DESIGN{}
			
			
			ADMINISTRATION{
				Conc
			}
			SAMPLING{
			 	pkwin1 : { type is simple, outcome=Conc, sampleTime = [0.5,2] }
				pkwin2 : { type is simple, outcome=Conc, numberSamples=0 }
			}
			DESIGN_SPACES{
				DS3 : { name=[pkwin1,pkwin2, 1.0], element is numberTimes, discrete=[1,2] }
			}
		}
	'''.parse
		mcl.assertError(MdlPackage::eINSTANCE.vectorElement,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Element type 'Real' is incompatible with vector type 'vector:List:SimpleSamplingList'."
		)
	}
	
	
	@Test
	def void testValidListVectorLiteral(){
		val mcl = 	'''
		d1g=designObj{
			DECLARED_VARIABLES{
				Conc
			}
			
			ADMINISTRATION{
			}
			
			DESIGN_PARAMETERS{
			}
			
			SAMPLING{
			 	pkwin1 : { type is simple, outcome=Conc, sampleTime = [0.5] }
				pkwin2 : { type is simple, outcome=Conc, numberSamples = [0], sampleTime = 0 }
			}
			DESIGN_SPACES{
				DS3 : { objRef=[pkwin1,pkwin2], element is numberTimes, discrete = [1] }
			}
		}
	'''.parse
		
		mcl.assertNoErrors
	}
	
	
	@Test
	def void testValidEnumExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX withCategories {male, female}
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
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX withCategories {male, female}
			}
		
			
			MODEL_PREDICTION{
				V = if(SEX == 2) then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Category:SEX type, but was Int."
		)
	}
	
	@Test
	def void testInValidInconsistentEnumExpressionSameCats(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX withCategories {male, female}
				SEX2 withCategories {male, female}
			}
		
			
			MODEL_PREDICTION{
				V = if(SEX == SEX2.male) then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Category:SEX type, but was Category:SEX2:male."
		)
	}
	
	@Test
	def void testInValidInconsistentEnumExpressionDiffCats(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX withCategories {male, female}
				SEX2 withCategories {male1, female2}
			}
		
			
			MODEL_PREDICTION{
				V = if(SEX == SEX2.female2) then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Category:SEX type, but was Category:SEX2:female2."
		)
	}
	
	@Test
	def void testInValidInconsistentEnumExpressionNoCatValues(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX withCategories {male, female}
				SEX2 withCategories {male1, female2}
			}
		
			
			MODEL_PREDICTION{
				V = if(SEX == SEX2) then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Category:SEX type, but was ref:Category:SEX2."
		)
	}
	
	@Test
	def void testInValidNumEnumExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX withCategories {male, female}
			}
		
			
			MODEL_PREDICTION{
				V = if(2 == SEX) then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was ref:Category:SEX."
		)
	}
	
	@Test
	def void testInValidEnumStringExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			COVARIATES{
				SEX withCategories {male, female}
			}
		
			
			MODEL_PREDICTION{
				V = if(SEX == "2") then 1 else 0
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.equalityExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Category:SEX type, but was String."
		)
	}
	
	@Test
	def void testValidBuiltinEnum(){
		val mcl = '''
		foo = dataObj {
			DATA_INPUT_VARIABLES{  anOther : { use is ignore } }

			SOURCE{
				foo : { file="aFile", inputFormat is nonmemFormat }
			}
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors()
	}
	
	@Test
	def void testValidIntVectorWithIntVector(){
		val mcl = '''
d1g=designObj{
	DECLARED_VARIABLES{
		Conc
		Effect
		Cmt
	}
	ADMINISTRATION{
		dose1 : {type is simple, input=Cmt, amount=100, doseTime=[0], duration=[1]} 
	}
	SAMPLING{
	}
	DESIGN_SPACES{
		DS1 : { objRef=[dose1], element is amount, discrete=[10,100,200] }
	}
	STUDY_DESIGN{
	}
}
		'''.parse
		
		mcl.assertNoErrors()
	}
	
	@Ignore("Seems to have been modified so does test mapping.")
	def void testValidMappingStructure(){
		val mcl = '''
d1g=designObj{
	DECLARED_VARIABLES{
		Conc; Cmt
	}
	ADMINISTRATION{
		dose1 : {type is simple, input=Cmt, amount=100, doseTime=[0], duration=[1]} 
	}
	SAMPLING{
	 	pkwin1 : { type is simple, outcome=Conc, sampleTime = [0.5,2] }
	}

	STUDY_DESIGN{
		set totalSize=100
		arm1 : {
			armSize=100,
			interventionSequence=[{
				admin=dose1
			}],
			samplingSequence=[{
				sample=pkwin1,
				start=0
				}
			]
		}
	}
}
		'''.parse
		
		mcl.assertNoErrors()
	}
	
	@Test
	def void testInvalidBuiltinEnum(){
		val mcl = '''
		foo = dataObj {
			DATA_INPUT_VARIABLES{  foo : { use is ignore } }

			SOURCE{
				foo2 : { file="aFile", inputFormat is foobar }
			}
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'inputFormat' expected value of type 'Enum:input' but was 'Undefined'"
		)
	}
	
	
	@Test
	def void testInvalidBuiltinEnumWrongType(){
		val mcl = '''
		foo = dataObj {
			DATA_INPUT_VARIABLES{ alt }

			SOURCE{
				foo : { file="aFile", inputFormat = alt }
			}
		} 
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'inputFormat' expected value of type 'Enum:input' but was 'ref:Real'"
		)
	}
	
	@Test
	def void testValidBoolExpressionAttributes(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				TIME : { use is idv }
				c2 : { use is amt, variable=D }
			}
			DATA_DERIVED_VARIABLES{
				DT : { use is doseTime, idvColumn = TIME, amtColumn = c2 }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidMappingExpressionAttributes(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D; E }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is cmt }
				AMT : { use is amt, define = {1 in CMT as D, 2 in CMT as E } }
			}
			DATA_DERIVED_VARIABLES{
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidMappingExpressionAttributes(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				AMT : { use is amt, define = D }
			}
			DATA_DERIVED_VARIABLES{
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'define' expected value of type 'Mapping' but was 'ref:Real'"
		)
	}

	@Test
	def void testInvalidNoVarRefAttributes(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				AMT : { use is amt, variable = 0.0 }
			}
			DATA_DERIVED_VARIABLES{
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'variable' expected value of type 'ref:Real' but was 'Real'."
		)
	}

	@Test
	def void testInvalidNonBoolExpressionAttributes(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				TIME : { use is idv }
			}
			DATA_DERIVED_VARIABLES{
				DT : { use is doseTime, idvColumn = TIME, amtColumn = D + 0 - 2 }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'amtColumn' expected value of type 'ref:List:Amt' but was 'Real'"
		)
	}

	@Test
	def void testInvalidNonRealExpressionAttributes(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				DEQ{
					B : { deriv = C > 0 && false, init = 33 }
				}
				C
				A = if(B > 0 && false) then B + C - 22 elseif(C == B || 22 < 0) then B^180 else 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'deriv' expected value of type 'Real' but was 'Boolean'"
		)
	}

	@Test
	def void testValidAsExpression(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ GUT}
	
	DATA_INPUT_VARIABLES {
		CMT : { use is cmt } 
		AMT : { use  is amt , define={ 1 in CMT as GUT } }
	}
	SOURCE {
	    foo: {file = "warfarin_conc_sex.csv",
        	inputFormat  is nonmemFormat} 
	} # end SOURCE
} # end data object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidLhsAsExpression(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ GUT; Y}
	
	DATA_INPUT_VARIABLES {
		CMT : { use is cmt } 
		AMT : { use  is amt , define={ Y in CMT as GUT } }
	}
	SOURCE {
	    foo: {file = "warfarin_conc_sex.csv",
        	inputFormat  is nonmemFormat} 
	} # end SOURCE
} # end data object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Int type, but was ref:Real."
		)
	}
	
	@Test
	def void testInValidRhsAsExpression(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ GUT; Y}
	
	DATA_INPUT_VARIABLES {
		CMT : { use is cmt } 
		AMT : { use  is amt , define={ 1 in CMT as 0.0 } }
	}
	SOURCE {
	    foo: {file = "warfarin_conc_sex.csv",
        	inputFormat  is nonmemFormat} 
	} # end SOURCE
} # end data object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected ref:Real type, but was Real."
		)
	}
	
	@Test
	def void testValidSimpleDataWhenExpression(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ GUT}
	
	DATA_INPUT_VARIABLES {
		SEX : { use is catCov withCategories {male when 0, female when 1} }
	}
	SOURCE {
	    foo: {file = "warfarin_conc_sex.csv",
        	inputFormat  is nonmemFormat} 
	} # end SOURCE
} # end data object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidRhsSimpleDataWhenExpression(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ GUT}
	
	DATA_INPUT_VARIABLES {
		SEX : { use is catCov withCategories {male when GUT, female when 1} }
	}
	SOURCE {
	    foo: {file = "warfarin_conc_sex.csv",
        	inputFormat  is nonmemFormat} 
	} # end SOURCE
} # end data object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Int type, but was ref:Real."
		)
	}
	
	@Test
	def void testValidDataMappingWhenExpression(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ Y; GUT; PCA withCategories {dead, alive}; OTHER withCategories {dead, alive} }
	
	DATA_INPUT_VARIABLES {
		DVID : { use  is dvid }
		DV : { use  is dv, define =  {
					1 in DVID as Y,
					2 in DVID as { PCA.dead when 1, PCA.alive when 2},
					3 in DVID as { OTHER.dead when 1, OTHER.alive when 2}
				}
			  }
	}

	SOURCE {
	    SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } 
	} # end SOURCE
} # end data object
		'''.parse
		
		mcl.assertNoErrors
	}


	@Test
	def void testValidExpectedRefTypeAttribute(){
		val mcl = '''
		foo = designObj{
			DECLARED_VARIABLES{
				Conc
				Effect
				Cmt
			}
			
			STUDY_DESIGN{}
			
			ADMINISTRATION{
				dose1 : {type is simple, input=Cmt, amount=100, doseTime=[0], duration=[1]} 
			}
		}
		'''.parse
		
		mcl.assertNoErrors
	}
	

	@Test
	def void testInValidExpectedRefTypeAttributeNoRef(){
		val mcl = '''
		foo = designObj{
			DECLARED_VARIABLES{
			}

			STUDY_DESIGN{}
			
			ADMINISTRATION{
				dose1 : {type is simple, input=0.0, amount=100, doseTime=[0], duration=[1]} 
			}
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'input' expected value of type 'ref:Real' but was 'Real'.")
	}
	

	
	@Ignore
	def void testValidObsWhenExpression(){
		val mcl = '''
		foo = mdlObj{
				IDV{T}
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				Prob0 
			}# end MODEL_PREDICTION
			
			OBSERVATION{
				PAIN : { type is categorical withCategories {mild when Prob0} }
			}
		}
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInValidRhsObsWhenExpression(){
		val mcl = '''
		bar = dataObj {
			DECLARED_VARIABLES{ D; E }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is cmt }
				AMT : { use is amt, define = {1.0 in CMT as D, 2 in CMT as E } }
			}
			DATA_DERIVED_VARIABLES{
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Int type, but was Real."
		)
	}

	@Test
	def void testInValidIncompatibleElseExpression(){
		val mcl = '''
		foo = mdlObj{
			VARIABILITY_LEVELS{
			}

			INDIVIDUAL_VARIABLES{
				BSA = if(true) then 1.0 else false
			}
			
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.elseClause,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was Boolean."
		)
	}

	@Test
	def void testInValidIncompatibleIfElseExpression(){
		val mcl = '''
		foo = mdlObj{
			VARIABILITY_LEVELS{
			}

			INDIVIDUAL_VARIABLES{
				BSA = if(true) then 1.0 elseif(false) then false else 2.0
			}
			
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.elifClause,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was Boolean."
		)
	}

	@Test
	def void testInValidIncompatibleIfExpression(){
		val mcl = '''
		foo = mdlObj{
			VARIABILITY_LEVELS{
			}

			INDIVIDUAL_VARIABLES{
				BSA = if(true) then true elseif(false) then 3.0 else 2.0
			}
			
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.ifClause,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was Boolean."
		)
	}

	@Test
	def void testValidDerivativeSuperType(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				V : { deriv = -V }
				COMPARTMENT{
					IN : { type is direct, to = V }
				}
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidCompartmentSuperType(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				V : { deriv = -V }
				COMPARTMENT{
					:: { type is elimination, from = V, cl=10 }
				}
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidSamplingSuperTypes(){
		val mcl = '''
d1g=designObj{
	DECLARED_VARIABLES{
		Conc
		Effect
		Cmt
	}
	ADMINISTRATION{
		dose1 : {type is simple, input=Cmt, amount=100, doseTime=[0], duration=[1]} 
	}
	SAMPLING{
	 	pkwin1 : { type is simple, outcome=Conc, sampleTime = [0.5,2] }
		pkwin2 : { type is simple, outcome=Conc, numberSamples=[0], sampleTime = 30 }
		pkwin3 : { type is simple, outcome=Conc, sampleTime = [30]}
	# Create sampling for PK and PD by stringing windows
		sampPK : {  type is combi, combination=[pkwin1,pkwin2] }
		sampPD : { type is combi, combination=[pkwin1,pkwin3] }
	# Create sampling for both responses by combining the PK and PD samples. Need to specify start/end so that the sampling are simultaneous
		sampPKPD : {type is combi, combination=[sampPK,sampPD]}
	}
}		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidDesignSpaceSuperTypes(){
		val mcl = '''
d1g=designObj{
	DECLARED_VARIABLES{
		Conc
		Effect
		Cmt
	}
	ADMINISTRATION{
		dose1 : {type is simple, input=Cmt, amount=100, doseTime=[0], duration=[1]} 
	}
	SAMPLING{
	 	pkwin1 : { type is simple, outcome=Conc, sampleTime = [0.5,2] }
		pkwin2 : { type is simple, outcome=Conc, numberSamples=[0], sampleTime=0 }
	# Create sampling for PK and PD by stringing windows
		sampPK : {  type is combi, combination=[pkwin1,pkwin2] }
		sampPD : { type is combi, combination=[pkwin1,pkwin2] }
	}
}		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidVectorArrayInferred(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlObj {
			IDV{ T }

			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				C
				A =  C
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testValidVectorAttributeInferred(){
		val mcl = '''
d1g=designObj{
	DECLARED_VARIABLES{
		Conc
		Effect
		Cmt
	}
	ADMINISTRATION{
		dose1 : { type is simple, input=Cmt, amount=100, doseTime=0, duration=1} 
	}
	SAMPLING{
		pkwin2 : { type is simple, outcome=Conc, numberSamples=0, sampleTime=30 }
	}
}		'''.parse
		
		mcl.assertNoErrors
	}

	@Ignore("Not supported by language now.")
	def void testValidGenericEnumValueAttribute(){
		val mcl = '''
d1g=designObj{
	DECLARED_VARIABLES{
		Conc
		Effect
		Cmt
	}
	ADMINISTRATION{
		dose1 : {type is simple, input=Cmt, amount=100, doseTime=0, duration=1} 
	}
	
	COVARIATES{
		catCovar withCategories { foo, bar }
	}
	
	DESIGN_SPACES{
		DS1 : { objRef=catCovar.foo, element is catCov, range=[0.1, 1.0] }
	}
}		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidCompartmentUsage(){
		val mcl = '''
warfarin_PK_Compartments_mdl = mdlObj {
	IDV { T }

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter  }
		DV : { level=1, type is observation }
	}

	
	INDIVIDUAL_VARIABLES {
	    CL = 1
	    V = 1
	    KA = 1
	    TLAG = 1
	    F1 = 1
	} # end INDIVIDUAL_VARIABLES

	MODEL_PREDICTION{
	  COMPARTMENT {
		INPUT_KA:   {type is depot, modelCmt=1, to=TESTVAR, ka=KA, tlag=TLAG, finput=F1}
	   }# end COMPARTMENT
      TESTVAR = 1
	} # end MODEL_PREDICTION

} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'to' expected value of type 'ref:ListSubtype:DosingTarget' but was 'ref:Int'."
		)
	}

	@Test
	def void testVectorOfVector(){
		val mcl = '''
testprior = priorObj{

	NON_CANONICAL_DISTRIBUTION{
		INPUT_PRIOR_DATA{
			bins_k::Vector[::Real]
			bins_v::Vector[::Real]
			bins_k_v= toMatrixByRow([bins_k, bins_v])
		}
	}
}
		'''.parse
		
		mcl.assertNoErrors
	}


	@Test
	def void testValidFunctionRef(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DATA_INPUT_VARIABLES {
		W : { use is covariate, interp=&linearInterp } 
	}
	SOURCE {
	    foo: {file = "warfarin_conc_sex.csv",
        	inputFormat  is nonmemFormat} 
	} # end SOURCE
} # end data object
		'''.parse
		
		mcl.assertNoErrors
	}
	
	@Test
	def void testInvalidFunctionRef(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DATA_INPUT_VARIABLES {
		W : { use is covariate, interp=&ln } 
	}
	SOURCE {
	    foo: {file = "warfarin_conc_sex.csv",
        	inputFormat  is nonmemFormat} 
	} # end SOURCE
} # end data object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"attribute 'interp' expected value of type 'ref:Function(Real,Real,Real,Real,Real)::Real' but was 'ref:Function(Real)::Real'."
		)
	}
	
}


