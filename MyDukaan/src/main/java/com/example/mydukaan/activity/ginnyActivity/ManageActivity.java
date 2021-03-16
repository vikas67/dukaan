package com.example.mydukaan.activity.ginnyActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.activity.OrderActivity;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinneyMainActivity;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinnyHomeActivity;
import com.example.mydukaan.databinding.ActivityDashboardBinding;
import com.example.mydukaan.databinding.ActivityManageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManageActivity extends AppCompatActivity {
    public static BottomNavigationView navView;
    NavController navController;
    ActivityManageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.manageFragment);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
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