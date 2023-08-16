package org.traceo.sdk.client;

import org.traceo.sdk.ClientOptions;
import org.traceo.sdk.IHandler;
import org.traceo.sdk.http.HttpAsyncAsyncClient;
import org.traceo.sdk.handlers.IncidentHandler;
import org.traceo.sdk.handlers.UncaughtExceptionHandler;
import org.traceo.sdk.logging.internal.SDKLogger;

import java.io.Closeable;
import java.io.IOException;

/**
 * Base client class to use in client SDKs.
 */
public class CoreClient implements Closeable {
    private final static SDKLogger LOGGER = new SDKLogger(CoreClient.class);

    /** The main configuration for client. **/
    private static volatile ClientOptions configuration;

    /** Async http client implementation to use inside client. **/
    public static HttpAsyncAsyncClient httpClient;


    /** Handler for captured incidents. **/
    public static IncidentHandler incidentHandler;

    protected CoreClient() {}

    /**
     * Method to initialized entire SDK.
     * @param options Class with configuration options for client.
     * @param <T> type to extends by {@link ClientOptions}
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

            httpClient = new HttpAsyncAsyncClient();
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
     * @param <T> type to extends by {@link ClientOptions}
     */
    public static <T extends ClientOptions> T getConfigs() {
        return (T) configuration;
    }

    @Override
    public void close() throws IOException {
        httpClient.closeHttpClient();
    }
}

