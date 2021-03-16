package com.example.mydukaan.fragment.ginny_fragment.home;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.ginnyActivity.ManageActivity;
import com.example.mydukaan.activity.ginnyActivity.OpenFragmentActivity;
import com.example.mydukaan.adapter.OrderAdapter;

public class HomeGinnyFragment extends Fragment {

    private HomeGinnyViewModel mViewModel;

    public static HomeGinnyFragment newInstance() {
        return new HomeGinnyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.home_ginny_fragment, container, false);
        RecyclerView recyclerView= view.findViewById(R.id.recycle);
        recyclerView.setAdapter(new OrderAdapter());
        view.findViewById(R.id.duraction_layout).setOnClickListener(v -> {getActivity().startActivity(new Intent(getActivity(), ManageActivity.class).putExtra("action","manage").putExtra("tag","purchse"));});
        view.findViewById(R.id.view_all).setOnClickListener(v -> {getActivity().startActivity(new Intent(getActivity(), ManageActivity.class).putExtra("action","manage").putExtra("tag","sale"));});
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeGinnyViewModel.class);
        // TODO: Use the ViewModel
    }

}