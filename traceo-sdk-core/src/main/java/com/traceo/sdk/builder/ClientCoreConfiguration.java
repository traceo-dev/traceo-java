package com.traceo.sdk.builder;

/**
 * Base client configuration class.
 */
public class ClientCoreConfiguration {

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

    public ClientCoreConfiguration() {}

    public ClientCoreConfiguration(ClientCoreConfiguration configuration) {
        this.apiKey = configuration.getApiKey();
        this.host = configuration.getHost();
        this.isEnabled = configuration.isEnabled();
        this.isDebug = configuration.isDebug();
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
