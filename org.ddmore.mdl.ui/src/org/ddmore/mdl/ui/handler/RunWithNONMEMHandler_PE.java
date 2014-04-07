package org.ddmore.mdl.ui.handler;

import org.ddmore.mdl.controller.RunJobOnTES;
import org.ddmore.mdl.mdl.Mcl;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.ddmore.converter.mdl2nonmem.Mdl2Nonmem;

public class RunWithNONMEMHandler_PE extends AbstractHandler implements IHandler {

    @Inject
    private Provider<EclipseResourceFileSystemAccess2> fileAccessProvider;

    @Inject
    private Mdl2Nonmem generator;

    @Inject
    IResourceSetProvider resourceSetProvider;

    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);

        if (selection instanceof IStructuredSelection) {

            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object firstElement = structuredSelection.getFirstElement();

            if (firstElement instanceof IFile) {

                IFile file = (IFile) firstElement;
                IProject project = file.getProject();
                URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
                ResourceSet rs = resourceSetProvider.get(project);
                Resource r = rs.getResource(uri, true);

                if (generator != null) {
                    Mcl mcl = (Mcl) r.getContents().get(0);
                	String dataFileName = generator.getDataSource(mcl);
                    new RunJobOnTES().run(file, dataFileName);
                }
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public boolean isEnabled() {
        return true;
    }
}
