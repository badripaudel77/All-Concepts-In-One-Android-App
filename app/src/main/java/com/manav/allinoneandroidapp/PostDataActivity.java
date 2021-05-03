package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manav.allinoneandroidapp.model.JSONPostModel;
import com.manav.allinoneandroidapp.retrofit.RetrofitClientInstance;
import com.manav.allinoneandroidapp.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataActivity extends AppCompatActivity {

    private EditText etPostTitle, etPostBody;

    private Button submitPostButton;

    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

        etPostTitle = findViewById(R.id.etPostTitle);
        etPostBody = findViewById(R.id.etPostBody);
        submitPostButton = findViewById(R.id.submitPostBtn);

        submitPostButton.setOnClickListener(v -> {
            String title = etPostTitle.getText().toString();
            String body = etPostBody.getText().toString();

            if(title.length()<1 || body.length()<1) {
                Toast.makeText(this, "please fill every fields", Toast.LENGTH_SHORT).show();
                return;
            }
            postData(etPostTitle.getText().toString(), etPostBody.getText().toString());
        });
    }
    private void postData(String title, String  body) {
        JSONPostModel postModel = new JSONPostModel(title, body);
        retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<JSONPostModel> call = retrofitService.createPost(postModel);

        call.enqueue(new Callback<JSONPostModel>() {
            @Override
            public void onResponse(Call<JSONPostModel> call, Response<JSONPostModel> response) {

                if(response.isSuccessful() && response.body() != null) {
                    JSONPostModel createdPost = response.body();
                    Toast.makeText(PostDataActivity.this, "response code : " + response.code() + " ID : " + createdPost.getId() + " Title : " + createdPost.getTitle() + " Body : " + createdPost.getBody() , Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(PostDataActivity.this, "Post couldn't be created with status code " + response.code(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<JSONPostModel> call, Throwable t) {
                Toast.makeText(PostDataActivity.this, "Something went wrong in network", Toast.LENGTH_SHORT).show();
            }
        });
    }
}