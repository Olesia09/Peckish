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

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.RecipeViewHolder>{
    Context context;
    List<Recipe> list;

    public ViewPagerAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager, parent, false);

        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        Picasso.get().load(list.get(position).image).into(holder.image);
        holder.recipe_header.setText(list.get(position).title);
        holder.recipe_header.setSelected(true);
        holder.prepare_time.setText(list.get(position).readyInMinutes + " Min");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder
    {
        CardView viewPager_cardView;
        ImageView image;
        TextView recipe_header, prepare_time;


        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            viewPager_cardView = itemView.findViewById(R.id.viewPager_cardView);
            image = itemView.findViewById(R.id.recipe_image);
            recipe_header = itemView.findViewById(R.id.recipe_name);
            prepare_time = itemView.findViewById(R.id.prepare_time);
        }
    }
}
