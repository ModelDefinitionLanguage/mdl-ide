package eu.ddmore.mdl

import com.google.inject.Inject
import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.MdlInjectorProvider
import org.junit.Test
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.DataObject
import eu.ddmore.mdl.mdl.DataInputBlock
import eu.ddmore.mdl.mdl.DataDerivedBlock
import eu.ddmore.mdl.mdl.DeclaredVariablesBlock
import eu.ddmore.mdl.mdl.SourceBlock
import eu.ddmore.mdl.mdl.ObjectBlock
import org.junit.Assert

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclParserDataObj1Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
	warfarin_PK_ODE_dat = dataobj {
	DECLARED_VARIABLES{GUT Y}

	DATA_INPUT_VARIABLES {
		ID : { use=id }
		TIME : { use=idv }
		WT : { type = continuous, use=covariate }
		AMT : { use=amt, define = GUT }
	} # end DATA_INPUT_VARIABLES
	SOURCE {
	    file = "warfarin_conc.csv" 
        inputformat = nonmemFormat 
	    ignore = "#" 
	} # end SOURCE
} # end data object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Test
	def void testExpectedAst(){
		val mcl = CODE_SNIPPET.parse
		
		val expectedVars = newLinkedList('warfarin_PK_ODE_dat', 'b', 'c')
		for(obj : mcl.objects){
			Assert::assertEquals(expectedVars.pop, obj.name)
		
			assertObject(obj)
		}
	}
	
	def dispatch assertObject(DataObject decl){
		for(blk : decl.blocks){
			assertBlock(blk);
		}
	}

	def dispatch assertObject(MclObject decl){
		Assert::fail("Don't go here!")
	}

	def dispatch assertBlock(DataInputBlock blk){
		val expectedVars = newLinkedList('ID', 'TIME', 'WT', 'AMT')
		Assert::assertEquals(expectedVars.size, blk.variables.size)
		for(stmt : blk.variables){
			Assert::assertEquals(expectedVars.pop, stmt.name)
		}
	}
	
	def dispatch assertBlock(DataDerivedBlock blk){
		val expectedVars = newLinkedList('DT')
		for(stmt : blk.variables){
			Assert::assertEquals(expectedVars.pop, stmt.name)
		}
	}
	
	def dispatch assertBlock(DeclaredVariablesBlock blk){
		val expectedVars = newLinkedList('GUT', 'Y')
		for(stmt : blk.variables){
			Assert::assertEquals(expectedVars.pop, stmt.name)
		}
	}
	
	def dispatch assertBlock(SourceBlock blk){
		val expectedVars = newLinkedList('file', 'inputformat', 'ignore')
		for(stmt : blk.statements){
			Assert::assertEquals(expectedVars.pop, stmt.propertyName)
		}
	}
	
	def dispatch assertBlock(ObjectBlock blk){
		Assert::fail("Don't go here!")
	}
	
}