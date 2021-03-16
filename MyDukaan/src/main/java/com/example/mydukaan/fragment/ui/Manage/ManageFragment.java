package com.example.mydukaan.fragment.ui.Manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.R;
import com.example.mydukaan.databinding.FragmentManageBinding;

import org.jetbrains.annotations.NotNull;


public class ManageFragment extends Fragment {

    FragmentManageBinding binding;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.marketDesignsCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_manageFragment_to_marketingDesignsFragment);
        });

//        binding.onlinePaymentCrd.setOnClickListener(v -> {
//            navController.navigate(R.id.action_manageFragment_to_onlinePaymentFragment);
//        });

        binding.discountCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_manageFragment_to_discountCouponsFragment);
        });
        binding.extraChargheCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_manageFragment_to_extraChargesFragment);
        });

    }

    @Override
    public void onStart() {
        super.onStart();
//        new DashboardActivity().ShowBottomNavigation();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}