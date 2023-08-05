package com.traceo.sdk.incident;

import com.traceo.sdk.TraceoIncident;
import com.traceo.sdk.TraceoRuntimePlatform;
import com.traceo.sdk.utils.ThrowableUtils;

public class IncidentHandler {

    public IncidentHandler() {}

    public TraceoIncident processIncident(Throwable throwable, String customMessage) {
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
