package com.traceo.sdk.converters;

public class StringConverter {
    /**
     *  Method to convert string to header-string:
     *  eq. Some header Key -> some-header-key
     * @param key
     * @return
     */
    public static String convertToHeaderKey(String key) {
        if (key == null) {
            return null;
        }

        String lowercaseString = key.toLowerCase();
        String result = lowercaseString.replaceAll("\\s+", "-");

        return result;
    }
}
