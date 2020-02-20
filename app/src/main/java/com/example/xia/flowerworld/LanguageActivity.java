package com.example.xia.flowerworld;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class LanguageActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragments;
    List<String> titles;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        SuperActivity.activityList.add(this);
        tabLayout=(TabLayout)findViewById(R.id.my_tab);
        viewPager=(ViewPager)findViewById(R.id.my_viewpager);

        titles=new ArrayList<>();
        titles.add("百花之语");
        titles.add("死亡花语");
        titles.add("星座花语");

        fragments=new ArrayList<>();
        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragment3=new Fragment3();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);


        MyFragmentPager myFragmentPager=new MyFragmentPager(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(myFragmentPager);
        tabLayout.setupWithViewPager(viewPager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_new);
        navView.setItemIconTintList(null);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.dot_select);
        }
        navView.setCheckedItem(R.id.f_huayu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem Menuitem) {
                switch (Menuitem.getItemId()) {
                    case R.id.f_image: {
                        Intent intent = new Intent(LanguageActivity.this, ImageActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_huayu: {
                        mDrawerLayout.closeDrawers();
                        break;
                    }
                    case R.id.f_story: {
                        Intent intent = new Intent(LanguageActivity.this, ListStoryActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_color: {
                        Intent intent = new Intent(LanguageActivity.this, ListColorActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_finish:{
                        AlertDialog.Builder dialog=new AlertDialog.Builder(LanguageActivity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("是确定退出？");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent startMain = new Intent(Intent.ACTION_MAIN);
                                startMain.addCategory(Intent.CATEGORY_HOME);
                                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(startMain);
                                System.exit(0);
                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        dialog.show();
                        break;
                    }
                    case R.id.f_sad: {
                        Intent intent = new Intent(LanguageActivity.this, BlackFlowerActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_update:{
                        Intent intent = new Intent(LanguageActivity.this, UpdateActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_more:{
                        Intent intent = new Intent(LanguageActivity.this, MoreActivity.class);
                        startActivity(intent);
                        break;
                    }
                    default:
                        break;
                }
                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}
