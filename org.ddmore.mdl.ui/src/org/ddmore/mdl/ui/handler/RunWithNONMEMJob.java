package org.ddmore.mdl.ui.handler;

import java.io.File;

import org.ddmore.mdl.ui.preference.MDLPreferences;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.swt.SWT;

public class RunWithNONMEMJob extends Job {
	public static final String NAME = "Execute model in NONMEM";
	private IFile model = null;
	private IFile data = null;

	public RunWithNONMEMJob(IFile model, IFile data) {
		super(NAME);
		this.model = model;
		this.data = data;
	}

	public RunWithNONMEMJob(IFile model) {
		super(NAME);
		this.model = model;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Processing NONMEM control file", 10);
		IStatus status = Status.OK_STATUS;
		try {
			monitor.worked(1);
			ToolChain chain = new ToolChain();
			
			String nonmemPath = MDLPreferences.getNONMEMHome();
			if (nonmemPath != null) {
				nonmemPath = nonmemPath.trim();
				if (nonmemPath.length() > 0) {
					nonmemPath = nonmemPath + File.separator + "bin" + File.separator;				
				}
			} else nonmemPath = "";
			String suffix = SWT.getPlatform().startsWith("win") ? ".exe" : "";
			String execCmd = nonmemPath + "nmgo" + suffix;

			//Testing
			execCmd = "C://Programs/RStudio/bin/rstudio.exe";

			String modelPath = model.getRawLocation().toOSString();
			if (data == null){
				chain.add(execCmd, modelPath);
			} else {
				String dataPath = data.getRawLocation().toOSString();
				chain.add(execCmd, modelPath, dataPath);
			}
			
			status = chain.execute(new SubProgressMonitor(monitor,9), false);
		} catch (Exception e) {
			monitor.done();
			return new Status(IStatus.ERROR, "org.ddmore.mdl.ui.handler.RunWithNONMEMJob", "Error while executing a command", e);
		}
		monitor.done();
		return status;
	}
}
