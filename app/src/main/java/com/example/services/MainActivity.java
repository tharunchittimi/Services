package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String msg = "Android : ";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForegroundService.class);
                startActivity(intent);
            }
        });
    }
    public void startService(View view) {
        startService(new Intent(this, MyService.class));
        Log.d(msg, "startService method started");
    }

    public void stopService(View view) {
        stopService(new Intent(this, MyService.class));
        Log.d(msg, "stopService Method stopped");
    }



}
