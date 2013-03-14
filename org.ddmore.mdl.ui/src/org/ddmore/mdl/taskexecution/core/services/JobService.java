package org.ddmore.mdl.taskexecution.core.services;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public interface JobService {
	/**
	 * Publishes files to a shared location and submits a request to a TES instance
	 * @param files - input files
	 * @return a job id
	 */
	String submit(List<IFile> files);
	/**
	 * Retrieves status of a job
	 * @param jobId job id
	 * @return a status of a job
	 */
	JobStatus getStatus(String jobId);
	/**
	 * Retrieves result files from the TES shared location to a workspace
	 * @param jobId job id
	 * @param destination to which results should be copied to
	 */
	void retrieveResults(String jobId, IFolder destination);
}
