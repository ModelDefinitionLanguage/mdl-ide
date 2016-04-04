package eu.ddmore.mdl.lib

import com.google.inject.Inject
import eu.ddmore.mdllib.scoping.MdlLibLib
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import com.google.inject.Provider

class MdlLib {
	@Inject
	MdlLibLib mdlLibLib 
	
	public val static MAIN_LIB = "funcLib.mlib"

	@Inject Provider<ResourceSet> resourceSetProvider;
	
	def loadLib() {
		val resourceSet = mdlLibLib.loadLib
		resourceSet.loadIndivLib(MAIN_LIB)
		resourceSet
	}
	
	def loadLibOnly(String libFileName) {
		val resourceSet = resourceSetProvider.get()
		resourceSet.loadIndivLib(libFileName)
		resourceSet
	}
	

	def private loadIndivLib(ResourceSet resourceSet, String path){
		val stream = getClass().getClassLoader().getResourceAsStream(path)
		val resource = resourceSet.createResource(URI::createURI(path))
		resource.load(stream, resourceSet.getLoadOptions())
	}

}