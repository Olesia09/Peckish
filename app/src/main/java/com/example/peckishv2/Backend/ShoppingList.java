package com.example.peckishv2.Backend;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peckishv2.Adapters.ShoppingListItemsAdapter;
import com.example.peckishv2.Models.ShoppingListItems;
import com.example.peckishv2.R;
import com.example.peckishv2.Utils.DataBaseManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShoppingList extends Fragment implements DialogCloseListener{

    RecyclerView toBuy_listView;
    FloatingActionButton btn;
    ShoppingListItemsAdapter add_ingredient_adapter;
    List<ShoppingListItems> ingredient_list;
    DataBaseManager db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
    {
        toBuy_listView = toBuy_listView.findViewById(R.id.recyclerView);
        toBuy_listView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ingredient_list = new ArrayList<>();
        db = new DataBaseManager(requireContext());
        db.openDB();

        add_ingredient_adapter = new ShoppingListItemsAdapter(db, this);
        toBuy_listView.setAdapter(add_ingredient_adapter);

        ingredient_list = db.getAllItems();
        Collections.reverse(ingredient_list);
        add_ingredient_adapter.setItem(ingredient_list);

        btn = btn.findViewById(R.id.add_item);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShoppingItem.newInstance().show(requireActivity().getSupportFragmentManager(), AddShoppingItem.tag);
            }
        });

        return inflater.inflate(R.layout.fragment_shopping_list,container,false);
    }

    @Override
    public void handleDialogClose(DialogInterface dialog)
    {
        ingredient_list = db.getAllItems();
        Collections.reverse(ingredient_list);
        add_ingredient_adapter.setItem(ingredient_list);
        add_ingredient_adapter.notifyDataSetChanged();
    }
}
