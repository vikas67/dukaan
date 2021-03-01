package com.example.mydukaan.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.databinding.RowOrderHeaderBinding;
import com.example.mydukaan.modal.OrderHeaderList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderHeaderAdapter extends RecyclerView.Adapter<OrderHeaderAdapter.OrderBinding> {

    List<OrderHeaderList> getorderHeaderlist;
    RowOrderHeaderBinding rowOrderHeaderBinding;
    OrderStatus orderStatus;

    public OrderHeaderAdapter(List<OrderHeaderList> getorderHeaderlist, OrderStatus orderStatus) {
        this.getorderHeaderlist = getorderHeaderlist;
        this.orderStatus = orderStatus;
    }

    @NonNull
    @NotNull
    @Override
    public OrderBinding onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        rowOrderHeaderBinding = RowOrderHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        rowOrderHeaderBinding.setOrderIdInterface(orderStatus);
        return new OrderBinding(rowOrderHeaderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderBinding holder, int position) {
        OrderHeaderList list = getorderHeaderlist.get(position);
        rowOrderHeaderBinding.setOrderTitle(list);
        rowOrderHeaderBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return getorderHeaderlist.size();
    }

    public class OrderBinding extends RecyclerView.ViewHolder {

        public OrderBinding(@NonNull @NotNull RowOrderHeaderBinding itemView) {
            super(itemView.getRoot());
        }

    }

    public interface OrderStatus {
        void getOrderHeaderId(OrderHeaderList list);
    }



}




















