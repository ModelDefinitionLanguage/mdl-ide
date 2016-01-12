package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.xtext.diagnostics.Diagnostic
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclParserMogObj3Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
foo_dat = dataObj{
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

foo_par = parObj{
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

foo_mdl = mdlObj{
	IDV{ T }
	
	FUNCTIONS{
	   	# declare a function that is obtained from another object. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc = function(int arg1, real arg2, string arg3)
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function using the '&' so the parser knows to look for the definition
		Z = &userFunc(arg1=1, arg2=2.0, arg3="foo") + 22.2
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
		DV : { level=1, type is observation }
	} 

} # end of model object

foo_task = taskObj{
	ESTIMATE{
		set algo is saem
	}
}# end of task object


feg = funcObj {
	FUNCTIONS{
   		foo = function(real arg1){
   			arg1
   		}
	}
}

foo_mog = mogObj {
		OBJECTS{
			foo_dat : { type is dataObj }
			foo_mdl : { type is mdlObj }
			foo_par : { type is parObj }
			foo_task : { type is taskObj }
			feg : { type is funcObj }
		}
}		'''
	
	@Ignore
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		
	}
	
	@Test
	// needed to stop initialisation failure
	def void testDummy(){
	}
}