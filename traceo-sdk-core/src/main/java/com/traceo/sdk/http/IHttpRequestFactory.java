package org.traceo.sdk.http;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * An interface to create request used by {@link HttpAsyncAsyncClient} or {@link org.traceo.sdk.http.HttpClient}
 */
public interface IHttpRequestFactory {

    /**
     * Factory method to create http request accepting by http client.
     * @param IRequest
     * @param settings
     * @return {@link org.apache.http.client.methods.HttpRequestBase} accepting by underlying http client for sdk.
     */
    HttpRequestBase create(IRequest<?> IRequest, HttpClientConfiguration settings);
}
