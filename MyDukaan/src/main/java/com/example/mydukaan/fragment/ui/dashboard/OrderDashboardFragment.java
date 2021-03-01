package com.example.mydukaan.fragment.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mydukaan.R;
import com.example.mydukaan.adapter.OrderHeaderAdapter;
import com.example.mydukaan.databinding.FragmentOrderDashboardBinding;
import com.example.mydukaan.fragment.Orderfragment.AcceptedFragment;
import com.example.mydukaan.fragment.Orderfragment.AllFragment;
import com.example.mydukaan.fragment.Orderfragment.CancelledFragment;
import com.example.mydukaan.fragment.Orderfragment.DeliveredFragment;
import com.example.mydukaan.fragment.Orderfragment.FailedFragment;
import com.example.mydukaan.fragment.Orderfragment.PendingFragment;
import com.example.mydukaan.fragment.Orderfragment.RejectedFragment;
import com.example.mydukaan.fragment.Orderfragment.ShippedFragment;
import com.example.mydukaan.modal.OrderHeaderList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class OrderDashboardFragment extends Fragment implements OrderHeaderAdapter.OrderStatus {

    FragmentOrderDashboardBinding binding;
    private static final String TAG = "OrderDashboardFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_order_dashboard, container, false);
        binding = FragmentOrderDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        binding.headerRecyclerView.setAdapter(new OrderHeaderAdapter(getorderHeaderlist(), this));
        lodaFragment(new AllFragment());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public List<OrderHeaderList> getorderHeaderlist() {
        List<OrderHeaderList> list = new ArrayList<>();
        list.add(new OrderHeaderList(0, "All"));
        list.add(new OrderHeaderList(1, "Pending"));
        list.add(new OrderHeaderList(2, "Accepted"));
        list.add(new OrderHeaderList(3, "Rejected"));
        list.add(new OrderHeaderList(4, "Shipped"));
        list.add(new OrderHeaderList(5, "Cancelled"));
        list.add(new OrderHeaderList(6, "Delivered"));
        list.add(new OrderHeaderList(7, "Failed"));
        return list;
    }

    @Override
    public void getOrderHeaderId(OrderHeaderList list) {
        Log.e(TAG, "getOrderHeaderId: " + list);
        int id = list.getId();
        Fragment fragment = null;
        switch (id) {
            case 0:
                fragment = new AllFragment();
                break;
            case 1:
                fragment = new PendingFragment();
                break;
            case 2:
                fragment = new AcceptedFragment();
                break;
            case 3:
                fragment = new RejectedFragment();
                break;
            case 4:
                fragment = new ShippedFragment();
                break;
            case 5:
                fragment = new CancelledFragment();
                break;
            case 6:
                fragment = new DeliveredFragment();
                break;
            case 7:
                fragment = new FailedFragment();
                break;
            default:
                fragment = null;

        }
        lodaFragment(fragment);
    }

    private void lodaFragment(Fragment fragment) {
        if (fragment != null)
            getChildFragmentManager().beginTransaction().replace(R.id.dashBoardFrameload, fragment).addToBackStack(null).commit();
    }


}