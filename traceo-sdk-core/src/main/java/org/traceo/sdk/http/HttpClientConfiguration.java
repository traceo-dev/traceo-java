package org.traceo.sdk.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class for http clients configuration.
 */
public class HttpClientConfiguration {
    /**
     * Headers used in request.
     */
    private Map<String, String> headers = new HashMap<String, String>();

    /**
     * Host on which Traceo Platform is running.
     */
    private String host = null;

    /**
     * Custom user agent defined for request.
     */
    private String userAgent = null;

    /**
     * Should be used data compression.
     */
    private boolean isCompression = true;

    /**
     * The default timeout for a request. This is disabled by default.
     */
    private int connectionRequestTimeout = 5000;

    /** The default timeout for creating new connections. */
    private int connectionTimeout = 5000;

    /** The default timeout for reading from a connected socket. */
    private int socketTimeout = 5000;

    /** Max redirections count from the 3xx status codes. **/
    private int maxRedirects = 5;

    /** Max connection per route.  **/
    private int maxConnPerRoute = 5;

    /** The default max connection pool size. */
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
        this.isCompression = other.isCompression();
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

    public void setCompression(boolean compression) {
        this.isCompression = compression;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isCompression() {
        return isCompression;
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
