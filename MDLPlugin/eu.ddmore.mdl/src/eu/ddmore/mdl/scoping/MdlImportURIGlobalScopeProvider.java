package eu.ddmore.mdl.scoping;

import java.util.LinkedHashSet;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.scoping.impl.ImportUriGlobalScopeProvider;

public class MdlImportURIGlobalScopeProvider extends ImportUriGlobalScopeProvider {

	public static final URI HEADER_URI = URI.createURI("platform:/plugin/eu.ddmore.mdl.definitions/definitions/mdlDefns.mlib");
	public static final URI TEST_LIB_URI = URI.createURI("platform:/plugin/eu.ddmore.mdl.definitions/definitions/testDefns.mlib");
    
    @Override
    protected LinkedHashSet<URI> getImportedUris(Resource resource) {
    	return getImportedUris(resource, HEADER_URI, TEST_LIB_URI);
    }

    protected LinkedHashSet<URI> getImportedUris(Resource resource, URI ... libUri)
    {
        LinkedHashSet<URI> importedURIs = super.getImportedUris(resource);
    	for(URI lu : libUri){
	        importedURIs.add(lu);
    	}
        return importedURIs;
    }
}
