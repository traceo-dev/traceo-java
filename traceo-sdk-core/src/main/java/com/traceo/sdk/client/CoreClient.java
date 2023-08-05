package com.traceo.sdk.client;

import com.traceo.sdk.builder.ClientCoreConfiguration;
import com.traceo.sdk.http.HttpAsyncClient;
import com.traceo.sdk.logging.ClientLogger;
import org.apache.commons.logging.Log;

import java.io.IOException;

public class CoreClient<T extends ClientCoreConfiguration> {
    private final static ClientLogger LOGGER = new ClientLogger(CoreClient.class);
    private static volatile ClientCoreConfiguration configuration;
    private static HttpAsyncClient asyncTransport;

    protected CoreClient() {}

    public static <T extends ClientCoreConfiguration> void init(T clientConfiguration) {
        configuration = clientConfiguration;

        if (clientConfiguration.isEnabled()) {
            asyncTransport = new HttpAsyncClient();
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

