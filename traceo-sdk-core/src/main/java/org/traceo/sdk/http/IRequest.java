package org.traceo.sdk.http;

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

    void setEndpoint(String endpoint);

    T getContent();

    void setContent(T content);
}
