package org.ddmore.mdl.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.ddmore.mdl.generator.MdlGenerator;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RunWithNONMEMHandler extends AbstractHandler implements IHandler {
	@Inject
	private Provider<EclipseResourceFileSystemAccess2> fileAccessProvider;
	
	@Inject
	private IGenerator generator;

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		final IFile file = (IFile) activeEditor.getEditorInput().getAdapter(IFile.class);
		if (file != null) {
			if (activeEditor instanceof XtextEditor) {
				((XtextEditor) activeEditor).getDocument().readOnly(
						new IUnitOfWork<Boolean, XtextResource>() {

							public Boolean exec(XtextResource r)
									throws Exception {
								if (generator instanceof MdlGenerator) {
									MdlGenerator mdlGenerator = (MdlGenerator) generator;
									String dataFileName = mdlGenerator
											.getDataSource(r);

									IProject project = file.getProject();
									IFile dataFile = project.getFile(dataFileName);

									String name = file.getName();
									String ext = file.getFileExtension();
									name = name.substring(0, name.length()
											- (ext.length() + 1));
									IFile modelFile = project.getFile(name);

									if (modelFile.exists() && dataFile.exists()){
										System.out.println("Submitting NONMEM file with data: " + name 
												+ " and " + dataFileName);
										RunWithNONMEMJob job = new RunWithNONMEMJob(
												modelFile, dataFile);
										job.schedule();
										return Boolean.TRUE;
									}
								}
								System.out
								.println("Submitting MDL file...");
								RunWithNONMEMJob job = new RunWithNONMEMJob(
										file);
								job.schedule();
								return Boolean.TRUE;
							}
						});
			}
		}
		return Boolean.FALSE;
	}

	public boolean isEnabled() {
		return true;
	}

}
