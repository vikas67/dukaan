package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.databinding.RowMobileBrandsBinding;
import com.example.mydukaan.databinding.RowOrderProductsBinding;
import com.example.mydukaan.ginnymodal.mobilebrand.MobileBrand;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    RowOrderProductsBinding binding;
    Context context;
    List<MobileBrand> mobileBrands;

//    public OrderAdapter(List<MobileBrand> mobileBrands) {
//        this.mobileBrands = mobileBrands;
//    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = RowOrderProductsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull RowOrderProductsBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
