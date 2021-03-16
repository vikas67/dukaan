package com.example.mydukaan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.R;
import com.example.mydukaan.databinding.FragmentDiscountCouponsBinding;

import org.jetbrains.annotations.NotNull;


public class DiscountCouponsFragment extends Fragment {

    NavController navController;
    FragmentDiscountCouponsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDiscountCouponsBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        new DashboardActivity().HideBottomNavigation();

        navController = Navigation.findNavController(view);

        binding.CreateCouponBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_discountCouponsFragment_to_createCouponFragment);
        });



    }




}






















