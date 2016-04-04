package eu.ddmore.mdl

import com.google.common.base.Preconditions
import com.google.inject.Inject
import eu.ddmore.mdl.scoping.MdlImportURIGlobalScopeProvider
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.util.LazyStringInputStream

class MdlTestHelper<T extends EObject> {
//	@Inject extension ParseHelper<T>	

	@Inject
    private XtextResourceSet resourceSet;

//	def loadLibAndParse(CharSequence p) {
//		p.parse(loadLib)
//	}
	
	def parse(CharSequence p) {
//		p.parse(loadLib)
		
		parse(getAsStream(p), resourceSet)
	}
	
	def parse(CharSequence p, XtextResourceSet rs) {
//		p.parse(loadLib)
		
		parse(getAsStream(p), rs)
	}
	
//	def parse(CharSequence p, URI uri){
//		p.parse(uri, loadLib)
//	}
	

//    new() {
//        setupParser();
//    }
 
	def private InputStream getAsStream(CharSequence text) {
		return new LazyStringInputStream(if(text == null) "" else text.toString());
	}

//    def private void setupParser() {
//        new StandaloneSetup().setPlatformUri("../");
//        var Injector injector = Guice.createInjector(new MdlRuntimeModule());
//        injector.injectMembers(this);
//        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
//        registerURIMappingsForImplicitImports(resourceSet);
//    }
 
    /**
     * Parses an input stream and returns the resulting object tree root element.
     * @param in Input Stream
     * @return Root model object
     * @throws IOException When and I/O related parser error occurs
     */
    def T parse(InputStream in, XtextResourceSet rs) throws IOException {
        registerURIMappingsForImplicitImports(rs);
    	val resource = rs.createResource(URI.createURI("sample.mdl"))
        resource.load(in, rs.getLoadOptions());
        return resource.getContents().get(0) as T;
    }
 
//    def T parse(URI uri) {
//        val resource = resourceSet.getResource(uri, true);
//        return resource.getContents().get(0) as T;
//    }
	
    def private static void registerURIMappingsForImplicitImports(XtextResourceSet resourceSet) {
        val uriConverter = resourceSet.getURIConverter();
        val uriMap = uriConverter.getURIMap();
        registerPlatformToFileURIMapping(MdlImportURIGlobalScopeProvider.HEADER_URI, uriMap);
    }
 
    def private static void registerPlatformToFileURIMapping(URI uri, Map<URI, URI> uriMap) {
        val fileURI = createFileURIForHeaderFile(uri);
        val file = new File(fileURI.toFileString());
        Preconditions.checkArgument(file.exists());
        uriMap.put(uri, fileURI);
        
    }
 
    def private static URI createFileURIForHeaderFile(URI uri) {
        return URI.createFileURI(deriveFilePathFromURI(uri));
    }
 
    def private static String deriveFilePathFromURI(URI uri) {
        return ".." + uri.path().substring(7);
    }
}