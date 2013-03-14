package org.ddmore.mdl.taskexecution.core.services;

import java.util.List;
import org.eclipse.core.resources.IFolder;
import java.util.UUID;

import org.eclipse.core.resources.IFile;

public class MockJobService implements JobService {
	private final static int WORKLOOP = 10;

	@Override
	//That will be called by Publish Task Inputs Job
	public String submit(List<IFile> file) {

		for(int i=0;i<WORKLOOP;i++) {
			System.out.println("Mocked publishing " + i);
			
		}
		return UUID.randomUUID().toString();
	}

	@Override
	//Monitor Task Progress Job
	public JobStatus getStatus(String jobId) {

		return JobStatus.values()[(int)Math.floor(Math.random()*(JobStatus.values().length-1))];
	}

	@Override
	//Retrieve Task Outputs Job
	public void retrieveResults(String requestID, IFolder targetDirectory) {

		for(int i=0;i<WORKLOOP;i++) {
			System.out.println("Mocked retrieval of files " + i);
			
		}
	}

}
