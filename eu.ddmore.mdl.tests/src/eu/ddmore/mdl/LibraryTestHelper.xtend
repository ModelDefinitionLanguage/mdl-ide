package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.lib.MdlLib
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.junit4.util.ParseHelper

class LibraryTestHelper<T extends EObject> {
	@Inject extension ParseHelper<T>	
	@Inject extension MdlLib

//	def loadLibAndParse(CharSequence p) {
//		p.parse(loadLib)
//	}
	
	def parse(CharSequence p) {
		p.parse(loadLib)
	}
	
	def parse(CharSequence p, URI uri){
		p.parse(uri, loadLib)
	}
	
}