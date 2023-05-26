package com.example.peckishv2.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.peckishv2.Backend.AddShoppingItem;
import com.example.peckishv2.Backend.ShoppingList;
import com.example.peckishv2.Models.ShoppingListItems;
import com.example.peckishv2.R;
import com.example.peckishv2.Utils.DataBaseManager;

import java.util.List;


public class ShoppingListItemsAdapter extends RecyclerView.Adapter<ShoppingListItemsAdapter.ViewHolder> {

    List<ShoppingListItems> ingredientList;
    ShoppingList shoppingList;
    DataBaseManager db;


    public ShoppingListItemsAdapter(DataBaseManager db, ShoppingList shoppingList)
    {
        this.db = db;
        this.shoppingList = shoppingList;
    }

    public void onBindViewHolder(ViewHolder holder, int pos)
    {
        db.openDB();
        ShoppingListItems shoppingListItems = ingredientList.get(pos);
        holder.item.setText(shoppingListItems.getIngredient());
        holder.item.setChecked(toBool(shoppingListItems.getStatus()));
        holder.item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(shoppingListItems.getId(), 1);
                }
                else{
                    db.updateStatus(shoppingListItems.getId(), 0);
                }
            }
        });
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

    public Context getContext(){
        return shoppingList.requireContext();
    }

    public void editItem(int pos)
    {
        ShoppingListItems item = ingredientList.get(pos);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("item", item.getIngredient());
        AddShoppingItem fragment = new AddShoppingItem();
        fragment.setArguments(bundle);
        fragment.show(shoppingList.requireActivity().getSupportFragmentManager(), AddShoppingItem.tag);
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
