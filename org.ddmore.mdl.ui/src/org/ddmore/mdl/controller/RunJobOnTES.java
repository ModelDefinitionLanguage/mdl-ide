package org.ddmore.mdl.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ddmore.mdl.taskexecution.core.services.TESExecJob;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;

public class RunJobOnTES {

    private static final Logger LOGGER = Logger.getLogger(RunJobOnTES.class);

    public void run(final IFile file, final String dataFileName) {

        IProject project = file.getProject();
        IFile dataFile = project.getFile(file.getParent().getProjectRelativePath() + "/" + dataFileName);

        LOGGER.debug("Executing a job with the following inputs: " + file.getProjectRelativePath() + " and "
            + dataFile.getProjectRelativePath() + "]");
        if (file.exists() && dataFile.exists()) {
            TESExecJob job = new TESExecJob("Running Job on Task Execution Service (" + file.getName() + ")", file, dataFile);
            job.setUser(true);
            job.setRule(new SerialSchedulingRule());
            job.schedule();

            // FIXME also run R (for demo)
            IFile rFile = project.getFile(file.getParent().getProjectRelativePath() + "/reportGeneration.R");
            if (rFile.exists()) {

                TESExecJob resultJob = new TESExecJob("Running Job on Task Execution Service (" + rFile.getName() + ")", rFile, dataFile,
                        new ResultsFiles(project, job));
                resultJob.setRule(new SerialSchedulingRule());
                resultJob.schedule();
            } else {
                LOGGER.error("Job can not be run as R script (reportGeneration.R) is missing");
            }
        } else {
            LOGGER.error("Job can not be run as missing a file [model file exists: " + file.exists() + "] [data file exists: "
                + dataFile.exists() + "]");
        }
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
