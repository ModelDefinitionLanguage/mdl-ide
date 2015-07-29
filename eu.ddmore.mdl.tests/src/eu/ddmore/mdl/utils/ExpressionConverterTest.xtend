package eu.ddmore.mdl.utils

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.PropertyStatement
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class ExpressionConverterTest {
	@Inject extension ParseHelper<Mcl>
//	@Inject extension ValidationTestHelper
	
	@Test
	def void testConverter1(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlobj (idv T) {
	MODEL_PREDICTION {
			KA
			GUT
			TLAG
			RATEIN = if(T >= TLAG) then GUT * KA else 0
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals(
"(GUT*KA) when T>=TLAG,
0 otherwise", eqn.expression.convertToString)
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
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals("10*log(GUT/KA^2)/(1-TLAG/(1+sqrt(GUT)))", eqn.expression.convertToString)
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
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals("10<22==true", eqn.expression.convertToString)
	}

	@Test
	def void testConverter4(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlobj (idv T) {
	MODEL_PREDICTION {
			KA
			GUT
			TLAG
			RATEIN = if (10 < 22 == !true) then 10 * log(GUT/KA^2) / ( 1 - TLAG /(1 + sqrt(GUT)))
			elseif(KA && GUT || false) then if(T >= TLAG) then GUT * KA else 0
			else INF
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals(
"(10*log(GUT/KA^2)/(1-TLAG/(1+sqrt(GUT)))) when 10<22==!true,
((GUT*KA) when T>=TLAG,
0 otherwise) when KA&&GUT||false,
INF otherwise", eqn.expression.convertToString)
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
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals("\"doo\"", eqn.expression.convertToString)
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
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals("[KA,4,true,\"help\",log(GUT/KA^2)/(1-TLAG/(1+sqrt(GUT))),1+2+3*4+5]", eqn.expression.convertToString)
	}

		@Test
	def void testConverter7(){
		val mcl =  '''
warfarin_PK_ODE_mdl = dataobj {
	DATA_INPUT_VARIABLES{
	} # end MODEL_PREDICTION
	SOURCE{
		set file is nonmem
	}
	
} # end of model object
		'''.parse
		val eqn = mcl.objects.head.blocks.last.statements.last as PropertyStatement
		Assert::assertEquals("nonmem", eqn.properties.head.expression.convertToString)
	}

	
}