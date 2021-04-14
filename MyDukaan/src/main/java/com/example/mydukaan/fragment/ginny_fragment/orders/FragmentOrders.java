package com.example.mydukaan.fragment.ginny_fragment.orders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydukaan.R;
import com.example.mydukaan.adapter.OrdeProductAdapter;
import com.example.mydukaan.adapter.OrderHeaderAdapter;
import com.example.mydukaan.databinding.FragmentOrdersFragmentBinding;
import com.example.mydukaan.fragment.Orderfragment.AcceptedFragment;
import com.example.mydukaan.fragment.Orderfragment.AllFragment;
import com.example.mydukaan.fragment.Orderfragment.CancelledFragment;
import com.example.mydukaan.fragment.Orderfragment.DeliveredFragment;
import com.example.mydukaan.fragment.Orderfragment.FailedFragment;
import com.example.mydukaan.fragment.Orderfragment.PendingFragment;
import com.example.mydukaan.fragment.Orderfragment.RejectedFragment;
import com.example.mydukaan.fragment.Orderfragment.ShippedFragment;
import com.example.mydukaan.modal.OrderHeaderList;

import java.util.ArrayList;
import java.util.List;

public class FragmentOrders extends Fragment implements OrderHeaderAdapter.OrderStatus {
    FragmentOrdersFragmentBinding binding;
    private FragmentOrdersViewModel mViewModel;
    private static final String TAG = "FragmentOrders";

    public static FragmentOrders newInstance() {
        return new FragmentOrders();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrdersFragmentBinding.inflate(inflater, container, false);
//        binding.recycle.setAdapter(new OrdeProductAdapter());
//        textView= binding.getRoot().findViewWithTag("button");
//        binding.all.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.all.setTextColor(getResources().getColor(R.color.white));binding.all.setBackground(getResources().getDrawable(R.drawable.round_corner));});
//        binding.pending.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.pending.setTextColor(getResources().getColor(R.color.white));binding.pending.setBackground(getResources().getDrawable(R.drawable.round_corner));});
//        binding.shipped.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.shipped.setTextColor(getResources().getColor(R.color.white));binding.shipped.setBackground(getResources().getDrawable(R.drawable.round_corner));});
//        binding.accepted.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.accepted.setTextColor(getResources().getColor(R.color.white));binding.accepted.setBackground(getResources().getDrawable(R.drawable.round_corner));});
//        binding.rejected.setOnClickListener(v -> {textView.setBackground(getResources().getDrawable(R.drawable.transparent_round_corner));binding.rejected.setTextColor(getResources().getColor(R.color.white));binding.rejected.setBackground(getResources().getDrawable(R.drawable.round_corner));});
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentOrdersViewModel.class);
        // TODO: Use the ViewModel

        binding.headerRecyclerView.setAdapter(new OrderHeaderAdapter(getorderHeaderlist(), this , requireContext()));
        lodaFragment(new AllFragment());


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
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
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
            getFragmentManager().beginTransaction().replace(R.id.dashBoardFrameload, fragment).addToBackStack(null).commit();
    }

}























