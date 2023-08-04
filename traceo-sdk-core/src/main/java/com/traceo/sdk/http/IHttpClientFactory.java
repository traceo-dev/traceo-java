package com.traceo.sdk.http;

import org.apache.http.impl.client.CloseableHttpClient;

public interface IHttpClientFactory {
    CloseableHttpClient create(HttpClientConfiguration settings);
}
