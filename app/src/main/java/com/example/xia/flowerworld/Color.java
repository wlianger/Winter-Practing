package com.example.xia.flowerworld;

public class Color {
    private String name;
    private  int imageId;
    public Color(String name, int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
