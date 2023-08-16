package org.traceo.sdk;

public interface Ilogger {

    void log(String message, Object... args);

    void debug(String message, Object... args);

    void error(String message, Object... args);

    void warning(String message, Object... args);
}
