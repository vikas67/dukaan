package com.example.mydukaan.fragment.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydukaan.fragment.HomeOrderFragment.AcceptedHomeFragment;
import com.example.mydukaan.fragment.HomeOrderFragment.PendingHomeFragment;
import com.example.mydukaan.fragment.HomeOrderFragment.ShippedHomeFragment;
import com.example.mydukaan.R;
import com.example.mydukaan.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.pendingTxt.setOnClickListener(v -> {
            loadFragment(new PendingHomeFragment());
        });
        binding.acceptedTxt.setOnClickListener(v -> {
            loadFragment(new AcceptedHomeFragment());
        });
        binding.shippedTxt.setOnClickListener(v -> {
            loadFragment(new ShippedHomeFragment());
        });

        binding.lifeTime.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(requireContext(), binding.viewAllOrder);
            popup.getMenuInflater()
                    .inflate(R.menu.life_time_menu, popup.getMenu());
            popup.show();
        });
    }


    @Override
    public boolean onContextItemSelected(@NonNull @NotNull MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        loadFragment(new PendingHomeFragment());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void loadFragment(Fragment fragment) {
        if (fragment != null)
            getChildFragmentManager().beginTransaction().replace(R.id.mainFragmentLayout, fragment).addToBackStack(null).commit();
    }

    private void lodaFragment(Fragment fragment) {
        if (fragment != null)
            getChildFragmentManager().beginTransaction().replace(R.id.mainFragmentLayout, fragment).addToBackStack(null).commit();
    }


}




























