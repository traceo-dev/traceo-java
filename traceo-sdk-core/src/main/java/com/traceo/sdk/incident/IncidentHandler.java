package com.traceo.sdk.incident;

import com.traceo.sdk.DefaultRequest;
import com.traceo.sdk.EventCallback;
import com.traceo.sdk.TraceoIncident;
import com.traceo.sdk.TraceoRuntimePlatform;
import com.traceo.sdk.http.HttpAsyncClient;
import com.traceo.sdk.logging.ClientLogger;
import com.traceo.sdk.utils.ThrowableUtils;

import static com.traceo.sdk.client.CoreClient.getConfigs;

public class IncidentHandler {
    private final static ClientLogger LOGGER = new ClientLogger(IncidentHandler.class);

    private static HttpAsyncClient httpAsyncClient;

    public IncidentHandler(HttpAsyncClient httpClient) {
        this.httpAsyncClient = httpClient;
    }

    public void catchException(Throwable throwable, String message, EventCallback<TraceoIncident> callback) {
        if (!getConfigs().isEnabled()) {
            LOGGER.log("Traceo client is disabled. Use withEnabled(true) to enabled exceptions scrapping.");
            return;
        }

        if (throwable == null) {
            LOGGER.log("Exception has been passed as null.");
            return;
        }

        TraceoIncident traceoIncident = processIncident(throwable, message);
        if (callback != null) {
            callback.run(traceoIncident);
        }

        DefaultRequest<TraceoIncident> request = new DefaultRequest<>();
        request.setContent(traceoIncident);
        request.setEndpoint("/incident");

        try {
            httpAsyncClient.executeAsync(request);
        } catch (Throwable e) {
            LOGGER.error("Failed to send exception.", e);
        }
    }

    private static TraceoIncident processIncident(Throwable throwable, String customMessage) {
        TraceoIncident traceoIncident = new TraceoIncident();

        String stacktrace = ThrowableUtils.stacktraceToString(throwable);
        TraceoRuntimePlatform platform = new TraceoRuntimePlatform();

        traceoIncident.setName(throwable.getClass().getName());

        if (customMessage != null) {
            traceoIncident.setMessage(customMessage);
        } else {
            traceoIncident.setMessage(throwable.getMessage());
        }

        traceoIncident.setStack(stacktrace);
        traceoIncident.setPlatform(platform);

        return traceoIncident;
    }
}
