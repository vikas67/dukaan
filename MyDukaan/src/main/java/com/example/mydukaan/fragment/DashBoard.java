package com.example.mydukaan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydukaan.R;
import com.example.mydukaan.service.SessionManager;

import org.jetbrains.annotations.NotNull;


public class DashBoard extends Fragment {


    private String mParam1;
    private String mParam2;
    ImageView img_back;
    TextView txt_id,txt_name;
    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View v, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        img_back=v.findViewById(R.id.img_back);
        txt_id=v.findViewById(R.id.txt_id);
        txt_name=v.findViewById(R.id.txt_name);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        sessionManager =new SessionManager();

        if(sessionManager.getPreferences(getActivity(),"login_id") !=null && sessionManager.getPreferences(getActivity(),"name") !=null){
            txt_id.setText(sessionManager.getPreferences(getActivity(),"login_id"));
            txt_name.setText(sessionManager.getPreferences(getActivity(),"name"));
        }

    }
}