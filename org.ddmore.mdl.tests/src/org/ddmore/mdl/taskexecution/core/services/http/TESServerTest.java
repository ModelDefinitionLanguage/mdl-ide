package org.ddmore.mdl.taskexecution.core.services.http;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.http.NameValuePair;
import org.ddmore.mdl.ui.preference.MDLPreferenceConstants;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.junit.Test;

/**
 * Tests server
 * 
 * @author mrogalski
 *
 */
public class TESServerTest {

    @Test
    public void shouldBuildAListOfRESTParameters() {
        // XXX this hardcodes the bundle symbolicname under test, not ideal but as a test it'll break if it changes and easy fix.
        IPreferenceStore preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, "org.ddmore.mdl.ui");

        // must have set the fileshare first
        preferenceStore.setValue(MDLPreferenceConstants.TES_SHARED_DIR, "testpath");
        preferenceStore.setValue(MDLPreferenceConstants.TES_TOOL_SHARED_DIR, "testpath-remote");

        List<NameValuePair> parameters = TESServer.getExecuteParameters("REQUEST_ID", "EXEC_FILE");
        assertNotNull(parameters);
        assertTrue(parameters.size() > 0);
    }
}
