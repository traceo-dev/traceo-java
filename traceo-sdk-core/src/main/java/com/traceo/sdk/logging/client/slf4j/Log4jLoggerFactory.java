package com.traceo.sdk.logging.client.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jLoggerFactory implements ILog4jLoggerFactory {
    private Logger logger;

    public Log4jLoggerFactory() {}

    public Logger create(String name) {
        logger = LoggerFactory.getLogger(name);
        return logger;
    }

    public Logger getLogger() {
        return logger;
    }
}
