package com.traceo.sdk.logging.client;

import com.traceo.sdk.ClientOptions;
import com.traceo.sdk.Ilogger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Traceo logger implementation based on Slf4j which display logs and send them to Traceo Platform.
 */
public class TraceoLogger implements Ilogger {

    private static final String BASE_LOGGER_NAME = "TraceoLogger";

    private static ClientOptions clientOptions = new ClientOptions();

    private static final int LOG_CHECK_INTERVAL_SECONDS = 180;

    private final LogsHandler logsHandler;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private TraceoLogger(String name) {
        this.logsHandler = new LogsHandler(name, clientOptions);

        // Schedule a task to check and send logs in intervals
        scheduler.scheduleAtFixedRate(this::sendLogs, 0, LOG_CHECK_INTERVAL_SECONDS, TimeUnit.SECONDS);

        // Register a shutdown hook to handle application shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public static TraceoLogger createLogger(ClientOptions options) {
        return createLogger(options, BASE_LOGGER_NAME);
    }

    public static TraceoLogger createLogger(ClientOptions options, String name) {
        clientOptions = options;
        return new TraceoLogger(name);
    }

    public void sendLogs() {
        this.logsHandler.send();
    }

    @Override
    public void log(String message, Object... args) {
        logsHandler.process(TraceoLogLevel.INFO, message, args);
    }

    @Override
    public void debug(String message, Object... args) {
        logsHandler.process(TraceoLogLevel.DEBUG, message, args);
    }

    @Override
    public void error(String message, Object... args) {
        logsHandler.process(TraceoLogLevel.ERROR, message, args);
    }

    @Override
    public void warning(String message, Object... args) {
        logsHandler.process(TraceoLogLevel.WARN, message, args);
    }

    public void shutdown() {
        logsHandler.send();
        scheduler.shutdown();
    }
}
