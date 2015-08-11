package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.validation.MdlValidator

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclParserDataObj3Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_v2_dat = dataobj{
	DECLARED_VARIABLES{ Y; GUT; PCA withCategories {dead, alive}; OTHER withCategories {dead, alive} }
	
	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		WT : { use is covariate }
		AGE : { use is covariate }
		SEX : { use is covariate withCategories { male when 0, female when 1} } 
		AMT : { use  is amt, define = { 1 as GUT } }
		DVID : { use  is dvid }
		DV : { use  is dv, define =  {
					1 in DVID as Y,
					2 in DVID as { PCA.dead when 1, PCA.alive when 2},
					3 in DVID as { OTHER.dead when 1, OTHER.alive when 2}
				}
			  }
		MDV : { use  is mdv}
	}

	DATA_DERIVED_VARIABLES{
		DT : { column=TIME, condition=AMT > 0 }
	}
	
	SOURCE {
	    SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat, ignore = "#" } 
	} # end SOURCE
} # end data object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Test
	def void testInvalidDvidAttribs(){
		val mcl = '''
warfarin_PK_v2_dat = dataobj{
	DECLARED_VARIABLES{ Y; D; TD; GUT; PCA}
	
	DATA_INPUT_VARIABLES {
		DVID : { use  is dvid, define= { 1 as Y, 2 as PCA } }
	}

	SOURCE {
	    SrcFile : { file="warfarin_conc_sex.csv", inputformat  is nonmemFormat, ignore = "#" } 
	} # end SOURCE
} # end data object
		'''.parse

		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_LIST_ATT_MISSING,
			"attribute 'define' is not recognised in this context"
		)
		
	}
}