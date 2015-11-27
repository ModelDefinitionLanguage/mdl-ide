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
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.validation.ListDefinitionProvider
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.NamedFuncArguments

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclScopeProviderTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	@Inject extension IScopeProvider
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	
	val static CODE_SNIPPET = '''
warfarin_PK_SEXAGE_mdl2 = mdlObj {
	IDV{ T }
			
	COVARIATES{
		WT
		AGE
		SEX withCategories {female,  male,  MISSING}
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}
	
	VARIABILITY_LEVELS{
	}

	STRUCTURAL_PARAMETERS {
		POP_FCL_FEM
	} # end STRUCTURAL_PARAMETERS
	
	GROUP_VARIABLES{
		FSEXCL = piecewise(piece=[{condition=(SEX == SEX.female), value=POP_FCL_FEM}], otherwise=1)
	}
} # end of model object

warfarin_PK_SEXAGE_mdl = mdlObj {
	IDV{ T }
			
	COVARIATES{
		WT
		AGE
		SEX withCategories {female, male, MISSING}
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}

	STRUCTURAL_PARAMETERS {
		POP_FCL_FEM
	} # end STRUCTURAL_PARAMETERS
	
	VARIABILITY_LEVELS{
	}

	GROUP_VARIABLES{
		FSEXCL = piecewise(piece=[{condition=(SEX == SEX.female), value=POP_FCL_FEM}], otherwise=1)
		TLAG
	}
	
	MODEL_PREDICTION{
		DEQ{
			RATEIN = piecewise(piece=[{condition=(T >= TLAG), value=GUT}], otherwise=0)
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
			assertScope(MdlPackage::eINSTANCE.categoryValueReference_Ref, "SEX.female, SEX.male, SEX.MISSING, warfarin_PK_SEXAGE_mdl2.SEX.female, warfarin_PK_SEXAGE_mdl2.SEX.male, warfarin_PK_SEXAGE_mdl2.SEX.MISSING, warfarin_PK_SEXAGE_mdl.SEX.female, warfarin_PK_SEXAGE_mdl.SEX.male, warfarin_PK_SEXAGE_mdl.SEX.MISSING")
		]
	}
	
	@Test
	def void testExpectedCategoryDescriptions(){
		val mcl = '''
obj1 = mdlObj{

   VARIABILITY_LEVELS{
	DV : { level=1, type is observation }
   }


   MODEL_PREDICTION{
	  P1 = 1
   }# end MODEL_PREDICTION

   OBSERVATION{
     Y : { type is discrete withCategories { success, fail }, distn = Bernoulli(category=Y.success, probability=P1)}
   }# end ESTIMATION
} # end of model object
		'''.parse
		
		val blkBody = (mcl.objects.head.blocks.last.body as BlockStatementBody)
		val catStmt = blkBody.statements.last as ListDefinition
		val funCall = catStmt.list.getAttributeExpression('distn') as BuiltinFunctionCall
		val argExpr = (funCall.argList as NamedFuncArguments).arguments.head.expression 
		argExpr.assertScope(MdlPackage::eINSTANCE.symbolReference_Ref, "DV, P1, Y")
	}
	
	@Test
	def void testExpectedCategoryDescriptionCatRefs(){
		val mcl = '''
obj1 = mdlObj{

   VARIABILITY_LEVELS{
	DV : { level=1, type is observation }
   }


   MODEL_PREDICTION{
	  P1 = 1
   }# end MODEL_PREDICTION

   OBSERVATION{
     Y : { type is discrete withCategories { success, fail }, distn = Bernoulli(category=success, probability=P1)}
   }# end ESTIMATION
} # end of model object
		'''.parse
		
		val blkBody = (mcl.objects.head.blocks.last.body as BlockStatementBody)
		val catStmt = blkBody.statements.last as ListDefinition
		val funCall = catStmt.list.getAttributeExpression('distn') as BuiltinFunctionCall
		val argExpr = (funCall.argList as NamedFuncArguments).arguments.head.expression 
		argExpr.assertScope(MdlPackage::eINSTANCE.categoryValueReference_Ref, "success, fail, Y.success, Y.fail, obj1.Y.success, obj1.Y.fail")
	}
	
	@Test
	def void testExpectedCategoryQualifiedLinking(){
		val mcl = '''
obj1 = mdlObj{
	IDV{T}

   VARIABILITY_LEVELS{
	DV : { level=1, type is observation }
   }


   MODEL_PREDICTION{
	  P1 = 1
   }# end MODEL_PREDICTION

   OBSERVATION{
     Y : { type is discrete withCategories { success, fail }, distn = Bernoulli(category=Y.success, probability=P1)}
   }# end ESTIMATION
} # end of model object
		'''.parse
		mcl.assertNoErrors
		
	}
	
	def private assertScope(EObject context, EReference reference, CharSequence expected){
		Assert::assertEquals(expected.toString, context.getScope(reference).allElements.map[name].join(", "))
	}
	
}