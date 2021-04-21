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

import com.bumptech.glide.Glide;
import com.manav.allinoneandroidapp.R;
import com.manav.allinoneandroidapp.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    ArrayList<UserModel> userModelArrayList;
    Context context;

    public ItemAdapter(ArrayList<UserModel> userModelArrayList, Context context) {
        this.userModelArrayList = userModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.json_user_item , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserModel user = userModelArrayList.get(position);
        holder.fullname.setText(user.getFirstName() + " " + user.getLastName());
        holder.email.setText(user.getEmail());

        //set the image using picasso or glide
        //Toast.makeText(context, "profile " + user.getAvatar(), Toast.LENGTH_SHORT).show();

        //Picasso.get().load("https://s0.2mdn.net/6629020/RMKT-Q1-2021_DOMAIN-SET-B_INTL_160X600_IMAGE.png").into(holder.profile);

        Glide.with(context).load(user.getAvatar()).placeholder(R.drawable.ic_launcher_foreground).into(holder.profile);

        //handling onclick on whole (usermodel) item while loading json
        holder.itemView.setOnClickListener(v ->
                Toast.makeText(context, "User : " + user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profile;

        TextView fullname, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile);
            fullname = itemView.findViewById(R.id.fullname);
            email = itemView.findViewById(R.id.email);
        }
    }
}
