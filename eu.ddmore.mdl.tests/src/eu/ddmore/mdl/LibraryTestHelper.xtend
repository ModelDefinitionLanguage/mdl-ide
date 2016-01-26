package eu.ddmore.mdl

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.junit4.util.ParseHelper
import eu.ddmore.mdl.lib.MdlLib

class LibraryTestHelper<T extends EObject> {
	@Inject extension ParseHelper<T>	
	@Inject extension MdlLib

	def loadLibAndParse(CharSequence p) {
		p.parse(loadLib)
	}
	
	
}