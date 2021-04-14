package com.example.mydukaan.fragment.Orderfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mydukaan.adapter.OrdeProductAdapter;
import com.example.mydukaan.databinding.FragmentAllBinding;

import org.jetbrains.annotations.NotNull;


public class AllFragment extends Fragment {

    FragmentAllBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAllBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.AllOrderRecyclerView.setAdapter(new OrdeProductAdapter());

    }
}