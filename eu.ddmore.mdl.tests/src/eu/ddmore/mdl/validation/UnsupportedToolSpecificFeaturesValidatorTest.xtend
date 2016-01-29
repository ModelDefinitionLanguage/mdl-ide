package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class UnsupportedToolSpecificFeaturesValidatorTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	


	@Test
	def void testMonolixSupportedIdv(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				ln(a) = linear(trans is ln, pop=d, ranEff = [ eta_a ]) 
			}
		}
	'''.parse
		mcl.assertNoIssues
//		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject, UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
//			"Objects of type 'desObj' are not currently supported for execution in R."
//		)
	}

	@Test
	def void testMonolixNotSupportedIdvGeneral(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				ln(a) = general(grp=d, ranEff = [ eta_a ]) 
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.equationTypeDefinition, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"General individual parameter definition is not currently supported by MONOLIX."
		)
	}


	@Test
	def void testMonolixNotSupportedIdvExplicit(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a = exp(d * eta_a) 
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.equationTypeDefinition, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"Explicit individual parameter definition is not currently supported by MONOLIX."
		)
	}

	@Test
	def void testMonolixNotSupportedIdvExplicitNoRV(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a = d 
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.equationTypeDefinition, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"Explicit individual parameter definition is not currently supported by MONOLIX."
		)
	}

	@Test
	def void testMonolixSupportedObs(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
				ID : { level=2, type is parameter }
				o : { level=1, type is observation }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			RANDOM_VARIABLE_DEFINITION(level = o){
				eps_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				ln(a) = linear(trans is ln, pop=d, ranEff = [ eta_a ]) 
			}
			
			OBSERVATION{
				z = combinedError2(additive=1, proportional=2, prediction=a, eps=eps_a)
			}
		}
	'''.parse
		mcl.assertNoIssues
//		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject, UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
//			"Objects of type 'desObj' are not currently supported for execution in R."
//		)
	}

	@Test
	def void testMonolixUnsupportedExplicitObs(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=2, type is parameter }
					o : { level=1, type is observation }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			RANDOM_VARIABLE_DEFINITION(level = o){
				eps_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				ln(a) = linear(trans is ln, pop=d, ranEff = [ eta_a ]) 
			}
			
			OBSERVATION{
				z = 1 + 2 * a + eps_a
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.equationTypeDefinition, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"Only the pre-defined error models are currently supported by MONOLIX."
		)
	}

}
