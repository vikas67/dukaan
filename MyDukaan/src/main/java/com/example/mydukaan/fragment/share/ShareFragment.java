package com.example.mydukaan.fragment.share;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydukaan.ActionBar.CustomActionBar;
import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;

import org.jetbrains.annotations.NotNull;


public class ShareFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new CustomActionBar(getActivity(),getString(R.string.title_share));
    }

    @Override
    public void onStart() {
        super.onStart();
//        new DashboardActivity().HideBottomNavigation();
    }

}