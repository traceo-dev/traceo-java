package com.traceo.sdk.client;

import com.traceo.sdk.builder.ClientCoreConfiguration;

public class TraceoClientConfiguration extends ClientCoreConfiguration {

    private boolean collectMetrics = false;

    private boolean offline = false;

    private int exportIntervalMillis = 15000;

    public TraceoClientConfiguration() {}

    public boolean isCollectMetrics() {
        return collectMetrics;
    }

    public void setCollectMetrics(boolean collectMetrics) {
        this.collectMetrics = collectMetrics;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }

    public int getExportIntervalMillis() {
        return exportIntervalMillis;
    }

    public void setExportIntervalMillis(int exportIntervalMillis) {
        this.exportIntervalMillis = exportIntervalMillis;
    }
}