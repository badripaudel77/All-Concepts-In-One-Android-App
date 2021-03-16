package com.manav.allinoneandroidapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manav.allinoneandroidapp.R;
import com.manav.allinoneandroidapp.TabActivity;
import com.manav.allinoneandroidapp.models.DataItem;

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
                Toast.makeText(context, "position : " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Intent intent = new Intent(v.getContext(), TabActivity.class);
                    v.getContext().startActivity(intent);
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
