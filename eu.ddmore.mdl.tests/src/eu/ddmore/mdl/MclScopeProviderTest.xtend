package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.EquationDeclaration
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ObjectBlock
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.scoping.IScopeProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.mdl.ModelPredictionBlockStatement

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclScopeProviderTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	@Inject extension IScopeProvider
	
	val static CODE_SNIPPET = '''
warfarin_PK_SEXAGE_mdl2 = mdlobj(idv T) {
	COVARIATES{
		WT
		AGE
		SEX : { type=categorical(female, male, MISSING) }
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}

	STRUCTURAL_PARAMETERS {
		POP_FCL_FEM
	} # end STRUCTURAL_PARAMETERS
	
	GROUP_VARIABLES{
		FSEXCL = when(SEX == female) POP_FCL_FEM  otherwise 1
	}
} # end of model object

warfarin_PK_SEXAGE_mdl = mdlobj(idv T) {
	COVARIATES{
		WT
		AGE
		SEX : { type=categorical(female, male, MISSING) }
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}

	STRUCTURAL_PARAMETERS {
		POP_FCL_FEM
	} # end STRUCTURAL_PARAMETERS
	
	GROUP_VARIABLES{
		FSEXCL = when(SEX == female) POP_FCL_FEM  otherwise 1
		TLAG
	}
	
	MODEL_PREDICTION{
		DEQ{
			RATEIN = when(T >= TLAG) GUT  otherwise 0
			GUT : { deriv =(- RATEIN), init = 0, x0 = 0 }
		}
	}
	
	OBSERVATION{
		foo = 1
	}
} # end of model object
'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Test
	def void testExpectedEObjectDescriptions(){
		val grpBlk = CODE_SNIPPET.parse.objects.last.blocks.last as ObjectBlock
		(grpBlk.statements.last as EquationDeclaration).expression =>  [
			assertScope(MdlPackage::eINSTANCE.symbolReference_Ref, "WT, AGE, SEX, female, male, MISSING, logtWT, tAGE, POP_FCL_FEM, FSEXCL, TLAG, RATEIN, GUT, foo, T")
		]
	}
	
	def private assertScope(EObject context, EReference reference, CharSequence expected){
		context.assertNoErrors
		Assert::assertEquals(expected.toString, context.getScope(reference).allElements.map[name].join(", "))
	}
	
}