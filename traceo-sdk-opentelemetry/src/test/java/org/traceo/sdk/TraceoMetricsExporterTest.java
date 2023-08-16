package org.traceo.sdk;

import org.traceo.sdk.builder.NoOpClientBuilder;
import org.traceo.sdk.client.CoreClient;
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
                .withApiKey("tr_5d1c3709-119f-4fb9-b930-3e5b3a88cac7")
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
        counter.add(13);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOtelSpans() throws InterruptedException {
        Span parentSpan = tracer.spanBuilder("parent-span").startSpan();

        try {
            parentSpan
                    .addEvent("Parent span event 1")
                    .setAttribute("atrybut eventu", "hahahah");

            Span childSpan1 = tracer
                    .spanBuilder("child-span-1")
                    .addLink(parentSpan.getSpanContext())
                    .setAttribute("jakiś atrybut", "co to za atrybut?")
                    .startSpan();

            try {
                childSpan1.addEvent("Child span 1 event 1", Attributes.of(AttributeKey.stringKey("AttributeKey"), "stringKey"));
            } finally {
                childSpan1.end();
            }

            Span childSpan2 = tracer
                    .spanBuilder("child-span-2")
                    .addLink(parentSpan.getSpanContext())
                    .startSpan();

            try {
                childSpan2.addEvent("Child span 2 event 1");
            } finally {
                childSpan2.end();
            }
        } finally {
            parentSpan.end();
        }

        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        exporter.shutdown();
    }
}