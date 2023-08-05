package com.traceo.sdk.builder;

public class ClientCoreConfiguration {

    private String apiKey = null;

    private String host = null;

    private boolean isEnabled = true;

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
