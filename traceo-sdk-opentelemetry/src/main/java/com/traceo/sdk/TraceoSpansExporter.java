package com.traceo.sdk;

import com.traceo.sdk.client.CoreClient;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.export.SpanExporter;

import java.util.*;

/**
 * Traceo custom exporter for OpenTelemetry spans and traces.
 * On export data every spans or traces are sent to Traceo platform to provided
 * in TraceoClientBuilder host.
 */
public class TraceoSpansExporter implements SpanExporter {
    private final ClientOptions options;

    private final String ENDPOINT = "/api/capture/traces";

    public TraceoSpansExporter() {
        this.options = CoreClient.getConfigs();
    }

    @Override
    public CompletableResultCode export(Collection<SpanData> collection) {
        if (!options.isEnabled()) {
            return CompletableResultCode.ofSuccess();
        }

        List<TraceoSpan> spans = OpenTelemetryMapper.mapOpenTelemetrySpanToTraceo(collection);
        return OpenTelemetryHttpClient.request(ENDPOINT, spans);
    }

    @Override
    public CompletableResultCode flush() {
        return CompletableResultCode.ofSuccess();
    }

    @Override
    public CompletableResultCode shutdown() {
        return CompletableResultCode.ofSuccess();
    }
}
