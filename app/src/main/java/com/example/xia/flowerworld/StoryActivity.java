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

public class StoryActivity extends AppCompatActivity {
    public static final String STORY_NAME="story_name";
    public static final String STORY_IMAGE_ID="story_image_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String storyName=intent.getStringExtra(STORY_NAME);
        switch (storyName){
            case "芍药":
                setContentView(R.layout.activity_story);
                break;
            case "大雪素":
                setContentView(R.layout.story_daxuesu);
                break;
            case "风信子":
                setContentView(R.layout.story_fengxinzi);
                break;
            case "秋海棠":
                setContentView(R.layout.story_haitang);
                break;
            case "蝴蝶兰":
                setContentView(R.layout.story_hudielan);
                break;
            case "满天星":
                setContentView(R.layout.story_mantianxing);
                break;
            case "曼陀罗华":
                setContentView(R.layout.story_mantuoluohua);
                break;
            case "曼珠沙华":
                setContentView(R.layout.story_manzhushahhua);
                break;
            case "梅兰":
                setContentView(R.layout.story_meilan);
                break;
            case "茉莉花":
                setContentView(R.layout.story_moli);
                break;
            case "七色堇":
                setContentView(R.layout.story_qisejin);
                break;
            case "三色堇":
                setContentView(R.layout.story_sansejin);
                break;
            case "睡莲":
                setContentView(R.layout.story_shuilian);
                break;
            case "水仙花":
                setContentView(R.layout.story_shuixianhua);
                break;
            case "昙花":
                setContentView(R.layout.story_tanhua);
                break;
            case "薰衣草":
                setContentView(R.layout.story_xunyicao);
                break;
            case "依米花":
                setContentView(R.layout.story_yimihua);
                break;
            case "鸢尾花":
                setContentView(R.layout.story_yuanwei);
                break;
            case "栀子花":
                setContentView(R.layout.story_zhizi);
                break;
            case "紫丁香":
                setContentView(R.layout.story_zidingxiang);
                break;
            default:
        }
        int TopImageId=intent.getIntExtra(STORY_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView topImageView=(ImageView) findViewById(R.id.top_image);


        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(storyName);
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
