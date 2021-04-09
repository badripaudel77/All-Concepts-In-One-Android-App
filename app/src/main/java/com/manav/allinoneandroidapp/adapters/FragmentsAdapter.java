package com.manav.allinoneandroidapp.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.manav.allinoneandroidapp.fragments.HomeFragment;
import com.manav.allinoneandroidapp.fragments.MessageFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {

    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : return new HomeFragment();
            case 1 : return new MessageFragment();

            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        //we just have two fragments now
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if(position == 0) {
            title = "Home";
        }
        else if (position == 1) {
            title = "Messages";
        }
        return title;
        //return super.getPageTitle(position);
    }
}
