package com.traceo.sdk.client;

import com.traceo.sdk.builder.CoreBuilder;

public class TraceoClientBuilder extends CoreBuilder<TraceoClientBuilder, TraceoClientConfiguration> {
    protected TraceoClientBuilder() {
        super(new TraceoClientConfiguration());
    }

    public static TraceoClientBuilder standard() {
        return new TraceoClientBuilder();
    }

    public static TraceoClientConfiguration defaultClient() {
        return standard().build();
    }

    public final TraceoClientBuilder withMetricsCollection(boolean collect) {
        configuration.setCollectMetrics(collect);
        return this;
    }

    public final TraceoClientBuilder withOfflineMode(boolean isOffline) {
        configuration.setOffline(isOffline);
        return this;
    }

    public final TraceoClientBuilder withExportIntervalMs(int ms) {
        configuration.setExportIntervalMillis(ms);
        return this;
    }
}
