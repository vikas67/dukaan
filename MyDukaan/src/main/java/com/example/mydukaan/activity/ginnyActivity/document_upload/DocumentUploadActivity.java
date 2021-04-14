package com.example.mydukaan.activity.ginnyActivity.document_upload;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.example.mydukaan.DistanceCalculation.CalculateDistance;
import com.example.mydukaan.Interface.RightDrawableOnTouchListener;
import com.example.mydukaan.R;
import com.example.mydukaan.activity.ginnyActivity.ginny_main_activity.GinnyHomeActivity;
import com.example.mydukaan.adapter.DocumentAdapter;
import com.example.mydukaan.databinding.ActivityDocumentUploadBinding;
import com.example.mydukaan.modal.DocumentList;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DocumentUploadActivity extends AppCompatActivity {


    Boolean type = false;
    String name;

    List<DocumentList> documentLists = new ArrayList<>();
    private LocationManager lm;
    LatLng pos;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    ActivityDocumentUploadBinding binding;
    MaterialButton setlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle(getString(R.string.profile));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        FragmentAction();

        LatLng start = new LatLng(43.4129238, -79.4771472);
        LatLng end = new LatLng(44.4129238, -80.4771472);
        CalculateDistance calculateDistance = new CalculateDistance(this);
        Log.e("diatance", String.valueOf(calculateDistance.getdistance(start, end)));


        binding.saveBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, GinnyHomeActivity.class));
        });

    }


//    private void InitializeFragment() {
//        camera = view.findViewById(R.id.camera);
//        adddocument = view.findViewById(R.id.adddocument);
//        storeimage = view.findViewById(R.id.store);
//        document = view.findViewById(R.id.recycle);
//        arrdress = view.findViewById(R.id.arrdress);
//    }


    private void FragmentAction() {
        binding.camera.setOnClickListener(v -> {
            type = true;
            ImagePicker.Companion.with(this)
                    .cameraOnly()
                    .cropSquare()
                    .start();

        });

        binding.adddocument.setOnClickListener(v -> {
            type = false;
            ImagePicker.Companion.with(this).crop().compress(1024).maxResultSize(1080, 1080).start();
        });

        binding.arrdress.setOnTouchListener(new RightDrawableOnTouchListener(binding.arrdress) {
            @Override
            public boolean onDrawableTouch(MotionEvent event) {
//                OpenMapDialog();
                return false;
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (type) {
                Uri fileUri = data.getData();
                binding.camera.setImageURI(fileUri);
            } else {
                Uri fileUri = data.getData();
                documentLists.add(new DocumentList(name, fileUri));
                DocumentAdapter documentAdapter = new DocumentAdapter(this, documentLists, binding.recycle);
                binding.recycle.setAdapter(documentAdapter);
                binding.recycle.setVisibility(View.VISIBLE);
            }
        }

    }


    private String OpenMapDialog() {
        Dialog mDialogMaps = new Dialog(this);
        mDialogMaps.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialogMaps.setContentView(R.layout.dialog_map_layour);
        setlocation = mDialogMaps.findViewById(R.id.setlocation);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = mDialogMaps.getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        setlocation.setOnClickListener(v -> {
//            getMyLocationAddress(pos);
            mDialogMaps.cancel();
        });

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        mDialogMaps.show();
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                supportMapFragment.getMapAsync(googleMap -> {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    getMyLocationAddress(latLng);
                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("i am here");
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                    googleMap.addMarker(markerOptions);

                    googleMap.setOnMapClickListener(latLng1 -> {
                        googleMap.clear();
                        MarkerOptions newmarker = new MarkerOptions().position(latLng1).title("set this location");
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                        googleMap.addMarker(newmarker);
                        getMyLocationAddress(latLng1);
                    });
                });
            }
        });

    }

    public String getMyLocationAddress(LatLng latLng) {
        List<Address> addresses;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String currentaddress;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            currentaddress = addresses.get(0).getAddressLine(0);
            binding.arrdress.setText(currentaddress);

        } catch (IOException e) {
            currentaddress = "could no find addrss";
            e.printStackTrace();
        }
        return currentaddress;
    }


}