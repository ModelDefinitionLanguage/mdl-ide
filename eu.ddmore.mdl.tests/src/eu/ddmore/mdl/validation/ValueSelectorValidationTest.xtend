package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class ValueSelectorValidationTest {
    
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	@Test
	def void testValidvalueSelection() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad }
			
			DATA_INPUT_VARIABLES{
			  CMT : { use is cmt }
		      AMT: {use is amt, define={1 in CMT as Ad}}
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidValueSelectionDefnAndSrcSame() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad }
			
			DATA_INPUT_VARIABLES{
		      AMT: {use is amt, define={1 in AMT as Ad}}
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'AMT' contains an expression that refers to itself.")
	}

	@Test
	def void testInValidValueSelectionTgtAndSrcSame() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad }
			
			DATA_INPUT_VARIABLES{
			  CMT : { use is cmt }
		      AMT: {use is amt, define={1 in CMT as AMT}}
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'AMT' contains an expression that refers to itself.")
	}


	@Test
	def void testInValidValueSelectionValueNotAnInteger() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad; other }
			
			DATA_INPUT_VARIABLES{
			  CMT : { use is cmt }
		      AMT: {use is amt, define={other in CMT as Ad}}
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Int type, but was ref:Real.")
	}

	@Test
	def void testInValidValueSelectionValueIsARealLiteral() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad; other }
			
			DATA_INPUT_VARIABLES{
			  CMT : { use is cmt }
		      AMT: {use is amt, define={1.0 in CMT as Ad}}
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Int type, but was Real.")
	}

	@Test
	def void testInalidValueSelectionSrcIsNotCmt() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad }
			
			DATA_INPUT_VARIABLES{
			  CMT : { use is id }
		      AMT: {use is amt, define={1 in CMT as Ad}}
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected source column of type 'List:Cmt', but was 'List:Id'.")
	}

	@Test
	def void testInalidValueSelectionDuplicateValues() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad; other }
			
			DATA_INPUT_VARIABLES{
			  CMT : { use is cmt }
		      AMT: {use is amt, define={1 in CMT as Ad, 1 in CMT as other}}
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::DUPLICATE_SELECTION_TEST_VALUE,
			"Test value '1' cannot be used more than once in the same value selection expression.")
	}

	@Test
	def void testInalidValueSelectionDuplicateSelectionVars() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad; other }
			
			DATA_INPUT_VARIABLES{
			  CMT : { use is cmt }
		      AMT: {use is amt, define={1 in CMT as Ad, 2 in CMT as Ad}}
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::DUPLICATE_SELECTION_REF,
			"Selection variable 'Ad' cannot be used more than once in the same value selection expression."
		)
	}

	@Test
	def void testInvalidCategoryDefinitionSelection() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad }
			
			DATA_INPUT_VARIABLES{
		      foo: {use is catCov withCategories { a when Ad, b when 1 } }
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Int type, but was ref:Real.")
	}

	@Test
	def void testInvalidCategoryDefinitionDuplicateValues() {
		val mclText = '''bar = dataObj {
			DECLARED_VARIABLES { Ad }
			
			DATA_INPUT_VARIABLES{
		      foo: {use is catCov withCategories { a when 1, b when 1 } }
			}
			
			SOURCE{
				srcFile : { file="count.csv", inputFormat is nonmemFormat }
			}
			
		}'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::DUPLICATE_SELECTION_TEST_VALUE,
			"Test value '1' cannot be used more than once in the same category definition expression.")
	}

	@Test
	def void testInvalidCategoryDefinitionDuplicateRefs() {
		val mclText = '''
		foo = mdlObj {
			IDV{T}
			
			VARIABILITY_LEVELS{
				DV : { type is observation, level = 1 }
			}

			MODEL_PREDICTION{
			  Prob0 = 0.1
			  Prob1 = 0.2
			  Prob2 = 0.5
			  Prob3 = 1 - sum([Prob0, Prob1, Prob2]) 
			}# end MODEL_PREDICTION
		
			OBSERVATION{
			   Y : {type is categorical withCategories{ c0 when Prob0, c1 when Prob0} } 
		    }# end ESTIMATION
		}
		'''
		
		val mcl = mclText.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::DUPLICATE_SELECTION_TEST_VALUE,
			"Test ref 'Prob0' cannot be used more than once in the same category definition expression.")
	}

}