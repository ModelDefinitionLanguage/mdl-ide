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
	    set file = "warfarin_conc_sex.csv",
        	inputformat  is nonmemFormat, 
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

	
}