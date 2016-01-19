package eu.ddmore.mdl

import org.eclipse.xtext.linking.impl.DefaultLinkingService
import eu.ddmore.mdl.mdl.MdlPackage
import java.util.Collections
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.linking.impl.IllegalNodeException
import java.util.List
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.mdl.SymbolReference

class MdlLinkingService extends DefaultLinkingService {
	
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	
	override getLinkedObjects(EObject context, EReference ref, INode node) throws IllegalNodeException {
		val List<EObject> result = super.getLinkedObjects(context, ref, node);
		// If the default implementation resolved the link, return it
		if (null != result && !result.isEmpty()){
			return result;
		}
		else{
			if(context instanceof SymbolReference){
				val name = getCrossRefNodeAsString(node)	
				if(isFunctionName(name))
					return createFunctionSignature(context, name)
			}
		}
		result
	}
	
	
	private def List<EObject> createFunctionSignature(SymbolReference context, String name){
		val fact = MdlPackage.eINSTANCE.mdlFactory
		val defn = fact.createEquationDefinition
		defn.name = name
		context.ref = defn
		return Collections::singletonList(defn)
	}

}