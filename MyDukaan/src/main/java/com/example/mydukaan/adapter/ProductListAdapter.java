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

import org.jetbrains.annotations.NotNull;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.Viewholder> {

    Context context;

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.row_productlist_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int i) {
        holder.item.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_productFragment_to_editProductFragment);
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        CardView item;

        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
        }
    }
}
