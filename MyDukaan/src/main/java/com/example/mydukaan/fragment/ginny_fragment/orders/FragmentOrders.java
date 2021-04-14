package com.example.mydukaan.fragment.ginny_fragment.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydukaan.R;
import com.example.mydukaan.adapter.OrdeProductAdapter;
import com.example.mydukaan.databinding.FragmentOrdersFragmentBinding;
import com.example.mydukaan.databinding.FragmentRegerEarnBinding;

public class FragmentOrders extends Fragment {
    FragmentOrdersFragmentBinding binding;
    private FragmentOrdersViewModel mViewModel;
    TextView all,pending;

    public static FragmentOrders newInstance() {
        return new FragmentOrders();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrdersFragmentBinding.inflate(inflater, container, false);
        binding.recycle.setAdapter(new OrdeProductAdapter());
//        textView= binding.getRoot().findViewWithTag("button");
//        binding.all.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.all.setTextColor(getResources().getColor(R.color.white));binding.all.setBackground(getResources().getDrawable(R.drawable.round_corner));});
//        binding.pending.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.pending.setTextColor(getResources().getColor(R.color.white));binding.pending.setBackground(getResources().getDrawable(R.drawable.round_corner));});
//        binding.shipped.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.shipped.setTextColor(getResources().getColor(R.color.white));binding.shipped.setBackground(getResources().getDrawable(R.drawable.round_corner));});
//        binding.accepted.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.accepted.setTextColor(getResources().getColor(R.color.white));binding.accepted.setBackground(getResources().getDrawable(R.drawable.round_corner));});
//        binding.rejected.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.rejected.setTextColor(getResources().getColor(R.color.white));binding.rejected.setBackground(getResources().getDrawable(R.drawable.round_corner));});
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentOrdersViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}