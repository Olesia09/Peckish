package com.example.peckishv2.Backend;

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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends Fragment {

    RecyclerView toBuy_listView;
    ShoppingListItemsAdapter add_ingredient_adapter;
    List<ShoppingListItems> ingredient_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
    {
        toBuy_listView = toBuy_listView.findViewById(R.id.recyclerView);
        toBuy_listView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ingredient_list = new ArrayList<>();

        add_ingredient_adapter = new ShoppingListItemsAdapter(this);
        toBuy_listView.setAdapter(add_ingredient_adapter);

        return inflater.inflate(R.layout.fragment_shopping_list,container,false);
    }
}
