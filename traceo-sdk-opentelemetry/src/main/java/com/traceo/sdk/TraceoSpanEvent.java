package org.traceo.sdk;

import io.opentelemetry.sdk.trace.data.EventData;

import java.util.HashMap;
import java.util.Map;

public class TraceoSpanEvent {
    private String name;

    private Map<String, String> attributes = new HashMap<>();

    private long epochNanos;

    public TraceoSpanEvent() {}

    public TraceoSpanEvent(EventData eventData) {
        this.name = eventData.getName();
        this.epochNanos = eventData.getEpochNanos();
        this.attributes = OpenTelemetryMapper.mapAttributesToHashMap(eventData.getAttributes());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public long getEpochNanos() {
        return epochNanos;
    }

    public void setEpochNanos(long epochNanos) {
        this.epochNanos = epochNanos;
    }
}
