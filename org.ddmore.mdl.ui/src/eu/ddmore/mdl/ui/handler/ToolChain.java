package eu.ddmore.mdl.ui.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

public class ToolChain {
	private List<String[]> commands = new ArrayList<String[]>();
	private StringBuffer lastOutput;
	private double executionTime;
	private int sleep = 500;
	private boolean printOutput = true;
	
	
	public void add(String... command) {
		commands.add(command);
	}
	
	public IStatus execute(IProgressMonitor monitor, boolean waitForLast) {
		
		monitor.beginTask("Executing Tool Chain", commands.size());
		executionTime = System.currentTimeMillis();
		
		for (int i=0; i<commands.size(); i++) {
			File file = new File(commands.get(i)[0]);
			monitor.subTask(file.getName() + "...");
			try {
				int exit = invoke(commands.get(i), new SubProgressMonitor(monitor,1), waitForLast || (i<commands.size()-1));
				if (exit!=0) {
					Exception e = new RuntimeException(commandString(commands.get(i)) + "\n" + lastOutput.toString());
					executionTime = System.currentTimeMillis() - executionTime;
					return new Status(Status.ERROR, "org.ddmore.mdl.ui", file.getName() + " return exit code " + exit + ".", e);
				}
			} catch (Exception e) {
				executionTime = System.currentTimeMillis() - executionTime;
				return new Status(Status.ERROR, "org.ddmore.mdl.ui", "Error invoking " + file.getName() + 
									". For more information, see the error log.", e);
			}
			if (monitor.isCanceled()) {
				executionTime = System.currentTimeMillis() - executionTime;
				return Status.CANCEL_STATUS;
			}
		}
		
		executionTime = System.currentTimeMillis() - executionTime;
		monitor.done();
		return Status.OK_STATUS;
	}
	
	
	protected int invoke(String command[], IProgressMonitor monitor, boolean wait) throws Exception {
				
		// Some debug output.
		if (printOutput) {
			System.out.println(commandString(command));
		}
		
		// Name of the program.
		String program = new File(command[0]).getName();
		monitor.beginTask(program, 1);
		
		// Create the new process.
		Process process = Runtime.getRuntime().exec(command);
		if (!wait) {
			monitor.worked(1); monitor.done();
			return 0;
		}
		
		// Read output.
		BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		BufferedReader out = new BufferedReader(new InputStreamReader(process.getInputStream()));
		lastOutput = new StringBuffer();
		
		while (true) {
			
			// Forward the output to System.out			
			if (printOutput) {
				while (err.ready() || out.ready()) {
					String line;
					if (err.ready()) {
						line = err.readLine();
					} else {
						line = out.readLine();
					}
					System.out.println(line);
					lastOutput.append(line + CR);
					if (!line.trim().equals("")) monitor.subTask(program + "... " + line);
					Thread.sleep(50);
				}
			}
        	
			// Check if it is terminated already:
        	try {
        		int exit = process.exitValue();
    			monitor.worked(1); 
    			monitor.done();
    			return exit;
        	}
        	catch (Exception e) {}
        	
        	// Check if it was cancelled.
        	if (monitor.isCanceled()) {
        		process.destroy();
        		return 0;
        	}
        	if (sleep>0) {
        		Thread.sleep(sleep);
        	}
		}
	}
	
	public String getLastOutput() {
		return lastOutput.toString();
	}
	
	private String commandString(String[] command) {
		String com = "";
		for (String c : command) com = com + c + " ";
		return com;
	}
	
	public double getExecutionTime() {
		return executionTime;
	}
	
	public void setSleep(int millis) {
		this.sleep = millis;
	}
	
	public void setPrintOutput(boolean printOutput) {
		this.printOutput = printOutput;
	}
	
	private static final String CR = System.getProperty("line.separator");

}
