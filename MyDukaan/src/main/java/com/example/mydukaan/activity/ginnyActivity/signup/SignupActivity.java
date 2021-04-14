package com.example.mydukaan.activity.ginnyActivity.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.ginnyActivity.otp.OtpActivity;
import com.example.mydukaan.databinding.ActivitySignupBinding;
import com.example.mydukaan.ginnymodal.signup.Data;
import com.example.mydukaan.ginnymodal.signup.SignupGinney;
import com.example.mydukaan.other.SessionManage;
import com.example.mydukaan.service.ApiGinnyClient;
import com.example.mydukaan.service.MyGinnyInterface;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {


    ActivitySignupBinding binding;
    MyGinnyInterface apiGinnyClient;
    String UserType = null;
    private static final String TAG = "SignupActivity";
    SessionManage sessionManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        apiGinnyClient = ApiGinnyClient.getClient().create(MyGinnyInterface.class);
        sessionManage = new SessionManage(this);

        binding.retails.setOnClickListener(v -> {
            binding.wholeseller.setChecked(false);
            UserType = "RETAILER";
        });
        binding.wholeseller.setOnClickListener(v -> {
            binding.retails.setChecked(false);
            UserType = "WHOLESELLER";
        });
        PrivacyPolicy();

        binding.SignupBtn.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, OtpActivity.class));
//            if (new NetworkCheck().haveNetworkConnection(this)) {
//                if (validatiopn()) {
//                    Signup();
//                }
//            } else {
//                Toast.makeText(SignupActivity.this, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
//            }
        });

    }

    private void Signup() {
        apiGinnyClient.SignUpGinny(binding.firstName.getEditText().getText().toString().trim(), binding.email.getEditText().getText().toString().trim(), binding.number.getEditText().getText().toString().trim(), binding.password.getEditText().getText().toString().trim(), UserType).enqueue(new Callback<SignupGinney>() {
            @Override
            public void onResponse(Call<SignupGinney> call, Response<SignupGinney> response) {
                Log.e(TAG, "onResponse: " + new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (!response.body().getResult().getError() && response.body().getResult().getErrorCode() == 200) {
                        Data list = response.body().getResult().getData();
                        sessionManage.signupData(String.valueOf(list.getId()), list.getName(), list.getEmail(), list.getMobile(), String.valueOf(list.getOtp()), "", list.getUserType(), "", "", "");
                        Toast.makeText(SignupActivity.this, response.body().getResult().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, OtpActivity.class).putExtra("otp", list.getOtp()).putExtra("user_id", String.valueOf(list.getId())).putExtra("number", String.valueOf(list.getMobile())));
                    } else {
                        Toast.makeText(SignupActivity.this, response.body().getResult().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<SignupGinney> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private boolean validatiopn() {
        if (
                FirstNameCheck(
                        binding.firstName.getEditText().getText().toString().trim()) &&
                        NumberCheck(binding.number.getEditText().getText().toString().trim()) &&
                        emailcheck(binding.email.getEditText().getText().toString().trim()) &&
                        PasswordCheck(binding.password.getEditText().getText().toString().trim()) &&
                        UserType()
        ) {
            return true;
        }
        return false;
    }

    private boolean FirstNameCheck(String firstname) {
        if (firstname.isEmpty()) {
//            Toast.makeText(this, "Field cant't empty", Toast.LENGTH_SHORT).show();
            binding.firstName.setError(getResources().getString(R.string.field_required));
            binding.firstName.setErrorEnabled(true);
            return false;
        }
        binding.firstName.setError(null);
        binding.firstName.setErrorEnabled(false);
        return true;
    }

    private boolean lastNameCheck(String lastname) {
        if (lastname.isEmpty()) {
            Toast.makeText(this, "Field not empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean NumberCheck(String number) {
        if (number.isEmpty()) {
//            Toast.makeText(this, "number empty", Toast.LENGTH_SHORT).show();
            binding.number.setError(getResources().getString(R.string.field_required));
            binding.number.setErrorEnabled(true);
            return false;
        }
        binding.number.setError(null);
        binding.number.setErrorEnabled(false);
        return true;
    }

    private boolean emailcheck(String email) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()) {
//            Toast.makeText(this, "Informate email", Toast.LENGTH_SHORT).show();
            binding.email.setError(getResources().getString(R.string.field_required));
            binding.email.setErrorEnabled(true);
            return false;
        } else if (!email.matches(emailPattern)) {
//            Toast.makeText(this, "field cant't empty", Toast.LENGTH_SHORT).show();
            binding.email.setError(getResources().getString(R.string.email_formate));
            binding.email.setErrorEnabled(true);
            return false;
        }
        binding.email.setError(null);
        binding.email.setErrorEnabled(false);
        return true;
    }


    private boolean PasswordCheck(String psw) {
        if (psw.isEmpty()) {
//            Toast.makeText(this, "pasw empty", Toast.LENGTH_SHORT).show();
            binding.password.setError(getResources().getString(R.string.field_required));
            binding.password.setErrorEnabled(true);
            return false;
        } else if (psw.length() <= 6) {
//            Toast.makeText(this, "pasw 6 small", Toast.LENGTH_SHORT).show();
            binding.password.setError(getResources().getString(R.string.psw_short));
            binding.password.setErrorEnabled(true);
            return false;
        }
        binding.password.setError(null);
        binding.password.setErrorEnabled(false);
        return true;
    }

    private boolean UserType() {
        if (UserType == null) {
            Toast.makeText(this, "Pleace select user type", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void PrivacyPolicy() {
        SpannableString ss = new SpannableString(binding.privacyPolicy.getText().toString().trim());

        ClickableSpan terms_service = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(SignupActivity.this, "One", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.purple_500));
                ds.setUnderlineText(true);

            }
        };
        ClickableSpan privacyPolicy = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(SignupActivity.this, "Two", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.purple_500));
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(terms_service, 49, 65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(privacyPolicy, 70, 84, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.privacyPolicy.setText(ss);
        binding.privacyPolicy.setMovementMethod(LinkMovementMethod.getInstance());
    }


}


























