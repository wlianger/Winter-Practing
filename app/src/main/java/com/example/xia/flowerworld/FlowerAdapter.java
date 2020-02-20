package com.example.xia.flowerworld;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.ViewHolder> {
    private Context mContext;
    private List<Flower> mFlowerList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView flowerImage;
        TextView flowerName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            flowerImage = (ImageView) view.findViewById(R.id.card_image);
           flowerName = (TextView) view.findViewById(R.id.card_name);
        }
    }
    public FlowerAdapter(List<Flower>flowerList){
        mFlowerList=flowerList;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycletextcard, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Flower flower=mFlowerList.get(position);
                Intent intent=new Intent(mContext,StoryActivity.class);
                intent.putExtra(StoryActivity.STORY_NAME,flower.getName());
                intent.putExtra(StoryActivity.STORY_IMAGE_ID,flower.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Flower flower=mFlowerList.get(position);
        holder.flowerName.setText(flower.getName());
        Glide.with(mContext).load(flower.getImageId()).into(holder.flowerImage);
    }
    @Override
    public int getItemCount(){
        return mFlowerList.size();
    }
}
