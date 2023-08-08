package com.traceo.sdk;

import com.traceo.sdk.client.TraceoClient;
import com.traceo.sdk.client.TraceoClientBuilder;
import com.traceo.sdk.client.TraceoClientConfiguration;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class OpenTelemetryTest extends TestCase {

    @Before
    public void setUp() {
        TraceoClientConfiguration configs = TraceoClientBuilder
                .standard()
                .withApiKey("xxx")
                .withHost("https://webhook.site/488164a0-5467-4621-9212-2974cc3eebc7/")
                .withDebug(true)
                .build();

        TraceoClient.init(configs);
    }

    @Test
    public void testOtelMetrics() {

    }
}
