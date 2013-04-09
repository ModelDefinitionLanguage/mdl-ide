package org.ddmore.mdl.taskexecution.core.services.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.ddmore.mdl.ui.internal.MdlActivator;
import org.ddmore.mdl.ui.preference.MDLPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * @author jcarr
 */
public class TESServer {

    private static final IPreferenceStore PREFERENCE_STORE = MdlActivator.getInstance().getPreferenceStore();

    private static final String TEMPLATE_URL = "http://<host>:<port>/<service>";

    private static final String JOBSUBMISSION_URL = "/jobsubmission";
    private static final String JOBSERVICE_URL = "/jobService";

    private final transient HttpClient client = new DefaultHttpClient();

    public String prepare() {
        final String url = getBaseURL() + JOBSUBMISSION_URL + "/prepare";

        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(getPrepareParameters()));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            HttpResponse response = client.execute(httpPost);
            return IOUtils.toString(response.getEntity().getContent());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private String getBaseURL() {
        // TODO get once and put a listener
        final String host = PREFERENCE_STORE.getString(MDLPreferenceConstants.TES_HOST);
        final int port = PREFERENCE_STORE.getInt(MDLPreferenceConstants.TES_PORT);
        final String service = PREFERENCE_STORE.getString(MDLPreferenceConstants.TES_SERVICE);

        String url = TEMPLATE_URL.replaceAll("<host>", host);
        url = url.replaceAll("<port>", String.valueOf(port));
        url = url.replaceAll("<service>", service);
        return url;
    }

    private static List<NameValuePair> getPrepareParameters() {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("userName", getUsername()));
        nameValuePairs.add(new BasicNameValuePair("encryptedPassword", getPassword()));
        return nameValuePairs;
    }

    private static String getUsername() {
        return PREFERENCE_STORE.getString(MDLPreferenceConstants.TES_UNAME);
    }

    private static String getPassword() {
        return EncrypterFactory.getEncrypter().encrypt(PREFERENCE_STORE.getString(MDLPreferenceConstants.TES_PWORD));
    }

    public String exec(final String requestId, final String execFile) {
        final String url = getBaseURL() + JOBSUBMISSION_URL + "/execute";

        HttpPost httpPost = new HttpPost(url);
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(getExecuteParameters(requestId, execFile), "UTF-8");

            httpPost.setHeader("accept", "*");
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            HttpResponse response = client.execute(httpPost);
            return IOUtils.toString(response.getEntity().getContent());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    static List<NameValuePair> getExecuteParameters(final String requestId, final String execFile) {

        ExecutionRequestBuilder builder = new ExecutionRequestBuilder();

        builder.setRequestId(requestId);
        builder.setExecutionType(ExecutionType.NMFE.toString());
        builder.setExecutionFile(execFile);
        builder.setUserName(getUsername());
        builder.setUserPassword(getPassword());

        String executionMessage = null;

        try {
            executionMessage = builder.getExecutionRequestMsg();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("executionRequest", executionMessage));
        return nameValuePairs;
    }

    public TESRequestStatus getRequestStatus(final String requestId) {
        final String url = getBaseURL() + JOBSERVICE_URL + "/jobs/info/status/" + requestId;

        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            String responseString = IOUtils.toString(response.getEntity().getContent());

            switch (JobStatus.valueOf(responseString)) {
            case COMPLETED:
                return TESRequestStatus.completed;
            case CANCELLED:
                // TODO a cancelled status
            case FAILED:
                return TESRequestStatus.failed;
            case NEW:
            case NOT_AVAILABLE:
            case RUNNING:
                return TESRequestStatus.running;
            default:
                System.out.println("Unexpected return status " + responseString);
            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return TESRequestStatus.failed;
    }

}
