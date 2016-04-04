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
class MclParserMogObj2Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
Categorical_DIST_dat = dataObj{
   DECLARED_VARIABLES{ Y withCategories {c1, c2, c3, c0} }
	
   DATA_INPUT_VARIABLES{
      ID:{ use is id }
	  TIME:{ use is ignore } # NOT USED IN MODEL
      CP:{ use is idv }
      DV:{ use is dv, define= {Y.c1 when 0, Y.c2 when 1, Y.c3 when 2, Y.c0 when 3 } }
      MDV:{ use is mdv }
   }# end DATA_INPUT_VARIABLES

   	SOURCE {
	    srcFile : {file = "category.csv", 
        	inputFormat is nonmemFormat} 
	} # end SOURCE
} # end data object

Categorical_DIST_par = parObj{
   STRUCTURAL{
      #THETA
      Beta : {value=1,lo=0,hi=10}
      Lgt0 : {value=-1.09861}
	  Lgt1 : {value=1.09861}
	  Lgt2 : {value=1.09861}
   }# end STRUCTURAL

   VARIABILITY{
      #OMEGA
      PPV_EVENT : {value=0.04 ,type is var}
   }# end VARIABILITY

} # end of parameter object

Categorical_DIST_mdl = mdlObj{
   IDV{ CP }

   VARIABILITY_LEVELS{
	ID : { level=2, type is parameter }
	DV : { level=1, type is observation }
   }

   STRUCTURAL_PARAMETERS{
      Beta
      Lgt0
	  Lgt1
	  Lgt2
   }# end STRUCTURAL_PARAMETERS

   VARIABILITY_PARAMETERS{
      PPV_EVENT
   }# end VARIABILITY_PARAMETERS


   RANDOM_VARIABLE_DEFINITION(level=ID){
      eta_PPV_EVENT ~ Normal(mean=0, var=PPV_EVENT )
   }# end RANDOM_VARIABLE_DEFINITION

   GROUP_VARIABLES{
	  B0 = Lgt0
	  B1 = B0 + Lgt1
	  B2 = B1 + Lgt2
   }
   
   INDIVIDUAL_VARIABLES{
      indiv_B0 = general(grp=B0, ranEff = [eta_PPV_EVENT])
      indiv_B1 = general(grp=B1, ranEff = [eta_PPV_EVENT])
      indiv_B2 = general(grp=B2, ranEff = [eta_PPV_EVENT])
   }# end INDIVIDUAL_VARIABLES

   MODEL_PREDICTION{
	  EDRUG = Beta * CP
	  
	  A0 = indiv_B0 + EDRUG
	  A1 = indiv_B1 + EDRUG
	  A2 = indiv_B2 + EDRUG
	  	  	  
	  P0 = invLogit(A0)
	  P1 = invLogit(A1)
	  P2 = invLogit(A2)
	  
	  Prob0 = P0
	  Prob1 = P1 - P0
	  Prob2 = P2 - P1
	  Prob3 = 1 - P2
   }# end MODEL_PREDICTION

	RANDOM_VARIABLE_DEFINITION(level=DV){
		Y withCategories{c0, c1, c2, c3} ~ Categorical(probability=[Prob0, Prob1, Prob2, Prob3])
	}


   OBSERVATION{
	   :: { type is categorical, variable=Y }
    }# end ESTIMATION

} # end of model object

Categorical_DIST_task = taskObj{
	ESTIMATE{
		set algo is saem
	}
}# end of task object

Categorical_DIST_mog = mogObj {
		OBJECTS{
			Categorical_DIST_dat : { type is dataObj }
			Categorical_DIST_mdl : { type is mdlObj }
			Categorical_DIST_par : { type is parObj }
			Categorical_DIST_task : { type is taskObj }
		}
}		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
}