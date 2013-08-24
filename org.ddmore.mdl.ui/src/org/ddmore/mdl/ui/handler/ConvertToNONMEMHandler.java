package org.ddmore.mdl.ui.handler;

import org.ddmore.mdl.generator.MdlGenerator;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class ConvertToNONMEMHandler extends AbstractHandler implements IHandler {
	
	@Inject
	private IGenerator generator;

	@Inject
	private Provider<EclipseResourceFileSystemAccess2> fileAccessProvider;
	
	@Inject
	IResourceDescriptions resourceDescriptions;
	
	@Inject
	IResourceSetProvider resourceSetProvider;
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		final IFile file = (IFile) activeEditor.getEditorInput().getAdapter(IFile.class);
		if (file != null) {
			IProject project = file.getProject();
			IFolder srcGenFolder = project.getFolder("src-gen");
			if (!srcGenFolder.exists()) {
				try {
					srcGenFolder.create(true, true, new NullProgressMonitor());
				} catch (CoreException e) {
					return null;
				}
			}
			final EclipseResourceFileSystemAccess2 fsa = fileAccessProvider.get();
			fsa.setProject(project);
			fsa.setOutputPath(srcGenFolder.getFullPath().toString());

			if (activeEditor instanceof XtextEditor) {
				((XtextEditor) activeEditor).getDocument().readOnly(
						new IUnitOfWork<Boolean, XtextResource>() {

							public Boolean exec(XtextResource source)
									throws Exception {
				                if (generator instanceof MdlGenerator){
									System.out.println("Generating NONMEM code for " + file.getName());
 				                	MdlGenerator mdlGenerator = (MdlGenerator)generator;
					            	 mdlGenerator.doGenerateNMTRAN(source, fsa);
					            	 //fsa.setPostProcessor(callBack);
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
