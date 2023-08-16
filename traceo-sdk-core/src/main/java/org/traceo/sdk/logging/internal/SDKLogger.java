package org.traceo.sdk.logging.internal;

import org.traceo.sdk.client.CoreClient;
import org.traceo.sdk.utils.ThrowableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Internal client logger based on Slf4j.
 */
public class SDKLogger {
    private final Logger logger;

    public SDKLogger(Class<?> clazz) {
        String className = clazz.getName();
        Logger initLogger = LoggerFactory.getLogger(className);

        this.logger = initLogger;
    }

    public void log(String message, Object... args) {
        printMessage(LogLevel.LOG, message, args);
    }

    public void debug(String message, Object... args) {
        printMessage(LogLevel.DEBUG, message, args);
    }

    public void error(String message, Object... args) {
        printMessage(LogLevel.ERROR, message, args);
    }

    /**
     * Method to log exception with provided custom message.
     *
     * @param message Custom message for exception.
     * @param throwable Catched exception.
     * @param <T> Exception type extended by {@link Throwable}
     * @return throwable
     */
    public <T extends Throwable> T logThrowableWithMessage(String message, T throwable) {
        Objects.requireNonNull(throwable, "Throwable cannot be null.");
        printMessage(LogLevel.ERROR, String.format("%s %s", message, ThrowableUtils.stacktraceToString(throwable)));

        return throwable;
    }

    /**
     * Log parsed exception.
     * @param throwable Exception
     * @param <T> Exception type extended by {@link Throwable}
     * @return throwable
     */
    public <T extends Throwable> T logThrowable(T throwable) {
        Objects.requireNonNull(throwable, "Throwable cannot be null.");
        printMessage(LogLevel.ERROR, ThrowableUtils.stacktraceToString(throwable));

        return throwable;
    }

    private void printMessage(LogLevel logLevel, String message, Object... args) {
        if (!CoreClient.getConfigs().isDebug()) {
            return;
        }

        switch (logLevel){
            case DEBUG:
                logger.debug(message, args);
                break;
            case ERROR:
                logger.error(message, args);
                break;
            case LOG:
                logger.info(message, args);
                break;
            default:
                break;
        }
    }
}
