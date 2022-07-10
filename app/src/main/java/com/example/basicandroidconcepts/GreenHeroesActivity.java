package com.example.basicandroidconcepts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class GreenHeroesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] characterNameList = {"Green Arrow", "Green Lantern", "Hulk", "Robin", "Martian", "Hornet", "Vision", "Swamp Thing", "Piccolo",
        "Beast Boy", "Mutant Turtles"};

    String[] characterDescription = {"Green Arrow Description", "Green Lantern", "Hulk", "Robin", "Martian", "Hornet", "Vision", "Swamp Thing", "Piccolo",
            "Beast Boy", "Mutant Turtles"};

    int[] characterImages = {R.drawable.green_arrow, R.drawable.green_lantern, R.drawable.hulk, R.drawable.robin, R.drawable.martian,
            R.drawable.hornet, R.drawable.vision, R.drawable.swamp_thing, R.drawable.piccolo, R.drawable.beast_boy,
        R.drawable.mutant_turtles};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_heroes);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GreenHeroesAdapter(this, characterNameList, characterDescription, characterImages);
        recyclerView.setAdapter(adapter);
    }
}