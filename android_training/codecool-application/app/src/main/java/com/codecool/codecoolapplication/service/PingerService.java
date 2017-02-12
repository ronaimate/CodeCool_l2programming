package com.codecool.codecoolapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by ronai on 29/06/16.
 */
public class PingerService extends Service {

    Thread thread;

    public PingerService() {

    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        thread = new Thread(new ServiceNetworkThread(this));
        thread.start();

    }

    @Override
    public void onDestroy() {
        thread.interrupt();
        super.onDestroy();
    }
}
