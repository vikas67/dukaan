package com.example.mydukaan.ginnymodal.category;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class CategoryList {
    String name;
    String img;

    public CategoryList(String name, String img) {
        this.name = name;
        this.img = img;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @BindingAdapter("android:productImage")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);
    }

}





































