package com.example.basicandroidconcepts;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PlantsHousePlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants_house_plan);

        //Pushing the cPlantsHousePlan class to PlantsHousePlanActivity
        cPlantsHousePlan cPlantsHousePlan = new cPlantsHousePlan(PlantsHousePlanActivity.this);
        setContentView(cPlantsHousePlan);
        cPlantsHousePlan.setBackgroundColor(Color.GRAY);
    }
}