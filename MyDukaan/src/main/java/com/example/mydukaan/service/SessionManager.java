package com.example.mydukaan.service;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

public class SessionManager extends Application {
    public  static String username = "";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;// shared pref mode
    int PRIVATE_MODE = 0;// Shared preferences file name
    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("Androidwarriors", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }
    public void setPreferences12(Context context, String key, Bitmap value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("Androidwarriors", Context.MODE_PRIVATE).edit();
        editor.putString(key, String.valueOf(value));
        editor.commit();

    }


    public String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("Androidwarriors",	Context.MODE_PRIVATE);
        username = prefs.getString(key, "");
        return username;
    }



}
