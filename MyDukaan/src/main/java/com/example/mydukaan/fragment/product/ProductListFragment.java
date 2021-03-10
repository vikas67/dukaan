package com.example.mydukaan.fragment.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.R;
import com.example.mydukaan.adapter.ProductListAdapter;
import com.example.mydukaan.databinding.FragmentProductListBinding;

import org.jetbrains.annotations.NotNull;

public class ProductListFragment extends Fragment {

    FragmentProductListBinding binding;
    ProductListViewModel productListViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productListViewModel = new ViewModelProvider(requireActivity()).get(ProductListViewModel.class);

        FragmentAction();
        SetFragmentData();
    }

    private void SetFragmentData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycle.setLayoutManager(linearLayoutManager);

        productListViewModel.getProducts().observe(requireActivity() , productLists -> {
            binding.recycle.setAdapter(new ProductListAdapter(productLists));
        });


    }


    private void FragmentAction() {
        binding.addnew.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_productFragment_to_addProductFragment);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}