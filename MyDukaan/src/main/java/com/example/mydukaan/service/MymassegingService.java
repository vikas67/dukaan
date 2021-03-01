package com.example.mydukaan.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.Home;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MymassegingService extends FirebaseMessagingService {
    Intent intent;
    public static String str_receiver = "com.example.chatmaster.receiver";
    String data;
    @SuppressLint({"CheckResult", "WrongThread"})
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("message_from_server", remoteMessage.toString());

        if (remoteMessage.getData().size() > 0) {
            Log.d("message_from_server", "Message data payload: " + remoteMessage.getData());

            try {
             data = remoteMessage.getData().get("data");

                intent = new Intent(str_receiver);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    final String image = jsonObject.getString("image");
                    final String title = jsonObject.getString("title");
                    final String message = jsonObject.getString("message");
                    final String agent_id = jsonObject.getString("agent_id");
                    final String chatlist_id = jsonObject.getString("chatlist_id");
                    final String msg = jsonObject.getString("msg");
                    final String side = jsonObject.getString("side");
                    final String time = jsonObject.getString("time");
                    final String user_id = jsonObject.getString("user_id");
                    final String user_name = jsonObject.getString("user_name");
                    final String order_no = jsonObject.getString("order_no");
                    final String api_type = jsonObject.getString("api_type");
                    final String order_id = jsonObject.getString("order_id");


                        Log.d("send boardcast", message);

                    intent.putExtra("agent_id",agent_id);
                    intent.putExtra("chatlist_id",chatlist_id);
                    intent.putExtra("msg",msg);
                    intent.putExtra("side",side);
                    intent.putExtra("time",time);
                    intent.putExtra("order_no",order_no);
                    intent.putExtra("order_id",order_id);
                    intent.putExtra("user_id",user_id);
                    intent.putExtra("user_name",user_name);
                    intent.putExtra("api_type",api_type);
                    sendBroadcast(intent);


                  //  new LongOperation2(getApplicationContext(),message, title).execute();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }catch (NullPointerException ex){


            }





        }

    }

    public class LongOperation2 extends AsyncTask<String, Void, String> {

        Context context;
        String  msg, title;


        private LongOperation2(Context context, String msg, String title) {
            this.context = context;

            this.msg = msg;
            this.title = title;

        }

        @Override
        protected String doInBackground(String... params) {


            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            showNotification(title, msg);

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }



    //
    public void showNotification(String titile, String massage) {

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent notificationIntent = new Intent(getApplicationContext(), Home.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0,
                notificationIntent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // CharSequence name = context.getString(R.string.app_name);
            //String description = getString(R.string.channel_description);

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Mynotification", "Mynotification", importance);
            channel.enableLights(true);
            channel.setDescription(massage);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);


            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            channel.enableVibration(true);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }



            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Mynotification")
                    .setContentTitle(titile)
                    .setContentText(massage)
                    .setSmallIcon(R.drawable.yall)
                    .setSound(alarmSound)
                    .setContentIntent(intent)
                    .setColor(ContextCompat.getColor(this, R.color.textcolor))
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                    .setAutoCancel(false);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(10, builder.build());

        }



//
}

