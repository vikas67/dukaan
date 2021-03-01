package com.example.mydukaan.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mydukaan.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class EditBusinessDetailActivity extends AppCompatActivity {

    EditText number;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_business_detail);

        setTitle(getResources().getString(R.string.title_edit_business_detail));
        InitializeActivity();
        ActivityAction();
    }

    private void InitializeActivity(){
        number= findViewById(R.id.number);
        imageView= findViewById(R.id.image);
    }
    private void SetActivityData() {

    }


    private void ActivityAction() {
        number.setOnClickListener(v -> {
//          Snackbar.make(v, R.string.title_numbernotchange, Snackbar.LENGTH_SHORT).setTextColor(R.color.white).setBackgroundTint(R.color.green)
//          .setBackgroundTintMode(PorterDuff.Mode.DARKEN).show();
        });
        imageView.setOnClickListener(v -> {
            ImagePicker.Companion.with(this)
                    .cameraOnly()
                    .cropSquare()
                    .start();
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri fileUri = data.getData();
            imageView.setImageURI(fileUri);

        }

    }
}

