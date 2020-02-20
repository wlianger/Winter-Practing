package com.example.xia.flowerworld;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class ImageBannerFramLayout extends FrameLayout implements ImageBarnnerViewGroup.ImageBarnnerViewGroupLisnner,ImageBarnnerViewGroup.ImageBarnnerLister{


    private ImageBarnnerViewGroup imageBarnnerViewGroup;
    private LinearLayout linearLayout;
    private FramLayoutLisenner lisenner;

    public FramLayoutLisenner getLisenner() {
        return lisenner;
    }

    public void setLisenner(FramLayoutLisenner lisenner) {
        this.lisenner = lisenner;
    }

    public ImageBannerFramLayout(@NonNull Context context) {
        super(context);
        initImageBarnnerViewGroup();
        initDotLinearlayout();
    }

    public ImageBannerFramLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initImageBarnnerViewGroup();
        initDotLinearlayout();
    }

    public ImageBannerFramLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBarnnerViewGroup();
        initDotLinearlayout();
    }

    public void addBitmaps(List<Bitmap> list) {
        for (int i = 0; i < list.size(); i++) {
            Bitmap bitmap = list.get(i);
            addBitmapToImageBarnnerViewGroup(bitmap);
            addDotToLinearlayout();
        }
    }

    private void addDotToLinearlayout() {
        ImageView iv = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(5,5,5,5);
        iv.setLayoutParams(layoutParams);
        iv.setImageResource(R.drawable.dot_normal);
        linearLayout.addView(iv);
    }

    private void addBitmapToImageBarnnerViewGroup(Bitmap bitmap) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageBitmap(bitmap);
        imageBarnnerViewGroup.addView(imageView);
    }

    //初始化自定义图片轮播功能核心类

    private void initImageBarnnerViewGroup() {
        imageBarnnerViewGroup = new ImageBarnnerViewGroup(getContext());
        LayoutParams layoutParams = new LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        imageBarnnerViewGroup.setLayoutParams(layoutParams);
        imageBarnnerViewGroup.setBarnnerViewGroupLisnner(this);//将linsnner传递给Framlayout
        imageBarnnerViewGroup.setLister(this);
        addView(imageBarnnerViewGroup);
    }

    //初始化底部圆点布局
    private void initDotLinearlayout() {
        linearLayout = new LinearLayout(getContext());
        LayoutParams layoutParams = new LayoutParams
                (LayoutParams.MATCH_PARENT, 40);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.RED);
        addView(linearLayout);
        LayoutParams layoutParams1 = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(layoutParams1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            linearLayout.setAlpha(0.5f);
        } else {
            linearLayout.getBackground().setAlpha(100);
        }
    }

    @Override

    public void selectImage(int index) {
        int count = linearLayout.getChildCount();
        for (int i = 0;i < count; i++) {
            ImageView iv = (ImageView) linearLayout.getChildAt(i);
            if (i == index) {
                iv.setImageResource(R.drawable.dot_select);
            } else {
                iv.setImageResource(R.drawable.dot_normal);
            }
        }
    }


    @Override
    public void chickImageIndex(int pos) {
        lisenner.chickImageIndex(pos);
    }

    public interface FramLayoutLisenner{
        void chickImageIndex(int pos);
    }
}
