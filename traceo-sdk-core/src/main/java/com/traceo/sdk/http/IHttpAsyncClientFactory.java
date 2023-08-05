package com.traceo.sdk.http;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

public interface IHttpAsyncClientFactory {
    CloseableHttpAsyncClient create(HttpClientConfiguration configs);
}
