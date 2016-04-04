package eu.ddmore.mdl.ui.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.Bundle;

public class NMTranToMDLHandler_PE extends AbstractHandler implements IHandler {
	
	private static final Logger LOGGER = Logger.getLogger(NMTranToMDLHandler_PE.class);
    
    public Object execute(ExecutionEvent event) throws ExecutionException {

    	ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object firstElement = structuredSelection.getFirstElement();
			final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

            if (firstElement instanceof IFile) {
				try {
					//NMTran file
	            	IFile nmtranFile = (IFile) firstElement;
	 				
	 				//Awk script
					Bundle bundle = Platform.getBundle("eu.ddmore.mdl.ui");
					URL awkURL = bundle.getEntry("scripts/nt2mdl.awk");
					File awkFile; //NOTE: file path is used to name MDL objects
					try {
						awkFile = new File(FileLocator.resolve(awkURL).toURI());
					} catch (URISyntaxException | IOException e) {
						e.printStackTrace();
						LOGGER.error("Unable to access awk script: " + awkURL.getPath(), e);
		                MessageDialog.openError(shell, "Error", "Internal error in attempting to access awk script for MDL generation; see the log output for details.");
		                return false;
					}
	 			    
					//Output folder 
	 			    File outputDirectory = new File(nmtranFile.getParent().getRawLocation() + "/output");
	                File outputFile = new File(outputDirectory.getPath() +'/'+ nmtranFile.getName().replace(".ctl", ".mdl"));

	 				//gawk -f nt2mdl.awk -v NMTRAN= input_filename > output.mdl;

	                String command = "gawk -f " + awkFile.getAbsolutePath() + " -v NMTRAN= " + nmtranFile.getName();
	                
	            	String newline = System.getProperty("line.separator");
	 				String logData = "# Automatically generated from NMTran file " + nmtranFile.getName() + newline + newline;
	
	 				File workingDir = nmtranFile.getParent().getRawLocation().toFile();
	                Runtime rt = Runtime.getRuntime();
	                Process p = rt.exec(command, null, workingDir);
                	
                	InputStream std = p.getInputStream();
                    InputStreamReader isr = new InputStreamReader(std);
                    BufferedReader br = new BufferedReader(isr);
                    String line = null;
                    while ( (line = br.readLine()) != null)
                        logData += line + newline;
                    //int exitVal = p.waitFor();
                    //System.out.println("Process exitValue: " + exitVal);
                	p.waitFor();
	                
	 				FileUtils.write(outputFile, logData);
					IProject project = nmtranFile.getProject();
					IFolder srcGenFolder = project.getFolder(nmtranFile.getParent().getName());
			            try{
					        if (!srcGenFolder.exists()) {
					        	srcGenFolder.create(true, true, new NullProgressMonitor());
					        }
					        srcGenFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
			            } catch (CoreException e) {
			            	e.printStackTrace();
			            	LOGGER.error("Unable to create output folder for MDL generation: " + srcGenFolder, e);
			                MessageDialog.openError(shell, "Error", "Internal error in attempting to create output folder for MDL generation; see the log output for details.");
			                return false;
			            }

				} catch(RuntimeException e){
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
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


