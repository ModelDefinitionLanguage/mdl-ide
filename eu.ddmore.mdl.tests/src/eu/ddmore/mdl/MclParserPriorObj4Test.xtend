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
class MclParserPriorObj4Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
example332_pri = priorObj{

  PRIOR_PARAMETERS{
	   # prior on "THETA"
	   MU_POP_K = 0.1# median
	   MU_POP_V = 8
 
 # <<<< Matrix definition ?>>>
	   SIGMA_POP_P[[]] = [[1,0.1;
	   						0.1,1]]
 		
	   # prior on "OMEGA"
	   a_OMEGA_T = 0.3
	   b_OMEGA_T = 0.3      
        
# <<<< Matrix definition ?>>>  // alternate
	   R[[]] = matrix(vector=[1,0.1,0.1,1], ncol=2, byRow=true)
	   rho = 2
        
        # prior on "SIGMA"
	   a_POP_T = 0.1
	   b_POP_T = 0.1
   } # end PRIOR_PARAMETERS


   PRIOR_VARIABLE_DEFINITION{

	   # prior on "THETA"
	   lMU_POP_K = ln(MU_POP_K)
	   lMU_POP_V = ln(MU_POP_V)
	    
# <<<< Creating a vector: v = c(el1,el2) ?>>>  
	   lMU_POP_P[] = [lMU_POP_K, lMU_POP_V] 
	   lPOP_P[] ~ MultivariateNormal(mean=lMU_POP_P, cov=SIGMA_POP_P)
	 	
# <<<< Accessing vector elements: el1 = v[1] ?>>> 
	   POP_K = exp(lPOP_P[1])
	   POP_V = exp(lPOP_P[2]) 

	   # prior on "OMEGA" 	
#	   invOMEGA_P ~ Wishart(scaleMatrix=R, nu=rho)
	# SLM not sure about this. Should wishard return a matrix?
	# Creating a dummy matrix to keep the inverse function happy
	   invOMEGA_P [[]]

# <<<< the inverse(matrix) operator does not exist in the current version>>> 
	   OMEGA_P[[]] = inverse(invOMEGA_P)
		
	   TAU_T ~ Gamma(shape=a_OMEGA_T, scale=b_OMEGA_T)
	   OMEGA_T = 1/TAU_T 
        
	   # prior on "SIGMA"  
	   POP_T ~ Gamma(shape=a_POP_T, scale=b_POP_T) 

    } # end PRIOR_VARIABLE_DEFINITION
} # end of parameter object



example332_par = parObj{
	STRUCTURAL{
	   POP_T:{value=0.1}
	   lPOP_P:{value=0.1}		
	}

	VARIABILITY{
	   OMEGA_T:{value=0.1, type is var}
	   OMEGA_P:{value=0.1, type is var}
	}
}




example332_mdl = mdlObj{

   IDV{ T }
   
   VARIABILITY_LEVELS{
   	ID : {level = 2, type is parameter}
   	DV : { level = 1, type is observation } 
   }

   STRUCTURAL_PARAMETERS{
	   POP_T
	   lPOP_P[]
   }# end STRUCTURAL_PARAMETERS

   VARIABILITY_PARAMETERS{
	   OMEGA_T
	   OMEGA_P[[]]
   }# end VARIABILITY_PARAMETERS

   RANDOM_VARIABLE_DEFINITION(level=ID) {
	   lP[] ~ MultivariateNormal(mean=lPOP_P, cov=OMEGA_P) 
	   eta_T ~ Normal(mean=0, var=OMEGA_T)
   }# end RANDOM_VARIABLE_DEFINITION

   RANDOM_VARIABLE_DEFINITION(level=DV) {
	   eps ~ Normal(mean=0, var=1)
   }# end RANDOM_VARIABLE_DEFINITION


   INDIVIDUAL_VARIABLES{
# <<<< Accessing vector elements: el1 = v[1] ?>>> 
	   K = exp(lP[1])
	   V = exp(lP[2])
	   ln(TAU) = linear(trans is ln, pop = POP_T, ranEff=[eta_T])
	   SD_ADD = sqrt(1/TAU)
   }# end INDIVIDUAL_VARIABLES

} # end of model object

		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
}