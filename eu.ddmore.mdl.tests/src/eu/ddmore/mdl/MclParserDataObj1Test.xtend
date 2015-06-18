package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclParserDataObj1Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
	warfarin_PK_ODE_dat = dataobj {
	DECLARED_VARIABLES{GUT Y}

	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		WT : { type  is continuous, use is covariate }
		AMT : { use is amt, define = GUT }
	} # end DATA_INPUT_VARIABLES
	SOURCE {
	    set file = "warfarin_conc.csv" 
        set inputformat  is nonmemFormat 
	    set ignore = "#" 
	} # end SOURCE
} # end data object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

//	@Test
//	def void testExpectedAst(){
//		val mcl = CODE_SNIPPET.parse
//		
//		val expectedVars = newLinkedList('warfarin_PK_ODE_dat', 'b', 'c')
//		for(obj : mcl.objects){
//			Assert::assertEquals(expectedVars.pop, obj.name)
//		
//			assertObject(obj)
//		}
//	}
	
//	def dispatch assertObject(DataObject decl){
//		for(blk : decl.blocks){
//			assertBlock(blk);
//		}
//	}

//	def dispatch assertObject(MclObject decl){
//		Assert::fail("Don't go here!")
//	}

//	def dispatch assertBlock(DataObjectBlock blk){
//		val expectedVars = newLinkedList('ID', 'TIME', 'WT', 'AMT')
//		Assert::assertEquals(expectedVars.size, blk.variables.size)
//		for(stmt : blk.variables){
//			Assert::assertEquals(expectedVars.pop, stmt.name)
//		}
//	}
//	
//	def dispatch assertBlock(DataDerivedBlock blk){
//		val expectedVars = newLinkedList('DT')
//		for(stmt : blk.variables){
//			Assert::assertEquals(expectedVars.pop, stmt.name)
//		}
//	}
//	
//	def dispatch assertBlock(DeclaredVariablesBlock blk){
//		val expectedVars = newLinkedList('GUT', 'Y')
//		for(stmt : blk.variables){
//			Assert::assertEquals(expectedVars.pop, stmt.name)
//		}
//	}
//	
//	def dispatch assertBlock(SourceBlock blk){
//		val expectedVars = newLinkedList('file', 'inputformat', 'ignore')
//		for(stmt : blk.statements){
//			Assert::assertEquals(expectedVars.pop, stmt.argumentName)
//		}
//	}
//	
//	def dispatch assertBlock(ObjectBlock blk){
//		Assert::fail("Don't go here!")
//	}
	
}