package com.traceo.sdk;

import com.traceo.sdk.client.TraceoClient;
import com.traceo.sdk.client.TraceoClientBuilder;
import com.traceo.sdk.client.TraceoClientConfiguration;
import org.junit.Before;
import org.junit.Test;

public class IncidentHandlerTest {

    @Before
    public void setUp() {
        TraceoClientConfiguration configs = TraceoClientBuilder
                .standard()
                .withApiKey("xxx")
                .withHost("https://webhook.site/488164a0-5467-4621-9212-2974cc3eebc7/")
                .withExportIntervalMs(12345)
                .withMetricsCollection(true)
                .withOfflineMode(false)
                .build();

        TraceoClient.init(configs);
    }

    @Test
    public void testCaptureException() throws ClassNotFoundException {
        try {
            throw new NullPointerException("Bardzo fajny exception");
        } catch (NullPointerException exception) {
            TraceoClient.catchException("CO TO KURWA MA BYĆ", exception);
        }
    }
}