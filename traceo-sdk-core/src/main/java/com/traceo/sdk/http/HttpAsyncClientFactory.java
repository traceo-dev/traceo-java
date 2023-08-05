package com.traceo.sdk.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.nio.charset.StandardCharsets;

public class HttpAsyncClientFactory implements IHttpAsyncClientFactory {
    @Override
    public CloseableHttpAsyncClient create(HttpClientConfiguration configs) {

        RequestConfig requestConfig = RequestConfig.custom()
                .setMaxRedirects(configs.getMaxRedirects())
                .setConnectTimeout(configs.getConnectionTimeout())
                .setSocketTimeout(configs.getSocketTimeout())
                .setConnectionRequestTimeout(configs.getConnectionRequestTimeout())
                .setRedirectsEnabled(false)
                .build();

        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setCharset(StandardCharsets.UTF_8)
                .build();

        return HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setDefaultConnectionConfig(connectionConfig)
                .setMaxConnPerRoute(configs.getMaxConnPerRoute())
                .setMaxConnTotal(configs.getMaxConnection())
                .build();
    }
}