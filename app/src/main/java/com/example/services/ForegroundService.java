package com.example.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForegroundService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_service);
        Button btnSTartService, btnStopService, buttonForBackgroundService;
        btnSTartService=findViewById(R.id.buttonStartService);
        btnStopService=findViewById(R.id.buttonStopService);
        buttonForBackgroundService = findViewById(R.id.buttonForBackgroundService);
        btnSTartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });
        buttonForBackgroundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForegroundService.this, BackgroundService.class);
                startActivity(intent);
            }
        });
    }

    private void startService() {
        Intent serviceIntent = new Intent(this,ForegroundSerClass.class);
        serviceIntent.putExtra("inputExtra","Foreground Service Running");
        ContextCompat.startForegroundService(this,serviceIntent);

    }
    private void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundSerClass.class);
        stopService(serviceIntent);
    }


}
