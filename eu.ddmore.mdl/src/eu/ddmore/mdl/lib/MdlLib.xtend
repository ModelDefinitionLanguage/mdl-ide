package eu.ddmore.mdl.lib

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import eu.ddmore.mdl.mdllib.scoping.MdlLibLib

class MdlLib {
	@Inject
	MdlLibLib mdlLibLib 
	
	public val static MAIN_LIB = "funcLib.mlib"
	
	def loadLib() {
		val resourceSet = mdlLibLib.loadLib
		resourceSet.loadIndivLib(MAIN_LIB)
		resourceSet
	}
	

	def private loadIndivLib(ResourceSet resourceSet, String path){
		val stream = getClass().getClassLoader().getResourceAsStream(path)
		val resource = resourceSet.createResource(URI::createURI(path))
		resource.load(stream, resourceSet.getLoadOptions())
	}

}