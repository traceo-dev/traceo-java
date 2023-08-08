package com.traceo.sdk.builder;

import com.traceo.sdk.ClientOptions;

/**
 * Builder for tests use case
 */
public class NoOpClientBuilder extends CoreBuilder<NoOpClientBuilder, ClientOptions> {
    protected NoOpClientBuilder(ClientOptions baseConfiguration) {
        super(baseConfiguration);
    }

    public static NoOpClientBuilder standard() {
        return new NoOpClientBuilder(new ClientOptions());
    }

    public static ClientOptions defaultClient() {
        return standard().build();
    }
}
