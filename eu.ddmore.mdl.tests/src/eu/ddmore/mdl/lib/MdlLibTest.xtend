package eu.ddmore.mdl.lib

import com.google.inject.Inject
import com.google.inject.Provider
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdllib.mdlLib.Library
import java.util.Collections
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.resource.IContainer
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.util.StringInputStream
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.mdl.Mcl

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MdlLibTest {
	@Inject extension ParseHelper<Mcl> ph
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper

	@Inject extension MdlLib
	
	val static CODE_SNIPPET = '''
Bernoulli_DIST_mdl = mdlObj{
   IDV{ CP }

   VARIABILITY_LEVELS{
	ID : { level=2, type is parameter }
	DV : { level=1, type is observation }
   }

   STRUCTURAL_PARAMETERS{
      POP_BASEP
      POP_BETA
   }# end STRUCTURAL_PARAMETERS
«««
«««   VARIABILITY_PARAMETERS{
«««      PPV_EVENT
«««   }# end VARIABILITY_PARAMETERS
«««
«««
«««   RANDOM_VARIABLE_DEFINITION(level=ID){
«««      eta_PPV_EVENT ~ Normal(mean=0, var=PPV_EVENT )
«««   }# end RANDOM_VARIABLE_DEFINITION

   GROUP_VARIABLES{
	  BASE = ln(POP_BASEP/(1-POP_BASEP))
   }

«««   INDIVIDUAL_VARIABLES{
«««      LP = BASE + POP_BETA*CP + eta_PPV_EVENT
«««   }# end INDIVIDUAL_VARIABLES
«««
«««   MODEL_PREDICTION{
«««	  P1 = 1/(1+exp(-LP))
«««	  # P1 = invLogit(LP)
«««   }# end MODEL_PREDICTION
«««
«««   OBSERVATION{
«««     Y : { type is discrete withCategories { success, fail }, distn = Bernoulli(category=Y.success, probability=P1)}
«««   }# end ESTIMATION
} # end of model object
'''

	@Test 
	def void testLib() {
		CODE_SNIPPET.loadLibAndParse.assertNoErrors
	}

	@Inject
	IContainer.Manager manager;
	@Inject ResourceDescriptionsProvider rdp

	@Test
	def listVisibleResources() {
		val myResource = loadLib.resources.head
		val index = rdp.getResourceDescriptions(myResource)
		val descr =	index.getResourceDescription(myResource.getURI());
		for(IContainer visibleContainer:
        	manager.getVisibleContainers(descr, index)) {
    		for(visibleResourceDesc:
            	visibleContainer.exportedObjects) {
      			System.out.println(visibleResourceDesc.name)
    		}
   		}
	}


	@Inject Provider<ResourceSet> resourceSetProvider;
	
	@Test
	def void testFunctionRef() {
		val resourceSet = resourceSetProvider.get
		
		val firstLang = resourceSet.createResource(URI.createURI("sample.mlib"))
		val codeSnippet = '''
			type Real _scalar;
			
			func ln (x::Real) returns ::Real;
		'''
		firstLang.load(new StringInputStream(codeSnippet), Collections::emptyMap)
	
		val result = firstLang.contents.head as Library
		result.assertNoErrors

		val second = ph.parse(
			'''
			Bernoulli_DIST_mdl = mdlObj{
			   IDV{ CP }
			
			   VARIABILITY_LEVELS{
				ID : { level=2, type is parameter }
				DV : { level=1, type is observation }
			   }
			
			   GROUP_VARIABLES{
				  BASE = ln(1.0)
			   }
			
			}
			''',result.eResource.resourceSet)
		
		second.assertNoErrors
	}

	@Test def void testLibHasNoError() {
		loadLibrary
	}

	def loadLibrary() {
		loadLib => [
			resources.forEach [
				contents.get(0).assertNoErrors
			]
		]
	}
}