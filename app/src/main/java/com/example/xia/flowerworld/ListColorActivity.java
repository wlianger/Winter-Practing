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

public class ListColorActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Color[] colors = {new Color("Red", R.drawable.red), new Color("Yellow", R.drawable.yellow), new Color("Orange", R.drawable.orange), new Color("Blue", R.drawable.blue), new Color("Green", R.drawable.green), new Color("Purple", R.drawable.purple), new Color("White", R.drawable.blank)};
    private List<Color> colorList = new ArrayList<>();
    private ColorAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;


   @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_color);
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
       navView.setCheckedItem(R.id.f_color);
       navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.f_story:{
                       Intent intent=new Intent(ListColorActivity.this,ListStoryActivity.class);
                       startActivity(intent);
                       break;
                   }
                   case R.id.f_color:{
                       mDrawerLayout.closeDrawers();
                       break;
                   }
                   case R.id.f_image:{
                       Intent intent=new Intent(ListColorActivity.this,ImageActivity.class);
                       startActivity(intent);
                       break;
                   }
                   case R.id.f_huayu:{
                       Intent intent=new Intent(ListColorActivity.this,LanguageActivity.class);
                       startActivity(intent);
                       break;
                   }
                   case R.id.f_finish:{
                       AlertDialog.Builder dialog=new AlertDialog.Builder(ListColorActivity.this);
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
                       Intent intent = new Intent(ListColorActivity.this, BlackFlowerActivity.class);
                       startActivity(intent);
                       break;
                   }
                   case R.id.f_update:{
                       Intent intent = new Intent(ListColorActivity.this, UpdateActivity.class);
                       startActivity(intent);
                       break;
                   }
                   case R.id.f_more:{
                       Intent intent = new Intent(ListColorActivity.this, MoreActivity.class);
                       startActivity(intent);
                       break;
                   }

                       default:
                           break;
               }
               return true;
           }
       });
       initColors();
       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_color_view);
       GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
       recyclerView.setLayoutManager(layoutManager);
       adapter = new ColorAdapter(colorList);
       recyclerView.setAdapter(adapter);
       swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
       swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
       swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               refreshColors();
           }
       });
   }
    private void refreshColors() {
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
                        initColors();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initColors() {
        colorList.clear();
        for (int i = 0; i < 7; i++) {
            colorList.add(colors[i]);
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
