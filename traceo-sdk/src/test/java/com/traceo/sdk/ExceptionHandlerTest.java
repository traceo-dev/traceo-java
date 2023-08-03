package com.traceo.sdk;

import com.traceo.sdk.client.TraceoClient;
import com.traceo.sdk.client.TraceoClientBuilder;
import com.traceo.sdk.client.TraceoClientConfiguration;
import com.traceo.sdk.exceptionHandler.ExceptionHandler;
import org.junit.Before;
import org.junit.Test;

public class ExceptionHandlerTest {

    @Before
    public void setUp() {
        TraceoClientConfiguration configs = TraceoClientBuilder
                .standard()
                .withApiKey("xxx")
                .withHost("yyy")
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
            ExceptionHandler.captureException(exception);
        }
    }
}