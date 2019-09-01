package com.example.bakingrceipesapp.recipeAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface recipeService {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
