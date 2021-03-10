package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.databinding.RowOfferProductBinding;
import com.example.mydukaan.ginnymodal.offer.OfferList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.Databinding> {

    Context context;
    RowOfferProductBinding binding;
    List<OfferList> offerLists ;

    public OfferAdapter(List<OfferList> offerLists) {
        this.offerLists = offerLists;
    }

    @NonNull
    @NotNull
    @Override
    public OfferAdapter.Databinding onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = RowOfferProductBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Databinding(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OfferAdapter.Databinding holder, int position) {
            OfferList list = offerLists.get(position);
            binding.setList(list);
            binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return offerLists.size();
    }

    public class Databinding extends RecyclerView.ViewHolder {
        public Databinding(@NonNull @NotNull RowOfferProductBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
