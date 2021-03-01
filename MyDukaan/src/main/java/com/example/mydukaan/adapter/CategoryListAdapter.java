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

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.Viewholder>{
    Context context;

    public CategoryListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_categorylist_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {
      holder.item.setOnClickListener(v -> {
          NavController navController= Navigation.findNavController(v);
          navController.navigate(R.id.action_productFragment_to_editCategoryFragment);
      });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        CardView item;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            item=itemView.findViewById(R.id.item);
        }
    }
}

