package com.traceo.sdk;

import io.opentelemetry.sdk.trace.data.SpanData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraceoSpan {

    private String name;

    private String kind;

    private String status;

    private String statusMessage;

    private String traceId;

    private String spanId;

    private String parentSpanId;

    private String serviceName;

    private long startEpochNanos;

    private long endEpochNanos;

    private Map<String, String> attributes = new HashMap<>();

    private List<TraceoSpanEvent> events = new ArrayList<>();

    public TraceoSpan() {}

    public TraceoSpan(SpanData otelSpan) {
        this.spanId = otelSpan.getSpanId();
        this.traceId = otelSpan.getTraceId();
        this.parentSpanId = otelSpan.getParentSpanId();
        this.name = otelSpan.getName();
        this.kind = otelSpan.getKind().name();
        this.startEpochNanos = otelSpan.getStartEpochNanos();
        this.endEpochNanos = otelSpan.getEndEpochNanos();
        this.status = otelSpan.getStatus().getStatusCode().name();
        this.statusMessage = otelSpan.getStatus().getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String king) {
        this.kind = king;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getStartEpochNanos() {
        return startEpochNanos;
    }

    public void setStartEpochNanos(long startEpochNanos) {
        this.startEpochNanos = startEpochNanos;
    }

    public long getEndEpochNanos() {
        return endEpochNanos;
    }

    public void setEndEpochNanos(long endEpochNanos) {
        this.endEpochNanos = endEpochNanos;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<TraceoSpanEvent> getEvents() {
        return events;
    }

    public void setEvents(List<TraceoSpanEvent> events) {
        this.events = events;
    }
}
