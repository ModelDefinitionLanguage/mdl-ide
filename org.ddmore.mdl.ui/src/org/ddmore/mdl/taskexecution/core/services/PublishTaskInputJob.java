/**
 * 
 */
package org.ddmore.mdl.taskexecution.core.services;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.ddmore.mdl.taskexecution.core.services.http.TESServer;
import org.ddmore.mdl.ui.internal.MdlActivator;
import org.ddmore.mdl.ui.preference.MDLPreferenceConstants;
import org.eclipse.core.resources.FileInfoMatcherDescription;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceFilterDescription;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * A long-running Job responsible for copying files to a shared location with TES and submitting an execution request to TES.
 * 
 * @author jcarr
 */
public class PublishTaskInputJob extends Job {

    private final transient IFile modelFile;
    private final transient Set<IFile> dataFiles = new HashSet<IFile>();

    private transient String requestId;
    private transient String jobId;

    /**
     * @param name the name of the job
     */
    public PublishTaskInputJob(final String name, final IFile model, final IFile data) {
        super(name);
        this.modelFile = model;
        this.dataFiles.add(data);
    }

    public PublishTaskInputJob(final String name, final IFile model, final Set<IFile> data) {
        super(name);
        this.modelFile = model;
        this.dataFiles.addAll(data);
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        monitor.subTask("Task Execution Service: Publisher");
        IStatus status = Status.OK_STATUS;
        try {

            String symbolicName = MdlActivator.getInstance().getBundle().getSymbolicName();
            if (modelFile == null) {
                return new Status(IStatus.ERROR, symbolicName, "A model file must be supplied");
            }
            if (dataFiles == null) {
                return new Status(IStatus.ERROR, symbolicName, "A data file must be supplied");
            }

            TESServer serverConn = new TESServer();

            // get the UID for this run 
            this.requestId = serverConn.prepare();

            if (monitor.isCanceled()) {
                return Status.CANCEL_STATUS;
            }

            //FIXME            
            if (modelFile.getName().endsWith(".R")) {
                publishFileToSharedDir(modelFile.getLocation().toFile(), this.requestId);
                // also need the control file, as we dont know where it is get all the ones in src-gen
                IFolder outputFolder = modelFile.getProject().getFolder("src-gen");
                outputFolder.createFilter(IResourceFilterDescription.INCLUDE_ONLY, new FileInfoMatcherDescription(
                        "org.eclipse.core.resources.regexFilterMatcher", "ctl"), IResource.BACKGROUND_REFRESH, null);
                for (IResource resource : outputFolder.members()) {
                    if (resource instanceof IFile) {
                        publishFileToSharedDir(((IFile) resource).getLocation().toFile(), this.requestId);
                    }
                }

            } else {
                // FIXME this should be accessible as the target file using the mdl as the input
                publishFileToSharedDir(
                    modelFile.getProject().getLocation().append("src-gen")
                            .append(modelFile.getName().substring(0, modelFile.getName().length() - 3) + "ctl").toFile(), this.requestId);
            }

            for (IFile dataFile : dataFiles) {
                publishFileToSharedDir(dataFile.getLocation().toFile(), this.requestId);
            }

            if (monitor.isCanceled()) {
                return Status.CANCEL_STATUS;
            }

            //FIXME    
            if (modelFile.getName().endsWith(".R")) {
                this.jobId = serverConn.exec(this.requestId, modelFile.getName());
            } else {
                this.jobId = serverConn.exec(this.requestId, modelFile.getName().substring(0, modelFile.getName().length() - 3) + "ctl");
            }

        } catch (Exception ex) {
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Error while executing a command", ex);
        }
        return status;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getJobId() {
        return jobId;
    }

    private void publishFileToSharedDir(final File file, final String UID) throws IOException {
        IPreferenceStore preferenceStore = MdlActivator.getInstance().getPreferenceStore();
        final String targetDir = preferenceStore.getString(MDLPreferenceConstants.TES_SHARED_DIR);
        final String targetInputPath = preferenceStore.getString(MDLPreferenceConstants.TES_SHARED_DIR_INPUT);

        if (targetDir == null || targetDir.isEmpty()) {
            throw new IllegalArgumentException("TES Shared Directory Path not set");
        }

        FileUtils.copyFileToDirectory(file, new File(new File(new File(targetDir), UID), targetInputPath), true);
    }

}
