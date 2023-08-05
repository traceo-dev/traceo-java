package com.traceo.sdk;

import com.traceo.sdk.http.HttpMethod;
import com.traceo.sdk.http.IRequest;

import java.util.HashMap;
import java.util.Map;

public class DefaultRequest<T> implements IRequest<T> {

    private T content;

    private Map<String, String> headers = new HashMap<>();

    private HttpMethod httpMethod = HttpMethod.POST;

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