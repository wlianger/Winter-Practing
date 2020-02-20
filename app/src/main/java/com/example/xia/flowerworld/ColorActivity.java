package com.example.xia.flowerworld;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ColorActivity extends AppCompatActivity {
    public static final String TOP_NAME="top_name";
    public static final String TOP_IMAGE_ID="top_image_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String topName=intent.getStringExtra(TOP_NAME);
        switch (topName){
            case "Red":
                setContentView(R.layout.activity_color2);
                break;
            case "Blue":
                setContentView(R.layout.top_blue);
                break;
            case "Green":
                setContentView(R.layout.top_green);
                break;
            case "Orange":
                setContentView(R.layout.top_orange);
                break;
            case "Purple":
                setContentView(R.layout.top_purple);
                break;
            case "White":
                setContentView(R.layout.top_white);
                break;
            case "Yellow":
                setContentView(R.layout.top_yellow);
                break;
                default:
        }
        int TopImageId=intent.getIntExtra(TOP_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView topImageView=(ImageView) findViewById(R.id.top_image);


        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(topName);
        Glide.with(this).load(TopImageId).into(topImageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
