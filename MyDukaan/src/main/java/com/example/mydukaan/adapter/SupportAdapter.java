package com.example.mydukaan.adapter;

import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.R;
import com.example.mydukaan.modal.chatdetails.Chatdetail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SupportAdapter extends RecyclerView.Adapter {
    private static final int TYPE_SENT_MASSAGE = 0;
    private static final int TYPE_RECIVE_MASSAGE = 1;
    private static final int TYPE_SENT_IMAGE = 2;
    private static final int TYPE_RECIVE_IMAGE = 3;
    LayoutInflater inflater;
    ArrayList<Chatdetail> chat_arraylist;

    Time today = new Time(Time.getCurrentTimezone());

    public SupportAdapter(LayoutInflater layoutInflater) {
        this.inflater = layoutInflater;

    }

    public class SendmassageHolder extends RecyclerView.ViewHolder {
        TextView massage, txt_send_massage_time;

        public SendmassageHolder(@NonNull View itemView) {
            super(itemView);
            massage = itemView.findViewById(R.id.txt_send_massage);
            txt_send_massage_time = itemView.findViewById(R.id.txt_send_massage_time);
        }

    }

    public class RecivemassageHolder extends RecyclerView.ViewHolder {
        TextView mass;
        TextView name, txt_recive_massage_time;

        public RecivemassageHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_recive_name);
            mass = itemView.findViewById(R.id.txt_recive_massage);
            txt_recive_massage_time = itemView.findViewById(R.id.txt_recive_massage_time);
        }

    }

    public class SendImageHolder extends RecyclerView.ViewHolder {
        ImageView mass;

        public SendImageHolder(@NonNull View itemView) {
            super(itemView);

            mass = itemView.findViewById(R.id.send_image);

        }

    }

    public class ReciveimageeHolder extends RecyclerView.ViewHolder {
        ImageView mass;
        TextView name;

        public ReciveimageeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_recive_name);
            mass = itemView.findViewById(R.id.txt_recive_image);

        }

    }

    @Override
    public int getItemViewType(int position) {

        final Chatdetail ma = chat_arraylist.get(position);
        try {
            if (ma.getSide().equalsIgnoreCase("USER")) {
                return TYPE_SENT_MASSAGE;
            } else {
                return TYPE_RECIVE_MASSAGE;
            }

        } catch (UnknownError e) {
            e.printStackTrace();
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case TYPE_SENT_MASSAGE:
                v = inflater.inflate(R.layout.send_massage, parent, false);
                return new SendmassageHolder(v);
            case TYPE_RECIVE_MASSAGE:
                v = inflater.inflate(R.layout.recive_massage, parent, false);
                return new RecivemassageHolder(v);
            case TYPE_SENT_IMAGE:
                v = inflater.inflate(R.layout.send_image, parent, false);
                return new SendImageHolder(v);
            case TYPE_RECIVE_IMAGE:
                v = inflater.inflate(R.layout.recive_image, parent, false);
                return new ReciveimageeHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Chatdetail mas = chat_arraylist.get(position);
        today.setToNow();
        DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());

        if (mas.getSide().equalsIgnoreCase("USER")) {

            SendmassageHolder sendmassageHolder = (SendmassageHolder) holder;
            sendmassageHolder.massage.setText(mas.getMsg());
            //  sendmassageHolder.txt_send_massage_time.setText(date);
            convertToCustomFormat(mas.getTime(), sendmassageHolder.txt_send_massage_time);


        } else {

            RecivemassageHolder recivemassage = (RecivemassageHolder) holder;
            // recivemassage.name.setText(mas.getUserName());
            recivemassage.mass.setText(mas.getMsg());
            // recivemassage.txt_recive_massage_time.setText(date);
            convertToCustomFormat(mas.getTime(), recivemassage.txt_recive_massage_time);


        }


    }

    @Override
    public int getItemCount() {
        return chat_arraylist.size();
    }

    public void addItem(ArrayList<Chatdetail> jsonObject) {
        this.chat_arraylist = jsonObject;
        notifyDataSetChanged();
    }

    public void clearlist() {
        chat_arraylist.clear();
    }

    private void convertToCustomFormat(String date, TextView txt_send_massage_time) {

        String finaldate = null;
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date input = inputFormat.parse(date);
            DateFormat outputFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
            finaldate = outputFormat.format(input);
            txt_send_massage_time.setText(finaldate);

        } catch (Exception ex) {

        }

    }
}

