package org.traceo.sdk.http;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.protocol.HttpContext;

import java.util.concurrent.Future;

/**
 * Interface to implement asynchronous operations to make http request.
 */
public interface IHttpAsyncClient {
    /**
     * Execute asynchronous requests.
     * @param request {@link IRequest} implementation
     * @return {@link Future}
     */
    Future<HttpResponse> execute(IRequest<?> request);

    /**
     * Execute asynchronous requests.
     * @param request {@link IRequest} implementation
     * @param callback Async callback to handle http response
     * @return {@link Future}
     */
    Future<HttpResponse> execute(IRequest<?> request, FutureCallback<HttpResponse> callback);

    /**
     * Execute asynchronous requests.
     * @param request {@link IRequest} implementation
     * @param context Custom http context
     * @return {@link Future}
     */
    Future<HttpResponse> execute(IRequest<?> request, HttpContext context);

    /**
     * Execute asynchronous requests.
     * @param request {@link IRequest} implementation
     * @param callback Async callback to handle http response
     * @param context Custom http context
     * @return {@link Future}
     */
    Future<HttpResponse> execute(IRequest<?> request, FutureCallback<HttpResponse> callback, HttpContext context);
}
