package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.manav.allinoneandroidapp.adapters.JSONPlaceholderAdapter;
import com.manav.allinoneandroidapp.model.JSONPostModel;
import com.manav.allinoneandroidapp.retrofit.RetrofitClientInstance;
import com.manav.allinoneandroidapp.services.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JSONPlaceholderCRUDActivity extends AppCompatActivity {
    private RetrofitService retrofitService;
    private JSONPlaceholderAdapter jsonPlaceholderAdapter;
    private RecyclerView jsonPlaceholderRCV;

    private Button getDataButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_s_o_n_placeholder_c_r_u_d);

        getDataButton = findViewById(R.id.getDataButton);
        progressBar = findViewById(R.id.indeterminateBar);

        retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

        getDataButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);

            //get all the posts
            Call<ArrayList<JSONPostModel>> call = retrofitService.getAllPosts();

            call.enqueue(new Callback<ArrayList<JSONPostModel>>() {
                @Override
                public void onResponse(Call<ArrayList<JSONPostModel>> call, Response<ArrayList<JSONPostModel>> response) {
                    if(response.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(JSONPlaceholderCRUDActivity.this, "data ? " + response.body().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        ArrayList<JSONPostModel> postModels = response.body();
                        showData(postModels);
                    }
                    else {
                        //handle response code ...
                        int responseCode = response.code();
                        Toast.makeText(JSONPlaceholderCRUDActivity.this, "error occurred with status code " + responseCode, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<JSONPostModel>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(JSONPlaceholderCRUDActivity.this, "Data Failed to Load.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    protected void showData(ArrayList<JSONPostModel> jsonPostModelArrayList) {
        Toast.makeText(this, "  fine : " + jsonPostModelArrayList.get(0).getId(), Toast.LENGTH_SHORT).show();
        jsonPlaceholderRCV = findViewById(R.id.jsonPlaceholderRCV);
        jsonPlaceholderAdapter = new JSONPlaceholderAdapter(jsonPostModelArrayList, this);
        jsonPlaceholderRCV.setLayoutManager(new LinearLayoutManager(this));
        jsonPlaceholderRCV.setAdapter(jsonPlaceholderAdapter); // this line is causing the problem.
    }
}