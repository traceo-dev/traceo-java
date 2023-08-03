package com.traceo.sdk.builder;

public class ClientCoreConfigurationFactory {
    protected static <T extends  ClientCoreConfiguration> T getDefaultConfig() {
        return (T) new ClientCoreConfiguration();
    }
}
