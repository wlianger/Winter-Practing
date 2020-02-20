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

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    private Context mContext;
    private List<Color> mColorList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView colorImage;
        TextView colorName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            colorImage = (ImageView) view.findViewById(R.id.card_image);
            colorName = (TextView) view.findViewById(R.id.card_name);
        }
    }
    public ColorAdapter(List<Color>colorList){
        mColorList=colorList;
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
                Color color=mColorList.get(position);
                Intent intent=new Intent(mContext,ColorActivity.class);
                intent.putExtra(ColorActivity.TOP_NAME,color.getName());
                intent.putExtra(ColorActivity.TOP_IMAGE_ID,color.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         Color color=mColorList.get(position);
         holder.colorName.setText(color.getName());
         Glide.with(mContext).load(color.getImageId()).into(holder.colorImage);
    }
    @Override
    public int getItemCount(){
        return mColorList.size();
    }
}
