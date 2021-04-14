package com.example.mydukaan.activity.ginnyActivity.ginny_main_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.activity.OrderActivity;
import com.example.mydukaan.activity.ProductActivity;
import com.example.mydukaan.activity.ginnyActivity.ManageActivity;
import com.example.mydukaan.activity.ginnyActivity.OpenFragmentActivity;
import com.example.mydukaan.activity.ginnyActivity.otp.OtpActivity;
import com.example.mydukaan.activity.ginnyActivity.signup.SignupActivity;
import com.example.mydukaan.activity.ginnyActivity.welcome.WelcomeActivity;
import com.example.mydukaan.databinding.ActivityGinnyHomeBinding;
import com.example.mydukaan.fragment.product.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class GinnyHomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    ActivityGinnyHomeBinding binding;
    DrawerLayout drawer;
    BottomNavigationView navView;
    NavController navController;
    boolean state= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGinnyHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.navView.setNavigationItemSelectedListener(this);
        binding.appBarMain.bottomnav.getMenu().findItem(R.id.navigation_ginnyhome).setChecked(true);
        binding.appBarMain.bottomnav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_ginnyhome:
                    startActivity(new Intent(this, GinnyHomeActivity.class));finish();
                    break;
                case R.id.navigation_ginnymain:
                    startActivity(new Intent(this, GinneyMainActivity.class));
                    break;
                case R.id.action_second_item:
                    startActivity(new Intent(this, OpenFragmentActivity.class).putExtra("action","order"));
//                    startActivity(new Intent(this, OrderActivity.class));
                    break;
                case R.id.action_fifth_item:
                    startActivity(new Intent(this, DashboardActivity.class));
                    break;

            }
            return true;
        });

        binding.appBarMain.sideBarIcon.setOnClickListener(v -> {openDrawer();});
    }

    private void openDrawer() {

        if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else binding.drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (state) {
//            super.onBackPressed();
//            return;
//        }
//        this.state = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//        new Handler().postDelayed(() -> state=false, 2000);
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
//                startActivity(new Intent(this, OpenFragmentActivity.class).putExtra("action","order"));
                startActivity(new Intent(this, OrderActivity.class));
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
}