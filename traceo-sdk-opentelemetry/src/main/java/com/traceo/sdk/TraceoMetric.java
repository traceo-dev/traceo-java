package com.traceo.sdk;

public class TraceoMetric {
    private String name;

    private String type;

    private long unixTimestamp;

    private long value;

    public TraceoMetric() {}

    public TraceoMetric(String name, String type, long unixTimestamp, long value) {
        this.name = name;
        this.type = type;
        this.unixTimestamp = unixTimestamp;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUnixTimestamp() {
        return unixTimestamp;
    }

    public void setUnixTimestamp(long unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
