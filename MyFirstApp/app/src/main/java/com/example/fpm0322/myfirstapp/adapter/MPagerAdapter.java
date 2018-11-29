package com.example.fpm0322.myfirstapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    private List<String> mTtileList;

    public MPagerAdapter(FragmentManager fm, List<Fragment> fragments,List<String> mTtileList){
        super(fm);
        this.fragments = fragments;
        this.mTtileList = mTtileList;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTtileList.get(position);
    }
}
