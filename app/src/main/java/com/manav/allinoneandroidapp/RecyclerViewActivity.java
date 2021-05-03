package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.manav.allinoneandroidapp.adapters.AppItemsAdapter;
import com.manav.allinoneandroidapp.model.DataItem;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    Button btnVerticalList, listBtn, btnGridList;

    RecyclerView recyclerView;
    ArrayList<DataItem> dataItemList;

    AppItemsAdapter appItemsAdapter;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        getSupportActionBar().hide();

        getWindow().setTitle("Recycler View : Select One");
        //initialize buttons
        btnVerticalList = findViewById(R.id.btnVerticalList);
        listBtn = findViewById(R.id.listViewBtn);
        btnGridList = findViewById(R.id.btnGridList);

        //initialize recycler view
        recyclerView = findViewById(R.id.recyclerView);

        //instantiate data list and adapter
        dataItemList = new ArrayList<>();
        appItemsAdapter = new AppItemsAdapter(this, dataItemList);

        //linerar layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL); // set this as default

        //grid layout manager
        //set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        gridLayoutManager = new GridLayoutManager(this,2);
        //add items to list
        dataItemList.add(new DataItem(R.mipmap.ic_launcher_round, "Tab Activity"));
        dataItemList.add(new DataItem(R.mipmap.ic_launcher_round, "Load JSON"));
        dataItemList.add(new DataItem(R.mipmap.ic_launcher_round, "Bottom Nav"));
        dataItemList.add(new DataItem(R.mipmap.ic_launcher_round, "Dashboard"));
        dataItemList.add(new DataItem(R.mipmap.ic_launcher_round, "Rest API - Retrofit"));
        dataItemList.add(new DataItem(R.mipmap.ic_launcher_round, "BroadCast and Services"));
        dataItemList.add(new DataItem(R.mipmap.ic_launcher_round, "CRUD Json Placeholder api"));

        //set adapter
        recyclerView.setAdapter(appItemsAdapter);


        btnVerticalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            }
        });

        btnGridList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        });
    }
}