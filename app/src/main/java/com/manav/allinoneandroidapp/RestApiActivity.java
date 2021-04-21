package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.manav.allinoneandroidapp.model.PhotoModel;
import com.manav.allinoneandroidapp.retrofit.RetrofitClientInstance;
import com.manav.allinoneandroidapp.services.RetrofitService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestApiActivity extends AppCompatActivity {

    RetrofitService retrofitService;
    Button loadJsonDataBtn;
    ArrayList<PhotoModel> photoModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        photoModelList = new ArrayList<>();

        loadJsonDataBtn = findViewById(R.id.loadJsonData);

        retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

        loadJsonDataBtn.setOnClickListener(v -> {
            Toast.makeText(this, "working ? ", Toast.LENGTH_SHORT).show();
            //get all photos
            Call<List<PhotoModel>> call = retrofitService.getAllPhotos();

            call.enqueue(new Callback<List<PhotoModel>>() {
                @Override
                public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                    Log.i("all photos", "response ? : " + response.body());
                    Toast.makeText(RestApiActivity.this, "get.. " + response.body().get(0), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                    Toast.makeText(RestApiActivity.this, "data from json place holder not fetched", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "rest api activity has started.", Toast.LENGTH_SHORT).show();
    }
}