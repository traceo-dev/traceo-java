package com.traceo.sdk.handlers;

import com.traceo.sdk.ClientOptions;
import com.traceo.sdk.IHandler;
import com.traceo.sdk.logging.internal.SDKLogger;

/**
 * Handler for uncaught exceptions.
 *
 * TODO:
 * This handler is working only on main thread.
 * Due to this fact, there is a need to create custom thread factory
 * where each thread should handle exceptions with this handler.
 */
public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler, IHandler {

    private static final SDKLogger LOGGER = new SDKLogger(UncaughtExceptionHandler.class);

    private static IncidentHandler incidentHandler;
    private ClientOptions options;

    @Override
    public void run(ClientOptions options) {
        this.options = options;

        incidentHandler = new IncidentHandler(options);
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.log("Catched uncaught exception.");

        if (options == null) {
            LOGGER.error("Uncaught exception has been received when client options is null.");
            return;
        }

        if (options.isCatchUncaughtException()) {
            incidentHandler.catchException(e, null, null);
        } else {
            LOGGER.error("Uncaught exception has been received when handler isCatchUncaughtException flag is not true.");
        }

        e.printStackTrace();
    }
}
