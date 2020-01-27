package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BoundService extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = BoundService.class.getSimpleName();
    Button buttonStartService, buttonStopService, buttonBindService, buttonUnBindService, buttongetRandomNumber, buttonForBroadcastReciever;
    private TextView textViewThreadCount;
    int count = 0;

    private MyAsyncTask myAsyncTask;
    private BoundServiceClass boundServiceClass;
    boolean isServiceBound;
    private ServiceConnection serviceConnection;

    private Intent serviceIntent;
    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        Log.i(getString(R.string.service_demo_tag), "MainActivity Thread Id:" + Thread.currentThread().getId());

        buttonStartService = findViewById(R.id.buttonStartService);
        buttonStopService = findViewById(R.id.buttonStopService);
        buttonBindService = findViewById(R.id.buttonBindService);
        buttonUnBindService = findViewById(R.id.buttonUnBindService);
        buttongetRandomNumber = findViewById(R.id.buttongetRandomNumber);
        textViewThreadCount= findViewById(R.id.textViewthreadCount);
        buttonForBroadcastReciever=findViewById(R.id.buttonForBroadcastReciever);

        buttonForBroadcastReciever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BoundService.this,BroadcastReciever.class);
                startActivity(intent);
            }
        });

        buttonStartService.setOnClickListener(this);
        buttonStopService.setOnClickListener(this);
        buttonBindService.setOnClickListener(this);
        buttonUnBindService.setOnClickListener(this);
        buttongetRandomNumber.setOnClickListener(this);

        serviceIntent = new Intent(getApplicationContext(), BoundServiceClass.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStartService:
                mStopLoop = true;
                startService(serviceIntent);
                break;
            case R.id.buttonStopService:
                stopService(serviceIntent);
                break;
            case R.id.buttonBindService:
                bindService();
                break;
            case R.id.buttonUnBindService:
                unbindService();
                break;
            case R.id.buttongetRandomNumber:
                setRandomNumber();
                break;
        }

    }

    private void bindService() {
        if (serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    BoundServiceClass.MyIntentServiceBinder myIntentServiceBinder = (BoundServiceClass.MyIntentServiceBinder) service;
                    boundServiceClass = myIntentServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceBound = false;
                }
            };
        }
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }

    private void setRandomNumber() {
        if (isServiceBound) {
            textViewThreadCount.setText("Random Number:" + boundServiceClass.getRandomNumber());
        } else {
            textViewThreadCount.setText("Service Not Bound");
        }

    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        private int customCounter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            customCounter = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            customCounter = integers[0];
            while (mStopLoop) {
                try {
                    Thread.sleep(1000);
                    customCounter++;
                    publishProgress(customCounter);
                } catch (InterruptedException e) {
                    Log.i(TAG, e.getMessage());
                }
            }
            return customCounter;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textViewThreadCount.setText(""+integer);
            count=integer;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textViewThreadCount.setText(""+values[0]);
        }
    }
}
