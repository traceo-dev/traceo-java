package com.traceo.sdk.client;

import com.traceo.sdk.builder.ClientCoreConfiguration;

public class CoreClient<T extends ClientCoreConfiguration> {
    private static volatile ClientCoreConfiguration configuration;

    protected CoreClient() {}

    public static <T extends ClientCoreConfiguration> void init(T clientConfiguration) {
        configuration = clientConfiguration;
    }

    public static <T extends ClientCoreConfiguration> T getConfigs() {
        return (T) configuration;
    }
}

