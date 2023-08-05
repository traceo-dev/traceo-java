package com.traceo.sdk;

import java.util.ArrayList;
import java.util.List;

public class TraceoIncident {
    private String name;

    private String message;

    private String stack;

    private TraceoRuntimePlatform platform = new TraceoRuntimePlatform();

    private List<TraceoTrace> traces = new ArrayList<>();

    public TraceoIncident() {}

    public TraceoIncident(String name, String message, String stack, TraceoRuntimePlatform platform, List<TraceoTrace> traces) {
        this.name = name;
        this.message = message;
        this.stack = stack;
        this.platform = platform;
        this.traces = traces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public TraceoRuntimePlatform getPlatform() {
        return platform;
    }

    public void setPlatform(TraceoRuntimePlatform platform) {
        this.platform = platform;
    }

    public List<TraceoTrace> getTraces() {
        return traces;
    }

    public void setTraces(List<TraceoTrace> traces) {
        this.traces = traces;
    }
}
