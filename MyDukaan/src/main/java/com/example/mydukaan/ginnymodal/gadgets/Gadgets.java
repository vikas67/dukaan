package com.example.mydukaan.ginnymodal.gadgets;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Gadgets {
    String img;

    public String getImg() {
        return img;
    }

    public Gadgets(String img) {
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
