package com.manav.allinoneandroidapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manav.allinoneandroidapp.R;
import com.manav.allinoneandroidapp.model.PhotoModel;
import com.manav.allinoneandroidapp.retrofit.RetrofitClientInstance;
import com.manav.allinoneandroidapp.services.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroPhotoItemAdapter extends RecyclerView.Adapter<RetroPhotoItemAdapter.ViewHolder> {
    ArrayList<PhotoModel> photoModelArrayList;
    Context context;

    RetrofitService retrofitService;


    public RetroPhotoItemAdapter(ArrayList<PhotoModel> photoModelArrayList, Context context) {
        this.photoModelArrayList = photoModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.retrofit_photo_item , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PhotoModel photoModel = photoModelArrayList.get(position);
        holder.title.setText(photoModel.getTitle());

        //set the image using picasso or glide
        //Toast.makeText(context, "profile " + user.getAvatar(), Toast.LENGTH_SHORT).show();

        Picasso.get().load(photoModel.getUrl()).into(holder.retroImgURL);
        Picasso.get().load(photoModel.getThumbnailUrl()).into(holder.retroThumbnail);


       // Glide.with(context).load(photoModel.getThumbnailUrl()).placeholder(R.drawable.ic_launcher_foreground).into(holder.retroThumbnail);
        //Glide.with(context).load(photoModel.getUrl()).placeholder(R.drawable.ic_launcher_foreground).into(holder.retroImgURL);

        //handling onclick on whole (photomodel) item while loading json
        holder.itemView.setOnClickListener(v -> {
            requestSinglePhoto(photoModel.getId());
        });
    }

    @Override
    public int getItemCount() {
        return photoModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView retroThumbnail, retroImgURL;

        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            retroThumbnail = itemView.findViewById(R.id.retroThumbnail);
            retroImgURL = itemView.findViewById(R.id.retroImgURL);
            title = itemView.findViewById(R.id.retroTitle);
        }
    }

    private void requestSinglePhoto(int id) {
        Toast.makeText(context, "requ one phot........" + id, Toast.LENGTH_SHORT).show();
        retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

        //get one photo
        Call<List<PhotoModel>> call = retrofitService.getOnePhoto(id);

        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                Toast.makeText(context, response.body().get(0).getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                Toast.makeText(context, "no photo found for id : " + id , Toast.LENGTH_SHORT).show();
            }
        });

    }
}
