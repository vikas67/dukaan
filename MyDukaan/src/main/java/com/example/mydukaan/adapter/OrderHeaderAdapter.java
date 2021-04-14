package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.R;
import com.example.mydukaan.modal.OrderHeaderList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderHeaderAdapter extends RecyclerView.Adapter<OrderHeaderAdapter.OrderBinding> {

    List<OrderHeaderList> getorderHeaderlist;
    //    RowOrderHeaderBinding rowOrderHeaderBinding;
    OrderStatus orderStatus;
    private int currentSelectedPosition = RecyclerView.NO_POSITION;
    Context context;
    boolean first = true;

    public OrderHeaderAdapter(List<OrderHeaderList> getorderHeaderlist, OrderStatus orderStatus, Context context) {
        this.getorderHeaderlist = getorderHeaderlist;
        this.orderStatus = orderStatus;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public OrderBinding onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new OrderBinding(LayoutInflater.from(context).inflate(R.layout.row_order_header, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderBinding holder, int position) {
        OrderHeaderList list = getorderHeaderlist.get(position);

        holder.mainFragmentLayout.setOnClickListener(v -> {
            orderStatus.getOrderHeaderId(getorderHeaderlist.get(position));
            currentSelectedPosition = position;
            notifyDataSetChanged();
        });

        if (currentSelectedPosition == position) {
            holder.all.setBackground(context.getResources().getDrawable(R.drawable.round_corner));
            holder.all.setTextColor(context.getColor(R.color.white));
        } else {
            holder.all.setBackground(context.getResources().getDrawable(R.drawable.transparent_round_corner));
            holder.all.setTextColor(context.getColor(R.color.black));
        }
        if (first) {
            holder.all.setBackground(context.getResources().getDrawable(R.drawable.round_corner));
            holder.all.setTextColor(context.getColor(R.color.white));
        }
        first = false;
        holder.all.setText(list.getName());

    }

    @Override
    public int getItemCount() {
        return getorderHeaderlist.size();
    }

    public class OrderBinding extends RecyclerView.ViewHolder {

        TextView all;
        LinearLayout mainFragmentLayout;

        public OrderBinding(@NonNull @NotNull View itemView) {
            super(itemView);
            all = itemView.findViewById(R.id.all);
            mainFragmentLayout = itemView.findViewById(R.id.mainFragmentLayout);
        }

    }

    public interface OrderStatus {
        void getOrderHeaderId(OrderHeaderList list);
    }



}




















