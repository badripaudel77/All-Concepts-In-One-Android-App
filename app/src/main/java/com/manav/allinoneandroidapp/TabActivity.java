package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.manav.allinoneandroidapp.adapters.FragmentsAdapter;

public class TabActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        getSupportActionBar().hide();

        viewPager = findViewById(R.id.viewPager);

        //set adpater to the view pager
        viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));

        tabLayout = findViewById(R.id.tabLayout);

        //attach viewpager in tablayout
        tabLayout.setupWithViewPager(viewPager);

    }
}