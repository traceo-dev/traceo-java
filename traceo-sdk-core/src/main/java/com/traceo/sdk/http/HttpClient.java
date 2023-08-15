package org.traceo.sdk.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Http client to perform sync http operations.
 *
 */
public class HttpClient implements Closeable {

    public static final HttpClientFactory httpClientFactory = new HttpClientFactory();
    private final HttpRequestFactory httpRequestFactory = new HttpRequestFactory();

    private final CloseableHttpClient httpClient;
    private final HttpClientConfiguration configuration;

    public HttpClient() {
        this(new HttpClientConfiguration());
    }

    public HttpClient(HttpClientConfiguration config) {
        this.configuration = config;
        this.httpClient = httpClientFactory.create(config);
    }

    /**
     * Execute sync operation to sent request.
     * @param request Implementation of {@link IRequest<?>} interface.
     * @return {@link CloseableHttpResponse}
     */
    public CloseableHttpResponse execute(IRequest<?> request) throws IOException {
        return execute(request, null, null);
    }

    /**
     * Execute sync operation to sent request.
     * @param request Implementation of {@link IRequest<?>} interface.
     * @param responseHandler {@link ResponseHandler}
     * @return {@link CloseableHttpResponse}
     */
    public CloseableHttpResponse execute(IRequest<?> request, ResponseHandler<?> responseHandler) throws IOException {
        return execute(request, responseHandler, null);
    }

    /**
     * Execute sync operation to sent request.
     * @param request Implementation of {@link IRequest<?>} interface.
     * @param context Provided {@link HttpContext} for custom implementation.
     * @return {@link CloseableHttpResponse}
     */
    public CloseableHttpResponse execute(IRequest<?> request, HttpContext context) throws IOException {
        return execute(request, null, context);
    }

    /**
     * Execute sync operation to sent request.
     * @param request Implementation of {@link IRequest<?>} interface.
     * @param responseHandler {@link ResponseHandler}
     * @param context Provided {@link HttpContext} for custom implementation.
     * @return {@link CloseableHttpResponse}
     */
    public CloseableHttpResponse execute(IRequest<?> request, ResponseHandler<?> responseHandler, HttpContext context) throws IOException {
        return (CloseableHttpResponse) httpClient.execute(httpRequestFactory.create(request, this.configuration), responseHandler, context);
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }
}
