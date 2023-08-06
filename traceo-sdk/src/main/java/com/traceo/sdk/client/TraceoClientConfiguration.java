package com.traceo.sdk.client;

import com.traceo.sdk.builder.ClientCoreConfiguration;

public class TraceoClientConfiguration extends ClientCoreConfiguration {

    /**
     * Determine whether to collect basic metrics.
     */
    private boolean collectMetrics = false;

    /**
     * Interval specifying the time at which basic metrics are to be collected.
     * Value provided in milliseconds.
     */
    private int exportIntervalMillis = 5000;

    public TraceoClientConfiguration() {}

    public boolean isCollectMetrics() {
        return collectMetrics;
    }

    public void setCollectMetrics(boolean collectMetrics) {
        this.collectMetrics = collectMetrics;
    }


    public int getExportIntervalMillis() {
        return exportIntervalMillis;
    }

    public void setExportIntervalMillis(int exportIntervalMillis) {
        this.exportIntervalMillis = exportIntervalMillis;
    }
}
