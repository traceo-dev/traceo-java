# Traceo SDK for Node.js

Library for integration with the [Traceo Platform](https://github.com/traceo-dev/traceo).

The Traceo platform offers the ability to collect and visualize data from [OpenTelemetry](https://opentelemetry.io/). OpenTelemetry, also known as OTel for short, is a vendor-neutral open-source Observability framework for instrumenting, generating, collecting, and exporting telemetry data such as traces, metrics, logs. [Docs](https://opentelemetry.io/docs/).

### How Traceo use OTel instruments?
By using custom metrics and spans exporters (logs in near future). After receiving data from Otel instruments, the data is successively sent to the Traceo platform, where it is aggregated and visualized.

### Installation
To install this SDK add this package to your `pom.xml` like below:
```java
<dependency>
    <groupId>org.traceo</groupId>
    <artifactId>traceo-sdk-opentelemetry</artifactId>
    <version>1.0.0</version>
</dependency>
```

or to `build.gradle`

```java
dependencies {
    implementation 'org.traceo.sdk:traceo-sdk-opentelemetry:1.0.0'
}
```

**TIP:** Remember to init [`TraceoClient`](https://github.com/traceo-dev/traceo-java/blob/master/traceo-sdk/README.md) before using this package.

### Metrics
To use the exporter for metrics you need to use `TraceoMetricsExporter` like below:
```java
import org.traceo.sdk.TraceoMetricsExporter;
import io.opentelemetry.api.metrics.MeterProvider;
import io.opentelemetry.sdk.metrics.export.MetricReader;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReaderBuilder;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.resources.Resource;

PeriodicMetricReaderBuilder readerBuilder = PeriodicMetricReader
        .builder(new TraceoMetricsExporter()) //use traceo exporter here
        .setInterval(Duration.ofMillis(500)); //optional

MetricReader reader = readerBuilder.build();

MeterProvider meterProvider = SdkMeterProvider.builder()
         .setResource(Resource.getDefault())
         .registerMetricReader(reader)
         .build();
         
// use meterProvider to collect metrics
```

After using `TraceoMetricsExporter` Traceo Platform will receive every metrics payload from OpenTelemetry.

### Spans
To use the exporter for spans you need to use `TraceoSpansExporter` like below:
```java
import org.traceo.sdk.TraceoSpansExporter;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;

SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
       .addSpanProcessor(
               BatchSpanProcessor
                    .builder(new TraceoSpansExporter()) // use traceo exporter here
                    .build()
               )
       .build();
        
OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder()
       .setTracerProvider(tracerProvider)
       .build();

Tracer tracer = openTelemetry.getTracerProvider().get("your-instrumentation-name");

// use tracer to instrument spans
```

After using `TraceoSpansExporter` Traceo Platform will receive every span payload from OpenTelemetry.

## Support
Feel free to create Issues, Pull Request and Discussion. If you want to contact with the developer working on this package click [here](mailto:piotr.szewczyk.software@gmail.com).
