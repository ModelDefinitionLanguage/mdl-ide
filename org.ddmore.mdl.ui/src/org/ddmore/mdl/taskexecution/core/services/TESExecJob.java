/**
 * 
 */
package org.ddmore.mdl.taskexecution.core.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ddmore.mdl.controller.RunJobOnTES.ResultsFiles;
import org.ddmore.mdl.taskexecution.core.services.http.TESRequestStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressConstants;

/**
 * Container for a job run, currently the job comprises 3 elements: publish, monitor and retrieve.
 * @author jcarr
 */
public class TESExecJob extends Job {

    private static final Logger LOG = Logger.getLogger(TESExecJob.class);

    private final transient IFile modelFile;
    private final transient Set<IFile> dataFiles = new HashSet<IFile>();
    private final transient ResultsFiles results;

    private transient PublishTaskInputJob publishInputjob;
    private transient MonitorTaskProgressJob monitorJob;
    private transient Job resultsJob;

    public TESExecJob(final String name, final IFile model) {
        super(name);
        this.modelFile = model;
        this.results = null;
    }

    public TESExecJob(final String name, final IFile model, final IFile data) {
        super(name);
        this.modelFile = model;
        this.dataFiles.add(data);
        this.results = null;
    }

    public TESExecJob(final String name, final IFile model, final Set<IFile> data) {
        super(name);
        this.modelFile = model;
        this.dataFiles.addAll(data);
        this.results = null;
    }

    /**
     * FIXME this is a workaround to get the results once the previous job has finished
     * @param dataFile 
     */
    public TESExecJob(final String name, final IFile model, final IFile data, final ResultsFiles results) {
        super(name);
        this.modelFile = model;
        this.dataFiles.add(data);
        this.results = results;
    }

    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        monitor.beginTask("Task Execution Service: Job Executor", IProgressMonitor.UNKNOWN);

        try {
            //FIXME this needs to be called at point of execution as results are not known on construction.
            if (results != null) {
                this.dataFiles.addAll(this.results.get());
            }

            if (dataFiles.isEmpty()) {
                publishInputjob = new PublishTaskInputJob("Publishing Task " + modelFile.getName(), modelFile);
            } else {
                publishInputjob = new PublishTaskInputJob("Publishing Task " + modelFile.getName(), modelFile, dataFiles);
            }

            publishInputjob.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
            publishInputjob.setUser(true);
            publishInputjob.addJobChangeListener(new JobChangeAdapter() {

                @Override
                public void done(IJobChangeEvent event) {
                    super.done(event);
                    if (event.getResult().isOK()) {

                        // TODO not capturing the status
                        scheduleMonitorJob(monitor, publishInputjob.getRequestId(), publishInputjob.getJobId());
                    } else {
                        LOG.error(String.format("Publish task failed: %s", event.getResult().getMessage()));
                    }
                }
            });
            publishInputjob.schedule();
            publishInputjob.join();

            // display this task after it's finished
            setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);
            setProperty(IProgressConstants.ACTION_PROPERTY,
                getJobCompletedAction(publishInputjob.getRequestId(), publishInputjob.getJobId()));
            setProperty(new QualifiedName("eu.ddmore.mdl.ui", "outputFolderId"), publishInputjob.getRequestId());

        } catch (Exception ex) {
            monitor.done();
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Error while executing a command", ex);
        }

        monitor.done();
        if (TESRequestStatus.failed.equals(monitorJob.getStatus())) {
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Job Failed");
        }
        return new Status(IStatus.OK, this.getClass().getPackage().getName(), "Job Completed Successfully");
    }

    private IStatus scheduleMonitorJob(final IProgressMonitor monitor, final String requestId, final String jobId) {
        if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
        }

        IStatus status = Status.OK_STATUS;

        try {
            monitorJob = new MonitorTaskProgressJob("Monitoring Task Progress (" + jobId + ")", jobId);
            monitorJob.setUser(true);
            monitorJob.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
            monitorJob.addJobChangeListener(new JobChangeAdapter() {

                @Override
                public void done(IJobChangeEvent event) {
                    super.done(event);
                    if (event.getResult().isOK()) {
                        LOG.debug(String.format("Job status %s, retrieving results", monitorJob.getStatus()));
                        scheduleRetrieveJob(monitor, requestId);
                    } else {
                        LOG.error(String.format("Monitor task failed: %s", event.getResult().getMessage()));
                    }
                }
            });
            monitorJob.schedule();
            monitorJob.join();

        } catch (Exception ex) {
            monitor.done();
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Error while executing a command", ex);
        }
        return status;
    }

    private IStatus scheduleRetrieveJob(final IProgressMonitor monitor, final String requestId) {
        if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
        }

        IStatus status = Status.OK_STATUS;

        try {
            resultsJob = new RetrieveTaskOutputsJob("Retrieving Task Results (" + requestId + ")", requestId, this.modelFile);
            resultsJob.setUser(true);
            resultsJob.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
            resultsJob.schedule();
            resultsJob.join();

        } catch (Exception ex) {
            monitor.done();
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Error while executing a command", ex);
        }
        return status;
    }

    private Action getJobCompletedAction(final String requestId, final String jobId) {
        return new Action() {

            @Override
            public void run() {
                MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Job Complete",
                    "Request ID " + requestId + " Job ID " + jobId + " has completed");
            }
        };
    }
}
