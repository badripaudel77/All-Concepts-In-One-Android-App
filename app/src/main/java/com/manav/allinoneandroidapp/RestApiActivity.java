package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONObject;

public class RestApiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        String myJson = loadJsonData();

        try {
            JSONObject jsonObject = new JSONObject(myJson);
        }
        catch (Exception exception) {

        }

    }

    private String loadJsonData() {
        String json = null;


        return  "hello";
    }
}