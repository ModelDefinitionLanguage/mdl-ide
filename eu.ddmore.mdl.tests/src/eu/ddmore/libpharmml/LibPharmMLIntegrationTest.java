package eu.ddmore.libpharmml;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests integration with libPharmML
 */
public class LibPharmMLIntegrationTest {

    private static final Logger LOG = Logger.getLogger(LibPharmMLIntegrationTest.class);

    @Test
    public void shouldValidatePharmMLResource() throws IOException {
        ILibPharmML libPharmML = PharmMlFactory.getInstance().createLibPharmML();
        InputStream in = null;
        
        String modelFile = "file:src/eu/ddmore/libpharmml/UseCase1.xml";
        URL url = null;
        try {
            url = new URL(modelFile);
            in = url.openStream();
            IPharmMLResource res = libPharmML.createDomFromResource(in);
            IPharmMLValidator validator = libPharmML.getValidator();
            IValidationReport rpt = validator.createValidationReport(res);
            if (rpt.isValid()) {
                LOG.info(modelFile + " is valid");
            } else {
                for (int i = 1; i <= rpt.numErrors(); i++) {
                    IValidationError err = rpt.getError(i);
                    LOG.info("Error " + (i) + ": " + err.getErrorMsg());
                }
                fail("The validation failed");
            }
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
