package com.manav.allinoneandroidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manav.allinoneandroidapp.R;
import com.manav.allinoneandroidapp.model.JSONPostModel;
import com.manav.allinoneandroidapp.model.PhotoModel;
import com.manav.allinoneandroidapp.retrofit.RetrofitClientInstance;
import com.manav.allinoneandroidapp.services.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JSONPlaceholderAdapter extends RecyclerView.Adapter<JSONPlaceholderAdapter.ViewHolder> {
    ArrayList<JSONPostModel> jsonPostModelArrayList;
    Context context;

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
        // holder.tvId.setText(jsonPostModel.getId()); don't do this, setting int to setText causes render problem.
        holder.tvUserId.setText(jsonPostModel.getUserId().toString());
        holder.tvTitle.setText(jsonPostModel.getTitle());
        holder.tvBody.setText(jsonPostModel.getBody());

        //handling onclick on whole (post mode) item while loading json
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "get one post ... to be handled " + jsonPostModel.getId(), Toast.LENGTH_SHORT).show();
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
}