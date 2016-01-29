package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclIndexDataTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	@Inject ResourceDescriptionsProvider rdp
	
	val static CODE_SNIPPET = '''
obj1 = dataObj{
	DECLARED_VARIABLES{
			Y
			GUT
			PCA withCategories {dead, alive}
			OTHER withCategories {dead, alive}
	}
	
	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		SEX : { use is catCov withCategories { male when 0, female when 1} } 
		AMT : { use  is amt, variable = GUT  }
		DVID : { use  is dvid }
		DV : { use  is dv, define = {
					1 in DVID as Y,
					2 in DVID as { PCA.dead when 1, PCA.alive when 2},
					3 in DVID as { OTHER.dead when 1, OTHER.alive when 2}
				}
			  }
		MDV : { use  is mdv}
	}

	SOURCE {
	    SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } 
	} # end SOURCE
}
'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Test
	def void testExpectedEObjectDescriptions(){
		CODE_SNIPPET.parse.assertExportedEObjectDescriptions("obj1, obj1.Y, obj1.GUT, obj1.PCA, obj1.PCA.dead, obj1.PCA.alive, obj1.OTHER, obj1.OTHER.dead, obj1.OTHER.alive, obj1.ID, obj1.TIME, obj1.SEX, obj1.SEX.male, obj1.SEX.female, obj1.AMT, obj1.DVID, obj1.DV, obj1.MDV, obj1.SrcFile")
	}
	
	def private assertExportedEObjectDescriptions(EObject o, CharSequence expected){
		Assert::assertEquals(expected.toString,
			o.getExportedEObjectDescriptions.map[qualifiedName].join(", ")
			);
	}
	
	def getResourceDescription(EObject o){
		val index = rdp.getResourceDescriptions(o.eResource);
		index.getResourceDescription(o.eResource.URI)
	}
	
	def getExportedEObjectDescriptions(EObject o){
		o.getResourceDescription.getExportedObjects
	}
}