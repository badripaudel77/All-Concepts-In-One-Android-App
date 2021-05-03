package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.manav.allinoneandroidapp.model.JSONPostModel;
import com.manav.allinoneandroidapp.retrofit.RetrofitClientInstance;
import com.manav.allinoneandroidapp.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SinglePostDetailsActivity extends AppCompatActivity {
    private Gson gson;
    private JSONPostModel jsonPostModel;
    private TextView singlePostID, singlePostUserID, singlePostTitle, singlePostBody;
    private Button deletePostButton, submitEditPostBtn;
    private EditText etEditPostTitle, etEditPostBody;

    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post_details);

        singlePostID = findViewById(R.id.singlePostID);
        singlePostUserID = findViewById(R.id.singlePostUserID);
        singlePostTitle = findViewById(R.id.singlePostTitle);
        singlePostBody = findViewById(R.id.singlePostBody);

        etEditPostTitle = findViewById(R.id.etEditPostTitle);
        etEditPostBody = findViewById(R.id.etEditPostBody);

        deletePostButton = findViewById(R.id.deletePostButton);
        submitEditPostBtn = findViewById(R.id.submitEditPostBtn);

        retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

         gson = new Gson();
         jsonPostModel = gson.fromJson(getIntent().getStringExtra("myPostJson"), JSONPostModel.class);

       if(jsonPostModel != null) {
           singlePostID.setText(jsonPostModel.getId().toString());
           singlePostUserID.setText(jsonPostModel.getUserId().toString());
           singlePostTitle.setText(jsonPostModel.getTitle());
           singlePostBody.setText(jsonPostModel.getBody());
       }

       //make delete request
       deletePostButton.setOnClickListener(v -> {
           deletePost(jsonPostModel.getId());
       });

       //handle put / patch request
        submitEditPostBtn.setOnClickListener(v ->{
            String title = etEditPostTitle.getText().toString();
            String body = etEditPostBody.getText().toString();
            int id = jsonPostModel.getId();
            if(title.length()<1 || body.length()<1 || id <= 0) {
                Toast.makeText(this, "Invalid update", Toast.LENGTH_SHORT).show();
                return;
            }
            JSONPostModel post = new JSONPostModel(title, body);
            editPost(post, id);
        });
    }

    private void deletePost(int postID) {
        Toast.makeText(this, "deleting post with ID : " + postID, Toast.LENGTH_SHORT).show();
        Call<Void> call = retrofitService.deletePost(postID);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(SinglePostDetailsActivity.this, "Post deleted with response code "+ response.code(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SinglePostDetailsActivity.this, "Post couldn't be deleted. Response code "+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SinglePostDetailsActivity.this, "something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editPost(JSONPostModel postModel, int postId) {
        Toast.makeText(this, "editing post with id " + postId, Toast.LENGTH_SHORT).show();
        Call<JSONPostModel> call = retrofitService.updatePost(postId, postModel);

        call.enqueue(new Callback<JSONPostModel>() {
            @Override
            public void onResponse(Call<JSONPostModel> call, Response<JSONPostModel> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(SinglePostDetailsActivity.this, "Post " + postId + " Updated code : " +  response.code() + "\n new Data " + response.body().getTitle() + " and \n" + response.body().getBody(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SinglePostDetailsActivity.this, "Post couldn't be updated. Response code "+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JSONPostModel> call, Throwable t) {
                Toast.makeText(SinglePostDetailsActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}