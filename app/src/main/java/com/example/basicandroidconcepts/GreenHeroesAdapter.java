package com.example.basicandroidconcepts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class GreenHeroesAdapter extends RecyclerView.Adapter<GreenHeroesAdapter.ViewHolder> {

    Context context;
    String[] characterNameList;
    String[] characterDescriptionList;
    int[] characterImages;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rowName;
        TextView rowDescription;
        ImageView rowImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.txtTitle);
            rowDescription = itemView.findViewById(R.id.txtDescription);
            rowImageView = itemView.findViewById(R.id.imageView2);
        }
    }

    public GreenHeroesAdapter(Context context, String[] characterNameList, String[] characterDescriptionList, int[] characterImages){
        this.context = context;
        this.characterNameList = characterNameList;
        this.characterDescriptionList = characterDescriptionList;
        this.characterImages = characterImages;
    }

    @NonNull
    @Override
    public GreenHeroesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView rowName = view.findViewById(R.id.txtTitle);
                TextView rowDescription = view.findViewById(R.id.txtDescription);
                Toast.makeText(context, rowName.getText().toString() + "\n" + rowDescription.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GreenHeroesAdapter.ViewHolder holder, int position) {
        holder.rowName.setText(characterNameList[position]);
        holder.rowDescription.setText(characterDescriptionList[position]);
        holder.rowImageView.setImageResource(characterImages[position]);
    }

    @Override
    public int getItemCount() {
        return characterNameList.length;
    }

}
