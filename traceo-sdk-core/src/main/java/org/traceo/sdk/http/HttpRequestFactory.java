package org.traceo.sdk.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.traceo.sdk.client.CoreClient;
import org.traceo.sdk.converters.StringConverter;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Factory class responsible for creating request based on {@link HttpRequestBase} object.
 */
public class HttpRequestFactory implements IHttpRequestFactory {

    private static final String CHARSET_ENCODING = "UTF-8";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String SDK_NAME_HEADER = "x-sdk-name";
    private static final String SDK_API_KEY_HEADER = "x-sdk-key";
    private static final String SDK_VERSION_HEADER = "x-sdk-version";

    public HttpRequestFactory() {}

    @Override
    public HttpRequestBase create(IRequest<?> request, HttpClientConfiguration settings) {
        URI uriEndpoint = createUriEndpoint(request);
        HttpRequestBase requestBase = new HttpGet(uriEndpoint);

        if (request.getHttpMethod().equals(HttpMethod.POST)) {
            requestBase = makePostRequest(request);
        }

        addRequestHeaders(requestBase, request);
        addRequestConfig(requestBase, settings);

        return requestBase;
    }

    private HttpRequestBase makePostRequest(IRequest<?> request) {
        URI uriEndpoint = createUriEndpoint(request);
        HttpPost httpPost = new HttpPost(uriEndpoint);
        String payload = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            payload = objectMapper.writeValueAsString(request.getContent());
        } catch (JsonProcessingException e) {
            payload = request.getContent().toString();
        }

        StringEntity stringEntity = new StringEntity(payload, CHARSET_ENCODING);
        stringEntity.setContentType(JSON_CONTENT_TYPE);

        httpPost.setEntity(stringEntity);

        return httpPost;
    }

    private URI createUriEndpoint(IRequest<?> request) {
        String host = CoreClient.getConfigs().getHost();

        try {
            if (request.getEndpoint() == null) {
                return new URI(host);
            }

            String endpoint = !request.getEndpoint().contains("/") ? String.format("/%s", request.getEndpoint()) : request.getEndpoint();
            String uri = String.format("%s%s", host, endpoint);

            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void addRequestHeaders(HttpRequestBase httpRequestBase, IRequest<?> request) {
        httpRequestBase.addHeader(SDK_NAME_HEADER, "java");
        httpRequestBase.addHeader(SDK_API_KEY_HEADER, CoreClient.getConfigs().getApiKey());
        httpRequestBase.addHeader(SDK_VERSION_HEADER, ".");

        if (request.getHttpMethod().equals(HttpMethod.POST)) {
            httpRequestBase.addHeader("Content-Type", JSON_CONTENT_TYPE);
        }

        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            httpRequestBase.addHeader(StringConverter.convertToHeaderKey(entry.getKey()), entry.getValue());
        }
    }

    private void addRequestConfig(HttpRequestBase base, HttpClientConfiguration settings) {
        final RequestConfig.Builder reqBuilder = RequestConfig
                .custom()
                .setMaxRedirects(settings.getMaxRedirects())
                .setConnectionRequestTimeout(settings.getConnectionRequestTimeout())
                .setConnectTimeout(settings.getConnectionTimeout());

        base.setConfig(reqBuilder.build());
    }
}
