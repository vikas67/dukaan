package com.example.mydukaan.fragment.choosecategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.adapter.ChooseCategoryAdapter;
import com.example.mydukaan.databinding.FragmentChooseCategoryBinding;

import org.jetbrains.annotations.NotNull;

public class ChooseCategoryFragment extends Fragment {

    FragmentChooseCategoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChooseCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentAction();
        SetFragmentData();
    }

    private void SetFragmentData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycle.setLayoutManager(linearLayoutManager);
        binding.recycle.setAdapter(new ChooseCategoryAdapter(getActivity()));

    }

    private void FragmentAction() {
    }

    @Override
    public void onStart() {
        super.onStart();
        new DashboardActivity().HideBottomNavigation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}