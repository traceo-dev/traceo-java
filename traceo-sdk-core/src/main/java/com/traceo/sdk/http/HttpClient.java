package com.traceo.sdk.http;

import org.apache.http.client.ResponseHandler;
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

    public void execute(IRequest<?> IRequest) throws IOException {
        this.httpClient.execute(httpRequestFactory.create(IRequest, this.configuration));
    }

    public void execute(IRequest<?> IRequest, ResponseHandler<Void> responseHandler) throws IOException {
        this.httpClient.execute(httpRequestFactory.create(IRequest, this.configuration), responseHandler);
    }

    public void execute(IRequest<?> IRequest, HttpContext httpContext) throws IOException {
        this.httpClient.execute(httpRequestFactory.create(IRequest, this.configuration), null, httpContext);
    }

    public void execute(IRequest<?> IRequest, ResponseHandler<Void> responseHandler, HttpContext context) throws IOException {
        this.httpClient.execute(httpRequestFactory.create(IRequest, this.configuration), responseHandler, context);
    }

    public void shutdown() throws IOException {
        httpClient.close();
    }
}
