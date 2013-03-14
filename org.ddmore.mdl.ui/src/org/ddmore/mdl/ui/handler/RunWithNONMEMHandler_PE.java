package org.ddmore.mdl.ui.handler;

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
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.ddmore.mdl.generator.MdlGenerator;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RunWithNONMEMHandler_PE extends AbstractHandler implements IHandler {
	@Inject
	private Provider<EclipseResourceFileSystemAccess2> fileAccessProvider;
	
	@Inject
	private IGenerator generator;
	
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
                if (generator instanceof MdlGenerator){
	            	 MdlGenerator mdlGenerator = (MdlGenerator)generator;

	            	 String name = file.getName(); 
         			 String ext = file.getFileExtension(); 
         			 name = name.substring(0, name.length() - (ext.length() + 1)); 
            		 IFile modelFile = project.getFile(name);

            		 String dataFileName = mdlGenerator.getDataSource(r);
            		 IFile dataFile = project.getFile(dataFileName);

					 if (modelFile.exists() && dataFile.exists()){
							System.out.println("Submitting NONMEM file with data: " + name 
									+ " and " + dataFileName);
							RunWithNONMEMJob job = new RunWithNONMEMJob(
									modelFile, dataFile);
							job.schedule();
							return Boolean.TRUE;
					 }
	            }
                System.out.println("Submitting MDL file...");
				RunWithNONMEMJob job = new RunWithNONMEMJob(file);
			    job.schedule();
				return Boolean.TRUE;
            }
        }
		return Boolean.FALSE;
	}

	public boolean isEnabled() {
		return true;
	}
}
