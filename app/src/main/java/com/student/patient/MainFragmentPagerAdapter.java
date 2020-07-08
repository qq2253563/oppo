package com.student.patient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.student.patient.fragment.DataFragment;
import com.student.patient.fragment.HomeFragment;

public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public MainFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.Companion.newInstance();
            default:
                return DataFragment.Companion.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}