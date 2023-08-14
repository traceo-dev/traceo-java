package com.traceo.sdk.handlers;

import com.traceo.sdk.*;
import com.traceo.sdk.logging.internal.SDKLogger;
import com.traceo.sdk.utils.ThrowableUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.traceo.sdk.client.CoreClient.getConfigs;

public class IncidentHandler {
    private final static SDKLogger LOGGER = new SDKLogger(IncidentHandler.class);
    private final static String TRACEO_PACKAGE = "com.traceo.sdk";

    private final ClientOptions options;

    public IncidentHandler() {
        this(new ClientOptions());
    }

    public IncidentHandler(ClientOptions options) {
        this.options = options;
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

        TraceoIncident payload = processIncident(throwable, message, options);
        if (callback != null) {
            callback.run(payload);
        }

        try {
            DefaultRequest<TraceoIncident> request = new DefaultRequest<>("/api/capture/incident", payload);
            options.getHttpClient().execute(request);
        } catch (Throwable e) {
            LOGGER.error("Failed to send exception.", e);
        }
    }

    private static TraceoIncident processIncident(Throwable throwable, String customMessage, ClientOptions options) {
        TraceoIncident traceoIncident = new TraceoIncident();

        String stacktrace = ThrowableUtils.stacktraceToString(throwable);
        TraceoRuntimePlatform platform = new TraceoRuntimePlatform();

        traceoIncident.setName(throwable.getClass().getName());

        if (customMessage != null) {
            traceoIncident.setMessage(customMessage);
        } else {
            traceoIncident.setMessage(throwable.getMessage());
        }

        List<TraceoTrace> traces = getTraceList(throwable, options);
        traceoIncident.setTraces(traces);

        traceoIncident.setStack(stacktrace);
        traceoIncident.setPlatform(platform);

        return traceoIncident;
    }

    private static List<TraceoTrace> getTraceList(Throwable throwable, ClientOptions options) {
        List<TraceoTrace> traces = new ArrayList<>();

        for (StackTraceElement element : throwable.getStackTrace()) {
            if (element == null) {
                continue;
            }

            TraceoTrace trace = new TraceoTrace();

            String className = element.getClassName();
            if (className.startsWith(TRACEO_PACKAGE)) {
                continue;
            }

            String[] splitFilename = element.getFileName().split("\\.");
            if (splitFilename.length > 1) {
                trace.setExtension(splitFilename[1]);
            }

            if (!element.isNativeMethod()) {
                trace.setLineNo(element.getLineNumber());
            }

            trace.setFilename(element.getFileName());
            trace.setFunction(element.getMethodName());
            trace.setAbsPath(element.getClassName());

            boolean isInternalTrace = options.getPackages().stream().anyMatch(className::startsWith);
            trace.setInternal(isInternalTrace);

            traces.add(trace);
        }
        return traces;
    }

    private static String getAbsolutePath(String className) {
        try {
            Class<?> myClass = Class.forName(className);
            ClassLoader classLoader = myClass.getClassLoader();

            URL classResource = classLoader.getResource(className.replace('.', '/') + ".class");
            if (classResource == null) {
                return null;
            }

            return classResource.getPath();
        } catch (java.lang.ClassNotFoundException e) {
            return null;
        }
    }
}
