package com.example.mydukaan.fragment.ginny_fragment.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydukaan.R;

public class HomeGinnyFragment extends Fragment {

    private HomeGinnyViewModel mViewModel;

    public static HomeGinnyFragment newInstance() {
        return new HomeGinnyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_ginny_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeGinnyViewModel.class);
        // TODO: Use the ViewModel
    }

}