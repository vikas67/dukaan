package com.example.mydukaan.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mydukaan.R;
import com.example.mydukaan.activity.Home;
import com.example.mydukaan.adapter.SupportAdapter;
import com.example.mydukaan.modal.ChatStart.UserChat;
import com.example.mydukaan.modal.SentSms.SendSms;
import com.example.mydukaan.modal.chatdetails.ChatDetailsExample;
import com.example.mydukaan.modal.chatdetails.Chatdetail;
import com.example.mydukaan.service.ApiClient;
import com.example.mydukaan.service.MymassegingService;
import com.example.mydukaan.service.RecyclerInterface;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class RoomChat extends Fragment implements PopupMenu.OnMenuItemClickListener, TextWatcher {

    RecyclerInterface apiInterface;
    ImageView img_setting;
    String chatlist_id = null, user_name = null, agent_id = null, user_id = null, agent_name = null, order_id = null, order_no = null;
    RecyclerView rv_chat_room;
    SupportAdapter supportAdapter;
    ArrayList<Chatdetail> chat_arraylist;
    EditText massageedit;
    LinearLayout sendBtn, ln_user_details;
    Time today = new Time(Time.getCurrentTimezone());
    TextView txt_user_name, txt_empty;
    int postion;
    Home.HomeAdapter adapter;
    RecyclerView rv_home;
    //    Call<SendSms> call;
    private static final String TAG = "RoomChat";
    List<UserChat> myCustomArray;
    LottieAnimationView animationView;
    Call<SendSms> call;


    public RoomChat(String chatlist_id, String user_name, String agent_id, String user_id, String agent_name, String order_id, String order_no, int postion, Home.HomeAdapter adapter, RecyclerView rv_home, List<UserChat> myCustomArray) {
        this.rv_home = rv_home;
        this.postion = postion;
        this.adapter = adapter;
        this.agent_id = agent_id;
        this.chatlist_id = chatlist_id;
        this.user_name = user_name;
        this.user_id = user_id;
        this.order_no = order_no;
        this.agent_name = agent_name;
        this.order_id = order_id;
        this.myCustomArray = myCustomArray;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_chat, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View v, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        img_setting = v.findViewById(R.id.img_setting);
        rv_chat_room = v.findViewById(R.id.rv_chat_room);
        animationView = v.findViewById(R.id.animationView);
        massageedit = v.findViewById(R.id.edt_massage);
        txt_empty = v.findViewById(R.id.txt_empty);
        ln_user_details = v.findViewById(R.id.ln_user_details);
        txt_user_name = v.findViewById(R.id.txt_user_name);
        sendBtn = v.findViewById(R.id.ln_send);
        apiInterface = ApiClient.getClient().create(RecyclerInterface.class);
        chat_arraylist = new ArrayList<>();
        Home.Active_user_id = user_id;

        if (user_name != null) {
            txt_user_name.setText(user_name);
        }

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_chat_room.setLayoutManager(llm);
        Toast.makeText(getActivity(), chatlist_id, Toast.LENGTH_SHORT).show();

        if (user_id == null) {
            img_setting.setVisibility(View.INVISIBLE);
        } else {
            img_setting.setVisibility(View.VISIBLE);
        }

        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(RoomChat.this);
                popup.inflate(R.menu.setting);
                Menu popupMenu = popup.getMenu();

                if (order_no == null) {
                    popupMenu.findItem(R.id.order_details).setVisible(false);
                }
                popup.show();
            }
        });

        if (user_id != null) {
            //  ChatDetails(chatlist_id);

            txt_empty.setVisibility(View.GONE);
        } else {
            txt_empty.setVisibility(View.VISIBLE);
        }

        initalvaiew(v);


    }

    public void ChatDetails(String chatlist_id) {
        animationView.setVisibility(View.VISIBLE);
        Call<ChatDetailsExample> call = apiInterface.Chat_details(chatlist_id);
        call.enqueue(new Callback<ChatDetailsExample>() {
            @Override
            public void onResponse(Call<ChatDetailsExample> call, retrofit2.Response<ChatDetailsExample> response) {
                Log.e("TAG", "ChatDetails : " + new Gson().toJson(response.body()));

                if (response.isSuccessful()) {
                    if (response.body().getChatdetail() != null) {
                        for (int i = 0; i < response.body().getChatdetail().size(); i++) {
                            Chatdetail v = new Chatdetail();
                            v.setAgentId(response.body().getChatdetail().get(i).getAgentId());
                            v.setAgentName(response.body().getChatdetail().get(i).getAgentName());
                            v.setChatlistId(response.body().getChatdetail().get(i).getChatlistId());
                            v.setUserId(response.body().getChatdetail().get(i).getUserId());
                            v.setUserName(response.body().getChatdetail().get(i).getUserName());
                            v.setOrderId(response.body().getChatdetail().get(i).getOrderId());
                            v.setMsg(response.body().getChatdetail().get(i).getMsg());
                            String user = null;
                            if (response.body().getChatdetail().get(i).getSide().equalsIgnoreCase("USER")) {
                                user = "AGENT";
                            } else {
                                user = "USER";
                            }
                            v.setSide(user);
                            v.setTime(response.body().getChatdetail().get(i).getTime());
                            v.setUserToken(response.body().getChatdetail().get(i).getUserToken());
                            chat_arraylist.add(v);
                        }
                        supportAdapter = new SupportAdapter(getLayoutInflater());
                        rv_chat_room.setAdapter(supportAdapter);
                        supportAdapter.addItem(chat_arraylist);
                        rv_chat_room.scrollToPosition(chat_arraylist.size() - 1);
                        txt_empty.setVisibility(View.GONE);
                        animationView.setVisibility(View.GONE);
                    } else {
                        txt_empty.setVisibility(View.VISIBLE);
                        animationView.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "chat details empty! ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), getResources().getText(R.string.Something_worg_from_server), Toast.LENGTH_SHORT).show();
                    animationView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ChatDetailsExample> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());
                animationView.setVisibility(View.GONE);
                // Log error here since request failed
            }
        });
    }

    private void initalvaiew(View v) {
        massageedit = v.findViewById(R.id.edt_massage);
        sendBtn = v.findViewById(R.id.ln_send);
        massageedit.addTextChangedListener(this);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (massageedit.getText().toString().isEmpty()) {
                    massageedit.setError("Empty!");
                } else {
                    today.setToNow();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = df.format(Calendar.getInstance().getTime());

                    Chatdetail v = new Chatdetail();
                    v.setAgentId(agent_id);
                    v.setAgentName(agent_name);
                    v.setChatlistId(chatlist_id);
                    v.setUserId(user_id);
                    v.setUserName(user_name);
                    v.setOrderId(order_id);
                    v.setMsg(massageedit.getText().toString());
                    v.setSide("USER");
                    v.setTime(date);
                    chat_arraylist.add(v);

                    Integer postion = supportAdapter.getItemCount();
                    supportAdapter.addItem(chat_arraylist);
                    rv_chat_room.smoothScrollToPosition(postion);
                    String msg = massageedit.getText().toString();
                    resetmassageedit();


                    Log.e(TAG, "onClick: " + user_name);


                    Toast.makeText(getActivity(), "order_id :" + order_id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "order_no :" + order_no, Toast.LENGTH_SHORT).show();
//                    call = apiInterface.SendSms(agent_id, user_id, msg, order_id, "AGENT", chatlist_id, user_name);
                    call = apiInterface.SendSms(agent_id, user_id, msg, order_id, "AGENT", chatlist_id, user_name);
                    call.enqueue(new Callback<SendSms>() {
                        @Override
                        public void onResponse(Call<SendSms> call, retrofit2.Response<SendSms> response) {
                            Log.e("TAG", "send sms : " + new Gson().toJson(response.body()));

                        }

                        @Override
                        public void onFailure(Call<SendSms> call, Throwable t) {

                            Log.e("TAG", "onFailure: " + t.toString());
                            // Log error here since request failed
                            // Toast.makeText(getActivity(), getResources().getText(R.string.Please_Check_Internet_Connection), Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });


    }

    private void resetmassageedit() {
        massageedit.removeTextChangedListener(this);

        massageedit.setText("");

        sendBtn.setVisibility(View.INVISIBLE);

        massageedit.addTextChangedListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_details:
                FragmentManager managers = getActivity().getSupportFragmentManager();
                FragmentTransaction transactions = managers.beginTransaction();
                UserDetails userDetails = new UserDetails(user_id);
                transactions.replace(R.id.main_content, userDetails, "fragment");
                transactions.addToBackStack(null);
                transactions.commit();
                return true;
            case R.id.order_details:
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                OrderDetails home = new OrderDetails(order_no, user_id, user_name);
                transaction.replace(R.id.main_content, home, "fragment");
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            case R.id.end_chat:
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you Want To Exit  Chat Room !");
                // builder.setCancelable(false);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ChatEnd(chatlist_id, user_id);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();


                return true;
            default:
                return false;
        }

    }

    public void ChatEnd(String chat_list_id, String use_id) {
        Call<Object> call = apiInterface.EndChat(use_id, chat_list_id);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                Log.e("TAG", "Chat end : " + new Gson().toJson(response.body()));

                if (response.isSuccessful()) {
                    myCustomArray.remove(postion);
                    adapter.notifyDataSetChanged();

                    chat_arraylist.clear();
                    supportAdapter = new SupportAdapter(getLayoutInflater());
                    rv_chat_room.setAdapter(supportAdapter);
                    supportAdapter.addItem(chat_arraylist);
                    if (myCustomArray.size() > 0) {
                        Home.row = 0;
                        ChatDetails(myCustomArray.get(0).getId());
                        adapter.addItem(myCustomArray);
                        Home.Active_user_id = myCustomArray.get(0).getUserId();
                    } else {
                        img_setting.setVisibility(View.INVISIBLE);
                        txt_empty.setVisibility(View.VISIBLE);
                        txt_user_name.setText("Chat Room");
                    }
                } else {
                    Toast.makeText(getActivity(), getResources().getText(R.string.Something_worg_from_server), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                // Log error here since request failed
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String string = s.toString().trim();
        if (string.isEmpty()) {
            resetmassageedit();
        } else {
            //pickImageBtn.setVisibility(View.INVISIBLE);
            sendBtn.setVisibility(View.VISIBLE);

        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String sms = intent.getStringExtra("msg");
            String agent_id = intent.getStringExtra("agent_id");
            String chatlist_id = intent.getStringExtra("chatlist_id");
            String side = intent.getStringExtra("side");
            String time = intent.getStringExtra("time");
            String user_id = intent.getStringExtra("user_id");
            String api_type = intent.getStringExtra("api_type");


            if (api_type.equalsIgnoreCase("SEND")) {

                if (Home.Active_user_id != null && Home.Active_user_id.equalsIgnoreCase(user_id)) {
                    Chatdetail v = new Chatdetail();
                    v.setAgentId(agent_id);
                    v.setAgentName("agent");
                    v.setChatlistId(chatlist_id);
                    v.setUserId(user_id);
                    v.setUserName(user_name);
                    v.setOrderId("1");
                    v.setMsg(sms);
                    v.setSide("AGENT");
                    v.setTime(time);
                    v.setUserToken("ads");
                    chat_arraylist.add(v);
                    Integer postion = supportAdapter.getItemCount();
                    supportAdapter.addItem(chat_arraylist);
                    rv_chat_room.smoothScrollToPosition(postion);
                    supportAdapter.notifyDataSetChanged();
                } else {
                    Home.Updatelist(Home.Active_user_id);
                }
                txt_empty.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(MymassegingService.str_receiver));
        chat_arraylist.clear();
        if (user_id != null) {
            ChatDetails(chatlist_id);
            // Getuserdrealis(user_id);
            txt_empty.setVisibility(View.GONE);
        } else {
            txt_empty.setVisibility(View.VISIBLE);
        }

        //ChatDetails(chatlist_id);
    }


}




























