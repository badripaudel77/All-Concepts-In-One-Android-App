package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> startActivity(new Intent(this, HomeScreenActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Toast Demo", Toast.LENGTH_SHORT).show();
    }
}