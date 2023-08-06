package com.traceo.sdk.http;

import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Interface to implement inside http clients.
 * @param <Type> Return type class.
 * @param <Callback> Type of the callbacks.
 */
public interface IHttpClient<Type, Callback> {
    Type execute(IRequest<?> request) throws IOException;
    Type execute(IRequest<?> request, Callback callback) throws IOException;
    Type execute(IRequest<?> request, HttpContext context) throws IOException;
    Type execute(IRequest<?> request, Callback callback, HttpContext context) throws IOException;
}
