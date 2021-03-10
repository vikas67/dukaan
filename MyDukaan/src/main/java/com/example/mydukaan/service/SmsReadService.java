package com.example.mydukaan.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

public class SmsReadService extends BroadcastReceiver {

    public SmsBroadcastReceiverListener smsBroadcastReceiverListener;
    private static final String TAG = "SmsReadService";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "onReceive: " + intent.getAction());
        Log.e(TAG, "onReceive: " + intent.getAction());


        if (intent.getAction() == SmsRetriever.SMS_RETRIEVED_ACTION) {
            Bundle extras = intent.getExtras();
            Status smsRetrieverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
            switch (smsRetrieverStatus.getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    Intent messageIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                    smsBroadcastReceiverListener.onSuccess(messageIntent);
                    break;
                case CommonStatusCodes.TIMEOUT:
                    smsBroadcastReceiverListener.onFailure();
                    break;
            }
        }
    }

    public static interface SmsBroadcastReceiverListener {
        void onSuccess(Intent intent);

        void onFailure();
    }



}


































