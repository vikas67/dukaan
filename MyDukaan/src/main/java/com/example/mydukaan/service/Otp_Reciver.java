package com.example.mydukaan.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.ginnymodal.verifyotp.Data;
import com.example.mydukaan.ginnymodal.verifyotp.VerifyOtp;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Otp_Reciver extends BroadcastReceiver {

    private static final String TAG = "Otp_Reciver";
    String message;
    String otp;
    private static EditText editText1, editText2, editText3, editText4;
    private static String userid;
    boolean numeric = true;

    public void setEdittext(EditText edittext1, EditText editText2, EditText editText3, EditText editText4, String userid) {
        Otp_Reciver.editText1 = edittext1;
        Otp_Reciver.editText2 = editText2;
        Otp_Reciver.editText3 = editText3;
        Otp_Reciver.editText4 = editText4;
        Otp_Reciver.userid = userid;

    }


    @Override
    public void onReceive(Context context, Intent intent) {

        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage sms : messages) {
            message = sms.getMessageBody();
        }
        otp = message.split(" ")[0];
        autoFillOtp(otp, context);

    }

    public void autoFillOtp(String sms, Context context) {


        try {
            Double num = Double.parseDouble(sms);
        } catch (Exception e) {
            numeric = false;
            Log.e(TAG, "autoFillOtp: " + e.getMessage());
        }

        if (sms.length() == 4) numeric = true;
        else numeric = false;

        if (numeric) {
            String mag = sms;
            for (int i = 0; i < mag.length(); i++) {
                int j = Character.digit(mag.charAt(i), 10);
                Log.e(TAG, "autoFillOtp: " + String.valueOf(j));
                if (i == 0) {
                    editText1.setText(String.valueOf(j));
                } else if (i == 1) {
                    editText2.setText(String.valueOf(j));
                } else if (i == 2) {
                    editText3.setText(String.valueOf(j));
                } else if (i == 3) {
                    editText4.setText(String.valueOf(j));
                }
            }


            String inputone = editText1.getText().toString();
            String inputtwo = editText2.getText().toString();
            String inputthree = editText3.getText().toString();
            String inputfour = editText4.getText().toString();

            String otp = inputone + inputtwo + inputthree + inputfour;

//            new OtpActivity().VerifyOtp(otp);
            VerifyOtp(otp, context);

        }


    }


    public void VerifyOtp(String otp, Context context) {
        MyGinnyInterface apiGinnyClient = ApiGinnyClient.getClient().create(MyGinnyInterface.class);
        apiGinnyClient.verifyOtp(userid, otp).enqueue(new Callback<VerifyOtp>() {
            @Override
            public void onResponse(Call<VerifyOtp> call, Response<VerifyOtp> response) {
                Log.e(TAG, "onResponse: " + new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (!response.body().getResult().getError() && response.body().getResult().getErrorCode() == 200) {
                        Data list = response.body().getResult().getData();
//                        sessionManage.VerifyUser("true");
                        Toast.makeText(context, response.body().getResult().getMessage(), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, DashboardActivity.class));
                    } else {
                        Toast.makeText(context, response.body().getResult().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "User And Otp Not Match", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyOtp> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}
