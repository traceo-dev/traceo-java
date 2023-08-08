package com.traceo.sdk;

import com.traceo.sdk.builder.NoOpClientBuilder;
import com.traceo.sdk.client.CoreClient;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.metrics.ObservableDoubleMeasurement;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.MetricReader;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReaderBuilder;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import jdk.internal.platform.Metrics;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

public class TraceoMetricsExporterTest extends TestCase {

    private SdkMeterProvider meterProvider;
    private Tracer tracer;

    private TraceoMetricsExporter exporter;


    @Before
    public  void setUp() {
        ClientOptions clientOptions = NoOpClientBuilder.standard()
                .withApiKey("xxx")
                .withHost("https://webhook.site/488164a0-5467-4621-9212-2974cc3eebc7")
                .build();

        CoreClient.init(clientOptions);

        exporter = new TraceoMetricsExporter();
        // Create a meter provider with the custom exporter

        PeriodicMetricReaderBuilder readerBuilder = PeriodicMetricReader.builder(exporter).setInterval(Duration.ofMillis(500));

        MetricReader reader = readerBuilder.build();

        meterProvider = SdkMeterProvider.builder()
                .setResource(Resource.getDefault())
                .registerMetricReader(reader)
                .build();

        OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(
                        SdkTracerProvider.builder()
                                .addSpanProcessor(BatchSpanProcessor.builder(new TraceoSpansExporter()).build())
                                .build())
                .build();

        tracer = openTelemetry.getTracerProvider().get("your-instrumentation-name");
    }

    @Test
    public void testOtelMetrics() {
        Meter meter = meterProvider.meterBuilder("test").build();

        LongCounter counter = meter.counterBuilder("xxx")
                .setDescription("description saaaas")
                .setUnit("xx")
                .build();

        ObservableDoubleMeasurement measurement = meter.gaugeBuilder("gauge-builder").buildObserver();

        measurement.record(123);
        counter.add(1);
        counter.add(1);
        counter.add(1);

        try {
            Thread.sleep(2000); // Adjust the duration based on your exporter's flushing interval
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOtelSpans() throws InterruptedException {
        Span parentSpan = tracer.spanBuilder("parent-span").startSpan();

        try {
            // Simulate some work in the parent span
            parentSpan
                    .addEvent("Parent span event 1")
                    .setAttribute("atrybut eventu", "hahahah");

            // Start a child span within the parent span
            Span childSpan1 = tracer
                    .spanBuilder("child-span-1")
                    .addLink(parentSpan.getSpanContext())
                    .setAttribute("jakiś atrybut", "co to za atrybut?")
                    .startSpan();

            try {
                // Simulate some work in the child span
                childSpan1.addEvent("Child span 1 event 1", Attributes.of(AttributeKey.stringKey("AttributeKey"), "stringKey"));
            } finally {
                // End the child span
                childSpan1.end();
            }

            // Start another child span within the parent span
            Span childSpan2 = tracer
                    .spanBuilder("child-span-2")
                    .addLink(parentSpan.getSpanContext())
                    .startSpan();

            try {
                // Simulate some work in the child span
                childSpan2.addEvent("Child span 2 event 1");
            } finally {
                // End the child span
                childSpan2.end();
            }
        } finally {
            // End the parent span
            parentSpan.end();

            Thread.sleep(5000); // Adjust the duration based on your exporter's flushing interval

        }
    }

    @After
    public void tearDown() {
        exporter.shutdown();
    }
}