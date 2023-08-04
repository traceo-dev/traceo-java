package com.traceo.sdk.http;

import java.util.HashMap;
import java.util.Map;

public class HttpClientConfiguration {
    private Map<String, String> headers = new HashMap<String, String>();

    private String host = null;

    private String userAgent = null;

    private boolean withCompression = true;

    private int connectionRequestTimeout = 5000;

    private int connectionTimeout = 5000;

    private int socketTimeout = 5000;

    private int maxRedirects = 5;

    // Maximum connections per route (host:port)
    private int maxConnPerRoute = 5;

    // Maximum total connections in the pool
    private int maxConnection = 100;

    public HttpClientConfiguration() {}

    public HttpClientConfiguration(HttpClientConfiguration other) {
        this.headers.putAll(other.getHeaders());
        this.host = other.getHost();
        this.connectionRequestTimeout = other.getConnectionTimeout();
        this.connectionTimeout = other.getConnectionTimeout();
        this.socketTimeout = other.getSocketTimeout();
        this.maxRedirects = other.getMaxRedirects();
        this.maxConnection = other.getMaxConnection();
        this.maxConnPerRoute = other.getMaxConnPerRoute();
        this.userAgent = other.getUserAgent();
        this.withCompression = other.isWithCompression();
    }

    public int getMaxConnPerRoute() {
        return maxConnPerRoute;
    }

    public void setMaxConnPerRoute(int maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
    }

    public int getMaxConnection() {
        return maxConnection;
    }

    public void setMaxConnection(int maxConnection) {
        this.maxConnection = maxConnection;
    }

    public void setWithCompression(boolean withCompression) {
        this.withCompression = withCompression;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isWithCompression() {
        return withCompression;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
