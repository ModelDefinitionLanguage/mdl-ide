package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclStatementValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidStatementCategories(){
		val mcl = '''foo = mdlObj {
			IDV{T}

			VARIABILITY_LEVELS{
			}

			COVARIATES{
				SEX withCategories { male, female}
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidWithMappingStatementCategories(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}

			COVARIATES{
				SEX withCategories { male when 0, female when 10}
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::INCORRECT_STATEMENT_CONTEXT,
			"Cannot use category mappings in a statement."
		)
	}


	@Test
	def void testInValidStatementNoCategories(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}

			COVARIATES{
				SEX withCategories { }
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoricalDefinitionExpr,
			Diagnostic::SYNTAX_DIAGNOSTIC)
	}


	@Test
	def void testInValidWithStatementMissingCategories(){
		val mcl = '''foo = mdlObj {
			VARIABILITY_LEVELS{
			}

			COVARIATES{
				SEX withCategories
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatementBody,
			Diagnostic::SYNTAX_DIAGNOSTIC)
	}


	@Test
	def void testValidCategoryDefnInList(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo
			}# end MODEL_PREDICTION
			
			OBSERVATION{
				PAIN : { type is categorical withCategories {mild when foo} }
			}
		}
		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidCategoryDefnInList(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}

			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo
			}# end MODEL_PREDICTION
			
			OBSERVATION{
				PAIN : { type is categorical withCategories {mild} }
			}
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::INCORRECT_LIST_CONTEXT,
			"A category definition must have a mapping in this context."
		)
	}

	@Test
	def void testValidCategoryDefnWithNoMappingsInList(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo
			}# end MODEL_PREDICTION
			
			OBSERVATION{
				PAIN : { type is discrete withCategories {mild}, distn=Bernoulli(category = PAIN.mild, probability=foo) }
			}
		}
		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidDiscreteCategoryDefnWithNoMappingsInList(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}
			VARIABILITY_LEVELS{
			}

			COVARIATES{
				FOO withCategories { baa }
			}

			MODEL_PREDICTION{
				foo
			}# end MODEL_PREDICTION
			
			OBSERVATION{
				PAIN : { type is discrete, distn=Bernoulli(category = FOO.baa, probability=foo) }
			}
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.enumPair,
			MdlValidator::INVALID_CATEGORY_DEFINITION,
			"Category definition is missing."
		)
	}

	@Test
	def void testInvalidCategoryDefnWithMappingsInList(){
		val mcl = '''
		foo = mdlObj{
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo
			}# end MODEL_PREDICTION
			
			OBSERVATION{
				PAIN : { type is discrete withCategories {mild when foo}, distn=Bernoulli(category=PAIN.mild, probability=foo) }
			}
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::INCORRECT_LIST_CONTEXT,
			"A category definition cannot have a mapping in this context."
		)
	}

	@Test
	def void testValidIfElseWithElse(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}

			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo = if(true) then 1 else 0
			}# end MODEL_PREDICTION
			
		}
		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidIfElseWithElseIf(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}
			
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo = if(true) then 1 elseif(false) then 0
			}# end MODEL_PREDICTION
			
		}
		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidIfElseOneClause(){
		val mcl = '''
		foo = mdlObj{
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo = if(true) then 1
			}# end MODEL_PREDICTION
			
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.whenExpression,
			MdlValidator::UNDER_DEFINED_IF_ELSE,
			"More than one condition or an else statement is required in this expression."
		)
	}

	@Test
	def void testInValidIfElseWithNestedOneClauseIf(){
		val mcl = '''
		foo = mdlObj{
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo = if(true) then 1 else if(false) then 0
			}# end MODEL_PREDICTION
			
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.whenExpression,
			MdlValidator::UNDER_DEFINED_IF_ELSE,
			"More than one condition or an else statement is required in this expression."
		)
	}

	@Test
	def void testInValidMissingCategories(){
		val mcl = '''
		foo = mdlObj{
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
			}# end MODEL_PREDICTION
			
			OBSERVATION{
				PAIN : { type is categorical withCategories {} }
			}
		}
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoricalDefinitionExpr,
			Diagnostic::SYNTAX_DIAGNOSTIC)
	}

	@Test
	def void testInValidVectorDefinition(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}
			
			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				foo::Vector
			}# end MODEL_PREDICTION
		}
		'''.parse

		mcl.assertNoErrors		
//		mcl.assertError(MdlPackage::eINSTANCE.equationDefinition,
//			MdlValidator::UNUSED_FEATURE, "Vector symbol definitions are not supported in this version of the language")
	}

}