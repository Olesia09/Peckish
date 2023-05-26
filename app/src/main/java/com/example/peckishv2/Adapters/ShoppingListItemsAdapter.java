package com.example.peckishv2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.peckishv2.Backend.ShoppingList;
import com.example.peckishv2.Models.ShoppingListItems;
import com.example.peckishv2.R;

import java.util.List;


public class ShoppingListItemsAdapter extends RecyclerView.Adapter<ShoppingListItemsAdapter.ViewHolder> {

    List<ShoppingListItems> ingredientList;
    ShoppingList shoppingList;


    public ShoppingListItemsAdapter(ShoppingList shoppingList)
    {
        this.shoppingList = shoppingList;
    }

    public void onBindViewHolder(ViewHolder holder, int pos)
    {
        ShoppingListItems shoppingListItems = ingredientList.get(pos);
        holder.item.setText(shoppingListItems.getIngredient());
        holder.item.setChecked(toBool(shoppingListItems.getStatus()));
    }

    public int getItemCount() {
        return ingredientList.size();
    }

    private boolean toBool(int num)
    {
        return num!=0;
    }

    public void setItem(List<ShoppingListItems> ingredientList)
    {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shopping_list_item, parent, false);
        return new ViewHolder(item);
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder
    {
        CheckBox item;

        ViewHolder(View v)
        {
            super(v);
            item = v.findViewById(R.id.to_buy_check);
        }
    }
}
