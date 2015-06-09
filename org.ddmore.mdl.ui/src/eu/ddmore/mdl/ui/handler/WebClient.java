package eu.ddmore.mdl.ui.handler;

/*
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.xml.bind.JAXBException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

*/
//import com.mango.mif.domain.ExecutionRequestBuilder;
//import com.mango.mif.domain.ExecutionType;

public class WebClient {
	/*
	// the size of 1K in bytes
	private static final short ONE_KILOBYTE = 1024;

	// the special user-name for a job executed on the grid
	public static final String GRID_USER_NAME = "grid.user";

	// the encrypted user password
	public static final String GRID_USER_PASSWORD = "!x675bda1%z";

	// the shared folder location
	public static final String SHARED_FOLDER = System.getProperty("user.home");

	// configurable items - pass into the JVM using -D parameters
	private static final String SERVER_HOST = "server.host";
	private static final String SERVER_PORT = "server.port";
	private static final String EXECUTE_USER_NAME = "execute.user.name";
	private static final String EXECUTE_USER_PASSWORD = "execute.user.password";
	private static final String EXECUTE_FILE_NAME = "execute.file.name";
	private static final String EXECUTE_TIMEOUT = "execute.timeout";
	
	// the client connection
	private static final HttpClient httpClient = new HttpClient();
    
    // the base URL
	private static String BASE_URL = "http://<host>:<port>/Test/REST";

	// other service URLs
	private static final String JOBSUBMISSION_URL = "/jobSubmission";
	private static final String JOBSERVICE_URL = "/jobService";

	private static String INDEX_URL;
	private static String PREPARE_URL;
	private static String EXECUTE_URL;
	private static String STATUS_URL;

	// details of the user executing the job
	private static final String USER_NAME;
	private static final String USER_PASSWORD;

	// the preparation details - getting ready for job submission
	private static String requestId;
	private static String inputDir;
	private static String outputDir;

	// the execution directory and file - for job submission
	private static String executionDir;
	private static String executionFile;

	// the execution job id
	private static String jobId;


	static {
		
		// set server host/port
		String host = System.getProperty(SERVER_HOST);

		// use host override if provided, otherwise host=localhost
		if (null == host) {

			try {
				host = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

		}
		
		String port = System.getProperty(SERVER_PORT);

		// use port override if provided, otherwise port=8080
		if (null == port) {
			port="8080";
		}

		// set up the URLs
		BASE_URL = BASE_URL.replaceAll("<host>", host);
		BASE_URL = BASE_URL.replaceAll("<port>", port);

		INDEX_URL = BASE_URL + JOBSUBMISSION_URL;
		PREPARE_URL = BASE_URL + JOBSUBMISSION_URL + "/prepare";
		EXECUTE_URL = BASE_URL + JOBSUBMISSION_URL + "/execute";
		STATUS_URL = BASE_URL + JOBSERVICE_URL + "/jobs/info/status/{jobId}";

		// set user name/password, possible from system properties
		USER_NAME = getUserName();
		USER_PASSWORD = getUserPassword();
	}
	
	
	public static void main(String... args) {

        // send HTTP GET request to retrieve the schema
        storeServiceDetails(sendGetRequest(INDEX_URL));

        // send HTTP POST request to prepare the environment
        storePreparationDetails(sendPostRequest(PREPARE_URL, getPrepareParameters()));

        // put file in the previously prepared directory
        createExecuteFile();

        // send HTTP POST request to execute the job
        String response = sendPostRequest(EXECUTE_URL, getExecuteParameters());

		// if execution is on the grid check the status
		if (USER_NAME.equals(GRID_USER_NAME)) {

			// store away job details for later use
			storeJobDetails(response);

			// send HTTP GET requests to continuously check the job status
			checkJobStatus();

			// list the files returned by the job
			listReturnedFiles();
		}

        // tidy up
//		httpClient.getConnectionManager().shutdown();
	}


	private static String sendGetRequest(String url) {

		return processRequest(new GetMethod(url));
	}


	private static String sendPostRequest(String url, NameValuePair parameter) {

		NameValuePair[] parameters = {parameter};

		return sendPostRequest(url, parameters);
	}


	private static String sendPostRequest(String url, NameValuePair[] parameters) {

		PostMethod httpPost = new PostMethod(url);
		httpPost.setRequestBody(parameters);

		return processRequest(httpPost);
	}
	
	
	private static String processRequest(HttpMethodBase request) {

		String response = null;

		try {

			System.out.println("----------------------------------------");
			System.out.println("Server request:");
			System.out.println(request.getName() + " " + request.getURI());

			// display parameters for post request if present 
			if (request instanceof PostMethod) {

				NameValuePair[] parameters = ((PostMethod)request).getParameters();
				
				if (null != parameters) {

					System.out.println("with params:");

					for (int p = 0; p < parameters.length; p++) {
						System.out.println("--> " +	parameters[p].getName() + " = " + parameters[p].getValue());					
					}
				}
			}

			System.out.println();

			int status = httpClient.executeMethod(request);
			response = getServerResponse(request);

			System.out.println("Server response (status = " + status + "):");
			System.out.println(response);
			System.out.println("----------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
		return response;
	}


	private static String getServerResponse(HttpMethodBase request) throws IOException {

		InputStream responseStream = request.getResponseBodyAsStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] response = new byte[ONE_KILOBYTE];
		int charsRead = responseStream.read(response);

		while (charsRead == ONE_KILOBYTE) {
			baos.write(response);
			Arrays.fill(response, (byte) 0);
			charsRead = responseStream.read(response);
		}

		baos.write(response);

		return baos.toString().trim();
	}


	private static void storeServiceDetails(String response) {

		// Not sure if there's anything useful we can do here!

	}


	private static void storePreparationDetails(String response) {

		requestId = response;

        String requestDir = new File(SHARED_FOLDER + File.separator + requestId).getAbsolutePath();

        inputDir = requestDir + File.separator + "inputs";
        outputDir = requestDir + File.separator + "outputs";

	}


	private static void createExecuteFile() {
		
		try {

			File execFile = null;

			// create input file with name possibly based on a property
			String filename = System.getProperty(EXECUTE_FILE_NAME);

			// use filename override if provided, otherwise generate name
			if (null == filename) {
				execFile = File.createTempFile("TES-", ".in", new File(inputDir));
			} else {
				execFile = Files.createFile(Paths.get(inputDir, filename)).toFile();
			}

			executionDir = execFile.getParent();
			executionFile = execFile.getName();
			System.out.println("\nCreated file: " + executionDir + File.separator + executionFile + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static NameValuePair getExecuteParameters() {

		ExecutionRequestBuilder builder = new ExecutionRequestBuilder();
		
		builder.setRequestId(requestId);
		builder.setExecutionType(ExecutionType.NMFE.toString());
		builder.setExecutionFile(executionFile);
		builder.setUserName(USER_NAME);
		builder.setUserPassword(USER_PASSWORD);

		String executionMessage = null;
		
		try {
			executionMessage = builder.getExecutionRequestMsg();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return new NameValuePair("executionRequest", executionMessage);
	}


	private static NameValuePair[] getPrepareParameters() {

		return new NameValuePair[] {
			new NameValuePair("userName", USER_NAME),
			new NameValuePair("encryptedPassword", USER_PASSWORD)	
		};
	}


	private static String getUserName() {

		// set user-name, possibly based on system property
		String userName = System.getProperty(EXECUTE_USER_NAME);

		// use property override if provided, otherwise user.name = WebUtils.GRID_USER_NAME
		if (null == userName) {
			userName = GRID_USER_NAME;
		}

		return userName;
	}


	private static String getUserPassword() {

		// set user-password, possibly based on system property
		String userPassword = System.getProperty(EXECUTE_USER_PASSWORD);

		// use property override if provided, otherwise user.name = WebUtils.GRID_USER_PASSWORD
		if (null == userPassword) {
			userPassword = GRID_USER_PASSWORD;
		}

		return userPassword;
	}


	private static void storeJobDetails(String response) {

		jobId = response;

	}


	private static void checkJobStatus() {

		// create the required URL
		String jobStatusUrl = STATUS_URL.replaceAll("\\{jobId\\}", jobId);

		// use execution timeout override if provided, otherwise timeout=1000000ms
		int timeout = 1000000;

		String timeoutOverride = System.getProperty(EXECUTE_TIMEOUT);

		if (null != timeoutOverride) {

			try {
				timeout = Integer.parseInt(timeoutOverride);
			} catch (NumberFormatException nfe) {
				// do nothing, just continue with default timeout
			}
		}

		// keep polling until the job completes
		System.out.println("Polling until job completion ...");
		long start = System.currentTimeMillis();
		
		while (jobStillRunning(sendGetRequest(jobStatusUrl))) {

			long now = System.currentTimeMillis();

			if (now - start > timeout) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Job Complete!");
	}

	private static boolean jobStillRunning(String jobResponse) {

		return !jobResponse.equals("Done") && !jobResponse.equals("Failed");

	}


	private static void listReturnedFiles() {

		String files;
		File folder = new File(inputDir);
		File[] listOfFiles = folder.listFiles();

		System.out.println("\nFiles in " + inputDir + " returned by job ...");

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				System.out.println(files);
			}
		}
		
		System.out.println("\nDone");
	}
*/	
	public static void CallService(String serviceID){
		System.out.println("I called service " + serviceID + " for you, it says hi!");
	}

}



