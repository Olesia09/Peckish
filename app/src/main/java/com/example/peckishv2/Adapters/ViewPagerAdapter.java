package com.example.peckishv2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peckishv2.Backend.ViewPagerItem;
import com.example.peckishv2.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>{

    ArrayList<ViewPagerItem> viewPagerItemsList;

    public ViewPagerAdapter(ArrayList<ViewPagerItem> viewPagerItemsList) {
        this.viewPagerItemsList = viewPagerItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ViewPagerItem viewPagerItem = viewPagerItemsList.get(position);

        holder.image.setImageResource(viewPagerItem.image_id);
        holder.recipe_header.setText(viewPagerItem.heading);

    }

    @Override
    public int getItemCount() {
        return viewPagerItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView recipe_header;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.recipe_image);
            recipe_header = itemView.findViewById(R.id.recipe_name);
        }
    }
}
