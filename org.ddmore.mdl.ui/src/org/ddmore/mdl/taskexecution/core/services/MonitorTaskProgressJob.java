/**
 * 
 */
package org.ddmore.mdl.taskexecution.core.services;

import org.ddmore.mdl.taskexecution.core.services.http.TESRequestStatus;
import org.ddmore.mdl.taskexecution.core.services.http.TESServer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * A long-running Job responsible for checking status of a TES Job and tailing standard output and error streams of the remote execution.
 * @author jcarr
 */
public class MonitorTaskProgressJob extends Job {

    private final transient String jobId;

    private transient TESRequestStatus requestStatus;

    public MonitorTaskProgressJob(final String name, final String jobId) {
        super(name);
        this.jobId = jobId;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        monitor.subTask("Task Execution Service: Monitor");
        IStatus status = Status.OK_STATUS;
        try {
            TESServer serverConn = new TESServer();
            do {
                this.requestStatus = serverConn.getRequestStatus(jobId);
                Thread.sleep(1500);
            } while (TESRequestStatus.running.equals(this.requestStatus));
        } catch (Exception ex) {
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Error while executing a command", ex);
        }
        return status;
    }

    public TESRequestStatus getStatus() {
        return requestStatus;
    }
}
