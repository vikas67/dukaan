package com.example.mydukaan.activity.ginnyActivity.ginny_main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mydukaan.R;

public class GinnyHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ginny_home);
    }
}