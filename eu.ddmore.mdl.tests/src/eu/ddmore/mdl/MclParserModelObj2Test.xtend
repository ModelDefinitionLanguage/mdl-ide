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
class MclParserModelObj2Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_ANALYTIC_mdl = mdlobj(idv=T) {

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
		DV : { level=1, type is observation }
   }

	COVARIATES{
		WT
		logtWT = log(WT/70)
	}

	STRUCTURAL_PARAMETERS {
		POP_CL
		POP_V
		POP_KA
		POP_TLAG
		BETA_CL_WT
		BETA_V_WT
		RUV_PROP
		RUV_ADD
	} # end STRUCTURAL_PARAMETERS
	
	VARIABILITY_PARAMETERS {
		PPV_CL
		PPV_V
		PPV_KA
		PPV_TLAG
	} # end VARIABILITY_PARAMETERS 
	
	RANDOM_VARIABLE_DEFINITION(level=ID) {
		ETA_CL ~ Normal(mean = 0, sd = PPV_CL)
		ETA_V ~ Normal(mean = 0, sd = PPV_V)
		ETA_KA ~ Normal(mean = 0, sd = PPV_KA)
		ETA_TLAG ~ Normal(mean = 0, sd = PPV_TLAG)
	} # end RANDOM_VARIABLE_DEFINITION 
	
	INDIVIDUAL_VARIABLES { # This maps to the "Type 3" individual parameter definition in PharmML
	    CL = linear(trans  is log, pop = POP_CL, fixEff = {coeff=BETA_CL_WT , covariate = logtWT }, ranEff = ETA_CL)
	    V = linear(trans  is log, pop = POP_V, fixEff =  {coeff=BETA_V_WT , covariate = logtWT } , ranEff = ETA_V)
	    KA = linear(trans  is log, pop = POP_KA, ranEff = ETA_KA)
	    TLAG = linear(trans  is log, pop = POP_TLAG, ranEff = ETA_TLAG) 
	} # end INDIVIDUAL_VARIABLES
	
	MODEL_PREDICTION {
	    D # dosing variable
	    DT # dosing time
	    k = CL/V
	    CC = when(T - DT < TLAG) 0 
	         otherwise (D/V) * (KA/(KA-k) * (exp(-k * (T -DT-TLAG) - exp(-KA*(T-DT-TLAG)))))
	} # end MODEL_PREDICTION

	RANDOM_VARIABLE_DEFINITION(level=DV){
		EPS_Y ~ Normal(mean = 0, var = 1) # This maps the standard error model in PharmML. The error model is predefined. 
	}

	OBSERVATION {
	    Y = combinedError1(additive = RUV_ADD, proportional = RUV_PROP,
	              eps = EPS_Y, prediction = CC) 
	} # end OBSERVATION
} # end of model object
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