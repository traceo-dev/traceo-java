package com.traceo.sdk.http;

import com.traceo.sdk.logging.internal.SDKLogger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.protocol.HttpContext;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * Http client to perform async http operations.
 */
public class HttpAsyncClient implements IHttpClient<Future<HttpResponse>, FutureCallback<HttpResponse>>, Closeable {
    private static final SDKLogger LOGGER = new SDKLogger(HttpAsyncClient.class);

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

    /**
     * Execute async operation to sent request.
     * @param request Implementation of {@link IRequest<?>} interface.
     * @param callback Callback used after async operation.
     * @return {@link Future<HttpResponse>}
     */
    @Override
    public Future<HttpResponse> execute(IRequest<?> request, FutureCallback<HttpResponse> callback) {
        return execute(request, callback, null);
    }

    /**
     * Execute async operation to sent request.
     * @param request Implementation of {@link IRequest<?>} interface.
     * @param context Provided {@link HttpContext} for custom implementation.
     * @return {@link Future<HttpResponse>}
     */
    @Override
    public Future<HttpResponse> execute(IRequest<?> request, HttpContext context) {
        return execute(request, null, context);
    }

    /**
     * Execute async operation to sent request.
     * @param request Implementation of {@link IRequest<?>} interface.
     * @param context Provided {@link HttpContext} for custom implementation.
     * @param callback Callback used after async operation.
     * @return {@link Future<HttpResponse>}
     */
    @Override
    public Future<HttpResponse> execute(IRequest<?> request, FutureCallback<HttpResponse> callback, HttpContext context) {
        HttpRequestBase requestBase = httpRequestFactory.create(request, configuration);
        return httpClient.execute(requestBase, context, callback);
    }

    /**
     * Execute async operation to sent request.
     * @param request Implementation of {@link IRequest<?>} interface.
     * @return {@link Future<HttpResponse>}
     */
    @Override
    public Future<HttpResponse> execute(IRequest<?> request) {
        Future<HttpResponse> futureResponse = null;
        latch = new CountDownLatch(1);
        HttpRequestBase requestBase = httpRequestFactory.create(request, configuration);

        try {
            futureResponse = httpClient.execute(requestBase, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse httpResponse) {
                    int statusCode = httpResponse.getStatusLine().getStatusCode();

                    if (statusCode >= 200 && statusCode <= 300) {
                        LOGGER.log("Request has been send successfully.");
                    } else {
                        LOGGER.error("Failed to send request. Received status code: {}", statusCode);
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

    @Override
    public void close() throws IOException {
        closeHttpClient();
    }
}
