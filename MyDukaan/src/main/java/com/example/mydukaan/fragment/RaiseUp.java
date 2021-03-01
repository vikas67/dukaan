package com.example.mydukaan.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydukaan.R;
import com.example.mydukaan.modal.ChatChomplane.ChatComplainList;
import com.example.mydukaan.modal.ChatChomplane.ComplaintList;
import com.example.mydukaan.modal.ChatNewComplain;
import com.example.mydukaan.modal.Movie;
import com.example.mydukaan.service.ApiClient;
import com.example.mydukaan.service.RecyclerInterface;
import com.example.mydukaan.service.SessionManager;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaiseUp extends Fragment {

    ImageView img_back;
    EditText edt_title, edt_number, edt_des;
    CardView submitComp;
    RecyclerView complaneRecyclerView;
    RecyclerInterface anInterface;
    String order_no, order_id;
    List<Movie> movieList;
    SessionManager sessionManager;
    private static final String TAG = "RaiseUp";
    String user_id, user_name;
    ProgressBar progress_circular;
    LinearLayout back;

    public RaiseUp(String order_no, String id, String user_id, String user_name) {
        this.order_no = order_no;
        this.order_id = order_id;
        this.user_id = user_id;
        this.user_name = user_name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_raise_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View v, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);


        img_back = v.findViewById(R.id.img_back);

        edt_title = v.findViewById(R.id.edt_title);
        edt_number = v.findViewById(R.id.edt_number);
        edt_des = v.findViewById(R.id.edt_des);
        submitComp = v.findViewById(R.id.submitComp);
        complaneRecyclerView = v.findViewById(R.id.complaneRecyclerView);
        progress_circular = v.findViewById(R.id.progress_circular);
        back = v.findViewById(R.id.back);

        anInterface = ApiClient.getClient().create(RecyclerInterface.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        complaneRecyclerView.setLayoutManager(linearLayoutManager);

        getComplaneList(user_id);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        submitComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComp.setClickable(false);
                String title = edt_title.getText().toString().trim();
                String mob = edt_number.getText().toString().trim();
                String des = edt_des.getText().toString().trim();
                if (haveNetworkConnection()) {
                    if (title.isEmpty()) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    } else if (mob.isEmpty()) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    } else if (des.isEmpty()) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    } else {
                        RaiseUpOrder(title, mob, des, user_id, user_name);
                    }
                } else {
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.Please_Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                }
                submitComp.setClickable(true);
            }
        });



    }


    private void getComplaneList(String user_id) {

        progress_circular.setVisibility(View.VISIBLE);

        Call<ChatComplainList> call = anInterface.getComplane(user_id);
        call.enqueue(new Callback<ChatComplainList>() {
            @Override
            public void onResponse(Call<ChatComplainList> call, Response<ChatComplainList> response) {
                Log.e("TAG", "userdetails: " + new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (!response.body().getError()) {
                        complaneRecyclerView.setAdapter(new ComplaneProduct(response.body().getComplaintList()));
                        progress_circular.setVisibility(View.GONE);
                    } else {
                        progress_circular.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ChatComplainList> call, Throwable t) {

                Log.e("TAG", "onFailure: " + t.toString());
                // Log error here since request failed
                Toast.makeText(getActivity(), getResources().getText(R.string.Please_Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    private void RaiseUpOrder(String title, String mobile, String des, final String user_id, String user_name) {

        progress_circular.setVisibility(View.VISIBLE);

        Call<ChatNewComplain> call = anInterface.Chat_new_complain(user_id, order_id, order_no, user_name, mobile, title, des);
        call.enqueue(new Callback<ChatNewComplain>() {
            @Override
            public void onResponse(Call<ChatNewComplain> call, Response<ChatNewComplain> response) {

                Log.e("TAG", "userdetails: " + new Gson().toJson(response.body()));
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    String error = jsonObject.getString("error");
                    String massage = jsonObject.getString("message");
                    if (error.equalsIgnoreCase("false")) {
                        Toast.makeText(getActivity(), massage, Toast.LENGTH_SHORT).show();
                        progress_circular.setVisibility(View.GONE);
                        edt_title.setText("");
                        edt_number.setText("");
                        edt_des.setText("");
                        getComplaneList(user_id);
                    } else {
                        Toast.makeText(getActivity(), massage, Toast.LENGTH_SHORT).show();
                        progress_circular.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progress_circular.setVisibility(View.GONE);
                }


            }

            @Override
            public void onFailure(Call<ChatNewComplain> call, Throwable t) {

                Log.e("TAG", "onFailure: " + t.toString());
                // Log error here since request failed
                Toast.makeText(getActivity(), getResources().getText(R.string.Please_Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }


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


    private class ComplaneProduct extends RecyclerView.Adapter<ComplaneProduct.Viewholder> {

        List<Movie> movies;
        List<ComplaintList> complaintLists;

        public ComplaneProduct(List<ComplaintList> complaintLists) {
            this.complaintLists = complaintLists;
        }

        @NonNull
        @Override
        public ComplaneProduct.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_complane, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ComplaneProduct.Viewholder holder, int position) {
            ComplaintList list = complaintLists.get(position);
            boolean isExpanded = list.isExpanded();

            holder.title.setText(list.getHeading());
            holder.des.setText(list.getDes());
            holder.Exp.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

            holder.resolutionStatus.setText(String.valueOf(list.getResolutionStatus()));

            if (list.getResolutionStatus().equals("PENDING")) {
                holder.res_des.setVisibility(View.GONE);
            } else {
                holder.res_des.setVisibility(View.VISIBLE);
                holder.resolution_des.setText(String.valueOf(list.getResolutionDes()));
            }


        }

        @Override
        public int getItemCount() {
            return complaintLists.size();
        }


        public class Viewholder extends RecyclerView.ViewHolder {
            TextView title, des, resolutionStatus, resolution_des;
            LinearLayout addLayout, Exp;
            LinearLayout res_des;

            public Viewholder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                addLayout = itemView.findViewById(R.id.addLayout);
                des = itemView.findViewById(R.id.des);
                Exp = itemView.findViewById(R.id.Exp);
                resolutionStatus = itemView.findViewById(R.id.resolutionStatus);
                res_des = itemView.findViewById(R.id.res_des);
                resolution_des = itemView.findViewById(R.id.resolution_des);

                addLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ComplaintList list = complaintLists.get(getAdapterPosition());
                        list.setExpanded(!list.isExpanded());
                        notifyItemChanged(getAdapterPosition());
                    }
                });
            }
        }
    }


}
































