package com.example.mydukaan.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.mydukaan.Interface.DialogCloseInterface;
import com.example.mydukaan.R;
import com.example.mydukaan.modal.ChatOrderAction.Chatorderaction;
import com.example.mydukaan.modal.order.OrderDetailsExample;
import com.example.mydukaan.modal.order.Orders;
import com.example.mydukaan.service.ApiClient;
import com.example.mydukaan.service.RecyclerInterface;
import com.example.mydukaan.service.SessionManager;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderDetails extends Fragment implements TextWatcher {


    ImageView img_back, img_search_first;
    RecyclerInterface apiInterface;
    RecyclerView rv_order;
    OrderAdapter adpter;
    TextView txt_order_no, txt_date, txt_time, txt_shipping_address, txt_payment_metod, txt_total_mrp,
            txt_discount_price, txt_coupon_price, txt_shipping_cahrge, txt_grand_total, txt_action, raider_up_text, refund_btn , UserName;
    CardView crd_action, crd_raiseUp, crd_refund, raise_take_action, refund_take_action;
    CardView crd_take_action, crd_search_second;
    LinearLayout ln_order_details;
    EditText edt_search;
    LottieAnimationView animationView;
    ArrayList<Orders> ordersArrayList;
    ArrayList<Orders> ordersChange_status_ArrayList;
    boolean check_action = false;
    String order_no, order_id, user_id, user_name = null;
    private static final String TAG = "OrderDetails";
    DialogCloseInterface dialogCloseInterface;
    Dialog dialog;
    SessionManager sessionManager;

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public OrderDetails(String order_no, String user_id, String user_name) {
        this.order_no = order_no;
        this.user_id = user_id;
        this.user_name = user_name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View v, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);



        img_back = v.findViewById(R.id.img_back);
        rv_order = v.findViewById(R.id.rv_order);
        edt_search = v.findViewById(R.id.edt_search);
        txt_order_no = v.findViewById(R.id.txt_order_no);
        ln_order_details = v.findViewById(R.id.ln_order_details);
        crd_action = v.findViewById(R.id.crd_action);
        txt_date = v.findViewById(R.id.txt_date);
        crd_search_second = v.findViewById(R.id.crd_search_second);
        txt_time = v.findViewById(R.id.txt_time);
        img_search_first = v.findViewById(R.id.img_search_first);
        txt_shipping_address = v.findViewById(R.id.txt_shipping_address);
        txt_payment_metod = v.findViewById(R.id.txt_payment_metod);
        txt_total_mrp = v.findViewById(R.id.txt_total_mrp);
        txt_discount_price = v.findViewById(R.id.txt_discount_price);
        txt_coupon_price = v.findViewById(R.id.txt_coupon_price);
        txt_shipping_cahrge = v.findViewById(R.id.txt_shipping_cahrge);
        txt_grand_total = v.findViewById(R.id.txt_grand_total);
        crd_take_action = v.findViewById(R.id.crd_take_action);
        crd_refund = v.findViewById(R.id.crd_refund);
        crd_raiseUp = v.findViewById(R.id.crd_raiseUp);
        txt_action = v.findViewById(R.id.txt_action);
        animationView = v.findViewById(R.id.animationView);
        raise_take_action = v.findViewById(R.id.raise_take_action);
        raider_up_text = v.findViewById(R.id.raider_up_text);
        refund_take_action = v.findViewById(R.id.refund_take_action);
        refund_btn = v.findViewById(R.id.refund_btn);
        UserName = v.findViewById(R.id.UserName);

        sessionManager = new SessionManager();
//        Log.e(TAG, "onCreateView: " +  sessionManager.getPreferences(getActivity() , "name"));
        Log.e(TAG, "onCreateView: " + user_id);

        apiInterface = ApiClient.getClient().create(RecyclerInterface.class);
        ordersArrayList = new ArrayList<>();
        ordersChange_status_ArrayList = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_order.setLayoutManager(llm);

        GetOrderDetails(order_no, user_id);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        crd_take_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordersChange_status_ArrayList.size() > 0) {
                    statusDialog();
                } else {
                    Toast.makeText(getActivity(), "Please Select At least One Order", Toast.LENGTH_SHORT).show();
                }

            }
        });


        crd_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check_action) {


                    for (int i = 0; i < ordersArrayList.size(); i++) {
                        Orders c = ordersArrayList.get(i);
                        c.setSelect_box(true);
                        c.setCheck_box(true);
                    }
                    adpter.notifyDataSetChanged();
                    check_action = true;
//                    raise_take_action.setVisibility(View.INVISIBLE);
                    crd_take_action.setVisibility(View.VISIBLE);
                    crd_action.setCardBackgroundColor(Color.parseColor("#F47D7D"));
                    txt_action.setTextColor(Color.parseColor("#FFFFFFFF"));
                    txt_action.setText("Close");


                    crd_raiseUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    raider_up_text.setTextColor(Color.parseColor("#FF040303"));
                    raider_up_text.setText("Raise Up");
                    raise_take_action.setVisibility(View.INVISIBLE);

                    refund_take_action.setVisibility(View.INVISIBLE);
                    refund_btn.setText("Refund");
                  
                    crd_refund.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));

                } else {
                    for (int i = 0; i < ordersArrayList.size(); i++) {
                        Orders c = ordersArrayList.get(i);
                        c.setSelect_box(false);
                        c.setCheck_box(false);
                    }
                    adpter.notifyDataSetChanged();
                    check_action = false;
//                    raise_take_action.setVisibility(View.INVISIBLE);
                    crd_take_action.setVisibility(View.INVISIBLE);
                    crd_action.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    txt_action.setTextColor(Color.parseColor("#FF040303"));
                    ordersChange_status_ArrayList.clear();
                    txt_action.setText("Action");

                    crd_raiseUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    raider_up_text.setTextColor(Color.parseColor("#FF040303"));
                    raider_up_text.setText("Raise Up");
                    raise_take_action.setVisibility(View.INVISIBLE);



                    refund_take_action.setVisibility(View.INVISIBLE);
                    refund_btn.setText("Refund");
                    crd_refund.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                }

            }
        });
        crd_raiseUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!check_action) {
                    for (int i = 0; i < ordersArrayList.size(); i++) {
                        Orders c = ordersArrayList.get(i);
                        c.setSelect_box(true);
                        c.setCheck_box(true);
                    }
                    adpter.notifyDataSetChanged();
                    check_action = true;
                    crd_take_action.setVisibility(View.INVISIBLE);
                    raise_take_action.setVisibility(View.VISIBLE);
                    crd_raiseUp.setCardBackgroundColor(Color.parseColor("#F47D7D"));
                    raider_up_text.setTextColor(Color.parseColor("#FFFFFFFF"));
                    raider_up_text.setText("Close");

                    crd_take_action.setVisibility(View.INVISIBLE);
                    crd_action.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    txt_action.setTextColor(Color.parseColor("#FF040303"));
                    txt_action.setText("Action");


                    refund_take_action.setVisibility(View.INVISIBLE);
                    refund_btn.setText("Refund");
                    crd_refund.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));

                } else {
                    for (int i = 0; i < ordersArrayList.size(); i++) {
                        Orders c = ordersArrayList.get(i);
                        c.setSelect_box(false);
                        c.setCheck_box(false);
                    }
                    adpter.notifyDataSetChanged();
                    check_action = false;
                    crd_take_action.setVisibility(View.INVISIBLE);
                    raise_take_action.setVisibility(View.INVISIBLE);
                    crd_raiseUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    raider_up_text.setTextColor(Color.parseColor("#FF040303"));
                    ordersChange_status_ArrayList.clear();
                    raider_up_text.setText("Raise Up");

                    crd_take_action.setVisibility(View.INVISIBLE);
                    crd_action.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    txt_action.setTextColor(Color.parseColor("#FF040303"));
                    txt_action.setText("Action");


                    refund_take_action.setVisibility(View.INVISIBLE);
                    refund_btn.setText("Refund");
                    crd_refund.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                }


            }
        });


        raise_take_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!check_action) {
                    for (int i = 0; i < ordersArrayList.size(); i++) {
                        Orders c = ordersArrayList.get(i);
                        c.setSelect_box(true);
                        c.setCheck_box(true);
                    }
                    adpter.notifyDataSetChanged();
                    check_action = true;


                    crd_take_action.setVisibility(View.INVISIBLE);
                    crd_action.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    txt_action.setTextColor(Color.parseColor("#FF040303"));
                    txt_action.setText("Action");


                    crd_raiseUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    raider_up_text.setTextColor(Color.parseColor("#FF040303"));
                    raider_up_text.setText("Raise Up");
                    raise_take_action.setVisibility(View.INVISIBLE);

                } else {
                    for (int i = 0; i < ordersArrayList.size(); i++) {
                        Orders c = ordersArrayList.get(i);
                        c.setSelect_box(false);
                        c.setCheck_box(false);
                    }
                    adpter.notifyDataSetChanged();
                    check_action = false;


                    crd_take_action.setVisibility(View.INVISIBLE);
                    crd_action.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    txt_action.setTextColor(Color.parseColor("#FF040303"));
                    txt_action.setText("Action");

                    crd_raiseUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    raider_up_text.setTextColor(Color.parseColor("#FF040303"));
                    raider_up_text.setText("Raise Up");
                    raise_take_action.setVisibility(View.INVISIBLE);

                }


                if (ordersChange_status_ArrayList.size() > 0) {
                    if (ordersChange_status_ArrayList.size() == 1) {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        RaiseUp raiseUp = new RaiseUp(order_no, ordersChange_status_ArrayList.get(0).getId(), user_id, user_name);
                        transaction.replace(R.id.main_content, raiseUp, "fragment");
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } else {
                        Toast.makeText(getActivity(), "Please Select One Order", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please Select At least One Order", Toast.LENGTH_SHORT).show();
                }

            }
        });

        crd_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ==================================================================================


                if (!check_action) {
                    for (int i = 0; i < ordersArrayList.size(); i++) {
                        Orders c = ordersArrayList.get(i);
                        c.setSelect_box(true);
                        c.setCheck_box(true);
                    }
                    adpter.notifyDataSetChanged();
                    check_action = true;

                    refund_take_action.setVisibility(View.VISIBLE);
                    refund_btn.setText("Close");
                    crd_refund.setCardBackgroundColor(Color.parseColor("#F47D7D"));

                    crd_take_action.setVisibility(View.INVISIBLE);
                    crd_action.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    txt_action.setTextColor(Color.parseColor("#FF040303"));
                    txt_action.setText("Action");


                    crd_raiseUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    raider_up_text.setTextColor(Color.parseColor("#FF040303"));
                    raider_up_text.setText("Raise Up");
                    raise_take_action.setVisibility(View.INVISIBLE);

                } else {
                    for (int i = 0; i < ordersArrayList.size(); i++) {
                        Orders c = ordersArrayList.get(i);
                        c.setSelect_box(false);
                        c.setCheck_box(false);
                    }
                    adpter.notifyDataSetChanged();
                    check_action = false;


                    refund_take_action.setVisibility(View.INVISIBLE);
                    refund_btn.setText("Refund");
                    crd_refund.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    refund_btn.setTextColor(Color.parseColor("#FF040303"));

                    crd_take_action.setVisibility(View.INVISIBLE);
                    crd_action.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    txt_action.setTextColor(Color.parseColor("#FF040303"));
                    txt_action.setText("Action");

                    crd_raiseUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    raider_up_text.setTextColor(Color.parseColor("#FF040303"));
                    raider_up_text.setText("Raise Up");
                    raise_take_action.setVisibility(View.INVISIBLE);


                }


//                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage("Refund Amount Is " + txt_grand_total.getText().toString());
//                // builder.setCancelable(false);
//                builder.setPositiveButton("Refund", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//
//                    }
//                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//                builder.show();

            }
        });

        edt_search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String item = edt_search.getText().toString();
                    if (item.isEmpty()) {
                        Toast.makeText(getActivity(), "Please Enter Value", Toast.LENGTH_SHORT).show();
                    } else {
                        if (haveNetworkConnection()) {
                            GetOrderDetails(item, user_id);
                            edt_search.getText().clear();
                        } else {
                            Toast.makeText(getActivity(), getResources().getText(R.string.Please_Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                        }

                    }
                    return true;
                }
                return false;
            }
        });
        edt_search.addTextChangedListener(this);

        dialogCloseInterface = new DialogCloseInterface() {
            @Override
            public void closeDialog() {
                Log.e(TAG, "closeDialog: close dialog ");
                GetOrderDetails(order_no, user_id);
                dialog.dismiss();
            }
        };

    }

    public void statusDialog() {
        dialog = new Dialog(getActivity());
        // Include dialog.xml file
        dialog.setContentView(R.layout.change_status_layout);
        // Set dialog title
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);
        final RecyclerView rv_dialog = dialog.findViewById(R.id.rv_dialog);
        ImageView img_cancal = dialog.findViewById(R.id.img_cancal);

        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.orderactionchange));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_dialog.setLayoutManager(llm);

        OrderDailogAdapter adpter = new OrderDailogAdapter(getActivity(), ordersChange_status_ArrayList, dialogCloseInterface);
        rv_dialog.setAdapter(adpter);

        dialog.show();

        img_cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }

    public void GetOrderDetails(String order_no, String user_id) {
        animationView.setVisibility(View.VISIBLE);
        ln_order_details.setVisibility(View.INVISIBLE);
        Call<OrderDetailsExample> call = apiInterface.GetorderDetails(order_no);
        call.enqueue(new Callback<OrderDetailsExample>() {
            @Override
            public void onResponse(Call<OrderDetailsExample> call, retrofit2.Response<OrderDetailsExample> response) {
                Log.e("TAG", "order details  : " + new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (response.body().getDetails() != null) {
                        txt_order_no.setText("#" + response.body().getDetails().get(0).getOrder_no());
                        order_id = response.body().getDetails().get(0).getId();
                        String date = parseDateToddMMyyyy(response.body().getDetails().get(0).getTime());
                        convertToCustomFormat(response.body().getDetails().get(0).getTime());
                        String address = response.body().getDetails().get(0).getAddress();
                        String method = response.body().getDetails().get(0).getPayment_mode();
                        String pretotoal = response.body().getDetails().get(0).getOrder_pre_total();
                        String discount = response.body().getDetails().get(0).getDiscount_amount();
                        String coupon = response.body().getDetails().get(0).getCoupon_code_amount();
                        String s_charge = response.body().getDetails().get(0).getDelivery_caharge();
                        String paid = response.body().getDetails().get(0).getPaid_amount();
                        String userName = response.body().getDetails().get(0).getUser_name();

                        ordersArrayList = (ArrayList<Orders>) response.body().getDetails();
                        adpter = new OrderAdapter(getActivity(), ordersArrayList);
                        rv_order.setAdapter(adpter);
                        txt_date.setText(date);
                        txt_shipping_address.setText(address);
                        txt_payment_metod.setText(method);
                        txt_total_mrp.setText(pretotoal);
                        txt_discount_price.setText(discount);
                        txt_coupon_price.setText(coupon);
                        txt_shipping_cahrge.setText(s_charge);
                        txt_grand_total.setText(paid);
                        UserName.setText(userName);
                        animationView.setVisibility(View.GONE);
                        ln_order_details.setVisibility(View.VISIBLE);
                    } else {
                        animationView.setVisibility(View.VISIBLE);
                        ln_order_details.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity(), "Please Check Order Id", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), getResources().getText(R.string.Something_worg_from_server), Toast.LENGTH_SHORT).show();
                    animationView.setVisibility(View.GONE);
                    ln_order_details.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<OrderDetailsExample> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());
                animationView.setVisibility(View.GONE);
                ln_order_details.setVisibility(View.VISIBLE);
            }
        });

    }

    private void convertToCustomFormat(String date) {
        String finaldate = null;
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date input = inputFormat.parse(date);
            DateFormat outputFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
            finaldate = outputFormat.format(input);
            txt_time.setText(finaldate);

        } catch (Exception ex) {

        }

    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";


        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat dtfmt = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = dtfmt.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            crd_search_second.setVisibility(View.VISIBLE);
            img_search_first.setVisibility(View.INVISIBLE);
            GetOrderDetails(edt_search.getText().toString().trim(), user_id);
        } else {
            crd_search_second.setVisibility(View.INVISIBLE);
            img_search_first.setVisibility(View.VISIBLE);
            GetOrderDetails(order_no, user_id);
        }
    }

    private void OrderSearchDetails(String orderid) {
        Log.e(TAG, "OrderSearchDetails: " + orderid);
    }

    public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Myholder> {
        SessionManager sessionManager;
        FragmentActivity homes;
        List<Orders> myCustomArray;

        public OrderAdapter(FragmentActivity home, List<Orders> myCustomArray) {
            this.homes = home;
            this.myCustomArray = myCustomArray;
        }


        @NonNull
        @Override
        public OrderAdapter.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_layout, parent, false);
            sessionManager = new SessionManager();

            return new OrderAdapter.Myholder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull final OrderAdapter.Myholder holder, final int position) {
            final Orders c = myCustomArray.get(position);

            if (c.getDelivey_status().equalsIgnoreCase("CANCEL")) {
                c.setSelect_box(false);
                holder.txt_status.setTextColor(Color.parseColor("#F47D7D"));
            }

            Log.e(TAG, "onBindViewHolder: " + c.isSelect_box());

            if (c.isSelect_box()) {
                holder.checkbox.setVisibility(View.VISIBLE);
            } else {
                holder.checkbox.setVisibility(View.GONE);
                holder.checkbox.setChecked(false);
            }

            if (c.isCheck_box()) {
                Log.e(TAG, "onBindViewHolder: ");
                holder.checkbox.setVisibility(View.VISIBLE);
            } else {
                Log.e(TAG, "onBindViewHolder: ");
                holder.checkbox.setVisibility(View.GONE);
                holder.checkbox.setChecked(false);
            }


            holder.txt_status.setText(c.getDelivey_status());
            holder.txt_name.setText(c.getProduct_name());
            holder.txt_paid.setText(c.getPaid_amount());
            holder.txt_qyt.setText(c.getQty());
            Glide.with(homes).load("https://yallatager.com/" + c.getProduct_img()).placeholder(R.drawable.progress_animation).into(holder.img_photo);


            holder.ln_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (c.isSelect_box()) {
                        if (c.getDelivey_status().equalsIgnoreCase("CANCEL")) {
                            Toast.makeText(homes, "Product Already Cancel", Toast.LENGTH_SHORT).show();
                        } else {
                            holder.checkbox.setChecked(true);
                            if (!ordersChange_status_ArrayList.isEmpty()) {
                                boolean check = false;
                                for (int i = 0; i < ordersChange_status_ArrayList.size(); i++) {
                                    Orders v = ordersChange_status_ArrayList.get(i);
                                    if (v.getId().equalsIgnoreCase(c.getId())) {
                                        check = true;
                                    }

                                }
                                if (!check) {
                                    ordersChange_status_ArrayList.add(c);
                                }
                            } else {
                                ordersChange_status_ArrayList.add(c);
                            }

                        }


                    } else {

                    }

                }
            });
            holder.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean check = holder.checkbox.isChecked();
                    if (check) {
                        ordersChange_status_ArrayList.add(c);
                    } else {
                        ordersChange_status_ArrayList.remove(position);
                    }
                }
            });


        }

        @Override
        public int getItemCount() {
            return myCustomArray.size();
        }

        public class Myholder extends RecyclerView.ViewHolder {
            LinearLayout ln_main;
            ImageView img_photo;
            TextView txt_qyt, txt_name, txt_paid, txt_status;
            CheckBox checkbox;

            public Myholder(@NonNull View itemView) {
                super(itemView);
                txt_qyt = (TextView) itemView.findViewById(R.id.txt_qyt);
                txt_name = (TextView) itemView.findViewById(R.id.txt_name);
                txt_paid = (TextView) itemView.findViewById(R.id.txt_paid);
                img_photo = (ImageView) itemView.findViewById(R.id.img_photo);
                ln_main = (LinearLayout) itemView.findViewById(R.id.ln_main);
                checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
                txt_status = (TextView) itemView.findViewById(R.id.txt_status);
            }
        }

        public void addItem(List<Orders> myCustomArray) {

            this.myCustomArray = myCustomArray;
            notifyDataSetChanged();
        }


    }

    public class OrderDailogAdapter extends RecyclerView.Adapter<OrderDailogAdapter.Myholder> {
        SessionManager sessionManager;
        FragmentActivity homes;
        List<Orders> myCustomArray;
        String[] after_process_items = new String[]{"SHIPPED", "DELIVERED", "CANCEL"};
        String[] after_shippid_items = new String[]{"SHIPPED", "DELIVERED", "CANCEL"};
        String[] after_deliverd_items = new String[]{"CANCEL"};
        String status;
        DialogCloseInterface dialogCloseInterface;

        public OrderDailogAdapter(FragmentActivity home, List<Orders> myCustomArray, DialogCloseInterface dialogCloseInterface) {
            this.homes = home;
            this.myCustomArray = myCustomArray;
            this.dialogCloseInterface = dialogCloseInterface;
        }


        @NonNull
        @Override
        public OrderDailogAdapter.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dialog_status_change_layout, parent, false);
            sessionManager = new SessionManager();

            return new OrderDailogAdapter.Myholder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull final OrderDailogAdapter.Myholder holder, final int position) {
            final Orders c = myCustomArray.get(position);
            Glide.with(homes).load("https://yallatager.com/" + c.getProduct_img()).placeholder(R.drawable.progress_animation).into(holder.img_photo);
            holder.txt_name.setText(c.getProduct_name());

            Log.e(TAG, "onBindViewHolder: " + c.getDelivey_status());

            if (c.getDelivey_status().equalsIgnoreCase("PROCESSING")) {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(homes, android.R.layout.simple_spinner_dropdown_item, after_process_items);
                holder.sp_action.setAdapter(adapter);
            } else if (c.getDelivey_status().equalsIgnoreCase("SHIPPED")) {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(homes, R.layout.simple_spinner_layout, after_shippid_items);
                holder.sp_action.setAdapter(adapter);
            } else if (c.getDelivey_status().equalsIgnoreCase("DELIVERED")) {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(homes, R.layout.simple_spinner_layout, after_deliverd_items);
                holder.sp_action.setAdapter(adapter);
            }

            holder.sp_action.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (c.getDelivey_status().equalsIgnoreCase("PROCESSING"))
                        status = String.valueOf(after_process_items[position]);
                    else if (c.getDelivey_status().equalsIgnoreCase("SHIPPED")) {
                        if (after_shippid_items[position].equalsIgnoreCase("SHIPPED")) {
                            Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                        } else {
                            status = String.valueOf(after_shippid_items[position]);
                        }

                    } else if (c.getDelivey_status().equalsIgnoreCase("DELIVERED"))
                        status = String.valueOf(after_deliverd_items[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            holder.crd_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChangeOrderAction(status, c.getId(), holder.disc.getText().toString().trim());
                    dialogCloseInterface.closeDialog();
                }
            });


        }

        private void ChangeOrderAction(String status, String order_id, String dis) {
            Call<Chatorderaction> chatorderactionCall = apiInterface.ChatOrderAction(order_id, dis, status);
            chatorderactionCall.enqueue(new Callback<Chatorderaction>() {
                @Override
                public void onResponse(Call<Chatorderaction> call, Response<Chatorderaction> response) {
                    if (response.isSuccessful()) {
                        Log.e(TAG, "onResponse: " + response.body());
                    }
                }

                @Override
                public void onFailure(Call<Chatorderaction> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return myCustomArray.size();
        }

        public class Myholder extends RecyclerView.ViewHolder {
            LinearLayout ln_main;
            ImageView img_photo;
            TextView txt_name;
            Spinner sp_action;
            CardView crd_change;
            EditText disc;

            public Myholder(@NonNull View itemView) {
                super(itemView);
                txt_name = (TextView) itemView.findViewById(R.id.txt_name);
                img_photo = (ImageView) itemView.findViewById(R.id.img_photo);
                ln_main = (LinearLayout) itemView.findViewById(R.id.ln_main);
                sp_action = (Spinner) itemView.findViewById(R.id.sp_action);
                crd_change = (CardView) itemView.findViewById(R.id.crd_change);
                disc = (EditText) itemView.findViewById(R.id.disc);

            }
        }

        public void addItem(List<Orders> myCustomArray) {
            this.myCustomArray = myCustomArray;
            notifyDataSetChanged();
        }


    }
}