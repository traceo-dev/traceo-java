package org.traceo.sdk;

import org.traceo.sdk.client.TraceoClient;
import org.traceo.sdk.client.TraceoClientBuilder;
import org.traceo.sdk.client.TraceoClientConfiguration;
import org.traceo.sdk.logging.client.TraceoLogger;
import org.junit.Before;
import org.junit.Test;

public class IncidentHandlerTest {

    @Before
    public void setUp() {
        TraceoClientConfiguration configs = TraceoClientBuilder
                .standard()
                .withApiKey("tr_408917b2-42fb-43d9-8602-861879c1a273")
                .withHost("http://localhost:3000")
                .withDebug(true)
                .withCatchUncaughtException(true)
                .build();

        TraceoClient.init(configs);
    }

    @Test
    public void testCaptureException() throws ClassNotFoundException {
        try {
            throw new ArrayIndexOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException exception) {
            TraceoClient.catchException("CO TO KURWA MA BYĆ", exception);
        }
    }

    @Test
    public void testLogger() {
        TraceoLogger traceoLogger = TraceoClient.getLogger(HttpClientTest.class.getName());
        for (int i=0; i< 50; i++) {
            traceoLogger.log("xxxxxxxxxxxxxx", "arg1", "arg2", "arg3");
        }
    }
}