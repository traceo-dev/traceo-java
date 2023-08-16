package org.traceo.sdk.client;

import org.traceo.sdk.builder.CoreBuilder;

/**
 * Builder to create configuration for {@link org.traceo.sdk.client.TraceoClient}.
 */
public class TraceoClientBuilder extends CoreBuilder<TraceoClientBuilder, TraceoClientConfiguration> {
    protected TraceoClientBuilder() {
        super(new TraceoClientConfiguration());
    }

    /**
     * Create new instance of this builder with default configuration for each option.
     * @return Builder instance
     */
    public static TraceoClientBuilder standard() {
        return new TraceoClientBuilder();
    }

    public static TraceoClientConfiguration defaultClient() {
        return standard().build();
    }

    /**
     * Set to false if you SDK shouldn't collect any default metrics. It does not affect for OpenTelemetry.
     * @param collect
     * @return
     */
//    public final TraceoClientBuilder withMetricsCollection(boolean collect) {
//        configuration.setCollectMetrics(collect);
//        return this;
//    }


    /**
     * Set custom value for export default metrics interval.
     *
     * @param ms value provided in milliseconds
     * @return TraceoClientBuilder instance
     */
    public final TraceoClientBuilder withExportIntervalMs(int ms) {
        configuration.setExportIntervalMillis(ms);
        return this;
    }
}
