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
class MclParserDataObj3Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_v2_dat = dataobj{
	DECLARED_VARIABLES{ Y; D; TD; GUT; PCA}
	
	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		WT : { use is covariate }
		AGE : { use is covariate }
		SEX : { use is covariate, categorical with { male when 0, female when 1} } 
		AMT : { use  is amt, define={ 1 as GUT } }
		#DVID : { use  is dvid }
		#DV : { use  is dv, define = { 1 as Y, 2 as PCA } }
		DVID : { use  is dvid }
		DV : { use  is dv, define = {
					1 of DVID as Y,
					2 of DVID as PCA with { dead when 1, alive when 2}
				}
			  }
		MDV : { use  is mdv}
	}

	DATA_DERIVED_VARIABLES{
		DT : { column=TIME, condition=AMT > 0 }
	}
	
	SOURCE {
	    SrcFile : { file="warfarin_conc_sex.csv", inputformat  is nonmemFormat, ignore = "#" } 
	} # end SOURCE
} # end data object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	
}