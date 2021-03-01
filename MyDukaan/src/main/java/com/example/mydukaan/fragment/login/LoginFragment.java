package com.example.mydukaan.fragment.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.databinding.FragmentLoginBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;


public class LoginFragment extends Fragment {


    LinearLayout loginview, otpview;
    ImageView back;
    MaterialButton start;
    TextView other, next;
    BottomSheetDialog bottomSheetDialog;
    private String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "android.permission.READ_SMS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_CONTACTS", "android.permission.READ_CONTACTS", "android.permission.INTERNET", "android.permission.READ_PHONE_STATE"};
    FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        InitializeFragment(view);
        FragmentAction();
        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }

    }


    private void InitializeFragment(View view) {
        loginview = view.findViewById(R.id.login);
        otpview = view.findViewById(R.id.otp);
        start = view.findViewById(R.id.start);
        back = view.findViewById(R.id.back);
        other = view.findViewById(R.id.other);
        next = view.findViewById(R.id.next);
    }


    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        new DashboardActivity().HideBottomNavigation();
    }


    private void FragmentAction() {
        start.setOnClickListener(v -> {
            loginview.setVisibility(View.GONE);
            otpview.setVisibility(View.VISIBLE);
        });
        back.setOnClickListener(v -> {
            otpview.setVisibility(View.GONE);
            loginview.setVisibility(View.VISIBLE);
        });
        other.setOnClickListener(v -> {
            MoreOptionDialog();
        });
        next.setOnClickListener(v -> {
            startActivity(new Intent(getContext() , DashboardActivity.class));
        });
    }


    private void MoreOptionDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.other_option_dialog_layout);
        ImageView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(v -> dialog.dismiss());

        dialog.show();


    }


}