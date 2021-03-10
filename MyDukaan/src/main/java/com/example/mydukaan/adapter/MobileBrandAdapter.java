package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.databinding.RowMobileBrandsBinding;
import com.example.mydukaan.ginnymodal.mobilebrand.MobileBrand;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MobileBrandAdapter extends RecyclerView.Adapter<MobileBrandAdapter.Databinding> {

    RowMobileBrandsBinding binding;
    Context context;
    List<MobileBrand> mobileBrands;

    public MobileBrandAdapter(List<MobileBrand> mobileBrands) {
        this.mobileBrands = mobileBrands;
    }

    @NonNull
    @NotNull
    @Override
    public Databinding onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = RowMobileBrandsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Databinding(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Databinding holder, int position) {
        MobileBrand mobileBrand = mobileBrands.get(position);
        binding.setList(mobileBrand);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mobileBrands.size();
    }

    public class Databinding extends RecyclerView.ViewHolder {
        public Databinding(@NonNull @NotNull RowMobileBrandsBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
