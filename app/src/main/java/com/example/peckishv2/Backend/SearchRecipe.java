package com.example.peckishv2.Backend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peckishv2.Adapters.RecipeAdapter;
import com.example.peckishv2.Listeners.RecipeListener;
import com.example.peckishv2.Models.RecipeResponse;
import com.example.peckishv2.R;
import org.jetbrains.annotations.Nullable;

public class SearchRecipe extends Fragment {

    RequestManager manager;
    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
    {
        manager = new RequestManager(requireContext());
        manager.getRecipe(recipeListener);
        return inflater.inflate(R.layout.fragment_search_recipe,container,false);

    }

    private final RecipeListener recipeListener = new RecipeListener() {
        @Override
        public void fetch(RecipeResponse result, String message) {
            View view = getView();
            if (view != null)
            {
                recyclerView = view.findViewById(R.id.recipe_list);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1));
                recipeAdapter = new RecipeAdapter(requireContext(), result.recipes);
                recipeAdapter.setAdapter();
            }
        }

        @Override
        public void error(String message) {
            Toast.makeText(requireContext(), "message", Toast.LENGTH_SHORT).show();
        }
    };
}
