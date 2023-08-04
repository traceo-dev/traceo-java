package com.traceo.sdk.http;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public interface IRequest<T> {

    default HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    };

    default Map<String, String> getHeaders() {
        return new HashMap<>();
    };

    String getEndpoint();

    T getContent();
}
