package com.example.mydukaan.ginnymodal.mobilebrand;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class MobileBrand {
    String img;
    String name;

    public MobileBrand(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }


    @BindingAdapter("android:productImage")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);
    }

}
