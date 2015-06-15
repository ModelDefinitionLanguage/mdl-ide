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
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclParserDataObj2Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_v2_dat = dataobj{
	DECLARED_VARIABLES{ Y; D; TD}
	
	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		WT : { use  is covariate }
		AGE : { use  is covariate }
		SEX : { use is covariate, type=categorical(male, female), 
				define=[{category=male, value=0}, {category=female,value=1}]}
		AMT : { use  is amt , define = D }
		DVID : { use  is dvid }
		DV : { use  is dv, define = Y }
		MDV : { use  is mdv}
	}

	DATA_DERIVED_VARIABLES{
		DT : { column=TIME, condition=AMT > 0 }
	}
	
	SOURCE {
	    file = "warfarin_conc_sex.csv"
        inputformat  is nonmemFormat 
	    ignore = "#" 
#		header = true  # or false
#		skip =  0  ##  << integer >> Skips number of rows before header / data
	} # end SOURCE
} # end data object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Ignore
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
			Assert::assertEquals(expectedVars.pop, stmt.argumentName)
		}
	}
	
	def dispatch assertBlock(ObjectBlock blk){
		Assert::fail("Don't go here!")
	}
	
}