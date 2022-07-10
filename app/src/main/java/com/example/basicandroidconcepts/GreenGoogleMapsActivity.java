package com.example.basicandroidconcepts;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class GreenGoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    //reference to the UI elements
    private Button btnBotanicalGardens, btnDrawUFSEastCentre, btnBack, btnDataManipulation;
    SupportMapFragment mapFragment;
    GoogleMap map;

    private static final int PERMISSIONS_FINE_LOCATION = 99;

    // current location
    Location currentLocation;

    //list of saved location
    List<Location> savedLocations;

    //Location request is a config file for all settings related to FusedLocationProviderClient
    LocationRequest locationRequest;

    LocationCallback locationCallBack;

    //Google's API for location services
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_google_maps);

        // giving each UI variable a value
        btnBotanicalGardens = findViewById(R.id.btnBotanicalGardens);
        btnDrawUFSEastCentre = findViewById(R.id.btnDrawUFSEastCentre);
        btnBack = findViewById(R.id.btnBack);
        btnDataManipulation = findViewById(R.id.btnDataManipulation);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);

        //Go to HomeActivity when button is triggered
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GreenGoogleMapsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        //Go to botanical gardens on the map when button is clicked
        btnBotanicalGardens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                LatLng botanicalGarden = new LatLng(-29.081114, 26.207333);
                map.addMarker(new MarkerOptions().position(botanicalGarden).title("Botanical Gardens - Bloemfontein")
                        .icon(BitmapDescriptorFactory.defaultMarker(HUE_RED)));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(botanicalGarden, 18));


                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        Toast.makeText(GreenGoogleMapsActivity.this, marker.getTitle(), Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
            }
        });

        //Draw a perimeter around UFS Visitors centre on the map when button is clicked
        btnDrawUFSEastCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                LatLng locationUFSEastCentre = new LatLng(-29.110725, 26.184762);
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(locationUFSEastCentre, (float) 17.85));
                map.addCircle(new CircleOptions()
                        .center(locationUFSEastCentre)
                        .radius(70)
                        .strokeColor(Color.GREEN)
                        .fillColor(Color.parseColor("#500084d3")));
            }
        });

        //Go to DataManipulationActivity page when button is clicked
        btnDataManipulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GreenGoogleMapsActivity.this, DataManipulationActivity.class);
                startActivity(intent);
            }
        });

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng adminBuilding = new LatLng(-29.111845, 26.189097);
        map.addMarker(new MarkerOptions().position(adminBuilding).title("UFS Admin Building Marker")
                .icon(BitmapDescriptorFactory.defaultMarker(HUE_GREEN)));
        map.moveCamera(CameraUpdateFactory.newLatLng(adminBuilding));

        LatLng lastLocation = adminBuilding;

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 1));

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Toast.makeText(GreenGoogleMapsActivity.this, marker.getTitle(), Toast.LENGTH_LONG).show();
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 12));
                return false;
            }
        });
    }

}