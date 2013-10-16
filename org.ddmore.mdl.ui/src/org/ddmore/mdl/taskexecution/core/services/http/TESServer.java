package org.ddmore.mdl.taskexecution.core.services.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.ddmore.mdl.ui.internal.MdlActivator;
import org.ddmore.mdl.ui.preference.MDLPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.utils.encrypt.DesEncrypter;
import com.mango.mif.utils.encrypt.EncryptionException;

/**
 * @author jcarr
 */
public class TESServer {

    private static final IPreferenceStore PREFERENCE_STORE = MdlActivator.getInstance().getPreferenceStore();

    private static final String TEMPLATE_URL = "http://<host>:<port>/<service>";

    private static final String JOBSUBMISSION_URL = "/jobsubmission";
    private static final String JOBSERVICE_URL = "/jobService";

    private static final Logger LOGGER = Logger.getLogger(TESServer.class);

    private static final String MIF_ENCRYPTION_KEY_PROP = "mif.encryption.key";

    private static DesEncrypter desEncrypter;

    private final transient HttpClient client = new DefaultHttpClient();

    public String exec(final String requestId, final String execFile) {
        final String url = getBaseURL() + JOBSUBMISSION_URL + "/execute";

        HttpPost httpPost = new HttpPost(url);
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(getExecuteParameters(requestId, execFile), "UTF-8");

            httpPost.setHeader("accept", "*");
            httpPost.setEntity(entity);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Problem building exec post", e);
        }

        try {
            HttpResponse response = client.execute(httpPost);
            return IOUtils.toString(response.getEntity().getContent());

        } catch (IOException e) {
            LOGGER.error("Problem sending exec post", e);
        }

        return null;
    }

    public static List<NameValuePair> getExecuteParameters(final String requestId, final String execFile) {

        ExecutionRequestBuilder builder = new ExecutionRequestBuilder();

        builder.setRequestId(requestId);
        // FIXME
        if (execFile.endsWith(".R")) {
            builder.setExecutionType(ExecutionType.R_Script.toString());
            builder.setCommand("/opt/mango/R/2.13.1/bin/Rscript");
            builder.setGridHostPreamble("export R_LIBS_USER=/opt/mango/Rpackages/ddmore");
        } else {
            builder.setExecutionType(ExecutionType.NMFE.toString());
        }
        builder.setExecutionFile(execFile);
        builder.setUserName(getUsername());
        builder.setUserPassword(getPassword());
        builder.setSubmitAsUserMode(true);

        Map<String, String> attributes = new HashMap<String, String>(2);
        attributes.put("EXECUTION_HOST_FILESHARE", getSharedDir());
        attributes.put("EXECUTION_HOST_FILESHARE_REMOTE", getToolSharedDir());
        builder.setRequestAttributes(attributes);

        String executionMessage = null;

        try {
            executionMessage = builder.getExecutionRequestMsg();
        } catch (JAXBException e) {
            LOGGER.error("Problem building execution message", e);
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
            case PROCESSING:
            case PROCESSING_FINISHED:
            case SCHEDULED:
                return TESRequestStatus.running;
            default:
                LOGGER.error("Unexpected return status " + responseString);
            }

        } catch (IOException e) {
            LOGGER.error("Problem getting response status", e);
        }

        return TESRequestStatus.failed;
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

    private static String getUsername() {
        return PREFERENCE_STORE.getString(MDLPreferenceConstants.TES_UNAME);
    }

    private static String getSharedDir() {
        return PREFERENCE_STORE.getString(MDLPreferenceConstants.TES_SHARED_DIR);
    }

    private static String getToolSharedDir() {
        return PREFERENCE_STORE.getString(MDLPreferenceConstants.TES_TOOL_SHARED_DIR);
    }

    private static String getPassword() {
        try {
            return getDesEncrypter().encrypt(PREFERENCE_STORE.getString(MDLPreferenceConstants.TES_PWORD));
        } catch (EncryptionException e) {
            throw new IllegalStateException(e);
        }
    }

    private static synchronized DesEncrypter getDesEncrypter() throws EncryptionException {
        if (desEncrypter != null) {
            return desEncrypter;
        }
        if (System.getProperties().containsKey(MIF_ENCRYPTION_KEY_PROP)) {
            desEncrypter = new DesEncrypter(System.getProperty(MIF_ENCRYPTION_KEY_PROP));
        } else {
            desEncrypter = new DesEncrypter();
        }
        return desEncrypter;
    }
}
