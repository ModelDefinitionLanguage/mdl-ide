package org.ddmore.mdl.ui.handler;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.ddmore.libpharmml.ILibPharmML;
import eu.ddmore.libpharmml.IPharmMLResource;
import eu.ddmore.libpharmml.IPharmMLValidator;
import eu.ddmore.libpharmml.IValidationError;
import eu.ddmore.libpharmml.IValidationReport;
import eu.ddmore.libpharmml.PharmMlFactory;

public class ValidatePharmMLHandler_PE  extends AbstractHandler implements IHandler {

	@Inject
	private Provider<JavaIoFileSystemAccess> fileAccessProvider;
	 
    @Inject
    IResourceSetProvider resourceSetProvider;
    
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);

        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object firstElement = structuredSelection.getFirstElement();

            if (firstElement instanceof IFile) {
				try {
	            	IFile file = (IFile) firstElement;
					InputStream inputStream = file.getContents();
	            	ILibPharmML libPharmML = PharmMlFactory.getInstance().createLibPharmML();
	            	String newline = System.getProperty("line.separator");
	 				String logData = "Validation of PharmML file " + file.getName() + newline + newline;
	 				IPharmMLResource res = libPharmML.createDomFromResource(inputStream);
	 				IPharmMLValidator validator = libPharmML.getValidator();
	 				IValidationReport rpt = validator.createValidationReport(res);
	 				if(rpt.isValid()){
	 					logData = file.getFullPath().toString() + " is valid" + newline;
	 				} else {
	 					for (int i = 1; i <= rpt.numErrors(); i++){
	 						IValidationError err = rpt.getError(i); 
	 						logData += "Error " + (i) + ": " + err.getErrorMsg() + newline;
	 					}
	 				}
	 				inputStream.close();
	 				//Write the report
	                File outputDirectory = new File(file.getParent().getRawLocation() + "/validation");
	                File outputFile = new File(outputDirectory.getPath() +'/'+ file.getName().replace(".xml", ".log"));
					FileUtils.write(outputFile, logData);
					IProject project = file.getProject();
					IFolder srcGenFolder = project.getFolder(file.getParent().getName());
					srcGenFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
				} catch (Exception e) {
					e.printStackTrace();
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