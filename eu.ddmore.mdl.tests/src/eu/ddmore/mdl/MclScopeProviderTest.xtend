package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
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
import eu.ddmore.mdl.mdl.BlockStatementBody

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclScopeProviderTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	@Inject extension IScopeProvider
	
	val static CODE_SNIPPET = '''
warfarin_PK_SEXAGE_mdl2 = mdlobj {
	IDV{ T }
			
	COVARIATES{
		WT
		AGE
		SEX with {female,  male,  MISSING}
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}
	
	VARIABILITY_LEVELS{
	}

	STRUCTURAL_PARAMETERS {
		POP_FCL_FEM
	} # end STRUCTURAL_PARAMETERS
	
	GROUP_VARIABLES{
		FSEXCL = if(SEX == SEX.female) then POP_FCL_FEM  else 1
	}
} # end of model object

warfarin_PK_SEXAGE_mdl = mdlobj {
	IDV{ T }
			
	COVARIATES{
		WT
		AGE
		SEX with {female, male, MISSING}
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}

	STRUCTURAL_PARAMETERS {
		POP_FCL_FEM
	} # end STRUCTURAL_PARAMETERS
	
	VARIABILITY_LEVELS{
	}

	GROUP_VARIABLES{
		FSEXCL = if(SEX == SEX.female) then POP_FCL_FEM  else 1
		TLAG
	}
	
	MODEL_PREDICTION{
		DEQ{
			RATEIN = if(T >= TLAG) then GUT  else 0
			GUT : {deriv = (- RATEIN), init = 0, x0 = 0 }
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
		val grpBlk = CODE_SNIPPET.parse.objects.last.blocks.last as BlockStatement
		((grpBlk.body as BlockStatementBody).statements.last as EquationDefinition).expression =>  [
			assertScope(MdlPackage::eINSTANCE.symbolReference_Ref, "T, WT, AGE, SEX, logtWT, tAGE, POP_FCL_FEM, FSEXCL, TLAG, RATEIN, GUT, foo")
		]
		((grpBlk.body as BlockStatementBody).statements.last as EquationDefinition).expression =>  [
			assertScope(MdlPackage::eINSTANCE.categoryReference_Ref, "SEX.female, SEX.male, SEX.MISSING, warfarin_PK_SEXAGE_mdl2.SEX.female, warfarin_PK_SEXAGE_mdl2.SEX.male, warfarin_PK_SEXAGE_mdl2.SEX.MISSING, warfarin_PK_SEXAGE_mdl.SEX.female, warfarin_PK_SEXAGE_mdl.SEX.male, warfarin_PK_SEXAGE_mdl.SEX.MISSING")
		]
	}
	
	def private assertScope(EObject context, EReference reference, CharSequence expected){
		context.assertNoErrors
		Assert::assertEquals(expected.toString, context.getScope(reference).allElements.map[name].join(", "))
	}
	
}