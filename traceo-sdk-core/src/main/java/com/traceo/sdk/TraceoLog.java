package com.traceo.sdk;

import com.traceo.sdk.logging.client.TraceoLogLevel;
import com.traceo.sdk.utils.TimestampUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Traceo representation of single log persisted in Traceo.
 */
public class TraceoLog {

    private String message;

    private TraceoLogLevel level;

    private String className;

    private long unix;

    private String timestamp;


    private Map<String, String> resources = new HashMap<>();

    public TraceoLog() {}

    public TraceoLog(String message, TraceoLogLevel level, String className) {
        this.message = message;
        this.level = level;
        this.className = className;
        this.unix = TimestampUtils.getCurrentUnixTimestamp();
        this.timestamp = TimestampUtils.getCurrentTimestamp();
    }

    public TraceoLog(String message, TraceoLogLevel level, String className, Map<String, String> resources) {
        this.message = message;
        this.level = level;
        this.className = className;
        this.resources = resources;
        this.unix = TimestampUtils.getCurrentUnixTimestamp();
        this.timestamp = TimestampUtils.getCurrentTimestamp();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public TraceoLogLevel getLevel() {
        return level;
    }

    public void setLevel(TraceoLogLevel level) {
        this.level = level;
    }

    public long getUnix() {
        return unix;
    }

    public void setUnix(long unix) {
        this.unix = unix;
    }

    public Map<String, String> getResources() {
        return resources;
    }

    public void setResources(Map<String, String> resources) {
        this.resources = resources;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraceoLog)) return false;
        TraceoLog traceoLog = (TraceoLog) o;
        return getUnix() == traceoLog.getUnix() && Objects.equals(getMessage(), traceoLog.getMessage()) && Objects.equals(getTimestamp(), traceoLog.getTimestamp()) && getLevel() == traceoLog.getLevel() && Objects.equals(getResources(), traceoLog.getResources());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), getTimestamp(), getLevel(), getUnix(), getResources());
    }
}
