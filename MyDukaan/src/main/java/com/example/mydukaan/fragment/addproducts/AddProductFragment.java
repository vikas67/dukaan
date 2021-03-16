package com.example.mydukaan.fragment.addproducts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class AddProductFragment extends Fragment {

    LinearLayout choose, scan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
//        new CustomActionBar(getActivity(),getString(R.string.title_addproduct));
        InitializeFragment(view);
        actionbar();
        FragmentAction();


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
//        new DashboardActivity().HideBottomNavigation();
    }

    private void InitializeFragment(View view) {
        choose = view.findViewById(R.id.choose);

    }

    private void FragmentAction() {
        choose.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_addProductFragment_to_chooseCategoryFragment);
        });
        scan.setOnClickListener(v -> {
            IntentIntegrator.forSupportFragment(this).setBeepEnabled(true).setOrientationLocked(true).setCameraId(0).initiateScan();
        });
    }


    // Get the results:
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(100);
                Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void actionbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(getActivity());
        View v = inflator.inflate(R.layout.add_product_actionbar, null);
        scan = v.findViewById(R.id.scan);
        ((TextView) v.findViewById(R.id.title)).setText(getString(R.string.title_addproduct));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(v);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }


}