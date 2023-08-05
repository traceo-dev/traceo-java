package com.traceo.sdk.client;

import com.traceo.sdk.builder.ClientCoreConfiguration;
import com.traceo.sdk.http.HttpAsyncClient;
import com.traceo.sdk.incident.IncidentHandler;
import com.traceo.sdk.logging.ClientLogger;
import org.apache.commons.logging.Log;

import java.io.IOException;

public class CoreClient<T extends ClientCoreConfiguration> {
    private final static ClientLogger LOGGER = new ClientLogger(CoreClient.class);
    private static volatile ClientCoreConfiguration configuration;
    public static HttpAsyncClient asyncTransport;
    public static IncidentHandler incidentHandler;

    protected CoreClient() {}

//    synchronized ensuring that only one thread should call this method
    public static synchronized <T extends ClientCoreConfiguration> void init(T clientConfiguration) {
        configuration = clientConfiguration;

        if (clientConfiguration.isEnabled()) {

            if (clientConfiguration.getHost() == null) {
                throw new IllegalArgumentException("Host is required. Set enabled to false to disabled Traceo SDK.");
            }

            if (clientConfiguration.getApiKey() == null) {
                throw new IllegalArgumentException("API Key is required. Generated new API Key in Traceo Platform or disable entire Traceo SDK.");
            }

            if (clientConfiguration.isDebug()) {
                LOGGER.log("You run Traceo SDK with enabled debug option. All internal messages for this SDK will be visible to you.");
            }

            LOGGER.log("Traceo SDK has been initialized.");

            asyncTransport = new HttpAsyncClient();
            incidentHandler = new IncidentHandler(asyncTransport);

            shutdownHook();
        } else {
            LOGGER.log("Traceo client is not enabled.");
        }
    }

    public static <T extends ClientCoreConfiguration> T getConfigs() {
        return (T) configuration;
    }

    private static void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                asyncTransport.closeHttpClient();
            } catch (IOException e) {
                LOGGER.logThrowableWithMessage("Exception while closing http client.", e);
                throw new RuntimeException(e);
            }
        }));
    }
}

