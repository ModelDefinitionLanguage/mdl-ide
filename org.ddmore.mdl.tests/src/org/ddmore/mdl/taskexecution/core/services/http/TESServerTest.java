package org.ddmore.mdl.taskexecution.core.services.http;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.http.NameValuePair;
import org.ddmore.mdl.taskexecution.core.services.http.TESServer;
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
		List<NameValuePair> parameters = TESServer.getExecuteParameters("REQUEST_ID","EXEC_FILE");
		assertNotNull(parameters);
		assertTrue(parameters.size()>0);
	}

}
