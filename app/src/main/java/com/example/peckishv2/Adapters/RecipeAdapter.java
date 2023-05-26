package com.example.peckishv2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peckishv2.Models.Recipe;
import com.example.peckishv2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter  extends RecyclerView.Adapter<RecipeViewHolder>{
    Context context;
    List<Recipe> list;

    public RecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.display_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int pos) {
        holder.recipe_title.setText(list.get(pos).title);
        holder.recipe_title.setSelected(true);
        Picasso.get().load(list.get(pos).image).into(holder.recipe_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
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
