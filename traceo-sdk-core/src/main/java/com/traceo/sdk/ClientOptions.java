package com.traceo.sdk;

import com.traceo.sdk.http.IHttpAsyncClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Base client configuration class.
 */
public class ClientOptions {

    /**
     * Api key generated in Traceo project.
     */
    private String apiKey = null;

    /**
     * The host on which the Traceo instance is running.
     * Host should be in format: [protocol]://[domain]:[port]
     */
    private String host = null;

    /**
     * Specifies whether the SDK should be launched. If not then it will not collect any information.
     * After changing the settings, it is necessary to restart the entire application.
     */
    private boolean isEnabled = true;

    /**
     * Specifies if SDK should display internal logs.
     */
    private boolean isDebug = false;

    /**
     * Specifies if SDK should catch every uncaught exception.
     */
    private boolean isCatchUncaughtException = false;

    /**
     * List of handlers to run on SDK initialization.
     */
    private List<IHandler> handlers = new ArrayList<>();

    /**
     * Http client used to http requests.
     */
    private IHttpAsyncClient httpClient;

    public ClientOptions() {}

    public ClientOptions(ClientOptions configuration) {
        this.apiKey = configuration.getApiKey();
        this.host = configuration.getHost();
        this.isEnabled = configuration.isEnabled();
        this.isDebug = configuration.isDebug();
        this.isCatchUncaughtException = configuration.isCatchUncaughtException();
        this.handlers = configuration.getHandlers();
        this.httpClient = configuration.getHttpClient();
    }

    public void setHandlers(List<IHandler> handlers) {
        this.handlers = handlers;
    }

    public IHttpAsyncClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(IHttpAsyncClient httpClient) {
        this.httpClient = httpClient;
    }

    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    public List<IHandler> getHandlers() {
        return this.handlers;
    }

    public boolean isCatchUncaughtException() {
        return isCatchUncaughtException;
    }

    public void setCatchUncaughtException(boolean catchUncaughtException) {
        isCatchUncaughtException = catchUncaughtException;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getHost() {
        return this.host;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }
}
