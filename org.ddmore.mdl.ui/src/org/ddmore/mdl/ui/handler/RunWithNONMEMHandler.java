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
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;
//import org.eclipse.jface.resource.ImageDescriptor;
//import org.eclipse.swt.graphics.ImageData;
//import org.eclipse.ui.console.ConsolePlugin;
//import org.eclipse.ui.console.IConsoleDocumentPartitioner;
//import org.eclipse.ui.console.IConsoleManager;
//import org.eclipse.ui.console.TextConsole;

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
                ((XtextEditor) activeEditor).getDocument().readOnly(new IUnitOfWork<Boolean, XtextResource>() {

                    public Boolean exec(XtextResource r) throws Exception {

                        if (generator instanceof MdlGenerator) {
                            MdlGenerator mdlGenerator = (MdlGenerator) generator;
                            String dataFileName = mdlGenerator.getDataSource(r);

                            IProject project = file.getProject();
                            IFile dataFile = project.getFile(file.getParent().getProjectRelativePath() + "/" + dataFileName);

                            if (file.exists() && dataFile.exists()) {
                                TESExecJob job = new TESExecJob("Running Job on Task Execution Service (" + file.getName() + ")", file,
                                        dataFile);
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
                                job.setRule(new SerialSchedulingRule());
                                job.schedule();

                                // FIXME also run R (for demo)
                                IFile rFile = project.getFile(file.getParent().getProjectRelativePath()
                                    + "/Write Multiple Graphics to Report Folder.R");
                                if (rFile.exists()) {
                                    TESExecJob resultJob = new TESExecJob(
                                            "Running Job on Task Execution Service (" + rFile.getName() + ")", rFile, dataFile);
                                    resultJob.setSystem(true); // dont show it
                                    resultJob.setRule(new SerialSchedulingRule());
                                    resultJob.schedule();
                                }

                                return Boolean.TRUE;
                            }
                        }

                        // XXX Is it valid to submit a job with no data?
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

    private class SerialSchedulingRule implements ISchedulingRule {

        @Override
        public boolean contains(ISchedulingRule rule) {
            return (rule instanceof SerialSchedulingRule);
        }

        @Override
        public boolean isConflicting(ISchedulingRule rule) {
            return (rule instanceof SerialSchedulingRule);
        }
    }

}
