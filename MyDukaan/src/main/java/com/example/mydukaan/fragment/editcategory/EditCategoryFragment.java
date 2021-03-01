package com.example.mydukaan.fragment.editcategory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydukaan.ActionBar.CustomActionBar;
import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.adapter.ProductListAdapter;
import com.example.mydukaan.databinding.FragmentEditCategoryBinding;

import org.jetbrains.annotations.NotNull;


public class EditCategoryFragment extends Fragment {


    FragmentEditCategoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditCategoryBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new CustomActionBar(getActivity(),"your Category");
        FragmentAction();
        SetFragmentData();
    }

    private void SetFragmentData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycle.setLayoutManager(linearLayoutManager);
        binding.recycle.setAdapter(new ProductListAdapter());

    }


    private void FragmentAction() {
        binding.addnew.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_editCategoryFragment_to_addProductFragment);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        new DashboardActivity().HideBottomNavigation();
    }



}