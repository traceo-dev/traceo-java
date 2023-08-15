package org.traceo.sdk.http;

import org.traceo.sdk.logging.internal.SDKLogger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.protocol.HttpContext;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Http client to perform async http operations.
 */
public class HttpAsyncAsyncClient implements IHttpAsyncClient, Closeable {
    private static final SDKLogger LOGGER = new SDKLogger(HttpAsyncAsyncClient.class);

    public static final HttpAsyncClientFactory httpClientFactory = new HttpAsyncClientFactory();
    private final HttpRequestFactory httpRequestFactory = new HttpRequestFactory();

    public final CloseableHttpAsyncClient httpClient;
    private final HttpClientConfiguration configuration;

    public HttpAsyncAsyncClient() {
        this(new HttpClientConfiguration());
    }

    public HttpAsyncAsyncClient(HttpClientConfiguration config) {
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
        HttpRequestBase requestBase = httpRequestFactory.create(request, configuration);

        return httpClient.execute(requestBase, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                if (statusCode >= 200 && statusCode <= 300) {
                    LOGGER.log("Request has been send successfully.");
                } else {
                    LOGGER.error("Failed to send request. Received status code: {}", statusCode);
                }
            }

            @Override
            public void failed(Exception e) {
                LOGGER.logThrowableWithMessage("Failed to send requests. ", e);
            }

            @Override
            public void cancelled() {
                LOGGER.log("Request has been cancelled.");
            }
        });
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
