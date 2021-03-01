package com.example.mydukaan.fragment.varients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mydukaan.ActionBar.CustomActionBar;
import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.databinding.AddProductActionbarBinding;
import com.example.mydukaan.databinding.FragmentAddVarientsBinding;

import org.jetbrains.annotations.NotNull;


public class AddVarientsFragment extends Fragment {

    FragmentAddVarientsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddVarientsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new CustomActionBar(getActivity(), getString(R.string.title_addvarient));
        FragmentAction();
    }


    private void FragmentAction() {


        binding.bottom.setOnClickListener(v -> {
            binding.add.setVisibility(View.VISIBLE);
            binding.bottom.setVisibility(View.GONE);
            binding.top.setVisibility(View.VISIBLE);
        });
        binding.top.setOnClickListener(v -> {
            binding.add.setVisibility(View.GONE);
            binding.top.setVisibility(View.GONE);
            binding.bottom.setVisibility(View.VISIBLE);
            binding.dataview.setVisibility(View.GONE);
        });
        binding.add.setOnClickListener(v -> {
            binding.dataview.setVisibility(View.VISIBLE);
        });
        binding.bottomcolor.setOnClickListener(v -> {
            binding.addcolor.setVisibility(View.VISIBLE);
            binding.bottomcolor.setVisibility(View.GONE);
            binding.topcolor.setVisibility(View.VISIBLE);
        });
        binding.topcolor.setOnClickListener(v -> {
            binding.addcolor.setVisibility(View.GONE);
            binding.topcolor.setVisibility(View.GONE);
            binding.bottomcolor.setVisibility(View.VISIBLE);
            binding.dataviewcolor.setVisibility(View.GONE);
        });
        binding.addcolor.setOnClickListener(v -> {
            binding.dataviewcolor.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        new DashboardActivity().HideBottomNavigation();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new DashboardActivity().ShowBottomNavigation();
    }

}