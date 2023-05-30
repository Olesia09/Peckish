package com.example.peckishv2.Listeners;

import com.example.peckishv2.Models.RecipeResponse;

public interface RecipeListener {
    void didfetch(RecipeResponse result, String message);
    void diderror(String message);
}
