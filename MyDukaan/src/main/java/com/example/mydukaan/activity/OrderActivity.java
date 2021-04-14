package com.example.mydukaan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinneyMainActivity;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinnyHomeActivity;
import com.example.mydukaan.databinding.ActivityAccountBinding;
import com.example.mydukaan.databinding.ActivityDashboardBinding;
import com.example.mydukaan.fragment.ui.account.AccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrderActivity extends AppCompatActivity {
    ActivityAccountBinding binding;
    public static BottomNavigationView navView;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dashboard);
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
        binding.navView.getMenu().findItem(R.id.action_second_item).setChecked(true);
        binding.navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_ginnyhome:
                    startActivity(new Intent(this, GinnyHomeActivity.class));
                    break;
                case R.id.navigation_ginnymain:
                    startActivity(new Intent(this, GinneyMainActivity.class));
                    break;
                case R.id.action_second_item:
                    startActivity(new Intent(this, OrderActivity.class));finish();
                    break;
                case R.id.action_fifth_item:
                    startActivity(new Intent(this, DashboardActivity.class));
                    break;
            }
            return true;
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,GinnyHomeActivity.class));
    }
}