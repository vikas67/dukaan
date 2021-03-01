package com.example.mydukaan.fragment.ui.account;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.example.mydukaan.activity.EditBusinessDetailActivity;
import com.example.mydukaan.activity.Login;
import com.example.mydukaan.databinding.AccountFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;
    private static final String TAG = "AccountFragment";
    AccountFragmentBinding binding;
    NavController navController;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AccountFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        FragmentAction();
    }

    private void FragmentAction() {
        binding.editDetail.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), EditBusinessDetailActivity.class));
        });
        binding.referearn.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_account_to_regerEarnFragment);
        });
        binding.share.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_account_to_shareFragment);
        });
        binding.language.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_account_to_changeLanguageFragment);
        });
        binding.pc.setOnClickListener(v -> {
            ForPC();
        });
        binding.support.setOnClickListener(v -> {
            supportdialog();

        });
        binding.whatsapp.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                SnackbarMessge(getString(R.string.w_on) , binding.getRoot());
            }else {
                SnackbarMessge(getString(R.string.w_off), binding.getRoot());
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        new DashboardActivity().ShowBottomNavigation();
    }


    private void ForPC() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_accesspc_layout);
        ImageView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void SnackbarMessge(String s , View v) {
        Snackbar snackbar;
        snackbar = Snackbar.make(v, "Message", Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.green));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setText(s);
        snackbar.show();
    }

    private void supportdialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_supportlayout);
        ImageView close = dialog.findViewById(R.id.close);
        LinearLayout liveChat = dialog.findViewById(R.id.liveChat);
        liveChat.setOnClickListener(v -> {startActivity(new Intent(requireContext() , Login.class));});
        close.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }


}































