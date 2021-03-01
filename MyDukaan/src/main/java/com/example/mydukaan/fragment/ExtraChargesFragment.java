package com.example.mydukaan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.R;
import com.example.mydukaan.databinding.FragmentExtraChargesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

public class ExtraChargesFragment extends Fragment {

    FragmentExtraChargesBinding binding;
    BottomSheetDialog bottomSheetDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExtraChargesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.CreateExtraChargeTextView.setOnClickListener(v -> {
            bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.DialogStyle);

            View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.row_extra_charge_bottom_sheet,
                    null);
            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        new DashboardActivity().HideBottomNavigation();
    }
}