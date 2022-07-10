package com.example.basicandroidconcepts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
