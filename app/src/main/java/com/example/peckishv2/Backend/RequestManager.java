package com.example.peckishv2.Backend;

import android.content.Context;

import com.example.peckishv2.Listeners.RecipeListener;
import com.example.peckishv2.Models.RecipeResponse;
import com.example.peckishv2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager
{
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context)
    {
        this.context = context;
    }

    public void getRecipe(RecipeListener listener)
    {
        CallRecipe callRecipe = retrofit.create(CallRecipe.class);
        Call<RecipeResponse> call = callRecipe.callRecipe(context.getString(R.string.api_key), "10");
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (!response.isSuccessful())
                {
                    listener.error(response.message());
                    return;
                }
                listener.fetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }
    private interface CallRecipe
    {
        @GET("recipes/complexSearch")
        Call<RecipeResponse> callRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
    }
}
