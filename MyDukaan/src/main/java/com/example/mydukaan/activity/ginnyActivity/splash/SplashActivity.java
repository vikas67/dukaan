package com.example.mydukaan.activity.ginnyActivity.splash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.activity.ginnyActivity.welcome.WelcomeActivity;
import com.example.mydukaan.other.LocalHelper;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        context = LocalHelper.setLocale(SplashActivity.this, "ar");
//        resources = context.getResources();
//        updateViews("ar");

            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
//            startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
//            finish();



    }


    private void updateViews(String languageCode) {
        String languageToLoad = languageCode;
        Locale myLocale = new Locale(languageToLoad);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLayoutDirection(new Locale(languageToLoad));
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

 /*       Configuration configuration = getResources().getConfiguration();
        configuration.setLayoutDirection(new Locale(languageCode));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());*/

        Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}