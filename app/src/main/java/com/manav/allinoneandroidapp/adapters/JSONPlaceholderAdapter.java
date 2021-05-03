package com.manav.allinoneandroidapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.manav.allinoneandroidapp.R;
import com.manav.allinoneandroidapp.SinglePostDetailsActivity;
import com.manav.allinoneandroidapp.model.JSONPostModel;
import com.manav.allinoneandroidapp.retrofit.RetrofitClientInstance;
import com.manav.allinoneandroidapp.services.RetrofitService;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JSONPlaceholderAdapter extends RecyclerView.Adapter<JSONPlaceholderAdapter.ViewHolder> {
    ArrayList<JSONPostModel> jsonPostModelArrayList;
    Context context;

    private RetrofitService retrofitService;

    public JSONPlaceholderAdapter(ArrayList<JSONPostModel> jsonPostModelArrayList, Context context) {
        this.jsonPostModelArrayList = jsonPostModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_json_placeholder , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JSONPostModel jsonPostModel = jsonPostModelArrayList.get(position);

        holder.tvId.setText(jsonPostModel.getId().toString());
        // **** holder.tvId.setText(jsonPostModel.getId()); don't do this, setting int to setText causes render problem.
        holder.tvUserId.setText(jsonPostModel.getUserId().toString());
        holder.tvTitle.setText(jsonPostModel.getTitle());
        holder.tvBody.setText(jsonPostModel.getBody());

        //handling onclick on whole (post mode) item while loading json
        holder.itemView.setOnClickListener(v -> {
            getOnePost(jsonPostModel.getId());
        });
    }

    @Override
    public int getItemCount() {
        return jsonPostModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvUserId, tvTitle, tvBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvUserId = itemView.findViewById(R.id.tvUserId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }

    //handle one post request
    private void getOnePost(Integer id) {

        retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<JSONPostModel> call = retrofitService.getOnePost(id);
        call.enqueue(new Callback<JSONPostModel>() {
            @Override
            public void onResponse(Call<JSONPostModel> call, Response<JSONPostModel> response) {
                final int code = response.code();
                JSONPostModel postModel = response.body();

                if(response.isSuccessful()) {
                    Toast.makeText(context, "data has arrived : single post => " + postModel.getId() + " \n" + response.body().getTitle() + " \n", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, SinglePostDetailsActivity.class);

                    //first convert object to json as string and receive in another activity as convert back to object
                    Gson gson = new Gson();
                    String myPostJson = gson.toJson(new JSONPostModel(postModel.getId(), postModel.getUserId(), postModel.getTitle(), postModel.getBody()));
                    intent.putExtra("myPostJson", myPostJson);
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(context, "something went wrong with status code " + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JSONPostModel> call, Throwable t) {
                Toast.makeText(context, "network call error ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}