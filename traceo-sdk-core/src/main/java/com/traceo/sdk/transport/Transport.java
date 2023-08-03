package com.traceo.sdk.transport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Transport {
    protected final ExecutorService executorService;

    public Transport(){
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void sendPackageAsync() {
        CompletableFuture.runAsync(() -> {
            sendPackage(null, null);
        }, executorService).exceptionally(throwable -> {
//            TODO: handle this exception
            return null;
        });
    }

    public void sendPackage(String payload, Object requestData) {
        try {
            URL url = new URL("");

            HttpURLConnection connection = openHttpConnection(url);

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(requestData.toString());
                wr.flush();
            }

            int responseCode = connection.getResponseCode();
//        TODO: handle response code

            connection.disconnect();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String buildUrl(String endpoint) {
//        return TraceoClientCoreBuilder.getOptions().getHost() + "/" + endpoint;
        return null;
    }

    private HttpURLConnection openHttpConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    /**
     * TODO: add more headers here
     * @param connection
     * @throws IOException
     */
    private void configureConnection(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        // Enable output and disable caching
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        connection.connect();
    }
}
