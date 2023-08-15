package org.traceo.sdk.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class HttpClientFactory implements IHttpClientFactory<CloseableHttpClient> {

    @Override
    public CloseableHttpClient create() {
        return create(new HttpClientConfiguration());
    }

    @Override
    public CloseableHttpClient create(HttpClientConfiguration configs) {
        final HttpClientBuilder builder = HttpClients.custom();

        builder
                .setMaxConnPerRoute(configs.getMaxConnPerRoute())
                .setMaxConnTotal(configs.getMaxConnection())
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(configs.getConnectionTimeout())
                        .setSocketTimeout(configs.getSocketTimeout())
                        .build())
                .setUserAgent(configs.getUserAgent());

        if (!configs.isCompression()) {
            builder.disableContentCompression();
        }

        return builder.build();
    }
}
