package org.ddmore.mdl.ui.handler;

import java.io.InputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
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
					
					/*//Testing: print PharmML file - ok!
					BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
					String inputLine;
					while ((inputLine = in.readLine()) != null) System.out.println(inputLine); */
					
	            	ILibPharmML libPharmML = PharmMlFactory.getInstance().createLibPharmML();
	 				System.out.println("Validating generated PharmML...");
	 				IPharmMLResource res = libPharmML.createDomFromResource(inputStream);
	 				IPharmMLValidator validator = libPharmML.getValidator();
	 				IValidationReport rpt = validator.createValidationReport(res);
	 				if(rpt.isValid()){
	 					System.out.println(file.getFullPath().toString() + " is valid");
	 				} else {
	 					for (int i = 1; i <= rpt.numErrors(); i++){
	 						IValidationError err = rpt.getError(i); 
	 						System.out.println("Error " + (i) + ": " + err.getErrorMsg());
	 					}
	 				}
	 				inputStream.close();

	 				/* //write .log file
	                JavaIoFileSystemAccess fsa = fileAccessProvider.get();
					fsa.setOutputPath(file.getParent().getFullPath().toString());
					IProject project = file.getProject();
					final IFolder srcGenFolder = project.getFolder(Preferences.SRC_GEN_PREFIX);
					if (srcGenFolder.exists())
						srcGenFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
					*/
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