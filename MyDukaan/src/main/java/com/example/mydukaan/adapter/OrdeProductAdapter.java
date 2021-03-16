package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.R;
import com.example.mydukaan.databinding.RowOrderProductlayoutBinding;
import com.example.mydukaan.databinding.RowOrderProductsBinding;
import com.example.mydukaan.ginnymodal.mobilebrand.MobileBrand;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrdeProductAdapter extends RecyclerView.Adapter<OrdeProductAdapter.ViewHolder> {
    RowOrderProductlayoutBinding binding;
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
        binding = RowOrderProductlayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if(position==3) {
            holder.itemView.findViewById(R.id.button).setBackgroundTintList(context.getResources().getColorStateList(R.color.very_light_red));
            holder.button.setTextColor(context.getResources().getColor(R.color.white));
            holder.button.setText("Cencelled");
        }else if(position==4 || position==5){
            holder.itemView.findViewById(R.id.button).setBackgroundTintList(context.getResources().getColorStateList(R.color.very_light_green));
            holder.button.setText("PAID");
        }else {
            holder.itemView.findViewById(R.id.button).setBackgroundTintList(context.getResources().getColorStateList(R.color.market_back));
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        public ViewHolder(@NonNull @NotNull RowOrderProductlayoutBinding itemView) {
            super(itemView.getRoot());
            button= binding.button;
        }
    }
}
