package com.example.xia.flowerworld;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private DrawerLayout mDrawerLayout;
    private Image[] images={new Image(R.drawable.chinese_herbaceous_peony),new Image(R.drawable.daturahua),new Image(R.drawable.daxuesu),new Image(R.drawable.epiphyllum),new Image(R.drawable.feiyancao),new Image(R.drawable.gardenia),new Image(R.drawable.gengju),new Image(R.drawable.guibeizhu),new Image(R.drawable.guihua),new Image(R.drawable.hanxiucao),new Image(R.drawable.helanwang),new Image(R.drawable.hua),new Image(R.drawable.hudielan),new Image(R.drawable.hyacinth),new Image(R.drawable.jasmine),new Image(R.drawable.jinyinmu),new Image(R.drawable.lavender),new Image(R.drawable.lianhua),new Image(R.drawable.linglan),new Image(R.drawable.liuyuexue),new Image(R.drawable.lris),new Image(R.drawable.lycorisradiata),new Image(R.drawable.malilian),new Image(R.drawable.mantainxing),new Image(R.drawable.meilan),new Image(R.drawable.midiexiang),new Image(R.drawable.moli_1),new Image(R.drawable.moli_2),new Image(R.drawable.narcissus),new Image(R.drawable.pansy),new Image(R.drawable.qiuhaitang),new Image(R.drawable.shuilian),new Image(R.drawable.tanhua),new Image(R.drawable.violet),new Image(R.drawable.xiankelai),new Image(R.drawable.yimi_flower),new Image(R.drawable.yingchunhua),new Image(R.drawable.zhizi_1),new Image(R.drawable.zhizi_2),new Image(R.drawable.zidingxiang)};
    private List<Image> imageList=new ArrayList<>();
    private ImageAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private ViewPager viewPager;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private String[] contentDescs;
    private TextView tv_desc;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
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
        navView.setCheckedItem(R.id.f_image);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem Menuitem) {
                switch (Menuitem.getItemId()) {
                    case R.id.f_image: {
                         mDrawerLayout.closeDrawers();
                         break;
                    }
                    case R.id.f_huayu: {
                        Intent intent = new Intent(ImageActivity.this, LanguageActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_story: {
                        Intent intent = new Intent(ImageActivity.this, ListStoryActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_color: {
                        Intent intent = new Intent(ImageActivity.this, ListColorActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_update:{
                        Intent intent = new Intent(ImageActivity.this, UpdateActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_more:{
                        Intent intent = new Intent(ImageActivity.this, MoreActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.f_finish:{
                        AlertDialog.Builder dialog=new AlertDialog.Builder(ImageActivity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("是确定退出？");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SuperActivity.exitClient(ImageActivity.this);
                            }
                        });
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                        break;
                    }
                    case R.id.f_sad:
                        Intent intent = new Intent(ImageActivity.this, BlackFlowerActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
        initImages();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImageAdapter(imageList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshImages();
            }
        });
        // 初始化布局 View视图
        initViews();
        // Model数据
        initData();
        // Controller 控制器
        initAdapter();
        // 开启轮询
        new Thread() {

            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(3540);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 往下跳一位
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("设置当前位置: " + viewPager.getCurrentItem());
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                            }
                    });
                }
            }



            ;

        }.start();
    }

    private void refreshImages() {
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
                        initImages();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initImages() {
        imageList.clear();
        for (int i = 0; i < 81; i++) {
            Random random=new Random();
            int index=random.nextInt(images.length);
            imageList.add(images[index]);
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

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(this);
//		viewPager.setOffscreenPageLimit(1);
        ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
    }

    private void initData() {
        imageResIds = new int[]{R.drawable.background_3, R.drawable.background_2,R.drawable.background_4,R.drawable.background_5,R.drawable.topbackground_1};
        contentDescs = new String[]{
                "花落草齐生，莺飞蝶双戏",
                "黄四娘家花满蹊，千朵万朵压枝低",
                "独怜幽草涧边生，上有黄鹂深树鸣",
                "草色烟光残照里",
                "花开不并百花丛，独立疏篱趣未穷",
        };
        imageViewList = new ArrayList<ImageView>();
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.dot_normal);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0)
                layoutParams.leftMargin = 10;
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }
    }
    private void initAdapter() {
        ll_point_container.getChildAt(0).setEnabled(true);
        tv_desc.setText(contentDescs[0]);
        previousSelectedPosition = 0;
        viewPager.setAdapter(new MyAdapter());
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        viewPager.setCurrentItem(5000000);
    }


    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);
            int newPosition = position % imageViewList.size();
            ImageView imageView = imageViewList.get(newPosition);
            container.addView(imageView);
            return imageView;
        }
        @Override

        public void destroyItem(ViewGroup container, int position, Object object) {
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }

    @Override

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override

    public void onPageSelected(int position) {
        System.out.println("onPageSelected: " + position);
        int newPosition = position % imageViewList.size();
        tv_desc.setText(contentDescs[newPosition]);
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);
        // 记录之前的位置
        previousSelectedPosition = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 滚动状态变化时调用
    }
}
