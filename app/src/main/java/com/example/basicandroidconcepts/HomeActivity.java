package com.example.basicandroidconcepts;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.BuildConfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class HomeActivity extends AppCompatActivity {

    //reference to the UI elements
    private Button btnPlantsHousePlan, btnMapFunctionality, btnPlantsAnimation, btnGreenCharacters, btnCamera, btnVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // giving each UI variable a value
        btnPlantsHousePlan = findViewById(R.id.btnPlantsHousePlan);
        btnMapFunctionality = findViewById(R.id.btnMapFunctionality);
        btnPlantsAnimation = findViewById(R.id.btnPlantsAnimation);
        btnGreenCharacters = findViewById(R.id.btnGreenHeroes);
        btnCamera = findViewById(R.id.btnCamera);
        btnVideo = findViewById(R.id.btnVideo);

        //Navigate to PlantsHousePlan activity when the button is clicked
        btnPlantsHousePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PlantsHousePlanActivity.class);
                startActivity(intent);
            }
        });

        //Navigate to MapFunctionality activity when the button is clicked and permission is granted
        btnMapFunctionality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(HomeActivity.this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(HomeActivity.this, GreenGoogleMapsActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            openSettings();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

            }
        });

        //Navigate to PlantsAnimation activity when the button is clicked
        btnPlantsAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PlantsAnimationActivity.class);
                startActivity(intent);
            }
        });

        //Navigate to GreenCharacters activity when the button is clicked
        btnGreenCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GreenHeroesActivity.class);
                startActivity(intent);
            }
        });

        //Navigate to Camera activity when the button is clicked
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        //Navigate to Video activity when the button is clicked
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openSettings(){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}