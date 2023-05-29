package com.example.peckishv2.Backend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

//import com.example.peckishv2.Adapters.RecipeAdapter;
import com.example.peckishv2.Adapters.ViewPagerAdapter;
import com.example.peckishv2.Listeners.RecipeListener;
import com.example.peckishv2.Models.RecipeResponse;
import com.example.peckishv2.R;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SearchRecipe extends Fragment {

    RequestManager manager;
  //  RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;

    ViewPager2 suggested_recipes, saved_recipes;
    ArrayList<ViewPagerItem> viewPagerItemsList;
    ImageButton search_recipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
    {
       // manager = new RequestManager(requireContext());
       // manager.getRecipe(recipeListener);

        suggested_recipes = suggested_recipes.findViewById(R.id.recipe_display);
        saved_recipes = saved_recipes.findViewById(R.id.saved_recipes);
        search_recipe = search_recipe.findViewById(R.id.search_recipe_btn);

        int[] images = {R.drawable};
        String[] heading = {R.string};

         viewPagerItemsList = new ArrayList<>();

         for (int i = 0; i < images.length; i++)
         {
             ViewPagerItem viewPagerItem = new ViewPagerItem(images[i],heading[i]);
             viewPagerItemsList.add(viewPagerItem);
         }

        ViewPagerAdapter adapter = new ViewPagerAdapter(viewPagerItemsList);

         suggested_recipes.setAdapter(adapter);
         suggested_recipes.setClipToPadding(false);
         suggested_recipes.setClipChildren(false);
         suggested_recipes.setOffscreenPageLimit(2);
         suggested_recipes.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);


        return inflater.inflate(R.layout.fragment_search_recipe,container,false);

    }
/*
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
    };*/
}
