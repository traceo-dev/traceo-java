package com.traceo.sdk.http;

import com.traceo.sdk.logging.ClientLogger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public class HttpAsyncClient {
    private static final ClientLogger LOGGER = new ClientLogger(HttpAsyncClient.class);

    public static final HttpAsyncClientFactory httpClientFactory = new HttpAsyncClientFactory();
    private final HttpRequestFactory httpRequestFactory = new HttpRequestFactory();

    public final CloseableHttpAsyncClient httpClient;
    private final HttpClientConfiguration configuration;

    private CountDownLatch latch;

    public HttpAsyncClient() {
        this(new HttpClientConfiguration());
    }

    public HttpAsyncClient(HttpClientConfiguration config) {
        this.configuration = config;
        this.httpClient = httpClientFactory.create(config);

        this.httpClient.start();
    }

    public Future<HttpResponse> executeAsync(IRequest<?> request, FutureCallback<HttpResponse> callback) {
        HttpRequestBase requestBase = httpRequestFactory.create(request, configuration);
        return httpClient.execute(requestBase, callback);
    }

    public Future<HttpResponse> executeAsync(IRequest<?> request, HttpContext context, FutureCallback<HttpResponse> callback) {
        HttpRequestBase requestBase = httpRequestFactory.create(request, configuration);
        return httpClient.execute(requestBase, context, callback);
    }

    public Future<HttpResponse> executeAsync(IRequest<?> request) {
        Future<HttpResponse> futureResponse = null;
        latch = new CountDownLatch(1);
        HttpRequestBase requestBase = httpRequestFactory.create(request, configuration);

        try {
            futureResponse = httpClient.execute(requestBase, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse httpResponse) {
                    int statusCode = httpResponse.getStatusLine().getStatusCode();

                    if (statusCode != 200) {
                        LOGGER.error("Failed to send request. Received status code: {}", statusCode);
                    } else {
                        LOGGER.log("Request has been send successfully.");
                    }

                    latch.countDown();
                }

                @Override
                public void failed(Exception e) {
                    LOGGER.logThrowableWithMessage("Failed to send requests. ", e);
                    latch.countDown();
                }

                @Override
                public void cancelled() {
                    LOGGER.log("Request has been cancelled.");
                    latch.countDown();
                }
            });

            latch.await();
        } catch (Throwable e) {
            LOGGER.logThrowableWithMessage("Error while making http request.", e);
            latch.countDown();
        }

        return futureResponse;
    }

    public void closeHttpClient() throws IOException {
        LOGGER.log("Http client has been shutdown.");
        this.httpClient.close();
    }
}
