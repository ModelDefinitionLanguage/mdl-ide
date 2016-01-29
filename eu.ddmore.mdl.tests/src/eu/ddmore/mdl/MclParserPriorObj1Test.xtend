package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import java.util.Deque
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserPriorObj1Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
testprior = priorObj{
	PRIOR_PARAMETERS{
		MU_V = 0.3
		VAR_V = 0.3
		# What is meant here
		a_OMEGA_V=0.3
		b_OMEGA_V=0.3
		a_OMEGA_k=0.3
		b_OMEGA_k=0.3
		a_OMEGA_SIGMA2_RES=0.3
		b_OMEGA_SIGMA2_RES=0.3
	}

	NON_CANONICAL_DISTRIBUTION{
		PRIOR_SOURCE{
			data : { file="simple3_prior.csv", inputFormat is RList,
						format=[{element="bins_k", type is vector},
								{element="p_k", type is vector},
								{element="data_SIGMA2_RES", type is vector},
								{element="p_SIGMA2_RES", type is vector}	] }    #the file structure has to be decided
		}

		INPUT_PRIOR_DATA{
			bins_k = readVector(src=data, element="bins_k")
			p_k = readVector(src=data, element="p_k")
			p_SIGMA2_RES = readVector(src=data, element="p_SIGMA2_RES")
			data_SIGMA2_RES = readVector(src=data, element="data_SIGMA2_RES")
		}
	}

	PRIOR_VARIABLE_DEFINITION{
		lnMU_V = ln(MU_V)
		POP_V ~ LogNormal(mean=lnMU_V, var=VAR_V)
		POP_k ~ NonParametric(bins=bins_k, probability=p_k) 
		invPOP_SIGMA2_RES ~ Empirical(data=data_SIGMA2_RES, probability=p_SIGMA2_RES) 

		invOMEGA_V ~ Gamma(shape=a_OMEGA_V, scale=b_OMEGA_V)
		invOMEGA_k ~ Gamma(shape=a_OMEGA_k, scale=b_OMEGA_k)
		invOMEGA_SIGMA_RES ~ Gamma(shape=a_OMEGA_SIGMA2_RES, scale=b_OMEGA_SIGMA2_RES)
	}
	

}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
	@Test
	def void testBlocks(){
		val mcl = CODE_SNIPPET.parse
		val Deque<String> expectedBlks = newLinkedList("PRIOR_PARAMETERS", "NON_CANONICAL_DISTRIBUTION", "PRIOR_VARIABLE_DEFINITION");
		for(blk : mcl.objects.last.blocks){
			Assert::assertEquals(expectedBlks.pop, blk.identifier)
		}
	}

	
}