package com.traceo.sdk.http;

import com.traceo.sdk.exceptions.SDKException;
import org.apache.http.client.methods.HttpRequestBase;

import java.net.URISyntaxException;

public interface IHttpRequestFactory {
    HttpRequestBase create(IRequest<?> IRequest, HttpClientConfiguration settings);
}
