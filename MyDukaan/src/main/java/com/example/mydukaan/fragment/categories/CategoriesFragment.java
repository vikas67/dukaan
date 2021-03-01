package com.example.mydukaan.fragment.categories;

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

import com.example.mydukaan.R;
import com.example.mydukaan.adapter.CategoryListAdapter;
import com.example.mydukaan.databinding.FragmentCategoriesBinding;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

public class CategoriesFragment extends Fragment {

    RecyclerView recyclerView;
    MaterialButton addcat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_categories, container, false);
        InitializeFragment(view);
        FragmentAction();
        SetFragmentData();
        return view;
    }


    private void SetFragmentData(){
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new CategoryListAdapter(getActivity()));

    }
    private void InitializeFragment(View view){
        recyclerView= view.findViewById(R.id.recycle);
        addcat= view.findViewById(R.id.addcat);
    }
    private void FragmentAction(){
        addcat.setOnClickListener(v -> {
            NavController navController= Navigation.findNavController(v);
            navController.navigate(R.id.action_productFragment_to_addNewCategoryFragment);
        });
    }

}