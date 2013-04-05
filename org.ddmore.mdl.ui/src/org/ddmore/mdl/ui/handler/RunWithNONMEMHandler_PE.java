package org.ddmore.mdl.ui.handler;

import org.ddmore.mdl.generator.MdlGenerator;
import org.ddmore.mdl.taskexecution.core.services.TESExecJob;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

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
                if (generator instanceof MdlGenerator) {
                    MdlGenerator mdlGenerator = (MdlGenerator) generator;
                    String dataFileName = mdlGenerator.getDataSource(r);

                    IFile dataFile = project.getFile(file.getParent().getProjectRelativePath() + "/" + dataFileName);

                    if (file.exists() && dataFile.exists()) {
                        TESExecJob job = new TESExecJob("Running Job on Task Execution Service (" + file.getName() + ")", file, dataFile);
                        job.setUser(true);
                        job.addJobChangeListener(new JobChangeAdapter() {

                            public void done(IJobChangeEvent event) {
                                if (event.getResult().isOK()) {
                                    System.out.println("Job completed successfully");
                                } else {
                                    System.out.println("Job did not complete successfully: " + event.getResult().getMessage());
                                }
                            }
                        });
                        job.schedule();
                        return Boolean.TRUE;
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
