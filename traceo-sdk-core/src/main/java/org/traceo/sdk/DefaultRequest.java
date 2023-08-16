package org.traceo.sdk;

import org.traceo.sdk.http.HttpMethod;
import org.traceo.sdk.http.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link org.traceo.sdk.http.IRequest} interface.
 * This class should be used only for internal SDK operations.
 * @param <T> Content type class defined what should be sent in request.
 */
public class DefaultRequest<T> implements IRequest<T> {

    /**
     * Payload which should be sent in request.
     */
    private T content;

    /**
     * Headers which should be included in request.
     */
    private Map<String, String> headers = new HashMap<>();

    /**
     * {@link org.traceo.sdk.http.HttpMethod} for request.
     */
    private HttpMethod httpMethod = HttpMethod.POST;

    /**
     * Path used to build full URI which is needed for request.
     * URI is created with using provided host in {@link ClientOptions} concatenated with this value.
     */
    private String endpoint;

    public DefaultRequest() {}

    public DefaultRequest(String endpoint, T content) {
        this(endpoint, content, new HashMap<>());
    }

    public DefaultRequest(String endpoint, T content, Map<String, String> headers) {
        this(HttpMethod.POST, endpoint, content, headers);
    }

    public DefaultRequest(HttpMethod httpMethod, String endpoint, T content, Map<String, String> headers) {
        this.content = content;
        this.headers = headers;
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String getEndpoint() {
        return this.endpoint;
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public T getContent() {
        return this.content;
    }

    @Override
    public void setContent(T content) {
        this.content = content;
    }
}