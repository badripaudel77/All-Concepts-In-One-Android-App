package com.manav.allinoneandroidapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manav.allinoneandroidapp.BottomNavActivity;
import com.manav.allinoneandroidapp.BroadcastActivity;
import com.manav.allinoneandroidapp.DashboardActivity;
import com.manav.allinoneandroidapp.GoogleMapActivity;
import com.manav.allinoneandroidapp.JSONPlaceholderCRUDActivity;
import com.manav.allinoneandroidapp.LoadJSONActivity;
import com.manav.allinoneandroidapp.R;
import com.manav.allinoneandroidapp.RestApiActivity;
import com.manav.allinoneandroidapp.SQLiteDemoActivity;
import com.manav.allinoneandroidapp.TabActivity;
import com.manav.allinoneandroidapp.model.DataItem;

import java.util.ArrayList;

public class AppItemsAdapter extends RecyclerView.Adapter<AppItemsAdapter.ViewHolder> {

    Context context;
    private ArrayList<DataItem> dataItemArrayList;

    public AppItemsAdapter(Context context, ArrayList<DataItem> dataItemArrayList) {
        this.context = context;
        this.dataItemArrayList = dataItemArrayList;
    }

    //inflate the layout item on view creation.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.rc_view_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    //data set
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get all the item from the corresponding position
        DataItem dataItem = dataItemArrayList.get(position);

        //set the value
        holder.imageView.setImageResource(dataItem.getImageURL());
        holder.itemName.setText(dataItem.getItemName());

        //set onclick listener on recycler view item and start activity based on the position of the item in the list
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "position : " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Intent intent = new Intent(v.getContext(), TabActivity.class);
                    v.getContext().startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(v.getContext(), LoadJSONActivity.class);
                    v.getContext().startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(v.getContext(), BottomNavActivity.class);
                    v.getContext().startActivity(intent);
                }

                if(position == 3) {
                    v.getContext().startActivity(new Intent(v.getContext(), DashboardActivity.class));
                }

                if(position == 4) {
                    v.getContext().startActivity(new Intent(v.getContext(), RestApiActivity.class));
                }
                if(position == 5) {
                    v.getContext().startActivity(new Intent(v.getContext(), BroadcastActivity.class));
                }

                if(position == 6) {
                    v.getContext().startActivity(new Intent(v.getContext(), JSONPlaceholderCRUDActivity.class));
                }
                if(position == 7) {
                    v.getContext().startActivity(new Intent(v.getContext(), GoogleMapActivity.class));
                }
                if(position == 8) {
                    v.getContext().startActivity(new Intent(v.getContext(), SQLiteDemoActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItemArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //holds the view that we're going to change in run time, single_layout_item

        ImageView imageView;
        TextView itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.tvRCViewItemName);
            imageView = itemView.findViewById(R.id.imageView2);

        }
    }
}
