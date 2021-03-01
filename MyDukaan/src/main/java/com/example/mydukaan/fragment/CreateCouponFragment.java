package com.example.mydukaan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mydukaan.databinding.FragmentCreateCouponBinding;

import org.jetbrains.annotations.NotNull;

public class CreateCouponFragment extends Fragment {

    FragmentCreateCouponBinding binding;
    private static final String TAG = "CreateCouponFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreateCouponBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.PercentRadio.setOnClickListener(v -> {
            binding.FlatDiscountRadio.setChecked(false);
            binding.PercentTextinput.setVisibility(View.VISIBLE);
            binding.MaximumDiscountTextinput.setVisibility(View.VISIBLE);
            binding.DiscountAmtTextinput.setVisibility(View.GONE);
        });

        binding.FlatDiscountRadio.setOnClickListener(v -> {
            binding.PercentRadio.setChecked(false);
            binding.PercentTextinput.setVisibility(View.INVISIBLE);
            binding.MaximumDiscountTextinput.setVisibility(View.GONE);
            binding.DiscountAmtTextinput.setVisibility(View.VISIBLE);
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}