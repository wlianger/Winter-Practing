package com.example.xia.flowerworld;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPager extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;
    private List<String> title;


    public MyFragmentPager(FragmentManager supportFragmentManager, List<Fragment> fragments, List<String> titles) {
        super(supportFragmentManager);
        fragmentManager = supportFragmentManager;
        fragmentList = fragments;
        title = titles;

    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return title.get(position);
    }
}
