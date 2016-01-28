package com.amanpreetsingh.moovi;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Aman on 1/28/2016.
 */
public class OkClient {

    private static OkClient instance;
    private OkHttpClient client;

    private OkClient(){
        client = new OkHttpClient();
    }

    public static OkClient getInstance(){
        if (instance == null){
            return new OkClient();
        }
        return instance;
    }

    public static Request getRequestFromURL (String url){

        return new Request.Builder()
                .url(url)
                .build();
    }

    public Response execute(String url) throws IOException {
        Request request = getRequestFromURL(url);
        return execute(request);
    }

    public Response execute(Request request) throws IOException {
        return this.client.newCall(request).execute();
    }

    public void executeAsync(String url, Callback callback) throws IOException {
        Request request = getRequestFromURL(url);
        this.client.newCall(request).enqueue(callback);
    }

    public void executeAsync(Request request, Callback callback) throws IOException {
        this.client.newCall(request).enqueue(callback);
    }

}