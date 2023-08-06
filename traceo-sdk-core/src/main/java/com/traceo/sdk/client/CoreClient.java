package com.traceo.sdk.client;

import com.traceo.sdk.ClientOptions;
import com.traceo.sdk.IHandler;
import com.traceo.sdk.http.HttpAsyncClient;
import com.traceo.sdk.handlers.IncidentHandler;
import com.traceo.sdk.handlers.UncaughtExceptionHandler;
import com.traceo.sdk.logging.ClientLogger;

import java.io.Closeable;
import java.io.IOException;

/**
 * Base client class to use in client SDKs.
 * @param <T>
 */
public class CoreClient<T extends ClientOptions> implements Closeable {
    private final static ClientLogger LOGGER = new ClientLogger(CoreClient.class);

    /** The main configuration for client. **/
    private static volatile ClientOptions configuration;

    /** Async http client implementation to use inside client. **/
    public static HttpAsyncClient httpClient;


    /** Handler for captured incidents. **/
    public static IncidentHandler incidentHandler;

    protected CoreClient() {}

    /**
     * Method to initialized entire SDK.
     * @param options Class with configuration options for client.
     */
    public static synchronized <T extends ClientOptions> void init(T options) {
        configuration = options;

        if (options.isEnabled()) {

            if (options.getHost() == null) {
                throw new IllegalArgumentException("Host is required. Set enabled to false to disabled Traceo SDK.");
            }

            if (options.getApiKey() == null) {
                throw new IllegalArgumentException("API Key is required. Generated new API Key in Traceo Platform or disable entire Traceo SDK.");
            }

            if (options.isDebug()) {
                LOGGER.log("You run Traceo SDK with enabled debug option.");
            }

            httpClient = new HttpAsyncClient();
            options.setHttpClient(httpClient);

            incidentHandler = new IncidentHandler(options);

            if (options.isCatchUncaughtException()) {
                options.addHandler(new UncaughtExceptionHandler());
            }

            /**
             * Implementation for the future feature to external handlers
             */
            for (IHandler handler : options.getHandlers()) {
                handler.run(configuration);
            }

            LOGGER.log("Traceo SDK has been initialized.");
        } else {
            LOGGER.log("Traceo client is not enabled.");
        }
    }

    /**
     * Return configuration for this client.
     * @return Configuration for this client.
     */
    public static <T extends ClientOptions> T getConfigs() {
        return (T) configuration;
    }

    @Override
    public void close() throws IOException {
        httpClient.closeHttpClient();
    }
}

