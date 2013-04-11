package org.ddmore.mdl.ui.handler;

import java.util.HashSet;
import java.util.Set;

import org.ddmore.mdl.generator.MdlGenerator;
import org.ddmore.mdl.taskexecution.core.services.TESExecJob;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
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
                                job.setRule(new SerialSchedulingRule());
                                job.schedule();

                                // FIXME also run R (for demo)
                                IFile rFile = project.getFile(file.getParent().getProjectRelativePath() + "/reportGeneration.R");
                                if (rFile.exists()) {

                                    TESExecJob resultJob = new TESExecJob(
                                            "Running Job on Task Execution Service (" + rFile.getName() + ")", rFile, new ResultsFiles(
                                                    project, job));
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

    public class ResultsFiles {

        private IProject project;
        private Job job;

        public ResultsFiles(IProject project, Job job) {
            this.project = project;
            this.job = job;
        }

        public Set<IFile> get() throws CoreException {
            IFolder outputFolder = project.getFolder("results/"
                + (String) job.getProperty(new QualifiedName("eu.ddmore.mdl.ui", "outputFolderId")));
            Set<IFile> dataFiles = new HashSet<IFile>();

            for (IResource resource : outputFolder.members()) {
                if (resource instanceof IFile) {
                    dataFiles.add((IFile) resource);
                }
            }

            return dataFiles;
        }
    }

}
