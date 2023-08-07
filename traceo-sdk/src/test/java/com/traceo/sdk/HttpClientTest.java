package com.traceo.sdk;

import com.traceo.sdk.logging.client.TraceoLogger;
import com.traceo.sdk.http.HttpAsyncClient;
import com.traceo.sdk.http.HttpClient;
import com.traceo.sdk.client.TraceoClient;
import com.traceo.sdk.client.TraceoClientBuilder;
import com.traceo.sdk.client.TraceoClientConfiguration;
import com.traceo.sdk.logging.internal.SDKLogger;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HttpClientTest {

    private final static SDKLogger LOGGER = new SDKLogger(HttpClient.class);

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
    public void testLogger() {
        TraceoLogger traceoLogger = TraceoClient.getLogger(HttpClientTest.class.getName());
        for (int i=0; i< 50; i++) {
            traceoLogger.log("xxxxxxxxxxxxxx", i);
        }
    }

    @Test
    public void testSyncExecute() throws IOException {
        HttpClient httpClient = new HttpClient();
        TestIRequest request = new TestIRequest();
        httpClient.execute(request);
    }

    @Test
    public void testAsyncExecute() throws ExecutionException, InterruptedException {
        HttpAsyncClient httpClient = new HttpAsyncClient();
        TestIRequest request = new TestIRequest();
        Future<HttpResponse> httpResponseFuture = httpClient.execute(request);
        HttpResponse response = httpResponseFuture.get();
    }

    @Test
    public void testLoggerException()  {
        try {
            throw new Throwable("Test throwable message");
        } catch (Throwable e) {
            LOGGER.logThrowableWithMessage("WTF?: ", e);
            LOGGER.logThrowable(e);
        }
    }
}