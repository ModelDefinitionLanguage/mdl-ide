package eu.ddmore.mdl.scoping;

import static java.util.Collections.emptyList;

import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.runtime.preferences.IScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportUriGlobalScopeProvider;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;

import com.google.common.collect.Lists;

public class MdlImportGlobalProvider extends ImportedNamespaceAwareLocalScopeProvider {

public static final URI HEADER_URI = URI.createURI("platform:/plugin/eu.ddmore.mdl.definitions/definitions/mdlDefn.mlib");
    
//    @Override
//    protected LinkedHashSet<URI> getImportedUris(Resource resource)
//    {
//        LinkedHashSet<URI> importedURIs = super.getImportedUris(resource);
//        importedURIs.add(HEADER_URI);
//        return importedURIs;
//    }

	@Override
	protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase) {
		List<ImportNormalizer> implicitImports = super.getImplicitImports(ignoreCase);
		implicitImports = Lists.newArrayList(implicitImports);
		implicitImports.add(
				new ImportNormalizer(
						QualifiedName.create(""), 
						true, 
						ignoreCase
				)
		);
		return implicitImports;
	}
}
