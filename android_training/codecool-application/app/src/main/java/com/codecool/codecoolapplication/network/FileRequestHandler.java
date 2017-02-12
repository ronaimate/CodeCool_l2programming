package com.codecool.codecoolapplication.network;

import android.app.Activity;
import android.app.ProgressDialog;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ronai on 11/07/16.
 */
public class FileRequestHandler implements Runnable {

    String url;
    RequestBody requestBody;
    private ProgressDialog dialog;
    private Activity activity;

    public FileRequestHandler(String url, RequestBody requestBody, Activity activity) {
        this.url = url;
        this.requestBody = requestBody;
        this.activity = activity;
    }

    @Override
    public void run() {
        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            String result = response.body().string();
        } catch (Exception e) {
        }
    }
}
