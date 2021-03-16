package com.example.mydukaan.activity.ginnyActivity.welcome;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinneyMainActivity;
import com.example.mydukaan.activity.ginnyActivity.otp.OtpActivity;
import com.example.mydukaan.activity.ginnyActivity.signup.SignupActivity;
import com.example.mydukaan.activity.ginnyActivity.splash.SplashActivity;
import com.example.mydukaan.adapter.CountryAdapter;
import com.example.mydukaan.databinding.ActivityWelcomeBinding;
import com.example.mydukaan.ginnymodal.country.CountryItem;
import com.example.mydukaan.ginnymodal.signin.SignInGinney;
import com.example.mydukaan.other.LocalHelper;
import com.example.mydukaan.other.NetworkCheck;
import com.example.mydukaan.other.SessionManage;
import com.example.mydukaan.service.ApiGinnyClient;
import com.example.mydukaan.service.MyGinnyInterface;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {


    ActivityWelcomeBinding binding;
    WelcomeViewmodel welcomeViewmodel;
    MyGinnyInterface apiGinnyClient;
    private static final String TAG = "WelcomeActivity";
    public static final int RequestPermissionCode = 7;
    SessionManage sessionManage;
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    Context context;
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();



        sessionManage = new SessionManage(this);

        initList();

        mAdapter = new CountryAdapter(this, mCountryList);
        binding.spinner.setAdapter(mAdapter);

/*        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getCountryName();
                Toast.makeText(WelcomeActivity.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        RequestMultiplePermission();


//        if (sessionManage.getUserDetails().get("verify_user") != null) {
//            startActivity(new Intent(WelcomeActivity.this, GinneyMainActivity.class));
//            finish();
//        }



        apiGinnyClient = ApiGinnyClient.getClient().create(MyGinnyInterface.class);
        binding.SigupBtn.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
        });

        binding.SignInBtn.setOnClickListener(v -> {
            if (new NetworkCheck().haveNetworkConnection(this)) {
                if (validation()) SignIN();
            }
//            Toast.makeText(this, , Toast.LENGTH_SHORT).show();
        });


    }

    private void SignIN() {


        apiGinnyClient.SignINGinny(binding.mobileNumber.getEditText().getText().toString().trim(), binding.Password.getEditText().getText().toString().trim()).enqueue(new Callback<SignInGinney>() {
            @Override
            public void onResponse(Call<SignInGinney> call, Response<SignInGinney> response) {
                Log.e(TAG, "onResponse: " + new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (!response.body().getResult().getError() && response.body().getResult().getErrorCode() == 200) {
                        sessionManage.VerifyUser("true");
                        sessionManage.AccessToken(response.body().getResult().getAccessToken());
                        startActivity(new Intent(WelcomeActivity.this, GinneyMainActivity.class));
                        finish();
                    } else {

                    }
                } else {
                    Toast.makeText(WelcomeActivity.this, "Invalid Password! for  mobile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignInGinney> call, Throwable t) {

            }
        });


    }


    private boolean validation() {
        if (NumberCheck(binding.mobileNumber.getEditText().getText().toString().trim()) && PasswordCheck(binding.Password.getEditText().getText().toString().trim()))
            return true;
        return false;
    }

    private boolean NumberCheck(String number) {
        if (number.isEmpty()) {
            Toast.makeText(this, "number empty", Toast.LENGTH_SHORT).show();
            binding.mobileNumber.setError(getResources().getString(R.string.field_required));
            binding.mobileNumber.setErrorEnabled(true);
            return false;
        }
        binding.mobileNumber.setError(null);
        binding.mobileNumber.setErrorEnabled(false);
        return true;
    }

    private boolean PasswordCheck(String psw) {
        if (psw.isEmpty()) {
//            Toast.makeText(this, "pasw empty", Toast.LENGTH_SHORT).show();
            binding.Password.setError(getResources().getString(R.string.field_required));
            binding.Password.setErrorEnabled(true);
            return false;
        }
//        else if (psw.length() <= 6) {
////            Toast.makeText(this, "pasw 6 small", Toast.LENGTH_SHORT).show();
//            binding.Password.setError(getResources().getString(R.string.psw_short));
//            binding.Password.setErrorEnabled(true);
//            return false;
//        }
        binding.Password.setError(null);
        binding.Password.setErrorEnabled(false);
        return true;
    }


    @AfterPermissionGranted(RequestPermissionCode)
    private void RequestMultiplePermission() {

        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECEIVE_SMS};
        if (EasyPermissions.hasPermissions(this, perms)) {
//            Toast.makeText(this, "Opening camera", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(this, "We need permissions because this and that",
                    RequestPermissionCode, perms);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull @NotNull List<String> perms) {
        Log.e(TAG, "onPermissionsGranted: " + requestCode);
        Log.e(TAG, "onPermissionsGranted: " + perms);
        Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull @NotNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

        }
    }


    private void initList() {
        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryItem("India", R.drawable.india_flag));
        mCountryList.add(new CountryItem("China", R.drawable.india_flag));
    }


    private void setAppLocale(String localeCode) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }




}




























































