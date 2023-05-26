package com.example.peckishv2.Backend;

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
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder holder, RecyclerView.ViewHolder target)
    {
        return false;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder holder, int dir)
    {
        final int pos = holder.getAbsoluteAdapterPosition();
        if(dir == ItemTouchHelper.LEFT)
        {
            adapter.editItem(pos);
        }
    }

}
