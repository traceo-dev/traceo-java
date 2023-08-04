package com.traceo.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traceo.sdk.http.HttpClient;
import com.traceo.sdk.http.HttpClientConfiguration;
import com.traceo.sdk.client.TraceoClient;
import com.traceo.sdk.client.TraceoClientBuilder;
import com.traceo.sdk.client.TraceoClientConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HttpClientTest {

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
    public void testExecute() throws IOException {
        HttpClient httpClient = new HttpClient();
        TestIRequest request = new TestIRequest();
        httpClient.execute(request);
    }
}