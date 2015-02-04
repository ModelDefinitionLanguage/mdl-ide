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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.ddmore.mdl.generator.Mdl2PharmMLWrapper;
import eu.ddmore.mdl.generator.Preferences;


public class ConvertToPharmMLHandler extends AbstractHandler implements IHandler {
	
	@Inject
	private Mdl2PharmMLWrapper generator;

	@Inject
	private Provider<JavaIoFileSystemAccess > fileAccessProvider;
	
	@Inject
	IResourceDescriptions resourceDescriptions;
	
	@Inject
	IResourceSetProvider resourceSetProvider;
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		final IFile file = (IFile) activeEditor.getEditorInput().getAdapter(IFile.class);
		if (file != null) {
			IProject project = file.getProject();
			final IFolder srcGenFolder = project.getFolder(Preferences.SRC_GEN_PREFIX);
			if (!srcGenFolder.exists() && (Preferences.SRC_GEN_PREFIX.length() > 0)) {
				try {
					srcGenFolder.create(true, true, new NullProgressMonitor());
				} catch (CoreException e) {
					return null;
				}
			}
			

			final JavaIoFileSystemAccess fsa = fileAccessProvider.get();			
			fsa.setOutputPath(srcGenFolder.getRawLocation().toString());
				
			if (activeEditor instanceof XtextEditor) {
				((XtextEditor) activeEditor).getDocument().readOnly(
						new IUnitOfWork<Boolean, XtextResource>() {

							public Boolean exec(XtextResource source)
									throws Exception {
								if (generator != null){
								       Mcl mcl = (Mcl) source.getContents().get(0);
								       //No MOGs defined
								       if (mcl != null && Utils.getMOGs(mcl).size() == 0){
								    	   Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
								    	   MessageDialog.openError(shell, "Error", "PharmML generation error: no MOG found!");
								       }
									
					            	generator.doGenerate(source, fsa);
					            	try {
										srcGenFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
									} catch (CoreException e) {
										e.printStackTrace();
									}
				                }
								return Boolean.TRUE;
							}
						});
			}
		}
		return null;
	}

	public boolean isEnabled() {
		return true;
	}
}
