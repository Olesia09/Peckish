package com.example.peckishv2.Backend;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);


        db = new DataBaseManager(requireContext());
        db.openDB();

        toBuy_listView = view.findViewById(R.id.recyclerView);
        toBuy_listView.setLayoutManager(new LinearLayoutManager(requireContext()));
        add_ingredient_adapter = new ShoppingListItemsAdapter(db, this);
        toBuy_listView.setAdapter(add_ingredient_adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new RecyclerItemTouchManager(add_ingredient_adapter));
        helper.attachToRecyclerView(toBuy_listView);

        btn = view.findViewById(R.id.add_item);

        ingredient_list = db.getAllItems();
        Collections.reverse(ingredient_list);

        add_ingredient_adapter.setItem(ingredient_list);
        btn.setOnClickListener(v -> AddShoppingItem.newInstance().show(requireActivity().getSupportFragmentManager(), AddShoppingItem.tag));

        return view;
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
