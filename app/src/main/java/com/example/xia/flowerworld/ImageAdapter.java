package com.example.xia.flowerworld;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context mContext;

    private List<Image>mImageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageImage;
        public ViewHolder( View view) {
            super(view);
            cardView=(CardView)view;
            imageImage=(ImageView) view.findViewById(R.id.card_image1);
        }
    }
    public ImageAdapter(List<Image>imageList){
        mImageList=imageList;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view=LayoutInflater.from(mContext).inflate(R.layout.recyclecard,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image=mImageList.get(position);
        Glide.with(mContext).load(image.getImageId()).into(holder.imageImage);
    }
    @Override
    public int getItemCount() {
        return mImageList.size();
    }
}
