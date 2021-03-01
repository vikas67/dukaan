package com.example.mydukaan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.mydukaan.R;
import com.example.mydukaan.modal.Userdetails.UserdetailsExample;
import com.example.mydukaan.service.ApiClient;
import com.example.mydukaan.service.RecyclerInterface;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;


public class UserDetails extends Fragment {

    RecyclerInterface apiInterface;
    String user_id;
    ImageView img_back,img_photo ;
    TextView txt_name,txt_number,txt_id,txt_login_time,txt_top_user_name;
    LottieAnimationView animationView;
    LinearLayout ln_user_details;

    public UserDetails(String user_id) {
        this.user_id=user_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View v, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        img_back=v.findViewById(R.id.img_back);
        txt_top_user_name=v.findViewById(R.id.txt_user_name);
        txt_name=v.findViewById(R.id.txt_name);
        img_photo=v.findViewById(R.id.img_photo);
        txt_number=v.findViewById(R.id.txt_number);
        txt_id=v.findViewById(R.id.txt_id);
        txt_login_time=v.findViewById(R.id.txt_login_time);
        ln_user_details=v.findViewById(R.id.ln_user_details);
        animationView=v.findViewById(R.id.animationView);
        apiInterface = ApiClient.getClient().create(RecyclerInterface.class);
        txt_top_user_name.setVisibility(View.INVISIBLE);
        animationView.setVisibility(View.VISIBLE);
        ln_user_details.setVisibility(View.INVISIBLE);
        GetUserDetails(user_id);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }


    public  void  GetUserDetails(String use_id){
        Call<UserdetailsExample> call = apiInterface.GetuserDetails(use_id);
        call.enqueue(new Callback<UserdetailsExample>() {
            @Override
            public void onResponse(Call<UserdetailsExample> call, retrofit2.Response<UserdetailsExample> response) {
                Log.e("TAG", "User details  : " + new Gson().toJson(response.body()));
                if(response.isSuccessful()){
                    txt_top_user_name.setText(response.body().getuser_profile().getUserName());
                    txt_name.setText(response.body().getuser_profile().getUserName());
                    txt_id.setText(response.body().getuser_profile().getEmail());
                    txt_number.setText(response.body().getuser_profile().getMobile());
                    Glide.with(getActivity()).load("https://yallatager.com/"+response.body().getuser_profile().getPhoto()).placeholder(R.drawable.progress_animation).into(img_photo);
                    animationView.setVisibility(View.GONE);
                    ln_user_details.setVisibility(View.VISIBLE);
                    txt_top_user_name.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(getActivity(), getResources().getText(R.string.Something_worg_from_server), Toast.LENGTH_SHORT).show();
                    animationView.setVisibility(View.GONE);
                    ln_user_details.setVisibility(View.VISIBLE);
                    txt_top_user_name.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<UserdetailsExample> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());
                animationView.setVisibility(View.GONE);
                ln_user_details.setVisibility(View.VISIBLE);
                txt_top_user_name.setVisibility(View.VISIBLE);
                // Log error here since request failed
            }
        });
    }


}































