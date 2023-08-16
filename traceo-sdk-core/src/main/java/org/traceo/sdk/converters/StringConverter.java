package org.traceo.sdk.converters;

public class StringConverter {
    public static String convertToHeaderKey(String key) {
        if (key == null) {
            return null;
        }

        String lowercaseString = key.toLowerCase();
        String result = lowercaseString.replaceAll("\\s+", "-");

        return result;
    }
}
