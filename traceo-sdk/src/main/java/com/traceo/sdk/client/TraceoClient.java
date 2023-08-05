package com.traceo.sdk.client;

import com.traceo.sdk.DefaultRequest;
import com.traceo.sdk.EventCallback;
import com.traceo.sdk.TraceoIncident;
import com.traceo.sdk.logging.ClientLogger;

public class TraceoClient extends CoreClient<TraceoClientConfiguration> {
    private final static ClientLogger LOGGER = new ClientLogger(TraceoClient.class);

    protected TraceoClient() {}

    public static void catchException(Throwable e) {
        catchException(e, null);
    }

    public static void catchException(String message, Throwable e) {
        catchException(e, message, null);
    }

    public static void catchException(Throwable e, EventCallback<TraceoIncident> callback) {
        catchException(e, null, callback);
    }

    public static void catchException(Throwable e, String message, EventCallback<TraceoIncident> callback) {
        if (!getConfigs().isEnabled()) {
            LOGGER.log("Traceo client is disabled. Use withEnabled(true) to enabled exceptions scrapping.");
            return;
        }

        TraceoIncident traceoIncident = incidentHandler.processIncident(e, message);
        if (callback != null) {
            callback.run(traceoIncident);
        }

        DefaultRequest<TraceoIncident> request = new DefaultRequest<>();
        request.setContent(traceoIncident);
        request.setEndpoint("/incident");

        asyncTransport.executeAsync(request);
    }
}
