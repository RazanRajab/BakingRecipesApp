package com.example.bakingrceipesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bakingrceipesapp.recipeAPI.Recipe;
import com.example.bakingrceipesapp.recipeAPI.recipeService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getRecipes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeService service = retrofit.create(recipeService.class);
        //Run the Request
        service.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> r = response.body();

                for (int i = 0; i < r.size(); i++) {
                    recipes.add(new Recipe(r.get(i).getId(), r.get(i).getName(),
                            r.get(i).getIngredients(), r.get(i).getSteps(),
                            r.get(i).getServings(), r.get(i).getImage()));
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }
}
