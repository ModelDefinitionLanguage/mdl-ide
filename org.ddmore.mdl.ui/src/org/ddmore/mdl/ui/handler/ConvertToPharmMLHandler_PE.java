package org.ddmore.mdl.ui.handler;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.validation.Utils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.ddmore.mdl.generator.Mdl2PharmMLWrapper;
import eu.ddmore.mdl.generator.Preferences;


public class ConvertToPharmMLHandler_PE extends AbstractHandler implements IHandler {

    @Inject
    private Provider<JavaIoFileSystemAccess> fileAccessProvider;

    @Inject
    private Mdl2PharmMLWrapper generator;

    @Inject
    IResourceSetProvider resourceSetProvider;

	@Inject
	IResourceDescriptions resourceDescriptions;
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			if (firstElement instanceof IFile) {
				IFile file = (IFile) firstElement;
				IProject project = file.getProject();
				IFolder srcGenFolder = project.getFolder(Preferences.SRC_GEN_PREFIX);
				if (!srcGenFolder.exists() && (Preferences.SRC_GEN_PREFIX.length() > 0)) {
					try {
						srcGenFolder.create(true, true,
								new NullProgressMonitor());
					} catch (CoreException e) {
						return Boolean.FALSE;
					}
				}
				JavaIoFileSystemAccess fsa = fileAccessProvider.get();
				fsa.setOutputPath(srcGenFolder.getRawLocation().toString());
				URI uri = URI.createPlatformResourceURI(file.getFullPath()
						.toString(), true);
				ResourceSet rs = resourceSetProvider.get(project);
				Resource r = rs.getResource(uri, true);
                if (generator != null){
				    Mcl mcl = (Mcl) r.getContents().get(0);
				    if (mcl != null && Utils.getMOGs(mcl).size() == 0){
				    	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				    	MessageDialog.openError(shell, "Error", 
				    		   "PharmML generation error: no MOG found!");
				    }
    				System.out.println("Generating PharmML code for " + file.getName());
	            	generator.doGenerate(r, fsa);
	            	try {
						srcGenFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
					} catch (CoreException e) {
						e.printStackTrace();
					}
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
