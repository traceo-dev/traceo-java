package com.traceo.sdk.builder;

public abstract class CoreBuilder<Subclass extends CoreBuilder, ConfigurationType extends ClientCoreConfiguration> {

    protected ConfigurationType configuration;

    protected CoreBuilder(ConfigurationType baseConfiguration) {
        this.configuration = baseConfiguration;
    }

    public final Subclass getSubclass() {
        return (Subclass) this;
    }

    public final ConfigurationType build() {
        return configuration;
    }

    public final Subclass withApiKey(String apiKey) {
        configuration.setApiKey(apiKey);
        return getSubclass();
    }

    public final Subclass withHost(String host) {
        configuration.setHost(host);
        return getSubclass();
    }

    public final Subclass withEnabled(boolean enabled) {
        configuration.setEnabled(enabled);
        return getSubclass();
    }

    public final Subclass withDebug(boolean enabled) {
        configuration.setDebug(enabled);
        return getSubclass();
    }
}