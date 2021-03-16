package com.example.mydukaan.activity.ginnyActivity.ginny_main_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.activity.OrderActivity;
import com.example.mydukaan.activity.ProductActivity;
import com.example.mydukaan.activity.ginnyActivity.ManageActivity;
import com.example.mydukaan.activity.ginnyActivity.OpenFragmentActivity;
import com.example.mydukaan.activity.ginnyActivity.otp.OtpActivity;
import com.example.mydukaan.activity.ginnyActivity.signup.SignupActivity;
import com.example.mydukaan.activity.ginnyActivity.welcome.WelcomeActivity;
import com.example.mydukaan.databinding.ActivityGinneyMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class GinneyMainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    ActivityGinneyMainBinding binding;
    DrawerLayout drawer;
    public static BottomNavigationView navView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGinneyMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        drawer = binding.drawerLayout;
        binding.navView.setNavigationItemSelectedListener(this);
//        navView = findViewById(R.id.bottomnav1);
//        navController = Navigation.findNavController(this, R.id.navigation_ginnymain);
//        NavigationUI.setupWithNavController(binding.navView, navController);
//        NavigationUI.setupActionBarWithNavController(this, navController);
        binding.appBarMain.bottomnav1.getMenu().findItem(R.id.navigation_ginnymain).setChecked(true);
        binding.appBarMain.bottomnav1.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_ginnyhome:
                    startActivity(new Intent(this, GinnyHomeActivity.class));
                    break;
                case R.id.navigation_ginnymain:
                    startActivity(new Intent(this, GinneyMainActivity.class));finish();
                    break;
                case R.id.action_second_item:
                    startActivity(new Intent(this, OrderActivity.class));
                    break;
                case R.id.action_fifth_item:
                    startActivity(new Intent(this, DashboardActivity.class));
                    break;
            }
            return true;
        });

      ActivityAction();
    }

    private void ActivityAction() {
        binding.appBarMain.sideBarIcon.setOnClickListener(v -> {
            openDrawer();
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main1);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void openDrawer() {

        if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else binding.drawerLayout.openDrawer(GravityCompat.START);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.login:
                startActivity(new Intent(this, WelcomeActivity.class));
                break;
            case R.id.otp:
                startActivity(new Intent(this, OtpActivity.class));
                break;
            case R.id.signin:
                startActivity(new Intent(this, SignupActivity.class));
                break;
            case R.id.dashboard1:
                startActivity(new Intent(this, OpenFragmentActivity.class).putExtra("action","dashboard1"));
                break;
            case R.id.product:
                startActivity(new Intent(this, ProductActivity.class));
                break;
            case R.id.refer:
                startActivity(new Intent(this, OpenFragmentActivity.class).putExtra("action","refer"));
                break;
            case R.id.share:
                startActivity(new Intent(this, OpenFragmentActivity.class).putExtra("action","share"));
                break;
            case R.id.language:
                startActivity(new Intent(this, OpenFragmentActivity.class).putExtra("action","language"));
                break;
            case R.id.order:
                startActivity(new Intent(this, OpenFragmentActivity.class).putExtra("action","order"));
                break;
            case R.id.document:
                startActivity(new Intent(this, OpenFragmentActivity.class).putExtra("action","document"));
                break;
            case R.id.manage:
                startActivity(new Intent(this, ManageActivity.class));
                break;

        }
        return false;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,GinnyHomeActivity.class));
    }
}























