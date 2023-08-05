package com.traceo.sdk.http;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class HttpClient {

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

    public CloseableHttpResponse execute(IRequest<?> request) throws IOException {
        return httpClient.execute(httpRequestFactory.create(request, this.configuration));
    }

    public <T> T execute(IRequest<T> request, ResponseHandler<T> responseHandler) throws IOException {
        return httpClient.execute(httpRequestFactory.create(request, this.configuration), responseHandler);
    }

    public <T> T execute(IRequest<T> request, HttpContext httpContext) throws IOException {
        return httpClient.execute(httpRequestFactory.create(request, this.configuration), null, httpContext);
    }

    public <T> T execute(IRequest<T> request, ResponseHandler<T> responseHandler, HttpContext context) throws IOException {
        return httpClient.execute(httpRequestFactory.create(request, this.configuration), responseHandler, context);
    }

    public void shutdown() throws IOException {
        httpClient.close();
    }
}
