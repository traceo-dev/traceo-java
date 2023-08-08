package com.traceo.sdk;

import com.traceo.sdk.client.CoreClient;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.metrics.InstrumentType;
import io.opentelemetry.sdk.metrics.data.*;
import io.opentelemetry.sdk.metrics.export.MetricExporter;

import java.util.Collection;
import java.util.List;

/**
 * Traceo custom exporter for OpenTelemetry metrics.
 * On export data every metrics are sent to Traceo platform to provided
 * in TraceoClientBuilder host.
 */
public class TraceoMetricsExporter implements MetricExporter {
    private final ClientOptions options;

    private final String ENDPOINT = "/api/capture/metrics";

    public TraceoMetricsExporter() {
        this.options = CoreClient.getConfigs();
    }

    @Override
    public CompletableResultCode export(Collection<MetricData> collection) {
        if (!options.isEnabled()) {
            return CompletableResultCode.ofSuccess();
        }

        List<TraceoMetric> traceoMetrics = OpenTelemetryMapper.mapOpenTelemetryMetricToTraceo(collection);
        return OpenTelemetryHttpClient.request(ENDPOINT, traceoMetrics);
    }

    @Override
    public CompletableResultCode flush() {
        return CompletableResultCode.ofSuccess();
    }

    @Override
    public CompletableResultCode shutdown() {
        return CompletableResultCode.ofSuccess();
    }

    @Override
    public AggregationTemporality getAggregationTemporality(InstrumentType instrumentType) {
        return AggregationTemporality.DELTA;
    }
}
