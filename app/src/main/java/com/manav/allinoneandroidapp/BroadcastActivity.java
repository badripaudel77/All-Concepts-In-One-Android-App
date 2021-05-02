package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.manav.allinoneandroidapp.services.MyBGMusicService;

public class BroadcastActivity extends AppCompatActivity {

    BroadcastReceiver airplaneModeBroadCastReceiver, charginModeBroadCastReceiver;

    Button startButton, stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        airplaneModeBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Airplane mode has changed", Toast.LENGTH_SHORT).show();
            }
        };

        charginModeBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "someone is calling you.", Toast.LENGTH_SHORT).show();
            }
        };

        //start service button
        startButton.setOnClickListener(v ->{
            startService(new Intent(this, MyBGMusicService.class));
        });
        //stop service button
        stopButton.setOnClickListener( v -> {
            // stopping the service
            stopService(new Intent( this, MyBGMusicService.class ) );
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        BroadcastActivity.this.registerReceiver(
                airplaneModeBroadCastReceiver,
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        );

        BroadcastActivity.this.registerReceiver(
                charginModeBroadCastReceiver,
                new IntentFilter(Intent.ACTION_ANSWER)
        );
    }

    @Override
    protected void onStart() {

        super.onStart();
        Toast.makeText(this, "Broadcast activity has started(visible)", Toast.LENGTH_SHORT).show();
    }
}