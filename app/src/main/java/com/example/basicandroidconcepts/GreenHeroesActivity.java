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

    String[] characterDescription = {"Peak human physical and mental conditioning Highly skilled martial artist and hand-to-hand " +
            "combatant Master archer and marksman Expert acrobat Utilizing high-tech equipment, armor, compound bow, and various types of specialty arrows",
            "The Green Lantern has used this ring to fly, to create a green energy which can be used in a variety of ways, to hypnotize people, to become " +
                    "invisible, to translate languages, to pass through solid objects, to heal, to paralyze enemies, and even to time travel.",
            "The Hulk is the alter-ego of scientist Bruce Banner. Whenever Banner's heart rate rises above 200 he transforms into a super-strong titan",
            "Robin was introduced as a junior crime-fighting partner for Batman, and he served as the template for later teenage sidekicks.",
            "One of the last surviving members of his species, the Martian Manhunter wields such powers as the ability to shape-shift, telepathy, flight, invisibility etc",
            "The Green Hornet was a skilled fighter. He was a good shot, and had two special guns he tended to use",
            "The android called Vision defies physics and fights as an Avenger with the power of density manipulation and his flawless computer brain",
            "A humanoid/plant elemental creature",
            "A wise and cunning warrior, he was the main antagonist in the final saga of Dragon Ball, the Piccolo Jr. Saga, and was thus a ruthless enemy of Goku.",
            "Beast Boy is a fictional superhero who possesses the ability to shapeshift into different animals",
            "The Turtles' hold distinct personality traits and skills, including: Raphael, as the oldest and biggest brother, he is the leader full of enthusiasm and bravado; " +
                    "Leonardo, the self-professed 'coolest' brother possesses irreverent charm and a rebel heart; Donatello, a mechanical genius and tech wizard whose ninja skills"};

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