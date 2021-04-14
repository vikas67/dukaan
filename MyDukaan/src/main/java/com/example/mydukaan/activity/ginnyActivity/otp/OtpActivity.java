package com.example.mydukaan.activity.ginnyActivity.otp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.activity.ginnyActivity.document_upload.DocumentUploadActivity;
import com.example.mydukaan.databinding.ActivityOtpBinding;
import com.example.mydukaan.ginnymodal.verifyotp.Data;
import com.example.mydukaan.ginnymodal.verifyotp.VerifyOtp;
import com.example.mydukaan.other.NetworkCheck;
import com.example.mydukaan.other.SessionManage;
import com.example.mydukaan.service.ApiGinnyClient;
import com.example.mydukaan.service.MyGinnyInterface;
import com.example.mydukaan.service.Otp_Reciver;
import com.example.mydukaan.service.SmsReadService;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements TextWatcher {

    public int counter;
    ActivityOtpBinding binding;
    private static final String TAG = "OtpActivity";
    SmsReadService smsBroadcastReceiver;
    private static final int REQ_USER_CONSENT = 200;
    MyGinnyInterface apiGinnyClient;
    String user_id, otp, number;
    SessionManage sessionManage;
    boolean numeric = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        apiGinnyClient = ApiGinnyClient.getClient().create(MyGinnyInterface.class);
        sessionManage = new SessionManage(this);

        user_id = getIntent().getStringExtra("user_id");
        otp = getIntent().getStringExtra("otp");
        number = getIntent().getStringExtra("number");
        binding.numbers.setText(number);


        conuter();
        binding.resendotp.setEnabled(false);
        binding.resendotp.setClickable(false);

        binding.resendotp.setOnClickListener(v -> {
            binding.resendotp.setEnabled(false);
            binding.resendotp.setClickable(false);
            binding.textCountdown.setVisibility(View.VISIBLE);
            resendotp();
        });

        binding.button2.setOnClickListener(v -> {
            startActivity(new Intent(this, DocumentUploadActivity.class));

/*            if (new NetworkCheck().haveNetworkConnection(this)) {

                String inputone = binding.otpEditTextOne.getText().toString();
                String inputtwo = binding.otpEditTextTwo.getText().toString();
                String inputthree = binding.otpEditTextThree.getText().toString();
                String inputfour = binding.otpEditTextFour.getText().toString();

                String otp = inputone + inputtwo + inputthree + inputfour;

                if (validation(otp)) {
                    binding.resendotp.setEnabled(false);
                    binding.resendotp.setClickable(false);
                    VerifyOtp(otp);
                }
            } else {
                Toast.makeText(OtpActivity.this, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
            }*/
        });

        ActivityAction();

        new Otp_Reciver().setEdittext(binding.otpEditTextOne, binding.otpEditTextTwo, binding.otpEditTextThree, binding.otpEditTextFour , user_id);


    }

    private void ActivityAction() {
        binding.otpEditTextOne.addTextChangedListener(this);
        binding.otpEditTextTwo.addTextChangedListener(this);
        binding.otpEditTextThree.addTextChangedListener(this);
        binding.otpEditTextFour.addTextChangedListener(this);
    }


    private void conuter() {
        new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long l) {
                //txt_counter.setText(String.valueOf(counter));
                binding.textCountdown.setText(String.valueOf(l / 1000));
                counter++;
                //Toast.makeText(CheckOtp.this, "Please Enter 4 digit OtP", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {

                binding.textCountdown.setVisibility(View.GONE);
                binding.resendotp.setEnabled(true);
                binding.resendotp.setClickable(true);

            }
        }.start();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        Log.e(TAG, "afterTextChanged: " + s.length());

        if (s.length() == 1) {
            if (binding.otpEditTextOne.length() == 1) {
                binding.otpEditTextTwo.requestFocus();
            }

            if (binding.otpEditTextTwo.length() == 1) {
                binding.otpEditTextThree.requestFocus();
            }
            if (binding.otpEditTextThree.length() == 1) {
                binding.otpEditTextFour.requestFocus();
            }
        } else {
            if (binding.otpEditTextFour.length() == 0) {
                binding.otpEditTextThree.requestFocus();
            }
            if (binding.otpEditTextThree.length() == 0) {
                binding.otpEditTextTwo.requestFocus();
            }
            if (binding.otpEditTextTwo.length() == 0) {
                binding.otpEditTextOne.requestFocus();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        /* Unregister service to recive new sms in app */
        unregisterReceiver(smsBroadcastReceiver);
    }

    public void registerBroadcastReceiver() {

        smsBroadcastReceiver = new SmsReadService();
        smsBroadcastReceiver.smsBroadcastReceiverListener =
                new SmsReadService.SmsBroadcastReceiverListener() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, REQ_USER_CONSENT);
                    }

                    @Override
                    public void onFailure() {

                    }
                };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver, intentFilter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_USER_CONSENT) {
            if ((resultCode == RESULT_OK) && (data != null)) {
                //That gives all message to us.
                // We need to get the code from inside with regex
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                //   Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                String mag = message.replaceAll("\\D+", "");

                Log.e(TAG, "onActivityResult: " + mag);


                for (int i = 0; i < mag.length(); i++) {
                    int j = Character.digit(mag.charAt(i), 10);
                    if (i == 0) {
                        binding.otpEditTextOne.setText(String.valueOf(j));
                    } else if (i == 1) {
                        binding.otpEditTextTwo.setText(String.valueOf(j));
                    } else if (i == 2) {
                        binding.otpEditTextThree.setText(String.valueOf(j));
                    } else if (i == 3) {
                        binding.otpEditTextFour.setText(String.valueOf(j));
                    }
                }

                String inputone = binding.otpEditTextOne.getText().toString();
                String inputtwo = binding.otpEditTextTwo.getText().toString();
                String inputthree = binding.otpEditTextThree.getText().toString();
                String inputfour = binding.otpEditTextFour.getText().toString();

                String otp = inputone + inputtwo + inputthree + inputfour;
//                Toast.makeText(OtpActivity.this, number, Toast.LENGTH_SHORT).show();
                if (otp.length() == 4) {
                    if (new NetworkCheck().haveNetworkConnection(this)) {
                        VerifyOtp(otp);
                    } else {
                        Toast.makeText(OtpActivity.this, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(OtpActivity.this, "Please Enter 4 digit OtP", Toast.LENGTH_SHORT).show();
                }
            }


        }
    }


    public void VerifyOtp(String otp) {
        apiGinnyClient.verifyOtp(user_id, otp).enqueue(new Callback<VerifyOtp>() {
            @Override
            public void onResponse(Call<VerifyOtp> call, Response<VerifyOtp> response) {
                Log.e(TAG, "onResponse: " + new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (!response.body().getResult().getError() && response.body().getResult().getErrorCode() == 200) {
                        Data list = response.body().getResult().getData();
                        sessionManage.VerifyUser("true");
                        Toast.makeText(OtpActivity.this, response.body().getResult().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OtpActivity.this, DashboardActivity.class));
                    } else {
                        Toast.makeText(OtpActivity.this, response.body().getResult().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OtpActivity.this, "User And Otp Not Match", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyOtp> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    private void resendotp() {
        conuter();
        Toast.makeText(this, "resend otp", Toast.LENGTH_SHORT).show();
    }


    private boolean validation(String otp) {
        if (otpverify(otp)) return true;
        return false;
    }

    private boolean otpverify(String otp) {
        if (otp.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.field_required), Toast.LENGTH_SHORT).show();
            return false;
        } else if (otp.length() == 3) {
            Toast.makeText(OtpActivity.this, getResources().getString(R.string.otp_four_digit), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void autoFillOtp(String sms) {

        binding.otpEditTextOne.setText("1");


        try {
            Double num = Double.parseDouble(sms);
        } catch (Exception e) {
            numeric = false;
            Log.e(TAG, "autoFillOtp: " + e.getMessage());
        }

/*
        if (numeric) {
            String mag = sms;
            for (int i = 0; i < mag.length(); i++) {
                int j = Character.digit(mag.charAt(i), 10);
                Log.e(TAG, "autoFillOtp: " + String.valueOf(j));
                if (i == 0) {
                    binding.otpEditTextOne.setText(String.valueOf(j));
                } else if (i == 1) {
                    binding.otpEditTextTwo.setText(String.valueOf(j));
                } else if (i == 2) {
                    binding.otpEditTextThree.setText(String.valueOf(j));
                } else if (i == 3) {
                    binding.otpEditTextFour.setText(String.valueOf(j));
                }
            }

            String inputone = binding.otpEditTextOne.getText().toString();
            String inputtwo = binding.otpEditTextTwo.getText().toString();
            String inputthree = binding.otpEditTextThree.getText().toString();
            String inputfour = binding.otpEditTextFour.getText().toString();

            String otp = inputone + inputtwo + inputthree + inputfour;
//                Toast.makeText(OtpActivity.this, number, Toast.LENGTH_SHORT).show();
            if (otp.length() == 4) {
                if (new NetworkCheck().haveNetworkConnection(this)) {
                    VerifyOtp(otp);
                } else {
                    Toast.makeText(OtpActivity.this, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(OtpActivity.this, "Please Enter 4 digit OtP", Toast.LENGTH_SHORT).show();
            }
        }*/


    }


}
































