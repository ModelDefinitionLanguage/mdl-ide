package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserPriorObj5Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
testprior = priorObj{

	NON_CANONICAL_DISTRIBUTION{
		PRIOR_SOURCE{
			data1 : { file="exampleUNIPV.csv", inputFormat is csv, #inputFormat to define
						column=["bins_k", "bin_v", "p_k_v"] }
							
			data2 : { file="exampleUNIPV2.csv", inputFormat is csv,
						column="data_SIGMA2_RES" } 
		}

		INPUT_PRIOR_DATA{
			# are bins_k, bins_v, p_k_v to be read with readVector function or are simply read as reported in the line below?
			bins_k_v = readMatrixFromTable(src=data1, column=["bins_k", "bins_v"])
			p_k_v = readVectorFromTable(src=data1, column="p_k_v")
			data_SIGMA2_RES = readVectorFromTable(src=data1, column="data_SIGMA2_RES")
		}
	}

	PRIOR_VARIABLE_DEFINITION{
		 POP_k_v ~ MultiNonParametric(bins=bins_k_v, probability=p_k_v) 
		 invPOP_SIGMA2_RES ~ Empirical(data=data_SIGMA2_RES) #in case of samples with the same probability

		 POP_K = POP_k_v[0]
		 POP_V = POP_k_v[1]
	}
	

}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
//	@Test //need this to enable test initialisation with 1 ignore
//	def void testDummy(){
//	}
	
}