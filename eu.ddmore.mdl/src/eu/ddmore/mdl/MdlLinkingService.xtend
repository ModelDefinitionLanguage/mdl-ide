package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.SymbolReference
import java.util.Collections
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.linking.impl.DefaultLinkingService
import org.eclipse.xtext.linking.impl.IllegalNodeException
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.resource.IContainer
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider

class MdlLinkingService extends DefaultLinkingService {
	
	@Inject
	IContainer.Manager manager;
	@Inject ResourceDescriptionsProvider rdp

//	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	
//	override getLinkedObjects(EObject context, EReference ref, INode node) throws IllegalNodeException {
//		val List<EObject> result = super.getLinkedObjects(context, ref, node);
//		// If the default implementation resolved the link, return it
//		if (null != result && !result.isEmpty()){
//			return result;
//		}
//		else if(context instanceof BuiltinFunctionCall){
//			val name = getCrossRefNodeAsString(node)	
//			val myResource = context.eResource			
//			val index = rdp.getResourceDescriptions(myResource)
//			val descr =	index.getResourceDescription(myResource.getURI());
//			for(IContainer visibleContainer:
//	        	manager.getVisibleContainers(descr, index)) {
//	    		for(visibleResourceDesc:
//	            	visibleContainer.exportedObjects) {
//	            	val libName = visibleResourceDesc.name
//	      			if(libName.toString == name){
//	      				return Collections.singletonList(visibleResourceDesc.getEObjectOrProxy())
//	      			}
//	    		}
//	   		}
//		}
//		result
//	}


	override getLinkedObjects(EObject context, EReference ref, INode node) throws IllegalNodeException {
		val List<EObject> result = super.getLinkedObjects(context, ref, node);
		// If the default implementation resolved the link, return it
		if (null != result && !result.isEmpty()){
			return result;
		}
		else{
			if(context instanceof SymbolReference){
				val name = getCrossRefNodeAsString(node)	
				val myResource = context.eResource			
				val index = rdp.getResourceDescriptions(myResource)
				val descr =	index.getResourceDescription(myResource.getURI());
				for(IContainer visibleContainer:
		        	manager.getVisibleContainers(descr, index)) {
		    		for(visibleResourceDesc:
		            	visibleContainer.exportedObjects) {
		            	val libName = visibleResourceDesc.name
		      			if(libName.toString == name){
		      				return Collections.singletonList(visibleResourceDesc.getEObjectOrProxy())
		      			}
		    		}
		   		}
	   		}
		}
		result
	}

}