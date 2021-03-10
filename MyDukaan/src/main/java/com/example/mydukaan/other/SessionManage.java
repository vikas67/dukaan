package com.example.mydukaan.other;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.activity.Login;

import java.util.HashMap;

public class SessionManage {

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences cart;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "UserSessionPref";

    // First time logic Check
    public static final String FIRST_TIME = "firsttime";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // userid address (make variable public to access from outside)
    public static final String USERID = "userid";

    // Email address (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";

    // Email address (make variable public to access from outside)
    public static final String KEYID = "keyid";

    // Mobile number (make variable public to access from outside)
    public static final String KEY_MOBiLE = "mobile";

    // user avatar (make variable public to access from outside)
    public static final String KEY_PHOTO = "photo";

    // number of items in our cart
    public static final String KEY_CART = "cartvalue";

    // number of items in our wishlist
    public static final String KEY_WISHLIST = "wishlistvalue";

    // check first time app launch
    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";



    /*
     *
     *
     *   My ginney
     *
     */

    public static final String ID = "is";
    public static final String USERNAME = "name";
    public static final String EMAIL = "email";
    public static final String MOBILE = "mobile";
    public static final String OTP = "otp";
    public static final String PASSWORD_ORI = "psw_ori";
    public static final String USER_TYPE = "user_type";
    public static final String PASSWORD = "psw";
    public static final String UPDATED_AT = "updated_at";
    public static final String CREATED_AT = "created_at";

    public static final String VERIFY_USER = "verify_user";

    public static final String ACCESS_TOKEN = "AccessToken";


    // Constructor
    public SessionManage(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createprofile(String email, String password, String name, String mobile, String userid, String token) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);


        // Storing password in shared pref
        editor.putString(KEY_PASSWORD, password);

        //  Storing phone number in pref
        editor.putString(KEY_MOBiLE, mobile);

//         Storing image url in pref
        editor.putString(KEYID, token);

        //         Storing token in pref
        editor.putString(USERID, userid);


        // commit changes
        editor.commit();
    }


    /**
     * Create login session
     */
//    public void createLoginSession(String name, String email, String mobile, String photo){     modified due to photo not available
    public void createLoginSession(String phone, String password, String token, String name, String mobile) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
//        editor.putString(KEY_NAME, name);

        // Storing email in pref
//        editor.putString(KEY_EMAIL, email);


        // Storing password in shared pref
        editor.putString(KEY_PASSWORD, password);

        //  Storing phone number in pref
        editor.putString(KEY_MOBiLE, phone);

        // Storing image url in pref
        editor.putString(KEYID, token);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }

    }

    public Boolean Checkingcredential() {
        Boolean status = false;
        if (this.isLoggedIn()) {
            status = true;
        }
        return status;
    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // user phone number
        user.put(KEY_MOBiLE, pref.getString(KEY_MOBiLE, null));

        // user avatar
        user.put(KEYID, pref.getString(KEYID, null));
        user.put(USERID, pref.getString(USERID, null));

        user.put(String.valueOf(VERIFY_USER), pref.getString(String.valueOf(VERIFY_USER), null));

        user.put(ACCESS_TOKEN, pref.getString(ACCESS_TOKEN, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.putBoolean(IS_LOGIN, false);
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(context, DashboardActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    /*
     *
     * Ginny session
     *
     * */

    public void signupData(String id, String name, String email, String mobile, String otp, String password_ori, String user_type, String password, String updatedAt, String createdAt) {
        editor.putString(ID, id);
        editor.putString(USERNAME, name);
        editor.putString(EMAIL, email);
        editor.putString(MOBILE, mobile);
        editor.putString(otp, otp);
        editor.putString(PASSWORD_ORI, password_ori);
        editor.putString(USER_TYPE, user_type);
        editor.putString(PASSWORD, password);
        editor.putString(UPDATED_AT, updatedAt);
        editor.putString(CREATED_AT, createdAt);
        editor.commit();
    }

    public void VerifyUser(String b) {
        editor.putString(VERIFY_USER, b);
        editor.commit();
    }

    public void AccessToken(String token) {
        editor.putString(ACCESS_TOKEN, token);
        editor.commit();
    }


}




























