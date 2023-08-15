package org.traceo.sdk.logging.client;

import org.traceo.sdk.ClientOptions;
import org.traceo.sdk.DefaultRequest;
import org.traceo.sdk.TraceoLog;
import org.traceo.sdk.logging.client.slf4j.Log4jLoggerFactory;
import org.traceo.sdk.logging.internal.SDKLogger;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 */
public class LogsHandler {
    private static final SDKLogger LOGGER = new SDKLogger(LogsHandler.class);

    private final Logger logger;
    private final List<TraceoLog> logsPool = new ArrayList<>();
    private final ClientOptions clientOptions;
    private static final int MAX_LOGS_IN_POOL = 150;

    public LogsHandler(String name, ClientOptions options) {
        this.clientOptions = options;

        Log4jLoggerFactory loggerFactory = new Log4jLoggerFactory();
        this.logger = loggerFactory.create(name);
    }

    public void process(TraceoLogLevel level, String message, Object... args) {
        if (clientOptions == null) {
            return;
        }

        String className = null;
        if (logger.getName() != null) {
            className = logger.getName();
        }

        StringBuilder logMsg = new StringBuilder(String.format("[%s] %s", className, message));

        for (Object arg : args) {
            logMsg.append(" ").append(arg.toString());
        }

        TraceoLog log = new TraceoLog(logMsg.toString(), level, className, argsToResources(args));
        logsPool.add(log);

        if (logsPool.size() >= MAX_LOGS_IN_POOL) {
            send();
        }

        switch (level) {
            case INFO:
            case LOG:
                logger.info(message, args);
                break;
            case DEBUG:
                logger.debug(message, args);
                break;
            case ERROR:
                logger.error(message, args);
                break;
            case WARN:
                logger.warn(message, args);
                break;
            default:
                break;
        }
    }

    public void send() {
        if (logsPool.isEmpty()) {
            return;
        }

        try {
            DefaultRequest<List<TraceoLog>> request = new DefaultRequest<>("/api/capture/log", logsPool);
            Future<HttpResponse> response = (Future<HttpResponse>) clientOptions.getHttpClient().execute(request);
            int statusCode = response.get().getStatusLine().getStatusCode();
            if (statusCode == 200) {
                logsPool.clear();
            }
        } catch (IOException | ExecutionException | InterruptedException e) {
            LOGGER.logThrowableWithMessage("Logs has not been send.", e);
        }
    }

    private Map<String, String> argsToResources(Object... args) {
        Map<String, String> resultMap = new HashMap<>();

        for (Object arg : args) {
            resultMap.put("resource", arg.toString());
        }

        return resultMap;
    }
}
