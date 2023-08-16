package org.traceo.sdk;

import org.traceo.sdk.client.CoreClient;
import org.traceo.sdk.logging.internal.SDKLogger;
import io.opentelemetry.sdk.common.CompletableResultCode;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Asynchronous Http client for open-telemetry exporters
 */
public class OpenTelemetryHttpClient {
    private final static SDKLogger LOGGER = new SDKLogger(OpenTelemetryHttpClient.class);

    public static <T> CompletableResultCode request(String endpoint, T content) {
        ClientOptions options = CoreClient.getConfigs();
        DefaultRequest<T> request = new DefaultRequest<>(endpoint, content);

        try {
            Future<HttpResponse> response = options.getHttpClient().execute(request);

//          Waiting for the response to handle it
            HttpResponse httpResponse = response.get();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode <= 300) {
                LOGGER.log("Metrics exporter successfully to Traceo.");
                return CompletableResultCode.ofSuccess();
            }

            return CompletableResultCode.ofFailure();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
