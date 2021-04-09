package com.manav.allinoneandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.manav.allinoneandroidapp.fragments.AccountFragment;
import com.manav.allinoneandroidapp.fragments.PaymentFragment;
import com.manav.allinoneandroidapp.fragments.SearchFragment;

public class BottomNavActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottomNav);

        //by default select this
        switchFragment(new AccountFragment());

        //set onclick listener on bottom nav, find specific item based on MenuItem
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.account :
                        Toast.makeText(BottomNavActivity.this, "Your Account", Toast.LENGTH_SHORT).show();

                        //replace by new fragment
                        switchFragment(new AccountFragment());
                        return true;

                    case R.id.payment :
                        Toast.makeText(BottomNavActivity.this, "Payment Info", Toast.LENGTH_SHORT).show();

                        switchFragment(new PaymentFragment());
                        return true;

                    case R.id.search :
                        Toast.makeText(BottomNavActivity.this, "Search Here", Toast.LENGTH_SHORT).show();

                        switchFragment(new SearchFragment());
                        return true;

                    default:
                        switchFragment(new AccountFragment());
                        return true;
                }
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.layoutToReplace, fragment);
        fragmentTransaction.commit();
    }
}