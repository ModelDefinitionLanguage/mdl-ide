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
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserModelObj7Test {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_SEXAGE_mdl = mdlObj {
	IDV{T}

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


	GROUP_VARIABLES{
		B0 = Lgt0
		B1 = B0 + Lgt1
		B2 = B1 + Lgt2
	}

	MODEL_PREDICTION{
		A = [1.0, 1.02, B0, B2]
	}# end MODEL_PREDICTION

} # end of model object
'''
	
	@Test
	def void testParsing(){
		val mcl = CODE_SNIPPET.parse
		
//		mcl.assertError(MdlPackage::eINSTANCE.equationDefinition, MdlValidator::UNUSED_FEATURE)
		mcl.assertNoErrors
		
	}

	
}