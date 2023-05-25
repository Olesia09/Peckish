package com.example.peckishv2.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peckishv2.R;

public class RecipeAdapter  extends RecyclerView.Adapter<RecipeViewHolder>{
    Context context;
    List<Recipe> list;

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class RecipeViewHolder extends RecyclerView.ViewHolder
{
    CardView recipe_container;
    TextView recipe_title;
    ImageView recipe_image;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        recipe_container = itemView.findViewById(R.id.recipe_container);
        recipe_title = itemView.findViewById(R.id.recipe_title);
        recipe_image = itemView.findViewById(R.id.recipe_image);

    }
}
