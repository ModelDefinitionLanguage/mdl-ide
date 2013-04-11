/**
 * 
 */
package org.ddmore.mdl.taskexecution.core.services;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.ddmore.mdl.ui.internal.MdlActivator;
import org.ddmore.mdl.ui.preference.MDLPreferenceConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * A long-running job responsible for copying results from shared TES location to users workspace. That job should be of 'WorkspaceJob' to ensure that Eclipse Workspace access is correctly handled.
 * @author jcarr
 */
public class RetrieveTaskOutputsJob extends Job {

    private final transient String requestId;
    private final transient IFile modelFile;

    public RetrieveTaskOutputsJob(final String name, final String requestId, final IFile modelFile) {
        super(name);
        this.requestId = requestId;
        this.modelFile = modelFile;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        monitor.subTask("Task Execution Service: Results");
        IStatus status = Status.OK_STATUS;
        try {
            copyFilesFromSharedLocation();
        } catch (Exception ex) {
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Error while executing a command", ex);
        }
        return status;
    }

    private void copyFilesFromSharedLocation() throws IOException {
        IPreferenceStore preferenceStore = MdlActivator.getInstance().getPreferenceStore();
        final String targetDir = preferenceStore.getString(MDLPreferenceConstants.TES_SHARED_DIR);
        final String targetOutputPath = preferenceStore.getString(MDLPreferenceConstants.TES_SHARED_DIR_OUTPUT);

        if (targetDir == null || targetDir.isEmpty()) {
            throw new IllegalArgumentException("TES Shared Directory Path not set");
        }

        File inputDir = new File(new File(new File(targetDir), requestId), targetOutputPath);

        IPath outputPath = this.modelFile.getProject().getLocation().append("results").append(requestId);

        //FIXME
        if (this.modelFile.getFileExtension().equals("R")) {
            // copy everything
            FileUtils.copyDirectory(inputDir, outputPath.toFile(), true);
        } else {
            // only copy file with same prefix
            File[] files = inputDir.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith(modelFile.getName());
                }
            });

            for (File file : files) {
                FileUtils.copyFileToDirectory(file, outputPath.toFile(), true);
            }
        }

        // TODO this refresh is more expensive than it needs to be
        // also we should probably use the eclipse file manipulation (as long as it's performant) so that we don't have to handle this.
        try {
            this.modelFile.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
