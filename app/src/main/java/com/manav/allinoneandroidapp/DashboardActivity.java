package com.manav.allinoneandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.manav.allinoneandroidapp.fragments.AccountFragment;
import com.manav.allinoneandroidapp.fragments.HomeFragment;
import com.manav.allinoneandroidapp.fragments.PaymentFragment;
import com.manav.allinoneandroidapp.fragments.SearchFragment;

public class DashboardActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.customToolbar);

        //toolbar.setTitle("");
        toolbar.setTitle("Dashboard");

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);


        //by default set one fragment
        switchFragment(new AccountFragment());

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.settings:
                        HomeFragment homeFragment = new HomeFragment();
                        switchFragment(homeFragment);
                        break;

                    case R.id.payment:
                        PaymentFragment paymentFragment = new PaymentFragment();
                        switchFragment(paymentFragment);
                        break;
                    case R.id.account:
                        switchFragment(new AccountFragment());
                        break;

                    case R.id.aboutApp:
                        switchFragment(new SearchFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.replacableContainer, fragment);
        drawerLayout.closeDrawer(navigationView, true);
        transaction.commit();
    }
}