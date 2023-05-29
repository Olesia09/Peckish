package com.example.peckishv2.Backend;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peckishv2.Adapters.ShoppingListItemsAdapter;

public class RecyclerItemTouchManager extends ItemTouchHelper.SimpleCallback {

    ShoppingListItemsAdapter adapter;

    public  RecyclerItemTouchManager(ShoppingListItemsAdapter adapter)
    {
        super(0, ItemTouchHelper.LEFT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,@NonNull RecyclerView.ViewHolder holder,@NonNull RecyclerView.ViewHolder target)
    {
        return false;
    }

    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder holder, int dir)
    {
        final int pos = holder.getAbsoluteAdapterPosition();
        if(dir == ItemTouchHelper.LEFT)
        {
            adapter.editItem(pos);
        }
    }

}
