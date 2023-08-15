package org.traceo.sdk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {

    public static long getCurrentUnixTimestamp() {
        return System.currentTimeMillis();
    }

    public static String getCurrentTimestamp() {
        long timestampInMillis = System.currentTimeMillis();
        Date date = new Date(timestampInMillis);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return dateFormat.format(date);
    }
}
