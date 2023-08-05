package com.traceo.sdk.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utils to manipulate Throwable exception class.
 */
public class ThrowableUtils {
    public static String stacktraceToString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);

        return sw.toString();
    }
}
