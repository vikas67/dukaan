package com.example.mydukaan.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinneyMainActivity;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinnyHomeActivity;
import com.example.mydukaan.databinding.ActivityDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    public static BottomNavigationView navView;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dashboard);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
        navController.navigate(R.id.navigation_account);

        binding.navView.getMenu().findItem(R.id.action_fifth_item).setChecked(true);
        binding.navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_ginnyhome:
                    startActivity(new Intent(this, GinnyHomeActivity.class));
                    break;
                case R.id.navigation_ginnymain:
                    startActivity(new Intent(this, GinneyMainActivity.class));
                    break;
                case R.id.action_second_item:
                    startActivity(new Intent(this, OrderActivity.class));
                    break;
                case R.id.action_fifth_item:
                    startActivity(new Intent(this, DashboardActivity.class));finish();
                    break;
            }
            return true;
        });

    }



    public void HideBottomNavigation() {
        navView.setVisibility(View.GONE);
    }

    public void ShowBottomNavigation() {
        navView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,GinnyHomeActivity.class));
    }

}