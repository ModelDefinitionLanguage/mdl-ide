package eu.ddmore.mdl.utils

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.ValuePair
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

import static extension eu.ddmore.mdl.utils.MdlExpressionConverter.convertToString

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MdlExpressionConverterTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension MdlUtils mu = new MdlUtils

	@Test
	def void testValidExpressionSyntax(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV { T }
	
	VARIABILITY_LEVELS{}
	
	
	MODEL_PREDICTION {
			KA
			GUT
			TLAG = GUT-1
			RATEIN = if(T >= TLAG) then GUT * KA else 0
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		
		mcl.assertNoErrors
	}

	
	@Test
	def void testConverter1(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{T}
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
			"if (T>=TLAG) then GUT*KA else 0",
			eqn.expression.convertToString.replace("\r\n", "\n")) // The replace() is an attempt to cater for Windows/Mac line ending differences
	}

	@Test
	def void testConverter2(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{T}
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
warfarin_PK_ODE_mdl = mdlObj {
	IDV{T}
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
warfarin_PK_ODE_mdl = mdlObj {
	IDV{T}
	MODEL_PREDICTION {
			KA
			GUT
			TLAG
			RATEIN = if (10 < 22 == !true) then 10 * log(GUT/KA^2) / ( 1 - TLAG /(1 + sqrt(GUT)))
			elseif(KA && GUT || false) then if(T >= TLAG) then GUT * KA else 0
			else inf
	} # end MODEL_PREDICTION
	
} # end of model object
		'''.parse
		
		val eqn = mcl.objects.head.blocks.last.statements.last as EquationDefinition
		Assert::assertEquals(
			"if (10<22==!true) then 10*log(GUT/KA^2)/(1-TLAG/(1+sqrt(GUT))) elseif (KA&&GUT||false) then if (T>=TLAG) then GUT*KA else 0 else inf",
			eqn.expression.convertToString.replace("\r\n", "\n")) // The replace() is an attempt to cater for Windows/Mac line ending differences
	}
	
	@Test
	def void testConverter5(){
		val mcl =  '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{T}
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
warfarin_PK_ODE_mdl = mdlObj {
	IDV{T}
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
warfarin_PK_ODE_mdl = dataObj {
	DATA_INPUT_VARIABLES{  foo : { use is ignore } } # end MODEL_PREDICTION
	SOURCE{
		file : { file="foo" }
	}
	
} # end of model object
		'''.parse
		val eqn = mcl.objects.head.blocks.last.statements.last as ListDefinition
		Assert::assertEquals("\"foo\"", (eqn.firstAttributeList.attributes.head as ValuePair).expression.convertToString)
	}

	
}
