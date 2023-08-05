package com.traceo.sdk.client;

import com.traceo.sdk.logging.ClientLogger;

public class TraceoClient extends CoreClient<TraceoClientConfiguration> {
    private final static ClientLogger LOGGER = new ClientLogger(TraceoClient.class);

    protected TraceoClient() {}

    public static void catchException(Throwable e) {
        if (!getConfigs().isEnabled()) {
            LOGGER.log("Traceo client is disabled. Use withEnabled(true) to enabled exceptions scrapping.");
            return;
        }

//        TODO: handle exception here
    }
}
