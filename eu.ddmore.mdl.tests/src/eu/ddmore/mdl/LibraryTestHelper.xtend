package eu.ddmore.mdl

import com.google.common.base.Preconditions
import com.google.inject.Inject
import eu.ddmore.mdl.scoping.MdlImportURIGlobalScopeProvider
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.Map
import javax.inject.Provider
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.util.LazyStringInputStream

class LibraryTestHelper<T extends EObject> {
//	val static Logger logger = Logger.getLogger(LibraryTestHelper.name)

	@Inject
    private Provider<XtextResourceSet> resourceSet;

	def parse(CharSequence p) {
		parse(getAsStream(p), resourceSet.get)
	}
	
	def parse(CharSequence p, XtextResourceSet rs) {
		parse(getAsStream(p), rs)
	}

	def parse(CharSequence p, URI fileUri, XtextResourceSet rs) {
		parse(getAsStream(p), fileUri, rs)
	}

	def parse(CharSequence p, URI fileUri) {
		parse(getAsStream(p), fileUri, resourceSet.get)
	}

	def private InputStream getAsStream(CharSequence text) {
		return new LazyStringInputStream(if(text == null) "" else text.toString());
	}


    def T parse(InputStream in, XtextResourceSet rs) throws IOException {
        parse(in, URI.createURI("_synthetic.mdl"), rs)
	}
	
    /**
     * Parses an input stream and returns the resulting object tree root element.
     * @param in Input Stream
     * @return Root model object
     * @throws IOException When and I/O related parser error occurs
     */
    def T parse(InputStream in, URI fileUri, XtextResourceSet rs) throws IOException {
        registerURIMappingsForImplicitImports(rs);
    	val resource = rs.createResource(fileUri)
        resource.load(in, rs.getLoadOptions());
        return resource.getContents().get(0) as T;
    }
 
    def private static void registerURIMappingsForImplicitImports(XtextResourceSet resourceSet) {
        val uriConverter = resourceSet.getURIConverter();
        val uriMap = uriConverter.getURIMap();
        registerPlatformToFileURIMapping(MdlImportURIGlobalScopeProvider.HEADER_URI, uriMap);
//        System.err.println("UriMap=" + uriMap)
    }
 
    def private static void registerPlatformToFileURIMapping(URI uri, Map<URI, URI> uriMap) {
        val fileURI = createFileURIForHeaderFile(uri);
//        System.err.println("registering URI as :" + fileURI.toString)
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