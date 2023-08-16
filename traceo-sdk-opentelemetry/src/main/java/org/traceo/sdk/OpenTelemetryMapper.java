package org.traceo.sdk;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.sdk.metrics.data.*;
import io.opentelemetry.sdk.trace.data.SpanData;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper class for map OpenTelemetry objects to Traceo objects
 */
public class OpenTelemetryMapper {

    protected OpenTelemetryMapper() {}

    public static Map<String, String> mapAttributesToHashMap(Attributes attributes) {
        Map<String, String> attributeMap = new HashMap<>();

        attributes.forEach((key, value) -> {
            attributeMap.put(key.getKey(), value.toString());
        });

        return attributeMap;
    }

    public static List<TraceoMetric> mapOpenTelemetryMetricToTraceo(Collection<MetricData> metrics) {
        List<TraceoMetric> result = new ArrayList<>();

        for (MetricData metricData : metrics) {
            List<TraceoMetric> points = getPoints(metricData);
            points.forEach((e) -> e.setName(metricData.getName()));
            result.addAll(points);
        }

        return result;
    }

    private static List<TraceoMetric> getPoints(MetricData metricData) {
        MetricDataType dataType = metricData.getType();
        List<TraceoMetric> metrics = new ArrayList<>();

        switch (dataType) {
            case SUMMARY:
                SummaryData summaryData = metricData.getSummaryData();
                metrics = summaryData.getPoints().stream().map(summaryPointData -> {
                    TraceoMetric traceoMetric = new TraceoMetric();
                    traceoMetric.setValue(summaryPointData.getCount());
                    traceoMetric.setUnixTimestamp(summaryPointData.getEpochNanos());
                    traceoMetric.setType(TraceoMetricEnum.COUNTER.name());
                    return traceoMetric;
                }).collect(Collectors.toList());
                break;
//            Histogram data are not supported yet
//            case HISTOGRAM:
//                HistogramData histogramData = metricData.getHistogramData();
//                histogramData.getPoints();
//            case EXPONENTIAL_HISTOGRAM:
//                ExponentialHistogramData exponentialHistogramData = metricData.getExponentialHistogramData();
//                exponentialHistogramData.getPoints();
            case LONG_SUM:
                SumData<LongPointData> longPointDataSumData = metricData.getLongSumData();
                metrics = longPointDataSumData.getPoints().stream().map(longPointData -> {
                    TraceoMetric traceoMetric = new TraceoMetric();
                    traceoMetric.setValue(longPointData.getValue());
                    traceoMetric.setUnixTimestamp(longPointData.getEpochNanos());
                    traceoMetric.setName(TraceoMetricEnum.COUNTER.name());

                    return traceoMetric;
                }).collect(Collectors.toList());
                break;
            case LONG_GAUGE:
                GaugeData<LongPointData>  longPointDataGaugeData = metricData.getLongGaugeData();
                metrics = longPointDataGaugeData.getPoints().stream().map(longPointData -> {
                    TraceoMetric traceoMetric = new TraceoMetric();
                    traceoMetric.setValue(longPointData.getValue());
                    traceoMetric.setUnixTimestamp(longPointData.getEpochNanos());
                    traceoMetric.setName(TraceoMetricEnum.GAUGE.name());

                    return traceoMetric;
                }).collect(Collectors.toList());
                break;
            case DOUBLE_SUM:
                SumData<DoublePointData> doublePointDataSumData = metricData.getDoubleSumData();
                metrics = doublePointDataSumData.getPoints().stream().map(longPointData -> {
                    TraceoMetric traceoMetric = new TraceoMetric();
                    traceoMetric.setValue((long) longPointData.getValue());
                    traceoMetric.setUnixTimestamp(longPointData.getEpochNanos());
                    traceoMetric.setName(TraceoMetricEnum.COUNTER.name());

                    return traceoMetric;
                }).collect(Collectors.toList());
                break;
            case DOUBLE_GAUGE:
                GaugeData<DoublePointData> gaugeData = metricData.getDoubleGaugeData();
                metrics = gaugeData.getPoints().stream().map(longPointData -> {
                    TraceoMetric traceoMetric = new TraceoMetric();
                    traceoMetric.setValue((long) longPointData.getValue());
                    traceoMetric.setUnixTimestamp(longPointData.getEpochNanos());
                    traceoMetric.setName(TraceoMetricEnum.GAUGE.name());

                    return traceoMetric;
                }).collect(Collectors.toList());
                break;
            default:
                break;
        }

        return metrics;
    }

    public static List<TraceoSpan> mapOpenTelemetrySpanToTraceo(Collection<SpanData> spans) {
        return spans.stream().map(spanData -> {
            TraceoSpan traceoSpan = new TraceoSpan(spanData);

            traceoSpan.setAttributes(OpenTelemetryMapper.mapAttributesToHashMap(spanData.getAttributes()));

            String serviceName = spanData.getAttributes().get(AttributeKey.stringKey("service.name"));
            if (serviceName == null) {
                traceoSpan.setServiceName("unknown");
            } else {
                traceoSpan.setServiceName(serviceName);
            }

            List<TraceoSpanEvent> traceoSpanEvents = spanData.getEvents().stream().map(TraceoSpanEvent::new).collect(Collectors.toList());
            traceoSpan.setEvents(traceoSpanEvents);

            return traceoSpan;
        }).collect(Collectors.toList());
    }
}
