package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Mcl
import static extension eu.ddmore.mdl.MclConverter.getString
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.validation.ValidationTestHelper

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclConverterTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	@Test
	def void testConverter1(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlobj (idv T) {
	MODEL_PREDICTION {
			KA
			GUT
			TLAG
			RATEIN = when(T >= TLAG) GUT * KA otherwise 0
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		mcl.assertNoErrors
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals(
"(GUT*KA) when T>=TLAG,
0 otherwise", eqn.expression.getString)
	}

	@Test
	def void testConverter2(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlobj (idv T) {
	MODEL_PREDICTION {
			KA
			GUT
			TLAG
			RATEIN = 10 * log(GUT/KA^2) / ( 1 - TLAG /(1 + sqrt(GUT)))
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		mcl.assertNoErrors
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals("10*log(GUT/KA^2)/(1-TLAG/(1+sqrt(GUT)))", eqn.expression.getString)
	}
	
	@Test
	def void testConverter3(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlobj (idv T) {
	MODEL_PREDICTION {
			RATEIN = 10 < 22 == true
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		mcl.assertNoErrors
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals("10<22==true", eqn.expression.getString)
	}

	@Test
	def void testConverter4(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlobj (idv T) {
	MODEL_PREDICTION {
			KA
			GUT
			TLAG
			RATEIN = when (10 < 22 == !true) 10 * log(GUT/KA^2) / ( 1 - TLAG /(1 + sqrt(GUT))),
			when(KA && GUT || false) when(T >= TLAG) GUT * KA otherwise 0
			otherwise INF
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		mcl.assertNoErrors
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals(
"(10*log(GUT/KA^2)/(1-TLAG/(1+sqrt(GUT)))) when 10<22==!true,
((GUT*KA) when T>=TLAG,
0 otherwise) when KA&&GUT||false,
INF otherwise", eqn.expression.getString)
	}
	
		@Test
	def void testConverter5(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlobj (idv T) {
	MODEL_PREDICTION {
			RATEIN = "doo"
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		mcl.assertNoErrors
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals("\"doo\"", eqn.expression.getString)
	}

		@Test
	def void testConverter6(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlobj (idv T) {
	MODEL_PREDICTION {
			KA
			GUT
			TLAG
			RATEIN = [ KA, 4, true, "help", log( GUT /KA^2)/( 1-TLAG / (1+sqrt(GUT))), 1 + 2 + 3 * 4 + 5]
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		mcl.assertNoErrors
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals("[KA,4,true,\"help\",log(GUT/KA^2)/(1-TLAG/(1+sqrt(GUT))),1+2+3*4+5]", eqn.expression.getString)
	}

	
}