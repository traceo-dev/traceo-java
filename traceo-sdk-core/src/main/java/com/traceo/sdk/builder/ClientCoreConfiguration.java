package com.traceo.sdk.builder;

public class ClientCoreConfiguration {

    private String apiKey = null;

    private String host = null;

    public ClientCoreConfiguration() {}

    public ClientCoreConfiguration(ClientCoreConfiguration configuration) {
        this.apiKey = configuration.getApiKey();
        this.host = configuration.getHost();
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
}
