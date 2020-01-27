package com.example.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class BoundServiceClass extends IntentService {
    private int mRandomNumber;
    private boolean mIsRandonGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    public BoundServiceClass() {
        super(BoundServiceClass.class.getSimpleName());
    }

    class MyIntentServiceBinder extends Binder {
        public BoundServiceClass getService() {
            return BoundServiceClass.this;
        }
    }

    private IBinder mBinder = new MyIntentServiceBinder();


    @Override
    public void onCreate() {

        super.onCreate();
        Log.i(getString(R.string.service_demo_tag), "Service Created");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        Log.i(getString(R.string.service_demo_tag), "Service Started");
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        super.onStart(intent, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(getString(R.string.service_demo_tag), "In onBind");
        Toast.makeText(this, "Service Binded", Toast.LENGTH_LONG).show();
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(getString(R.string.service_demo_tag), "In onRebind");
        Toast.makeText(this, "Service Rebind", Toast.LENGTH_LONG).show();
        super.onRebind(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mIsRandonGeneratorOn = true;
        startRandomNumberGenerator();

    }

    private void startRandomNumberGenerator() {
        while (mIsRandonGeneratorOn) {
            try {
                Thread.sleep(1000);
                if (mIsRandonGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i(getString(R.string.service_demo_tag), "Thread Id:" + Thread.currentThread().getId() + ", Random Number : " + mRandomNumber);
                }
            } catch (InterruptedException e) {
                Log.i(getString(R.string.service_demo_tag), "Thread Interrupted");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsRandonGeneratorOn=false;
        Log.i(getString(R.string.service_demo_tag), "Service Destroyed");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(getString(R.string.service_demo_tag), "In onUnbind");
        Toast.makeText(this, "Service Unbind", Toast.LENGTH_LONG).show();

        return super.onUnbind(intent);

    }
    public  int getRandomNumber(){
        return mRandomNumber;
    }
}
