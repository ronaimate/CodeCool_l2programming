package com.codecool.codecoolapplication.service;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ServiceNetworkThread implements Runnable {

    public static final String TAG = ServiceNetworkThread.class.getSimpleName();

    Context context;

    public ServiceNetworkThread(Context ctx) {
        context = ctx;
    }

    @Override
    public void run() {

        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.150.149:8080/GetVideoProject/DummyRequest";
        RequestBody requestBody = new FormBody.Builder()
                .build();

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();


            while (true)
            {
                boolean processIsRunning = false;
                ActivityManager manager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> runningTaskInfo = manager.getRunningAppProcesses();
                for(ActivityManager.RunningAppProcessInfo process : runningTaskInfo)
                {
                    if(process.processName.equals("com.codecool.codecoolapplication"))
                    {
                        processIsRunning = true;
                    }
                }
                if(processIsRunning)
                {
                    client.newCall(request).execute();
                }
                else {
                    context.stopService(new Intent(context, PingerService.class));
                    //Thread.currentThread().interrupt();
                }
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
