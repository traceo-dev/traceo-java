package org.traceo.sdk.http;

/**
 * An interface to create asynchronous http client instance.
 */
public interface IHttpClientFactory<T> {

    /**
     * Factory method to create asynchronous http instance with default http configs.
     * @return {@link T instance}
     */
    T create();

    /**
     * Factory method to create asynchronous http instance.
     * @param configs Configuration for http client.
     * @return {@link T instance}
     */
    T create(HttpClientConfiguration configs);
}
