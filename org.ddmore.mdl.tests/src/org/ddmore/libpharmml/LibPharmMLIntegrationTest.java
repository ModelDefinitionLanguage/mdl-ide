package org.ddmore.libpharmml;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import eu.ddmore.libpharmml.ILibPharmML;
import eu.ddmore.libpharmml.IPharmMLResource;
import eu.ddmore.libpharmml.IPharmMLValidator;
import eu.ddmore.libpharmml.IValidationError;
import eu.ddmore.libpharmml.IValidationReport;
import eu.ddmore.libpharmml.PharmMlFactory;

/**
 * Tests integration with libPharmML
 */
public class LibPharmMLIntegrationTest {

    private static final Logger LOG = Logger.getLogger(LibPharmMLIntegrationTest.class);

    @Test
    public void shouldValidatePharmMLResource() throws IOException {
        ILibPharmML libPharmML = PharmMlFactory.getInstance().createLibPharmML();
        InputStream in = null;
        
        String modelFile = "platform:/plugin/eu.ddmore.libpharmml-test-data/pharmml/spec_examples/example1_NONMEM.xml";
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
