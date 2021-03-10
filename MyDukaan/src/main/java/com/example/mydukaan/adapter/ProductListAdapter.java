package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.R;
import com.example.mydukaan.databinding.RowProductlistLayoutBinding;
import com.example.mydukaan.modal.productList.ProductList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.Viewholder> {

    Context context;
    List<ProductList> productLists;
    RowProductlistLayoutBinding binding;

    public ProductListAdapter(List<ProductList> productLists) {
        this.productLists = productLists;
    }


    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        binding = RowProductlistLayoutBinding.inflate(LayoutInflater.from(context) , viewGroup , false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int i) {

        ProductList productList = productLists.get(i);
        binding.setProduct(productList);
        binding.executePendingBindings();


        binding.item.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_productFragment_to_editProductFragment);
        });


    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        CardView item;

        public Viewholder(@NonNull @NotNull RowProductlistLayoutBinding itemView) {
            super(itemView.getRoot());

        }
    }
}
