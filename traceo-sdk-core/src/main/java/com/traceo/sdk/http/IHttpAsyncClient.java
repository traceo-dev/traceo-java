package com.traceo.sdk.http;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Interface to implement asynchronous operations to make http request.
 */
public interface IHttpAsyncClient {
    /**
     * Execute asynchronous requests.
     * @param request
     * @return
     */
    Future<HttpResponse> execute(IRequest<?> request) throws IOException;

    /**
     * Execute asynchronous requests.
     * @param request
     * @return
     */
    Future<HttpResponse> execute(IRequest<?> request, FutureCallback<HttpResponse> callback) throws IOException;

    /**
     * Execute asynchronous requests.
     * @param request
     * @return
     */
    Future<HttpResponse> execute(IRequest<?> request, HttpContext context) throws IOException;

    /**
     * Execute asynchronous requests.
     * @param request
     * @return
     */
    Future<HttpResponse> execute(IRequest<?> request, FutureCallback<HttpResponse> callback, HttpContext context) throws IOException;
}
