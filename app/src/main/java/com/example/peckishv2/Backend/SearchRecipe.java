package com.example.peckishv2.Backend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.peckishv2.Adapters.ViewPagerAdapter;
import com.example.peckishv2.Listeners.RecipeListener;
import com.example.peckishv2.Models.RecipeResponse;
import com.example.peckishv2.R;
import org.jetbrains.annotations.Nullable;


public class SearchRecipe extends Fragment {
    RequestManager manager;
    ViewPagerAdapter adapter;

    ViewPager2 suggested_recipes;
    ImageButton search_recipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
    {
        View rootView = inflater.inflate(R.layout.fragment_search_recipe, container, false);

        manager = new RequestManager(requireContext());
        manager.getRecipe(recipeListener);

        suggested_recipes = rootView.findViewById(R.id.recipe_display);
        search_recipe = rootView.findViewById(R.id.search_recipe_btn);

        search_recipe.setOnClickListener(view -> {
            // Handle the click event for the search_recipe button
        });

        return rootView;

    }

    private final RecipeListener recipeListener = new RecipeListener() {
        @Override
        public void fetch(RecipeResponse result, String message) {

            adapter = new ViewPagerAdapter(requireContext(), result.recipes);

            suggested_recipes.setAdapter(adapter);
            suggested_recipes.setClipToPadding(false);
            suggested_recipes.setClipChildren(false);
            suggested_recipes.setOffscreenPageLimit(2);
            suggested_recipes.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        }

        @Override
        public void error(String message) {
            Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT);
        }
    };
}
