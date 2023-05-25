package com.example.peckishv2.Listeners;

import com.example.peckishv2.Models.RecipeResponse;

public interface RecipeListener {
    void fetch(RecipeResponse result, String message);
    void error(String message);
}
