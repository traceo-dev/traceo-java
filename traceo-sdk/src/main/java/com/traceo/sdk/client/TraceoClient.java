package com.traceo.sdk.client;

import com.traceo.sdk.ClientOptions;
import com.traceo.sdk.EventCallback;
import com.traceo.sdk.TraceoIncident;
import com.traceo.sdk.logging.client.TraceoLogger;

/**
 * Entry point for accessing Traceo SDK.
 */
public final class TraceoClient extends CoreClient<ClientOptions> {

    private TraceoClient() {}

    /**
     *
     * @return
     */
    public static TraceoLogger getLogger() {
        return TraceoLogger.createLogger(getConfigs());
    }

    /**
     *
     * @param clazz
     * @return
     */
    public static TraceoLogger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    /**
     *
     * @param name
     * @return
     */
    public static TraceoLogger getLogger(String name) {
        return TraceoLogger.createLogger(getConfigs(), name);
    }

    /**
     * Catch the exception.
     * @param throwable Exception.
     */
    public static void catchException(Throwable throwable) {
        incidentHandler.catchException(throwable, null, null);
    }

    /**
     * Catch the exception.
     * @param message Custom message instead of eq. java.lang.NullPointerException.
     * @param throwable Exception.
     */
    public static void catchException(String message, Throwable throwable) {
        incidentHandler.catchException(throwable, message, null);
    }

    /**
     * Catch the exception.
     * @param throwable Exception.
     * @param callback The callback to provide operations on already prepared TraceoIncident.
     */
    public static void catchException(Throwable throwable, EventCallback<TraceoIncident> callback) {
        incidentHandler.catchException(throwable, null, callback);
    }

    /**
     * Catch the exception.
     * @param throwable Exception.
     * @param message Custom message instead of eq. java.lang.NullPointerException.
     * @param callback The callback to provide operations on already prepared TraceoIncident.
     */
    public static void catchException(Throwable throwable, String message, EventCallback<TraceoIncident> callback) {
        incidentHandler.catchException(throwable, message, callback);
    }
}
