package com.traceo.sdk.exceptionHandler;

import com.traceo.sdk.client.TraceoClient;
import com.traceo.sdk.client.TraceoClientConfiguration;

import java.io.*;
public class ExceptionHandler {

    public ExceptionHandler() {}

    public static void captureException(Throwable throwable) throws ClassNotFoundException {
        TraceoClientConfiguration configuration = TraceoClient.getConfigs();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);

        String stacktrace = sw.toString();
        String message = throwable.getMessage();
        String type = throwable.getClass().getName();
    }
}
