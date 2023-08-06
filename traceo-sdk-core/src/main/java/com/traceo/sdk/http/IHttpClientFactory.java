package com.traceo.sdk.http;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * An interface to create http client instance.
 */
public interface IHttpClientFactory {
    /**
     * Factory method to create http incstance.
     * @param settings Configuration for http client.
     * @return {@link org.apache.http.impl.client.CloseableHttpClient instance}
     */
    CloseableHttpClient create(HttpClientConfiguration settings);
}
