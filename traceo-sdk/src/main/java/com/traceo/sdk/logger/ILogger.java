package com.traceo.sdk.logger;

public interface ILogger {

    static void logMessage(LogLevel level, String message) {}

    /**
     * Log a message with log level and message
     */
    static void log(String message) {
        logMessage(LogLevel.LOG, message);
    };

    /**
     * Log a message with info level and message
     */
    static void info(String message) {
        logMessage(LogLevel.INFO, message);
    }

    /**
     * Log a message with error level and message
     */
    static void error(String message) {
        logMessage(LogLevel.ERROR, message);
    }

    /**
     * Log a message with warn level and message
     */
    static void warn(String message) {
        logMessage(LogLevel.WARN, message);
    }

    /**
     * Log a message with debug level and message
     */
    static void debug(String message) {
        logMessage(LogLevel.DEBUG, message);
    }
}
