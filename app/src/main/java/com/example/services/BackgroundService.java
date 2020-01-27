package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BackgroundService extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_service);

        Button startBackService = (Button)findViewById(R.id.buttonStartBackGround);
        startBackService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startServiceIntent = new Intent(BackgroundService.this, BackgroundSerClass.class);
                startService(startServiceIntent);
            }
        });

        Button stopBackService = (Button)findViewById(R.id.buttonStopBackGround);
        stopBackService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopServiceIntent = new Intent(BackgroundService.this, BackgroundSerClass.class);
                stopService(stopServiceIntent);
            }
        });

        Button buttonForBoundService= findViewById(R.id.buttonForBoundService);
        buttonForBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BackgroundService.this,BoundService.class);
                startActivity(intent);
            }
        });
    }
}
