package com.example.mydukaan.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.R;
import com.example.mydukaan.fragment.DashBoard;
import com.example.mydukaan.fragment.RoomChat;
import com.example.mydukaan.modal.ChatStart.ChatlistExample;
import com.example.mydukaan.modal.ChatStart.UserChat;
import com.example.mydukaan.service.MymassegingService;
import com.example.mydukaan.service.RecyclerInterface;
import com.example.mydukaan.service.SessionManager;
import com.example.mydukaan.service.ApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class Home extends AppCompatActivity {

    RecyclerView rv_home;
    HomeAdapter adpter;
    RecyclerInterface apiInterface;
    public static ArrayList<UserChat> chat_arrayList;
    TextView txt_empty;
    public static String Active_user_id = null;
    public static int row = 0;
    public static String total_cases = "0", active_cases = "0", cases_left = "0", cases_closed = "0";
    LinearLayout ln_deshboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        rv_home = (RecyclerView) findViewById(R.id.rv_home);
        chat_arrayList = new ArrayList<>();
        txt_empty = findViewById(R.id.txt_empty);
        ln_deshboard = findViewById(R.id.ln_deshboard);
        apiInterface = ApiClient.getClient().create(RecyclerInterface.class);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_home.setLayoutManager(llm);

        adpter = new HomeAdapter(Home.this, chat_arrayList);
        rv_home.setAdapter(adpter);

        fecthagent();

        ln_deshboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                DashBoard home = new DashBoard();
                transaction.replace(R.id.main_content, home, "fragment");
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


    }

    private void fecthagent() {
        Call<ChatlistExample> call = apiInterface.chatlist("1");
        call.enqueue(new Callback<ChatlistExample>() {
            @Override
            public void onResponse(Call<ChatlistExample> call, retrofit2.Response<ChatlistExample> response) {
                Log.e("TAG", "chat_list : " + new Gson().toJson(response.body()));

                if (response.isSuccessful()) {
                    if (response.body().getUserChat() != null) {
                        if (response.body().getUserChat().size() > 0) {
                            for (int i = 0; i < response.body().getUserChat().size(); i++) {
                                UserChat v = new UserChat();
                                v.setAgentId(response.body().getUserChat().get(i).getAgentId());
                                v.setAgentName(response.body().getUserChat().get(i).getAgentName());
                                v.setUserId(response.body().getUserChat().get(i).getUserId());
                                v.setUserName(response.body().getUserChat().get(i).getUserName());
                                v.setOrderId(response.body().getUserChat().get(i).getOrderId());
                                v.setOrder_no(response.body().getUserChat().get(i).getOrder_no());
                                v.setId(response.body().getUserChat().get(i).getId());
                                v.setStartTime(response.body().getUserChat().get(i).getStartTime());
                                v.setCount_bedage("0");
                                chat_arrayList.add(v);
                            }
                            adpter = new HomeAdapter(Home.this, chat_arrayList);
                            rv_home.setAdapter(adpter);


                            UserChat c = chat_arrayList.get(0);
                            row = 0;
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            RoomChat home = new RoomChat(c.getId(), c.getUserName(), c.getAgentId(), c.getUserId(), c.getAgentName(), c.getOrderId(), c.getOrder_no(), 0, adpter, rv_home, chat_arrayList);

                            transaction.replace(R.id.main_content, home, "fragment");
                            transaction.addToBackStack(null);
                            transaction.commit();
                            txt_empty.setVisibility(View.GONE);
                        } else {
//                            FragmentManager manager =getSupportFragmentManager();
//                            FragmentTransaction transaction =manager.beginTransaction();
//                            RoomChat home=new RoomChat();
//                            transaction.replace(R.id.main_content,home,"fragment");
//                            transaction.addToBackStack(null);
//                            transaction.commit();


                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            DashBoard home = new DashBoard();
                            transaction.replace(R.id.main_content, home, "fragment");
                            transaction.addToBackStack(null);
                            transaction.commit();

                            Toast.makeText(Home.this, "chat list empty! ", Toast.LENGTH_SHORT).show();
                            txt_empty.setVisibility(View.VISIBLE);
                        }


                    }

                } else {
                    Toast.makeText(Home.this, getResources().getText(R.string.Something_worg_from_server), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ChatlistExample> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                // Log error here since request failed
            }
        });

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String sms = intent.getStringExtra("msg");
            String agent_id = intent.getStringExtra("agent_id");
            String chatlist_id = intent.getStringExtra("chatlist_id");
            String side = intent.getStringExtra("side");
            String order_no = intent.getStringExtra("order_no");
            String order_id = intent.getStringExtra("order_id");
            String time = intent.getStringExtra("time");
            String user_name = intent.getStringExtra("user_name");
            String user_id = intent.getStringExtra("user_id");
            String api_type = intent.getStringExtra("api_type");
            boolean check = false;


            if (api_type.equalsIgnoreCase("SEND")) {
                for (int i = 0; i < chat_arrayList.size(); i++) {
                    UserChat v = chat_arrayList.get(i);
                    if (!Active_user_id.equalsIgnoreCase(user_id)) {
                        if (v.getUserId().equalsIgnoreCase(user_id)) {
                            Integer count = Integer.parseInt(v.getCount_bedage());
                            count++;
                            UserChat object = new UserChat();
                            object.setAgentId(v.getAgentId());
                            object.setAgentName(v.getAgentName());
                            object.setUserId(v.getUserId());
                            object.setUserName(v.getUserName());
                            object.setStartTime(time);
                            object.setOrder_no(v.getOrder_no());
                            object.setOrderId(v.getOrderId());
                            object.setCount_bedage(String.valueOf(count));
                            object.setId(chatlist_id);
                            chat_arrayList.remove(i);
                            chat_arrayList.add(i, object);
                            adpter.addItem(chat_arrayList);

                        }
                    }
                }
                Gson gson = new GsonBuilder().create();
                JsonArray myCustomArray = gson.toJsonTree(chat_arrayList).getAsJsonArray();

                //////////////////////////////////////////make static Array object  /////////////////////////////////
                Log.e("chat_arrayList new ", "buy " + myCustomArray.toString());
            } else {
                for (int i = 0; i < chat_arrayList.size(); i++) {
                    UserChat v = chat_arrayList.get(i);
                    if (v.getUserId().equalsIgnoreCase(user_id)) {
                        check = true;
                    }
                }

                if (!check) {

                    UserChat object = new UserChat();
                    object.setAgentId(agent_id);
                    object.setAgentName("agent");
                    object.setUserId(user_id);
                    object.setStartTime(time);
                    object.setCount_bedage("1");
                    object.setOrder_no(order_no);
                    object.setOrderId(order_id);
                    object.setUserName(user_name);
                    object.setId(chatlist_id);
                    chat_arrayList.add(object);
                    if (chat_arrayList.size() <= 0) {
                        adpter = new HomeAdapter(Home.this, chat_arrayList);
                        rv_home.setAdapter(adpter);

                        UserChat c = chat_arrayList.get(0);
                        Active_user_id = c.getUserId();
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        RoomChat home = new RoomChat(c.getId(), c.getUserName(), c.getAgentId(), c.getUserId(), c.getAgentName(), c.getOrderId(), c.getOrder_no(), 0, adpter, rv_home, chat_arrayList);
                        transaction.replace(R.id.main_content, home, "fragment");
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } else {

                        adpter.addItem(chat_arrayList);

                    }
                    Gson gson = new GsonBuilder().create();
                    JsonArray myCustomArray = gson.toJsonTree(chat_arrayList).getAsJsonArray();

                    //////////////////////////////////////////make static Array object  /////////////////////////////////
                    Log.e("chat_arrayList new add", "buy " + myCustomArray.toString());

                }


            }
            txt_empty.setVisibility(View.GONE);
        }

    };

    public static void Updatelist(String active_user_id) {
        Log.e("Tag active : ", active_user_id);
        ArrayList<UserChat> arrayList = new ArrayList<>();

    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(MymassegingService.str_receiver));


    }

    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Myholder> {
        SessionManager sessionManager;
        Home homes;
        List<UserChat> myCustomArray;

        public HomeAdapter(Home home, List<UserChat> myCustomArray) {
            this.homes = home;
            this.myCustomArray = myCustomArray;
        }


        @NonNull
        @Override
        public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_new_user_second, parent, false);
            sessionManager = new SessionManager();

            return new Myholder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull final Myholder holder, final int position) {
            final UserChat c = myCustomArray.get(position);


            holder.txt_id.setText("ID : " + c.getUserId());
            holder.txt_name.setText(c.getUserName());

            convertToCustomFormat(c.getStartTime(), holder.txt_send_massage_time);

            if (c.getCount_bedage().equalsIgnoreCase("0")) {
                holder.txt_badge_count.setVisibility(View.INVISIBLE);
            } else {
                holder.txt_badge_count.setVisibility(View.VISIBLE);
                holder.txt_badge_count.setText(c.getCount_bedage());
            }


            if (position == row) {

                holder.cons_line.setBackgroundResource(R.color.selected_row_color);

            } else {
                holder.cons_line.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }


            holder.cons_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //holder.txt_badge_count.setVisibility(View.GONE);


                    Log.e("TAG", "onClick: " + c);
                    Log.e("TAG", "onClick: " + c);

                    row = position;
                    Active_user_id = c.getUserId();
                    c.setCount_bedage("0");
                    holder.txt_badge_count.setVisibility(View.INVISIBLE);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    RoomChat home = new RoomChat(c.getId(), c.getUserName(), c.getAgentId(), c.getUserId(), c.getAgentName(), c.getOrderId(), c.getOrder_no(), 0, adpter, rv_home, chat_arrayList);

                    transaction.replace(R.id.main_content, home, "fragment");
                    transaction.addToBackStack(null);
                    transaction.commit();
                    notifyDataSetChanged();


                }
            });


        }

        @Override
        public int getItemCount() {
            return myCustomArray.size();
        }

        public class Myholder extends RecyclerView.ViewHolder {
            ConstraintLayout cons_line;
            TextView txt_id, txt_name, txt_badge_count, txt_send_massage_time;

            public Myholder(@NonNull View itemView) {
                super(itemView);
                txt_id = (TextView) itemView.findViewById(R.id.txt_id);
                txt_name = (TextView) itemView.findViewById(R.id.txt_name);
                txt_badge_count = (TextView) itemView.findViewById(R.id.txt_badge_count);
                txt_send_massage_time = (TextView) itemView.findViewById(R.id.txt_send_massage_time);
                cons_line = (ConstraintLayout) itemView.findViewById(R.id.cons_line);
            }
        }


        public void addItem(List<UserChat> myCustomArray) {

            this.myCustomArray = myCustomArray;
            notifyDataSetChanged();
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



}