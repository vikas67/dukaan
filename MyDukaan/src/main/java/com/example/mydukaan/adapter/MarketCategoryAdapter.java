package com.example.mydukaan.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.databinding.RowCategoryBinding;
import com.example.mydukaan.ginnymodal.category.CategoryList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MarketCategoryAdapter extends RecyclerView.Adapter<MarketCategoryAdapter.ViewBinding> {

    List<CategoryList> categoryLists;
    RowCategoryBinding binding;

    public MarketCategoryAdapter(List<CategoryList> categoryLists) {
        this.categoryLists = categoryLists;
    }

    @NonNull
    @NotNull
    @Override
    public ViewBinding onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = RowCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewBinding(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewBinding holder, int position) {
        CategoryList list =categoryLists.get(position);
        binding.setList(list);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    public class ViewBinding extends RecyclerView.ViewHolder {
        public ViewBinding(@NonNull @NotNull RowCategoryBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
