package com.example.basicandroidconcepts;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlantsAnimationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AnimationDrawable plantsAnimation;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants_animation);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter
                .createFromResource(this, R.array.Activities, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.animation);
        plantsAnimation = (AnimationDrawable) imageView.getBackground();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plantsAnimation.start();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        plantsAnimation.start();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();

        switch (item){
            case "Please select an Activity to visit!":
                break;
            case "Home Activity": Intent intent = new Intent(PlantsAnimationActivity.this, HomeActivity.class);
            startActivity(intent);
            break;
            case "Plants House Plan Activity": intent = new Intent(PlantsAnimationActivity.this, PlantsHousePlanActivity.class);
                startActivity(intent);
                break;
            case "Plants Animation Activity": intent = new Intent(PlantsAnimationActivity.this, PlantsAnimationActivity.class);
                startActivity(intent);
                Toast.makeText(this, item, Toast.LENGTH_LONG).show();
                break;
            case "Google Maps Activity": intent = new Intent(PlantsAnimationActivity.this, GreenGoogleMapsActivity.class);
                startActivity(intent);
                break;
            case "Data Manipulation Maps Activity": intent = new Intent(PlantsAnimationActivity.this, DataManipulationActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}