package com.example.xia.flowerworld;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ListStoryActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Flower[] flowers = { new Flower("芍药",R.drawable.chinese_herbaceous_peony),new Flower("满天星",R.drawable.mantainxing),new Flower("曼陀罗华",R.drawable.daturahua),new Flower("大雪素",R.drawable.daxuesu),new Flower("昙花",R.drawable.epiphyllum),new Flower("栀子花",R.drawable.gardenia),new Flower("秋海棠",R.drawable.qiuhaitang),new Flower("蝴蝶兰",R.drawable.hudielan),new Flower("风信子",R.drawable.hyacinth),new Flower("鸢尾花",R.drawable.lris),new Flower("茉莉花",R.drawable.jasmine),new Flower("薰衣草",R.drawable.lavender),new Flower("曼珠沙华",R.drawable.lycorisradiata),new Flower("梅兰",R.drawable.meilan),new Flower("水仙花",R.drawable.narcissus),new Flower("三色堇",R.drawable.pansy),new Flower("睡莲",R.drawable.shuilian),new Flower("七色堇",R.drawable.violet),new Flower("依米花",R.drawable.yimi_flower),new Flower("紫丁香",R.drawable.zidingxiang)};
    private List<Flower> flowerList = new ArrayList<>();
    private FlowerAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_story);
        SuperActivity.activityList.add(this);
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
        navView.setCheckedItem(R.id.f_story);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.f_color:{
                        Intent intent=new Intent(ListStoryActivity.this,ListColorActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_story:{
                        mDrawerLayout.closeDrawers();
                        break;
                    }
                    case R.id.f_image:{
                        Intent intent=new Intent(ListStoryActivity.this,ImageActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_huayu:{
                        Intent intent=new Intent(ListStoryActivity.this,LanguageActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_finish:{
                        AlertDialog.Builder dialog=new AlertDialog.Builder(ListStoryActivity.this);
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
                    case R.id.f_update:{
                        Intent intent = new Intent(ListStoryActivity.this, UpdateActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_sad: {
                        Intent intent = new Intent(ListStoryActivity.this, BlackFlowerActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_more:{
                        Intent intent = new Intent(ListStoryActivity.this, MoreActivity.class);
                        startActivity(intent);
                        break;
                    }
                    default:
                        break;
                }
                return true;
            }
        });
        initFlowers();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_story_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FlowerAdapter(flowerList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFlowers();
            }
        });
    }
    private void refreshFlowers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFlowers();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFlowers() {
        flowerList.clear();
        for (int i = 0; i < 20; i++) {
            flowerList.add(flowers[i]);
        }
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