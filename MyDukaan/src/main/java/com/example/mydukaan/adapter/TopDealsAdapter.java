package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.databinding.RowTopDealBinding;
import com.example.mydukaan.ginnymodal.topDeals.TopDealsList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TopDealsAdapter extends RecyclerView.Adapter<TopDealsAdapter.Databinding> {

    Context context;
    RowTopDealBinding binding;
    List<TopDealsList> topDealsLists;

    public TopDealsAdapter(List<TopDealsList> topDealsLists) {
        this.topDealsLists = topDealsLists;
    }

    @NonNull
    @NotNull
    @Override
    public Databinding onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = RowTopDealBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Databinding(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Databinding holder, int position) {
        TopDealsList list = topDealsLists.get(position);
        binding.setList(list);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return topDealsLists.size();
    }

    public class Databinding extends RecyclerView.ViewHolder {
        public Databinding(@NonNull @NotNull RowTopDealBinding itemView) {
            super(itemView.getRoot());
        }
    }
}















