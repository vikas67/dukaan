package com.example.mydukaan.fragment.editproducts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydukaan.ActionBar.CustomActionBar;
import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.databinding.FragmentEditProductBinding;

import org.jetbrains.annotations.NotNull;


public class EditProductFragment extends Fragment {

    FragmentEditProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditProductBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new CustomActionBar(getActivity(),getString(R.string.title_editproduct));
        FragmentAction();
    }

    private void FragmentAction(){
        binding.addvarient.setOnClickListener(v -> {
            NavController navController= Navigation.findNavController(v);
            navController.navigate(R.id.action_editProductFragment_to_addVarientsFragment);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        new DashboardActivity().HideBottomNavigation();

    }

}