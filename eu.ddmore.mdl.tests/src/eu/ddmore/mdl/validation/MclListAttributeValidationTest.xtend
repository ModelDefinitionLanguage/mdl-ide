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
import org.junit.Ignore

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
				aCov : { use is covariate, categories are {male, female} }
			}
			
			SOURCE{	}
		}'''.parse
		
		//@TODO: Implement the correct validation to check that categories are not defined.
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_ATT_MISSING,
			"categories are not mapped to data"
		)
	}

	@Ignore // @TODO: Write a test for dep attributes.
	def void testMissingDependentAttribute(){
		val mcl = '''bar = dataobj {
			DECLARED_VARIABLES{ Y }
			
			DATA_INPUT_VARIABLES{
				aCov : { use is covariate, categories are male when 1 female when 2 }
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
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				AMT : { use is amt, define = D }
				TIME : { use is idv }
			}
			DATA_DERIVED_VARIABLES{
				DT : { column = TIME, condition = D > 0 }
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