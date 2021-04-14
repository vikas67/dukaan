package com.example.mydukaan.activity.ginnyActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinnyHomeActivity;
import com.example.mydukaan.databinding.ActivityGinneyMainBinding;
import com.example.mydukaan.databinding.ActivityOpenFragmentBinding;
import com.example.mydukaan.fragment.ginny_fragment.orders.FragmentOrders;
import com.example.mydukaan.fragment.language.ChangeLanguageFragment;
import com.example.mydukaan.fragment.product.ProductFragment;
import com.example.mydukaan.fragment.referearn.RegerEarnFragment;
import com.example.mydukaan.fragment.share.ShareFragment;
import com.example.mydukaan.fragment.ui.Manage.ManageFragment;
import com.example.mydukaan.fragment.ui.dashboard.OrderDashboardFragment;
import com.example.mydukaan.persionalinfo.PersionalInfoFragment;

public class OpenFragmentActivity extends AppCompatActivity {
    ActivityOpenFragmentBinding  binding;
    FragmentTransaction transaction;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOpenFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        transaction = getSupportFragmentManager().beginTransaction();
        setFragment(getIntent().getStringExtra("action"));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setFragment(String action){
        switch (action){
            case "dashboard1":
                transaction.replace(R.id.framelayout, new OrderDashboardFragment());
                break;
            case "product":
                transaction.replace(R.id.framelayout, new ProductFragment());
                break;
            case "refer":
                transaction.replace(R.id.framelayout, new RegerEarnFragment());
                break;
            case "share":
                transaction.replace(R.id.framelayout, new ShareFragment());
                break;
            case "language":
                transaction.replace(R.id.framelayout, new ChangeLanguageFragment());
                break;
            case "order":
                transaction.replace(R.id.framelayout, new FragmentOrders());
                break;
            case "manage":
                transaction.replace(R.id.framelayout, new ManageFragment());
                break;
            case "document":
                transaction.replace(R.id.framelayout, new PersionalInfoFragment());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, GinnyHomeActivity.class));
    }


}