package eu.ddmore.mdl.type

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.TransformedDefinition
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.mdl.RandomVariableDefinition

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclTypeProviderExpressionTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	extension MclTypeProvider th = new MclTypeProvider 

	@Test
	def void testValidNumericalEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = B + C - 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		
		val eqnStmt = (mcl.objects.head.blocks.last.statements.last as EquationDefinition)
		Assert::assertEquals(MclTypeProvider.PrimitiveType.Real, eqnStmt.expression.evalTypeFor)
	}
	
	@Test
	def void testValidWhenEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = when(B > 0 && false) B + C - 22, when(C == B || 22 < 0) B^180 otherwise 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		
		val eqnStmt = (mcl.objects.head.blocks.last.statements.last as EquationDefinition)
		Assert::assertEquals(MclTypeProvider.PrimitiveType.Real, eqnStmt.expression.evalTypeFor)
	}
	
	@Test
	def void testInvalidWhenEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B
				C
				A = when(B > 0 && "false") B + C - 22, when(C == B || 22 < 0) B^180 otherwise 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		
		val eqnStmt = (mcl.objects.head.blocks.last.statements.last as EquationDefinition)
		Assert::assertNull(eqnStmt.expression.evalTypeFor)
	}
	
	@Test
	def void testInValidFunctionArgumentExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				log(B) = 26
				C
				log(A) =  exp("B") + C - 22
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		
		val eqnStmt = (mcl.objects.head.blocks.last.statements.last as TransformedDefinition)
		Assert::assertNull(eqnStmt.expression.evalTypeFor)
	}
	
	@Test
	def void testValidFunctionEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
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
		
		val eqnStmt = (mcl.objects.head.blocks.last.statements.last as TransformedDefinition)
		Assert::assertEquals(MclTypeProvider.PrimitiveType.Real, eqnStmt.expression.evalTypeFor)
	}
	
	@Test
	def void testValidDistnFunctionExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
			VARIABILITY_LEVELS{
				ID : { type is parameter, level = 1 }
			}
		
			RANDOM_VARIABLE_DEFINITION(level=ID){
				foo ~ Normal(mean=0, sd=1)
			}
			
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		
		val eqnStmt = (mcl.objects.head.blocks.last.statements.last as RandomVariableDefinition)
		Assert::assertEquals(MclTypeProvider.PrimitiveType.Pdf, eqnStmt.distn.evalTypeFor)
	}
	
	@Test
	def void testValidVectorEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
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
		
		val eqnStmt = (mcl.objects.head.blocks.last.statements.last as TransformedDefinition)
		Assert::assertEquals(MclTypeProvider.PrimitiveType.Real, eqnStmt.expression.evalTypeFor)
	}
	
	@Test
	def void testInValidVectorEquationExpression(){
		val mcl = '''
		warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
			VARIABILITY_LEVELS{
			}
		
			
			MODEL_PREDICTION{
				B = 26
				C
				log(A) =  [ 20, true, B]
			}
			
		} # end of model object
		'''.parse
		
		mcl.assertNoErrors
		
		val eqnStmt = (mcl.objects.head.blocks.last.statements.last as TransformedDefinition)
		Assert::assertNull(eqnStmt.expression.evalTypeFor)
	}
	
}