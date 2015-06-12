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
class MclParserParamObj2Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_ODE_par = parobj {
	DECLARED_VARIABLES{ETA_CL ETA_V}

	# By default a parameter is to be estimated if fix is omitted
 	STRUCTURAL {
		POP_CL = (0.1, 0.001 )
		POP_V = (8, 0.001) 
		POP_KA = (0.362, 0.001)
		POP_TLAG = (1, 0.001, 10)
		BETA_CL_WT = 0.75
		BETA_V_WT = 1 
		RUV_PROP = (0.1, 0 )
		RUV_ADD = (0.1, 0) 
		} # end STRUCTURAL
	VARIABILITY(type=var) {
		PPV_CL = (,0.1) 
		PPV_V = (0.1, 0, 1)
		PPV_KA = 0.1
		PPV_TLAG = (,0.1)
		OMEGA : { type=corr, parameter=[ETA_CL, ETA_V], value = [0.01] } 
	} # end VARIABILITY
} # end of parameter object 
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
			Assert::assertEquals(expectedVars.pop, stmt.propertyName)
		}
	}
	
	def dispatch assertBlock(ObjectBlock blk){
		Assert::fail("Don't go here!")
	}
	
}