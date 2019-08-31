package com.example.bakingrceipesapp.recipeAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface recipeService {

    @GET("59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();
}
