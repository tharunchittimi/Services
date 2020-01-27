package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    String msg = "Android : ";

    @Override
    public void onCreate() {
        Log.d(msg,"Service onCreate");
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(msg,"onStartedCommand started");
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(msg,"Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(msg,"Service Destroyed");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
