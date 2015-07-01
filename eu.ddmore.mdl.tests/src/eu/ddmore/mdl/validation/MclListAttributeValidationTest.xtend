package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclListAttributeValidationTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidAttributes(){
		val mcl = '''bar = dataobj {
			DATA_INPUT_VARIABLES{
				ID : { use is id }
			}
			
			SOURCE{	}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testMissingMandatoryAttribute(){
		val mcl = '''bar = dataobj {
			DECLARED_VARIABLES{ Y }
			
			DATA_INPUT_VARIABLES{
				ID : { variable = Y }
			}
			
			SOURCE{	}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_KEY_ATT_MISSING,
			"mandatory key attribute is missing in list"
		)
	}

	@Test
	def void testMissingAttributeWithDep(){
		val mcl = '''bar = dataobj {
			DECLARED_VARIABLES{ Y }
			
			DATA_INPUT_VARIABLES{
				aCov : { use is covariate, categories = [male, female] }
			}
			
			SOURCE{	}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_ATT_MISSING,
			"mandatory attribute 'define'"
		)
	}

	@Test
	def void testMissingDependentAttribute(){
		val mcl = '''bar = dataobj {
			DECLARED_VARIABLES{ Y }
			
			DATA_INPUT_VARIABLES{
				aCov : { use is covariate, define = {1 as male, 2 as female} }
			}
			
			SOURCE{	}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_ATT_MISSING,
			"mandatory attribute 'categories'"
		)
	}

	@Test
	def void testDataDerivedVariablesAttributes(){
		val mcl = '''bar = dataobj {
			DECLARED_VARIABLES{ Y }
			
			DATA_INPUT_VARIABLES{
				dvid : { use is dvid, define = { 0 as Y, 1 as PCA } }
				dv : { use is dv }
			}
			DATA_DERIVED_VARIABLES{
				PCA : { column = dv, categories=[ dead, alive], define={ 0 as dead, 1 as alive} }
			}
			
			SOURCE{	}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testAnonymousCompartmentAttributesOK(){
		val mcl = '''bar = mdlobj(idv T) {
			VARIABILITY_LEVELS{
				ID { type is idv, level=1 }
			}
			
			STRUCTURAL_PARAMETERS{
				KA
				ALAG1
				F1
				V
				CL
			}
			
			MODEL_PREDICTION{
	  			COMPARTMENT {
					INPUT_KA:   {type is depot, cmt=1, to=CENTRAL, ka=KA, tlag=ALAG1, finput=F1}
					CENTRAL:    {type is compartment, cmt=2}
                    			{type is elimination, cmt=2, from=CENTRAL, v=V, cl=CL}
   				}# end COMPARTMENT
				CONC=CENTRAL/V
			} # end MODEL_PREDICTION
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testAnonymousCompartmentAttributesNoKey(){
		val mcl = '''bar = mdlobj(idv T) {
			VARIABILITY_LEVELS{
				ID { type is idv, level=1 }
			}
			
			STRUCTURAL_PARAMETERS{
				KA
				ALAG1
				F1
				V
				CL
			}
			
			MODEL_PREDICTION{
	  			COMPARTMENT {
					INPUT_KA:   {type is depot, cmt=1, to=CENTRAL, ka=KA, tlag=ALAG1, finput=F1}
					CENTRAL:    {type is compartment, cmt=2}
                    			{cmt=2, from=CENTRAL, v=V, cl=CL}
   				}# end COMPARTMENT
				CONC=CENTRAL/V
			} # end MODEL_PREDICTION
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_KEY_ATT_MISSING,
			"mandatory key attribute is missing in list"
		)
	}


	@Test
	def void testUnrecognizedAttribute(){
		val mcl = '''bar = mdlobj(idv T) {
			VARIABILITY_LEVELS{
				ID { type is idv, level=1 }
			}
			
			STRUCTURAL_PARAMETERS{
				KA
				ALAG1
				F1
				V
				CL
			}
			
			MODEL_PREDICTION{
	  			COMPARTMENT {
					INPUT_KA:   {type is depot, blahblah=1, to=CENTRAL, ka=KA, tlag=ALAG1, finput=F1}
					CENTRAL:    {type is compartment, cmt=2}
                    			{cmt=2, from=CENTRAL, v=V, cl=CL}
   				}# end COMPARTMENT
				CONC=CENTRAL/V
			} # end MODEL_PREDICTION
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_LIST_ATT_MISSING,
			"attribute 'blahblah' is not recognised in this context"
		)
	}

}