package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.databinding.RowGadgetsBinding;
import com.example.mydukaan.databinding.RowOfferProductBinding;
import com.example.mydukaan.ginnymodal.gadgets.Gadgets;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GadgetsAdapter extends RecyclerView.Adapter<GadgetsAdapter.Databinding> {

    RowGadgetsBinding binding;
    Context context;
    List<Gadgets> gadgetsList;

    public GadgetsAdapter(List<Gadgets> gadgetsList) {
        this.gadgetsList = gadgetsList;
    }

    @NonNull
    @NotNull
    @Override
    public Databinding onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = RowGadgetsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Databinding(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Databinding holder, int position) {
        Gadgets list = gadgetsList.get(position);
        binding.setList(list);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return gadgetsList.size();
    }

    public class Databinding extends RecyclerView.ViewHolder {
        public Databinding(@NonNull @NotNull RowGadgetsBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
