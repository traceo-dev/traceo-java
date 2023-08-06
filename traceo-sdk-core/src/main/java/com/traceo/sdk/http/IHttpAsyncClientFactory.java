package com.traceo.sdk.http;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

/**
 * An interface to create asynchronous http client instance.
 */
public interface IHttpAsyncClientFactory {
    /**
     * Factory method to create asynchronous http instance.
     * @param configs Configuration for http client.
     * @return {@link org.apache.http.impl.nio.client.CloseableHttpAsyncClient instance}
     */
    CloseableHttpAsyncClient create(HttpClientConfiguration configs);
}
