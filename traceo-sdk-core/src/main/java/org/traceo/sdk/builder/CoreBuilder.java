package org.traceo.sdk.builder;

import org.traceo.sdk.ClientOptions;

import java.util.List;

/**
 * Base class for every client builders.
 *
 * @param <Subclass> Client builder.
 * @param <ConfigurationType> Client configuration class extended by ClientCoreConfiguration.
 */
public abstract class CoreBuilder<Subclass extends CoreBuilder, ConfigurationType extends ClientOptions> {

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

    /**
     * Set api key generated for this project.
     *
     * @param apiKey Api key generated in Traceo Platform.
     * @return This builder for method calling.
     */
    public final Subclass withApiKey(String apiKey) {
        configuration.setApiKey(apiKey);
        return getSubclass();
    }

    /**
     * Set host on which Traceo Platform is currently running.
     *
     * @param host in format [protocol]://[domain]:[port]
     * @return This builder for method calling.
     */
    public final Subclass withHost(String host) {
        configuration.setHost(host);
        return getSubclass();
    }

    /**
     * If false then client is not initialized. None of the captured exception are send to Traceo Platform.
     *
     * @param enabled boolean
     * @return This builder for method calling.
     */
    public final Subclass withEnabled(boolean enabled) {
        configuration.setEnabled(enabled);
        return getSubclass();
    }

    /**
     * Set to true if you want to check internal logs for SDK.
     *
     * @param enabled boolean
     * @return This builder for method calling.
     */
    public final Subclass withDebug(boolean enabled) {
        configuration.setDebug(enabled);
        return getSubclass();
    }

    /**
     * Set to true if you want to catch every uncaught exception.
     * @param enabled boolean
     * @return This builder for method calling.
     */
    public final Subclass withCatchUncaughtException(boolean enabled) {
        configuration.setCatchUncaughtException(enabled);
        return getSubclass();
    }

    /**
     * List of packages where SDK is used. Based on this values, SDK
     * can check wheter incoming exception is inside client code or in external library.
     *
     * @param packages List of packages where SDK is used.
     * @return This builder for method calling.
     */
    public final Subclass withPackages(List<String> packages) {
        configuration.setPackages(packages);
        return getSubclass();
    }
}