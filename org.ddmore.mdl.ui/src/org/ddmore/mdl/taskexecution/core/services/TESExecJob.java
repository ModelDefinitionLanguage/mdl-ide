/**
 * 
 */
package org.ddmore.mdl.taskexecution.core.services;

import org.ddmore.mdl.taskexecution.core.services.http.TESRequestStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
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

    private final transient IFile modelFile;
    private final transient IFile dataFile;

    public TESExecJob(final String name, final IFile model, final IFile data) {
        super(name);
        this.modelFile = model;
        this.dataFile = data;
    }

    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        monitor.beginTask("Task Execution Service: Job Executor", IProgressMonitor.UNKNOWN);

        try {
            final PublishTaskInputJob job = new PublishTaskInputJob("Publishing Task " + modelFile.getName(), modelFile, dataFile);
            job.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
            job.setUser(true);
            job.addJobChangeListener(new JobChangeAdapter() {

                @Override
                public void done(IJobChangeEvent event) {
                    super.done(event);
                    if (event.getResult().isOK()) {

                        // TODO not capturing the status
                        scheduleMonitorJob(monitor, job.getRequestId(), job.getJobId());
                    } else {
                        // something went wrong
                        // TODO log - monitor end
                    }
                }
            });
            job.schedule();
            job.join();

            // display this task after it's finished
            setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);
            setProperty(IProgressConstants.ACTION_PROPERTY, getJobCompletedAction(job.getRequestId(), job.getJobId()));

        } catch (Exception ex) {
            monitor.done();
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Error while executing a command", ex);
        }
        monitor.done();
        return new Status(IStatus.OK, this.getClass().getPackage().getName(), "Job Completed Successfully");
    }

    private IStatus scheduleMonitorJob(final IProgressMonitor monitor, final String requestId, final String jobId) {
        if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
        }

        IStatus status = Status.OK_STATUS;

        try {
            final MonitorTaskProgressJob job = new MonitorTaskProgressJob("Monitoring Task Progress (" + jobId + ")", jobId);
            job.setUser(true);
            job.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
            job.addJobChangeListener(new JobChangeAdapter() {

                @Override
                public void done(IJobChangeEvent event) {
                    super.done(event);
                    if (event.getResult().isOK()) {

                        if (TESRequestStatus.completed.equals(job.getStatus())) {
                            // TODO not capturing the status
                            scheduleRetrieveJob(monitor, requestId);
                        } else if (TESRequestStatus.running.equals(job.getStatus())) {
                            // check again
                            //scheduleMonitorJob(monitor, jobId);
                        } else {
                            // something went wrong
                        }
                    } else {
                        // something went wrong
                        // TODO log - monitor end
                    }
                }
            });
            job.schedule();
            job.join();

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
            RetrieveTaskOutputsJob job = new RetrieveTaskOutputsJob("Retrieving Task Results (" + requestId + ")", requestId,
                    this.modelFile);
            job.setUser(true);
            job.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
            job.schedule();
            job.join();

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
