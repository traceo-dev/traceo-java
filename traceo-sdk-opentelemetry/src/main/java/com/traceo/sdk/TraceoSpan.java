package org.traceo.sdk;

import io.opentelemetry.sdk.trace.data.SpanData;

import java.math.BigDecimal;
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

    private BigDecimal startEpochNanos;

    private BigDecimal endEpochNanos;

    private Map<String, String> attributes = new HashMap<>();

    private List<TraceoSpanEvent> events = new ArrayList<>();

    public TraceoSpan() {}

    public TraceoSpan(SpanData otelSpan) {
        this.spanId = otelSpan.getSpanId();
        this.traceId = otelSpan.getTraceId();

        if (!otelSpan.getParentSpanId().equals("0000000000000000")) {
            this.parentSpanId = otelSpan.getParentSpanId();
        }

        this.name = otelSpan.getName();
        this.kind = otelSpan.getKind().name();
        this.startEpochNanos = parseLongToBigDecimal(otelSpan.getStartEpochNanos());
        this.endEpochNanos = parseLongToBigDecimal(otelSpan.getEndEpochNanos());
        this.status = otelSpan.getStatus().getStatusCode().name();
        this.statusMessage = otelSpan.getStatus().getDescription();
    }

    private BigDecimal parseLongToBigDecimal(long epochNanos) {
        BigDecimal originalValue = new BigDecimal(epochNanos);
        BigDecimal divisor = new BigDecimal("1e9");

        return originalValue.divide(divisor);
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

    public BigDecimal getStartEpochNanos() {
        return startEpochNanos;
    }

    public void setStartEpochNanos(BigDecimal startEpochNanos) {
        this.startEpochNanos = startEpochNanos;
    }

    public BigDecimal getEndEpochNanos() {
        return endEpochNanos;
    }

    public void setEndEpochNanos(BigDecimal endEpochNanos) {
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
