package com.example.basicandroidconcepts;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DataManipulationActivity extends AppCompatActivity implements OnMapReadyCallback {

    //reference to the UI elements
    private Button btnSave, btnDisplay, btnBack;
    private SearchView searchView;
    private EditText edtSearchDescription;
    SupportMapFragment mapFragment;
    GoogleMap map;

    AlertDialog.Builder builder;

    String location;
    List<Address> addressList;
    DataBaseHelper db;

    LocationsModel locationsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_manipulation);

        // giving each UI variable a value
        btnSave = findViewById(R.id.btnSave);
        btnDisplay = findViewById(R.id.btnDisplay);
        btnBack = findViewById(R.id.btnBack);

        searchView = findViewById(R.id.searchView);
        edtSearchDescription = findViewById(R.id.edtSearchDescription);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);

        db = new DataBaseHelper(DataManipulationActivity.this);

        //Go to HomeActivity when button is triggered
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataManipulationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        //Save location and description when button is clicked
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    locationsModel = new LocationsModel(-1,
                            searchView.getQuery().toString(), edtSearchDescription.getText().toString());
                }catch (Exception e)
                {
                    Toast.makeText(DataManipulationActivity.this, "Error saving location", Toast.LENGTH_LONG).show();
                    locationsModel = new LocationsModel(-1, "error", "No Description");
                }

                boolean success = db.addOne(locationsModel);

                Toast.makeText(DataManipulationActivity.this, "Success: Location Added", Toast.LENGTH_LONG).show();
            }
        });

        builder = new AlertDialog.Builder(this);

        //Display searched locations when button is clicked
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getAllData();
                if(res.getCount() == 0){
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id: "+ res.getString(0)+"\n");
                    buffer.append("Name: "+ res.getString(1)+"\n");
                    buffer.append("Description: "+ res.getString(2)+"\n\n");
                }

                ShowMessage("Saved Location(s)", buffer.toString());
            }
        });

        //Search a location and put a marker on the location
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                location = searchView.getQuery().toString();
                addressList = null;

                if(location != null || !location.equals(""))
                {
                    Geocoder geocoder = new Geocoder((DataManipulationActivity.this));
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng startLocation = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(startLocation).title("Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(startLocation));

        LatLng lastLocation = startLocation;

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 0));

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Toast.makeText(DataManipulationActivity.this, marker.getTitle(), Toast.LENGTH_LONG).show();
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 12));
                return false;
            }
        });
    }

    public void ShowMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

