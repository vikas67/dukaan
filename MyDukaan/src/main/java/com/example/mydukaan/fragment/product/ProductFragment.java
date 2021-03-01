package com.example.mydukaan.fragment.product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.adapter.ViewPagerAdapter;
import com.example.mydukaan.databinding.FragmentProductBinding;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;


public class ProductFragment extends Fragment {

    FragmentProductBinding binding;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        binding = FragmentProductBinding.inflate(inflater , R.layout.fragment_product , container );
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SetFragmentData();
        FragmentAction();

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), getActivity(), binding.tablayout.getTabCount());
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.viewPager.setCurrentItem(0);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
    }

    private void FragmentAction() {
        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()) {
                    case "Products":
                        binding.swap.setVisibility(View.GONE);
                        binding.search.setVisibility(View.VISIBLE);
                        binding.viewPager.setCurrentItem(0);
                        break;
                    case "Categories":
                        binding.swap.setVisibility(View.VISIBLE);
                        binding.search.setVisibility(View.GONE);
                        binding.viewPager.setCurrentItem(1);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("tab", String.valueOf(tab));
            }
        });

        binding.search.setOnClickListener(v -> {
            binding.catalogueview.setVisibility(View.GONE);
            binding.searchview.setVisibility(View.VISIBLE);
        });
        binding.cencel.setOnClickListener(v -> {
            binding.catalogueview.setVisibility(View.VISIBLE);
            binding.searchview.setVisibility(View.GONE);
        });
    }

    private void SetFragmentData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        new DashboardActivity().ShowBottomNavigation();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }


}