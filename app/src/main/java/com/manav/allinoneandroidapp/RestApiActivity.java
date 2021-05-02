package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.manav.allinoneandroidapp.adapters.RetroPhotoItemAdapter;
import com.manav.allinoneandroidapp.model.PhotoModel;
import com.manav.allinoneandroidapp.retrofit.RetrofitClientInstance;
import com.manav.allinoneandroidapp.services.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestApiActivity extends AppCompatActivity {

    RetrofitService retrofitService;
    Button loadJsonDataBtn;
    //ArrayList<PhotoModel> photoModelList;

    private RecyclerView retroRecylerView;
    private RetroPhotoItemAdapter retroPhotoItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        //photoModelList = new ArrayList<>();

        loadJsonDataBtn = findViewById(R.id.loadJsonData);

        retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

        loadJsonDataBtn.setOnClickListener(v -> {
            Toast.makeText(this, "loading data... please wait? ", Toast.LENGTH_SHORT).show();

            //get all photos
            //Call<ArrayList<PhotoModel>> call = retrofitService.getAllPhotos();
            Call<ArrayList<PhotoModel>> call = retrofitService.getAllPhotos();

            call.enqueue(new Callback<ArrayList<PhotoModel>>() {
                @Override
                public void onResponse(Call<ArrayList<PhotoModel>> call, Response<ArrayList<PhotoModel>> response) {
                    if(response.isSuccessful()) {
                        Log.i("all photos", "response ? : " + response.body().get(0).getTitle());
                        // Toast.makeText(RestApiActivity.this, "get first title ... " + response.body().get(0).getTitle() + " url " + response.body().get(0).getUrl(), Toast.LENGTH_SHORT).show();
                        generateData(response.body());
                    }
                    else {
                        int responseCode = response.code();
                        //handle
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PhotoModel>> call, Throwable t) {
                    Toast.makeText(RestApiActivity.this, "data from json place holder not fetched", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void generateData(ArrayList<PhotoModel> photoModels) {
        retroRecylerView = findViewById(R.id.retroRCV);
        retroPhotoItemAdapter = new RetroPhotoItemAdapter(photoModels, RestApiActivity.this);
        retroRecylerView.setLayoutManager(new LinearLayoutManager(this));
        retroRecylerView.setAdapter(retroPhotoItemAdapter);
    }
}