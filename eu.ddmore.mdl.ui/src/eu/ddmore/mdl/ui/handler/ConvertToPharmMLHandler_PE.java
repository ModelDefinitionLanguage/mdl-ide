package eu.ddmore.mdl.ui.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * {@link org.eclipse.core.commands.IHandler} implementation for MDL->PharmML generation invoked from the Project Explorer tree view.
 */
public class ConvertToPharmMLHandler_PE extends AbstractConvertToPharmMLHandler {

    public Object execute(final ExecutionEvent event) throws ExecutionException {

        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            final Object firstElement = structuredSelection.getFirstElement();
            if (firstElement instanceof IFile) {

                final IFile file = (IFile) firstElement;
                final URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
                final ResourceSet rs = resourceSetProvider.get(file.getProject());
                final Resource r = rs.getResource(uri, true);

                return doGenerateHandlingErrors(file, r);
            }
        }
        return Boolean.FALSE;
    }

}
