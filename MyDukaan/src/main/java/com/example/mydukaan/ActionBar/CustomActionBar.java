package com.example.mydukaan.ActionBar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydukaan.R;
import com.google.android.material.textview.MaterialTextView;

public class CustomActionBar {

    public   CustomActionBar(Activity activity, String s) {
        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowCustomEnabled(false);
        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(activity);
        View v = inflator.inflate(R.layout.actionbar_layout, null);
        ((TextView) v.findViewById(R.id.title)).setText(s);
        ((AppCompatActivity) activity).getSupportActionBar().setCustomView(v);
        ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) activity).getSupportActionBar().show();
    }
}
